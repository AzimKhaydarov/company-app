package com.example.companyapp.controller;

import com.example.companyapp.entity.Worker;
import com.example.companyapp.payload.ApiResponse;
import com.example.companyapp.payload.WorkerDto;
import com.example.companyapp.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;
    @GetMapping("/list")
    public HttpEntity<List<Worker>> getCompanies(){
        List<Worker> workerList = workerService.getWorkerList();
        return ResponseEntity.ok(workerList);
    }
    @GetMapping("/byID/{id}")
    public HttpEntity<ApiResponse>getWorker(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.getWorker(id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse>addWorker(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        if(apiResponse.isStatus()) return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse>editWorker(@RequestBody WorkerDto workerDto, @PathVariable Integer id){
        ApiResponse apiResponse = workerService.editWorker(workerDto, id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteWorker(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
        return ResponseEntity.status(apiResponse.isStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

}
