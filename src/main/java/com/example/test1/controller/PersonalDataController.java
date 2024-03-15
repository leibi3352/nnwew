package com.example.test1.controller;

import com.example.test1.Service.NewsService;
import com.example.test1.entity.News;
import com.example.test1.entity.UserNews;
import com.example.test1.mapper.NewsMapper;
import com.example.test1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/PersonalData")
public class PersonalDataController {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private UserMapper userMapper;



    @GetMapping("/getData")
    @ResponseBody
    public Map<String, Integer> getPersonalData(@RequestParam("Uid") String Uid,@RequestParam("Username") String Username){
        Map<String, Integer> resultMap = new HashMap<>();
        Integer totalClicks = newsMapper.getTotalClicksForAs(Username);
        Integer fansnumber = newsMapper.getfansnumber(Uid);
        Integer Attention = userMapper.getAttentionnumber(Uid);
        Integer Comment = userMapper.getTotalCommentForAs(Username);
        Integer Ulike = userMapper.getTotalUlikeForAs(Uid);

        resultMap.put("totalClicks", totalClicks);
        resultMap.put("fansnumber", fansnumber);
        resultMap.put("Attention", Attention);
        resultMap.put("Comment",Comment);
        resultMap.put("Ulike", Ulike);

        return resultMap;
    }

}
