package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;

import java.time.LocalDate;

public class DeleteBook implements Command {

    private final int bookId;

    public DeleteBook(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book book = library.getBookByID(bookId);

        if (book.isDeleted()) {
            throw new LibraryException("Book #" + bookId + " is already deleted.");
        }

        if (book.isOnLoan()) {
            throw new LibraryException("Book #" + bookId + " is currently on loan and cannot be deleted.");
        }

        book.setDeleted(true);
        System.out.println("Book #" + bookId + " deleted (soft delete).");
    }
}
