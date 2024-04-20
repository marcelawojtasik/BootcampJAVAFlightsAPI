package BootcampJAVA.FlightsAPI.controller;

import BootcampJAVA.FlightsAPI.exceptions.ResourceNotFoundException;
import BootcampJAVA.FlightsAPI.model.Company;
import BootcampJAVA.FlightsAPI.services.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyServices companyServices;


    @CrossOrigin
    @PostMapping("/create")
    public void createCompany (@RequestBody Company company){
        companyServices.createCompany(company);
    }
    @PostMapping("/createAll")
    public void createAllCompanies (@RequestBody List<Company> companies){
        companyServices.createCompanies(companies);
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
//    public void updateCompany(@RequestBody Company company){
//        companyServices.updateCompany(company);
//    }
    public String updateCompany(@RequestBody Company company){
        try{
            companyServices.updateCompany(company);
            return "Compañía actualizada correctamente";
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return "No existe la compañía solicitada";
        }
    }

    @DeleteMapping("/delete{id}")
    public String deleteCompanyById(@PathVariable Long id){
        try{
            companyServices.deleteCompanyById(id);
            return "Compañía borrada exitosamente";
        } catch (ResourceNotFoundException e){
            System.out.println(e.getMessage());
            return "No se encontró la compañía solicitada";
        } catch (Exception e){
            e.printStackTrace();
            return "No se completó el proceso";}
    }

}
