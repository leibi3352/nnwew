package com.example.test1.controller;

import com.example.test1.Service.UserNewsService;
import com.example.test1.Service.UserService;
import com.example.test1.entity.UserNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/UserNews")
public class UserNewsController {
    @Autowired
    private UserNewsService userNewsService;

    @GetMapping("/getUserNews")
    @ResponseBody
    public UserNews getUserNews(@RequestParam long Uid){
        String StNid= userNewsService.findByUid(Uid).getNid();
        String[] parts =StNid.split(",");

        return userNewsService.findByUid(Uid);
    }

    @GetMapping("/titles")
    @ResponseBody
    public List<String> getNewsTitlesByUid(@RequestParam int Uid) {
        return userNewsService.getNewsTitlesByUid(Uid);
    }
}
