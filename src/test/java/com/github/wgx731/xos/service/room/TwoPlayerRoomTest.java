package com.github.wgx731.xos.service.room;

import com.github.wgx731.xos.exception.RoomException;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.assertj.core.api.Assertions.*;

public class TwoPlayerRoomTest {

    private static final String P1 = "player1";
    private static final String P2 = "player2";
    private static final String P3 = "player3";
    private Room testRoom;

    @Before
    public void setUp() {
        testRoom = new TwoPlayerRoom();
    }

    @After
    public void tearDown() {
        testRoom = null;
    }

    @Test(expected = RoomException.class)
    public void testAddPlayer() throws RoomException {

        String returnString = testRoom.addPlayer(P1);
        assertThat(returnString).isEqualTo(P1);
        returnString = testRoom.addPlayer(P2);
        assertThat(returnString).isEqualTo(P2);
        returnString = testRoom.addPlayer(P3);
    }

    @Test(expected = RoomException.class)
    public void testGetNextPlayerEmptyRoom() throws RoomException {
        testRoom.getNextPlayer();
    }

    @Test
    public void testGetNextPlayerFullRoom() throws RoomException {
        testRoom.addPlayer(P1);
        testRoom.addPlayer(P2);
        String returnString = testRoom.getNextPlayer();
        assertThat(returnString).isEqualTo(P1);
        returnString = testRoom.getNextPlayer();
        assertThat(returnString).isEqualTo(P2);
        returnString = testRoom.getNextPlayer();
        assertThat(returnString).isEqualTo(P1);
    }

    @Test
    public void testGetNumOfPlayers() throws RoomException {
        int returnNum = testRoom.getNumOfPlayers();
        assertThat(returnNum).isEqualTo(0);
        testRoom.addPlayer(P1);
        returnNum = testRoom.getNumOfPlayers();
        assertThat(returnNum).isEqualTo(1);
        testRoom.addPlayer(P2);
        returnNum = testRoom.getNumOfPlayers();
        assertThat(returnNum).isEqualTo(2);
    }

}
