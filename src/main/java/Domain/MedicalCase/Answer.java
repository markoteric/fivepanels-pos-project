package Domain.MedicalCase;

import Domain.Assertion.Assertion;

public class Answer {
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
        Assertion.hasMaxLength(answer, 255, "answer");
        this.answer = answer;
    }
}
