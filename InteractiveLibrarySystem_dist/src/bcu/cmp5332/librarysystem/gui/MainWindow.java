package bcu.cmp5332.librarysystem.gui;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Library;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu booksMenu;
    private JMenu membersMenu;

    private JMenuItem adminExit;

    private JMenuItem booksView;
    private JMenuItem booksAdd;
    private JMenuItem booksDel;	
    private JMenuItem booksIssue;
    private JMenuItem booksReturn;

    private JMenuItem memView;
    private JMenuItem memAdd;
    private JMenuItem memDel;

    private Library library;

    public MainWindow(Library library) {

        initialize();
        this.library = library;
    } 
    
    public Library getLibrary() {
        return library;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Library Management System");

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // adding booksMenu menu and menu items
        booksMenu = new JMenu("Books");
        menuBar.add(booksMenu);

        booksView = new JMenuItem("View");
        booksAdd = new JMenuItem("Add");
        booksDel = new JMenuItem("Delete");
        booksIssue = new JMenuItem("Issue");
        booksReturn = new JMenuItem("Return");
        booksMenu.add(booksView);
        booksMenu.add(booksAdd);
        booksMenu.add(booksDel);
        booksMenu.add(booksIssue);
        booksMenu.add(booksReturn);
        for (int i = 0; i < booksMenu.getItemCount(); i++) {
            booksMenu.getItem(i).addActionListener(this);
        }

        // adding membersMenu menu and menu items
        membersMenu = new JMenu("Patrons");
        menuBar.add(membersMenu);

        memView = new JMenuItem("View");
        memAdd = new JMenuItem("Add");
        memDel = new JMenuItem("Delete");

        membersMenu.add(memView);
        membersMenu.add(memAdd);
        membersMenu.add(memDel);

        memView.addActionListener(this);
        memAdd.addActionListener(this);
        memDel.addActionListener(this);

        setSize(800, 500);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
/* Uncomment the following line to not terminate the console app when the window is closed */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);        

    }	

/* Uncomment the following code to run the GUI version directly from the IDE */
//    public static void main(String[] args) throws IOException, LibraryException {
//        Library library = LibraryData.load();
//        new MainWindow(library);			
//    }



    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == adminExit) {
            System.exit(0);
        } else if (ae.getSource() == booksView) {
            displayBooks();
            
        } else if (ae.getSource() == booksAdd) {
            new AddBookWindow(this);
            
        } else if (ae.getSource() == booksDel) {
            
            
        } else if (ae.getSource() == booksIssue) {
            
            
        } else if (ae.getSource() == booksReturn) {
            
            
        } else if (ae.getSource() == memView) {
            try {
				displayPatrons();
			} catch (LibraryException e) {
				
				e.printStackTrace();
			}
            
        } else if (ae.getSource() == memAdd) {
            
            
        }else if (ae.getSource() == memDel) {
            
            
        }

}

    public void displayBooks() {
        List<Book> booksList = library.getBooks();
        // headers for the table
        String[] columns = new String[]{"Title", "Author", "Pub Date", "Status"};

        Object[][] data = new Object[booksList.size()][6];
        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            data[i][0] = book.getTitle();
            data[i][1] = book.getAuthor();
            data[i][2] = book.getPublicationYear();
            data[i][3] = book.getStatus();
        }
        
        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
}
    
    
        
        public void displayPatrons() throws LibraryException{
        	
        	List<Patron> patronsList = library.getPatrons();
        	
        	String[] columns = new String[] {"ID", "Name", "Phone", "Email", "Books on loan"};
        	
        	Object[][] data = new Object[patronsList.size()][5];
        	for (int i = 0; i < patronsList.size(); i++) {
        		Patron patron = patronsList.get(i);
        		
        		data[i][0] = patron.getId();
        		data[i][1] = patron.getName();
        		data[i][2] = patron.getPhone();
        		data[i][3] = patron.getEmail();
        		data[i][4] = patron.getBorrowedBooks().size();
        	}
        	
        	JTable table = new JTable(data, columns);
        	
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
        	@Override
        	public void mouseClicked(java.awt.event.MouseEvent e) {
        		if (e.getClickCount() == 2) {
        	int row = table.getSelectedRow();
        	if (row >= 0) {
        		int patronID = (int) table.getValueAt (row, 0);
        		try {
					showBorrowedPopUp(patronID);
				} catch (LibraryException e1) {
					
					e1.printStackTrace();
				}
        	}
        		}
        	}
        });
        
        this.getContentPane().removeAll();
    	this.getContentPane().add(new JScrollPane(table));
    	this.revalidate();
        }
        	
        
        	
       
        
        private void showBorrowedPopUp(int patronID) throws LibraryException {
        	Patron patron = library.getPatronByID(patronID);
        	
        	List<Book> borrowed = patron.getBorrowedBooks();
        	
        	if (borrowed.isEmpty()) {
        		javax.swing.JOptionPane.showMessageDialog(
        				this,
        				patron.getName() + " has no loaned books.", "Borrowed books",
        				javax.swing.JOptionPane.INFORMATION_MESSAGE);
        		return;
        	}
        	
        	String[] columns = new String[] {"ID", "Title", "Year", "Status"};
        	Object[][] rows = new Object[borrowed.size()] [4];
        	
        	for (int i = 0; i < borrowed.size(); i++) {
        		Book b = borrowed.get(i);
        		rows[i][0] = b.getId();
        		rows[i][1] = b.getTitle();
        		rows[i][2] = b.getPublicationYear();
        		rows[i][3] = b.getStatus();
        	
        	}
        	
        	JTable booksTable = new JTable(rows, columns);
        	JScrollPane scroll = new JScrollPane(booksTable);
        	scroll.setPreferredSize(new java.awt.Dimension(600, 200));
        	
        	javax.swing.JOptionPane.showMessageDialog(
        			this, scroll, "Books loaned to" + patron.getName() + " (#" + patron.getId() + ")",
        					javax.swing.JOptionPane.INFORMATION_MESSAGE
        	);
         
        }
}
