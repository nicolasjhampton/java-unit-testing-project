package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by nicolasjhampton on 10/14/16.
 */
public class UserTest {

    // DONE: Create a new Test Fixture for the User model in a separate but same package test directory structure.


    private Board board;
    private User user1;
    private User user2;
    private Question question;
    private Answer answer;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        board = new Board("Poetry");
        user1 = board.createUser("Bill Yeats");
        user2 = board.createUser("Gurdy Stein");
        question = user1.askQuestion("And what rough beast, its hour come round at last,\n Slouches towards Bethlehem to be born?");
        answer = user2.answerQuestion(question, "A kind in glass and a cousin, a spectacle and nothing strange a single hurt color and an arrangement in a system to pointing.");
    }


    // DONE : Write a test to ensure that the questioner’s reputation goes up by 5 points if their question is upvoted.

    @Test
    public void upVotingQuestionIncreasesReputationFivePoints() throws Exception {

        // Arrange (In setup)

        // Act
        user2.upVote(question);

        // Assert
        assertEquals(5, user1.getReputation());

    }


    // DONE: Write a test to assert that the answerer’s reputation goes up by 10 points if their answer is upvoted.

    @Test
    public void answeringQuestionIncreasesReputationFivePoints() throws Exception {

        // Arrange (In setup)

        // Act
        user1.upVote(answer);

        // Assert
        assertEquals(10, user2.getReputation());

    }


    // DONE: Write a test that proves that having an answer accepted gives the answerer a 15 point reputation boost

    @Test
    public void acceptedAnswerGivesFifteenReputationPoints() throws Exception {

        // Arrange (In setup)

        // Act
        user1.acceptAnswer(answer);

        // Assert
        assertEquals(15, user2.getReputation());

    }


    // DONE: Using a test, ensure that voting either up or down is not allowed on questions or answers by the original author, you know to avoid gaming the system. Ensure the proper exceptions are being thrown.

    // Assert
    @Test(expected = VotingException.class)
    public void userCannotUpvoteTheirOwnAnswer() throws Exception {

        // Arrange (In setup)

        // Act
        user2.upVote(answer);

    }

    // Assert
    @Test(expected = VotingException.class)
    public void userCannotDownvoteTheirOwnAnswer() throws Exception {

        // Arrange (In setup)

        // Act
        user2.downVote(answer);
    }

    // Assert
    @Test(expected = VotingException.class)
    public void userCannotUpvoteTheirOwnQuestion() throws Exception {

        // Arrange (In setup)

        // Act
        user1.upVote(question);
    }

    // Assert
    @Test(expected = VotingException.class)
    public void userCannotDownvoteTheirOwnQuestion() throws Exception {

        // Arrange (In setup)

        // Act
        user1.downVote(question);
    }

    // DONE: Write a test to make sure that only the original questioner can accept an answer. Ensure the intended messaging is being sent to back to the caller.

    @Test
    public void throwsExceptionIfAnswerAcceptedBySomeoneOtherThanAsker() throws Exception {

        // Assert
        thrown.expect(AnswerAcceptanceException.class);
        thrown.expectMessage("Only Bill Yeats can accept this answer as it is their question");

        // Arrange (In setup)

        // Act
        user2.acceptAnswer(answer);
    }

    // DONE: Reviewing the User.getReputation method may expose some code that is not requested to be tested in the Meets Project instructions. Write the missing test.

    @Test
    public void userStartsWithZeroReputation() throws Exception {

        // Assert
        assertEquals(0, user1.getReputation());

    }

    @Test
    public void downVoteDecreasesAnswerersReputationByOnePoint() throws Exception {

        // Act
        user1.downVote(answer);

        // Assert
        assertEquals(-1, user2.getReputation());
    }

    @Test
    public void downVotingQuestionHasNoEffectOnQuestionAskersReputation() throws Exception {

        // Act
        user2.downVote(question);

        // Assert
        assertEquals(0, user1.getReputation());
    }
}