package com.mykyta.iexstocks.service;

import com.mykyta.iexstocks.dto.CompanyDto;
import com.mykyta.iexstocks.model.Company;
import com.mykyta.iexstocks.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@EnableScheduling
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;
    private final RestTemplate restTemplate;

    @Value(value = "${iexapi.refdata.host}")
    private String host;
    @Value("${iexapi.token}")
    private String token;

    @Override
    public List<Company> getCompaniesDto() {
        if (companyRepository.count() == 0) {
            CompanyDto[] companyDtos =
                    restTemplate.getForEntity(host + token, CompanyDto[].class).getBody();
            List<Company> companyDtoList = Arrays.stream(companyDtos)
                    .map(CompanyDto::toCompany)
                    .collect(Collectors.toList());
            companyRepository.saveAll(companyDtoList);
            return companyDtoList;
        }
        return companyRepository.findAll();
    }
}
