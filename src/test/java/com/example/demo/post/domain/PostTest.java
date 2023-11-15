package com.example.demo.post.domain;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class PostTest {

    @Test
    @DisplayName("PostCreate 로 게시물을 만들 수 있다 ")
    public void test1() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .writerId(3)
                .content("helloWorld")
                .build();
        User writer = User.builder()
                .id(3L)
                .build();

        //when
        Post post = Post.from(writer, postCreate);

        //then
        assertThat(post.getContent()).isEqualTo("helloWorld");
        assertThat(post.getWriter().getId()).isEqualTo(3L);
    }
}
