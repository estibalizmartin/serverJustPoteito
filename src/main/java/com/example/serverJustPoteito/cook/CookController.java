package com.example.serverJustPoteito.cook;

import com.example.serverJustPoteito.cook.model.CookPostRequest;
import com.example.serverJustPoteito.cook.model.CookServiceModel;
import com.example.serverJustPoteito.cook.model.CookUpdateResponse;
import com.example.serverJustPoteito.cook.service.CookService;
import com.example.serverJustPoteito.dish.model.DishServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CookController {

    @Autowired
    CookService cookService;

    @GetMapping("/cooks")
    public ResponseEntity<Iterable<CookServiceModel>> getCooks() {
        return new ResponseEntity<>(cookService.getCooks(), HttpStatus.OK);
    }
    @GetMapping("/cooksNoToken")
    public ResponseEntity<Iterable<CookServiceModel>> getCooksNoToken() {
        return new ResponseEntity<>(cookService.getCooks(), HttpStatus.OK);
    }

    @GetMapping("/cooksNoToken/{id}")
    public ResponseEntity<CookServiceModel> getCookById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(cookService.getCookById(id), HttpStatus.OK);
    }

    @PostMapping("/cooks")
    public ResponseEntity<CookServiceModel> createCook(@RequestBody CookPostRequest cookPostRequest) {
        return new ResponseEntity<>(cookService.createCook(cookPostRequest), HttpStatus.CREATED);
    }

    @PutMapping("/cooks/{id}")
    public ResponseEntity<CookServiceModel> updateCook(@PathVariable("id") Integer id, @RequestBody CookPostRequest cookPostRequest) {
            CookUpdateResponse response = cookService.updateCook(id, cookPostRequest);

        if (response.isExists()) {
            return new ResponseEntity<>(response.getUpdatedCook(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cooks/{id}")
    public ResponseEntity<?> deleteCook(@PathVariable("id") Integer id) {
        cookService.deleteCook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/dishesByCookNoToken/{cookId}")
    public ResponseEntity <List<DishServiceModel>> getDishesByCookId(@PathVariable("cookId") Integer cookId) {
        return new ResponseEntity<>(cookService.getDishesByCookId(cookId), HttpStatus.OK);
    }

}
