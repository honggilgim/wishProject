package com.example.wish.wishTicket.Service;

import com.example.wish.wishTicket.Entity.WishTicket;

import java.util.List;

public interface WishService {

    public List<WishTicket> selectWishTicekbyticketStatus(String ticketStatus);

    public long countByTicketStatus(String ticketStatus);

    public void insertTicket(String comment);

    public List<WishTicket> findAll();

    public Long countAll();

}
