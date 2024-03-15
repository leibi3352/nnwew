package com.example.test1.controller;

import com.example.test1.Service.UserService;
import com.example.test1.entity.News;
import com.example.test1.entity.User;
import com.example.test1.mapper.UserMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.runtime.regexp.RegExp;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    //注册功能
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // 检查账号是否已存在
        User existingUser = userService.findByAccount(user.getAccount());
        if (existingUser != null) {
            return "Username already exists";
        }
        // 保存用户
        userService.save(user);
        return "User registered successfully";
    }


    //登录功能
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userService.findByAccount(user.getAccount());
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            return "Invalid username or password";
        }

        // 生成 JWT 令牌
        String token = Jwts.builder()
//                .setSubject(existingUser.getUsername())
                .claim("userName", Base64.encodeBase64String(existingUser.getUsername().getBytes(StandardCharsets.UTF_8))) // 添加用户 ID 到 JWT 有效载荷
                .claim("userId", existingUser.getUid()) // 添加用户 ID 到 JWT 有效载荷
                .claim("account", existingUser.getAccount()) // 添加用户 ID 到 JWT 有效载荷
                .claim("uweight", existingUser.getUweight()) // 添加用户 ID 到 JWT 有效载荷
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 设置过期时间为 24 小时
                .signWith(SignatureAlgorithm.HS512, "your-secret-key")
                .compact();
        return token;
    }

    //注销账号
    @PostMapping("/logout")
    public String logout() {
        // 注销逻辑，实际上 JWT 令牌在客户端过期即可
        return "Logged out";
    }

    @GetMapping("/getUser")
    @ResponseBody
    public User getUserById(@RequestParam long Account) {
        return userService.findByAccount(Account);
    }


    @GetMapping("/getUsername")
    @ResponseBody
    public User getUserByAuthor(@RequestParam String Author) {
        return userService.findByAuthor(Author);
    }


    //用户列表
    @GetMapping
    public List<User> getUser() {
        return userService.list();
    }


    //    点赞和收藏功能
    @GetMapping("/LikeOrCollection")
    @ResponseBody
    public HashMap<String, Integer> getUserLikeOrCollection(@RequestParam String Nid, @RequestParam String Uid,
                                                            @RequestParam int LikeOrCollection, @RequestParam int Modify) {
        //点赞和收藏功能


        User user = userService.getById(Uid);
        HashMap<String, Integer> UserLikeOrCollection = new HashMap<String, Integer>();

        if (user.getUcollection()!= null || user.getUlike()!= null) {
            String[] arr = user.getUcollection().split(",");
            String[] arr2 = user.getUlike().split(",");


            if (Modify == 1) {
                if (LikeOrCollection == 1) {
//            Ulike等于1
                    if (user.getUlike() != null && !user.getUlike().isEmpty()) {
                        if (!user.getUlike().contains(Nid)) {
                            user.setUlike(user.getUlike() + "," + Nid);
                        } else {
                            if (arr2.length > 1) {
                                if (arr2[0].equals(Nid))
                                    user.setUlike(user.getUlike().replace(Nid + ",", ""));
                                else
                                    user.setUlike(user.getUlike().replace("," + Nid, ""));
                            } else
                                user.setUlike(user.getUlike().replace(Nid, ""));
                        }
                    } else {
                        user.setUlike(Nid);
                    }
                } else {
//                Ucollection不等于1

                    if (user.getUcollection() != null && !user.getUcollection().isEmpty()) {
                        if (!user.getUcollection().contains(Nid)) {
                            user.setUcollection(user.getUcollection() + "," + Nid);
                        } else {
                            if (arr.length > 1) {
                                if (arr[0].equals(Nid))
                                    user.setUcollection(user.getUcollection().replace(Nid + ",", ""));
                                else
                                    user.setUcollection(user.getUcollection().replace("," + Nid, ""));
                            } else
                                user.setUcollection(user.getUcollection().replace(Nid, ""));
                        }
                    } else {
                        user.setUcollection(Nid);
                    }

                }
                userMapper.updateById(user);
            }
        }

//------------------------------------------------------------------------------------

        if (user.getUlike() != null) {
            if (user.getUlike().contains(Nid))
                UserLikeOrCollection.put("Ulike", 0);
            else
                UserLikeOrCollection.put("Ulike", 1);
        } else UserLikeOrCollection.put("Ulike", 1);


        if (user.getUcollection() != null) {
            if (user.getUcollection().contains(Nid))
                UserLikeOrCollection.put("Ucollection", 0);
            else
                UserLikeOrCollection.put("Ucollection", 1);
        } else UserLikeOrCollection.put("Ucollection", 1);

        return UserLikeOrCollection;
    }

    //删除用户
    @GetMapping("/deleteUserById")
    @ResponseBody
    public void deleteUserById(@RequestParam Long Uid) {
        userMapper.deleteById(Uid);
    }

    //更新用户
    @PostMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        System.out.println(user.toString());
        userService.updateById(user);
    }

}
