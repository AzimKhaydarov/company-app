package com.example.companyapp.controller;

import com.example.companyapp.entity.Department;
import com.example.companyapp.payload.ApiResponse;
import com.example.companyapp.payload.DepartmentDto;
import com.example.companyapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/list")
    public HttpEntity<List<Department>> getCompanies(){
        List<Department> departmentList = departmentService.getDepartmentList();
        return ResponseEntity.ok(departmentList);
    }
    @GetMapping("/byID/{id}")
    public HttpEntity<ApiResponse>getDepartment(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.getDepartment(id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse>addDepartment(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        if(apiResponse.isStatus()) return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse>editDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable Integer id){
        ApiResponse apiResponse = departmentService.editDepartment(departmentDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteDepartment(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}
