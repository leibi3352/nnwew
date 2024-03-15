package com.example.test1.util;

import com.google.gson.Gson;

public class ArrayToJson {
    public static void main(String[] args) {
        String[] array = {"item1", "item2", "item3"};
        Gson gson = new Gson();

        String jsonArray = gson.toJson(array);

        System.out.println(jsonArray);
    }
}
