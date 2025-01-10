import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

import java.util.List;



public class MainPageGUI extends JFrame {

    private Library library;
    private JFrame frame;
    private User currentUser;
    private JPanel mainpanel;
    private JScrollPane booksScrollPane;
    private JTextArea availableBooksArea;
    
    public MainPageGUI(Library library, User currentUser) {
        this.library = library;
        this.currentUser = currentUser;
        initialize();
    }
    
    private void initialize()
    {
        frame = new JFrame("Main Page");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.setLayout(new FlowLayout());

        mainpanel = new JPanel(new FlowLayout());

        frame.add(mainpanel);



        JLabel welcomeLabel = new JLabel("Welcome " + currentUser.getUsername());
        mainpanel.add(welcomeLabel);

        //Holds Checkout button/Return Button/Signout/ and Add Book
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(250,250,250,250);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new GridLayout(4,1));
        frame.add(buttonPanel, BorderLayout.SOUTH);



        JButton practiceButton = new JButton("Checkout Book");
        practiceButton.setBackground(Color.CYAN);
        practiceButton.setFocusable(false);
        
        JButton returnBook = new JButton("Return Book");
        returnBook.setBackground(Color.CYAN);
        returnBook.setFocusable(false);

        JButton addBook = new JButton("Add Book");
        addBook.setBackground(Color.CYAN);
        addBook.setFocusable(false);


        JButton signOut = new JButton("SignOut");
        signOut.setBackground(Color.CYAN);
        signOut.setFocusable(false);




        buttonPanel.add(practiceButton);
        buttonPanel.add(returnBook);
        buttonPanel.add(addBook);
        buttonPanel.add(signOut);
        
        



        //Shows up on the side.
        JPanel availableBooks = new JPanel();
        availableBooks.setLayout(new BorderLayout());

        JTextArea availableBooksArea = new JTextArea();
        availableBooksArea = new JTextArea();
        availableBooksArea.setText("Available Books:\n");
        availableBooksArea.setEditable(false);


        booksScrollPane = new JScrollPane(availableBooksArea);
        booksScrollPane.setVisible(true);
        availableBooks.add(booksScrollPane, BorderLayout.CENTER);

        frame.add(availableBooks, BorderLayout.EAST);
        


        /*
         * 
         * 
         *         JButton checkoutButton = new JButton("Checkout Books");
        checkoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               checkingoutBook();
            }
            
        });
         * 
         */


        //frame.add(checkoutButton);



        frame.setVisible(true);



    }


    public void showBooks(List<Book> books)
    {
        availableBooksArea.setText("Available books:\n\n");
        for(Book book : books)
        {
            availableBooksArea.append(book.getTitle() + "\n");
        }
    }

    
    
}
