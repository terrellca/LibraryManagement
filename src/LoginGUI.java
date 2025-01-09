import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginGUI extends JFrame{
    private Library library;
    private JFrame frame;
    private JTextField usernameField;
    private JTextField passwordField;



    public LoginGUI(Library library)
    {
        this.library = library;
        initialize();
    }


    public void initialize()
    {
        frame = new JFrame("Library Login");
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());





        frame.add(new JLabel("Username"));
        usernameField = new JTextField(15);
        frame.add(usernameField);



        frame.add(new JLabel("Password"));
        passwordField = new JTextField(15);
        frame.add(passwordField);


        

        //Login
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                if(library.signIn(username, password))
                {
                    frame.setVisible(false);
                    new MainPageGUI(library, library.getSignedinUser());
                    
                }
                else{
                    JOptionPane.showMessageDialog(loginButton, "Incorrect username or password");
                }

                
            }
            
        });





        frame.add(loginButton);

        JButton createButton = new JButton("Create Account");
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createAccountTab();

            }
            
        });

        frame.add(createButton);






        frame.setVisible(true);


    }



    private void createAccountTab()
    {
        JFrame createAccountFrame = new JFrame("Create Account");

        createAccountFrame.setSize(300, 300);
        createAccountFrame.setLayout(new FlowLayout());

        JTextField newUsernameTextField = new JTextField(15);
        JTextField newPasswordTextField = new JTextField(15);

        createAccountFrame.add(new JLabel("New Username"));
        createAccountFrame.add(newUsernameTextField);

        createAccountFrame.add(new JLabel("New Password"));
        createAccountFrame.add(newPasswordTextField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String newusername = newUsernameTextField.getText();
                String newpassword = newPasswordTextField.getText();

               if(newusername.isEmpty() || newpassword.isEmpty())
               {
                    JOptionPane.showMessageDialog(createAccountFrame,"Username and password cannot be empty.");
                    return;
               }
               

               if(library.isUsernameUsed(newusername))
               {
                    JOptionPane.showMessageDialog(createAccountFrame,"Username is already in use.");
                    return;
               }


                User newUser = new User(newusername, newpassword, false);
                library.addUser(newUser);
                JOptionPane.showMessageDialog(createAccountFrame,"New account created.");
                createAccountFrame.dispose();
    
                
            }

        });


        createAccountFrame.add(submitButton);

        createAccountFrame.setVisible(true);




    }



    


}
