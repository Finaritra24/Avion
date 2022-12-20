package com.example.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Log;
import com.example.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{
    /**
     * @param log
     */
    List<Token> findByLogOrderByIdTokenDesc(Log log);
    List<Token> findByLog(Log log);
    Token findByToken(String token);
    void  deleteByLog(Log log);
    Token findByIdToken(Long id);
}
