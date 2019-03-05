package com.ralphy.controller;

import com.ralphy.domain.Request;
import com.ralphy.domain.Shared;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by shrk on 04/03/19.
 */
public class ConcurrentServer implements Runnable
{
    int port;

    public ConcurrentServer(int port)
    {
        this.port = port;
    }

    public void run()
    {
        try{
            start(port);
        }
        catch(IOException c)
        {
            c.printStackTrace();
        }

    }

    public void start(int port) throws IOException
    {
        System.out.println("Started server!");
        while(true)
        {
            try(ServerSocket server = new ServerSocket(port))
            {
                Request request = new Request(server.accept());
                Shared.requests.add(request);
            }
        }
    }

    public void stream(String data) throws IOException
    {
        Request request;
        for(int i = 0 ; i < Shared.requests.size(); i++)
        {
            request = Shared.requests.get(i);
            request.say(data);
        }
    }
}
