import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
    private JPanel mainpanel, buttonPanel, addBookPanel, availableBooks, headerPanel;
    private JScrollPane booksScrollPane;
    private JTextArea availableBooksArea;
    private JTextField authorField, titleField, isbnField, genreField, descField;
    private JButton checkOut, returnBook, addBook, signOut, createBookButton;
        
        public MainPageGUI(Library library, User currentUser) {
            this.library = library;
            this.currentUser = currentUser;
            initialize();
    
            List<Book> books = library.getBooks(); // Or however you retrieve the books
            showBooks(books);
        }
        
        private void initialize()
        {
            frame = new JFrame("Main Page");
            frame.setSize(600,400);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            mainpanel = new JPanel();
            mainpanel.setLayout(new BorderLayout());

            //Goes at the top.
            headerPanel = new JPanel();

            JLabel welcomeLabel = new JLabel("Welcome " + currentUser.getUsername());
            headerPanel.add(welcomeLabel);

            mainpanel.add(headerPanel, BorderLayout.NORTH);

    
            //Add Book Panel
            addBookPanel = new JPanel();
            addBookPanel.setLayout(new GridLayout(7,1));
            
            addBookPanel.add(new JLabel("Title"));
            titleField = new JTextField();
            titleField.setPreferredSize(new Dimension(30, 30));
            addBookPanel.add(titleField);

            addBookPanel.add(new JLabel("Author"));
            authorField = new JTextField();
            addBookPanel.add(authorField);

            addBookPanel.add(new JLabel("Isbn"));
            isbnField = new JTextField();
            addBookPanel.add(isbnField);

            addBookPanel.add(new JLabel("Description"));
            descField = new JTextField();
            addBookPanel.add(descField);


            addBookPanel.add(new JLabel("Genre"));
            genreField = new JTextField();
            addBookPanel.add(genreField);

            createBookButton = new JButton("Add Book");
            addBookPanel.add(createBookButton, BorderLayout.SOUTH);
            addBookPanel.setVisible(false);



            mainpanel.add(addBookPanel, BorderLayout.CENTER);



        frame.add(mainpanel);



      


        //Holds Checkout button/Return Button/Signout/ and Add Book
        buttonPanel = new JPanel();
        buttonPanel.setBounds(250,250,250,250);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new GridLayout(4,1));
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
        
        



        //Shows up on the side.
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
        






        //Dealing with button actions

        checkOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });


        returnBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
            
        });


        //Need to deal with the addBook logic. (Almosy done)
        addBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(library.getSignedinUser() != null && library.getSignedinUser().isAdmin())
                {
                    addBookPanel.setVisible(true);
                    addNewBook();
                }
                else
                {
                     addBook.setEnabled(false);
                }
                

            }
                

            
        });

        //Works.
        signOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                library.signOut();
                frame.dispose();

                new LoginGUI(library);
                
                
            }
            
        });

       




        frame.setVisible(true);



    }


    public void showBooks(List<Book> books)
    {
        availableBooksArea.setText("Available books:\n");
        for(Book book : books)
        {
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






                if(title.isEmpty() || author.isEmpty() || isbn.isEmpty() || genre.isEmpty() || description.isEmpty())
                {
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
    
                    // Hide the add book panel
                    addBookPanel.setVisible(false);
    
                    // Notify the user
                    JOptionPane.showMessageDialog(frame, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to add the book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }











            }
            
        });
    }
    
    
}
