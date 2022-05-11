package com.user.homedeco.services;

import com.user.homedeco.exceptions.EmptyFieldException;
import com.user.homedeco.exceptions.IncorrectMailOrPassword;
import com.user.homedeco.exceptions.UsernameNotAvailable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;
import java.util.Iterator;

public class User {

    public static String name;
    public static String key = "Jar12345Jar12345";
    public static String initVector = "RandomInitVector";

    public static void addUserClient(String firstName, String lastName, String email, String password) throws EmptyFieldException, UsernameNotAvailable {

       checkIfFieldsAreEmptyClient(firstName,lastName,email,password);
        name=firstName+" "+lastName;
        JSONObject obj = new JSONObject();
        JSONArray arrayClient = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/usersClient.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Iterator<JSONObject> iterator = arrayClient.iterator();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if (obj2.get("Email:").equals(email))
            {
                throw new UsernameNotAvailable();
            }

        }
        JSONArray array= new JSONArray();
        obj.put("First Name:", firstName);
        obj.put("Last Name:", lastName);
        obj.put("Email:",email);
        obj.put("Password:", encodePassword(key,initVector,password));

        arrayClient.add(obj);
        try {
            File file = new File("src/main/resources/usersClient.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayClient.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void checkIfFieldsAreEmptyClient(String firstName, String lastName, String username, String password) throws EmptyFieldException {

        if (firstName.isEmpty() | lastName.isEmpty() | username.isEmpty() | password.isEmpty())
            throw new EmptyFieldException();
    }

    public static String encodePassword(String key, String initVector, String value)
    {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    static boolean correctAccount = false;
    public static String loginCheckClient(String email, String password)throws IncorrectMailOrPassword {

        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/resources/usersClient.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Iterator<JSONObject> iterator = arrayClient.iterator();


        while (iterator.hasNext())
        {
            JSONObject obj = iterator.next();
            if (obj.get("Email:").equals(email)&& obj.get("Password:").equals(encodePassword(key,initVector,password)))
            {
                correctAccount = true;
                name=(String)obj.get("First Name:")+" "+(String)obj.get("Last Name:");
                return "Client";
            }

        }

        return"";

    }

    public static void checkIncorrect()throws IncorrectMailOrPassword {
        if(!correctAccount)
        {
            throw new IncorrectMailOrPassword();
        }
    }


}

