import additional.Books;
import model.Library;
import utils.InputOutput;
import view.LibraryView;

public class Main {
    public static void main(String[] args) {
        Library library = Library.getInstance();
        LibraryView libraryView = LibraryView.getInstance();

        Books.getStartupBooks()
                .forEach(library::addBook);

        libraryView.mainMenu();
        InputOutput.readLine("\nWcisnij cokolwiek, aby zakonczyc...");
    }
}
