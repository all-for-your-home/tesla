package com.example.allforyourhome.repository;

import com.example.allforyourhome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumberAndEnabledTrue(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByVerificationCode(Integer verificationCode);

    boolean existsByVerificationCode(Integer verificationCode);
}