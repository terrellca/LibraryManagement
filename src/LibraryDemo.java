import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;



public class LibraryDemo {
    //private static final File USER_FILE = new File("users.txt");
    //private static final ArrayList<String> USERNAMES = new ArrayList<>();
    //private static final ArrayList<String> PASSWORDS = new ArrayList<>();



    public static void main(String[] args) {
        // Initialize the library and add some test data
        Library library = new Library();
        //library.addUser(new User("user1", "password1", false)); // Regular user
        //library.addUser(new User("admin1", "adminpass", true)); // Admin user
    
        // Add some books to the library
        //library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", "123456789"));
        //library.addBook(new Book("1984", "George Orwell", "Dystopian", "987654321"));
        //library.signOut();
        //library.initializeUsers();
        // Launch the Login GUI
        new LoginGUI(library);
        }



    


    
}
