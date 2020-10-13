package com.progettoingsw19.covid19.controller;

import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.model.Type;
import com.progettoingsw19.covid19.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/structure")
public class StructureController {

    @Autowired
    private StructureService structureService;

    @GetMapping(value = "/getAllStructures")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<Structure> getAllStructure(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return structureService.getAllStructure(page, size);
    }

    @GetMapping(value = "/getAllStructures/{type}" )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<Structure> getAllStructure(@PathVariable("type") Type type, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        switch (type){
            case HOTEL -> { return structureService.getAllHotel(page, size); }
            case RESTAURANT -> { return structureService.getAllRestaurant(page, size); }
            case ATTRACTION -> { return structureService.getAllAttraction(page, size); }
            default ->  { return  structureService.getAllStructure(page,size); }
        }
    }


    @GetMapping(value = "/getAllStructuresByText/{text}" )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<Structure> getAllStructure(@PathVariable("text") String text, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return structureService.getAllStructureByText(page,size,text);
    }

    @GetMapping(value = "/getAllStructuresByText/{type}/{text}" )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<Structure> getAllStructure(@PathVariable("type") Type type, @PathVariable("text") String text, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        switch (type){
            case HOTEL -> { return structureService.getAllHotelByText(page, size,text); }
            case RESTAURANT -> { return structureService.getAllRestaurantByText(page, size, text); }
            case ATTRACTION -> { return structureService.getAllAttractionByText(page, size,text); }
            default ->  { return  structureService.getAllStructureByText(page,size,text); }
        }
    }
}
