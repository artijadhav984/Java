import java.util.LinkedList;
import java.util.ListIterator;

import org.joda.time.DateTime;

/*
 * Assignment 4: Book database
 * Name: Arti
 * Created date: 05, 06 Apr 2017
 * Last updted: 
 */

public class Library {
	static DateTime openingTime = new DateTime().withHourOfDay(9);
	static DateTime closingTime = new DateTime().withHourOfDay(17);
	LinkedList<Book> bookCollection = new LinkedList<Book>();
	String address;

	/*
	 * Constructor initialize address of Library
	 */
	Library(String libAddress) {
		address = libAddress;
	}

	/*
	 * Add book in bookCollection of Library
	 * book: book to be added in collection
	 */
	void addBook(Book book) {
		bookCollection.add(book);
	}

	/*
	 * Print opening hours of Library 
	 */
	static void printOpeningHours() {
		String openAt = openingTime.getHourOfDay() + "am";

		if (openingTime.getHourOfDay() > 12) {
			openAt = (openingTime.getHourOfDay() - 12) + "pm";
		}

		String closeAt = closingTime.getHourOfDay() + "am";

		if (closingTime.getHourOfDay() > 12) {
			closeAt = (closingTime.getHourOfDay() - 12) + "pm";
		}

		System.out.print("Libraries are open daily from " + openAt + " to "
				+ closeAt + ".");
	}

	/*
	 * Print address of Library 
	 */
	void printAddress() {
		System.out.print(address);
	}

	/*
	 * Borrow book from Library
	 * book: book to be borrowed
	 */
	void borrowBook(String title) {
		ListIterator<Book> collection = bookCollection.listIterator();
		boolean bookInCatalog = false;

		while (collection.hasNext()) {
			Book libBook = collection.next();

			if (libBook.getTitle().equals(title)) {
				if (!libBook.isBorrowed()) {
					libBook.rented();
					System.out.println("You successfully borrowed " + title);
				} else {
					System.out.println("Sorry, this book is already borrowed.");
				}

				bookInCatalog = true;
				break;
			}
		}

		if (!bookInCatalog) {
			System.out.println("Sorry, this book is not in our catalog.");
		}
	}

	/*
	 * Print list of available books in Library 
	 */
	void printAvailableBooks() {
		int noBooksAvailable = 0;

		for (Book libBook : bookCollection) {
			if (!libBook.isBorrowed()) {
				System.out.println(libBook.title);
				noBooksAvailable++;
			}
		}

		if (noBooksAvailable == 0) {
			System.out.println("No book in catalog");
		}
	}

	/*
	 * Return book from Library
	 * book: book to be returned
	 */
	void returnBook(String title) {
		boolean bookInCatalog = false;

		for (ListIterator<Book> collection = bookCollection.listIterator(); collection
				.hasNext();) {
			Book libBook = collection.next();

			if (libBook.getTitle().equals(title)) {
				if (libBook.isBorrowed()) {
					libBook.returned();
					System.out.println("You successfully returned " + title);
				} else {
					System.out.println("Sorry, this book is already returned.");
				}

				bookInCatalog = true;
				break;
			}
		}

		if (!bookInCatalog) {
			System.out.println("Sorry, this book is not from our catalog.");
		}
	}

	public static void main(String[] args) {
		// Create two libraries
		Library firstLibrary = new Library("10 Main St.");
		Library secondLibrary = new Library("228 Liberty St.");

		// Add four books to the first library
		firstLibrary.addBook(new Book("The Da Vinci Code"));
		firstLibrary.addBook(new Book("Le Petit Prince"));
		firstLibrary.addBook(new Book("A Tale of Two Cities"));
		firstLibrary.addBook(new Book("The Lord of the Rings"));

		// Print opening hours and the addresses
		System.out.println("Library hours:");
		printOpeningHours();
		System.out.println();

		System.out.println("Library addresses:");
		firstLibrary.printAddress();
		secondLibrary.printAddress();
		System.out.println();

		// Try to borrow The Lords of the Rings from both libraries
		System.out.println("Borrowing The Lord of the Rings:");
		firstLibrary.borrowBook("The Lord of the Rings");
		firstLibrary.borrowBook("The Lord of the Rings");
		secondLibrary.borrowBook("The Lord of the Rings");
		System.out.println();

		// Print the titles of all available books from both libraries
		System.out.println("Books available in the first library:");
		firstLibrary.printAvailableBooks();
		System.out.println();
		System.out.println("Books available in the second library:");
		secondLibrary.printAvailableBooks();
		System.out.println();

		// Return The Lords of the Rings to the first library
		System.out.println("Returning The Lord of the Rings:");
		firstLibrary.returnBook("The Lord of the Rings");
		System.out.println();

		// Print the titles of available from the first library
		System.out.println("Books available in the first library:");
		firstLibrary.printAvailableBooks();
	}
}