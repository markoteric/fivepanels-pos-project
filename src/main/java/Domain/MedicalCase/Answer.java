package Domain.MedicalCase;

import Foundation.Assertion.Assertion;

public class Answer {

    // Not null, not blank, minLength = 2, maxLength = 64,
    private String answer;

    public Answer(String answer) {
        setAnswer(answer);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {

        Assertion.isNotNull(answer, "answer");
        Assertion.isNotBlank(answer, "answer");
        Assertion.hasMinLength(answer, 2, "answer");
        Assertion.hasMaxLength(answer, 128, "answer");
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                '}';
    }
}
