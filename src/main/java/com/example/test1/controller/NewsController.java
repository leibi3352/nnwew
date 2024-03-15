package com.example.test1.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.test1.Service.NewsService;
import com.example.test1.Service.UserService;
import com.example.test1.entity.News;
import com.example.test1.entity.User;
import com.example.test1.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @Autowired
    private NewsMapper newsMapper;


    //增加点击量
    @PutMapping("/clicks/{nid}")
    public void increaseClicks(@PathVariable Long nid) {

        newsService.updateClicks(nid);
    }


    //获取新闻列表
    @GetMapping
    public List<News> getArticles() {

        return newsService.list();
    }


    //热点榜
    @GetMapping("/hot-news")
    public List<News> getHotNews() {
        // 使用MyBatis-Plus的LambdaQueryWrapper对数据进行排序和限制查询结果
        LambdaQueryWrapper<News> queryWrapper = new QueryWrapper<News>().lambda();
        queryWrapper.orderByDesc(News::getClicks);
        queryWrapper.last("limit 5");
        return newsService.list(queryWrapper);
    }

    //获取行业新闻
    @GetMapping("/industry/{industry}")
    public List<News> getFinanceNews(@PathVariable("industry") String industry) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Industry", industry).last("limit 5");
        return newsService.list(queryWrapper);
    }

    //搜索新闻
    @GetMapping("/search")
    public ResponseEntity<List<News>> searchNews(@RequestParam("keyword") String keyword) {
        List<News> newsList = newsService.searchNews(keyword);
        return ResponseEntity.ok(newsList);
    }


    //提交评论
    @GetMapping("/submit")
    @ResponseBody
    public void submitComment(@RequestParam("Comm") String comm, @RequestParam("Uid") long uid, @RequestParam("Nid") long nid) {

        // 1. 根据条件查找要修改的News对象
        News newsToUpdate = newsService.getById(nid);

        String comment;
        String commentId;
        String nid2 = String.valueOf(nid);
        String uid2 = String.valueOf(uid);
        if (newsToUpdate.getCommentId()!= null){
            comment = newsToUpdate.getComment() + "," + comm;
            commentId = newsToUpdate.getCommentId() + "," + uid2;
        }
        else {commentId = uid2; comment= comm;
        }

        // 2. 设置新的Comment值
        newsToUpdate.setComment(comment);
        newsToUpdate.setCommentId(commentId);
        // 3. 调用newsService的更新方法
        newsMapper.updateById(newsToUpdate);
    }

    //投稿列表
    @GetMapping("Contribution")
    @ResponseBody
    public List<News> getContribution(@RequestParam("Username") String Username) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Author",Username);
        return newsService.list(queryWrapper);
    }

