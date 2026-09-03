import javax.swing.*;
import java.awt.*;

public class ResultFrame extends JFrame {

    private final String username;
    private final String studentName;
    private final int score;
    private final int totalQuestions;

    public ResultFrame(
            String username,
            String studentName,
            int score,
            int totalQuestions) {

        this.username = username;
        this.studentName = studentName;
        this.score = score;
        this.totalQuestions = totalQuestions;

        setTitle("Online Examination System - Result");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initializeUI();
    }

    private void initializeUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );

        // =========================
        // HEADER
        // =========================

        JLabel titleLabel = new JLabel(
                "EXAMINATION RESULT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        // =========================
        // RESULT INFORMATION
        // =========================

        double percentage =
                ((double) score / totalQuestions) * 100;

        String performance;

        if (percentage >= 80) {
            performance = "Excellent Performance!";
        } else if (percentage >= 60) {
            performance = "Good Performance!";
        } else if (percentage >= 40) {
            performance = "Satisfactory Performance";
        } else {
            performance = "Needs Improvement";
        }

        JPanel resultPanel = new JPanel(
                new GridLayout(5, 1, 10, 10)
        );

        JLabel studentLabel = new JLabel(
                "Student: " + studentName,
                SwingConstants.CENTER
        );

        JLabel usernameLabel = new JLabel(
                "Username: " + username,
                SwingConstants.CENTER
        );

        JLabel scoreLabel = new JLabel(
                "Score: " + score + " / " + totalQuestions,
                SwingConstants.CENTER
        );

        JLabel percentageLabel = new JLabel(
                String.format("Percentage: %.2f%%", percentage),
                SwingConstants.CENTER
        );

        JLabel performanceLabel = new JLabel(
                performance,
                SwingConstants.CENTER
        );

        studentLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        usernameLabel.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        scoreLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        percentageLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        performanceLabel.setFont(
                new Font("Arial", Font.BOLD, 17)
        );

        resultPanel.add(studentLabel);
        resultPanel.add(usernameLabel);
        resultPanel.add(scoreLabel);
        resultPanel.add(percentageLabel);
        resultPanel.add(performanceLabel);

        // =========================
        // BUTTONS
        // =========================

        JButton restartButton = new JButton(
                "Take Exam Again"
        );

        JButton logoutButton = new JButton(
                "Logout"
        );

        restartButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        logoutButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        JPanel buttonPanel = new JPanel(
                new FlowLayout()
        );

        buttonPanel.add(restartButton);
        buttonPanel.add(logoutButton);

        restartButton.addActionListener(e -> restartExam());

        logoutButton.addActionListener(e -> logout());

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                resultPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    private void restartExam() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Do you want to take the examination again?",
                "Restart Examination",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            dispose();

            new ExamFrame(
                    username,
                    studentName
            ).setVisible(true);
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

            new LoginFrame().setVisible(true);
        }
    }

    @Override
    protected void processWindowEvent(
            java.awt.event.WindowEvent event) {

        if (event.getID()
                == java.awt.event.WindowEvent.WINDOW_CLOSING) {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to close the application?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

        super.processWindowEvent(event);
    }
}