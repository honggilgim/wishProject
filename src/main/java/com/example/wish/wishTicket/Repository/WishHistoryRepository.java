package com.example.wish.wishTicket.Repository;

import com.example.wish.wishTicket.Entity.WishHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WishHistoryRepository  extends JpaRepository<WishHistory,Object> {

    void deleteByTicketSeq(Long ticketSeq);
}