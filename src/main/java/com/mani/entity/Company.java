package com.mani.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Company")
public class Company {
	
	  @Id
	  private String CompanyId;
	  private String password;
	  private String companyName;
	  private String companyGSTno;
	  private int companyphoneno;
	  private String companyMd;
	  private int Mdno;
	  private String Address;
	  
	public Company(String companyId, String password) {
		CompanyId = companyId;
		this.password = password;
	}
	  
	  

}
