package com.github.wgx731.xos.service.room;

public interface Room {

    public String addPlayer(String player);

    public String getNextPlayer();

    public String getCurrentPlayer();

    public int getNumOfPlayers();

}
