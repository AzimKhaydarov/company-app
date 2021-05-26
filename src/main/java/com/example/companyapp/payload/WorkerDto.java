package com.example.companyapp.payload;

import com.example.companyapp.entity.Address;
import com.example.companyapp.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WorkerDto {

    private String name, phoneNumber, street, homeNumber;

    private Integer departmentId;
}
