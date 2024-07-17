package com.example.blogmanagersystem.Mapper;


import com.example.blogmanagersystem.POJO.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT * FROM post WHERE post_id = #{postId}")
    Post getPostById(@Param("postId") int postId);

    @Insert("INSERT INTO post(title, content, user_id, created, last_modified) " +
            "VALUES(#{title}, #{content}, #{userId}, #{created}, #{lastModified})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    void insertPost(Post post);

    @Update("UPDATE post SET title = #{title}, content = #{content}, " +
            "user_id = #{userId}, created = #{created}, last_modified = #{lastModified} " +
            "WHERE post_id = #{postId}")
    void updatePost(Post post);

    @Delete("DELETE FROM post WHERE post_id = #{postId}")
    void deletePost(@Param("postId") int postId);

    @Select("SELECT * FROM post")
    List<Post> getAllPosts();

    @Select("SELECT * FROM post WHERE user_id = #{userId} ORDER BY created ${orderBy}")
    List<Post> getPostsByUserIdWithPaginationAndOrder(@Param("userId") Long userId,
                                                      @Param("orderBy") String orderBy);
}
