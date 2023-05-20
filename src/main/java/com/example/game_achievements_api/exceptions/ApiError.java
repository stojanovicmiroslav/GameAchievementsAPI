package com.example.game_achievements_api.exceptions;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
//@AllArgsConstructor // kada koristimo builder nije dobro koristii Allargsconstructor, zato sto
// po definiciji programiranja, builder, ne smemo da imamo public construktore kada korisitmo buildere
// uvek trebamo da napravimo rucno privatan contruktor
// U OVOM SLUCAJU mora da bude PRiVATAN
public class ApiError {

    private ApiError(LocalDate localDate, String status, String error, String message, String path) {
        this.localDate = localDate;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    private LocalDate localDate;
    private String status;
    private String error;
    private String message;
    private String path;




       // DEFAULT Message od Springboot
    /*
        "timestamp": "2023-04-10T16:52:55.453+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/api/v1/gallery/get-al"
     */

}
