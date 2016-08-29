package com.github.wgx731.xos.service.engine;

import com.github.wgx731.xos.service.room.Room;

public interface Engine {

    public Room getGameRoom();

    public void reset(int boardNum);

    public String getBoard();

    public String getWinner();

    // NOTE: move is 0 based index
    public State placeMove(int move);

}
