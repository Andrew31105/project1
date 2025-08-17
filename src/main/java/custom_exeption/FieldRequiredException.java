package custom_exeption;

public class FieldRequiredException extends Exception {
    private static final long serialVersionUID = 1L;

    public FieldRequiredException(String s) {
        super(s);
    }
}
