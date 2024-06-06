import utils.InputOutput;
import view.LibraryView;

public class Main {
    public static void main(String[] args) {
        LibraryView libraryView = LibraryView.getInstance();

        libraryView.mainMenu();
        InputOutput.readLine("\nWcisnij cokolwiek, aby zakonczyc...");
    }
}