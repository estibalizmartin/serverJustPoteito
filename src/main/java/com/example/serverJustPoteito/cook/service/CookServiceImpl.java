package com.example.serverJustPoteito.cook.service;

import com.example.serverJustPoteito.cook.exceptions.CookNotFoundException;
import com.example.serverJustPoteito.cook.model.Cook;
import com.example.serverJustPoteito.cook.model.CookPostRequest;
import com.example.serverJustPoteito.cook.model.CookServiceModel;
import com.example.serverJustPoteito.cook.model.CookUpdateResponse;
import com.example.serverJustPoteito.cook.repository.CookRepository;
import com.example.serverJustPoteito.dish.service.DishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CookServiceImpl implements CookService {

    @Autowired
    CookRepository cookRepository;
    @Autowired
    DishServiceImpl DishServiceImpl;

    @Override
    public Iterable<CookServiceModel> getCooks() {

       List<Cook> queryCooks = (List<Cook>) cookRepository.findAll();

       List<CookServiceModel> responseCooks = new ArrayList<>();
        for (Cook cook: queryCooks) {
            responseCooks.add(new CookServiceModel(
                    cook.getId(),
                    cook.getName(),
                    cook.getLast_names(),
                    cook.getBornDate(),
                    cook.getNationality(),
                    cook.getMichelin_stars(),
                    cook.getDishes()
            ));
        }

        return responseCooks;
    }

    @Override
    public CookServiceModel getCookById(Integer id) throws CookNotFoundException {
        Cook cook = cookRepository.findById(id)
                .orElseThrow(
                        () -> new CookNotFoundException("Cook not found")
                );

        return new CookServiceModel(
                cook.getId(),
                cook.getName(),
                cook.getLast_names(),
                cook.getBornDate(),
                cook.getNationality(),
                cook.getMichelin_stars(),
                cook.getDishes()
        );
    }

    @Override
    public CookServiceModel createCook(CookPostRequest cookPostRequest) {
        Cook cook = new Cook(
                null,
                cookPostRequest.getName(),
                cookPostRequest.getLast_names(),
                cookPostRequest.getNationality(),
                cookPostRequest.getBorn_date(),
                cookPostRequest.getMichelin_stars(),
                null
        );

        Cook responseCook = cookRepository.save(cook);

        return new CookServiceModel(
                responseCook.getId(),
                responseCook.getName(),
                responseCook.getLast_names(),
                responseCook.getBornDate(),
                responseCook.getNationality(),
                responseCook.getMichelin_stars(),
                responseCook.getDishes()
        );
    }

    public CookUpdateResponse updateCook(Integer id, CookPostRequest cookPostRequest) {
        Cook cook = new Cook(
                id,
                cookPostRequest.getName(),
                cookPostRequest.getLast_names(),
                cookPostRequest.getNationality(),
                cookPostRequest.getBorn_date(),
                cookPostRequest.getMichelin_stars(),
                cookRepository.findById(id).get().getDishes()
        );

        boolean cookExists = cookRepository.existsById(id);
        Cook queryCook = null;
        CookServiceModel responseCook = null;

        if (cookExists) {
           queryCook = cookRepository.save(cook);
           responseCook = new CookServiceModel(
                   queryCook.getId(),
                   queryCook.getName(),
                   queryCook.getLast_names(),
                   queryCook.getBornDate(),
                   queryCook.getNationality(),
                   queryCook.getMichelin_stars(),
                   queryCook.getDishes()
           );
        }

        return new CookUpdateResponse(cookExists, responseCook);
    }

    @Override
    public void deleteCook(Integer id) {
        cookRepository.deleteById(id);
    }


}
