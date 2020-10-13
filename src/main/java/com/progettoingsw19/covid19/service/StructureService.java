package com.progettoingsw19.covid19.service;

import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.model.Type;
import com.progettoingsw19.covid19.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;

@Service
public class StructureService {
    @Autowired
    private StructureRepository structureRepository;

    public Page<Structure> getAllStructure(Integer page, Integer size){ return structureRepository.findAll(PageRequest.of(page, size)); }
    public Page<Structure> getAllHotel(Integer page, Integer size){ return structureRepository.findAllByTypeIs(PageRequest.of(page,size), Type.HOTEL); }
    public Page<Structure> getAllRestaurant(Integer page, Integer size){ return structureRepository.findAllByTypeIs(PageRequest.of(page,size), Type.RESTAURANT); }
    public Page<Structure> getAllAttraction(Integer page, Integer size){ return structureRepository.findAllByTypeIs(PageRequest.of(page,size), Type.ATTRACTION); }

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
