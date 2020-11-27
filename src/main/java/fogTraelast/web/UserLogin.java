package fogTraelast.web;

public class UserLogin {

        public static User login(String email, String password ){
            return null;
        }


        public static User createBruger(String email, String password, String telefonnummer,
                                        String adresse, String navn, int postnummer, String by, String rolle ) {


            User user = new User(navn, adresse, by, telefonnummer, email, password, postnummer, rolle);
            return user;
        }
}
