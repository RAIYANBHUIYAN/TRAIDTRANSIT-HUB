//package com.example.guiapp;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class prac {
//
//    public static void main(String [] args)
//    {
//        Scanner input = new Scanner(System.in);
//        System.out.println("enter spot :");
//        String spot = input.nextLine();
//        System.out.println("enter time");
//        int time = input.nextInt();
//        System.out.println("enter price");
//        int pr = input.nextInt();
//        System.out.println("enter ev feature");
//        input.nextLine();
//        String ev =input.nextLine();
//        System.out.println("enter ev price");
//        int evp =input.nextInt();
//        try (Connection con = DB.getConnection();
//             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO login.parking(spot,tim,price,ev,evp) VALUES (?, ?, ?,?,?)")) {
//            preparedStatement.setString(1, spot);
//            preparedStatement.setInt(2, time);
//            preparedStatement.setInt(3, pr);
//            preparedStatement.setString(4,ev);
//            preparedStatement.setInt(5,evp);
//
//
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("data entry done");
//            } else {
//                System.out.println("!");
//
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//
//    }
//}
