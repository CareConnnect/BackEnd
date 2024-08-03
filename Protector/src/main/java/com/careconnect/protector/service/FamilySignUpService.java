package com.careconnect.protector.service;

import com.careconnect.protector.entity.FamilyMember;
import com.careconnect.protector.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FamilySignUpService {
    private final FamilyRepository familyRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(String familyPhoneId, String password,
                       String name, String relation, String email, String address,
                       String profilePhoto, String nickname, String selfIntroduction,
                       Instant signupDate, Instant updateDate) {
        FamilyMember family = new FamilyMember();

        family.setFamilyPhoneId(familyPhoneId);
        family.setPassword(passwordEncoder.encode(password));
        family.setName(name);
        family.setRelation(relation);
        family.setEmail(email);
        family.setAddress(address);
        family.setProfilePhoto(profilePhoto);
        family.setNickname(nickname);
        family.setSelfIntroduction(selfIntroduction);
        family.setSignupDate(signupDate);
        family.setUpdateDate(updateDate);

        this.familyRepository.save(family);
    }

    public void withdraw(String familyPhoneId) {
        Optional<FamilyMember> familyOptional = Optional.ofNullable(familyRepository.findByFamilyPhoneId(familyPhoneId));

        // 사용자가 존재하는지 확인
        if (familyOptional.isPresent()) {
            FamilyMember family = familyOptional.get();
            familyRepository.delete(family);
        } else {
            System.out.println("No family found with familyPhoneId: " + familyPhoneId);
            throw new RuntimeException("No family found with familyPhoneId: " + familyPhoneId);
        }
    }

    public void update(String familyPhoneId, String password,
                       String name, String relation, String email, String address,
                       String profilePhoto, String nickname, String selfIntroduction,
                       Instant signupDate, Instant updateDate) {
        Optional<FamilyMember> familyOptional = Optional.ofNullable(familyRepository.findByFamilyPhoneId(familyPhoneId));

        // 사용자가 존재하는지 확인
        if (familyOptional.isPresent()) {
            FamilyMember family = familyOptional.get();

            family.setFamilyPhoneId(familyPhoneId);
            family.setPassword(passwordEncoder.encode(password));
            family.setName(name);
            family.setRelation(relation);
            family.setEmail(email);
            family.setAddress(address);
            family.setProfilePhoto(profilePhoto);
            family.setNickname(nickname);
            family.setSelfIntroduction(selfIntroduction);
            family.setSignupDate(signupDate);
            family.setUpdateDate(updateDate);

            this.familyRepository.save(family);
        } else {
            System.out.println("No family found with familyPhoneId: " + familyPhoneId);
            throw new RuntimeException("No family found with familyPhoneId: " + familyPhoneId);
        }
    }
}
