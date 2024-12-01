package com.example.wish.wishTicket.Service;

import com.example.wish.wishTicket.Entity.WishHistory;
import com.example.wish.wishTicket.Entity.WishTicket;
import com.example.wish.wishTicket.VO.RequestVO;

import java.util.List;

public interface WishService {

    public List<WishTicket> selectWishTicekbyticketStatus(String ticketStatus);

    public long countByTicketStatus(String ticketStatus);

    public void insertTicket(String comment);

    public List<WishTicket> findAll();

    public Long countAll();

    public void uodateTicket(RequestVO requestVO);

    public void deleteTicket(Long seq);

    public List<WishHistory> findHistoryAll();

    public List<WishHistory> selectHistroyList(long id);

}
