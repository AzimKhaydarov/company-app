package com.example.companyapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentDto {

    private String name;
    private Integer companyId;


}
