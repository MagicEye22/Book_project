package com.zpp.Service.impl;

import com.zpp.Service.BookService;
import com.zpp.dao.BookDao;
import com.zpp.dao.impl.BookDaoImpl;
import com.zpp.pojo.Book;
import com.zpp.pojo.Page;

import java.util.List;

/**
 * @author : zpp
 * @version : 1.0
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        //设置每页显示数量
        page.setPageSize(pageSize);

        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();

        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;

        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }

        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);
        //当前页数据开始的索引
        int begin = (page.getPageNo() - 1) * pageSize;

        //求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);

        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        //设置每页显示数量
        page.setPageSize(pageSize);

        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);

        //设置总记录数
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;

        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }

        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页码
        page.setPageNo(pageNo);
        //当前页数据开始的索引
        int begin = (page.getPageNo() - 1) * pageSize;

        //求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);

        //设置当前页数据
        page.setItems(items);
        return page;
    }

    /**
     *解决删除方法的bug
     * @return 返回总页码
     */
    @Override
    public int pageTotal() {
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //求总页码
        Integer pageTotal = pageTotalCount / Page.PAGE_SIZE;

        if (pageTotalCount % Page.PAGE_SIZE > 0) {
            pageTotal += 1;
        }
        return pageTotal;
    }
}
