package com.example.serverJustPoteito.cuisineType;

import com.example.serverJustPoteito.cuisineType.service.CuisineTypeServiceImp;
import com.example.serverJustPoteito.cuisineType.model.CuisineType;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypePostRequest;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypeUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api")
public class CuisineTypeController {
    @Autowired
    private CuisineTypeServiceImp cuisineTypeService;
    @GetMapping("/cuisineTypes")
    public ResponseEntity<Iterable<CuisineType>> getCuisineTypes() {
        return new ResponseEntity<Iterable<CuisineType>>(cuisineTypeService.getCuisineTypes(), HttpStatus.OK);
    }

    @GetMapping("/cuisineTypes/{id}")
    public ResponseEntity<CuisineType> getCuisineTypeId(@PathVariable("id") Integer id){
        CuisineType cuisineType = cuisineTypeService.getCuisineType(id);
        return new ResponseEntity<CuisineType>(cuisineType, HttpStatus.OK);
    }

    @PostMapping("/cuisineTypes")
    public ResponseEntity<CuisineType> createCuisineType(@RequestBody CuisineTypePostRequest cuisineTypePostRequest){
        return new ResponseEntity<>(
                cuisineTypeService.createCuisineType(cuisineTypePostRequest),
                HttpStatus.OK
        );
    }

    @PutMapping("/cuisineTypes/{id}")
    public ResponseEntity<CuisineType> updateCuisineType(@PathVariable("id") Integer id, @RequestBody CuisineTypePostRequest cuisineTypePostRequest) {
        CuisineTypeUpdateResponse response = cuisineTypeService.updateCuisineType(id, cuisineTypePostRequest);

        if (response.isAlreadyExists()) {
            return new ResponseEntity<>(response.getUpdatedCuisine(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response.getUpdatedCuisine(), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/cuisineTypes/{id}")
    public ResponseEntity<?> deleteCuisineTypeById(@PathVariable("id") Integer id) {
        try {
            cuisineTypeService.deleteCuisineType(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No existe el tipo de cocina");
        }
    }
}
