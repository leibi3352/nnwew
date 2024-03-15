package com.example.test1.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.test1.entity.UserNews;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserNewsService extends IService<UserNews> {
    public UserNews findByUid(long Uid);

    public List<String> getNewsTitlesByUid(int uid);
}
