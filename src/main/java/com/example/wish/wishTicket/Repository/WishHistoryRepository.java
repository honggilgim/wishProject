package com.example.wish.wishTicket.Repository;

import com.example.wish.wishTicket.Entity.WishHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishHistoryRepository  extends JpaRepository<WishHistory,Object> {

    void deleteByTicketSeq(Long ticketSeq);

    List<WishHistory> findWishHistoryByTicketSeqOrderByProcessStatus(Long seq);
}