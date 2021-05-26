package com.example.companyapp.controller;

import com.example.companyapp.entity.Address;
import com.example.companyapp.payload.ApiResponse;
import com.example.companyapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @GetMapping("/list")
    public HttpEntity<List<Address>> getAddresses(){
        List<Address> addressList = addressService.getAddressList();
        return ResponseEntity.ok(addressList);
    }
    @GetMapping("/byID/{id}")
    public HttpEntity<ApiResponse>getAddress(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.getAddress(id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse>addAddress(@RequestBody Address address){
        ApiResponse apiResponse = addressService.addAddress(address);
        if(apiResponse.isStatus()) return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse>editAddress(@RequestBody Address address, @PathVariable Integer id){
        ApiResponse apiResponse = addressService.editAddress(address, id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}
