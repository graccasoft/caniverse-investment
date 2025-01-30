package io.caniverse.investment.service;

import io.caniverse.investment.model.dto.RegisterDto;
import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.mapper.InvestorMapper;
import io.caniverse.investment.repository.InvestorRepository;
import io.caniverse.investment.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final String INVESTOR_ROLE = "INVESTOR";
    private final InvestorMapper investorMapper;
    private final PasswordEncoder passwordEncoder;
    private final InvestorRepository investorRepository;
    private final RoleRepository roleRepository;

    public AuthService(InvestorMapper investorMapper,
                       PasswordEncoder passwordEncoder,
                       InvestorRepository investorRepository,
                       RoleRepository roleRepository) {
        this.investorMapper = investorMapper;
        this.passwordEncoder = passwordEncoder;
        this.investorRepository = investorRepository;
        this.roleRepository = roleRepository;
    }

    public Investor register(RegisterDto registerDto){
        var investor = investorMapper.apply(registerDto);
        investor.getUser().setPassword( passwordEncoder.encode( investor.getUser().getPassword() ) );
        if(registerDto.referrerId() != null) {
            investor.setReferrer(investorRepository.findById(registerDto.referrerId()).orElse(null));
        }
        roleRepository.findByName(INVESTOR_ROLE).ifPresent(role-> investor.getUser().setRole(role));

        return investorRepository.save(investor);
    }
}
