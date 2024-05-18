package com.example.f1_insider_api.Driver;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.f1_insider_api.Driver.DriverModel;

public interface DriverRepository extends JpaRepository<DriverModel, Long> {

}
