package com.zpp.test;

import com.zpp.dao.BookDao;
import com.zpp.dao.impl.BookDaoImpl;
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
public class BookDaoTest {
    private BookDao bookDao=new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"风声大苏打","10086",new BigDecimal(231231),11111,111,null));
    }

    @Test
    public void deleteBook() {
       bookDao.deleteBook(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"风声","李胜",new BigDecimal(113),11111,1121,null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(11);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());

    }

    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(8, Page.PAGE_SIZE);
        books.forEach(System.out::println);

    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(200,2000));

    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> books = bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,10,50);
        books.forEach(System.out::println);

    }
}