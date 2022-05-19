package com.goodee.ex08.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.goodee.ex08.domain.BookDTO;

@Repository
public class BookRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate; // Connection, PreparedStatement, ResultSet을 내부에서 사용

	private String sql;

	public List<BookDTO> selectBookList() {
		return null;
	}

	public BookDTO selectBookByNo(long book_no) {
		return null;
	}

	public int insertBook(BookDTO book) {
		return 0;
	}

	public int updateBook(BookDTO book) {
		return 0;
	}

	public int deleteBook(long book_no) {
		return 0;
	}

}