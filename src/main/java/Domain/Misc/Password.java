package Domain.Misc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Password password1 = new Password("Noob");
        System.out.println(password1);
    }

    private char[] password;
    private byte[] hashedPassword;

    public Password() {

    }

    public Password(String password) {

        setPassword(password);
    }

    public char[] getPassword() {

        return this.password;
    }

    public void setPassword(String password) {

        Assertion.isNotNull(password, "password");
        Assertion.isNotBlank(password, "password");
        Assertion.longerThan10Chars(password, "password");
        Assertion.containsNumbers(password, "password");
        this.password = password.toCharArray();
    }

    // FOR DATABASE
    public byte[] getHashedPassword(String password) throws NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        return md.digest(new String(password).getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValid() throws NoSuchAlgorithmException {
        // TODO: Implement requirements for password validation

        Assertion.isNotNull(hashedPassword, "hashedPassword");
        Assertion.isNotNull(hashedPassword, "hashedPassword");
        Assertion.isNotBlank(hashedPassword.toString(), "password");
        Assertion.isNotBlank(hashedPassword.toString(), "hashedPassword");
        Assertion.longerThan10Chars(hashedPassword.toString(), "password");
        Assertion.containsNumbers(hashedPassword.toString(), "password");

        return true;
    }
}
