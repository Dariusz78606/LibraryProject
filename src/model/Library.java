package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Library {
    private static Library instance;
    private List<Book> books;
    private List<User> users;
    private Map<Person, List<Book>> borrowedBooks;
    public static final int MAX_BORROWED_BOOKS = 5;

    private Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        borrowedBooks = new HashMap<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<Book> getBorrowedBooksByUser(User user) {
        return borrowedBooks.getOrDefault(user, new ArrayList<>());
    }

    public User deleteUser(User user) {
        if (users.contains(user)) {
            for (Book book: borrowedBooks.getOrDefault(user, new ArrayList<>())) {
                book.setAvailable(true);
            }
            borrowedBooks.remove(user);
            users.remove(user);
            return user;
        }
        return null;
    }

    public Book borrowBook(User user, Book book) {
        if (book.isAvailable() && borrowedBooks.getOrDefault(user, new ArrayList<>()).size() < MAX_BORROWED_BOOKS) {
            book.setAvailable(false);
            borrowedBooks.computeIfAbsent(user, k -> new ArrayList<>()).add(book);
            return book;
        } else {
            return null;
        }
    }

    public Book returnBook(User user, Book book) {
        if (borrowedBooks.containsKey(user) && borrowedBooks.get(user).contains(book)) {
            book.setAvailable(true);
            borrowedBooks.get(user).remove(book);
            if (borrowedBooks.get(user).isEmpty()) {
                borrowedBooks.remove(user);
            }
            return book;
        } else {
            return null;
        }
    }

    public List<Book> findBookByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .toList();
    }

    public List<Book> findBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }


    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserByUsername(String username) {
        for (User user: users) {
            if (user.username.toLowerCase().strip().equals(username.toLowerCase().strip())) {
                return user;
            }
        }
        return null;
    }

    public boolean isUser(String username) {
        if (users.isEmpty()) {
            return false;
        } else {
            for (User user: users) {
                if (user.username.toLowerCase().strip().equals(username.toLowerCase().strip())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Map<Person, List<Book>> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setBorrowedBooks(Map<Person, List<Book>> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}