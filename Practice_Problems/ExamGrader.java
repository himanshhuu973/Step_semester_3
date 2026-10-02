package Practice_Problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Question {
    protected String text, correctAnswer, studentAnswer;
    protected double points;
    public Question(String text, String correctAnswer, String studentAnswer, double points) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }
    public abstract double grade();
    public abstract String getTypeName();
}

class MCQQuestion extends Question {
    public MCQQuestion(String text, String correctAnswer, String studentAnswer, double points) {
        super(text, correctAnswer, studentAnswer, points);
    }
    @Override public double grade() {
        return studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim()) ? points : 0.0;
    }
    @Override public String getTypeName() { return "MCQ"; }
}

class TFQuestion extends Question {
    public TFQuestion(String text, String correctAnswer, String studentAnswer, double points) {
        super(text, correctAnswer, studentAnswer, points);
    }
    @Override public double grade() {
        return studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim()) ? points : 0.0;
    }
    @Override public String getTypeName() { return "TF"; }
}

class EssayQuestion extends Question {
    public EssayQuestion(String text, String correctAnswer, String studentAnswer, double points) {
        super(text, correctAnswer, studentAnswer, points);
    }
    @Override public double grade() {
        String[] keywords = correctAnswer.split(",");
        int matchCount = 0;
        String studentLower = studentAnswer.toLowerCase();
        for (String kw : keywords) {
            String trimmedKw = kw.trim().toLowerCase();
            if (!trimmedKw.isEmpty() && studentLower.contains(trimmedKw)) {
                matchCount++;
            }
        }
        if (matchCount >= 2) return points * 0.75;
        if (matchCount == 1) return points * 0.50;
        return 0.0;
    }
    @Override public String getTypeName() { return "ESSAY"; }
}

public class ExamGrader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            // Read quoted text, correct answer, student answer, and points
            String qText = scanQuotedString(scanner);
            String cAns = scanQuotedString(scanner);
            String sAns = scanQuotedString(scanner);
            double pts = scanner.nextDouble();

            if (type.equalsIgnoreCase("MCQ")) questions.add(new MCQQuestion(qText, cAns, sAns, pts));
            else if (type.equalsIgnoreCase("TF")) questions.add(new TFQuestion(qText, cAns, sAns, pts));
            else if (type.equalsIgnoreCase("ESSAY")) questions.add(new EssayQuestion(qText, cAns, sAns, pts));
        }
        scanner.close();

        double totalScore = 0;
        for (Question q : questions) {
            double score = q.grade();
            totalScore += score;
            System.out.printf("%s: %.2f\n", q.getTypeName(), score);
        }
        System.out.printf("Total Score: %.2f\n", totalScore);
    }

    private static String scanQuotedString(Scanner scanner) {
        String token = scanner.next();
        if (token.startsWith("\"")) {
            if (token.endsWith("\"") && token.length() > 1) {
                return token.substring(1, token.length() - 1);
            }
            StringBuilder sb = new StringBuilder(token.substring(1));
            while (scanner.hasNext()) {
                String nextToken = scanner.next();
                if (nextToken.endsWith("\"")) {
                    sb.append(" ").append(nextToken.substring(0, nextToken.length() - 1));
                    break;
                } else {
                    sb.append(" ").append(nextToken);
                }
            }
            return sb.toString();
        }
        return token;
    }
}