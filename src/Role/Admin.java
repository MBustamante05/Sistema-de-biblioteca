package Role;

public class Admin extends Role {
    @Override
    public boolean canPerform(Action action) {
        return true;
    }
}
