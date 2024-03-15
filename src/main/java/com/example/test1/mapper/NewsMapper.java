package com.example.test1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test1.entity.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper extends BaseMapper<News> {
    @Update("UPDATE news SET clicks = clicks + 1 WHERE Nid = #{nid}")
    int updateClicks(@Param("nid") Long nid);

    List<News> selectByIndustry(@Param("industry") String industry);

    @Select("SELECT SUM(clicks) FROM news WHERE Author = #{Username}")
    Integer getTotalClicksForAs(@Param("Username") String Username);

    @Select("SELECT LENGTH(Fid) - LENGTH(REPLACE(Fid, ',', '')) + 1 FROM fans WHERE Uid= #{Uid};")
    Integer getfansnumber(@Param("Uid") String Uid);



}
