import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ExamFrame extends JFrame {

    private final String username;
    private final String studentName;
    private final List<Question> questions;

    private int currentQuestion = 0;
    private final int[] selectedAnswers;

    private JLabel questionNumberLabel;
    private JLabel studentLabel;
    private JLabel timerLabel;
    private JLabel progressLabel;
    private JLabel questionLabel;

    private JRadioButton optionA;
    private JRadioButton optionB;
    private JRadioButton optionC;
    private JRadioButton optionD;

    private ButtonGroup optionGroup;

    private JButton previousButton;
    private JButton nextButton;
    private JButton submitButton;

    private Timer timer;

    private int remainingSeconds = 5 * 60;

    public ExamFrame(String username, String studentName) {

        this.username = username;
        this.studentName = studentName;

        questions = QuestionBank.getQuestions();
        selectedAnswers = new int[questions.size()];

        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }

        setTitle("Online Examination System - Examination");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                confirmClose();
            }
        });

        initializeUI();
        loadQuestion();
        startTimer();
    }

    private void initializeUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("ONLINE EXAMINATION");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        studentLabel = new JLabel("Student: " + studentName);
        studentLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(studentLabel);

        JPanel timerPanel = new JPanel();
        timerPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        BorderFactory.createEmptyBorder(8, 15, 8, 15)
                )
        );

        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        timerPanel.add(timerLabel);

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(timerPanel, BorderLayout.EAST);

        // =========================
        // QUESTION INFO
        // =========================

        JPanel infoPanel = new JPanel(new BorderLayout());

        questionNumberLabel = new JLabel();
        questionNumberLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        progressLabel = new JLabel();
        progressLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        infoPanel.add(
                questionNumberLabel,
                BorderLayout.WEST
        );

        infoPanel.add(
                progressLabel,
                BorderLayout.EAST
        );

        // =========================
        // QUESTION CARD
        // =========================

        JPanel questionPanel = new JPanel(
                new BorderLayout(10, 20)
        );

        questionPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(20, 20, 20, 20)
                )
        );

        questionLabel = new JLabel();
        questionLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        questionPanel.add(
                questionLabel,
                BorderLayout.NORTH
        );

        // =========================
        // OPTIONS
        // =========================

        JPanel optionsPanel = new JPanel(
                new GridLayout(4, 1, 10, 10)
        );

        optionA = createOptionButton();
        optionB = createOptionButton();
        optionC = createOptionButton();
        optionD = createOptionButton();

        optionGroup = new ButtonGroup();

        optionGroup.add(optionA);
        optionGroup.add(optionB);
        optionGroup.add(optionC);
        optionGroup.add(optionD);

        optionsPanel.add(optionA);
        optionsPanel.add(optionB);
        optionsPanel.add(optionC);
        optionsPanel.add(optionD);

        questionPanel.add(
                optionsPanel,
                BorderLayout.CENTER
        );

        // =========================
        // NAVIGATION BUTTONS
        // =========================

        previousButton = new JButton("← Previous");
        nextButton = new JButton("Next →");
        submitButton = new JButton("Submit Exam");

        previousButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        nextButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        submitButton.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 5)
        );

        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);

        previousButton.addActionListener(
                e -> showPreviousQuestion()
        );

        nextButton.addActionListener(
                e -> showNextQuestion()
        );

        submitButton.addActionListener(
                e -> confirmSubmit()
        );

        // =========================
        // MAIN LAYOUT
        // =========================

        JPanel centerPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        centerPanel.add(infoPanel, BorderLayout.NORTH);
        centerPanel.add(questionPanel, BorderLayout.CENTER);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    private JRadioButton createOptionButton() {

        JRadioButton button = new JRadioButton();

        button.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );

        return button;
    }

    private void loadQuestion() {

        Question question = questions.get(currentQuestion);

        questionNumberLabel.setText(
                "Question " + (currentQuestion + 1)
                        + " of " + questions.size()
        );

        questionLabel.setText(
                "<html><body style='width: 680px'>"
                        + question.getQuestionText()
                        + "</body></html>"
        );

        String[] options = question.getOptions();

        optionA.setText("A. " + options[0]);
        optionB.setText("B. " + options[1]);
        optionC.setText("C. " + options[2]);
        optionD.setText("D. " + options[3]);

        optionGroup.clearSelection();

        int savedAnswer = selectedAnswers[currentQuestion];

        if (savedAnswer == 0) {
            optionA.setSelected(true);
        } else if (savedAnswer == 1) {
            optionB.setSelected(true);
        } else if (savedAnswer == 2) {
            optionC.setSelected(true);
        } else if (savedAnswer == 3) {
            optionD.setSelected(true);
        }

        updateProgress();

        previousButton.setEnabled(currentQuestion > 0);

        nextButton.setEnabled(
                currentQuestion < questions.size() - 1
        );
    }

    private void updateProgress() {

        int answered = 0;

        for (int answer : selectedAnswers) {
            if (answer != -1) {
                answered++;
            }
        }

        progressLabel.setText(
                "Answered: " + answered
                        + " / " + questions.size()
        );
    }

    private void saveCurrentAnswer() {

        if (optionA.isSelected()) {
            selectedAnswers[currentQuestion] = 0;
        } else if (optionB.isSelected()) {
            selectedAnswers[currentQuestion] = 1;
        } else if (optionC.isSelected()) {
            selectedAnswers[currentQuestion] = 2;
        } else if (optionD.isSelected()) {
            selectedAnswers[currentQuestion] = 3;
        }
    }

    private void showNextQuestion() {

        saveCurrentAnswer();

        if (currentQuestion < questions.size() - 1) {
            currentQuestion++;
            loadQuestion();
        }
    }

    private void showPreviousQuestion() {

        saveCurrentAnswer();

        if (currentQuestion > 0) {
            currentQuestion--;
            loadQuestion();
        }
    }

    private void startTimer() {

        updateTimerLabel();

        timer = new Timer(
                1000,
                e -> {

                    remainingSeconds--;

                    updateTimerLabel();

                    if (remainingSeconds <= 0) {

                        timer.stop();

                        JOptionPane.showMessageDialog(
                                this,
                                "Time is over. Your examination will be submitted automatically.",
                                "Time Up",
                                JOptionPane.WARNING_MESSAGE
                        );

                        submitExam();
                    }
                }
        );

        timer.start();
    }

    private void updateTimerLabel() {

        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;

        timerLabel.setText(
                String.format(
                        "Time Remaining: %02d:%02d",
                        minutes,
                        seconds
                )
        );
    }

    private void confirmSubmit() {

        saveCurrentAnswer();

        int unanswered = 0;

        for (int answer : selectedAnswers) {
            if (answer == -1) {
                unanswered++;
            }
        }

        String message;

        if (unanswered > 0) {
            message = "You have " + unanswered
                    + " unanswered question(s).\n"
                    + "Are you sure you want to submit?";
        } else {
            message = "You have answered all questions.\n"
                    + "Are you sure you want to submit?";
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                message,
                "Confirm Submission",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            submitExam();
        }
    }

    private void submitExam() {

        saveCurrentAnswer();

        if (timer != null) {
            timer.stop();
        }

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {

            if (selectedAnswers[i]
                    == questions.get(i).getCorrectAnswer()) {

                score++;
            }
        }

        dispose();

        new ResultFrame(
                username,
                studentName,
                score,
                questions.size()
        ).setVisible(true);
    }

    private void confirmClose() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Your examination is currently in progress.\n"
                        + "Are you sure you want to close the examination?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {

            if (timer != null) {
                timer.stop();
            }

            System.exit(0);
        }
    }
}