package com.github.wgx731.xos.service.engine;

import com.github.wgx731.xos.exception.EngineException;
import com.github.wgx731.xos.service.room.TwoPlayerRoom;
import com.github.wgx731.xos.service.room.Room;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.assertj.core.api.Assertions.*;

public class TwoPlayerTicTacToeEngineTest {

    private static final String INIT_BOARD  = "\n 1 | 2 | 3 \n" +
                                              "-----------\n" +
                                              " 4 | 5 | 6 \n" +
                                              "-----------\n" +
                                              " 7 | 8 | 9 \n" +
                                              "-----------\n";

    private static final String X_WIN_BOARD = "\n x | o | 3 \n" +
                                              "-----------\n" +
                                              " o | x | 6 \n" +
                                              "-----------\n" +
                                              " 7 | 8 | x \n" +
                                              "-----------\n";

    private static final String O_WIN_BOARD = "\n x | x | o \n" +
                                              "-----------\n" +
                                              " x | o | 6 \n" +
                                              "-----------\n" +
                                              " o | 8 | 9 \n" +
                                              "-----------\n";

    private static final String DRAW_BOARD  = "\n x | o | x \n" +
                                              "-----------\n" +
                                              " o | x | x \n" +
                                              "-----------\n" +
                                              " o | x | o \n" +
                                              "-----------\n";

    private static final String PLAYER_ONE  = "Eric";
    private static final String PLAYER_TWO  = "Json";

    private Engine testEngine;
    private Room room;

    @Before
    public void setUp() {
        room = new TwoPlayerRoom();
        room.addPlayer(PLAYER_ONE);
        room.addPlayer(PLAYER_TWO);
        testEngine = new TwoPlayerTicTacToeEngine(room);
    }

    @After
    public void tearDown() {
        testEngine = null;
        room = null;
    }

    @Test
    public void testGetGameRoom() {
        Room testRoom = testEngine.getGameRoom();
        assertThat(testRoom).isNotNull();
        assertThat(testRoom).isEqualTo(room);
    }

    @Test
    public void testGetCurrentMove() {
        char currentMove = testEngine.getCurrentMove();
        assertThat(currentMove).isEqualTo(TwoPlayerTicTacToeEngine.X_MOVE);
        testEngine.placeMove(1);
        currentMove = testEngine.getCurrentMove();
        assertThat(currentMove).isEqualTo(TwoPlayerTicTacToeEngine.O_MOVE);
        testEngine.placeMove(2);
        currentMove = testEngine.getCurrentMove();
        assertThat(currentMove).isEqualTo(TwoPlayerTicTacToeEngine.X_MOVE);
    }

    @Test
    public void testReset() {
        testEngine.placeMove(1);
        String board = testEngine.getBoard();
        assertThat(board).isNotEqualTo(INIT_BOARD);
        testEngine.reset(3);
        board = testEngine.getBoard();
        assertThat(board).isEqualTo(INIT_BOARD);
    }

    @Test
    public void testGetBoard() {
        String board = testEngine.getBoard();
        assertThat(board).isEqualTo(INIT_BOARD);
    }

    @Test(expected = EngineException.class)
    public void testUpLeft() {
        testEngine.placeMove(0);
        testEngine.placeMove(1);
        testEngine.placeMove(4);
        testEngine.placeMove(3);
        testEngine.placeMove(8);
        String winner = testEngine.getWinner();
        assertThat(winner).containsOnlyOnce(PLAYER_ONE);
        assertThat(winner).doesNotContain(PLAYER_TWO);
        assertThat(testEngine.getBoard()).isEqualTo(X_WIN_BOARD);
        testEngine.placeMove(7);
    }

    @Test(expected = EngineException.class)
    public void testUpRight() {
        testEngine.placeMove(0);
        testEngine.placeMove(2);
        testEngine.placeMove(1);
        testEngine.placeMove(4);
        testEngine.placeMove(3);
        testEngine.placeMove(6);
        String winner = testEngine.getWinner();
        assertThat(winner).containsOnlyOnce(PLAYER_TWO);
        assertThat(winner).doesNotContain(PLAYER_ONE);
        assertThat(testEngine.getBoard()).isEqualTo(O_WIN_BOARD);
        testEngine.placeMove(7);
    }

