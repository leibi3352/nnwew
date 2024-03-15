package com.example.test1.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.test1.entity.News;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public interface NewsService extends IService<News> {
    void updateClicks(Long nid) ;

    List<News> getNewsByIndustry(String industry);

    List<News> searchNews(String keyword);
}
