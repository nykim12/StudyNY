CREATE TABLE BOOK
(
    BOOK_NO NUMBER NOT NULL PRIMARY KEY,
    TITLE VARCHAR2(100 BYTE) NOT NULL,
    AUTHOR VARCHAR2(100 BYTE) NOT NULL,
    PRICE NUMBER,
    PUBDATE VARCHAR2(10 BYTE),
    REGDATE VARCHAR2(10 BYTE)
);

CREATE SEQUENCE BOOK_SEQ NOCACHE;

COMMIT;