package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

public class AddPatron implements Command {

	private final int id;
    private final String name;
    private final String phone;
    

    public AddPatron(String name, String phone, int id) {
        this.name = name;
        this.phone = phone;
        this.id= id;   
        
        }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
    
    
    Patron patron = new Patron(id, name, phone);
    
    library.addPatron(patron);
    

    System.out.println(name + " was added.");

}
}
 