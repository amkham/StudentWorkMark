package com.bam.run;

import com.bam.server.SampleDb;
import com.bam.server.SystemServerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRun {

    public static void main(String[] args) throws IOException {

        ServerSocket _serverSocket = new ServerSocket(4005);
        SampleDb _sampleDb = new SampleDb();
        SystemServerSocket _systemServerSocket = new SystemServerSocket(_serverSocket, _sampleDb);
        _systemServerSocket.startServer();
    }
}
