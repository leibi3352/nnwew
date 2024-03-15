package com.example.test1.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test1.Service.ArrayService;
import com.example.test1.entity.Fans;
import com.example.test1.mapper.ArrayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrayServiceImpl extends ServiceImpl<ArrayMapper, Fans> implements ArrayService {
    @Autowired
    private ArrayMapper arrayMapper;


    public void saveArray(Fans fans) {
        arrayMapper.insert(fans);
    }

    public List<Fans> getArray() {
        return arrayMapper.selectList(null);
    }

    public int insertFansNameById(Long id, String fansName) {
        return getBaseMapper().insertFansNameById(id, fansName);
    }

}
