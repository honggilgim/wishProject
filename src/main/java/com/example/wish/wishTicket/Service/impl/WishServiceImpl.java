package com.example.wish.wishTicket.Service.impl;

import com.example.wish.Enum.TicketStatus;
import com.example.wish.wishTicket.Repository.WishHistoryRepository;
import com.example.wish.wishTicket.Repository.WishRepository;
import com.example.wish.wishTicket.Service.WishService;
import com.example.wish.wishTicket.Entity.WishHistory;
import com.example.wish.wishTicket.Entity.WishTicket;
import com.example.wish.wishTicket.VO.RequestVO;
import com.example.wish.wishTicket.util.hashUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Service
public class WishServiceImpl implements WishService {
    private final WishRepository wishRepository;

    private final WishHistoryRepository wishHistoryRepository;

    public WishServiceImpl(WishRepository wishRepository, WishHistoryRepository wishHistoryRepository) {
        this.wishRepository = wishRepository;
        this.wishHistoryRepository = wishHistoryRepository;
    }

    public List<WishTicket> selectWishTicekbyticketStatus(String ticketStatus) {
        return wishRepository.findByTicketStatusOrderByModifiedDate(ticketStatus);
    }

    public long countByTicketStatus(String ticketStatus) {
        return wishRepository.countByTicketStatus(ticketStatus);
    }

    @Transactional
    public void insertTicket(String comment) {
        WishTicket newTicket = new WishTicket();
        newTicket.setTicketStatus(TicketStatus.ISSUED.name());
        newTicket.setModifiedDate(LocalDateTime.now().toString());
        newTicket.setComment(comment);
        newTicket.setTicketId(formatString(hashUtil.
                generate12CharHashWithBase64(LocalDateTime.now().toString())));
        // save() 메소드를 통해 새로운 엔티티를 저장
        newTicket = wishRepository.save(newTicket);

        WishHistory wishHistory = new WishHistory();
        wishHistory.setTicketSeq(newTicket.getSeq());
        wishHistory.setProcessStatus(newTicket.getTicketStatus());
        wishHistory.setProcessDate(newTicket.getModifiedDate());
        wishHistory.setProcessComment("티켓 최초 생성");
         wishHistoryRepository.save(wishHistory);
    }

    public List<WishTicket> findAll() {
        return wishRepository.findAll();
    }

    public Long countAll() {
        return wishRepository.count();
    }

    public static String formatString(String input) {
        // 입력이 12자리 이상이어야 한다는 가정
        if (input == null || input.length() != 12) {
            throw new IllegalArgumentException("Input must be exactly 12 characters long.");
        }

        // 소문자를 대문자로 변환
        String upperCaseString = input.toUpperCase();

        // 4자리마다 -를 추가
        StringBuilder formattedString = new StringBuilder();
        for (int i = 0; i < upperCaseString.length(); i++) {
            // 4자마다 "-"를 삽입
            if (i > 1 && i % 4 == 0) {
                formattedString.append("-");
            }
            formattedString.append(upperCaseString.charAt(i));
        }

        return formattedString.toString();
    }

    @Transactional
    public void uodateTicket(RequestVO requestVO) {
        long seq = requestVO.getSeq();
        WishTicket wishTicket = wishRepository.findById(seq)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + seq));
        String comment = !StringUtils.isEmpty(requestVO.getComment()) ?
                requestVO.getComment() : "";

        if(!"".equals(comment))
            wishTicket.setComment(comment);

        wishTicket.setTicketStatus(TicketStatus.fromValue(requestVO.getProcessState()).toString());
        wishTicket.setModifiedDate(LocalDateTime.now().toString());

        WishHistory wishHistory = new WishHistory();
        wishHistory.setTicketSeq(seq);
        wishHistory.setCurrentState(TicketStatus.fromValue(requestVO.getCurrentState()).toString());
        wishHistory.setProcessStatus(TicketStatus.fromValue(requestVO.getProcessState()).toString());
        wishHistory.setProcessDate(LocalDateTime.now().toString());
        wishHistory.setProcessComment(comment);
        wishHistoryRepository.save(wishHistory);

    }

    @Transactional
    public void deleteTicket(Long seq) {
        wishRepository.deleteById(seq);
        wishHistoryRepository.deleteByTicketSeq(seq);
    }

    public List<WishHistory> findHistoryAll() {
        return wishHistoryRepository.findAll();
    }

    public List<WishHistory> selectHistroyList(long id) {
        return wishHistoryRepository.findWishHistoryByTicketSeqOrderByProcessStatus(id);
    }
}
