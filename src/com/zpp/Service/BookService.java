package com.zpp.Service;

import com.zpp.pojo.Book;
import com.zpp.pojo.Page;

import java.util.List;

/**
 * @author : zpp
 * @version : 1.0
 */
public interface BookService {

    

    public void addBook(Book book);

    public void deleteBook(Integer id);

    public  void updateBook(Book book);

    public  Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);

    int pageTotal();
}
