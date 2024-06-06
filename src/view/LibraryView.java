package view;

import additional.Banner;

import utils.InputOutput;


public class LibraryView {
    private static LibraryView instance;
    private static final int BLANKLINES = 60;
    private LibraryView() {
    }

    public static LibraryView getInstance() {
        if (instance == null) {
            instance = new LibraryView();
        }
        return instance;
    }

    public void mainMenu() {
        final int LINE_LIMIT = 80;
        mainMenuLoop:
        while (true) {
            InputOutput.printMoreLines(BLANKLINES);
            System.out.println("\n" + Banner.getBanner());
            System.out.println(InputOutput.breakLine("\n\n\tWybierz numer interesujacej Cie opcji:", LINE_LIMIT));
            System.out.println(InputOutput.breakLine("\t\t 1.\tWyswietl wszystkie tytuly", LINE_LIMIT));
            System.out.println(InputOutput.breakLine("\t\t 2.\tPrzegladaj wszystkie tytuly", LINE_LIMIT));
            System.out.println(InputOutput.breakLine("\t\t 3.\tWyswietl tylko dostepne tytuly", LINE_LIMIT));
            System.out.println(InputOutput.breakLine("\t\t 4.\tPrzegladaj i wypozycz", LINE_LIMIT));
            System.out.println(InputOutput.breakLine("\t\t 5.\tWyszukaj i wypozycz", LINE_LIMIT));
            System.out.println(InputOutput.breakLine("\t\t 6.\tPokaz wypozyczone", LINE_LIMIT));
            System.out.println(InputOutput.breakLine("\t\t 7.\tOddaj ksiazke", LINE_LIMIT));
            System.out.println(InputOutput.breakLine("\t\t 8.\tUsun uzytkownika", LINE_LIMIT));
            System.out.println(InputOutput.breakLine("\n\t\t \"q\" aby zakonczyc", LINE_LIMIT));
            String choice = InputOutput.readLine("\n\tTwoj wybor");
            switch (choice.toLowerCase().strip()) {
                case "q":
                    break mainMenuLoop;
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
            }
        }
    }
}