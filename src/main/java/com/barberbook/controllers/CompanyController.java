package com.barberbook.controllers;

import com.barberbook.models.Company;
import com.barberbook.repositories.UserRepository;
import com.barberbook.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final UserRepository userRepository; // Inyectar UserRepository

    // Crear una nueva compañía
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        // Verificar si el usuario existe
        if (!userRepository.existsById(company.getIdUser())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Company createdCompany = companyService.createCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    // Obtener todas las compañías
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    // Obtener una compañía por ID
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    // Actualizar una compañía
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        Company updatedCompany = companyService.updateCompany(id, companyDetails);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    // Eliminar una compañía
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}