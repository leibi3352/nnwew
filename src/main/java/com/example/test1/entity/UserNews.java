package com.example.test1.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_news")
public class UserNews {
    private Long id;
    private String Nid;
    private Long Uid;
}
