package bcu.cmp5332.librarysystem.model;
import java.util.Collections;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.util.ArrayList;
import java.util.List;

public class Patron {

    private final int id;
    private final String name;
    private final String phone;
    private final String email;
    private final List<Book> books;

    public Patron(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.books = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public String getEmail() {
    	return email;
    }

    public String getDetailsLong() {
        String details =
            "Patron #" + id + "\n" +
            "Name: " + name + "\n" +
            "Phone: " + phone + "\n" +
            "Email: " + email + "\n";

        details += "Borrowed books:\n";

        if (books.isEmpty()) {
            details += "  (none)\n";
        } else {
            for (Book book : books) {
                details += "  - " + book.getDetailsShort() + "\n";
            }
        }

        return details;
    }

    /* ---- ONLY NEEDED SO BORROW FEATURE CAN DISPLAY BOOKS ---- */
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
    
    public List<Book> getBorrowedBooks() {
    	return Collections.unmodifiableList(books);
    }
    
   

    
    public void borrowBook(Book book) throws LibraryException {
    	
    	if(book == null) {
    		throw new LibraryException("Book not found.");
    	}
    	if (books.contains(book)) {
    		throw new LibraryException("Patron already has this book loaned to them.");
    	}
    	
    	books.add(book);
    	
    }
    
    public void returnBook(Book book) throws LibraryException {
    	if (book == null) {
    		throw new LibraryException("No Book found.");
    	}
    	if (!books.contains(book)) {
    		throw new LibraryException("Book is not on loan to patron.");
    	}
    	
    	books.remove(book);
    }
    
     public String getDetailsShort() 
     {
        return id + ": " + name;
    }
}
