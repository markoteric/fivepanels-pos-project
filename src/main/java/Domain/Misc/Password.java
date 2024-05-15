package Domain.Misc;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Password {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Password password1 = new Password("foobar12345!A");
        System.out.println(password1);
        System.out.println(Arrays.toString(password1.getHashedPassword("foobar12345!A")));
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
    public byte[] getHashedPassword(String password) {

        this.password = password.toCharArray();
        byte[] bcryptHashBytes = BCrypt.withDefaults().hash(6, password.getBytes(StandardCharsets.UTF_8));
        return bcryptHashBytes;
    }

    public boolean isValid() {
        // TODO: Implement requirements for password validation

        Assertion.isNotNull(password, "password");
        Assertion.isNotNull(hashedPassword, "hashedPassword");
        Assertion.isNotBlank(Arrays.toString(password), "password");
        Assertion.isNotBlank(Arrays.toString(hashedPassword), "hashedPassword");
        Assertion.longerThan10Chars(Arrays.toString(password), "password");
        Assertion.containsNumbers(Arrays.toString(hashedPassword), "password");
        return true;
    }
}
