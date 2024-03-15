package com.example.test1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test1.entity.Fans;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface ArrayMapper extends BaseMapper<Fans> {
    @Update("UPDATE fans SET Fname = #{Fname} WHERE id = #{id}")
    int insertFansNameById(@Param("id") Long id, @Param("Fname") String Fname);

}
