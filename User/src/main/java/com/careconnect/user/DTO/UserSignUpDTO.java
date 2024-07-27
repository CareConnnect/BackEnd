package com.careconnect.user.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserSignUpDTO {
    private String userPhoneId;
    private String password;
    private String confirmPassword;
    private String kakaoId;
    private String name;
    private String email;
    private String address;
    private String profilePhoto;
    private String nickname;
    private String selfIntroduction;
    private Instant signupDate;
    private Instant updateDate;
}
