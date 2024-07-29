package com.careconnect.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "user_phone_id", nullable = false, length = 20)
    private String userPhoneId;

    @Column(name = "password")
    private String password;

    @Column(name = "kakao_id", length = 50)
    private String kakaoId;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Lob
    @Column(name = "self_introduction")
    private String selfIntroduction;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Column(name = "signup_date", nullable = false)
    private Instant signupDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private Instant updateDate;

}