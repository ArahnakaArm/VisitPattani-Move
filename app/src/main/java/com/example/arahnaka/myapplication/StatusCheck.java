package com.example.arahnaka.myapplication;

public class StatusCheck
{
    private static boolean status =false;


    public static void login()
    {
        status = true; // Assigning a value;
    }
    public static void nologin(){
        status = false;
    }

    public static boolean getStatus()
    {
        return status;
    }
}
