package com.progettoingsw19.covid19.service;

import com.progettoingsw19.covid19.model.Review;
import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.model.Type;
import com.progettoingsw19.covid19.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

@Service
public class StructureService {
    @Autowired
    private StructureRepository structureRepository;

    public Page<Structure> getAllStructure(Integer page, Integer size){ return structureRepository.findAll(PageRequest.of(page, size)); }

    public Page<Structure> getAllHotel(Integer page, Integer size){ return structureRepository.findAllByTypeIs(PageRequest.of(page,size), Type.HOTEL); }

    public Page<Structure> getAllRestaurant(Integer page, Integer size){ return structureRepository.findAllByTypeIs(PageRequest.of(page,size), Type.RESTAURANT); }

    public Page<Structure> getAllAttraction(Integer page, Integer size){ return structureRepository.findAllByTypeIs(PageRequest.of(page,size), Type.ATTRACTION); }

    public Page<Structure> getAllStructureByText(Integer page, Integer size,String text){ return structureRepository.findByNameOrAddressOrCityOrState(PageRequest.of(page,size), text); }

    public Page<Structure> getAllHotelByText(Integer page, Integer size, String text){ return structureRepository.findByNameOrAddressOrCityOrStateAndTypeEquals(PageRequest.of(page,size),text,Type.HOTEL); }

    public Page<Structure> getAllRestaurantByText(Integer page, Integer size, String text){ return structureRepository.findByNameOrAddressOrCityOrStateAndTypeEquals(PageRequest.of(page,size), text, Type.RESTAURANT); }

    public Page<Structure> getAllAttractionByText(Integer page, Integer size, String text){ return structureRepository.findByNameOrAddressOrCityOrStateAndTypeEquals(PageRequest.of(page,size),text, Type.ATTRACTION); }





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

    public Collection<Structure> getStructureAtDistance(BigDecimal latitude, BigDecimal longitude, BigDecimal distance) {
        return structureRepository.getStructureAtDistance(latitude,longitude,distance);
    }

    public Collection<Structure> getStructureAroundYou(BigDecimal latitude, BigDecimal longitude,Double priceMin,Double priceMax,Double rating, Type... type) {
        Collection<Structure>  s;
        int num=0;
        double avg;
        double sum=0D;
        switch (type.length){
            case 1:{
                s=structureRepository.getStructureAroundYou(latitude,longitude,priceMin,priceMax,type[0]);
            }break;
            case 2:{
                s=structureRepository.getStructureAroundYou(latitude,longitude,priceMin,priceMax,type[0],type[1]);
            }break;
            case 3:{
                s=structureRepository.getStructureAroundYou(latitude,longitude,priceMin,priceMax,type[0],type[1],type[2]);
            }break;
            default:{
                return null;
            }
        }
        if(s!=null){
            Collection<Structure>  ris=new ArrayList<>();
            for(Structure ss : s){
                for(Review r: ss.getReviews()){
                    sum=r.getRating().doubleValue()+sum;
                    num++;
                }
                avg=sum/num;
                if(avg<=rating){
                    ris.add(ss);
                }
                sum=0D;
                num=0;
            }
            return ris;
        }
        return s;
    }

    public Collection<Structure> getStructureByText(Double priceMin, Double priceMax,String query,Double rating,Type... type) {

       Collection<Structure>  s;
       int num=0;
       double avg;
       double sum=0D;
        switch (type.length){
            case 1:{
               s=structureRepository.getStructureByText(priceMin,priceMax,query,type[0]);
            }break;
            case 2:{
                s=structureRepository.getStructureByText(priceMin,priceMax,query,type[0],type[1]);
            }break;
            case 3:{
                s = structureRepository.getStructureByText(priceMin,priceMax,query,type[0],type[1],type[2]);
            }break;
            default:{
                return null;
            }
        }
        if(s!=null){
            Collection<Structure>  ris=new ArrayList<>();
            for(Structure ss : s){
                for(Review r: ss.getReviews()){
                    sum=r.getRating().doubleValue()+sum;
                    num++;
                }
                avg=sum/num;
                if(avg<=rating){
                    ris.add(ss);
                }
                sum=0D;
                num=0;
            }
            return ris;
        }
        return s;
    }


    public Collection<String> getTips(String text) {

        ListenableFuture<Collection<String>> ftipsState;
        ListenableFuture<Collection<String>> ftipsCity;
        Collection<String> tipsCity;
        Collection<String> tipsState;
        ftipsState=structureRepository.getTipsState(text);
        ftipsCity=structureRepository.getTipsCity(text);

        if(ftipsCity.isDone() && ftipsState.isDone()){
            try {
                tipsState=ftipsState.get();
                tipsCity=ftipsCity.get();
                if(tipsCity != null){
                    if(tipsState != null){
                        tipsCity.addAll(tipsState);
                    }
                    return tipsCity;
                }else {
                    return tipsState;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
