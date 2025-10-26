import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private Library library;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI(Library library) {
        this.library = library;
        initialize();
    }

    private void initialize() {
        setTitle("📚 Library Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gridbag = new GridBagConstraints();
        gridbag.insets = new Insets(10, 10, 10, 10);
        gridbag.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Library Login");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gridbag.gridx = 0;
        gridbag.gridy = 0;
        gridbag.gridwidth = 2;
        panel.add(titleLabel, gridbag);

        gridbag.gridwidth = 1;
        gridbag.gridy++;

        panel.add(new JLabel("Username:"), gridbag);
        usernameField = new JTextField(15);
        gridbag.gridx = 1;
        panel.add(usernameField, gridbag);

        gridbag.gridx = 0;
        gridbag.gridy++;
        panel.add(new JLabel("Password:"), gridbag);
        passwordField = new JPasswordField(15);
        gridbag.gridx = 1;
        panel.add(passwordField, gridbag);

        gridbag.gridx = 0;
        gridbag.gridy++;
        gridbag.gridwidth = 2;
        gridbag.anchor = GridBagConstraints.CENTER;

        JButton loginButton = new JButton("Login");
        JButton createButton = new JButton("Create Account");
        JButton managerButton = new JButton("Manager Login");

        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.black);
        createButton.setBackground(new Color(60, 179, 113));
        createButton.setForeground(Color.black);
        managerButton.setBackground(new Color(220, 20, 60));
        managerButton.setForeground(Color.black);

        loginButton.setFocusPainted(false);
        createButton.setFocusPainted(false);
        managerButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(loginButton);
        buttonPanel.add(createButton);
        buttonPanel.add(managerButton);

        gridbag.gridy++;
        panel.add(buttonPanel, gridbag);

        add(panel);

        // Button actions
        loginButton.addActionListener(e -> login());
        createButton.addActionListener(e -> createAccount());
        managerButton.addActionListener(e -> managerLogin());

        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (library.signIn(username, password)) {
            JOptionPane.showMessageDialog(this, "Welcome " + username);
            dispose();
            new MainPageGUI(library, library.getSignedinUser());
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createAccount() {
        String username = JOptionPane.showInputDialog(this, "Enter a username:");
        String password = JOptionPane.showInputDialog(this, "Enter a password:");
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty");
            return;
        }
        if (library.isUsernameUsed(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists");
            return;
        }
        library.addUser(new User(username, password, "user"));
        JOptionPane.showMessageDialog(this, "Account created successfully!");
    }

    private void managerLogin() {
        String managerPass = JOptionPane.showInputDialog(this, "Enter Manager Access Code:");
        if ("root123".equals(managerPass)) { // Replace with secure config later
            String newAdmin = JOptionPane.showInputDialog(this, "Enter new admin username:");
            String newPass = JOptionPane.showInputDialog(this, "Enter admin password:");
            if (newAdmin != null && newPass != null) {
                library.addUser(new User(newAdmin, newPass, "admin"));
                JOptionPane.showMessageDialog(this, "Admin account created successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect manager code.");
        }
    }


    public static void main(String[] args) {
    Library library = new Library();
    new LoginGUI(library);
}
}
