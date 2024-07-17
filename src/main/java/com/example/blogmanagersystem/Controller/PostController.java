package com.example.blogmanagersystem.Controller;


import com.example.blogmanagersystem.POJO.Post;
import com.example.blogmanagersystem.POJO.Result;
import com.example.blogmanagersystem.Service.IPostService;
import com.example.blogmanagersystem.Utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    IPostService postService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    // 获取单个文章信息
    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable int postId) {
        return postService.getPostById(postId);
    }

    // 获取所有文章列表
    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/")
    public List<Post> getPostsByUserPaginated(@RequestParam Long userId,
                                              @RequestParam int page,
                                              @RequestParam int size,
                                              @RequestParam(defaultValue = "DESC") String orderBy) {
        return postService.getPostsByUserWithPaginationAndOrder(userId, page, size, orderBy);
    }

    // 创建一篇新文章
    @PostMapping("/")
    public void insertPost(@RequestBody Post post) {
        postService.insertPost(post);
    }

    // 更新文章信息
    @PutMapping("/{postId}")
    public void updatePost(@PathVariable int postId, @RequestBody Post post) {
        post.setPostId(postId);  // 确保传入的 post 对象中有正确的 postId
        postService.updatePost(post);
    }

    @DeleteMapping("/{postId}")
    public Result deletePost(@PathVariable int postId, @RequestHeader("Authorization") String authorizationHeader) {
        // 解析 Authorization 头，获取用户信息
        String userName = decodeUserIdFromToken(authorizationHeader);

        // 检查用户是否有权限删除文章
        boolean hasPermission = postService.checkPermission(postId, userName);

        if (!hasPermission) {
            // 如果没有权限，返回错误信息
            return  Result.failure(10002,"Unauthorized to delete this post");
        }

        // 执行删除操作
        postService.deletePost(postId);
        return  Result.success("Post deleted successfully");
    }


    private String decodeUserIdFromToken(String token) {
        return jwtTokenUtil.getUserNameFromToken(token);
    }
}
