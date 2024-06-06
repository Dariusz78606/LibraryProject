package utils;

import java.io.IOException;
import java.util.Scanner;

public class InputOutput {
    public static String readLine(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message + ": ");
        return scanner.nextLine();
    }

    public static String breakLine(String text, int charsLimit) {
        StringBuilder result = new StringBuilder();
        int counter = 0;
        for (String character: text.split("")) {
            result.append(character);
            counter++;
            if (counter >= charsLimit && character.equals(" ")) {
                result.append("\n");
                counter = 0;
            }
        }
        return result.toString();
    }

    public static String breakLine(String text, int charsLimit, String linePrefix) {
        StringBuilder result = new StringBuilder();
        int counter = 0;
        result.append(linePrefix);
        for (String character: text.split("")) {
            result.append(character);
            counter++;
            if (counter >= charsLimit && character.equals(" ")) {
                result.append("\n");
                result.append(linePrefix);
                counter = 0;
            }
        }
        return result.toString();
    }

    public static void printMoreLines(int lines_count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines_count; i++) {
            result.append("\n");
        }
        System.out.println(result.toString());
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            return;
        }
    }

    public static String getCountOfChars(int chars_count, char character) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chars_count; i++) {
            result.append(character);
        }
        return result.toString();
    }
}