package com.example.test1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {
    @TableId(value = "Uid", type = IdType.AUTO)
    private Long Uid;
    private String username;
    private Long password;
    private Long Account;
    private String Attention;
    private String Ucollection;
    private String Ulike;
    private Long Uweight;
    private String Uread;

}

