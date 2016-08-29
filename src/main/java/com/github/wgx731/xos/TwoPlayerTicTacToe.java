package com.github.wgx731.xos;

import com.github.wgx731.xos.service.room.Room;
import com.github.wgx731.xos.service.view.View;
import com.github.wgx731.xos.service.engine.Engine;
import com.github.wgx731.xos.service.engine.State;
import com.github.wgx731.xos.exception.EngineException;
import com.github.wgx731.xos.exception.RoomException;

import javax.inject.Inject;
import java.util.InputMismatchException;

public class TwoPlayerTicTacToe {

    private final static int BOARD_NUM = 5;

    private final Engine engine;
    private final View view;

    public final String PLAYER_NAME_TEMPLATE = "Enter name for Player %d:";
    public final String PLAYER_MOVE_TEMPLATE = "%s, choose a box to " +
                                                    "place an '%s' into:";

    @Inject
    public TwoPlayerTicTacToe(Engine engine, View view) {
        this.engine = engine;
        this.view = view;
    }

    public void run() {
        try {
            Room room = engine.getGameRoom();
            String playerOne = view.getPlayer(
                String.format(PLAYER_NAME_TEMPLATE, 1)
            );
            room.addPlayer(playerOne);
            String playerTwo = view.getPlayer(
                String.format(PLAYER_NAME_TEMPLATE, 2)
            );
            room.addPlayer(playerTwo);
            engine.reset(BOARD_NUM);
            view.inform(engine.getBoard());
            // NOTO: user input will be 1 based
            int move = 0;
            State currentState = State.RUNNING;
            while(currentState == State.RUNNING) {
                try {
                    move = view.getMove(
                            String.format(
                                PLAYER_MOVE_TEMPLATE,
                                room.getCurrentPlayer(),
                                engine.getCurrentMove()
                                )
                            );
                    currentState = engine.placeMove(move - 1);
                    view.inform(engine.getBoard());
                } catch (EngineException e) {
                    view.error(e.getMessage());
                    currentState = State.RUNNING;
                }
            }
            view.inform(engine.getWinner());
            System.exit(0);
        }
        catch (RoomException e) {
            view.error(e.getMessage());
            System.exit(1);
        }
    }

}
