package com.example.supplychain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {
    DatabaseConnection dbConn = new DatabaseConnection();

    public static byte[] getSHA(String input){
         try{
             MessageDigest md = MessageDigest.getInstance("SHA-256");
             return md.digest(input.getBytes(StandardCharsets.UTF_8));
         }catch(Exception e){
             e.printStackTrace();
         }
         return null;
    }

    static String getEncryptedPassword(String password){
        String encryptedPassword = "";
        BigInteger number = new BigInteger(1,getSHA(password));
        StringBuilder hexString = new StringBuilder(number.toString(16));  // 0 to 9 and A to F
        encryptedPassword = hexString.toString();
        return encryptedPassword;
    }

    public boolean customerLogin(String email, String password){

         try{
             String query = String.format("SELECT * FROM customer WHERE email = '%s' AND password = '%s' ", email, getEncryptedPassword(password));
             ResultSet rs = dbConn.getQueryTable(query);
             if(rs==null)  return false;
             if(rs.next()){
                 return true;
             }else{
                 return false;
             }
         }
         catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(getEncryptedPassword("@pass123"));
//    }

}
