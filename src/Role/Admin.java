package Role;

public class Admin extends Role {
    @Override
    public boolean canPerfom(Action action) {
        return true;
    }
}
