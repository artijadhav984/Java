package com.bookshop.restservice.infrastructure;

import java.util.*;

import com.bookshop.restservice.domain.Author;

/**
 * 
 * @author Arti
 *
 */
public class AuthorRepositoryBean implements AuthorRepository {

	private final Map<String, Author> authors = new HashMap<>();
	
	@Override
	public Author saveAuthor(final Author author) {
		authors.put(author.getId(), author);
		return author;
	}

	@Override
	public Author deleteAuthor(String id) {
		return authors.remove(id);
	}

	@Override
	public List<Author> getAll() {
		return new ArrayList<>(authors.values());
	}

	@Override
	public Optional<Author> getById(String id) {
		return Optional.ofNullable(authors.get(id));
	}

	@Override
	public List<Author> saveAuthors(List<Author> authorsList) {
		authorsList.forEach(this::saveAuthor);
		return authorsList;
	}

}
