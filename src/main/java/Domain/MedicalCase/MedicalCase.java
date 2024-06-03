package Domain.MedicalCase;

import Domain.User.Misc.Email;
import Domain.User.Misc.Hashtag;
import Domain.User.Misc.Password;
import Domain.User.User;
import Foundation.Assertion.Assertion;
import Foundation.BaseEntity;
import Foundation.Exception.UserException;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicalCase extends BaseEntity {

    private String medicalCaseName;
    private User owner;
    private List<String> textContent;
    private List<File> fileContent;
    private Set<User> medicalCaseMembers;
    private Integer viewCount;
    private Integer likeCount;
    private Set<Hashtag> medicalCaseHashtags;
    private Set<Vote> votes;
    private Set<User> usersLiked;

    public MedicalCase() {
        super();
        this.medicalCaseName = "foobarlol";
        this.owner = new User("John", "Doe", new Email("owner@example.com"), new Password("foobar123!XD".toCharArray()));
        this.textContent = List.of("This is a sample text content of medical case.");
        this.fileContent = List.of(new File("sample.txt"));
        this.medicalCaseMembers = new HashSet<>(Collections.singleton(new User("John", "Doe", new Email("member@example.com"), new Password("foobar123!XD".toCharArray()))));
        this.viewCount = 0;
        this.likeCount = 0;
        this.medicalCaseHashtags = new HashSet<>(Collections.singleton(new Hashtag("#sampleTag")));
        this.votes = new HashSet<>(Collections.singleton(new Vote()));
        this.usersLiked = new HashSet<>();
    }

    public MedicalCase(String medicalCaseName, User owner, List<String> textContent, List<File> fileContent, Set<User> medicalCaseMembers, Set<Hashtag> medicalCaseHashtags, Set<Vote> votes) {
        super();
        setMedicalCaseName(medicalCaseName);
        setOwner(owner);
        setTextContent(textContent);
        setFileContent(fileContent);
        setMedicalCaseMembers(medicalCaseMembers);
        this.viewCount = 0;
        this.likeCount = 0;
        setMedicalCaseHashtags(medicalCaseHashtags);
        setVotes(votes);
        this.usersLiked = new HashSet<>();
    }

    public String getMedicalCaseName() {
        return medicalCaseName;
    }

    public void setMedicalCaseName(String medicalCaseName) {
        Assertion.isNotNull(medicalCaseName, "medicalCaseName");
        Assertion.isNotBlank(medicalCaseName, "medicalCaseName");
        Assertion.hasMinLength(medicalCaseName, 8, "medicalCaseName");
        Assertion.hasMaxLength(medicalCaseName, 128, "medicalCaseName");
        this.medicalCaseName = medicalCaseName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        Assertion.isNotNull(owner, "owner");
        this.owner = owner;
    }

    public List<String> getTextContent() {
        return textContent;
    }

    public void setTextContent(List<String> textContent) {
        Assertion.isNotNull(textContent, "textContent");
        Assertion.isNotEmpty(textContent, "textContent");
        this.textContent = textContent;
    }

    public List<File> getFileContent() {
        return fileContent;
    }

    public void setFileContent(List<File> fileContent) {
        Assertion.isNotNull(fileContent, "fileContent");
        Assertion.isNotEmpty(fileContent, "fileContent");
        this.fileContent = fileContent;
    }

    public Set<User> getMedicalCaseMembers() {

        return medicalCaseMembers;
    }

    public void setMedicalCaseMembers(Set<User> medicalCaseMembers) {
        Assertion.isNotNull(medicalCaseMembers, "medicalCaseMembers");
        if (medicalCaseMembers.contains(this.owner)) {
            throw new UserException("Owner cannot be a member of medical case");
        }
        this.medicalCaseMembers = medicalCaseMembers;
    }

    public Integer getViewCount() {

        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        Assertion.isNotNull(viewCount, "viewCount");
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        Assertion.isNotNull(likeCount, "likeCount");
        this.likeCount = likeCount;
    }

    public Set<Hashtag> getMedicalCaseHashtags() {
        return medicalCaseHashtags;
    }

    public void setMedicalCaseHashtags(Set<Hashtag> medicalCaseHashtags) {
        Assertion.isNotNull(medicalCaseHashtags, "medicalCaseHashtags");
        this.medicalCaseHashtags = medicalCaseHashtags;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        Assertion.isNotNull(votes, "votes");
        this.votes = votes;
    }

    public void addView() {
        this.viewCount++;
    }

    public void addLike(User user) {
        Assertion.isNotNull(user, "user");
        if (usersLiked.contains(user)) {
            throw new UserException("User has already liked this medical case");
        }
        usersLiked.add(user);
        this.likeCount++;
    }

    public void addMedicalCaseMember(User member) {
        Assertion.isNotNull(member, "member");
        this.medicalCaseMembers.add(member);
    }

    public void removeMedicalCaseMember(User member) {
        Assertion.isNotNull(member, "member");
        Assertion.isNotNull(this.medicalCaseMembers, "medicalCaseMembers");
        Assertion.isTrue(this.medicalCaseMembers.contains(member), () -> "member is not in medical case");
        this.medicalCaseMembers.remove(member);
    }

    public void assessVotes() {

        // TODO
    }
}
