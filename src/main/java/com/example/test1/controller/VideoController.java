package com.example.test1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.test1.Service.VideoService;
import com.example.test1.entity.News;
import com.example.test1.entity.Video;
import com.example.test1.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoService videoService;

    @GetMapping("/video")
    public List<Video> getAllVideo(){
        return videoMapper.selectList(null);
    }

    @GetMapping("/video/{vid}")
    public List<Video> getAllVideo(@PathVariable("vid") String vid){
        return videoMapper.selectList(null);
    }


    //视频投稿
    @GetMapping("/SavaVideo")
    @ResponseBody
    public void SavaVideo(@RequestParam("vurl") String vurl , @RequestParam("Author") String author ,
                          @RequestParam("title") String title,@RequestParam("Industry")String industry){
        Video video =new Video();
        ZonedDateTime now = ZonedDateTime.now();
        String nowString = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        video.setTitle(title);
        video.setUrl(vurl);
        video.setAuthor(author);
        video.setIndustry(industry);
        video.setDate(nowString);
        videoService.save(video);
    }


    //投稿列表
    @GetMapping("Contribution")
    @ResponseBody
    public List<Video> getContribution(@RequestParam("Username") String Username) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Author",Username);
        return videoService.list(queryWrapper);
    }

    @GetMapping("/deleteVideoById")
    @ResponseBody
    public void deleteVideoById(@RequestParam Long Vid) {
        System.out.println(Vid);
        videoMapper.deleteById(Vid);
    }


    @PostMapping("/updateVideo")
    public void updateVideo( @RequestBody Video video) {
        videoMapper.updateById(video);
    }
}
