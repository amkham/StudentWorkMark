package com.bam.client;

import java.io.*;
import java.net.Socket;

public class ClientSocket {

    private final Socket __socket;

    public ClientSocket(Socket socket) {
        __socket = socket;
    }

    public String sendMessage(String msg) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(__socket.getInputStream())); // поток чтения из сокета
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(__socket.getOutputStream())); // поток записи в сокет

        out.write(msg);
        out.flush();

        String _line;
        StringBuilder _builder = new StringBuilder();
        while ((_line = in.readLine()) != null)
        {
            if (_line.equals("$$$$$$$")) break;
            _builder.append(_line).append(System.lineSeparator());
        }

        return _builder.toString();
    }


}
