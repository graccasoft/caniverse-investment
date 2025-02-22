package io.caniverse.investment.model.mapper;

import io.caniverse.investment.model.dto.RegisterDto;
import io.caniverse.investment.model.entity.Investor;
import io.caniverse.investment.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InvestorMapper implements Function<RegisterDto, Investor> {
    @Override
    public Investor apply(RegisterDto registerDto) {
        var user = new User(registerDto.email(), registerDto.password());
        return new Investor(registerDto.name(), registerDto.phoneNumber(), user);
    }
}
