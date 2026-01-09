package bcu.cmp5332.librarysystem.tests;
import bcu.cmp5332.librarysystem.model.Book;
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
        assertTrue(s.toLowerCase().contains("ethan"));
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
        Patron p = new Patron(1, "Hayden", "123", "Hayden@mail");
        Book b = new Book(15, "Pride and Prejudice", "Jane Austen", "1813", "Whatever");

        // simulate an existing loan
        LocalDate start = LocalDate.of(2026, 1, 1);
        LocalDate oldDue = LocalDate.of(2026, 1, 8);
        Loan loan = new Loan(p, b, start, oldDue);
        b.setLoan(loan);
        p.addBook(b);

        LocalDate newDue = LocalDate.of(2026, 1, 20);
        p.renewBook(b, newDue);

        assertEquals(newDue, b.getLoan().getDueDate());
    }

    @Test
    void renewBook_throwsIfPatronDoesNotHaveBook() {
        Patron p = new Patron(1, "Hayden", "123", "Hayden@mail");
        Book b = new Book(15, "Pride and Prejudice", "Jane Austen", "1813", "Whatever");

        assertThrows(LibraryException.class, () -> p.renewBook(b, LocalDate.now().plusDays(7)));
    }
}
