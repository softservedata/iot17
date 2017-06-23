package com.softserve.edu.app;

import com.softserve.edu.dto.BookDTO;
import com.softserve.edu.service.BookService;
import com.softserve.edu.service.HibernateManager;

public class Appl {

    public static void main(String[] args) {
        HibernateManager hibernateManager = new HibernateManager();
        BookService bookService = new BookService(hibernateManager);
        BookDTO bookDto = new BookDTO("One4");
        bookService.addBook(bookDto);
        bookDto.setTitle("Two4");
        bookService.addBook(bookDto);
        bookService.updateBook(bookDto, new BookDTO("Tree"));
        hibernateManager.closeSession();
        hibernateManager = null;
        System.out.println("done");
    }
    
}
