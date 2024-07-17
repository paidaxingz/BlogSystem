package com.example.blogmanagersystem.Service.Impl;


import com.example.blogmanagersystem.Mapper.PostMapper;
import com.example.blogmanagersystem.Mapper.UserMapper;
import com.example.blogmanagersystem.POJO.Post;
import com.example.blogmanagersystem.Service.IPostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    public Post getPostById(int postId) {
        return postMapper.getPostById(postId);
    }

    public List<Post> getAllPosts() {
        return postMapper.getAllPosts();
    }

    public void insertPost(Post post) {
        postMapper.insertPost(post);
    }

    public void updatePost(Post post) {
        postMapper.updatePost(post);
    }

    public void deletePost(int postId) {
        postMapper.deletePost(postId);
    }

    public List<Post> getPostsByUserWithPaginationAndOrder(Long userId, int page, int size, String orderBy) {
        PageHelper.startPage(page, size);
        return postMapper.getPostsByUserIdWithPaginationAndOrder(userId, orderBy);
    }

    @Override
    public boolean checkPermission(int postId, String userName) {
        int userId = userMapper.getUserByName(userName);
        Post post = postMapper.getPostById(postId);
        return userId == post.getUserId() ? true : false;
    }
}
