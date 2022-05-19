package com.goodee.ex08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.ex08.domain.BookDTO;
import com.goodee.ex08.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	public BookRepository bookRepository;

	@Override
	public List<BookDTO> findBooks() {
		return bookRepository.selectBookList();
	}

	@Override
	public BookDTO findBookByNo(long book_no) {
		return bookRepository.selectBookByNo(book_no);
	}

	@Override
	public int save(BookDTO book) {
		return bookRepository.insertBook(book);
	}

	@Override
	public int change(BookDTO book) {
		return bookRepository.updateBook(book);
	}

	@Override
	public int remove(long book_no) {
		return bookRepository.deleteBook(book_no);
	}
}