package com.example.test1.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test1.Service.NewsService;
import com.example.test1.entity.News;
import com.example.test1.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public void updateClicks(Long nid) {
        newsMapper.updateClicks(nid);
    }

    public List<News> getNewsByIndustry(String industry) {
        return newsMapper.selectList(new QueryWrapper<News>().eq("Industry", industry));
    }

    public List<News> searchNews(String keyword) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", keyword).or().like("content", keyword);
        return newsMapper.selectList(queryWrapper);
    }

}
