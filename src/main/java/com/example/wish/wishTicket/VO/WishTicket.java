package com.example.wish.wishTicket.VO;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ticket_info")
public class WishTicket {


    // ticketId (Primary Key)
    @Id  // 기본 키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")  // DB 테이블의 ticket_id 컬럼에 매핑
    private Long ticketId;

    // ticketStatus
    @Column(name = "ticket_status", length = 10)
    private String ticketStatus;

    // 수정일자
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private String modifiedDate;

    // 코멘트
    @Column(name = "comment", length = 20)
    private String comment;

}
