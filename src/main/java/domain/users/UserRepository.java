package domain.users;

public interface UserRepository {
    User createUsr(String email, String password);
    User loginUsr(String email, String password)  throws NoSuchUserExists;

    User findUser(int id) throws NoSuchUserExists;
}
