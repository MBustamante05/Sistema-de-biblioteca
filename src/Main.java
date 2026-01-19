import Role.Admin;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializar servicios
        Scanner scanner = new Scanner(System.in);
        LibraryService libraryService = new LibraryService();

        // Inicializar biblioteca con libros predeterminados
        initializeLibrary(libraryService);

        // Servicios de autenticación y menú
        AuthService authService = new AuthService(scanner);
        MenuHandler menuHandler = new MenuHandler(libraryService, scanner);

        // Flujo principal
        System.out.println("=== SISTEMA DE BIBLIOTECA ===");
        User user = authService.login();
        menuHandler.showMenu(user);

        scanner.close();
    }

    /**
     * Inicializa la biblioteca con libros predeterminados
     */
    private static void initializeLibrary(LibraryService libraryService) {
        // Usuario del sistema para agregar libros iniciales
        User systemAdmin = new User("System", new Admin());

        Book bk1 = new Book(
                10001,
                "Alicia en el país de las maravillas",
                "Lewis Carroll",
                "Una niña curiosa que cae en un mundo fantástico subterráneo, lleno de criaturas absurdas y situaciones ilógicas como el Sombrerero Loco y la Reina de Corazones, explorando temas de identidad y la irracionalidad a través de los ojos de una dama victoriana que lucha por mantener la cordura y los modales en un lugar sin sentido, a menudo visto como un sueño o una metáfora del crecimiento y la subjetividad de la percepción."
        );

        Book bk2 = new Book(
                10002,
                "La mujer en la ventana",
                "J.A. Finn",
                "Anna Fox vive sola recluida en su casa de Nueva York, sin atreverse a salir. Pasa el día chateando con desconocidos, bebiendo vino (quizá más de la cuenta), viendo películas antiguas, recordando tiempos felices... y espiando a los vecinos."
        );

        Book bk3 = new Book(
                10003,
                "La chica del tren",
                "Paula Hawkins",
                "Rachel es una mujer de alrededor treinta años de edad, devastada por su divorcio. Cada mañana toma el tren de las 08:04 hacia Londres. Todos los días el tren se detiene en un semáforo rojo, desde donde puede observar varias casas."
        );

        libraryService.addBook(systemAdmin, bk1);
        libraryService.addBook(systemAdmin, bk2);
        libraryService.addBook(systemAdmin, bk3);
    }
}