package com.example.wish.wishTicket.VO;

import lombok.Data;

@Data
public class RequestVO {
    public int seq;

    public String currentState;

    public String processState;
    public String comment;
    public String ticketStatus;
}
