CREATE TABLE FREE_BOARD
(
    FREE_BOARD_NO NUMBER NOT NULL,
    WRITER VARCHAR2(50 BYTE),
    CONTENT VARCHAR2(1000 BYTE),
    IP VARCHAR2(30 BYTE),
    CREATED DATE,
    MODIFIED DATE,
    STATE NUMBER,		/* 정상 : 1, 삭제 : -1 */
    DEPTH NUMBER,		/* 원글 : 0, 댓글 :1 또는 1 이상 */
    GROUP_NO NUMBER,    /* 게시글 : FREE_BOARD_NO, 댓글 : 게시글의 FREE_BOARD_NO */
    GROUP_ORD NUMBER    /* 동일 그룹 내 표시 순서 */
);

CREATE SEQUENCE FREE_BOARD_SEQ NOCACHE;