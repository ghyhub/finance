package com.ocrown;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeOperator {
    public static void main(String args[]) throws ParseException {

    }

    public static String timetamp2time(long timetamp)  {
        Date date = new Date(timetamp);
        SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return ss.format(date);
    }
}
