import java.time.LocalDate;

public class Loan {

    private Book book;
    private User user;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan (Book book, User user) {
        this.book = book;
        this.user = user;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isActive() {
        return returnDate == null;
    }

    public void markAsReturned() {
        this.returnDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Préstamo:\n" +
                "Libro: " + book.getTitle() +
                "\nUsuario: " + user +
                "\nFecha préstamo: " + loanDate +
                (isActive() ? "\nEstado: ACTIVO" : "\nDevuelto el: " + returnDate);
    }
}
