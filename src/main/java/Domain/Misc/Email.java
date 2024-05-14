package Domain.Misc;

public class Email {

    private String email;

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
        // Mail-Check: Not Null, Not Empty, maxlength of 320, maxlength of local part (before the @) of 64, maxlength of domain is 255, maybe external email-validation? (RFC3696)
        this.email = email;
    }

}
