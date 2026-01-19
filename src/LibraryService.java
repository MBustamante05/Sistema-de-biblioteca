import Role.Action;

import java.util.ArrayList;

public class LibraryService {
    public ArrayList<Book> books;
    public ArrayList<Loan> loans;

    public LibraryService() {
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    // Métodos Generales
    public void displayBooks() {
        for (Book bk : books)
            if (bk.isAvailable())
                System.out.println(bk.toString());
    }

    public boolean displayBookById(int id) {
        for (Book bk : books)
            if (bk.getId() == id) {
                System.out.println(bk.toString());
                return true;
            }

        System.out.println("Lo sentimos. No se encontró ningún libro con ese ID");
        return false;
    }

    // Métodos de Usuario
    public void addBook(User user, Book book) {
        if (user.can(Action.ADD_BOOK)) {
            books.add(book);
            System.out.println("Libro agregado correctamente!");
        }
        System.out.println("No fue posible agregar el libro. Parece que no eres administrador.");
    }

    public boolean removeBook(User user, int id) {
        if (user.can(Action.REMOVE_BOOK)) {
            books.removeIf(bk -> bk.getId() == id);
            System.out.println("Libro eliminado correctamente!");
            return true;
        }
        System.out.println("No fue posible eliminar el libro. Parece que hubo un problema!");
        return false;
    }

    public boolean unavailableBook(User user, int id) {
        if (user.can(Action.UNAVAILABLE_BOOK)) {
            for (Book bk : books)
                if (bk.getId() == id)
                    bk.setAvailable(false);

            System.out.println("El libro ahora se encuentra no disponible!");
            return true;
        }
        System.out.println("No fue posible ejecutar esta acción. Parece que hubo un problema!");
        return false;
    }

    public void loanBook(User user, int id) {
        if (user.can(Action.LOAN_BOOK)) {
            for (Book book : books)
                if (book.getId() == id && book.isAvailable()) {
                    Loan loan = new Loan(book, user);
                    book.setAvailable(false);
                    loans.add(loan);
                    System.out.println("Préstamo exitoso!");
                }
            System.out.println("Este libro no está disponible");
        }
    }

    public void returnBook(User user, int id) {
        if (user.can(Action.RETURN_BOOK)) {
            for (Loan ln : loans)
                if (ln.getBook().getId() == id) {
                    ln.markAsReturned();
                    ln.getBook().setAvailable(true);
                    System.out.println("Gracias por utilizar nuestro servicio!");
                }
        }
    }

    // Mostrar prestamos
    public void displayLoans() {
        for (Loan ln : loans)
            System.out.println(ln.toString());
    }
}