//    点赞列表
    @GetMapping("like")
    @ResponseBody
    public List<News> getlike(@RequestParam("Username") String Username) {
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("Username",Username);
        User user = userService.getOne(queryWrapper1);
        List<News> result = new ArrayList<>();
        String like = user.getUlike();
        if (like!=null) {
            String[] like2 = new String[like.split(",").length];
            if (like.contains(",")) {
                like2 = like.split(",");
            } else {
                like2[0] = like;
            }

        for (String likeValue : like2) {
            QueryWrapper<News> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Nid", likeValue);

            result.addAll(newsService.list(queryWrapper));
        }}
        return result;
    }

    //收藏列表
    @GetMapping("Collection")
    @ResponseBody
    public List<News> getCollection(@RequestParam("Username") String Username) {
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("Username",Username);
        User user = userService.getOne(queryWrapper1);
        List<News> result = new ArrayList<>();
        String Collection = user.getUcollection();

        if (Collection!=null) {
            String[] Collection2 = new String[Collection.split(",").length];
            if (Collection.contains(",")) {
                Collection2 = Collection.split(",");
            } else {
                Collection2[0] = Collection;
            }


            for (String likeValue : Collection2) {
                QueryWrapper<News> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("Nid", likeValue);

                result.addAll(newsService.list(queryWrapper));
            }
        }
        return result;
    }

    //关注列表
    @GetMapping("Attention")
    @ResponseBody
    public List<News> getAttentionNews(@RequestParam("Username") String Username) {
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("Username",Username);
        User user = userService.getOne(queryWrapper1);
        String Attention = user.getAttention();
        String[] Attention2 = new String[Attention.split(",").length];
        String[] Attention3 = new String[Attention.split(",").length];
        if (Attention.contains(",")){
            Attention2=Attention.split(",");
        }
        else {
            Attention2[0] = Attention;
        }

        List<User> result2 = new ArrayList<>();
        for (String a : Attention2){
            QueryWrapper<User> queryWrapper3 = new QueryWrapper<>();
            queryWrapper3.eq("Uid", a);

            result2.addAll(userService.list(queryWrapper3));
        }

        List<News> result = new ArrayList<>();

        for (User likeValue : result2) {
            QueryWrapper<News> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Author", likeValue.getUsername());

            result.addAll(newsService.list(queryWrapper));

        }
        return result;
    }


    //提交文章
    @PostMapping("Submit")
    @ResponseBody
    public void SubmitArticle(
            @RequestParam("content") String content,@RequestParam("title") String title,
            @RequestParam("newsname") String newsname,@RequestParam("author") String author
    )
    {
        News news =new News();
        news.setAuthor(author);
        news.setTitle(title);
        news.setContent(content);
        news.setNewsname(newsname);

        newsService.save(news);
    }



    //删除文章
    @GetMapping("/deleteNewsById")
    @ResponseBody
    public void deleteNewsById(@RequestParam Long Nid) {
        newsMapper.deleteById(Nid);
    }


    //更新文章
    @PostMapping("/updateNews")
    public void updateNews( @RequestBody News news) {
        System.out.println(news.getDate());
        newsService.updateById(news);
    }


    //文章权重
    @GetMapping("/Weight")
    public List<News> WeightNews( ) {
        List<News> news = newsService.list();
        List<News> news2 = new ArrayList<>();
        for ( News a : news){
            if (a.getWeight()>0){
                news2.add(a);
            }
        }

        Collections.sort(news2, new Comparator<News>() {
            @Override
            public int compare(News n1, News n2) {
                return n2.getDate().compareTo(n1.getDate());
            }
        });

        return news2;
    }

    //删除评论
    @GetMapping("/deleteComment")
    @ResponseBody
    public void deleteComment(@RequestParam("Commid") Long commid,
                              @RequestParam("Nid") Long nid) {
        System.out.println(commid+"++++"+nid);

        // 1. 根据条件查找要修改的News对象
        News newsToUpdate = newsService.getById(nid);

        String[] array1 = newsToUpdate.getComment().split(",");
        String[] array2 = newsToUpdate.getCommentId().split(",");

        String[] newArray = new String[array1.length - 1];
        String[] newArray2 = new String[array2.length - 1];
        for (int i = 0, j = 0; i< array1.length; i++) {
            if (i != commid) {
                newArray[j] = array1[i];
                newArray2[j] = array2[i];
                j++;
            }
        }

        String comment = Arrays.toString(newArray);
        String commentId = Arrays.toString(newArray2);

        // 2. 设置新的Comment值
        newsToUpdate.setComment(comment);
        newsToUpdate.setCommentId(commentId);

        // 3. 调用newsService的更新方法
        newsMapper.updateById(newsToUpdate);
    }

    //快报
    @GetMapping("/KuaiBao")
    @ResponseBody
    public List<News> KuaiBao(@RequestParam("Username") String Username){
        List<News> news = getAttentionNews(Username);
        List<News> news2=new ArrayList<>();
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("Username",Username);
        User user = userService.getOne(queryWrapper1);

        //看过的新闻数组
        String[] array1 = user.getUread().split(",");

        for (int i=0 ; i <array1.length;i++){
            for (News nn : news){
                if ( !nn.getNid().toString().equals(array1[i]) ) {
                    news2.add(nn);
                }
            }
            if (!(i ==array1.length-1)) {
                news.clear();
                news.addAll(news2);
                news2.clear();
            }

        }

        // 按照 Data 字段对 news2 列表进行降序排序
        Collections.sort(news2, new Comparator<News>() {
            @Override
            public int compare(News n1, News n2) {
                return n2.getDate().compareTo(n1.getDate());
            }
        });
        return  news2;

    }

}
