package facebook.pro;

import java.util.ArrayList;
class Friends extends User {
    private boolean isFriend;
    ArrayList <String>nameofFriends;
    public Friends(String email, String password, String username, String gender, String birthDate, boolean isFriend) {
        super(email, password, username, gender, birthDate);
        this.isFriend = isFriend;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }
}