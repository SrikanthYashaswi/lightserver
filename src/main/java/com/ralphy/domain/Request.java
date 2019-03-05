package com.ralphy.domain;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by shrk on 04/03/19.
 */
public class Request {

    private Socket client;

    public Request(Socket socketConnection) throws IOException
    {
        this.client = socketConnection;
        doHandshake();
    }

    private void doHandshake() throws IOException
    {
        sendOk();
    }

    public void sendOk() throws IOException
    {
        say("HTTP/1.1 200");
        say("Connection: close");
    }

    public void say(String message) throws IOException {
        PrintStream o = new PrintStream(client.getOutputStream());
        o.println(message);
    }

}
