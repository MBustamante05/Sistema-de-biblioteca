import Role.Role;
import Role.Action;

public class User {

    private String name;
    private Role role;

    public  User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public boolean can(Action action) {
        return role.canPerfom(action);
    }
}
