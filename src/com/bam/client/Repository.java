package com.bam.client;

import com.bam.MessagePattern;
import com.bam.MessageType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Repository {

    private final ClientSocket __clientSocket;

    public Repository(ClientSocket clientSocket) {
        __clientSocket = clientSocket;
    }

    public int load(String name, FileReader fileReader) throws IOException {
        StringBuilder _builder = new StringBuilder();
        BufferedReader _bufferedReader = new BufferedReader(fileReader);
        String _line;
        while ((_line = _bufferedReader.readLine()) != null)
        {
            _builder.append(_line).append(System.lineSeparator());
        }


        String _answer = __clientSocket.sendMessage(MessagePattern.generate(MessageType.LOAD, name + System.lineSeparator() +_builder));

        String[] _strings = parseAnswer(_answer);

        if (_strings != null)
        {
            return Integer.parseInt(_strings[1]);
        }

        return -1;
    }

    public int[] getForEvaluation() throws IOException {
        int[] _result = new int[3];
        String _answer = __clientSocket.sendMessage(MessagePattern.generate(MessageType.GET_FOR_EVA, "Запрос на получение трех работ"));

        String[] _parseAnswer = parseAnswer(_answer);

        if (_parseAnswer != null)
        {
            for (int i = 0; i < _result.length; i++) {
                _result[i] = Integer.parseInt(_parseAnswer[i+1]);
            }
        }

        return _result;
    }


    public ByteArrayInputStream getById(int id) throws IOException {
        ByteArrayInputStream _inputStream = null;
        String _answer = __clientSocket.sendMessage(MessagePattern.generate(MessageType.GET_BY_ID, String.valueOf(id)));
        String[] _parseAnswer = parseAnswer(_answer);

        if (_parseAnswer != null) _inputStream = new ByteArrayInputStream(_parseAnswer[0].getBytes(StandardCharsets.UTF_8));

        return _inputStream;
    }

    public void setMark(int id, String name, int mark) throws IOException {
        __clientSocket.sendMessage(MessagePattern.generate(MessageType.SET_MARK,
                id + System.lineSeparator() + name + System.lineSeparator() + mark));
    }


    public double getMarkAverage(int id) throws IOException {
        double _result = -1;
        String _answer = __clientSocket.sendMessage(MessagePattern.generate(MessageType.AVERAGE, String.valueOf(id)));
        String[] _parseAnswer = parseAnswer(_answer);

        if (_parseAnswer != null) _result = Double.parseDouble(_parseAnswer[1]);

        return _result;
    }

    public Map<String, Integer> getAllHomeWork() throws IOException {
        Map<String, Integer> _result = new HashMap<>();
        String _answer = __clientSocket.sendMessage(MessagePattern.generate(MessageType.ALL, "get All"));
        String[] _parseAnswer = parseAnswer(_answer);
        if (_parseAnswer != null)
        {
            _result = parseToMap(_parseAnswer);
        }
        return  _result;
    }


    public Map<String, Integer> getMarksForWork(int id) throws IOException {
        Map<String, Integer> _result = new HashMap<>();
        String _answer = __clientSocket.sendMessage(MessagePattern.generate(MessageType.GET_MARKS, String.valueOf(id)));
        String[] _parseAnswer = parseAnswer(_answer);
        if (_parseAnswer != null)
        {
            _result = parseToMap(_parseAnswer);
        }
        return _result;
    }

    private String[] parseAnswer(String msg)
    {
        String[] _result = msg.split(System.lineSeparator());
        MessageType _type = MessageType.valueOf(_result[0]);

        if (_type == MessageType.SUCCESS)
        {
            return _result;
        }

        return null;
    }

    private Map<String, Integer> parseToMap(String[] msg)
    {
        Map<String, Integer> _result = new HashMap<>();

        for (int i =1; i < msg.length; i++) {
            String[] _parseMap = msg[i].split(":");
            _result.put(_parseMap[0], Integer.parseInt( _parseMap[1]));
        }

        return _result;
    }






}
