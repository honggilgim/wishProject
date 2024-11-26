package com.example.wish.wishTicket.Service;

import com.example.wish.wishTicket.Repository.WishRepository;
import com.example.wish.wishTicket.VO.WishTicket;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }
    public List<WishTicket> selectWishTicekbyticketStatus(String ticketStatus) {
        return wishRepository.findByTicketStatusOrderByModifiedDate(ticketStatus);
    }

    public long countByTicketStatus(String ticketStatus) {
        return wishRepository.countByTicketStatus(ticketStatus);
    }

    public void insertTicket(String comment) {
        WishTicket newTicket = new WishTicket();
        newTicket.setTicketStatus("WAIT");
        newTicket.setModifiedDate(LocalDateTime.now().toString());
        newTicket.setComment(comment);

        // save() 메소드를 통해 새로운 엔티티를 저장
        wishRepository.save(newTicket);
    }

    public List<WishTicket> findAll() {
        return wishRepository.findAll();
    }

    public Long countAll() {
        return wishRepository.count();
    }

}
