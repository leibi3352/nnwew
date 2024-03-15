package com.example.test1.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.test1.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends IService<User> {
    User findByAccount(long Account);
    public User findByAuthor(String Author);
}
