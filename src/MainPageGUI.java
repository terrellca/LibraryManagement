import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.border.Border;
//import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.concurrent.Flow;

import java.util.List;

public class MainPageGUI extends JFrame {

    private Library library;
    private JFrame frame;
    private User currentUser;
    private JPanel mainpanel, buttonPanel, addBookPanel, availableBooks, headerPanel, checkoutBookPanel;
    private JScrollPane booksScrollPane;
    private JTextArea availableBooksArea;
    private JTextField authorField, titleField, isbnField, genreField, descField, rentTitle, rentAuthor;
    private JButton checkOut, returnBook, addBook, signOut, createBookButton, rentBookButton;

    public MainPageGUI(Library library, User currentUser) {
        this.library = library;
        this.currentUser = currentUser;
        initialize();

        List<Book> books = library.getBooks(); // Or however you retrieve the books
        showBooks(books);
    }

    private void initialize() {
        frame = new JFrame("Main Page");
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainpanel = new JPanel();

        CardLayout c1 = new CardLayout();
        mainpanel.setLayout(c1);
        // mainpanel.setLayout(new CardLayout());

        // Goes at the top.
        headerPanel = new JPanel();

        JLabel welcomeLabel = new JLabel("Welcome " + currentUser.getUsername());
        headerPanel.add(welcomeLabel);

        mainpanel.add(headerPanel, BorderLayout.NORTH);

        // Add Book Panel - move for better formatting later. (1/10/25)
        addBookPanel = new JPanel();
        addBookPanel.setLayout(new FlowLayout());

        addBookPanel.add(new JLabel("Title"));
        titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(70, 25));
        addBookPanel.add(titleField);

        addBookPanel.add(new JLabel("Author"));
        authorField = new JTextField();
        authorField.setPreferredSize(new Dimension(60, 25));
        addBookPanel.add(authorField);

        addBookPanel.add(new JLabel("Isbn"));
        isbnField = new JTextField();
        isbnField.setPreferredSize(new Dimension(70, 25));
        addBookPanel.add(isbnField);

        addBookPanel.add(new JLabel("Description"));
        descField = new JTextField();
        descField.setPreferredSize(new Dimension(70, 25));
        addBookPanel.add(descField);

        addBookPanel.add(new JLabel("Genre"));
        genreField = new JTextField();
        genreField.setPreferredSize(new Dimension(70, 25));
        addBookPanel.add(genreField);

        createBookButton = new JButton("Add Book");
        createBookButton.setFocusable(false);
        addBookPanel.add(createBookButton);
        addBookPanel.setVisible(false);

        mainpanel.add(addBookPanel, "AddBookPanel");

        // Rent Book material
        checkoutBookPanel = new JPanel();
        checkoutBookPanel.setLayout(new FlowLayout());

        checkoutBookPanel.add(new JLabel("Title"));
        rentTitle = new JTextField();
        rentTitle.setPreferredSize(new Dimension(200, 30));
        checkoutBookPanel.add(rentTitle);

        checkoutBookPanel.add(new JLabel("Author"));
        rentAuthor = new JTextField();
        rentAuthor.setPreferredSize(new Dimension(200, 30));
        checkoutBookPanel.add(rentAuthor);

        rentBookButton = new JButton("Rent Book");
        rentBookButton.setFocusable(false);
        checkoutBookPanel.add(rentBookButton);

        mainpanel.add(checkoutBookPanel, "CheckoutBookPanel");

        frame.add(mainpanel);

