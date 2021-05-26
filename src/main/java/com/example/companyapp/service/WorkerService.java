package com.example.companyapp.service;

import com.example.companyapp.entity.Address;
import com.example.companyapp.entity.Department;
import com.example.companyapp.entity.Worker;
import com.example.companyapp.payload.ApiResponse;
import com.example.companyapp.payload.WorkerDto;
import com.example.companyapp.repository.AddressRepository;
import com.example.companyapp.repository.DepartmentRepository;
import com.example.companyapp.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;


    public ApiResponse addWorker(WorkerDto workerDto) {

        boolean exists = workerRepository.existsByNameAndPhoneNumber(workerDto.getName(), workerDto.getPhoneNumber());
        if (exists) return new ApiResponse("Current worker already exists!", false);
        Worker worker1 = new Worker();
        worker1.setName(workerDto.getName());
        worker1.setPhoneNumber(workerDto.getPhoneNumber());
        Address address1 = new Address();
        address1.setStreet(workerDto.getStreet());
        address1.setHomeNumber(workerDto.getHomeNumber());
        worker1.setAddress(address1);
        addressRepository.save(address1);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if(!optionalDepartment.isPresent()) return new ApiResponse("Current department not found!", false);
        worker1.setDepartment(optionalDepartment.get());
        workerRepository.save(worker1);
        return new ApiResponse("Worker added successfully!", true);
    }

    public ApiResponse editWorker(WorkerDto workerDto, Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if(!optionalWorker.isPresent()) return new ApiResponse("Current worker not found!", false);
        boolean exists = workerRepository.existsByNameAndPhoneNumber(workerDto.getName(), workerDto.getPhoneNumber());
        if (exists) return new ApiResponse("Current worker already exists!", false);
        Worker worker1 = new Worker();
        worker1.setName(workerDto.getName());
        worker1.setPhoneNumber(workerDto.getPhoneNumber());
        Address address1 = new Address();
        address1.setStreet(workerDto.getStreet());
        address1.setHomeNumber(workerDto.getHomeNumber());
        worker1.setAddress(address1);
        addressRepository.save(address1);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if(!optionalDepartment.isPresent()) return new ApiResponse("Current department not found!", false);
        worker1.setDepartment(optionalDepartment.get());
        workerRepository.save(worker1);
        return new ApiResponse("Selected worker edited successfully!", true);
    }

    public List<Worker> getWorkerList() {
        List<Worker> workerList = workerRepository.findAll();
        return workerList;
    }

    public ApiResponse getWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return new ApiResponse("Current worker not found!", false);
        return new ApiResponse("worker exists!", true, optionalWorker.get());
    }

    public ApiResponse deleteWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()) return new ApiResponse("Current worker not found!", false);
        workerRepository.delete(optionalWorker.get());
        return new ApiResponse("Selected worker deleted successfully!", true);
    }

}
