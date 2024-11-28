package com.example.wish.wishTicket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TICKET_INFO")
public class WishTicket {


    // ticketId (Primary Key)
    @Id  // 기본 키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")  // DB 테이블의 SEQ 컬럼에 매핑
    private Long seq;


    // 티켓ID
    @Column(name = "TICKET_ID", length = 20)
    private String ticketId;

    // ticketStatus
    @Column(name = "ticket_status", length = 20)
    private String ticketStatus;

    // 수정일자
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private String modifiedDate;

    // 코멘트
    @Column(name = "comment", length = 100)
    private String comment;

}
