package com.github.wgx731.xos.service.view;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConsoleViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private View testView;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);
        testView = null;
    }

    @Test
    public void testGetPlayer() {
        final String playerName = "player1";
        final String askPlayerName = "Player 1 Name:";
        System.setIn(new ByteArrayInputStream(playerName.getBytes()));
        testView = new ConsoleView(
            System.in,
            System.out,
            System.err
        );
        String returnString = testView.getPlayer(askPlayerName);
        assertThat(returnString).isEqualTo(playerName);
        assertThat(outContent.toString().trim()).isEqualTo(askPlayerName);
        assertThat(errContent.toString()).isEqualTo("");
    }

    @Test
    public void testGetMove() {
        final int move = 5;
        final String askMove = "Player 1 Move:";
        System.setIn(new ByteArrayInputStream(String.valueOf(move).getBytes()));
        testView = new ConsoleView(
            System.in,
            System.out,
            System.err
        );
        int returnInt = testView.getMove(askMove);
        assertThat(returnInt).isEqualTo(move);
        assertThat(outContent.toString().trim()).isEqualTo(askMove);
        assertThat(errContent.toString()).isEqualTo("");
    }

    @Test
    public void testInform() {
        final String informMessage = "test inform";
        testView = new ConsoleView(
            System.in,
            System.out,
            System.err
        );
        testView.inform(informMessage);
        assertThat(outContent.toString().trim()).isEqualTo(informMessage);
        assertThat(errContent.toString()).isEqualTo("");
    }

    @Test
    public void testError() {
        final String errorMessage = "test error";
        testView = new ConsoleView(
            System.in,
            System.out,
            System.err
        );
        testView.error(errorMessage);
        assertThat(errContent.toString().trim()).isEqualTo(errorMessage);
        assertThat(outContent.toString()).isEqualTo("");
    }

}