        // Holds Checkout button/Return Button/Signout/ and Add Book
        buttonPanel = new JPanel();
        buttonPanel.setBounds(250, 250, 250, 250);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new GridLayout(4, 1));
        frame.add(buttonPanel, BorderLayout.SOUTH);

        checkOut = new JButton("Checkout Book");
        checkOut.setBackground(Color.CYAN);
        checkOut.setFocusable(false);

        returnBook = new JButton("Return Book");
        returnBook.setBackground(Color.CYAN);
        returnBook.setFocusable(false);

        addBook = new JButton("Add Book");
        addBook.setBackground(Color.CYAN);
        addBook.setFocusable(false);

        signOut = new JButton("SignOut");
        signOut.setBackground(Color.CYAN);
        signOut.setFocusable(false);

        buttonPanel.add(checkOut);
        buttonPanel.add(returnBook);
        buttonPanel.add(addBook);
        buttonPanel.add(signOut);

        // Shows up on the side.
        availableBooks = new JPanel();
        availableBooks.setLayout(new BorderLayout());

        availableBooksArea = new JTextArea();
        availableBooksArea = new JTextArea();
        availableBooksArea.setText("Available Books:\n");
        availableBooksArea.setEditable(false);

        booksScrollPane = new JScrollPane(availableBooksArea);
        booksScrollPane.setVisible(true);
        availableBooks.add(booksScrollPane, BorderLayout.CENTER);

        frame.add(availableBooks, BorderLayout.EAST);

        // Dealing with button actions

        // Working on currently.
        checkOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                c1.show(mainpanel, "CheckoutBookPanel");

            }

        });

        // Not finished
        returnBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("hello im not implemented - returnbook");
            }

        });

        // THe main AddButton button. If the user is an admin it wont disable itself.
        // Then when you click it, it will show the addbook panel.
        // Works - Done
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (library.getSignedinUser() != null && library.getSignedinUser().isAdmin()) {

                    c1.show(mainpanel, "AddBookPanel");

                } else {
                    addBook.setEnabled(false);
                    JOptionPane.showMessageDialog(frame, "Only admins can add books");
                }

            }
        });

        // Works - Done
        signOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                library.signOut();
                frame.dispose();

                new LoginGUI(library);

            }
        });

        addNewBook();
        rentBook();

        frame.setVisible(true);

    }

    public void showBooks(List<Book> books) {
        availableBooksArea.setText("Available books:\n");
        for (Book book : books) {
            availableBooksArea.append(book.getTitle() + "\n");
        }
    }

    private void addNewBook() {
        createBookButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                String isbn = isbnField.getText().trim();
                String genre = genreField.getText().trim();
                String description = descField.getText().trim();

                if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || genre.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must have text");
                    return;
                }

                try {
                    // Create a new Book object
                    Book newBook = new Book(title, author, isbn, genre, description);

                    // Add the book to the library
                    library.addBook(newBook);

                    // Update the book display
                    showBooks(library.getBooks());

                    // Clear the input fields
                    titleField.setText("");
                    authorField.setText("");
                    isbnField.setText("");
                    genreField.setText("");
                    descField.setText("");

                    // Notify the user
                    JOptionPane.showMessageDialog(frame, "Book added successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to add the book: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });
    }

    private void rentBook() {
        rentBookButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String title = rentTitle.getText().trim();
                String author = rentAuthor.getText().trim();

                List<Book> results;
                if (!title.isEmpty()) {
                    results = library.searchBookTitle(title);
                } else if (!author.isEmpty()) {
                    results = library.searchBookAuthor(author);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a title or author", "Input error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (results.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No books found", "Search", JOptionPane.ERROR_MESSAGE);
                } else {
                    StringBuilder searchResults = new StringBuilder();
                    for (int i = 0; i < results.size(); i++) {
                        searchResults.append(i + 1).append(". ").append(results.get(i).getTitle()).append(" by ")
                                .append(results.get(i).getAuthor()).append("\n");
                    }

                    String input = JOptionPane.showInputDialog(frame, "Search Results: \n" + searchResults.toString() + "\nEnter the # of the book to rent.");
                    try {
                        int choice = Integer.parseInt(input) - 1;
                        if(choice >= 0 && choice < results.size())
                        {
                            Book selectedBook = results.get(choice);


                            if(selectedBook.isAvailable())
                            {
                                library.checkoutBook(selectedBook);
                                showBooks(library.getBooks());
                            }
                            else{
                                JOptionPane.showMessageDialog(frame, "That book is not available.", "Checkout error", JOptionPane.ERROR_MESSAGE);
                            }

                        }




                    } catch (NumberFormatException bookrent) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }


                }

                rentTitle.setText("");
                rentAuthor.setText("");

            }
        });

    }

}
