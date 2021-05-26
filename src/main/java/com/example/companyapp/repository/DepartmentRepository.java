package com.example.companyapp.repository;

import com.example.companyapp.entity.Company;
import com.example.companyapp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

   boolean existsByNameAndCompany_Id(String name, Integer company_id);
}
