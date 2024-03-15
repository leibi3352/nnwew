package com.example.test1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test1.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface  UserMapper extends BaseMapper<User> {
    @Select("SELECT LENGTH(Attention) - LENGTH(REPLACE(Attention, ',', '')) + 1 FROM user WHERE Uid=#{Uid};")
    Integer getAttentionnumber(@Param("Uid") String Uid);

    @Select("SELECT SUM( LENGTH(Comment) - LENGTH(REPLACE(Comment, ',', ''))+1 )  FROM news WHERE Author = #{Username} ")
    Integer getTotalCommentForAs(@Param("Username") String Username);

    @Select("SELECT LENGTH(Ulike) - LENGTH(REPLACE(Ulike, ',', ''))+1  FROM user WHERE Uid = #{Uid}")
    Integer getTotalUlikeForAs(@Param("Uid") String Uid);


}
