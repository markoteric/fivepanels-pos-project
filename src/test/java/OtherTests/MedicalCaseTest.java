package OtherTests;

import Domain.User.Misc.Email;
import Domain.User.Misc.Hashtag;
import Domain.User.Misc.Password;
import Foundation.Exception.AssertionException;
import Foundation.Exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Domain.MedicalCase.MedicalCase;
import Domain.MedicalCase.Vote;
import Domain.User.User;

import static org.junit.jupiter.api.Assertions.*;

public class MedicalCaseTest {

    private MedicalCase medicalCase;
    private User owner;
    private User member;
    private User newMember;
    private Hashtag hashtag;
    private Vote vote;

    @BeforeEach
    public void setup() {
        owner = new User("Owner", "Sample", new Email("owner@example.com"), new Password("foobar123!XD".toCharArray()));
        member = new User("Member", "Sample", new Email("member@example.com"), new Password("foobar123!XD".toCharArray()));
        newMember = new User("New Member", "Sample", new Email("newmember@example.com"), new Password("foobar123!XD".toCharArray()));
        hashtag = new Hashtag("#sampleTag");
        vote = new Vote();

        medicalCase = new MedicalCase();
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenNameIsNull() {
        assertThrows(AssertionException.class, () -> medicalCase.setMedicalCaseName(null));
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenNameIsBlank() {
        assertThrows(AssertionException.class, () -> medicalCase.setMedicalCaseName(""));
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenNameIsTooShort() {
        assertThrows(AssertionException.class, () -> medicalCase.setMedicalCaseName("Short"));
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenNameIsTooLong() {
        String longName = "a".repeat(129);
        assertThrows(AssertionException.class, () -> medicalCase.setMedicalCaseName(longName));
    }

    @Test
    public void test_MedicalCase_ShouldSetAndGetOwner() {
        medicalCase.setOwner(newMember);
        assertEquals(newMember, medicalCase.getOwner());
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenOwnerIsNull() {
        assertThrows(AssertionException.class, () -> medicalCase.setOwner(null));
    }

    @Test
    public void test_MedicalCase_ShouldSetAndGetTextContent() {
        List<String> newTextContent = List.of("New text content");
        medicalCase.setTextContent(newTextContent);
        assertEquals(newTextContent, medicalCase.getTextContent());
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenTextContentIsNull() {
        assertThrows(AssertionException.class, () -> medicalCase.setTextContent(null));
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenTextContentIsEmpty() {
        assertThrows(AssertionException.class, () -> medicalCase.setTextContent(Collections.emptyList()));
    }

    @Test
    public void test_MedicalCase_ShouldSetAndGetFileContent() {
        List<File> newFileContent = List.of(new File("newfile.txt"));
        medicalCase.setFileContent(newFileContent);
        assertEquals(newFileContent, medicalCase.getFileContent());
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenFileContentIsNull() {
        assertThrows(AssertionException.class, () -> medicalCase.setFileContent(null));
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenFileContentIsEmpty() {
        assertThrows(AssertionException.class, () -> medicalCase.setFileContent(Collections.emptyList()));
    }

    @Test
    public void test_MedicalCase_ShouldAddAndRemoveMedicalCaseMember() {
        medicalCase.addMedicalCaseMember(newMember);
        assertTrue(medicalCase.getMedicalCaseMembers().contains(newMember));

        medicalCase.removeMedicalCaseMember(newMember);
        assertFalse(medicalCase.getMedicalCaseMembers().contains(newMember));
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenRemovingNonExistentMember() {
        User nonExistentMember = new User("Nonexistent", "Member", new Email("nonexistent@example.com"), new Password("foobar123!XD".toCharArray()));
        assertThrows(AssertionException.class, () -> medicalCase.removeMedicalCaseMember(nonExistentMember));
    }

    @Test
    public void test_MedicalCase_ShouldSetAndGetViewCount() {
        medicalCase.setViewCount(100);
        assertEquals(100, medicalCase.getViewCount());
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenViewCountIsNull() {
        assertThrows(AssertionException.class, () -> medicalCase.setViewCount(null));
    }

    @Test
    public void test_MedicalCase_ShouldAddView() {
        int initialViewCount = medicalCase.getViewCount();
        medicalCase.addView();
        assertEquals(initialViewCount + 1, medicalCase.getViewCount());
    }

    @Test
    public void test_MedicalCase_ShouldSetAndGetLikeCount() {
        medicalCase.setLikeCount(100);
        assertEquals(100, medicalCase.getLikeCount());
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenLikeCountIsNull() {
        assertThrows(AssertionException.class, () -> medicalCase.setLikeCount(null));
    }

    @Test
    public void test_MedicalCase_ShouldAddLike() {
        int initialLikeCount = medicalCase.getLikeCount();
        medicalCase.addLike(member);
        assertEquals(initialLikeCount + 1, medicalCase.getLikeCount());
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenUserAlreadyLiked() {
        medicalCase.addLike(member);
        assertThrows(UserException.class, () -> medicalCase.addLike(member));
    }

    @Test
    public void test_MedicalCase_ShouldSetAndGetMedicalCaseHashtags() {
        Set<Hashtag> newHashtags = new HashSet<>(Collections.singleton(new Hashtag("#newTag")));
        medicalCase.setMedicalCaseHashtags(newHashtags);
        assertEquals(newHashtags, medicalCase.getMedicalCaseHashtags());
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenMedicalCaseHashtagsAreNull() {
        assertThrows(AssertionException.class, () -> medicalCase.setMedicalCaseHashtags(null));
    }

    @Test
    public void test_MedicalCase_ShouldSetAndGetVotes() {
        Set<Vote> newVotes = new HashSet<>(Collections.singleton(new Vote()));
        medicalCase.setVotes(newVotes);
        assertEquals(newVotes, medicalCase.getVotes());
    }

    @Test
    public void test_MedicalCase_ShouldThrowException_WhenVotesAreNull() {
        assertThrows(AssertionException.class, () -> medicalCase.setVotes(null));
    }
    @Test
    public void test_MedicalCase_ShouldInitializeWithDefaultValues() {
        MedicalCase defaultCase = new MedicalCase();
        assertNotNull(defaultCase);
        assertEquals("foobarlol", defaultCase.getMedicalCaseName());
        assertNotNull(defaultCase.getOwner());
        assertEquals(1, defaultCase.getTextContent().size());
        assertEquals(1, defaultCase.getFileContent().size());
        assertEquals(1, defaultCase.getMedicalCaseMembers().size());
        assertEquals(0, defaultCase.getViewCount());
        assertEquals(0, defaultCase.getLikeCount());
        assertEquals(1, defaultCase.getMedicalCaseHashtags().size());
        assertEquals(1, defaultCase.getVotes().size());
    }
}
