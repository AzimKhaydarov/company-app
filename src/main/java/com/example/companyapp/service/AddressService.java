package com.example.companyapp.service;

import com.example.companyapp.entity.Address;
import com.example.companyapp.payload.ApiResponse;
import com.example.companyapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addAddress(Address address){
        boolean existsByStreetAndHomeNumber = addressRepository.existsByStreetAndHomeNumber(address.getStreet(), address.getHomeNumber());
        if(existsByStreetAndHomeNumber) return new ApiResponse("Current address already exists!", false);
        Address address1 = new Address();
        address1.setHomeNumber(address.getHomeNumber());
        address1.setStreet(address.getStreet());
        addressRepository.save(address1);
        return new ApiResponse("Address added successfully!", true);
    }
    public ApiResponse editAddress(Address address, Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(!optionalAddress.isPresent()) return new ApiResponse("Current address not found!", false);
        boolean existsByStreetAndHomeNumber = addressRepository.existsByStreetAndHomeNumber(address.getStreet(), address.getHomeNumber());
        if(existsByStreetAndHomeNumber) return new ApiResponse("Current address already exists!", false);
        Address address1 = new Address();
        address1.setHomeNumber(address.getHomeNumber());
        address1.setStreet(address.getStreet());
        addressRepository.save(address1);
        return new ApiResponse("Address edited successfully!", true);
    }
    public List<Address> getAddressList(){
        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }
    public ApiResponse getAddress( Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(!optionalAddress.isPresent()) return new ApiResponse("Current address not found!", false);
        return new ApiResponse("address exists!",true, optionalAddress.get());
    }
    public ApiResponse deleteAddress(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(!optionalAddress.isPresent()) return new ApiResponse("Current address not found!", false);
        addressRepository.delete(optionalAddress.get());
        return new ApiResponse("Selected address deleted successfully!", true);
    }

}
