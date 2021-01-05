package com.akash.walletapp.service;

import com.akash.walletapp.entity.Transaction;
import com.akash.walletapp.entity.Wallet;
import com.akash.walletapp.exception.WalletException;
import com.akash.walletapp.repository.TransactionRepository;
import com.akash.walletapp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;

    public Transaction createOrUpdate(Long walletId, Transaction transaction){
        Optional<Wallet> wallet = walletRepository.findById(walletId);

        if (wallet.isPresent()){
            transaction.setWallet(wallet.get());
            if(transaction.getId()==null){
                transactionRepository.save(transaction);
            } else {
                transactionRepository.save(transaction);
            }

            return transaction;
        }
        return null;

    }

    public boolean delete(Long walletId,Long id){
        Optional<Wallet> wallet = walletRepository.findById(walletId);

        if (wallet.isPresent()) {
            Optional<Transaction> transaction = transactionRepository.findById(id);

            if (transaction.isPresent()) {
                transactionRepository.delete(transaction.get());
                return true;
            }
        }
        throw new WalletException("Transaction with "+ id+ " does not exist!");
    }

    public  Transaction getById(Long walletId, Long id){
        Optional<Wallet> wallet = walletRepository.findById(walletId);

        if (wallet.isPresent()) {
            Optional<Transaction> transaction = transactionRepository.findById(id);
            if (transaction.isPresent()) {
                return transaction.get();
            }
        }
        throw new WalletException("Transaction with "+ id+ " does not exist!");
    }

    public List<Transaction> getAll(Long walletId){
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if(wallet.isPresent()){
            return transactionRepository.findByWallet(wallet.get());
        }
        return null;

    }


}
