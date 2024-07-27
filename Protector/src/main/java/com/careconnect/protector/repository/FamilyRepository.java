package com.careconnect.protector.repository;

import com.careconnect.protector.entity.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<FamilyMember, String> {
    FamilyMember findByFamilyPhoneId(String familyPhoneId);
}
