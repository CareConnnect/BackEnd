package com.careconnect.protector.service;

import com.careconnect.protector.entity.FamilyMember;
import com.careconnect.protector.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class FamilySignUpService {
    private final FamilyRepository familyRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(String familyPhoneId, /*String password, String confirmPassword,*/
                       String name, String relation, String email, String address,
                       String profilePhoto, String nickname, String selfIntroduction,
                       Instant signupDate, Instant updateDate) {
        FamilyMember family = new FamilyMember();

        family.setFamilyPhoneId(familyPhoneId);
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
}
