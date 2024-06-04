package Domain.MedicalCase;

import Foundation.Assertion.Assertion;

public class Vote {

    // Not null, between 2 and 5 possible answers, not blank, minlength 2, max length 64
    private Answer answer;
    // Not null, between 0 and 100 value total
    private Integer percentage;

    public Vote(Answer answer, Integer percentage) {

        setAnswer(answer);
        setPercentage(percentage);
    }

    public Vote() {

        setAnswer(null);
        setPercentage(0);
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {

        this.answer = answer;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {

        Assertion.isNotNull(percentage, "percentage");
        Assertion.isGreaterThan(percentage, -1, "percentage");
        Assertion.isLessThan(percentage, 101, "percentage");
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "answer=" + answer +
                ", percentage=" + percentage +
                '}';
    }
}
