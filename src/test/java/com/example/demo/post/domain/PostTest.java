package com.example.demo.post.domain;

import com.example.demo.mock.TestClockHolder;
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
        Post post = Post.from(writer, postCreate, new TestClockHolder(1L));

        //then
        assertThat(post.getContent()).isEqualTo("helloWorld");
        assertThat(post.getWriter().getId()).isEqualTo(3L);
    }

    @Test
    @DisplayName("PostUpdate 로 게시물을 수정할수있다")
    public void test2() {
        // given
        Post post = Post.builder()
                .id(1L)
                .content("변경전")
                .build();

        PostUpdate postUpdate = PostUpdate.builder()
                .content("변경함")
                .build();


        //when
        Post result = post.update(postUpdate, new TestClockHolder(1L));

        //then
        assertThat(result.getContent()).isEqualTo("변경함");
        assertThat(result.getModifiedAt()).isEqualTo(1L);
    }

}
