package com.example.test1.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test1.Service.VideoService;
import com.example.test1.entity.Video;
import com.example.test1.mapper.VideoMapper;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
}
