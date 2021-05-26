package com.example.companyapp.controller;

import com.example.companyapp.entity.Company;
import com.example.companyapp.payload.ApiResponse;
import com.example.companyapp.payload.CompanyDto;
import com.example.companyapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @GetMapping("/list")
    public HttpEntity<List<Company>> getCompanies(){
        List<Company> companyList = companyService.getCompanyList();
        return ResponseEntity.ok(companyList);
    }
    @GetMapping("/byID/{id}")
    public HttpEntity<ApiResponse>getCompany(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.getCompany(id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse>addCompany(@RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        if(apiResponse.isStatus()) return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse>editCompany(@RequestBody CompanyDto companyDto, @PathVariable Integer id){
        ApiResponse apiResponse = companyService.editCompany(companyDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteCompany(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.deleteCompany(id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}
