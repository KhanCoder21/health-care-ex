package com.arsad.service;

import com.arsad.entity.Specialization;

import java.util.List;

public interface SpecializationService {

    public Long saveSpecialization(Specialization specialization);

    public List<Specialization> getAllSpecialization();

    public void removeSpecializationById(Long id);

    public Specialization getSpecializationById(Long id);

    public void updateSpecialization(Specialization specialization);

    public boolean isSpecCodeExist(String specCode);

    public boolean isSpecCodeExistForEdit(String specCode, Long id);

}
