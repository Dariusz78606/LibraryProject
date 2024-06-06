package model;

import utils.InputOutput;

public class Book implements LibraryItem{
    private String title;
    private String author;
    private String description;
    private int pages;
    private String isbn10;
    private String publisher;
    private boolean available;

    @Override
    public void displayInfo() {
        int charLimit = 60;
        System.out.println(InputOutput.breakLine("Tytul:\t\t" + title, charLimit, "\t"));
        System.out.println(InputOutput.breakLine("Autor:\t\t" + author, charLimit, "\t"));
        System.out.println(InputOutput.breakLine("ISBN-10:\t\t" + isbn10, charLimit, "\t"));
        System.out.println(InputOutput.breakLine("Liczba stron:\t\t" + pages, charLimit, "\t"));
        System.out.println(InputOutput.breakLine("Wydawca:\t\t" + publisher, charLimit, "\t"));
        System.out.println(InputOutput.breakLine("Dostepnosc:\t\t" + this.isAvailableString(), charLimit, "\t"));
        System.out.println(InputOutput.breakLine("Opis:\t\t" +this.description, 60, "\t\t"));
    }

    private String shortDescription(int charsLimit) {
        return title.substring(0, charsLimit);
    }

    public Book(String title, String author, String description, int pages, String isbn10, String publisher, boolean available) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.pages = pages;
        this.isbn10 = isbn10;
        this.publisher = publisher;
        this.available = available;
    }

    private String isAvailableString() {
        if (this.available) {
            return "tak";
        } else {
            return "nie";
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }
}