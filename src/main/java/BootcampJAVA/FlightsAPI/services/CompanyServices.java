package BootcampJAVA.FlightsAPI.services;

import BootcampJAVA.FlightsAPI.model.Company;
import BootcampJAVA.FlightsAPI.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServices {

    @Autowired
    CompanyRepository companyRepository;

    //CREATE
    public void createCompany(Company company){
        companyRepository.save(company);
    }
    public void createCompanies(List<Company> companies){
        companyRepository.saveAll(companies);
    }

    //READ
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    public List<Company> getCompanyByName(String name){
        return companyRepository.findByName(name);
    }
    public Company getCompanyById(Long id){
        return companyRepository.findById(id).orElse(null);
    }

    //UPDATE
    public Company updateCompany(Company company){
        companyRepository.save(company);
        return companyRepository.findById(company.getId()).orElse(null);
    }

    //DELETE
    public void deleteCompanyById(Long id){
        companyRepository.deleteById(id);
    }

    public void deleteAllCompanies(){
        companyRepository.deleteAll();
    }

}
