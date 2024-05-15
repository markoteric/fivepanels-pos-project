package Domain.Misc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class Password {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Password password1 = new Password("1235!aB");
        System.out.println(password1);
        System.out.println(password1.getHashedPassword());
    }

    private char[] password;
    private byte[] hashedPassword;

    public Password() {

        setPassword("foobar");
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
        this.password = password.toCharArray();
    }

    // FOR DATABASE
    public byte[] getHashedPassword() throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        return md.digest(new String(password).getBytes(StandardCharsets.UTF_8));
    }

    public boolean isValid() {
        // TODO: Implement requirements for password validation
        Assertion.isNotNull(password, "password");
        Assertion.isNotNull(hashedPassword, "hashedPassword");
        Assertion.isNotBlank(Arrays.toString(password), "password");
        Assertion.isNotBlank(Arrays.toString(hashedPassword), "hashedPassword");
        return true;
    }
}
