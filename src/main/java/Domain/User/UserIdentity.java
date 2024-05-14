package Domain.User;
import Domain.Misc.Assertion;
import Domain.Misc.Email;
import Domain.Misc.Password;
import java.util.UUID;

public class UserIdentity {

    private UUID id;
    private Email email;
    private Password password;

    public UserIdentity(){
        this.id = UUID.randomUUID();
        this.email = new Email("teric@fivepanels.at");
        this.password = new Password("123456");
    }

    public UserIdentity(UUID id, Email email, Password password){
        this.id = id;
        this.email = email;
        this.password = password;
    }


    // -------------------------------------- SETTER & GETTER -----------------------------------------


    public UUID getUUID(){
        return id;
    }

    public Email getEmail(){
        return email;
    }

    public Password getPassword(){
        return password;
    }

    public void setPassword(Password password){
        this.password = password;
    }


    // -------------------------------------- LOGICS -----------------------------------------
    

    public void updateEmail(Email email){
        Assertion.isNotNull(email, "email");
        Assertion.isNotBlank(email.getEmail(), "email");
        this.email = email;
    }

    public void updatePassword(Password password){
        Assertion.isNotNull(password, "password");
        Assertion.charsAreNotBlank(password.getPassword(), "password");
        this.password = password;
    }








}
