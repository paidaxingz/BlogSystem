package com.example.blogmanagersystem.Service;

import com.example.blogmanagersystem.POJO.Post;

import java.util.List;

public interface IPostService {

    // 根据 postId 查询 Post
    Post getPostById(int postId);

    // 查询所有 Post
    List<Post> getAllPosts();

    // 插入一条 Post
    void insertPost(Post post);

    // 更新一条 Post
    void updatePost(Post post);

    // 删除一条 Post
    void deletePost(int postId);

    List<Post> getPostsByUserWithPaginationAndOrder(Long userId, int page, int size, String orderBy);

    boolean checkPermission(int postId, String userName);
}
