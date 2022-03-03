package com.bam.run;

import com.bam.client.ClientSocket;
import com.bam.client.Repository;
import com.bam.client.StudentService;
import com.bam.client.TeacherService;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ClientRun {
    static  StudentService _studentService;
    static TeacherService _teacherService;

    public static void main(String[] args) throws IOException {
        Socket _socket = new Socket("localhost", 4005);
        ClientSocket _clientSocket = new ClientSocket(_socket);
        Repository _repository = new Repository(_clientSocket);

         _studentService = new StudentService(_repository);

         _teacherService = new TeacherService(_repository);

         // Загрузка работы из файла. Изменить путь по назначению
        System.out.println("Работа загружена id = " + _studentService.loadWork("TestStudent", new FileReader("D:/file.txt")));

         //Устанавливаем оценки для работы с id = 1
        _studentService.setMark(1, "Student5", 4);
        _studentService.setMark(1, "Student4", 3);
        _studentService.setMark(1, "Student3", 5);

        // Получаем среднее арифметическое
        System.out.println("Средняя = " + _studentService.getAverage(1));

        //Получаем id трех работ для проверки
        System.out.println(Arrays.toString(_studentService.getForElevation()));

        //Получение работы по id
        System.out.println(_studentService.getWorkById(5));


        // Получение всех работ
        System.out.println(_teacherService.getAllWorks());

    }


}
