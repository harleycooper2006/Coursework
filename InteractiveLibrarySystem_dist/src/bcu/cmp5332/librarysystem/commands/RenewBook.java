package bcu.cmp5332.librarysystem.commands;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import java.time.LocalDate;

public class RenewBook implements Command {

    private final int patronID;
    private final int bookID;

    public RenewBook(int patronID, int bookID) {
        this.patronID = patronID;
        this.bookID = bookID;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {

        Patron patron = library.getPatronByID(patronID);
        Book book = library.getBookByID(bookID);

        if (!book.isOnLoan() || book.getLoan() == null) {
            throw new LibraryException("Book #" + bookID + " is not currently on loan.");
        }

        Loan loan = book.getLoan();
        if (loan.getPatron().getId() != patron.getId()) {
            throw new LibraryException("Book #" + bookID + " is not loaned to Patron #" + patronID + ".");
        }

        LocalDate newDueDate = currentDate.plusDays(library.getLoanPeriod());
        loan.setDueDate(newDueDate);

        System.out.println("Renewed loan: Book #" + bookID + " now due on " + newDueDate);
    }
}
