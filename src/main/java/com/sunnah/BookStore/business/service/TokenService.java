package com.sunnah.BookStore.business.service;

import com.sunnah.BookStore.data.Entity.Token;
import com.sunnah.BookStore.data.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void saveToken(Token confirmationToken) {

        tokenRepository.save(confirmationToken);
    }

    void deleteToken(Long id){

        tokenRepository.deleteById(id);
    }

}
