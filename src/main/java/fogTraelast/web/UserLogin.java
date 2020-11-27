package fogTraelast.web;

public class UserLogin {

        public static User login(String email, String password ){
            return null;
        }


        public static User createBruger(String email, String password, String phoneNumber,
                                        String address, String name, int mailNr, String city, String role ) {


            User user = new User(name, address, city, phoneNumber, email, password, mailNr, role);
            return user;
        }
}
