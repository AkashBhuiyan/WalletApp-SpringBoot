package com.akash.walletapp.controller;


import com.akash.walletapp.entity.Wallet;
import com.akash.walletapp.service.ValidationErrorService;
import com.akash.walletapp.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private ValidationErrorService validationService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result){

        ResponseEntity errors = validationService.validate(result);
        if(errors != null){
            return errors;
        }

        Wallet walletSaved = walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Wallet wallet, BindingResult result){

        ResponseEntity errors = validationService.validate(result);
        if(errors != null){
            return errors;
        }
        wallet.setId(id);
        Wallet walletSaved = walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        walletService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(walletService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return new ResponseEntity<>(walletService.getById(id), HttpStatus.OK);
    }
}
