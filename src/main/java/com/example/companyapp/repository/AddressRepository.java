package com.example.companyapp.repository;

import com.example.companyapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    boolean existsByStreetAndHomeNumber(String street, String home_number);

}
