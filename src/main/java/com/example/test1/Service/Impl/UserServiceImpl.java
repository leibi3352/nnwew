package com.example.test1.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test1.Service.UserService;
import com.example.test1.entity.User;
import com.example.test1.entity.UserNews;
import com.example.test1.mapper.UserMapper;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User findByAccount(long Account) {
        return getBaseMapper().selectOne(new QueryWrapper<User>().eq("Account", Account));
    }
    //Scav
    @Override
    public User findByAuthor(String Author) {
        return getBaseMapper().selectOne(new QueryWrapper<User>().eq("username", Author));
    }

}
