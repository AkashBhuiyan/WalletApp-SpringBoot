package com.akash.walletapp.service;

import com.akash.walletapp.entity.Wallet;
import com.akash.walletapp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createOrUpdate(Wallet wallet){
        if(wallet.getId()==null){
            walletRepository.save(wallet);
        } else {
            walletRepository.save(wallet);
        }
        return wallet;
    }
}
