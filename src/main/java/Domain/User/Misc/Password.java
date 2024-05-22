package Domain.User.Misc;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.nulabinc.zxcvbn.Strength;

public class Password {

    // Password needs to be strong enough (isPwStrongEnough), saves us many if-statements / assertions
    private String password;
    private char[] hashedPw;

    public String getPassword() {
        return password;
    }

    public char[] getHashedPw() {
        return hashedPw;
    }

    public Password(char[] pw) {

        setHashedPw(pw);
    }

    public void setPassword(String pw) {

        this.password = pw;
    }

    public boolean isValid() {

        return true;
    }

    public void setHashedPw(char[] actualPw) {

        this.hashedPw = hashPassword(actualPw);
    }

    public char[] hashPassword(char[] otherPw) {

        return hashedPw = BCrypt.withDefaults().hashToChar(12, otherPw);
    }

    public boolean isPwStrongEnough(CharSequence pw) {

        Zxcvbn zxcvbn = new Zxcvbn();
        Strength strength = zxcvbn.measure(pw);
        return strength.getScore() >= 3;
    }
}
