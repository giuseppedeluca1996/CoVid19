package com.progettoingsw19.covid19.controller;

import com.progettoingsw19.covid19.model.Structure;
import com.progettoingsw19.covid19.model.Type;
import com.progettoingsw19.covid19.service.StructureService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
            case HOTEL : { return structureService.getAllHotel(page, size); }
            case RESTAURANT : { return structureService.getAllRestaurant(page, size); }
            case ATTRACTION : { return structureService.getAllAttraction(page, size); }
            default :  { return  structureService.getAllStructure(page,size); }
        }
    }

    @GetMapping(value = "/public/getAllStructuresByText/{text}" )
    public Page<Structure> getAllStructure(@PathVariable("text") String text,  @RequestParam("page") Integer page,  @RequestParam("size") Integer size) { return structureService.getAllStructureByText(page,size,text); }

    @GetMapping(value = "/public/getAllStructuresByText/{type}/{text}" )
    public Page<Structure> getAllStructure(@PathVariable("type") Type type, @PathVariable("text") String text,  @RequestParam("page") Integer page,  @RequestParam("size") Integer size) {
        switch (type){
            case HOTEL : { return structureService.getAllHotelByText(page, size,text); }
            case RESTAURANT : { return structureService.getAllRestaurantByText(page, size, text); }
            case ATTRACTION : { return structureService.getAllAttractionByText(page, size,text); }
            default :  { return  structureService.getAllStructureByText(page,size,text); }
        }
    }
    @GetMapping(value = "/public/getStructureAtDistance", params = {"latitude","longitude","distance"})
    public Collection<Structure> getStructureAtDistance(@RequestParam(name = "latitude") BigDecimal latitude,@RequestParam(name = "longitude")BigDecimal longitude, @RequestParam(name = "distance")BigDecimal distance){
        return structureService.getStructureAtDistance(latitude,longitude,distance);
    }

    @PostMapping(value = "/public/getStructureByText")
    public Collection<Structure> getStructureByText(@RequestBody String body) {
        JSONParser jsonParser =new JSONParser();
        try {
            JSONObject json = (JSONObject) jsonParser.parse(body);
            Double priceMin = ((Number)json.get("priceMin")).doubleValue();
            Double priceMax = ((Number)json.get("priceMax")).doubleValue();
            Double rating = ((Number)json.get("rating")).doubleValue();
            JSONArray jsonArray=(JSONArray) json.get("types");
            String query =(String)json.get("searchValue");
            Type hotel=null;
            Type attraction=null;
            Type restaurant=null;
            for (int i = 0; i < jsonArray.size(); i++) {
                Type t=Type.valueOf(jsonArray.get(i).toString());
                if(t ==Type.HOTEL){
                    hotel=Type.HOTEL;
                }
                if( t ==Type.ATTRACTION){
                    attraction=Type.ATTRACTION;
                }
                if(t ==Type.RESTAURANT){
                    restaurant=Type.RESTAURANT;
                }
            }

            if(hotel != null && attraction != null && restaurant != null)
                return structureService.getStructureByText(priceMin,priceMax,query,rating ,hotel,attraction,restaurant);
            else if(hotel != null && attraction != null)
                return structureService.getStructureByText(priceMin,priceMax,query,rating,hotel,attraction);
            else if (attraction != null && restaurant != null)
                return structureService.getStructureByText(priceMin,priceMax,query,rating,attraction,restaurant);
            else if(hotel != null && restaurant != null)
                return structureService.getStructureByText(priceMin,priceMax,query,rating,hotel,restaurant);
            else if(hotel!=null)
                return structureService.getStructureByText(priceMin,priceMax,query,rating ,hotel);
            else if(attraction!=null)
                return structureService.getStructureByText(priceMin,priceMax,query,rating,attraction);
            else
                return structureService.getStructureByText(priceMin,priceMax,query,rating,restaurant);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/public/getStructureAroundYou")
    public Collection<Structure> getStructureAroundYou(@RequestBody String body) {
        JSONParser jsonParser =new JSONParser();
        try {
            JSONObject json = (JSONObject) jsonParser.parse(body);
            BigDecimal latitude = BigDecimal.valueOf((Double) json.get("latitude"));
            BigDecimal longitude = BigDecimal.valueOf((Double) json.get("longitude"));
            Double priceMin = ((Number)json.get("priceMin")).doubleValue();
            Double priceMax = ((Number)json.get("priceMax")).doubleValue();
            JSONArray jsonArray=(JSONArray) json.get("types");
            Double rating = ((Number)json.get("rating")).doubleValue();
            Type hotel=null;
            Type attraction=null;
            Type restaurant=null;
            for (int i = 0; i < jsonArray.size(); i++) {
                Type t=Type.valueOf(jsonArray.get(i).toString());
                if(t ==Type.HOTEL){
                    hotel=Type.HOTEL;
                }
                if( t ==Type.ATTRACTION){
                    attraction=Type.ATTRACTION;
                }
                if(t ==Type.RESTAURANT){
                    restaurant=Type.RESTAURANT;
                }
            }

            if(hotel != null && attraction != null && restaurant != null)
                return structureService.getStructureAroundYou(latitude,longitude,priceMin,priceMax,rating,hotel,attraction,restaurant);
            else if(hotel != null && attraction != null)
                return structureService.getStructureAroundYou(latitude,longitude,priceMin,priceMax,rating,hotel,attraction);
            else if (attraction != null && restaurant != null)
                return structureService.getStructureAroundYou(latitude,longitude,priceMin,priceMax,rating,attraction,restaurant);
            else if(hotel != null && restaurant != null)
                return structureService.getStructureAroundYou(latitude,longitude,priceMin,priceMax,rating,hotel,restaurant);
            else if(hotel!=null)
                return structureService.getStructureAroundYou(latitude,longitude,priceMin,priceMax,rating,hotel);
            else if(attraction!=null)
                return structureService.getStructureAroundYou(latitude,longitude,priceMin,priceMax,rating,attraction);
            else
                return structureService.getStructureAroundYou(latitude,longitude,priceMin,priceMax,rating,restaurant);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    @GetMapping(value = "/public/getTips", params = "text")
    public Collection<String> getTips(@RequestParam(name = "text") String text){
        return structureService.getTips(text);
    }
}
