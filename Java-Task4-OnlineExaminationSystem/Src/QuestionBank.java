import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    public static List<Question> getQuestions() {

        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "Which keyword is used to inherit a class in Java?",
                new String[]{"this", "super", "extends", "implements"},
                2
        ));

        questions.add(new Question(
                "Which of the following is not a primitive data type in Java?",
                new String[]{"int", "float", "String", "boolean"},
                2
        ));

        questions.add(new Question(
                "Which method is the entry point of a Java application?",
                new String[]{"start()", "main()", "run()", "init()"},
                1
        ));

        questions.add(new Question(
                "Which collection does not allow duplicate elements?",
                new String[]{"List", "ArrayList", "Set", "Vector"},
                2
        ));

        questions.add(new Question(
                "What does JVM stand for?",
                new String[]{
                        "Java Variable Machine",
                        "Java Virtual Machine",
                        "Java Verified Machine",
                        "Java Visual Machine"
                },
                1
        ));

        questions.add(new Question(
                "Which concept allows the same method name with different parameters?",
                new String[]{"Inheritance", "Encapsulation", "Method Overloading", "Abstraction"},
                2
        ));

        questions.add(new Question(
                "Which access modifier provides the highest level of restriction?",
                new String[]{"public", "protected", "default", "private"},
                3
        ));

        questions.add(new Question(
                "Which keyword is used to create an object in Java?",
                new String[]{"class", "object", "new", "create"},
                2
        ));

        questions.add(new Question(
                "Which exception occurs when an array is accessed outside its valid index?",
                new String[]{
                        "NullPointerException",
                        "ArrayIndexOutOfBoundsException",
                        "IOException",
                        "ArithmeticException"
                },
                1
        ));

        questions.add(new Question(
                "Which package contains the Swing GUI components?",
                new String[]{"java.io", "java.util", "javax.swing", "java.net"},
                2
        ));

        return questions;
    }
}