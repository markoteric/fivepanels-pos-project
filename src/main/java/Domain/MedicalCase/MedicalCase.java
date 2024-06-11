package Domain.MedicalCase;

import Domain.User.User;
import Domain.User.Misc.Hashtag;

import Foundation.Assertion.Assertion;
import Foundation.BaseEntity;
import Foundation.Exception.UserException;

import java.io.File;
import java.util.*;

public class MedicalCase extends BaseEntity {

    private String medicalCaseName;
    private User owner;
    private List<String> textContent;
    private List<File> fileContent;
    private Set<User> medicalCaseMembers;
    private Integer viewCount;
    private Integer likeCount;
    private Set<Hashtag> medicalCaseHashtags;
    private Set<Answer> answers;
    private Map<UUID, Map<User, Integer>> votes;

    public MedicalCase(String medicalCaseName, User owner, List<String> textContent, List<File> fileContent, Set<User> medicalCaseMembers, Set<Hashtag> medicalCaseHashtags) {
        Assertion.isNotNull(medicalCaseName, "medicalCaseName");
        Assertion.isNotBlank(medicalCaseName, "medicalCaseName");
        Assertion.hasMinLength(medicalCaseName, 8, "medicalCaseName");
        Assertion.hasMaxLength(medicalCaseName, 128, "medicalCaseName");

        this.medicalCaseName = medicalCaseName;
        this.owner = owner;
        this.textContent = textContent != null ? textContent : new ArrayList<>();
        this.fileContent = fileContent != null ? fileContent : new ArrayList<>();
        this.medicalCaseMembers = medicalCaseMembers != null ? medicalCaseMembers : new HashSet<>();
        this.viewCount = 0;
        this.likeCount = 0;
        this.medicalCaseHashtags = medicalCaseHashtags != null ? medicalCaseHashtags : new HashSet<>();
        this.answers = new HashSet<>();
        this.votes = new HashMap<>();
    }

    public String getMedicalCaseName() {
        return medicalCaseName;
    }

    public User getOwner() {
        return owner;
    }

    public List<String> getTextContent() {
        return textContent;
    }

    public List<File> getFileContent() {
        return fileContent;
    }

    public Set<User> getMedicalCaseMembers() {
        return medicalCaseMembers;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public Set<Hashtag> getMedicalCaseHashtags() {
        return medicalCaseHashtags;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public Map<UUID, Map<User, Integer>> getVotes() {
        return votes;
    }

    public void addAnswer(String answerText, boolean isCorrect) {
        Assertion.isNotNull(answerText, "answerText");
        Assertion.isNotBlank(answerText, "answerText");
        answers.add(new Answer(answerText, isCorrect));
    }

    public void vote(User user, UUID answerId, int percentage) {
        Assertion.isNotNull(user, "user");
        Assertion.isNotNull(answerId, "answerId");
        Assertion.isTrue(percentage >= 0 && percentage <= 100, () -> "Percentage must be between 0 and 100");

        if (!medicalCaseMembers.contains(user)) {
            throw new UserException("User is not a member of this medical case");
        }

        Map<User, Integer> userVotes = votes.getOrDefault(answerId, new HashMap<>());
        userVotes.put(user, percentage);
        votes.put(answerId, userVotes);

        // Remove immediate validation
        // validateVotes(user);
    }

    public void validateAllVotes() {
        for (User user : medicalCaseMembers) {
            int totalPercentage = getTotalVotePercentageForUser(user);
            if (totalPercentage != 100) {
                throw new UserException("Total votes by " + user + " must be exactly 100%");
            }
        }
    }

    public int getTotalVotePercentageForUser(User user) {
        return votes.values().stream()
                .mapToInt(userVotes -> userVotes.getOrDefault(user, 0))
                .sum();
    }

    public Map<UUID, Double> getLiveVoteResults() {
        Map<UUID, Double> results = new HashMap<>();

        for (Map.Entry<UUID, Map<User, Integer>> entry : votes.entrySet()) {
            UUID answerId = entry.getKey();
            Map<User, Integer> userVotes = entry.getValue();

            double total = 0;
            for (int vote : userVotes.values()) {
                total += vote;
            }
            results.put(answerId, total / userVotes.size());
        }

        return results;
    }

    public void updateExpertScores() {
        validateAllVotes(); // Validate votes before updating scores

        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                Map<User, Integer> answerVotes = votes.get(answer.getId());
                if (answerVotes != null) {
                    for (Map.Entry<User, Integer> entry : answerVotes.entrySet()) {
                        entry.getKey().getUserProfile().addExpertScore(entry.getValue(), true);
                    }
                }
            } else {
                Map<User, Integer> answerVotes = votes.get(answer.getId());
                if (answerVotes != null) {
                    for (Map.Entry<User, Integer> entry : answerVotes.entrySet()) {
                        entry.getKey().getUserProfile().addExpertScore(entry.getValue(), false);
                    }
                }
            }
        }
    }

}
