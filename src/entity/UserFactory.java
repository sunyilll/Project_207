package entity;

public class UserFactory {
    /**
     * Requires: password is valid.
     * @param userid
     * @param nickname
     * @param password
     * @return
     */

    public User create(String userid, String nickname, String password) {
        return new User(userid, nickname, password);
    }
}
