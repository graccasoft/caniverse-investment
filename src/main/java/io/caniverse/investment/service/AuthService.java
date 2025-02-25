package io.caniverse.investment.service;

import io.caniverse.investment.model.dto.ConfirmOtpDto;
import io.caniverse.investment.model.dto.RegisterDto;
import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.mapper.InvestorMapper;
import io.caniverse.investment.repository.InvestorRepository;
import io.caniverse.investment.repository.RoleRepository;
import io.caniverse.investment.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final String INVESTOR_ROLE = "INVESTOR";
    private final InvestorMapper investorMapper;
    private final PasswordEncoder passwordEncoder;
    private final InvestorRepository investorRepository;
    private final RoleRepository roleRepository;
    private final OtpService otpService;
    private final UserRepository userRepository;

    public AuthService(InvestorMapper investorMapper,
                       PasswordEncoder passwordEncoder,
                       InvestorRepository investorRepository,
                       RoleRepository roleRepository, OtpService otpService, UserRepository userRepository) {
        this.investorMapper = investorMapper;
        this.passwordEncoder = passwordEncoder;
        this.investorRepository = investorRepository;
        this.roleRepository = roleRepository;
        this.otpService = otpService;
        this.userRepository = userRepository;
    }

    public Investor register(RegisterDto registerDto){

        var optionalUser = userRepository.findByUsername(registerDto.email());
        if(optionalUser.isPresent()){
            throw new ValidationException("User with email already exists");
        }

        var investor = investorMapper.apply(registerDto);
        investor.getUser().setPassword( passwordEncoder.encode( investor.getUser().getPassword() ) );
        investor.getUser().setEnabled(true);
        if(registerDto.referrerId() != null) {
            investor.setReferrer(investorRepository.findById(registerDto.referrerId()).orElse(null));
        }
        roleRepository.findByName(INVESTOR_ROLE).ifPresent(role-> investor.getUser().setRole(role));

        return investorRepository.save(investor);
    }

    public void changePassword(ConfirmOtpDto confirmOtpDto){
        var user = otpService.validateOtpAndReturnUser(confirmOtpDto);
        user.setPassword(passwordEncoder.encode(confirmOtpDto.password()));
        userRepository.save(user);
    }
}
