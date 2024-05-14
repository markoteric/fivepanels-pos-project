package Domain.Misc;

public class Password {
    private char[] password;
    private String hashedPassword;

    public Password(String password) {
        this.password = password.toCharArray();
    }
}
