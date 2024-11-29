package com.example.wish.wishTicket.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.wish.wishTicket.Entity.WishTicket;

import java.util.List;

public interface WishRepository extends JpaRepository<WishTicket,Object> {
    List<WishTicket> findByTicketStatusOrderByModifiedDate(String TicketStatus);

    long countByTicketStatus(String ticketStatus);

}
