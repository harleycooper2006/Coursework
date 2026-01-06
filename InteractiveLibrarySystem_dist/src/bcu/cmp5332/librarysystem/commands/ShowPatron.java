package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import java.time.LocalDate;

public class ShowPatron implements Command {

    private final int patronID;

    public ShowPatron(int patronID) {
        this.patronID = patronID;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron patron = library.getPatronByID(patronID);
        System.out.println(patron.getDetailsLong());
    }
}
