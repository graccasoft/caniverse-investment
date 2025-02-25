package io.caniverse.investment.service;

import io.caniverse.investment.model.dto.ConfirmOtpDto;
import io.caniverse.investment.model.entity.User;
import io.caniverse.investment.model.entity.UserOtp;
import io.caniverse.investment.repository.UserOtpRepository;
import io.caniverse.investment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Service
public class OtpService {


    private final UserOtpRepository userOtpRepository;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;

    public OtpService(UserOtpRepository userOtpRepository, EmailSenderService emailSenderService, UserRepository userRepository) {
        this.userOtpRepository = userOtpRepository;
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
    }

    public void sendOtpEmail(String email) {
        var user = userRepository.findByUsername(email)
                .orElseThrow(() -> new ValidationException("The provided email could not be found." + email));
        var otp = saveOtp(user);
        var otpUrl = "https://cnvinvest.com/change-password?username=" + URLEncoder.encode(user.getUsername(), StandardCharsets.UTF_8) + "&otp=" + otp ;
        emailSenderService.sendEmail(
                user.getUsername(),
                "CNV Password reset",
                otpEmailContent( otp, otpUrl));
    }

    private String otpEmailContent( String otp, String otpUrl) {
        return """
                <h1>Dear investor</h1>
                <p>Use this OTP to reset your password</p>
                <p>Your OTP is: %s</p>
                <p>Or click <a href="%s">here</a> to verify reset your password</p>
                """.formatted( otp, otpUrl);
    }

    private String saveOtp(User user) {
        var otp = generateRandomDigits(6);
        UserOtp userOtp = new UserOtp();
        userOtp.setUser(user);
        userOtp.setOtp(otp);

        userOtpRepository.save(userOtp);

        return otp;
    }

    private String generateRandomDigits(int length) {
        var randomDigits = new StringBuilder();

        var random = new Random();
        int rand;
        for (int x = 0; x < length; x++) {
            while (true) {
                rand = random.nextInt(10);
                if (rand != 0) {
                    randomDigits.append(rand);
                    break;
                }
            }
        }

        return randomDigits.toString();
    }


    public User validateOtpAndReturnUser(ConfirmOtpDto confirmOtp) {
        var userOtp = userOtpRepository.findFirstByUser_UsernameAndOtp(confirmOtp.username(), confirmOtp.otp())
                .orElseThrow(() -> new ValidationException("Invalid OTP"));

        var user = userOtp.getUser();
        if (user == null) {
            throw new ValidationException("Invalid email");
        }

        return user;
    }

}
