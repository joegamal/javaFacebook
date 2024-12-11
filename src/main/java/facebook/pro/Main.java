package facebook.pro;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserManager.loadUserFromjsonFile("users.json");

        Welcome screen = new Welcome();
        if(!(UserManager.listOfUsers == null))
            System.out.println(UserManager.listOfUsers.toString());
        UserManager.store();


    }
}
