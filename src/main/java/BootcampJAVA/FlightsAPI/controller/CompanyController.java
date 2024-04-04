package BootcampJAVA.FlightsAPI.controller;

import BootcampJAVA.FlightsAPI.model.Company;
import BootcampJAVA.FlightsAPI.model.Flight;
import BootcampJAVA.FlightsAPI.services.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyServices companyServices;

    @PostMapping("/create")
    public void createCompany (@RequestBody Company company){
        companyServices.createCompany(company);
    }

    @GetMapping("/all")
    public List<Company> allCompanies(){
        return companyServices.getAllCompanies();
    }

    @GetMapping("/name")
    public List<Company> companiesByName(@RequestParam String name){
        return companyServices.getCompanyByName(name);
    }
    @GetMapping("/id{id}")
    public Company companyById(@PathVariable Long id){
        return companyServices.getCompanyById(id);
    }

    @PutMapping("/update")
    public void updateCompany(@RequestBody Company company){
        companyServices.updateCompany(company);
    }

    @DeleteMapping("/delete")
    public void deleteCompanyById(@PathVariable Long id){
        companyServices.deleteCompanyById(id);
    }

}
