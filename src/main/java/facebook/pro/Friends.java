package facebook.pro;

class Friends extends User {
    private boolean isFriend;

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