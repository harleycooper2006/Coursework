package bcu.cmp5332.librarysystem.commands;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

public class ReturnBook  implements Command{
	
	private final int patronID;
	private final int bookID;
	
	public ReturnBook(int patronID, int bookID) {
		
		this.patronID = patronID;
		this.bookID = bookID;
		
	}

	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		
		Patron patron = library.getPatronByID(patronID);
		Book book = library.getBookByID(bookID);
		
		patron.returnBook(book);
		
		System.out.println("Book # " + bookID + "Returned by # " + patronID);
		
	}
	
}
