package view;

import additional.Banner;
import controller.LibraryController;
import model.Book;
import model.Person;
import model.User;
import utils.InputOutput;

import java.util.ArrayList;
import java.util.List;

public class LibraryView {
    private static LibraryView instance;
    private LibraryController controller;
    private static final int BLANKLINES = 60;
    private LibraryView() {
        this.controller = LibraryController.getInstance();
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
                    showBooks(controller.getAllBooks(), "Lista wszystkich tytulow:");
                    break;
                case "2":
                    previewAllBooks(false);
                    break;
                case "3":
                    showBooks(controller.getAvailableBooks(), "Lista dostepnych tytulow:");
                    break;
                case "4":
                    previewAllBooks(true);
                case "5":
                    break;
                case "6":
                    previewBorrowed();
                    break;
                case "7":
                    break;
                case "8":
                    break;
            }
        }
    }

    public void showBooks(List<Book> books, String message) {

        InputOutput.printMoreLines(BLANKLINES);
        System.out.println("\n\t" + message + "\n");
        for (Book book:books) {
            System.out.println("\t\t" + book.getAuthor());
            System.out.println("\t\t\t" + book.getTitle());
            System.out.println("\t\t\t"+ InputOutput.getCountOfChars(book.getTitle().length(), '-') + "\n");
        }
        InputOutput.readLine("\n Wcisnij cokolwiek, aby wrocic do menu glownego...");
    }


    public void borrowBook(Book book, User user) {
        InputOutput.printMoreLines(BLANKLINES);
        Book result = controller.borrowBook(user, book);
        if (result != null) {
            System.out.println(InputOutput.breakLine("Ksiazka zostala wypozyczona\n\n", 60));
            System.out.println(InputOutput.breakLine("\n\nUzytkownik: " + user.getUsername(), 60, "\t\t"));
            System.out.println(InputOutput.breakLine("\n\nTytul: " + book.getTitle(), 60, "\t\t"));
            System.out.println(InputOutput.breakLine("\n\nAutor ksiazki: " + book.getAuthor(), 60, "\t\t"));
        } else {
            InputOutput.breakLine("\n\n\tWystapil jakis problem i ksiazka nie zostala wypozyczona", 60);
        }
    }


    public void previewBorrowed() {
        InputOutput.printMoreLines(BLANKLINES);
        if (controller.getBorrowedBooks().isEmpty()) {
            System.out.println("Brak wypozyczonych ksiazek");
            return;
        }
        System.out.println(InputOutput.breakLine("\nUzytkownicy i ich wypozyczone ksiazki" ,60, "\t\t"));
        for (Person person: controller.getBorrowedBooks().keySet()) {
            if (controller.getBorrowedBooks().getOrDefault(person, new ArrayList<>()).isEmpty()) {
                continue;
            }
            System.out.println("\n\t\t" + person.getUsername() + "\n");
            for (Book book: controller.getBorrowedBooks().getOrDefault(person, new ArrayList<>())) {
                System.out.println(InputOutput.breakLine("Tytul: " + book.getTitle(), 60, "\t"));
                System.out.println(InputOutput.breakLine("Autor: " + book.getAuthor(), 60, "\t"));
                System.out.println("");
            }
        }
        InputOutput.readLine("\nWcisnij enter by wrocic do menu glownego...");
    }

    public void previewAllBooks(boolean borrow) {
        String choice;
        String name;
        User user = null;
        InputOutput.printMoreLines(BLANKLINES);
        System.out.println("\n\tPrzeglad wszystkich tytulow:\n");
        mainLoop:
        for (Book book:controller.getAllBooks()) {
            InputOutput.printMoreLines(BLANKLINES);
            book.displayInfo();
            if (!borrow) {
                choice = InputOutput.readLine(InputOutput.breakLine("\n\n\tJesli chcesz zakonczyc wpisz \"q\" oraz enter. W innym przypadku dowolny znak", 60, "\t"));
                if (choice.toLowerCase().strip().equals("q")) {
                    break;
                }
            } else {
                choice = InputOutput.readLine(InputOutput.breakLine("\n\n\tJesli chcesz wypozyczyc wcisnij \"w\" oraz enter. Jesli chcesz zakonczyc wcisnij \"q\" oraz enter. W innym przypadku dowolny znak", 60, "\t"));
                switch (choice) {
                    case "q":
                        break mainLoop;
                    case "w":
                        if (user == null) {
                            name = InputOutput.readLine(InputOutput.breakLine("\n\n\tPodaj nazwe uzytkownika", 60, "\t"));
                            if (!controller.isUser(name)) {
                                user = new User(name);
                            } else {
                                user = controller.getUserByUsername(name);
                            }
                        }
                        borrowBook(book, user);
                        choice = InputOutput.readLine(InputOutput.breakLine("\n\n\tKontyunowac przegladanie? Jesli tak wcnisnij \"t\" oraz enter", 60, "\t"));
                        if (!choice.toLowerCase().strip().equals("t")) {
                            break mainLoop;
                        }
                        break;
                }
            }
        }
    }
}