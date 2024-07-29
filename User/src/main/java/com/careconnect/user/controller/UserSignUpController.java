package com.careconnect.user.controller;

import com.careconnect.user.DTO.UserSignUpDTO;
import com.careconnect.user.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class UserSignUpController {
    private final UserSignUpService userSignUpService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String signup(@RequestBody UserSignUpDTO userSignUpDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().toString();
        }

        if (!userSignUpDTO.getPassword().equals(userSignUpDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "confirmPassword.invalid",
                    "2개의 비밀번호가 일치하지 않습니다.");
            return bindingResult.getAllErrors().toString();
        }

        userSignUpService.signUp(userSignUpDTO.getUserPhoneId(), userSignUpDTO.getPassword(),
                userSignUpDTO.getName(), userSignUpDTO.getEmail(), userSignUpDTO.getAddress(),
                userSignUpDTO.getProfilePhoto(), userSignUpDTO.getNickname(),
                userSignUpDTO.getSelfIntroduction(), userSignUpDTO.getSignupDate(), userSignUpDTO.getUpdateDate());

        return "회원 가입 성공!!";
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody UserSignUpDTO userSignUpDTO) {
        try {
            userSignUpService.withdraw(userSignUpDTO.getUserPhoneId());

            return ResponseEntity.ok().body("User with userPhoneId " + userSignUpDTO.getUserPhoneId() + " has been successfully deleted.");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }
}
