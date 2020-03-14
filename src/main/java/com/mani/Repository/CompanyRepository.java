package com.mani.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mani.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, String> {

}
