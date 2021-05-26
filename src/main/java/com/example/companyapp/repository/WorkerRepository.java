package com.example.companyapp.repository;

import com.example.companyapp.entity.Company;
import com.example.companyapp.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
boolean existsByNameAndPhoneNumber(String name, String phone_number);

}
