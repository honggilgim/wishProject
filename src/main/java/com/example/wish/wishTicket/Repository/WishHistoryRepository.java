package com.example.wish.wishTicket.Repository;

import com.example.wish.wishTicket.Entity.WishHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishHistoryRepository  extends JpaRepository<WishHistory,Object> {

    void deleteByTicketSeq(Long ticketSeq);
}