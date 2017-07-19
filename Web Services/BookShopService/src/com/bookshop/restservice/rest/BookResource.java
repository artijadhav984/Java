package com.bookshop.restservice.rest;

import java.util.*;

//import javax.ejb.EJB;
//import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bookshop.restservice.domain.Author;
import com.bookshop.restservice.domain.Book;
import com.bookshop.restservice.infrastructure.BookRepository;
import com.bookshop.restservice.infrastructure.BookRepositoryBean;

/**
 * 
 * @author Arti
 *
 */

//@Stateless
@Path("/books")
public class BookResource {
	
	//@EJB
    private BookRepository bookRepository;
	
	public BookResource() {
		bookRepository = new BookRepositoryBean();
	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String getAllBooks() {
//		return "Hello Arti !";
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBooks() {
		Book book = new Book("0001", "title", "description", null, 21, "http://link", new Date());
		Author author = new Author("000A", "firstName", "lastName", "http://blogUrl");
		book.setAuthors(new ArrayList<Author>(){
			private static final long serialVersionUID = 1L;
		{add(author);}});
		bookRepository.saveBook(book);
		List<Book> books = bookRepository.getAll();
		
		if(books.size() == 0 ) {
			return Response.noContent().build();
		}
		
		GenericEntity<List<Book>> bookWrapper = new GenericEntity<List<Book>>(books) { };
        return Response.ok(bookWrapper).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveBook(Book book) {
		Book persistedBook = bookRepository.saveBook(book);
        return Response.ok(persistedBook).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookById(final @PathParam("id") String id) {
		Book newBook = new Book("0002", "title", "description", null, 21, "http://link", new Date());
		Author newAuthor = new Author("000B", "firstName", "lastName", "http://blogUrl");
		newBook.setAuthors(new ArrayList<Author>() {{
			add(newAuthor);
			}});
		bookRepository.saveBook(newBook);
		
		Optional<Book> book = bookRepository.getById(id);
		
		if(book.isPresent()) {
			return Response.ok(book.get()).build();
		}
		else {
			return Response.noContent().build();
		}
	}
}
