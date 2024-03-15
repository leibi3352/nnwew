package com.example.test1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.test1.Service.ArrayService;
import com.example.test1.entity.Fans;
import com.example.test1.mapper.ArrayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/arrays")
public class ArrayController {
    @Autowired
    private ArrayService arrayService;

    @Autowired
    private ArrayMapper arrayMapper;

//    //指定id添加数据
//    @PostMapping("/insertArray")
//    public void insertArray(@RequestBody Fans fans) {
////        return arrayService.save(array);
//        List<Fans> arr = arrayService.getArray();
//        for (Fans a : arr) {
//            if (a.getId() == fans.getId()) {
//                String jsonArray = a.getFname();
//                String[] parts = jsonArray.split(",");
//                ArrayList<String> stringList = new ArrayList<>(Arrays.asList(parts));
//                stringList.add(fans.getFname());
//                String b = String.join(",", stringList);
//                arrayService.insertFansNameById(fans.getId(), b);
//            }
//        }
//    }

    //关注功能
    @GetMapping("/attention")
    @ResponseBody
    public boolean attention(@RequestParam long uid, long fid, long modify) {
        List<Fans> arr = arrayService.getArray();
        QueryWrapper<Fans> queryWrapper = new QueryWrapper<>();
        boolean a1 = false;
        if (modify == 1 && uid != fid) {
            for (Fans a : arr) {
                if (a.getUid() == uid) {
                    int f = 0;
                    String jsonArray = a.getFid();
                    String[] parts = jsonArray.split(",");
                    int[] intArray = new int[parts.length];
                    for (int i = 0; i < parts.length; i++) {
                        intArray[i] = Integer.parseInt(parts[i]);
                    }
                    for (int b : intArray) {
                        if (fid == b) {
                            f = 1;
                        }
                    }
                    if (f != 1) {
                        jsonArray += "," + fid;
                        a.setFid(jsonArray);
                        queryWrapper.eq("Uid", uid);
                        arrayMapper.update(a, queryWrapper);
                        a1 = true;
                        return a1;

                    } else {
                        if (f == 1) {
                            int[] intArray2 = new int[parts.length - 1];
                            int index = 0;
                            for (int i : intArray) {
                                if (i != fid) {
                                    intArray2[index++] = i;
                                }
                            }
                            String arrStr = Arrays.toString(intArray2);
                            arrStr = arrStr.substring(1, arrStr.length() - 1).replace(" ", "");
                            ;
                            a.setFid(arrStr);
                            queryWrapper.eq("Uid", uid);
                            arrayMapper.update(a, queryWrapper);
                            a1 = false;
                            return a1;
                        }
                    }
                    return a1;
                }
            }
        }

        if (uid == fid) return true;
        return a1;
    }

    //查全部
    @GetMapping("/getArray")
    public List<Fans> getArray() {
        return arrayService.list();
    }


}
