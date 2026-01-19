import Role.Action;
import java.util.Scanner;

public class MenuHandler {
    private final LibraryService libraryService;
    private final Scanner scanner;

    public MenuHandler(LibraryService libraryService, Scanner scanner) {
        this.libraryService = libraryService;
        this.scanner = scanner;
    }

    public void showMenu(User user) {
        boolean running = true;

        while (running) {
            if (user.can(Action.ADD_BOOK)) {
                running = showAdminMenu(user);
            } else {
                running = showReaderMenu(user);
            }
        }
    }

    private boolean showAdminMenu(User user) {
        System.out.println("\n=== MENÚ ADMINISTRADOR ===");
        String[] options = {
                "1. Agregar libro",
                "2. Deshabilitar libro",
                "3. Eliminar libro",
                "4. Buscar libro",
                "5. Ver catálogo",
                "6. Ver préstamos",
                "7. Salir"
        };

        for (String option : options) {
            System.out.println(option);
        }

        System.out.print("\nElige una opción: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                handleAddBook(user);
                break;
            case "2":
                handleDisableBook(user);
                break;
            case "3":
                handleRemoveBook(user);
                break;
            case "4":
                handleSearchBook();
                break;
            case "5":
                libraryService.displayBooks();
                break;
            case "6":
                libraryService.displayLoans();
                break;
            case "7":
                System.out.println("\nGracias por utilizar nuestro servicio. ¡Hasta pronto!");
                return false;
            default:
                System.out.println("\n❌ Opción inválida. Intenta nuevamente.");
        }
        return true;
    }

    private boolean showReaderMenu(User user) {
        // Mostrar catálogo al inicio
        System.out.println("\n=== CATÁLOGO DISPONIBLE ===");
        libraryService.displayBooks();

        System.out.println("\n=== MENÚ LECTOR ===");
        String[] options = {
                "1. Buscar libro",
                "2. Prestar libro",
                "3. Devolver libro",
                "4. Ver catálogo completo",
                "5. Salir"
        };

        for (String option : options) {
            System.out.println(option);
        }

        System.out.print("\nElige una opción: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                handleSearchBook();
                break;
            case "2":
                handleLoanBook(user);
                break;
            case "3":
                handleReturnBook(user);
                break;
            case "4":
                libraryService.displayBooks();
                break;
            case "5":
                System.out.println("\nGracias por utilizar nuestro servicio. ¡Hasta pronto!");
                return false;
            default:
                System.out.println("\n❌ Opción inválida. Intenta nuevamente.");
        }
        return true;
    }

    // Métodos privados para manejar cada acción
    private void handleAddBook(User user) {
        try {
            System.out.print("\nID del libro: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Título del libro: ");
            String title = scanner.nextLine();

            System.out.print("Autor del libro: ");
            String author = scanner.nextLine();

            System.out.print("Descripción del libro: ");
            String description = scanner.nextLine();

            Book book = new Book(id, title, author, description);
            libraryService.addBook(user, book);
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Error: El ID debe ser un número válido.");
        }
    }

    private void handleDisableBook(User user) {
        try {
            System.out.print("\nID del libro a deshabilitar: ");
            int id = Integer.parseInt(scanner.nextLine());
            libraryService.unavailableBook(user, id);
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Error: El ID debe ser un número válido.");
        }
    }

    private void handleRemoveBook(User user) {
        try {
            System.out.print("\nID del libro a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            libraryService.removeBook(user, id);
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Error: El ID debe ser un número válido.");
        }
    }

    private void handleSearchBook() {
        System.out.print("\nTítulo del libro: ");
        String title = scanner.nextLine();
        libraryService.displayBookByTitle(title);
    }

    private void handleLoanBook(User user) {
        try {
            System.out.print("\nID del libro a prestar: ");
            int id = Integer.parseInt(scanner.nextLine());
            libraryService.loanBook(user, id);
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Error: El ID debe ser un número válido.");
        }
    }

    private void handleReturnBook(User user) {
        try {
            System.out.print("\nID del libro a devolver: ");
            int id = Integer.parseInt(scanner.nextLine());
            libraryService.returnBook(user, id);
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Error: El ID debe ser un número válido.");
        }
    }
}