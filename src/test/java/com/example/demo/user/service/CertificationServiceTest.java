package com.example.demo.user.service;

import com.example.demo.mock.FakeMailSender;
import com.example.demo.post.service.CertificationService;
import com.example.demo.post.service.port.MailSender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CertificationServiceTest {

    @Test
    @DisplayName("이메일과 컨텐츠가 제대로 만들어져서 보내지는지 테스트한다")
    public void test1() {
        // given
        FakeMailSender fakeMailSender = new FakeMailSender();
        CertificationService certificationService = new CertificationService(fakeMailSender);

        // when
        certificationService.send("test9999@naver.com", 1, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");

        // then
        assertThat(fakeMailSender.email).isEqualTo("test9999@naver.com");
        assertThat(fakeMailSender.title).isEqualTo("Please certify your email address");
        assertThat(fakeMailSender.content).isEqualTo("Please click the following link to certify your email address: http://localhost:8080/api/users/1/verify?certificationCode=aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
    }
}
