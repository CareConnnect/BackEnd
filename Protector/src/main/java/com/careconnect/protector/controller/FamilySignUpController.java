package com.careconnect.protector.controller;

import com.careconnect.protector.DTO.FamilySignUpDTO;
import com.careconnect.protector.service.FamilySignUpService;
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
public class FamilySignUpController {
    private final FamilySignUpService familySignUpService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String signup(@RequestBody FamilySignUpDTO familySignUpDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().toString();
        }

        if (!familySignUpDTO.getPassword().equals(familySignUpDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "confirmPassword.invalid",
                    "2개의 비밀번호가 일치하지 않습니다.");
            return bindingResult.getAllErrors().toString();
        }

        familySignUpService.signUp(familySignUpDTO.getFamilyPhoneId(), familySignUpDTO.getPassword(), familySignUpDTO.getName(),
                familySignUpDTO.getRelation(), familySignUpDTO.getEmail(), familySignUpDTO.getAddress(),
                familySignUpDTO.getProfilePhoto(), familySignUpDTO.getNickname(), familySignUpDTO.getSelfIntroduction(),
                familySignUpDTO.getSignupDate(), familySignUpDTO.getUpdateDate());

        return "회원 가입 성공!!";
    }

    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody FamilySignUpDTO familySignUpDTO) {
        try {
            familySignUpService.withdraw(familySignUpDTO.getFamilyPhoneId());

            return ResponseEntity.ok().body("Family with familyPhoneId " + familySignUpDTO.getFamilyPhoneId()+ " has been successfully deleted.");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<String> update(@RequestBody FamilySignUpDTO familySignUpDTO) {
        try {
            familySignUpService.update(familySignUpDTO.getFamilyPhoneId(), familySignUpDTO.getPassword(),
                    familySignUpDTO.getName(), familySignUpDTO.getRelation(), familySignUpDTO.getEmail(),
                    familySignUpDTO.getAddress(), familySignUpDTO.getProfilePhoto(), familySignUpDTO.getNickname(),
                    familySignUpDTO.getSelfIntroduction(), familySignUpDTO.getSignupDate(), familySignUpDTO.getUpdateDate());

            return ResponseEntity.ok().body("Family with familyPhoneId " + familySignUpDTO.getFamilyPhoneId()+ " has been successfully changed.");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        }


    }
}
