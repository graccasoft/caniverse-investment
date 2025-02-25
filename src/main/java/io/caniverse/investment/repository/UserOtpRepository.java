package io.caniverse.investment.repository;

import io.caniverse.investment.model.entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {
    Optional<UserOtp> findFirstByUser_UsernameAndOtp(String username, String otp);
}
