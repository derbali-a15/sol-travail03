package com.example.travail03v2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

@Path("/solution")
public class NumerosResource {

    @GET
    @Path("/numero1")
    @Produces("text/plain")
    public String numero1() {
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();
        return "Bienvenue au cours d’applications orientées service , nous sommes le " + today + ", " + time;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/numero2/{temp}/{unite_source}")  //unite_source: Fahrenheit ou Celsius
    public String numero2(@PathParam("temp") int temp, @PathParam("unite_source") String uniteSource) {
        int result = 0;
        String uniteDestination = "";
        if (uniteSource.toLowerCase().equals("celsius")) {
            uniteDestination = "fahrenheit";
            result = (9 * temp + 160) / 5;
        } else if (uniteSource.toLowerCase().equals("fahrenheit")) {
            uniteDestination = "celsius";
            result = (5 * (temp - 32)) / 9;
        } else {
            return "Attention, unité erronée";
        }
        return temp + " " + uniteSource + " ==> " + result + " " + uniteDestination;
    }

    /*CONSTANTES*/
    public final LocalDate DEBUT_HIVER = LocalDate.of(LocalDate.now().getYear(), 12, 21);
    public final LocalDate FIN_HIVER = LocalDate.of(LocalDate.now().getYear(), 3, 22);
    public final LocalDate DEBUT_PRINTEMPS = LocalDate.of(LocalDate.now().getYear(), 3, 21);
    public final LocalDate FIN_PRINTEMPS = LocalDate.of(LocalDate.now().getYear(), 6, 22);
    public final LocalDate DEBUT_ETE = LocalDate.of(LocalDate.now().getYear(), 6, 21);
    public final LocalDate FIN_ETE = LocalDate.of(LocalDate.now().getYear(), 9, 22);
    public final LocalDate DEBUT_AUTOMNE = LocalDate.of(LocalDate.now().getYear(), 9, 21);
    public final LocalDate FIN_AUTOMNE = LocalDate.of(LocalDate.now().getYear(), 12, 22);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("numero3/{jour}/{mois}")  //jour: 1- 31  et mois:1 - 12
    public String numero3(@PathParam("jour") int jour, @PathParam("mois") int mois) {
        try {
            LocalDate date = LocalDate.of(LocalDate.now().getYear(), mois, jour);
            if (date.isAfter(DEBUT_PRINTEMPS) && date.isBefore(FIN_PRINTEMPS)) {
                int temp = getRandomNumber(5, 25);
                return "Jour en printemps, la température est " + temp + " celsius";
            } else if (date.isAfter(DEBUT_ETE) && date.isBefore(FIN_ETE)) {
                int temp = getRandomNumber(10, 35);
                return "Jour en été, la température est " + temp + " celsius";
            } else if (date.isAfter(DEBUT_AUTOMNE) && date.isBefore(FIN_AUTOMNE)) {
                int temp = getRandomNumber(-10, 15);
                return "Jour en automne, la température est " + temp + " celsius";
            } else if (date.isAfter(DEBUT_HIVER) || date.isBefore(FIN_HIVER)) {
                int temp = getRandomNumber(-20, 15);
                return "Jour en hiver, la température est " + temp + " celsius";
            }
        } catch (DateTimeException e) {
            return "jour ou date erroné !";
        }
        return null;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
