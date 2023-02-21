package com.mykyta.iexstocks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mykyta.iexstocks.model.Company;
import lombok.Data;

@Data
public class CompanyDto {
    private Long id;

    @JsonProperty(value = "symbol")
    private String companyName;

    public Company toCompany(){
        Company company = new Company();
        company.setCompanyName(companyName);

        return company;
    }

    public static CompanyDto fromCompany(Company company){
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyName(company.getCompanyName());

        return companyDto;
    }
}
