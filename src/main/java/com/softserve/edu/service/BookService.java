package com.softserve.edu.service;

import com.softserve.edu.dao.BookDAO;
import com.softserve.edu.dto.BookDTO;
import com.softserve.edu.entity.Book;

public class BookService {
    private HibernateManager hibernateManager;
    private BookDAO bookDao;

    public BookService(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
        this.bookDao = new BookDAO(hibernateManager.getSession());
    }

    public void addBook(BookDTO bookDto) {
        Book book =new Book();
        book.setTitle(bookDto.getTitle());
        hibernateManager.transactionBegin();
        bookDao.add(book);
        hibernateManager.transactionEnd();
    }

    public void updateBook(BookDTO oldBookDto, BookDTO newBookDto) {
        for (Book book : bookDao.getAll()) {
            if (book.getTitle().equals(oldBookDto.getTitle())) {
                //Book newBook = new Book();
                //newBook.setId(book.getId());
                //newBook.setTitle(newBookDto.getTitle());
                hibernateManager.transactionBegin();
                // TODO move to DAO
                book.setTitle(newBookDto.getTitle());
                //bookDao.update(newBook);
                hibernateManager.transactionEnd();
            }
        }
    }

}
