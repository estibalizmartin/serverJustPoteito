package com.example.serverJustPoteito.cuisineType.service;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypeServiceModel;
import com.example.serverJustPoteito.cuisineType.persistence.CuisineType;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypePostRequest;
import com.example.serverJustPoteito.cuisineType.model.CuisineTypeUpdateResponse;
import com.example.serverJustPoteito.cuisineType.repository.CuisineTypeRepository;
import com.example.serverJustPoteito.ingredient.model.IngredientServiceModel;
import com.example.serverJustPoteito.ingredient.persistence.Ingredient;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CuisineTypeServiceImpl implements CuisineTypeService{
    @Autowired
    CuisineTypeRepository cuisineTypeRepository;

    public List<CuisineTypeServiceModel> getCuisineTypes(){

        Iterable<CuisineType> cuisineTypes = cuisineTypeRepository.findAll();

        List<CuisineTypeServiceModel> response = new ArrayList<>();

        for (CuisineType cuisineType : cuisineTypes) {
            response.add(new CuisineTypeServiceModel(
                    cuisineType.getId(),
                    cuisineType.getName(),
                    cuisineType.getSubtype(),
                    null,
                    getBase64EncodedImage(cuisineType.getImage())
            ));
        }
        return response;
    }
    public CuisineType getCuisineType(Integer id){
        return cuisineTypeRepository.findById(id).orElseThrow (
                () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No existe el tipo de cocina")
        );
    }

    @Override
    public CuisineType createCuisineType(CuisineTypePostRequest cuisineTypePostRequest) {
        CuisineType cuisineType = new CuisineType(
                cuisineTypePostRequest.getName(),
                cuisineTypePostRequest.getSubtype()
        );
        return cuisineTypeRepository.save(cuisineType);
    }

    @Override
    public CuisineTypeUpdateResponse updateCuisineType(Integer id, CuisineTypePostRequest cuisineTypePostRequest) {
        CuisineType cuisineType = new CuisineType (
                id,
                cuisineTypePostRequest.getName(),
                cuisineTypePostRequest.getSubtype()
        );
        boolean alreadyExists = cuisineTypeRepository.existsById(id);
        CuisineType response = cuisineTypeRepository.save(cuisineType);

        return new CuisineTypeUpdateResponse(alreadyExists, response);
    }

    @Override
    public void deleteCuisineType(Integer id) {
        cuisineTypeRepository.deleteById(id);
    }

    public String getBase64EncodedImage(String imageURL) {

        if (imageURL != null) {
            try {
                byte[] fileContent = FileUtils.readFileToByteArray(new File(imageURL + ".png"));
                String encodedString = Base64.encodeBase64String(fileContent);
                return encodedString;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }
}
