package bcu.cmp5332.librarysystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;

public class ShowPatron implements Command {

	private final int PatronID;
	
	public ShowPatron(int PatronID) {
		this.PatronID = PatronID;
	}
	
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException{
	
	
	Patron patron = library.getPatronByID(PatronID);
	
}
