package com.example.test1.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test1.Service.UserNewsService;
import com.example.test1.entity.User;
import com.example.test1.entity.UserNews;
import com.example.test1.mapper.UserNewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserNewsServiceImpl extends ServiceImpl<UserNewsMapper, UserNews> implements UserNewsService {
    @Override
    public UserNews findByUid(long Uid) {
        return getBaseMapper().selectOne(new QueryWrapper<UserNews>().eq("Uid", Uid));
    }

    @Autowired
    private UserNewsMapper userNewsMapper;

    public List<String> getNewsTitlesByUid(int uid) {
        return userNewsMapper.selectNewsTitlesByUid(uid);
    }
}
