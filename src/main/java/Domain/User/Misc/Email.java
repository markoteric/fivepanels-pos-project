package Domain.User.Misc;

import Foundation.Assertion.Assertion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private String email;
    Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
    Matcher m = p.matcher(email);

    public Email() {

        setEmail("paricteric@gmail.com");
    }
    public Email(String email) {

        setEmail(email);
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        // TODO ASSERTION
        Assertion.isNotNull(email, "email");
        Assertion.isNotBlank(email, "email");
        Assertion.hasMaxLength(email, 320, "email");
        if (m.find()&&m.group().equals(email)) { // yes, we will do the assertion

            this.email = email;
        }
        // Mail-Check: Not Null, Not Empty, maxlength of 320, maxlength of local part (before the @) of 64, maxlength of domain is 255, maybe external email-validation? (RFC3696)
    }
}
