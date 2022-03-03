package com.bam.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

public class TeacherService implements IClientContract.Teacher {

    private final Repository __repository;

    public TeacherService(Repository repository) {
        __repository = repository;
    }


    @Override
    public Map<String, Integer> getAllWorks() throws IOException {
        return __repository.getAllHomeWork();
    }

    @Override
    public ByteArrayInputStream getWorkById(int id) throws IOException {
        return __repository.getById(id);
    }

    @Override
    public Map<String, Integer> getMarksForWork(int id) throws IOException {
        return __repository.getMarksForWork(id);
    }

    @Override
    public double getAverageForWork(int id) throws IOException {
        return __repository.getMarkAverage(id);
    }
}
