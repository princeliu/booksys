package com.booksys.controller;

import com.booksys.domain.Book;
import com.booksys.service.impl.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @date 2017年7月1日 上午10:45:59
 */
@Controller
@RequestMapping("/book")
public class BookController {
 
	@Resource(name="bookService")
	private BookServiceImpl bookService;
	
	@RequestMapping("/toAddBook")
	public String toAddUser(){
		return "/addBook";
	}
	
	@RequestMapping("/doAddBook")
	public String addBook(Book book){
		
		bookService.addBook(book);
		
		return "redirect:/book/getAllBooks";
	}
	
	@RequestMapping("/getAllBooks")
	public String getAllBooks(HttpServletRequest request){
		List<Book> book = bookService.getAllBooks();
		
		request.setAttribute("book", book);
		
		return "/bookManager";
	}
	
	@RequestMapping("/delBook")
	public void delBook(int id,HttpServletResponse response){
		String result = "{\"result\":\"error\"}";
		if(bookService.deleteBook(id)>0){
			result = "{\"result\":\"success\"}";
		}
		PrintWriter out = null;
		response.setContentType("application/json");
		
		try {
			out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/getBook")
	public String getBook(int id,HttpServletRequest request){
		Book book = bookService.getBook(id);
		
		request.setAttribute("book", book);
		return "/editBook";
	}
	
	@RequestMapping("/updateBook")
	public String updateBook(Book book, HttpServletRequest request){
		try{
			bookService.updateBook(book);
			return "redirect:/book/getAllBooks";
		}catch(Exception e){
			e.printStackTrace();
			return "/error";
		}
	}
}

