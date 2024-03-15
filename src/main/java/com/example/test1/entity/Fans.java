package com.example.test1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("fans")
public class Fans {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long Uid;
    private String Fid;

}
