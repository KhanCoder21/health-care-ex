package com.arsad.service.impl;

import java.util.List;
import java.util.Optional;

import com.arsad.entity.Specialization;
import com.arsad.exception.SpecializationNotFoundException;
import com.arsad.repository.SpecializationRepository;
import com.arsad.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository repository;

    @Override
    public Long saveSpecialization(Specialization specialization) {
        System.out.println("save method called");
        return repository.save(specialization).getId();
    }

    @Override
    public List<Specialization> getAllSpecialization() {
        System.out.println("Find all method called");
        return repository.findAll();
    }

    @Override
    public void removeSpecializationById(Long id) {
        repository.delete(getSpecializationById(id));
    }

    @Override
    public Specialization getSpecializationById(Long id) {
        /*
         * Optional<Specialization> optional = repository.findById(id); if
         * (optional.isPresent()) { return optional.get(); } else throw new
         * SpecializationNotFoundException(id + " not found");
         */
        return repository.findById(id).orElseThrow(() -> new SpecializationNotFoundException(id + " not found"));

    }

    @Override
    public void updateSpecialization(Specialization specialization) {
        System.out.println("update method called");
        repository.save(specialization);
    }

    @Override
    public boolean isSpecCodeExist(String specCode) {
        return repository.getSpecCodeCount(specCode) > 0 ? true : false;
    }

    @Override
    public boolean isSpecCodeExistForEdit(String specCode, Long id) {
        return repository.getSpecCodeCountForEdit(specCode, id) > 0 ? true : false;
    }

}
