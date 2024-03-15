package com.example.test1.util;
import com.google.gson.Gson;

import java.util.Arrays;

public class JsonToArray {
    public static void main(String[] args) {
        String jsonArray = "[\"item1\", \"item2\", \"item3\"]";

        Gson gson = new Gson();
        String[] array = gson.fromJson(jsonArray, String[].class);

        System.out.println(Arrays.toString(array));
    }
}
