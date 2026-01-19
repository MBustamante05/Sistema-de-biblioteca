package Role;

public class Reader extends Role {
    @Override
    public boolean canPerform(Action action) {
        return action == Action.LOAN_BOOK
                || action == Action.RETURN_BOOK
                || action == Action.VIEW_CATALOG;
    }
}
