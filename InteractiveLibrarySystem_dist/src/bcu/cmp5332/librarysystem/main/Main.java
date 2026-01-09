package bcu.cmp5332.librarysystem.main;
import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.AddBook;
import bcu.cmp5332.librarysystem.commands.AddPatron;
import bcu.cmp5332.librarysystem.commands.BorrowBook;
import bcu.cmp5332.librarysystem.commands.RenewBook;
import bcu.cmp5332.librarysystem.commands.ReturnBook;
import bcu.cmp5332.librarysystem.model.Library;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Main {

    private static boolean isStateChanging(Command command) {
        return (command instanceof AddBook)
                || (command instanceof AddPatron)
                || (command instanceof BorrowBook)
                || (command instanceof RenewBook)
                || (command instanceof ReturnBook);
    }

    public static void main(String[] args) throws IOException, LibraryException {

        Library library = LibraryData.load();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Library system");
        System.out.println("Enter 'help' to see a list of available commands.");

        while (true) {
            System.out.print("> ");
            String line = br.readLine();
            if (line == null) break;

            if (line.equals("exit")) {
                break;
            }

            try {
                Command command = CommandParser.parse(line);

                // execute the command
                command.execute(library, LocalDate.now());

                // 6.5: if state changed, store immediately and rollback on failure
                if (isStateChanging(command)) {
                    try {
                        LibraryData.store(library);
                    } catch (IOException ioEx) {
                        System.out.println("ERROR: Failed to store data to file storage.");
                        System.out.println("Reason: " + ioEx.getMessage());
                        System.out.println("Rolling back changes...");

                        try {
                            library = LibraryData.load();
                            System.out.println("Rollback successful. No changes were saved.");
                        } catch (Exception rollbackEx) {
                            System.out.println("CRITICAL: Rollback failed due to: " + rollbackEx.getMessage());
                            System.out.println("Exiting to prevent inconsistent state.");
                            System.exit(1);
                        }
                    }
                }

            } catch (LibraryException ex) {
                System.out.println(ex.getMessage());
            }
        }

        // still store on exit (optional, but kept)
        try {
            LibraryData.store(library);
        } catch (IOException ioEx) {
            System.out.println("WARNING: Could not save on exit: " + ioEx.getMessage());
        }

        System.exit(0);
    }
}
