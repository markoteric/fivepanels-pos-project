package UserTest;

import Domain.Messenger.Chat;
import Domain.Messenger.Messenger;
import Domain.MedicalCase.MedicalCase;
import Domain.User.Misc.Email;
import Domain.User.Misc.Password;
import Domain.User.User;
import Domain.User.UserProfile;
import Domain.User.UserRelationship;
import Foundation.Exception.AssertionException;
import Foundation.Exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;
    private User friend;
    private User anotherFriend;
    private MedicalCase medicalCase;
    private Messenger messenger;

    @BeforeEach
    public void setUp() {
        user = new User(new Email("user@example.com"), new Password("foobar123!XD".toCharArray()));
        friend = new User(new Email("friend@example.com"), new Password("foobar123!XD".toCharArray()));
        anotherFriend = new User(new Email("anotherfriend@example.com"), new Password("foobar123!XD".toCharArray()));
        medicalCase = new MedicalCase();
        messenger = new Messenger();
    }

    @Test
    public void test_User_ShouldBeInitializedCorrectly() {
        assertNotNull(user.getId());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        assertFalse(user.isVerified());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPassword());
        assertNotNull(user.getMessenger());
        assertNotNull(user.getIsMemberOfMedicalCases());
        assertNotNull(user.getIsOwnerOfMedicalCases());
    }

    @Test
    public void test_User_ShouldBeVerified() {
        user.verify();
        assertTrue(user.isVerified());
    }

    @Test
    public void test_User_ShouldAddAndRemoveMedicalCase() {
        user.createNewMedicalCase(medicalCase);
        assertTrue(user.getIsOwnerOfMedicalCases().contains(medicalCase));

        user.removeMedicalCase(medicalCase);
        assertFalse(user.getIsOwnerOfMedicalCases().contains(medicalCase));
    }

    @Test
    public void test_User_ShouldJoinAndLeaveMedicalCase() {
        user.joinMedicalCase(medicalCase);
        assertTrue(user.getIsMemberOfMedicalCases().contains(medicalCase));

        user.leaveMedicalCase(medicalCase);
        assertFalse(user.getIsMemberOfMedicalCases().contains(medicalCase));
    }

    @Test
    public void test_User_ShouldThrowException_WhenEmailIsNull() {
        assertThrows(AssertionException.class, () -> new User(null, new Password("foobar123!XD".toCharArray())));
    }

    @Test
    public void test_User_ShouldThrowException_WhenPasswordIsNull() {
        assertThrows(AssertionException.class, () -> new User(new Email("user@example.com"), null));
    }

    @Test
    public void test_User_ShouldSetAndGetUserProfile() {
        UserProfile profile = new UserProfile();
        user.setUserProfile(profile);
        assertEquals(profile, user.getUserProfile());
    }

    @Test
    public void test_User_ShouldSetAndGetUserRelationship() {
        user.setUserRelationship(friend.getId(), UserRelationship.ESTABLISHED);
        assertEquals(UserRelationship.ESTABLISHED, user.getUserRelationship(friend.getId()));
    }


    @Test
    public void test_User_ShouldAddAndRemoveFriend() {
        user.addFriend(friend);
        assertEquals(UserRelationship.OUTGOING, user.getRelationships().get(friend.getId()));
        assertEquals(UserRelationship.INCOMING, friend.getRelationships().get(user.getId()));

        friend.acceptFriendRequest(user);
        assertEquals(UserRelationship.ESTABLISHED, user.getRelationships().get(friend.getId()));
        assertEquals(UserRelationship.ESTABLISHED, friend.getRelationships().get(user.getId()));

        user.removeFriend(friend);
        assertFalse(user.getRelationships().containsKey(friend.getId()));
        assertFalse(friend.getRelationships().containsKey(user.getId()));
    }

    @Test
    public void test_User_ShouldAcceptAndDeclineFriendRequest() {
        user.addFriend(friend);
        assertEquals(UserRelationship.OUTGOING, user.getRelationships().get(friend.getId()));
        assertEquals(UserRelationship.INCOMING, friend.getRelationships().get(user.getId()));

        friend.declineFriendRequest(user);
        assertFalse(user.getRelationships().containsKey(friend.getId()));
        assertFalse(friend.getRelationships().containsKey(user.getId()));
    }

    @Test
    public void test_User_ShouldCreateDirectChatWhenFriendRequestIsAccepted() {
        user.addFriend(friend);
        friend.addFriend(user);

        friend.acceptFriendRequest(user);
        assertEquals(UserRelationship.ESTABLISHED, user.getRelationships().get(friend.getId()));
        assertEquals(UserRelationship.ESTABLISHED, friend.getRelationships().get(user.getId()));

        boolean chatExists = user.getMessenger().getChats().stream()
                .anyMatch(chat -> chat.getMembers().contains(user) && chat.getMembers().contains(friend));
        assertTrue(chatExists);
    }

    @Test
    public void test_User_ShouldNotAddSelfAsFriend() {
        assertThrows(UserException.class, () -> user.addFriend(user));
    }
}
