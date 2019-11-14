package com.example.smasheditor;

import android.util.Log;

public class Debug {

    public static void debug(String text) {
        Log.println(Log.INFO, "debug", text);
    }
}
