package Domain.User.Misc;

import Foundation.Assertion.Assertion;
import Foundation.Exception.UserException;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.nulabinc.zxcvbn.Strength;

import java.util.Arrays;

public class Password {

    // Not null, meet all criteria below
    private char[] password;

    public Password(char[] password) {
        setPassword(password);
    }

    public Password() {
        setPassword("StrongPassword123!FoobarLOL".toCharArray());
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] passwordToHash) {
        Assertion.isNotNull(passwordToHash, "passwordToHash");
        String passwordStr = new String(passwordToHash);

        Assertion.isNotBlank(passwordStr, "passwordToHash");
        Assertion.hasMinLength(passwordStr, 8, "passwordToHash");
        Assertion.hasMaxLength(passwordStr, 128, "passwordToHash");
        Assertion.containsLetter(passwordStr, "passwordToHash");
        Assertion.containsNumber(passwordStr, "passwordToHash");
        Assertion.containsSpecialCharacter(passwordStr, "passwordToHash");

        if (!isPwStrongEnough(passwordStr)) {
            throw new UserException("Password is not strong enough");
        }

        this.password = hashPassword(passwordToHash);
    }

    private char[] hashPassword(char[] password) {
        return BCrypt.withDefaults().hashToChar(12, password);
    }

    public boolean isPwStrongEnough(CharSequence password) {
        Zxcvbn zxcvbn = new Zxcvbn();
        Strength strength = zxcvbn.measure(password);
        return strength.getScore() >= 3;
    }

    @Override
    public String toString() {
        return Arrays.toString(password);
    }

    public static void main(String[] args) {

        Password password = new Password();

        // Printing the already hashed password as char array
        System.out.println("Already hashed password as char array: ");
        System.out.println(password);
        System.out.println();

        // Printing the hashed password as string
        System.out.println("Hashed password as string: ");
        System.out.println(new String(password.getPassword()));
        System.out.println();

        // Printing if the password is strong enough
        System.out.println("Is password strong enough: ");
        System.out.println(password.isPwStrongEnough("password123!XDFOOBAR"));
        System.out.println();

        // Printing if the password is not strong enough
        System.out.println("Is password strong enough (weak): ");
        System.out.println(password.isPwStrongEnough("weak"));
    }
}
