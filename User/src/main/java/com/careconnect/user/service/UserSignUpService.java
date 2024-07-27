package com.careconnect.user.service;

import com.careconnect.user.entity.User;
import com.careconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class UserSignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(String userPhoneId, String password, String name, String email,
                       String address, String profilePhoto, String nickname, String selfIntroduction,
                       Instant signupDate, Instant updateDate) {
        User user = new User();

        user.setUserPhoneId(userPhoneId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setName(name);
        user.setEmail(email);
        user.setAddress(address);
        user.setProfilePhoto(profilePhoto);
        user.setNickname(nickname);
        user.setSelfIntroduction(selfIntroduction);
        user.setSignupDate(signupDate);
        user.setUpdateDate(updateDate);

        this.userRepository.save(user);
    }
}