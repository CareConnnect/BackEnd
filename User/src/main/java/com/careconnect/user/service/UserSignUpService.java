package com.careconnect.user.service;

import com.careconnect.user.entity.User;
import com.careconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

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
        user.setPassword(passwordEncoder.encode(password));
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

    public void withdraw(String userPhoneId) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUserPhoneId(userPhoneId));

        // 사용자가 존재하는지 확인
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
        } else {
            System.out.println("No user found with userPhoneId: " + userPhoneId);
            throw new RuntimeException("No user found with userPhoneId: " + userPhoneId);
        }
    }
}