package bcu.cmp5332.librarysystem.test.tests;
import bcu.cmp5332.librarysystem.model.Book;

import bcu.cmp5332.librarysystem.commands.RenewBook;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
public class PatronTest {

	
	
	
	
    @Test
    void getDetailsShort_containsIdAndName() {
        Patron p = new Patron(1, "Hayden", "123", "Hayden@mail");
        String s = p.getDetailsShort();
        assertTrue(s.contains("1"));
        assertTrue(s.toLowerCase().contains("hayden"));
    }

    @Test
    void getDetailsLong_whenNoBooks_showsNone() {
        Patron p = new Patron(1, "Hayden", "123", "Hayden@mail");
        String s = p.getDetailsLong().toLowerCase();
        assertTrue(s.contains("borrowed"));
        assertTrue(s.contains("none") || s.contains("(none)"));
    }

    @Test
    void addBook_thenDetailsLong_listsBook() {
        Patron p = new Patron(1, "Hayden", "123", "Hayden@mail");
        Book b = new Book(15, "Pride and Prejudice", "Jane Austen", "1813", "Whatever");
        p.addBook(b);

        String s = p.getDetailsLong();
        assertTrue(s.contains("Pride") || s.contains("Pride and Prejudice"));
    }

    @Test
    void removeBook_removesFromBorrowedList() {
        Patron p = new Patron(1, "Hayden", "123", "Hayden@mail");
        Book b = new Book(15, "Pride and Prejudice", "Jane Austen", "1813", "Whatever");
        p.addBook(b);
        p.removeBook(b);

        String s = p.getDetailsLong();
        assertFalse(s.contains("Pride") || s.contains("Pride and Prejudice"));
    }

    @Test
    void renewBook_updatesDueDate_whenLoanExistsAndBelongsToPatron() throws LibraryException {
        Patron patron = new Patron(1, "Hayden", "123", "Hayden@mail");
        Book book = new Book(15, "Pride and Prejudice", "Jane Austen", "1813", "Whatever");
        

       
        LocalDate start = LocalDate.of(2026, 1, 1);
        LocalDate oldDue = LocalDate.of(2026, 1, 8);
        Loan loan = new Loan(patron, book, start, oldDue);
        book.setLoan(loan);
        patron.addBook(book);

        LocalDate newDue = LocalDate.of(2026, 1, 20);
        

        assertEquals(newDue, book.getLoan().getDueDate());
    }

    @Test
    void renewBook_throwsIfPatronDoesNotHaveBook() {
        Patron patron = new Patron(1, "Hayden", "123", "Hayden@mail");
        Book book = new Book(15, "Pride and Prejudice", "Jane Austen", "1813", "Whatever");

        assertThrows(LibraryException.class, () -> Patron.renewBook(book, LocalDate.now().plusDays(7)));
    }
}
