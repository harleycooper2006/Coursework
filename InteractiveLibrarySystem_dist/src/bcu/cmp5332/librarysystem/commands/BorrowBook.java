package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Book;

import java.time.LocalDate;

public class BorrowBook implements Command {
	
	private final int patronID;
	private final int bookID;
	
	public BorrowBook(int patronID, int bookID) {
		
		this.patronID = patronID;
		this.bookID = bookID;
	}
	
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		
		Patron patron = library.getPatronByID(patronID);
		Book book = library.getBookByID(bookID);
		
		LocalDate dueDate = currentDate.plusDays(library.getLoanPeriod());
		
		patron.borrowBook(book, dueDate);
		
		System.out.println("Book # " + bookID + " Issued to patron # " + patronID);
	}
	
	

}
