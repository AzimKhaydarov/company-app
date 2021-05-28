package com.example.companyapp.service;

import com.example.companyapp.entity.Company;
import com.example.companyapp.entity.Department;
import com.example.companyapp.payload.ApiResponse;
import com.example.companyapp.payload.DepartmentDto;
import com.example.companyapp.repository.CompanyRepository;
import com.example.companyapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DepartmentRepository departmentRepository;


    public ApiResponse addDepartment(DepartmentDto departmentDto) {

        boolean exists = departmentRepository.existsByNameAndCompany_Id(departmentDto.getName(),departmentDto.getCompanyId());
        if (exists) return new ApiResponse("Current department already exists!", false);
        Department department1 = new Department();
        department1.setName(departmentDto.getName());
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if(!optionalCompany.isPresent()) return new ApiResponse("Current company not found!", false);
        department1.setCompany(optionalCompany.get());
        departmentRepository.save(department1);
        return new ApiResponse("Department added successfully!", true);
    }

    public ApiResponse editDepartment(DepartmentDto departmentDto, Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(!optionalDepartment.isPresent()) return new ApiResponse("Current department not found!", false);
        boolean exists = departmentRepository.existsByNameAndCompany_Id(departmentDto.getName(),departmentDto.getCompanyId());
        if (exists) return new ApiResponse("Current department already exists!", false);
        Department department1 = optionalDepartment.get();
        department1.setName(departmentDto.getName());
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if(!optionalCompany.isPresent()) return new ApiResponse("Current company not found!", false);
        department1.setCompany(optionalCompany.get());
        departmentRepository.save(department1);
        return new ApiResponse("Selected department edited successfully!", true);
    }

    public List<Department> getDepartmentList() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList;
    }

    public ApiResponse getDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return new ApiResponse("Current department not found!", false);
        return new ApiResponse("department exists!", true, optionalDepartment.get());
    }

    public ApiResponse deleteDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) return new ApiResponse("Current department not found!", false);
        departmentRepository.delete(optionalDepartment.get());
        return new ApiResponse("Selected department deleted successfully!", true);
    }

}
