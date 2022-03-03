package com.bam.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeWork {

    private final int id;
    private final String name;
    private final String text;
    private final Map<String, Integer> __marks = new HashMap<>();

    public HomeWork(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public void addMark(String studentName, int mark)
    {
        __marks.put(studentName, mark);
    }

    public double getAverage()
    {
        List<Integer> _marksList = __marks.values().stream().toList();
        double sum = 0;
        for (int m: _marksList)
        {
            sum += m;
        }

        return sum / _marksList.size();
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getMarks() {
        return __marks;
    }

    @Override
    public String toString() {
        return "HomeWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", __marks=" + __marks +
                '}';
    }
}
