package com.example.serverJustPoteito.cook.service;

import com.example.serverJustPoteito.cook.model.Cook;
import com.example.serverJustPoteito.cook.model.CookPostRequest;
import com.example.serverJustPoteito.cook.model.CookServiceModel;
import com.example.serverJustPoteito.cook.model.CookUpdateResponse;
import com.example.serverJustPoteito.cook.repository.CookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CookServiceImpl implements CookService {

    @Autowired
    CookRepository cookRepository;

    @Override
    public Iterable<CookServiceModel> getCooks() {

       List<Cook> queryCooks = (List<Cook>) cookRepository.findAll();

       List<CookServiceModel> responseCooks = new ArrayList<>();
        for (Cook cook: queryCooks) {
            responseCooks.add(new CookServiceModel(
                    cook.getId(),
                    cook.getName(),
                    cook.getSur_names(),
                    cook.getBornDate(),
                    cook.getNationality(),
                    cook.getMichelin_stars(),
                    null
            ));
        }

        return responseCooks;
    }

    @Override
    public CookServiceModel getCookById(Integer id){
        Cook cook = cookRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Cocinero no encontrado.")
                );

        return new CookServiceModel(
                cook.getId(),
                cook.getName(),
                cook.getSur_names(),
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
                responseCook.getSur_names(),
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
                null
        );

        boolean cookExists = cookRepository.existsById(id);
        Cook responseCook = null;
        if (cookExists) {
           responseCook = cookRepository.save(cook);
        }

        return new CookUpdateResponse(cookExists, responseCook);
    }
}
