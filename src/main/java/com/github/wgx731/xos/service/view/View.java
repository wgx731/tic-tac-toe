package com.github.wgx731.xos.service.view;

public interface View {

    public String getPlayer(String message);

    public int getMove(String message);

    public void inform(String message);

    public void error(String message);

}
