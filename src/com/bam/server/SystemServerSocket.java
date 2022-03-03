package com.bam.server;

import com.bam.MessagePattern;
import com.bam.MessageType;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SystemServerSocket {

    private final ServerSocket __serverSocket;
    private final SampleDb __sampleDb;
    private BufferedReader in;
    private  BufferedWriter out;


    public SystemServerSocket(ServerSocket serverSocket, SampleDb sampleDb) {
        __serverSocket = serverSocket;
        __sampleDb = sampleDb;
    }

    public void startServer() throws IOException {


        Socket _socket = __serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(_socket.getInputStream())); // поток чтения из сокета
        out = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream())); // поток записи в сокет


        while (true)
        {
            List<String> _command = new ArrayList<>();

            String _line;
            while (!(_line = in.readLine()).equals(""))
            {
                if (_line.equals("$$$$$$$")) break;
               _command.add(_line);
            }

            if (_command.size() > 0)
            {
                String _answerToClient = parseCommand(_command);

                out.write(_answerToClient);
                out.flush();
            }

        }




    }

    private String parseCommand(List<String> command) {
        MessageType _type = MessageType.valueOf(command.get(0));
        switch (_type) {
            case LOAD -> {
                String _name = command.get(1);

                StringBuilder _builder = new StringBuilder();
                for (int i = 2; i <command.size(); i++) {
                    _builder.append(command.get(i));
                }
                String _text = _builder.toString();

                int id = __sampleDb.insert(_name, _text);
                return MessagePattern.generate(MessageType.SUCCESS, String.valueOf(id));

            }
            case GET_FOR_EVA -> {
                int[] _result = __sampleDb.getForExamination();

                return MessagePattern.generate(MessageType.SUCCESS,
                        _result[0] + System.lineSeparator() + _result[1] + System.lineSeparator() + _result[2]);
            }
            case GET_BY_ID -> {

                String _text = __sampleDb.getById(Integer.parseInt(command.get(1))).getText();
                return MessagePattern.generate(MessageType.SUCCESS, _text);
            }
            case SET_MARK -> {
                __sampleDb.setMark(Integer.parseInt(command.get(1)), command.get(2), Integer.parseInt(command.get(3)));
                return MessagePattern.generate(MessageType.SUCCESS, "");
            }
            case AVERAGE -> {
                int id = Integer.parseInt(command.get(1));
                double average = __sampleDb.getAverage(id);
                return MessagePattern.generate(MessageType.SUCCESS, String.valueOf(average));
            }
            case ALL -> {
                Map<String, Integer> _result = __sampleDb.getAll();
                return MessagePattern.generate(MessageType.SUCCESS, mapToString(_result));
            }
            case GET_MARKS -> {

                String _result = mapToString(__sampleDb.getById(Integer.parseInt(command.get(1))).getMarks());
                return MessagePattern.generate(MessageType.SUCCESS, _result);
            }
        }

        return MessagePattern.generate(MessageType.ERROR, "Неизвестная команда");

    }

    private String mapToString(Map<String,Integer> map)
    {
        StringBuilder _builder = new StringBuilder();
        for (Map.Entry<String, Integer> item: map.entrySet())
        {
            _builder.append(item.getKey())
                    .append(":")
                    .append(item.getValue())
                    .append(System.lineSeparator());
        }

        return _builder.toString();
    }




}
