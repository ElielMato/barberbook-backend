package com.barberbook.services;

import com.barberbook.repositories.CompanyRepository;
import com.barberbook.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CompanyRepository companyRepository;

    public com.barberbook.models.Service createService(com.barberbook.models.Service service) {
        if (!companyRepository.existsById(service.getIdCompany())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company does not exist");
        }
        return serviceRepository.save(service);
    }

    public List<com.barberbook.models.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public com.barberbook.models.Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));
    }

    public com.barberbook.models.Service updateService(Long id, com.barberbook.models.Service serviceDetails) {
        if (!companyRepository.existsById(serviceDetails.getIdCompany())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company does not exist");
        }
        com.barberbook.models.Service service = getServiceById(id);
        service.setName(serviceDetails.getName());
        service.setDuration(serviceDetails.getDuration());
        service.setPrice(serviceDetails.getPrice());
        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        com.barberbook.models.Service service = getServiceById(id);
        serviceRepository.delete(service);
    }
}