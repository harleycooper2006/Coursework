package bcu.cmp5332.librarysystem.tests;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
public class BookTest {

    @Test
    void isOnLoan_falseWhenNoLoan() {
        Book b = new Book(15, "Pride and Prejudice", "Jane Austen", "1813", "Whatever");
        assertFalse(b.isOnLoan());
        assertNull(b.getLoan());
    }

    @Test
    void isOnLoan_trueWhenLoanSet() {
        Patron p = new Patron(1, "Ethan", "123", "ethan@mail");
        Book b = new Book(15, "Pride and Prejudice", "Jane Austen", "1813", "Whatever");

        Loan loan = new Loan(p, b, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 8));
        b.setLoan(loan);

        assertTrue(b.isOnLoan());
        assertNotNull(b.getLoan());
    }

    @Test
    void detailsShort_containsIdAndTitle() {
        Book b = new Book(15, "Pride and Prejudice", "Jane Austen", "1813", "Whatever");
        String s = b.getDetailsShort();
        assertTrue(s.contains("15"));
        assertTrue(s.toLowerCase().contains("pride"));
    }
}
