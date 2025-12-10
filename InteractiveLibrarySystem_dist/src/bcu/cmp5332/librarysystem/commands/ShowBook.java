package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import java.time.LocalDate;

public class ShowBook implements Command{
	
	private final int bookID;
	
	
	public ShowBook(int bookID) {
		this.bookID = bookID;
	}
	
	@Override 
	public void execute(Library library, LocalDate currentDate) throws LibraryException{
		Book book = library.getBookByID(bookID);
		System.out.println(book.getDetailsLong());
	}
	

}
