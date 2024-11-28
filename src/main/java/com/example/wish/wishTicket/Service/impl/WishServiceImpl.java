package com.example.wish.wishTicket.Service.impl;

import com.example.wish.wishTicket.Repository.WishHistoryRepository;
import com.example.wish.wishTicket.Repository.WishRepository;
import com.example.wish.wishTicket.Service.WishService;
import com.example.wish.wishTicket.Entity.WishHistory;
import com.example.wish.wishTicket.Entity.WishTicket;
import com.example.wish.wishTicket.util.hashUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        newTicket.setTicketStatus("BEFORE");
        newTicket.setModifiedDate(LocalDateTime.now().toString());
        newTicket.setComment(comment);
        newTicket.setTicketId(formatString(hashUtil.generate12CharHashWithBase64(LocalDateTime.now().toString())));
        // save() 메소드를 통해 새로운 엔티티를 저장
        newTicket = wishRepository.save(newTicket);

        WishHistory wishHistory = new WishHistory();
        wishHistory.setTicketSeq(newTicket.getSeq());
        wishHistory.setProcessStatus(newTicket.getTicketStatus());
        wishHistory.setProcessDate(newTicket.getModifiedDate());
        wishHistory.setProcessComment(comment);
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
}
