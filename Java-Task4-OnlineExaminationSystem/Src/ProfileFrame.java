import javax.swing.*;
import java.awt.*;

public class ProfileFrame extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;

    public ProfileFrame(String username) {

        setTitle("Online Examination System - Student Profile");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 35, 25, 35)
        );

        JLabel titleLabel = new JLabel(
                "STUDENT PROFILE",
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel welcomeLabel = new JLabel(
                "Welcome, " + username,
                SwingConstants.CENTER
        );
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        headerPanel.add(titleLabel);
        headerPanel.add(welcomeLabel);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 15));

        JLabel nameLabel = new JLabel("Full Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneLabel = new JLabel("Phone:");

        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(emailLabel);
        formPanel.add(emailField);

        formPanel.add(phoneLabel);
        formPanel.add(phoneField);

        JButton saveButton = new JButton("Save Profile");
        JButton logoutButton = new JButton("Logout");

        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(logoutButton);

        saveButton.addActionListener(e -> saveProfile(username));
        logoutButton.addActionListener(e -> logout());

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void saveProfile(String username) {

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all profile fields.",
                    "Profile Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Profile Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain exactly 10 digits.",
                    "Profile Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Profile updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Profile saved successfully.\nDo you want to start the examination?",
                "Start Examination",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();

            new ExamFrame(username, name).setVisible(true);
        }
    }

    private void logout() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();

            // LoginFrame will be connected here.
            new LoginFrame().setVisible(true);
        }
    }
}