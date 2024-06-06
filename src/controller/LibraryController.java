package controller;

import model.Book;
import model.Library;
import model.Person;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LibraryController {
    private final Library library = Library.getInstance();
    private static LibraryController instance;

    private LibraryController() {

    }

    public static LibraryController getInstance() {
        if (instance == null) {
            instance = new LibraryController();
        }
        return instance;
    }

    public List<Book> getAllBooks() {
        return library.getBooks();
    }

    public List<Book> getAvailableBooks() {
        return library.getBooks().stream()
                .filter(Book::isAvailable)
                .toList();
    }

    public boolean isUser(String username) {
        return library.isUser(username);
    }

    public void addUser(User user) {
        library.addUser(user);
    }

    public User getUserByUsername(String username) {
        return library.getUserByUsername(username);
    }

    public Book borrowBook(User user, Book book) {
        return library.borrowBook(user, book);
    }
    public  Map<Person, List<Book>> getBorrowedBooks() {
        return library.getBorrowedBooks();
    }
    public List<Book> getBorrowedBooksByUser(User user) {
        return library.getBorrowedBooksByUser(user);
    }

    public Book returnBook(User user, Book book) {
        return library.returnBook(user, book);
    }
}
