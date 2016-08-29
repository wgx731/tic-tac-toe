package com.github.wgx731.xos.service.engine;

import com.github.wgx731.xos.service.room.Room;
import com.github.wgx731.xos.exception.EngineException;

import javax.inject.Inject;

public class TwoPlayerTicTacToeEngine implements Engine {

    static final char X_MOVE = 'x';
    static final char O_MOVE = 'o';
    static final char EMPTY = 'e';
    static final int DEFAULT_BOARD_NUM = 3;

    private final Room gameRoom;

    private int boardNum;
    private char[] board;
    private State gameState;
    private int total;
    private char currentMove = X_MOVE;

    @Inject
    public TwoPlayerTicTacToeEngine(Room gameRoom) {
        this.gameRoom = gameRoom;
        reset(DEFAULT_BOARD_NUM);
    }

    @Override
    public Room getGameRoom() {
        return gameRoom;
    }

    @Override
    public char getCurrentMove() {
        return currentMove;
    }

    @Override
    public void reset(int boardNum) {
        this.boardNum = boardNum;
        total = boardNum * boardNum;
        gameRoom.reset();
        gameState = State.RUNNING;
        currentMove = X_MOVE;
        board = new char[total];
        for(int i = 0; i < total; i++) {
            board[i] = EMPTY;
        }
    }

    @Override
    public String getBoard() {
        StringBuffer sb = new StringBuffer();
        sb.append(System.lineSeparator());
        int digit = String.valueOf(total).length();
        String template = String.format("%%0%dd", digit);
        for (int i = 0; i < total; i++) {
            // output current cell
            sb.append(" ");
            if (board[i] == EMPTY) {
                sb.append(String.format(template, i + 1));
            }
            else {
                sb.append(board[i]);
            }
            sb.append(" ");
            // output divider (column & row)
            if ((i + 1) % boardNum != 0) {
                sb.append("|");
            }
            else {
                sb.append(System.lineSeparator());
                for (int j = 0; j < (3 + digit) * boardNum - 1; j++) {
                    sb.append("-");
                }
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String getWinner() {
        String winnerTemplate = "Congratulations %s! You have won.";
        switch(gameState) {
            case END:
                return String.format(
                        winnerTemplate,
                        gameRoom.getCurrentPlayer()
                        );
            case DRAW:
                return "Draw game. No winner.";
            default:
                throw new EngineException("Game not finished yet.");
        }
    }

    @Override
    public State placeMove(int index) {
        if (gameState != State.RUNNING) {
            throw new EngineException("Game is finished.");
        }
        if (!isOnBoard(index)) {
            throw new EngineException(
                String.format(
                    "Invalid move %d, must be within [1-%d).",
                    index + 1,
                    total
                    )
                );
        }
        if (board[index] != EMPTY) {
            throw new EngineException(
                String.format(
                    "Invalid move %d, borad not empty.",
                    index + 1
                    )
                );
        }
        board[index] = currentMove;
        updateGameState(index);
        if (gameState == State.RUNNING) {
            updateCurrentMove();
            gameRoom.getNextPlayer();
        }
        return gameState;
    }

    private void updateCurrentMove() {
        currentMove = currentMove == X_MOVE ? O_MOVE : X_MOVE;
    }

    private void updateGameState(int index) {
        if (gameRoom.getRoundNum() >= total - 1) {
            gameState = State.DRAW;
            return;
        }
        int i, j, k;
        int row = ((index + 1) / boardNum);
        int col = ((index + 1) % boardNum);
        if (col == 0) {
            col = boardNum;
        }
        else {
            col--;
        }
        // check left
        if (col > 1) {
            i = index;
            j = i - 1;
            k = j - 1;
            if (isOnBoard(j) &&
                isOnBoard(k) &&
                isOneLine(i, j, k)) {
                gameState = State.END;
                return;
            }
        }
        // check right
        if (col + 1 < boardNum) {
            i = index;
            j = i + 1;
            k = j + 1;
            if (isOnBoard(j) &&
                isOnBoard(k) &&
                isOneLine(i, j, k)) {
                gameState = State.END;
                return;
            }
        }
        // check up
        if (row > 1) {
            i = index;
            j = i - boardNum;
            k = j - boardNum;
            if (isOnBoard(j) &&
                isOnBoard(k) &&
                isOneLine(i, j, k)) {
                gameState = State.END;
                return;
            }
        }
        // check down
        if (row + 1 < boardNum) {
            i = index;
            j = i + boardNum;
            k = j + boardNum;
            if (isOnBoard(j) &&
                isOnBoard(k) &&
                isOneLine(i, j, k)) {
                gameState = State.END;
                return;
            }
        }
        // check up left
        if (col > 1 && row > 1) {
            i = index;
            j = i - boardNum - 1;
            k = j - boardNum - 1;
            if (isOnBoard(j) &&
                isOnBoard(k) &&
                isOneLine(i, j, k)) {
                gameState = State.END;
                return;
            }
        }
        // check up right
        if (col + 1 < boardNum && row > 1) {
            i = index;
            j = i - boardNum + 1;
            k = j - boardNum + 1;
            if (isOnBoard(j) &&
                isOnBoard(k) &&
                isOneLine(i, j, k)) {
                gameState = State.END;
                return;
            }
        }
        // check down left
        if (col > 1 && row + 1 < boardNum) {
            i = index;
            j = i + boardNum - 1;
            k = j + boardNum - 1;
            if (isOnBoard(j) &&
                isOnBoard(k) &&
                isOneLine(i, j, k)) {
                gameState = State.END;
                return;
            }
        }
        // check down right
        if (col + 1 < boardNum && row + 1 < boardNum) {
            i = index;
            j = i + boardNum + 1;
            k = j + boardNum + 1;
            if (isOnBoard(j) &&
                isOnBoard(k) &&
                isOneLine(i, j, k)) {
                gameState = State.END;
                return;
            }
        }
    }

    private boolean isOnBoard(int index) {
        return index >= 0 && index < total;
    }

    private boolean isOneLine(int i, int j, int k) {
        return board[i] == board[j] &&
               board[j] == board[k] &&
               board[k] != EMPTY;
    }

}
