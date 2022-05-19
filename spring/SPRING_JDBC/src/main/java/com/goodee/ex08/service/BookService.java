package com.goodee.ex08.service;

import java.util.List;

import com.goodee.ex08.domain.BookDTO;

public interface BookService {

	public List<BookDTO> findBooks();

	public BookDTO findBookByNo(long book_no);

	public int save(BookDTO book);

	public int change(BookDTO book);

	public int remove(long book_no);

}