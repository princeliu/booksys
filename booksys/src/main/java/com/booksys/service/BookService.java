package com.booksys.service;

import com.booksys.domain.Book;

import java.util.List;

public interface BookService
{
	// 添加图书
	int addBook(Book book);

	List<Book> getAllBooks();
	
	int deleteBook(int id);
	Book getBook(int id);
	void updateBook(Book book);
}
