package Domain.Misc;

public class Email {

    private String email;

    public Email(String email) {

        setEmail(email);
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        // TODO ASSERTION
        // Mail-Check: Not Null, Not Empty, maxlength of 320, maxlength of local part (before the @) of 64, maxlength of domain is 255, maybe external email-validation? (RFC3696)
        this.email = email;
    }

}
