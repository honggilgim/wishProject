package com.example.wish.wishTicket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TICKET_HISTORY")
public class WishHistory {
    @Id  // 기본 키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")  // DB 테이블의 SEQ 컬럼에 매핑
    private Long seq;

    // ticketStatus
    @Column(name = "TICKET_SEQ", length = 7)
    private Long ticketSeq;

    // 처리 상태
    @Column(name = "PROCESS_STATUS", length = 20)
    private String processStatus;

    // 수정일자
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PROCESS_DATE")
    private String processDate;


    // 코멘트
    @Column(name = "PROCESS_COMMENT", length = 100)
    private String processComment;
}
