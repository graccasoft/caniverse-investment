package io.caniverse.investment.service;

import io.caniverse.investment.exception.RecordNotFoundException;
import io.caniverse.investment.model.dto.SupportIssueResponseDto;
import io.caniverse.investment.model.entity.SupportIssue;
import io.caniverse.investment.model.entity.SupportIssueResponse;
import io.caniverse.investment.model.enums.SupportIssueStatus;
import io.caniverse.investment.repository.SupportIssueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportIssueService {

    private final InvestorService investorService;
    private final SupportIssueRepository supportIssueRepository;
    private final Logger log = LoggerFactory.getLogger(SupportIssueService.class);

    public SupportIssueService(InvestorService investorService, SupportIssueRepository supportIssueRepository) {
        this.investorService = investorService;
        this.supportIssueRepository = supportIssueRepository;
    }

    public void saveIssue(SupportIssue supportIssue, Authentication authentication) {
        supportIssue.setInvestor( investorService.getInvestorFromAuthentication(authentication) );
        supportIssue.setStatus(SupportIssueStatus.OPEN);
        supportIssueRepository.save(supportIssue);
    }

    public void saveResponse(SupportIssueResponseDto responseDto, boolean fromAdmin){
        var supportIssue = supportIssueRepository.findById(responseDto.supportIssueId())
                .orElseThrow(()-> new RecordNotFoundException("Support issue not found"));

        log.info("Support issue found: {}", responseDto);
        SupportIssueResponse supportIssueResponse = new SupportIssueResponse();
        supportIssueResponse.setResponse(responseDto.response());
        supportIssueResponse.setSupportIssue(supportIssue);
        supportIssueResponse.setFromAdmin(fromAdmin);
        supportIssue.getResponses().add(supportIssueResponse);
        supportIssueRepository.save(supportIssue);
        log.info("Support issue responses: {}", supportIssue.getResponses().size());
    }

    public List<SupportIssue> getAllIssues(){
        return supportIssueRepository.findAll();
    }

    public SupportIssue getIssueById(Long id){
        return supportIssueRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("Support issue not found"));
    }

    public List<SupportIssue> getIssuesByInvestor(Authentication authentication){
        return supportIssueRepository.findByInvestor(investorService.getInvestorFromAuthentication(authentication));
    }


}
