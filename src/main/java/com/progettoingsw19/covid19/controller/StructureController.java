package com.progettoingsw19.covid19.controller;

import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.model.Type;
import com.progettoingsw19.covid19.service.StructureService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;

@RestController
@RequestMapping( value = "/structure")
public class StructureController {

    @Autowired
    private StructureService structureService;

    @GetMapping(value = "/public/getAllStructures")
    public Page<Structure> getAllStructure(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return structureService.getAllStructure(page, size);
    }

    @GetMapping(value = "/public/getAllStructures/{type}" )
    public Page<Structure> getAllStructure(@PathVariable("type") Type type, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        switch (type){
            case HOTEL -> { return structureService.getAllHotel(page, size); }
            case RESTAURANT -> { return structureService.getAllRestaurant(page, size); }
            case ATTRACTION -> { return structureService.getAllAttraction(page, size); }
            default ->  { return  structureService.getAllStructure(page,size); }
        }
    }

    @GetMapping(value = "/public/getAllStructuresByText/{text}" )
    public Page<Structure> getAllStructure(@PathVariable("text") String text,  @RequestParam("page") Integer page,  @RequestParam("size") Integer size) { return structureService.getAllStructureByText(page,size,text); }

    @GetMapping(value = "/public/getAllStructuresByText/{type}/{text}" )
    public Page<Structure> getAllStructure(@PathVariable("type") Type type, @PathVariable("text") String text,  @RequestParam("page") Integer page,  @RequestParam("size") Integer size) {
        switch (type){
            case HOTEL -> { return structureService.getAllHotelByText(page, size,text); }
            case RESTAURANT -> { return structureService.getAllRestaurantByText(page, size, text); }
            case ATTRACTION -> { return structureService.getAllAttractionByText(page, size,text); }
            default ->  { return  structureService.getAllStructureByText(page,size,text); }
        }
    }

    @GetMapping(value = "/public/getAllStructuresByTextNotPaginable/{text}" )
    public Collection<Structure> getAllStructure(@PathVariable("text") String text) {
        return structureService.getAllStructureByText(text);
    }

    @GetMapping(value = "/public/getAllStructuresByTextNotPaginable/{type}/{text}" )
    public Collection<Structure> getAllStructure(@PathVariable("type") Type type, @PathVariable("text") String text) {
        switch (type){
            case HOTEL -> { return structureService.getAllHotelByText(text); }
            case RESTAURANT -> { return structureService.getAllRestaurantByText( text); }
            case ATTRACTION -> { return structureService.getAllAttractionByText(text); }
            default ->  { return  structureService.getAllStructureByText(text); }
        }
    }

    @GetMapping(value = "/public/getStructureAtDistance", params = {"latitude", "longitude", "distance"} )
    public Collection<Structure> getStructureAroundYou(@RequestParam(name = "latitude") BigDecimal latitude, @RequestParam(name = "longitude") BigDecimal longitude, @RequestParam(name = "distance") BigDecimal distance) {
        return structureService.getStructureAtDistance(latitude,longitude,distance);
    }

    @GetMapping(value = "/public/getStructureAroundYou", params = {"latitude", "longitude", "type"} )
    public Collection<Structure> getStructureAroundYou(@RequestParam(name = "latitude") BigDecimal latitude, @RequestParam(name = "longitude") BigDecimal longitude, @RequestParam(name = "type") Type type) {
        return structureService.getStructureAroundYou(latitude,longitude,type);
    }

    @GetMapping(value = "/public/getStructureAroundYou", params = {"latitude", "longitude"} )
    public Collection<Structure> getStructureAroundYou(@RequestParam(name = "latitude") BigDecimal latitude, @RequestParam(name = "longitude") BigDecimal longitude) {
        return structureService.getStructureAroundYou(latitude,longitude);
    }


    @GetMapping(value = "/public/getTips", params = "text")
    public Collection<String> getTips(@RequestParam(name = "text") String text){
        return structureService.getTips(text);
    }
}
