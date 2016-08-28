package com.github.wgx731.xos.service.room;

import com.github.wgx731.xos.exception.RoomException;

public class TwoPlayerRoom implements Room {

    private final int MAX_NUM_OF_PLAYERS = 2;

    private String[] players = new String[MAX_NUM_OF_PLAYERS];
    private int numOfPlayers = 0;
    private int round = 0;

    @Override
    public String addPlayer(String player) {
        if (numOfPlayers >= MAX_NUM_OF_PLAYERS) {
            throw new RoomException(String.format(
                        "Reach %s players in the room.",
                        MAX_NUM_OF_PLAYERS));
        }
        players[numOfPlayers++] = player;
        return player;
    }

    @Override
    public String getNextPlayer() {
        if (numOfPlayers < MAX_NUM_OF_PLAYERS) {
            throw new RoomException(String.format(
                        "Need %d players in the room.",
                        MAX_NUM_OF_PLAYERS));
        }
        String nextPlayer = players[round % MAX_NUM_OF_PLAYERS];
        round++;
        return nextPlayer;
    }

    @Override
    public String getCurrentPlayer() {
        return players[round % MAX_NUM_OF_PLAYERS];
    }

    @Override
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

}
