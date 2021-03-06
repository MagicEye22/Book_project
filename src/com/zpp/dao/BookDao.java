package com.zpp.dao;

import com.zpp.pojo.Book;

import java.util.List;

/**
 * @author : zpp
 * @version : 1.0
 */
public interface BookDao {
    public int addBook(Book book);

    public int deleteBook(Integer id);

    public  int updateBook(Book book);

    public  Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
