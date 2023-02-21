package com.mykyta.iexstocks.repository;

import com.mykyta.iexstocks.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
