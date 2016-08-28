package com.github.wgx731.xos.service.view;

import java.util.Scanner;
import java.io.InputStream;
import java.io.PrintStream;

public class ConsoleView implements View {

    private InputStream in;
    private PrintStream out;
    private PrintStream err;
    private Scanner sc;

    public ConsoleView(InputStream in, PrintStream out, PrintStream err) {
        this.in = in;
        this.out = out;
        this.err = err;
        this.sc = new Scanner(in);
    }

    @Override
    public String getPlayer(String message) {
        out.println(message);
        return sc.nextLine();
    }

    @Override
    public int getMove(String message) {
        out.println(message);
        return sc.nextInt();
    }

    @Override
    public void inform(String message) {
        out.println(message);
    }

    @Override
    public void error(String message) {
        err.println(message);
    }

}
