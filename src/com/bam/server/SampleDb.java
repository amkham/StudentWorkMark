package com.bam.server;

import java.util.*;

public class SampleDb {
    private final Map<Integer, HomeWork> __workMap = new HashMap<>();



    public SampleDb()
    {
        for (int i = 0; i < 10; i ++)
        {
            int id = generateId();
            __workMap.put(id, new HomeWork(id, "Student" + i, "HomeWork text" + 1));
        }
    }


    /**
     * Добавить работу
     * @param name имя выполнившего
     * @param text текст работы
     * @return
     */
    public int insert(String name, String text)
    {
        int id = generateId();
        __workMap.put(id, new HomeWork(id, name, text));
        return id;
    }


    /**
     * Получить работу по id
     * @param id
     * @return
     */
    public HomeWork getById(int id)
    {
        return __workMap.get(id);
    }

    /**
     * Получить все работы
     * @return
     */
    public Map<String, Integer> getAll()
    {
        Map<String, Integer> _result = new HashMap<>();
        for (Map.Entry<Integer, HomeWork> m : __workMap.entrySet())
        {
            _result.put(m.getValue().getName(), m.getValue().getId());
        }

        return _result;
    }


    /**
     * Получить три работы с минимальным колличеством оценок
     * @return
     */
    public int[] getForExamination()
    {
        List<HomeWork> _homeWorkList = __workMap.values().stream().toList();

        if (_homeWorkList.size() == 0) return  null;

        _homeWorkList.stream().sorted(Comparator.comparingInt(o -> o.getMarks().size()));
        int[] _result = new int[3];
        for (int i = 0; i < 3; i++) {
            _result[i] = _homeWorkList.get(i).getId();
        }
        return _result;
    }

    /**
     * Добавить оценку к работе
     * @param homeWorkId
     * @param name
     * @param mark
     */
    public void setMark(int homeWorkId, String name, int mark)
    {
        HomeWork _homeWork = __workMap.get(homeWorkId);
        _homeWork.addMark(name, mark);
        __workMap.put(homeWorkId, _homeWork);
    }

    /**
     * Получить среднее арифмитическое работы
     * @param id
     * @return
     */
    public double getAverage(int id)
    {
        List<HomeWork> _homeWorkList = __workMap.values().stream().toList();
        Optional<HomeWork> _homeWork = _homeWorkList.stream().filter(h -> h.getId() == id).findFirst();
        return _homeWork.map(HomeWork::getAverage).orElse(-1.0);
    }


    /**
     * Сгенерировать id
     * @return
     */
    private int generateId()
    {


        return __workMap.size() + 1;
    }

}
