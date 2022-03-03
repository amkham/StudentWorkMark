package com.bam.client;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public interface IClientContract {

    interface Student{

        //Загрузить работу на сервер
        int loadWork(String name, FileReader fileReader) throws IOException;

        //Получить три работы на оценку
        int[] getForElevation() throws IOException;

        /**
         * Получить работу по id
         * @param id
         * @return
         * @throws IOException
         */
        ByteArrayInputStream getWorkById(int id) throws IOException;

        /**
         * Оценить чужую работу
         * @param id
         * @param name
         * @param mark
         * @throws IOException
         */
        void setMark(int id, String name, int mark) throws IOException;

        /**
         * Получить среднее арифмитичекое
         * @param id
         * @return
         * @throws IOException
         */
        double getAverage(int id) throws IOException;
    }

    interface Teacher{
        /**
         * Получить все работы
         * @return
         * @throws IOException
         */
        Map<String, Integer> getAllWorks() throws IOException;

        /**
         * Получить работу по id
         * @param id
         * @return
         * @throws IOException
         */
        ByteArrayInputStream getWorkById(int id) throws IOException;

        /**
         * Получить оценки для работы по id
         * @param id
         * @return
         * @throws IOException
         */
        Map<String, Integer> getMarksForWork(int id) throws IOException;

        /**
         * Получить среднюю оценку для работы
         * @param id
         * @return
         * @throws IOException
         */
        double getAverageForWork(int id) throws IOException;

    }
}
