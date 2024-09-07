import java.util.ArrayList;
import java.util.Scanner;

// Question class to store the question text, options, and the correct answer
class Question {
    private String questionText;
    private String[] options;
    private char correctOption;

    public Question(String questionText, String[] options, char correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public char getCorrectOption() {
        return correctOption;
    }
}

// Quiz class to store the quiz title and a list of questions
class Quiz {
    private String title;
    private ArrayList<Question> questions;

    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}

// User class to store the user's name and score
class User {
    private String name;
    private int score;

    public User(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}

// Main QuizApp class to manage quiz creation, quiz-taking, and user interaction
public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Quiz> quizzes = new ArrayList<>();
        User user;

        System.out.println("Enter your name: ");
        user = new User(scanner.nextLine());

        while (true) {
            System.out.println("\n1. Create Quiz\n2. Take Quiz\n3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    Quiz quiz = createQuiz(scanner);
                    quizzes.add(quiz);
                    break;
                case 2:
                    if (quizzes.isEmpty()) {
                        System.out.println("No quizzes available.");
                    } else {
                        System.out.println("Select a quiz:");
                        for (int i = 0; i < quizzes.size(); i++) {
                            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
                        }
                        int quizChoice = Integer.parseInt(scanner.nextLine()) - 1;
                        takeQuiz(quizzes.get(quizChoice), user, scanner);
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Method to create a quiz by adding questions
    public static Quiz createQuiz(Scanner scanner) {
        System.out.println("Enter quiz title: ");
        String title = scanner.nextLine();
        Quiz quiz = new Quiz(title);

        System.out.println("Enter the number of questions: ");
        int numQuestions = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numQuestions; i++) {
            System.out.println("Enter question " + (i + 1) + ": ");
            String questionText = scanner.nextLine();

            String[] options = new String[4];
            for (int j = 0; j < 4; j++) {
                System.out.println("Enter option " + (char) ('A' + j) + ": ");
                options[j] = scanner.nextLine();
            }

            System.out.println("Enter correct option (A, B, C, D): ");
            char correctOption = scanner.nextLine().charAt(0);

            quiz.addQuestion(new Question(questionText, options, correctOption));
        }

        return quiz;
    }

    // Method to take a quiz and calculate the user's score
    public static void takeQuiz(Quiz quiz, User user, Scanner scanner) {
        System.out.println("Starting quiz: " + quiz.getTitle());

        for (Question question : quiz.getQuestions()) {
            System.out.println(question.getQuestionText());
            String[] options = question.getOptions();

            for (int i = 0; i < options.length; i++) {
                System.out.println((char) ('A' + i) + ": " + options[i]);
            }

            System.out.println("Your answer: ");
            char answer = scanner.nextLine().charAt(0);

            if (answer == question.getCorrectOption()) {
                user.incrementScore();
            }
        }

        System.out.println(user.getName() + ", your score is: " + user.getScore() + "/" + quiz.getQuestions().size());
    }
}