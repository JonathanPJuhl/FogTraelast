package domain.users;

import java.io.IOException;
import java.net.Socket;


public class Client {
    private final Socket socket;
    private final String identifierName;
    private User user;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        this.identifierName = socket.getInetAddress().getHostAddress() + " : " + socket.getPort();
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
