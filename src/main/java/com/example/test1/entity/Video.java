package com.example.test1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("video")
public class Video {
    @TableId(value = "Vid", type = IdType.AUTO)
    private Long Vid;
    private String Title;
    private String Url;
    private String Purl;
    private String date;
    private String Author;
    private String Industry;

}
