package com.bookshop.restservice.infrastructure;

import java.util.List;
import java.util.Optional;

import com.bookshop.restservice.domain.Book;

/**
 * 
 * @author Arti
 *
 */
public interface BookRepository {
	Book saveBook(final Book book);

    Optional<Book> deleteBook(final String id);

    List<Book> getAll();

    Optional<Book> getById(String id);
}
