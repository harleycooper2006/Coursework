package bcu.cmp5332.librarysystem.model;

import java.time.LocalDate;

public class Loan {
    
    private Patron patron;
    private Book book;
    private LocalDate startDate;
    private LocalDate dueDate;

    public Loan(Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
        this.patron = patron;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
    
    public Patron getPatron() {
    	return patron;
    }
    
    public Book getBook() {
    	return book;
    }
    
    public LocalDate getstartDate() {
    	return startDate;
    }
    
    public LocalDate getDueDate() {
    	return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
    	this.dueDate = dueDate;
    }
}
 