package Domain.MedicalCase;

import Domain.Messenger.Chat;
import Domain.Messenger.Messenger;
import Domain.User.Misc.Email;
import Domain.User.Misc.Hashtag;
import Domain.User.Misc.Password;
import Domain.User.User;
import Foundation.Assertion.Assertion;
import Foundation.Exception.AssertionException;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicalCase {

    private String medicalCaseName;
    private User owner;
    private List<String> textContent;
    private List<File> fileContent;
    private Set<User> medicalCaseMembers;
    private Integer viewCount;
    private Integer likeCount;
    private Set<Hashtag> medicalCaseHashtags;
    private Set<Vote> votes;
    private Set<User> likedUsers;
    private Messenger messenger;

    public MedicalCase() {
        this.medicalCaseName = "foobarlol";
        this.owner = new User(new Email("owner@example.com"), new Password("foobar123!XD".toCharArray()));
        this.textContent = List.of("This is a sample text content of medical case.");
        this.fileContent = List.of(new File("sample.txt"));
        this.medicalCaseMembers = new HashSet<>(Collections.singleton(new User(new Email("member@example.com"), new Password("foobar123!XD".toCharArray()))));
        this.viewCount = 0;
        this.likeCount = 0;
        this.medicalCaseHashtags = new HashSet<>(Collections.singleton(new Hashtag("#sampleTag")));
        this.votes = new HashSet<>(Collections.singleton(new Vote()));
        this.likedUsers = new HashSet<>();
        initializeMessenger();
    }

    public MedicalCase(String medicalCaseName, User owner, List<String> textContent, List<File> fileContent, Set<User> medicalCaseMembers, Set<Hashtag> medicalCaseHashtags, Set<Vote> votes) {
        setMedicalCaseName(medicalCaseName);
        setOwner(owner);
        setTextContent(textContent);
        setFileContent(fileContent);
        setMedicalCaseMembers(medicalCaseMembers);
        this.viewCount = 0;
        this.likeCount = 0;
        setMedicalCaseHashtags(medicalCaseHashtags);
        setVotes(votes);
        this.likedUsers = new HashSet<>();
        initializeMessenger();
    }

    public void initializeMessenger() {

        this.messenger = new Messenger();
        Chat chat = new Chat("MedicalCase Chat", new HashSet<>(getMedicalCaseMembers()));
        this.messenger.addChat(chat);
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
        this.medicalCaseMembers = medicalCaseMembers;
        initializeMessenger(); // Reinitialize the messenger when members change
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

    public Messenger getMessenger() {
        return messenger;
    }

    public void addView() {
        this.viewCount++;
    }

    public void addLike(User user) {
        Assertion.isNotNull(user, "user");
        if (likedUsers.contains(user)) {
            throw new AssertionException("User has already liked this medical case");
        }
        likedUsers.add(user);
        this.likeCount++;
    }

    public void addMedicalCaseMember(User member) {
        Assertion.isNotNull(member, "member");
        this.medicalCaseMembers.add(member);
        initializeMessenger(); // Update the messenger when members change
    }

    public void removeMedicalCaseMember(User member) {
        Assertion.isNotNull(member, "member");
        Assertion.isNotNull(this.medicalCaseMembers, "medicalCaseMembers");
        Assertion.isTrue(this.medicalCaseMembers.contains(member), () -> "member is not in medical case");
        this.medicalCaseMembers.remove(member);
        initializeMessenger(); // Update the messenger when members change
    }
}
