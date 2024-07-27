package com.careconnect.protector.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class FamilySignUpDTO {
    private String familyPhoneId;
//    private String password;
//    private String confirmPassword;
    private String name;
    private String relation;
    private String email;
    private String address;
    private String profilePhoto;
    private String nickname;
    private String selfIntroduction;
    private Instant signupDate;
    private Instant updateDate;
}
