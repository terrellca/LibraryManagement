import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;





public class Library {
    
    private List<Book> books; //All books
    private User signedInUser; //the current user
    private List<User> users; //All users
    private static final File USER_FILE = new File("users.txt");
    private static final File BOOK_FILE = new File("books.txt");
    //private static final ArrayList<String> USERNAMES = new ArrayList<>();
    //private static final ArrayList<String> PASSWORDS = new ArrayList<>();


    public Library()
    {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        initializeUsers();
        initializeBooks();
    }
    private void initializeUsers(){
        
        
        try(BufferedReader reader = new BufferedReader(new FileReader(USER_FILE)))
        {
            String line;
            //Scanner inFile = new Scanner(USER_FILE);
            while((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                if(parts.length == 3)
                {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    boolean isAdmin = Boolean.parseBoolean(parts[2].trim());

                    boolean userExists = false;
                    for (User u : users) {
                        if (u.getUsername().equals(username)) {
                            userExists = true;
                             break; // Exit the loop as soon as the user is found
                            }
                            }
                    if(!userExists)
                    {
                       users.add(new User(username, password, isAdmin));
                    }
                    
                    
                }
            }
            System.out.println("Users after initialization:");
            for (User user : users) {
            System.out.println(user.getUsername());
            }
        }
        catch (IOException e)
        {
            System.out.println("An error occured.");
        }
    }
    

    private void initializeBooks()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(BOOK_FILE)))
        {
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");
                if(parts.length == 6)
                {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    String genre = parts[1].trim();
                    String description = parts[1].trim();
                    String isbn = parts[1].trim();
                    int copies = (parts.length == 6) ? Integer.parseInt(parts[5].trim()) : 1;             ;
                    
                    
                    //books.add(new Book(title, author, genre, description, isbn));
                    Book book = new Book(title, author, genre, description, isbn);
                    for(int i = 0; i < copies; i++)
                    {
                        book.addCopy();
                        copies--;
                    }
                    books.add(book);
                    
                }
            }
            // System.out.println("Books after initialization:");
            // for (Book book : books) {
            // System.out.println(book.getTitle());
            // }
        }
        catch (IOException e)
        {
            System.out.println("An error occured. No books could be found.");
        }
    }

        public void addBook(Book book)
    {
        if(signedInUser.isAdmin() && signedInUser != null)
        {
            for(Book b : books)
            {
                if(b.getIsbn().equals(book.getIsbn()))
                {
                    System.out.println("Another copy of " + b.getTitle() + " has been added");
                    b.addCopy();
                    return;
                }
            }
        }
        books.add(book);
        appendBookToFile(book);
        System.out.println("New Book added.");
    }





    public User getSignedinUser()
    {
        return signedInUser;
    }
    
    public List<User> getUsers() 
    {
        return users; 
    }

    public List<Book> getBooks() 
    {
        return books; 
    }

    public boolean isUsernameUsed(String username)
    {
        for(User user : users)
        {
            if(user.getUsername().equals(username))
            {
                return true;
            }
        }
        return false;
    }





    public void appendUserToFile(String username, String password, boolean isAdmin)
    {
        try(FileWriter writer = new FileWriter("users.txt", true))
        {
            writer.write(username + "," + password + "," + isAdmin + "\n");
        } catch (IOException e)
        {
            System.out.println("Error saving user to File");
        }
    }

    public void appendBookToFile(Book book)
    {
        try(FileWriter writer = new FileWriter("books.txt", true))
        {
            writer.write(String.format("%s,%s,%s,%s,%s,%d\n", book.getTitle(), book.getAuthor(), book.getIsbn(), book.getDescription(), book.getGenre(), book.getNumCopies()));
        } catch (IOException e)
        {
            System.out.println("Error saving book to File" + e.getMessage());
        }
    }


    public void addUser(User user)
    {
        
        for(User u : users)
        {
            if(u.getUsername().equals(user.getUsername()))
            {
                System.out.print("Username already exists, please sign in with that username or pick a new username\n");
                return;
            }
        }
        users.add(user);
        appendUserToFile(user.getUsername(), user.getPassword(), user.isAdmin());
        System.out.println("Your user is " + user.getUsername());
    }





    //These two methods aren't really needed anymore. Keeping for later updates.

    public List<Book> searchBookTitle(String title)
    {
        List<Book> result = new ArrayList<>();
        for(Book book : books)
        {
            if(book.getTitle().toLowerCase().contains(title.toLowerCase()))
            {
                result.add(book);
            }
        }
        return result;
    }



    public List<Book> searchBookAuthor(String author)
    {
        List<Book> result = new ArrayList<>();
        for(Book book : books)
        {
            if(book.getAuthor().equalsIgnoreCase(author))
            {
                result.add(book);
            }
        }
        return result;
    }


//Two methods above^

    public boolean signIn(String username, String password)
    {
        for(User user : users)
        {
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                signedInUser = user;
                System.out.println(signedInUser);
                System.out.println("Signed in as " + user.getUsername());

                if(user.isAdmin())
                {
                    System.out.println("Welcome admin: " + user.getUsername());
                }
                return true;
            }

        }
        
        System.out.println("Invalid username or password\n");
        return false;
    }



    public void signOut()
    {
        if(signedInUser == null)
        {
            System.out.println("No user is signed in\n");
            return;
        }

        System.out.println("User " + signedInUser.getUsername() + " has signed out.\n");
        signedInUser = null;
    }


     private void saveBooks() {
        try (FileWriter writer = new FileWriter(BOOK_FILE)) {
            for (Book b : books) {
                writer.write(b.getTitle() + "," + b.getAuthor() + "," + b.getGenre() + "," +
                             b.getIsbn() + "," + b.getDescription() + "," + b.getNumCopies() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }



    public boolean checkoutBook(Book book)
    {
        if(signedInUser == null)
        {
            System.out.println("Sign in.\n");
            return false;
        }

        if(!book.isAvailable())
        {
           JOptionPane.showMessageDialog(null,"This book is not available: " + book.getTitle());
            return false;
        }

        book.BorrowCopy();
        JOptionPane.showMessageDialog(null,"You checked out: " + book.getTitle());
        return true;


    }




    public boolean returnBook(Book book)
    {

        // if(signedInUser == null)
        // {
        //     System.out.println("Sign in.");
        //     return false;
        // }

        if(book.isAvailable())
        {
            System.out.println("Book is not checked out" + book.getTitle());
            return false;
        }

        book.ReturnCopy();
        saveBooks();
        JOptionPane.showMessageDialog(null, "You returned: " + book.getTitle());
        return true;

    }

}
