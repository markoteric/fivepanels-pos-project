package OtherTests;

import Domain.MedicalCase.MedicalCase;
import Domain.User.User;
import Domain.User.Misc.Email;
import Domain.User.Misc.Password;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalCaseTest {

    private User owner;
    private User member1;
    private User member2;
    private MedicalCase medicalCase;

    @BeforeEach
    public void setUp() {
        owner = new User("John", "Doe", "New York", new Email("owner@example.com"), new Password("password123!LOLFOOBAR".toCharArray()));
        member1 = new User("Jane", "Doe", "New York", new Email("member1@example.com"), new Password("password123!FOOBAR".toCharArray()));
        member2 = new User("Jack", "Smith", "New York", new Email("member2@example.com"), new Password("password123!FOOBAR".toCharArray()));

        medicalCase = new MedicalCase("Sample Medical Case", owner, List.of("This is a sample text content."), List.of(new File("sample.txt")), new HashSet<>(Arrays.asList(member1, member2)), new HashSet<>());
    }

    @Test
    public void test_MedicalCase_ShouldAllowVotingWithinLimit() {
        owner.addAnswerToMedicalCase(medicalCase, "Answer A", false);
        owner.addAnswerToMedicalCase(medicalCase, "Answer B", true);

        UUID answerAId = medicalCase.getAnswers().stream().filter(a -> a.getAnswerText().equals("Answer A")).findFirst().get().getId();
        UUID answerBId = medicalCase.getAnswers().stream().filter(a -> a.getAnswerText().equals("Answer B")).findFirst().get().getId();

        member1.voteOnMedicalCase(medicalCase, answerAId, 60);
        member1.voteOnMedicalCase(medicalCase, answerBId, 20);
        member2.voteOnMedicalCase(medicalCase, answerAId, 20);

        assertEquals((60 + 20) / medicalCase.getAnswers().size(), medicalCase.getLiveVoteResults().get(answerAId).intValue());
        assertEquals(20, medicalCase.getLiveVoteResults().get(answerBId).intValue());
    }
}
