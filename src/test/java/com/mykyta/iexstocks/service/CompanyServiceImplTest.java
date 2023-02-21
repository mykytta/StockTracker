package com.mykyta.iexstocks.service;

import com.mykyta.iexstocks.model.Company;
import com.mykyta.iexstocks.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CompanyServiceImplTest {

    @MockBean
    CompanyRepository companyRepository;

    @Autowired
    CompanyService companyService;

    @Test
    void getCompaniesDto() {
        when(companyRepository.findAll()).thenReturn(companyList());
        assertEquals(2, companyService.getCompaniesDto().size());
        assertNotNull(companyService.getCompaniesDto());
    }

    private List<Company> companyList(){
        return Stream.of(new Company(1L, "-H"),
                new Company(2L, "-S")).collect(Collectors.toList());
    }
}