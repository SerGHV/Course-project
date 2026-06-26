package model;

public class User {

    private int userId;
    private String login;
    private String passwordHash;
    private String fullName;
    private String roleName;

    public User() {
    }

    public User(int userId,
                String login,
                String passwordHash,
                String fullName,
                String roleName) {

        this.userId = userId;
        this.login = login;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.roleName = roleName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}