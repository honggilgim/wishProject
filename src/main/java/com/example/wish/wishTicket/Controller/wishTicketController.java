package com.example.wish.wishTicket.Controller;


import com.example.wish.Enum.TicketStatus;
import com.example.wish.wishTicket.Entity.WishHistory;
import com.example.wish.wishTicket.Service.WishService;
import com.example.wish.wishTicket.VO.RequestVO;
import org.springframework.web.bind.annotation.*;
import com.example.wish.wishTicket.Entity.WishTicket;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class wishTicketController {

    private final WishService wishService;

    public wishTicketController(WishService wishService) {
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
    public List<WishTicket> selectWishTicketList(@RequestParam(defaultValue = "WAIT", name = "ticketStatus") String ticketStatus) {
        System.out.println("in");

            if (TicketStatus.ALL.name().equals(ticketStatus))
                return wishService.findAll();
            else
                return wishService.selectWishTicekbyticketStatus(ticketStatus);
    }

    /*
        티켓 리스트 받아오기
        param : ticketStatus
             1. all - 모든 티켓
             2. 기타 - 티켓 상태에 맞는 리스트
         return 숫자
     */
    @RequestMapping("/wishTicekListCount.do")
    public long selectWishTicketListCount(@RequestParam(defaultValue = "BEFORE" , name = "ticketStatus") String ticketStatus){
        System.out.println("in");
            if (TicketStatus.ALL.name().equals(ticketStatus))
                return wishService.countAll();
            else
                return wishService.countByTicketStatus(ticketStatus);
    }

    /*
    티켓 생성 받아오기
    param : comment, count
    count - 티켓 발매 횟수
    commnet - comment
 */
    @RequestMapping("/insertTicket.do")
    public Boolean insertTicket(@RequestParam(defaultValue = "1", name = "count") String count,
                             @RequestParam(defaultValue = "", name = "comment") String comment) throws Exception {
        int roop = Integer.parseInt(count);
        try {
            for (int i = 0; i < roop; i++) {
                wishService.insertTicket(comment);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        티켓 상태 수정
        param : ticketStatus
             1. seq - 티켓 순번 (고유값)
             2. currentState - 현재값
             3. processState - 진행값
         return - True False
     */
    @RequestMapping("/updateTicket.do")
    public boolean updateTicket(@ModelAttribute RequestVO requestVO) {
        System.out.println("updateTicket");
        try {
            wishService.uodateTicket(requestVO);
            System.out.println("Sucess");
            return true;
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Fail");
        };
        return false;
    }


    /*
        티켓 삭제
        param :
             1. seq - 티켓 순번 (고유값)
         return - True False
     */
    @RequestMapping("/deleteTicket.do")
    public boolean deleteTicket(@RequestParam(name="seq") String seq) {
        System.out.println("deleteTicket");
        Long id = Long.parseLong(seq);
        try {
            wishService.deleteTicket(id);
            System.out.println("Sucess");
            return true;
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Fail");
        };
        return false;
    }

    /*
    티켓 삭제
    param :
         1. seq - 티켓 순번 (고유값)
     return - List<WishHistory>
 */
    @RequestMapping("/selectTicketHistory.do")
    public List<WishHistory> selectTicketHistory(@RequestParam(defaultValue = "0",
            name="seq") String seq) {
        System.out.println("selectTicketHistory");
        Long id = Long.parseLong(seq);
        if(id == 0) {
            return wishService.findHistoryAll();
        } else {
            return wishService.selectHistroyList(id);
        }
    }


    @RequestMapping("/test.do")
    public String test() {
        System.out.println("in");
        return "test";
    }

}
