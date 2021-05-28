package com.example.companyapp.service;

import com.example.companyapp.entity.Address;
import com.example.companyapp.entity.Company;
import com.example.companyapp.payload.ApiResponse;
import com.example.companyapp.payload.CompanyDto;
import com.example.companyapp.repository.AddressRepository;
import com.example.companyapp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CompanyRepository companyRepository;


    public ApiResponse addCompany(CompanyDto companyDto) {

        boolean existsByCorpName = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (existsByCorpName) return new ApiResponse("Current company already exists!", false);
        Company company1 = new Company();
        company1.setCorpName(companyDto.getCorpName());
        company1.setDirectorName(companyDto.getDirectorName());
        Address address1 = new Address();
        address1.setStreet(companyDto.getStreet());
        address1.setHomeNumber(companyDto.getHomeNumber());
        company1.setAddress(address1);

        addressRepository.save(address1);
        companyRepository.save(company1);
        return new ApiResponse("Company added successfully!", true);
    }

    public ApiResponse editCompany(CompanyDto companyDto, Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(!optionalCompany.isPresent()) return new ApiResponse("Current company not found!", false);
        boolean existsByCorpName = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (existsByCorpName) return new ApiResponse("Current company already exists!", false);
        Company company1 = optionalCompany.get();
        company1.setCorpName(companyDto.getCorpName());
        company1.setDirectorName(companyDto.getDirectorName());
        Address address1 = company1.getAddress();
        address1.setStreet(companyDto.getStreet());
        address1.setHomeNumber(companyDto.getHomeNumber());
        company1.setAddress(address1);
        addressRepository.save(address1);
        companyRepository.save(company1);
        return new ApiResponse("Selected company edited successfully!", true);
    }

    public List<Company> getCompanyList() {
        List<Company> companyList = companyRepository.findAll();
        return companyList;
    }

    public ApiResponse getCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) return new ApiResponse("Current company not found!", false);
        return new ApiResponse("company exists!", true, optionalCompany.get());
    }

    public ApiResponse deleteCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()) return new ApiResponse("Current company not found!", false);
        companyRepository.delete(optionalCompany.get());
        return new ApiResponse("Selected company deleted successfully!", true);
    }

}
