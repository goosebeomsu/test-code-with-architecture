package com.example.demo.user.domain;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestUuidHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    @Test
    public void test1() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("UserCreate로 객체를 생성 할 수 있다")
    public void test2() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .email("test@gmail.com")
                .nickname("test")
                .address("성남시")
                .build();

        // when
        User user = User.from(userCreate, new TestUuidHolder("uuid"));

        // then
        assertThat(user.getId()).isNull();
        assertThat(user.getAddress()).isEqualTo("성남시");
        assertThat(user.getCertificationCode()).isEqualTo("uuid");
    }

    @Test
    @DisplayName("UserUpdate로 데이터를 업데이트 할 수 있다")
    public void test3() {
        // given
        User user = User.builder()
                .id(1L)
                .address("성남")
                .nickname("구스범수")
                .build();

        UserUpdate userUpdate = UserUpdate.builder()
                .address("시흥")
                .nickname("구범")
                .build();

        // when
        user = user.update(userUpdate);

        // then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getAddress()).isEqualTo("시흥");
        assertThat(user.getNickname()).isEqualTo("구범");
    }

    @Test
    @DisplayName("로그인을 할 수 있고 로그인시 마지막 로그인 시간이 변경된다")
    public void test4() {
        // given
        User user = User.builder()
                .id(1L)
                .address("성남")
                .nickname("구스범수")
                .lastLoginAt(10L)
                .build();

        // when
        user = user.login(new TestClockHolder(100L));

        // 로그인이 됐는지 체크 + 마지막 로그인 시간 테스트가 필요
        // then
        assertThat(user.getLastLoginAt()).isEqualTo(100L);
    }

    @Test
    @DisplayName("인증코드로 계정을 활성화 할 수 있다")
    public void test5() {
        // given
        User user = User.builder()
                .id(1L)
                .address("성남")
                .nickname("구스범수")
                .certificationCode("code")
                .build();

        // when
        user = user.certificate("code");

        // then
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    @DisplayName("잘못된 인증 코드로 계정을 활성화 하려하면 에러를 던진다.")
    public void test6() {
        // given
        User user = User.builder()
                .id(1L)
                .address("성남")
                .nickname("구스범수")
                .certificationCode("code")
                .build();

        // when
        assertThatThrownBy(() -> {
            user.certificate("another code");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);

    }

}
