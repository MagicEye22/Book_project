package com.zpp.test;

import com.zpp.Service.BookService;
import com.zpp.Service.impl.BookServiceImpl;
import com.zpp.pojo.Book;
import com.zpp.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author : zpp
 * @version : 1.0
 */
public class BookServiceTest {
    private BookService bookService=new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"天天向上","110",new BigDecimal(20),100,20,null));

    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"天天向上","天天兄弟",new BigDecimal(20.22),100,20,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(12));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        books.forEach(System.out::println);
    }
    @Test
    public void  page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void  pagebyPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }
}