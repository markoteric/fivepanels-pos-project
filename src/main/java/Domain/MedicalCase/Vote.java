package Domain.MedicalCase;

import Domain.User.User;
import Foundation.Assertion.Assertion;

public class Vote {

    private User user;
    private Answer answer;
    private Integer percentage;

    public Vote(User user, Answer answer, Integer percentage) {
        this.user = user;
        this.answer = answer;
        setPercentage(percentage);
    }

    public User getUser() {
        return user;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        Assertion.isTrue(percentage >= 0 && percentage <= 100, () -> "Percentage must be between 0 and 100");
        this.percentage = percentage;
    }
}
