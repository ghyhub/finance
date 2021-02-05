package com.ocrown;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class DataBase {
    Connection conn = null;

    public static void main(String[] args) {
        DataBase db = null;
        Properties jdbcprops = new Properties();

        try {
            jdbcprops.load(new FileInputStream("src/main/resources/jdbc.properties"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            db = new DataBase(jdbcprops.getProperty("driver"),
                    jdbcprops.getProperty("url"), jdbcprops.getProperty("username"),
                    jdbcprops.getProperty("password"));
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            db.sendData("create table A(B int,C int)");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    DataBase(String drive, String url, String username, String password) throws ClassNotFoundException, SQLException {
        conn=DriverManager.getConnection(url, username, password);
    }
    public void sendData(String sql) throws SQLException {
        Statement st=conn.createStatement();
        System.out.println(sql);
        st.executeUpdate(sql);
        st.close();
    }

    public String generateInsertStatement(String table,Map<String,String>map,String primarykey){
        return generateInsertStatement(table, map);
    }
    public String generateInsertStatement(String table,Map<String,String>map){
        String ret="REPLACE INTO "+table+" (";
        Set<String>keys=map.keySet();
        String tmp0="`";
        String tmp1="'";
        for(Iterator<String>i=keys.iterator();i.hasNext();){
            String key=i.next();
            tmp0=tmp0+key;
            tmp1=tmp1+StringOperator.sqlCharEscape(map.get(key));
            if(i.hasNext()){
                tmp1=tmp1+"','";
                tmp0=tmp0+"`,`";
            }
            else{
                tmp1=tmp1+"')";
                tmp0=tmp0+"`) VALUE (";
            }
        }
        ret=ret+tmp0+tmp1;
        return ret;
    }

    @Override
    protected void finalize() throws Throwable {
        if(conn!=null)
            conn.close();
        super.finalize();
    }
    
}
