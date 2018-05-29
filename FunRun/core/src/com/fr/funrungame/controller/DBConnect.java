package com.fr.funrungame.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://den1.mysql2.gear.host:3306/lpooproject", "lpooproject", "Ou1o6Nvm!-P0");
            st = con.createStatement();

        }catch(Exception ex){
            System.out.println("Erro: " + ex);
        }
    }

    public void getData(){
        try{
            String query = "select * from highscores";
            rs = st.executeQuery(query);

            System.out.println("Records from database");
            while(rs.next()){
                String map = rs.getString("map");
                String movement = rs.getString("movement");
                String time = rs.getString("time");

                System.out.println("Map: " +map+", movement: " + movement + ", time: " + time);
            }
            st.executeUpdate("INSERT INTO highscores (map, movement, time) VALUES (169, 'teste_android', 20)");

        }catch(Exception ex){
            System.out.println("Erro: " + ex);
        }
    }
}
