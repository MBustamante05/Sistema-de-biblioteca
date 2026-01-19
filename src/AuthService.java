import Role.Admin;
import Role.Reader;
import java.util.Scanner;

public class AuthService {
    private final Scanner scanner;

    public AuthService(Scanner scanner) {
        this.scanner = scanner;
    }

    public User login() {
        System.out.print("Ingrese su nombre: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        if (password.equals("admin")) {
            System.out.println("\n¡Bienvenido, Administrador " + name + "!\n");
            return new User(name, new Admin());
        } else {
            System.out.println("\n¡Bienvenido, " + name + "!\n");
            return new User(name, new Reader());
        }
    }
}