package bcu.cmp5332.librarysystem.data;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class PatronDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/patrons.txt";

    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] p = line.split(SEPARATOR, -1);

                int id = Integer.parseInt(p[0]);
                String name = p[1];
                String phone = p[2];
                String email = p[3];

                library.addPatron(new Patron(id, name, phone, email));
            }
        }
    }

    @Override
    public void storeData(Library library) throws IOException {
        Path target = Paths.get(RESOURCE);
        Path tmp = Paths.get(RESOURCE + ".tmp");

        try (PrintWriter out = new PrintWriter(new FileWriter(tmp.toFile()))) {
            for (Patron patron : library.getPatrons()) {
                out.print(patron.getId() + SEPARATOR);
                out.print(patron.getName() + SEPARATOR);
                out.print(patron.getPhone() + SEPARATOR);
                out.print(patron.getEmail() + SEPARATOR);
                out.println();
            }
        }

        Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    }
}
