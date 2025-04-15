package com.amar.api.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/secure-api")
public class SecureController {

    @RequestMapping
    public Map sayHello(){
        return Map.of("message", "This endpoint will provide banklist");
    }

    @GetMapping
    @RequestMapping("/accounts")
    //Public API
    public Map getAccounts(@RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7);

        DecodedJWT decodedJWT = JWT.decode(token);
        String claims = decodedJWT.getClaims().get("scope").toString();
        if (claims.contains("ACCOUNTS")){
            return Map.of("message", "This endpoint will provides list of accounts");
        }else{
            return Map.of("message", "Sorry ! you do not have enough previleges");
        }
        //return "This restricted endpoint returns all accounts - admin only ";
    }

    @GetMapping
    // admin can access (admin)
    @RequestMapping("/account/balances")
    public Map getBalances(@RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7);

        DecodedJWT decodedJWT = JWT.decode(token);
        String claims = decodedJWT.getClaims().get("scope").toString();
        if (claims.contains("ACCOUNT_BALANCES")){
            return Map.of("message", "This endpoint will provides balance for an accounts");
        }else{
            return Map.of("message", "Sorry ! you do not have enough previleges");
        }

    }

    @GetMapping
    // admin can access (admin)
    @RequestMapping("/account/transactions")
    public Map getTransactions(@RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7);

        DecodedJWT decodedJWT = JWT.decode(token);
        String claims = decodedJWT.getClaims().get("scope").toString();
        if (claims.contains("TRANSACTIONS")){
            return Map.of("message", "This endpoint will provide transaction on an account");
        }else{
            return Map.of("message", "Sorry ! you do not have enough previleges");
        }

    }



}
