package com.example.wish.wishTicket.Controller;


import com.example.wish.wishTicket.Service.WishService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.wish.wishTicket.VO.WishTicket;

import java.util.List;

@RestController
@RequestMapping("/api")
public class wishTickeController {

    private final WishService wishService;

    public wishTickeController(WishService wishService) {
        this.wishService = wishService;
    }

    @RequestMapping("/wishTicekList.do")
    public List<WishTicket> selectWishTicketList(@RequestParam("ticketStatus") String ticketStatus) {
        System.out.println("in");
        if("all".equals(ticketStatus))
            return wishService.findAll();
        else
            return wishService.selectWishTicekbyticketStatus(ticketStatus);
    }

    @RequestMapping("/wishTicekListCount.do")
    public long selectWishTicketListCount(@RequestParam("ticketStatus") String ticketStatus) {
        System.out.println("in");
        if("all".equals(ticketStatus))
            return wishService.countAll();
        else
            return wishService.countByTicketStatus(ticketStatus);
    }

    @RequestMapping("/insertTicket.do")
    public void insertTicket(@RequestParam("comment") String comment) {
        System.out.println("in");
        wishService.insertTicket(comment);
    }


    @RequestMapping("/test.do")
    public String test() {
        System.out.println("in");
        return "test";
    }

}
