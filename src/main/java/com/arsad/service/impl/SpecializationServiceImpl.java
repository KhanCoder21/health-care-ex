package com.arsad.service.impl;

import com.arsad.utils.CollectionUtils;
import com.arsad.entity.Specialization;
import com.arsad.exception.SpecializationNotFoundException;
import com.arsad.repository.SpecializationRepository;
import com.arsad.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository repository;

    @Override
    public Long saveSpecialization(Specialization specialization) {
        return repository.save(specialization).getId();
    }

    @Override
    public List<Specialization> getAllSpecialization() {
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

    @Override
    public Map<Long, String> getSpecIdAndName() {
        List<Object[]> specIdAndNameList = repository.getSpecIdAndName();
        Map<Long, String> specIdAndNameMap = CollectionUtils.convertListToMap(specIdAndNameList);
        return specIdAndNameMap;
    }

}
