package com.bookshop.restservice.infrastructure;

import java.util.*;
//import javax.ejb.EJB;
import com.bookshop.restservice.domain.Book;

/**
 * 
 * @author Arti
 *
 */
public class BookRepositoryBean implements BookRepository {

//	@EJB
	private AuthorRepository authorRepository;
	private final Map<String, Book> books = new HashMap<>();
	
	public BookRepositoryBean(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	    
	@Override
	public Book saveBook(final Book book) {
		authorRepository.saveAuthors(book.getAuthors());
        books.put(book.getId(), book);
        return book;
	}

	@Override
	public Optional<Book> deleteBook(final String id) {
		return Optional.of(books.remove(id));
	}

	@Override
	public List<Book> getAll() {
		return new ArrayList<>(books.values());
	}

	@Override
	public Optional<Book> getById(final String id) {
		return Optional.ofNullable(books.get(id));
	}

}