    @Test(expected = EngineException.class)
    public void testDraw() {
        testEngine.placeMove(0);
        testEngine.placeMove(1);
        testEngine.placeMove(2);
        testEngine.placeMove(3);
        testEngine.placeMove(4);
        testEngine.placeMove(6);
        testEngine.placeMove(5);
        testEngine.placeMove(8);
        testEngine.placeMove(7);
        String winner = testEngine.getWinner();
        assertThat(winner).doesNotContain(PLAYER_TWO);
        assertThat(winner).doesNotContain(PLAYER_ONE);
        assertThat(testEngine.getBoard()).isEqualTo(DRAW_BOARD);
        testEngine.placeMove(7);
    }

    @Test
    public void testLeftWin() {
        testEngine.placeMove(0);
        testEngine.placeMove(3);
        testEngine.placeMove(1);
        testEngine.placeMove(4);
        testEngine.placeMove(2);
        String winner = testEngine.getWinner();
        assertThat(winner).containsOnlyOnce(PLAYER_ONE);
        assertThat(winner).doesNotContain(PLAYER_TWO);
    }

    @Test
    public void testRightWin() {
        testEngine.placeMove(2);
        testEngine.placeMove(3);
        testEngine.placeMove(1);
        testEngine.placeMove(4);
        testEngine.placeMove(0);
        String winner = testEngine.getWinner();
        assertThat(winner).containsOnlyOnce(PLAYER_ONE);
        assertThat(winner).doesNotContain(PLAYER_TWO);
    }

    @Test
    public void testUpWin() {
        testEngine.placeMove(0);
        testEngine.placeMove(1);
        testEngine.placeMove(2);
        testEngine.placeMove(4);
        testEngine.placeMove(8);
        testEngine.placeMove(7);
        String winner = testEngine.getWinner();
        assertThat(winner).containsOnlyOnce(PLAYER_TWO);
        assertThat(winner).doesNotContain(PLAYER_ONE);
    }

    @Test
    public void testDownWin() {
        testEngine.placeMove(0);
        testEngine.placeMove(7);
        testEngine.placeMove(2);
        testEngine.placeMove(4);
        testEngine.placeMove(8);
        testEngine.placeMove(1);
        String winner = testEngine.getWinner();
        assertThat(winner).containsOnlyOnce(PLAYER_TWO);
        assertThat(winner).doesNotContain(PLAYER_ONE);
    }

    @Test
    public void testDownLeftWin() {
        testEngine.placeMove(4);
        testEngine.placeMove(3);
        testEngine.placeMove(6);
        testEngine.placeMove(1);
        testEngine.placeMove(2);
        String winner = testEngine.getWinner();
        assertThat(winner).containsOnlyOnce(PLAYER_ONE);
        assertThat(winner).doesNotContain(PLAYER_TWO);
    }

    @Test
    public void testDownRightWin() {
        testEngine.placeMove(8);
        testEngine.placeMove(3);
        testEngine.placeMove(4);
        testEngine.placeMove(1);
        testEngine.placeMove(0);
        String winner = testEngine.getWinner();
        assertThat(winner).containsOnlyOnce(PLAYER_ONE);
        assertThat(winner).doesNotContain(PLAYER_TWO);
    }

    @Test(expected = EngineException.class)
    public void testNegativeMove() {
        testEngine.placeMove(-1);
    }

    @Test(expected = EngineException.class)
    public void testMoveBiggerThanBoard() {
        testEngine.placeMove(9);
    }

    @Test(expected = EngineException.class)
    public void testGetWinnerWhenGameRunning() {
        testEngine.getWinner();
    }

    @Test(expected = EngineException.class)
    public void testPlaceMoveOnExisting() {
        testEngine.placeMove(1);
        testEngine.placeMove(1);
    }

}
