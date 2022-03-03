package com.bam;

public class MessagePattern {

    public static final String splitter = "--!--";
    public static final String map_splitter = ":";

    /**
     * Структура сообщения отправляемого на клиент
     * @param type
     * @param msg
     * @return
     */
    public static String generate(MessageType type, String msg) {

        return type.name() + System.lineSeparator() + msg + System.lineSeparator() + "$$$$$$$" + System.lineSeparator();
    }



}
