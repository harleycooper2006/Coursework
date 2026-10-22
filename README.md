# Interactive Library Management System

A Java-based library management application designed to manage books, patrons and lending operations through a command-line interface, with a Java Swing graphical interface.

The project focuses on object-oriented design, command-based operation handling, file-based data persistence, input validation and the management of relationships between books, patrons and loan records.

## Overview

The Interactive Library Management System provides a structured way to manage a library's core operations. It models the relationships between library resources and patrons, enforces lending rules, and persists changes to local data files.

The application was developed as a software engineering coursework project, providing practical experience with Java application architecture, object-oriented programming, exception handling, GUI development and unit testing.

## Key Features

- **Book management** — maintain book records containing titles, authors, publication years, publishers and unique identifiers.
- **Patron management** — create and manage library patron records, including contact information and borrowed books.
- **Loan management** — issue books, track loan and due dates, renew loans and process returns.
- **Business rule validation** — check book and patron identifiers, prevent duplicate identifiers in the library model, and validate loan availability and patron ownership during renewal.
- **Persistent storage** — load library data from local files and save state-changing operations to disk.
- **Command-line interaction** — parse user commands and execute library operations through dedicated command classes.
- **Graphical interface** — provide a Java Swing interface with menu navigation and tabular book and patron views.
- **Automated testing** — use JUnit 5 to test model behaviour and selected library operations.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java | Core application logic and object-oriented domain model |
| Java Swing | Desktop graphical user interface |
| Java Collections Framework | Store and retrieve books and patrons using ID-keyed maps |
| Java Time API | Represent loan start dates and due dates using `LocalDate` |
| File I/O | Load and persist library records |
| JUnit 5 | Unit testing and assertions |
| Git & GitHub | Version control and source code management |

## Architecture

The application separates its principal responsibilities into distinct packages, helping to keep domain logic, user interaction and data handling organised.

```text
bcu.cmp5332.librarysystem
├── commands/    # Individual library operations
├── data/        # Data loading and persistence
├── gui/         # Java Swing interface
├── main/        # Application entry point, parsing and exceptions
└── model/       # Core domain entities and library state
```

### Domain model

The model represents the main entities in the system:

- `Library` — manages collections of books and patrons, provides ID-based lookups and defines the standard loan period.
- `Book` — stores bibliographic information and tracks its current loan and availability status.
- `Patron` — represents library users and their borrowing relationships.
- `Loan` — connects a book to a patron and records the loan start date and due date.

Books and patrons are stored in ID-keyed `TreeMap` collections. This supports efficient lookup by identifier and provides ordered iteration by key.

### Command-based operation handling

Library operations are represented by classes implementing a shared `Command` interface. The `CommandParser` interprets user input and creates the appropriate command, which is then executed against the library model.

This separates command interpretation from operation-specific business logic and makes individual operations easier to understand, maintain and test.

Examples include `BorrowBook`, `ReturnBook` and `RenewBook`.

### Data persistence and error handling

The data layer uses dedicated data-manager classes to load and store library records in local files.

State-changing commands are persisted after execution. The command-line application also attempts to reload the previously stored state if a write operation fails, helping to avoid leaving the running application in an inconsistent state.

The application uses a custom `LibraryException` to report domain-level errors, such as requesting a book or patron that does not exist.

## Example Operations

The command-line interface supports operations including:

| Command | Description |
|---|---|
| `addbook` | Add a book to the library |
| `addpatron` | Register a patron |
| `listbooks` | Display the book collection |
| `listpatrons` | Display registered patrons |
| `showbook <id>` | Display details for a book |
| `showpatron <id>` | Display details for a patron |
| `borrow <patronId> <bookId>` | Borrow a book |
| `renew <patronId> <bookId>` | Renew a loan |
| `return <patronId> <bookId>` | Return a borrowed book |
| `deletebook <id>` | Request deletion of a book |
| `loadgui` | Open the graphical interface |

For example, a loan renewal can be requested using:

```text
renew 1 15
```

This identifies patron `1` and book `15`. The renewal operation checks that the book is on loan and that the loan belongs to the specified patron before updating the due date.

The `addbook` and `addpatron` commands prompt for record details interactively.

## Running the Application

### Prerequisites

- A Java Development Kit (JDK).
- An IDE that supports Java projects, such as IntelliJ IDEA, Eclipse or Visual Studio Code with Java extensions.

### Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/harleycooper2006/Coursework.git
   ```

2. Open the project in your Java IDE.
3. Locate the application entry point at `InteractiveLibrarySystem_dist/src/bcu/cmp5332/librarysystem/main/Main.java`.
4. Configure the project source root and ensure the required source files and resources are included in the build.
5. Run the `Main` class and follow the command-line prompts.

The application loads its library records from the configured local data files. Keep the expected resource files available when running the project.

## Testing

The project includes JUnit 5 tests covering selected behaviours of the domain model and library operations.

The test suite is intended to verify areas such as:

- Book and patron detail generation.
- Borrowed-book collection management.
- Loan-related behaviour and due-date handling.
- Error handling for invalid operations.

Tests should be run and reviewed before treating the suite as a verified measure of application correctness.

## Engineering Considerations

The project demonstrates practical application of:

- Object-oriented programming and domain modelling.
- Separation of concerns between business logic, interfaces and persistence.
- Command-based operation dispatch.
- Collection-based entity management and identifier lookups.
- Exception handling and validation of business rules.
- File-based persistence and recovery after storage failures.
- Desktop GUI development and unit-testing practices.

## Future Improvements

Potential areas for further development include:

- Completing and refining GUI workflows for issuing, returning and deleting books.
- Expanding automated tests to cover command execution, persistence failures and loan lifecycle edge cases.
- Improving validation and consistency across book, patron and loan relationships.
- Introducing a standardised build configuration and automated test execution.
- Exploring database-backed persistence as an alternative to local data files.

## Author

**Harley Cooper**
**Hayden Ward**

Computer Science undergraduate interested in software engineering, application development and data-driven systems.

[GitHub Profile](https://github.com/harleycooper2006) · [View Repository](https://github.com/harleycooper2006/Coursework)
