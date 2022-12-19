package com.example.serverJustPoteito.cuisineType.Service;
import com.example.serverJustPoteito.cuisineType.Model.CuisineType;
import com.example.serverJustPoteito.cuisineType.Model.CuisineTypePostRequest;
import com.example.serverJustPoteito.cuisineType.Model.CuisineTypeUpdateResponse;
import com.example.serverJustPoteito.cuisineType.Repository.CuisineTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CuisineTypeServiceImp implements CuisineTypeService{
    @Autowired
    CuisineTypeRepository cuisineTypeRepository;

    public Iterable<CuisineType> getCuisineTypes(){
        return cuisineTypeRepository.findAll();
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
}
