package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Loan;
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
		
		if (book.getLoan() != null) {
			throw new LibraryException("Book #" + bookID + "is on loan already.");
		}
		
		
		LocalDate dueDate = currentDate.plusDays(library.getLoanPeriod());
		
		Loan loan = new Loan(patron, book, currentDate, dueDate);
		
		book.setLoan(loan);
		patron.borrowBook(book);
		
		System.out.println("Book # " + bookID + " Issued to patron # " + patronID);
	}
	
	

}
