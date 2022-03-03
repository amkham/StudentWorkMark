package com.bam.client;

import java.io.*;

public class StudentService implements IClientContract.Student{

    private Repository __repository;


    public StudentService(Repository repository) {
        __repository = repository;
    }

    @Override
    public int loadWork(String name, FileReader fileReader) throws IOException {
        return __repository.load(name, fileReader);
    }

    @Override
    public int[] getForElevation() throws IOException {
        return __repository.getForEvaluation();
    }
    @Override
    public ByteArrayInputStream getWorkById(int id) throws IOException {
        return __repository.getById(id);
    }
    @Override
    public void setMark(int id, String name, int mark ) throws IOException {
        __repository.setMark(id, name, mark);
    }
    @Override
    public double getAverage(int id) throws IOException {
        return __repository.getMarkAverage(id);
    }
}
