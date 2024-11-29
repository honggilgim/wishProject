package com.example.wish.Enum;

public enum TicketStatus {
    // 티켓 생성 전 (티켓 생성 요청 상태)
    BEFORE,
    // 티켓 생성
    ISSUED,
    // 티켓 사용 요청
    REQUEST,
    // 티켓 허용됨
    APPROVED,
    // 티켓 거절됨
    REFUSED,
    // 티켓 닫힘
    CLOSE,
    // 전체 티켓
    ALL;

    // 특정 값으로 enum 찾기
    public static TicketStatus fromValue(String value) {
        for (TicketStatus e : TicketStatus.values()) {
            if (e.name().equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
