package com.mooc.sell.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

    public static void main(String[] args) throws Exception {

        Date dd = new Date();

        SimpleDateFormat ss = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String toDate = ss.format(new Date());
        ss.format(dd);
        Long to = ss.parse(toDate).getTime();
        long sss = dd.getTime();

        int days = (int) ((to - sss)/(1000 * 60 * 60 * 24));
        System.out.println(days);
    }
}
