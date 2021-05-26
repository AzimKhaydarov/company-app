package com.example.companyapp.repository;

import com.example.companyapp.entity.Address;
import com.example.companyapp.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByCorpName(String corp_name);
}
