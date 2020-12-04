package infrastructure;

import java.sql.SQLException;

public class UnexpectedDBError extends RuntimeException {
    public UnexpectedDBError() {
    }

    public UnexpectedDBError(String message) {
        super(message);
    }

    public UnexpectedDBError(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedDBError(Throwable cause) {
        super(cause);
    }
}
