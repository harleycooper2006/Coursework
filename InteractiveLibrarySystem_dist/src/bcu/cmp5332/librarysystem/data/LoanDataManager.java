package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Scanner;

public class LoanDataManager implements DataManager {

    public final String RESOURCE = "./resources/data/loans.txt";

    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] p = line.split(SEPARATOR, -1);

                int bookId = Integer.parseInt(p[0]);
                int patronId = Integer.parseInt(p[1]);
                LocalDate startDate = LocalDate.parse(p[2]);
                LocalDate dueDate = LocalDate.parse(p[3]);

                Book book = library.getBookByID(bookId);
                Patron patron = library.getPatronByID(patronId);

                Loan loan = new Loan(patron, book, startDate, dueDate);
                book.setLoan(loan);
                patron.addBook(book);
            }
        }
    }

    @Override
    public void storeData(Library library) throws IOException {
        Path target = Paths.get(RESOURCE);
        Path tmp = Paths.get(RESOURCE + ".tmp");

        try (PrintWriter out = new PrintWriter(new FileWriter(tmp.toFile()))) {
            for (Book book : library.getBooks()) {
                if (book.isOnLoan() && book.getLoan() != null) {
                    Loan loan = book.getLoan();

                    out.print(book.getId() + SEPARATOR);
                    out.print(loan.getPatron().getId() + SEPARATOR);
                    out.print(loan.getstartDate() + SEPARATOR);
                    out.print(loan.getDueDate() + SEPARATOR);
                    out.println();
                }
            }
        }

        Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    }
}
