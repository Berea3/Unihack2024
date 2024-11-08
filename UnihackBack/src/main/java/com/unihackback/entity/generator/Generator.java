package com.unihackback.entity.generator;

import java.time.LocalTime;
import java.util.Random;

public class Generator {

    public static String generateId()
    {
        StringBuilder s=new StringBuilder();
//        for (char i='a';i<='z';i++) s.append(i);
//        for (char i='A';i<='Z';i++) s.append(i);
//        for (char i='0';i<='9';i++) s.append(i);
//        s.append("!@#$%^&*");
        s.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*");

        LocalTime localTime=LocalTime.now();
        long seed=localTime.getSecond()+localTime.getMinute()*60+ localTime.getHour()*60*60;

        Random random=new Random();
        random.setSeed(seed);

        StringBuilder id=new StringBuilder();

        int x;
        for (int i=0;i<32;i++)
        {
            x=Math.abs(random.nextInt()%s.length());
            id.append(s.charAt(x));
        }

        return id.toString();
    }
}
