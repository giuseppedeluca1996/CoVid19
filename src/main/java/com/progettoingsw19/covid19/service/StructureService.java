package com.progettoingsw19.covid19.service;

import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class StructureService {
    @Autowired
    private StructureRepository structureRepository;


    public List<Structure> getAllStructure(){ return structureRepository.findAll(); }
    public  Structure getStructureById(Integer id){ return structureRepository.findById(id).orElse(null); }
    public  Structure getStructureByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude){ return structureRepository.findByLatitudeAndLongitude(latitude,longitude); }

    public void deleteStructure(Structure structure) { structureRepository.delete(structure); }
    public void deleteStructureById(Integer id) { structureRepository.deleteById(id); }
    public void deleteStructureByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude ) { structureRepository.deleteByLatitudeAndLongitude(latitude,longitude); }

    public void updateStructureById(Structure newStructure, Integer id) {
        Structure s= structureRepository.findById(id).orElse(null);
        if(s != null){
            s.setAddress(newStructure.getAddress());
            s.setCity(newStructure.getCity());
            s.setState(newStructure.getState());
            s.setClosingHours(newStructure.getClosingHours());
            s.setOpeningHours(newStructure.getOpeningHours());
            s.setEmail(newStructure.getEmail());
            s.setImageLink(newStructure.getImageLink());
            s.setSite(newStructure.getSite());
            s.setName(newStructure.getName());
            s.setLatitude(newStructure.getLongitude());
            s.setLongitude(newStructure.getLongitude());
            s.setPriceMax(newStructure.getPriceMax());
            s.setPriceMax(newStructure.getPriceMin());
            s.setPhone(newStructure.getPhone());
            s.setType(newStructure.getType());
            structureRepository.save(s);
        }
    }
    public void updateStructureByLatitudeAndLongitude(Structure newStructure, BigDecimal latitude, BigDecimal longitude ) {
        Structure s= structureRepository.findByLatitudeAndLongitude(latitude,longitude);
        if(s != null){
            s.setAddress(newStructure.getAddress());
            s.setCity(newStructure.getCity());
            s.setState(newStructure.getState());
            s.setClosingHours(newStructure.getClosingHours());
            s.setOpeningHours(newStructure.getOpeningHours());
            s.setEmail(newStructure.getEmail());
            s.setImageLink(newStructure.getImageLink());
            s.setSite(newStructure.getSite());
            s.setName(newStructure.getName());
            s.setLatitude(newStructure.getLongitude());
            s.setLongitude(newStructure.getLongitude());
            s.setPriceMax(newStructure.getPriceMax());
            s.setPriceMax(newStructure.getPriceMin());
            s.setPhone(newStructure.getPhone());
            s.setType(newStructure.getType());
            structureRepository.save(s);
        } }

    public void insert(Structure structure){ structureRepository.save(structure); }
}
