package Domain.User.Misc;

import Foundation.Exception.UserException;
import org.apache.commons.validator.routines.EmailValidator;

public class Email {

    private String email;

    public Email(String email) {

        setEmail(email);
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        if (!EmailValidator.getInstance().isValid(email)) {

            throw new UserException("Invalid email format.");
        }

        this.email = email;
    }
}
