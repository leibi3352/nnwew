package com.example.test1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test1.entity.UserNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserNewsMapper extends BaseMapper<UserNews> {
    @Select("SELECT Title FROM news WHERE FIND_IN_SET(Nid, (SELECT GROUP_CONCAT(Nid ORDER BY Nid SEPARATOR ',') FROM user_news WHERE Uid = #{uid}))")
    List<String> selectNewsTitlesByUid(int uid);
}

