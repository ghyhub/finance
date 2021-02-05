package com.ocrown;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

import com.ocrown.chatmsg.ChatMsg;
import com.ocrown.exception.GetChatDataFailException;
import com.ocrown.exception.GetChatDateErrorException;
import com.ocrown.exception.SdkInitFailException;

public class Main {
    public static void main(String[] args) {

        Properties sdkprops = new Properties();
        try {
            sdkprops.load(new FileInputStream("src/main/resources/sdk.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties jdbcprops = new Properties();
        try {
            jdbcprops.load(new FileInputStream("src/main/resources/jdbc.properties"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        DataFetcher df;
        try {
            // System.out.println(sdkprops.getProperty("corpid"));
            // System.out.println(sdkprops.getProperty("secret"));
            df = new DataFetcher(sdkprops.getProperty("corpid"), sdkprops.getProperty("secret"),
                    sdkprops.getProperty("private_key"));
        } catch (SdkInitFailException e) {
            e.printStackTrace();
            return;
        }
        Vector<String> chatdatal3s = new Vector<>();
        try {
            chatdatal3s = df.fetchChatData();
        } catch (GetChatDataFailException e) {
            e.printStackTrace();
            return;
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GetChatDateErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String chatdatal3 : chatdatal3s) {
            System.out.println(chatdatal3);
        }

        Properties fileprops = new Properties();
        try {
            fileprops.load(new BufferedReader(
                    new InputStreamReader(new FileInputStream("src/main/resources/file.properties"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileStorer fs = new FileStorer(df, fileprops.getProperty("storefile"));

        DataBase db = null;
        try {
            db = new DataBase(jdbcprops.getProperty("driver"), jdbcprops.getProperty("url"),
                    jdbcprops.getProperty("username"), jdbcprops.getProperty("password"));
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (String chatdatal3 : chatdatal3s) {
            System.out.println(chatdatal3);
            ChatMsg cm=null;
            try {
                cm=ChatMsg.msgFactory(chatdatal3, fs);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            try {
                cm.saveMsg(db, jdbcprops.getProperty("table"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        /*
         * Properties dataprops = new Properties();
         * 
         * try { dataprops.load(new BufferedReader(new InputStreamReader(new
         * FileInputStream("src/main/resources/datal3.properties")))); } catch
         * (IOException e) { e.printStackTrace(); }
         * 
         * ChatMsg cmExample = null; try { cmExample =
         * ChatMsg.msgFactory(dataprops.getProperty("datal3example3"),fs); } catch
         * (ClassNotFoundException | NoSuchMethodException | SecurityException |
         * IllegalAccessException | IllegalArgumentException | InvocationTargetException
         * e) { // TODO Auto-generated catch block e.printStackTrace(); }
         * 
         * try { cmExample.saveMsg(db, jdbcprops.getProperty("table")); } catch
         * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
         */
    }
}
