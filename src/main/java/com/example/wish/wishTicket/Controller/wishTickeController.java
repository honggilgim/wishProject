package com.example.wish.wishTicket.Controller;


import com.example.wish.wishTicket.Service.WishService;
import com.example.wish.wishTicket.VO.RequestVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.wish.wishTicket.Entity.WishTicket;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class wishTickeController {

    private final WishService wishService;

    public wishTickeController(WishService wishService) {
        this.wishService = wishService;
    }

    /*
        티켓 리스트 받아오기
        param : ticketStatus
             1. all - 모든 티켓
             2. 기타 - 티켓 상태에 맞는 리스트
         return - TicketList Json 객체
     */
    @RequestMapping("/wishTicekList.do")
    public List<WishTicket> selectWishTicketList(@RequestBody RequestVO requestVO) {
        System.out.println("in");

        if(requestVO != null) {
            if ("all".equals(requestVO.getTicketStatus()))
                return wishService.findAll();
            else
                return wishService.selectWishTicekbyticketStatus(requestVO.getTicketStatus());
        } else {
            return new ArrayList<>();
        }
    }

    /*
        티켓 리스트 받아오기
        param : ticketStatus
             1. all - 모든 티켓
             2. 기타 - 티켓 상태에 맞는 리스트
         return 숫자
     */
    @RequestMapping("/wishTicekListCount.do")
    public long selectWishTicketListCount(@RequestBody RequestVO requestVO) {
        System.out.println("in");
        if(requestVO != null) {
            if ("all".equals(requestVO.getTicketStatus()))
                return wishService.countAll();
            else
                return wishService.countByTicketStatus(requestVO.getTicketStatus());
        } else {
            return 0;
        }
    }

    /*
    티켓 생성 받아오기
    param : comment, count
    count - 티켓 발매 횟수
    commnet - comment
 */
    @RequestMapping("/insertTicket.do")
    public void insertTicket(@RequestParam(defaultValue = "1") int count,
                             @RequestParam(defaultValue = "") String comment) {
        int roop = count;
        for (int i = 0; i < roop; i++) {
            wishService.insertTicket(comment);
        }
    }


    @RequestMapping("/test.do")
    public String test() {
        System.out.println("in");
        return "test";
    }

}
