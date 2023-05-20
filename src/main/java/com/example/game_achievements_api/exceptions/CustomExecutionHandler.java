package com.example.game_achievements_api.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import java.time.LocalDate;

@RestControllerAdvice  // ovo je od springboot
public class CustomExecutionHandler {

    // mogu da vatam sve greske od Exception ali to nije dobro,
    // bolje je da vatam samo najbitnije greske

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public ApiError notFound (String servletPath, String message, String localizedMessage){

        return ApiError.builder() // builder je staticka metoda
                .path(servletPath)
                .localDate(LocalDate.now())
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .error(localizedMessage)
                .message(message)
                .build(); // metoda build() poziva privatni konstruktor nase klase i samim time vraca isti objekat
                          // sa setovanim podacima
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRequest() {
    return "Bad Request";
    }





}
