package com.example.test1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@TableName("news")
@Document(indexName = "news")
public class News {
    @TableId(value = "Nid", type = IdType.AUTO)
    private Long Nid;
    private String Title;
    private String Url;
    private String content;
    private Date date;
    private String Author;
    private String newsname;
    private String versionNumber;
    private int clicks;
    private String Nurl;
    private String Industry;
    private String Abstract;
    private String Comment;
    private String CommentId;
    private int Weight;

}
