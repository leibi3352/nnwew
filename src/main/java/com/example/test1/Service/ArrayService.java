package com.example.test1.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.test1.entity.Fans;
import com.example.test1.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ArrayService extends IService<Fans> {
    public void saveArray(Fans fans);
    public List<Fans> getArray();
    public int insertFansNameById(Long id, String fansName);

}
