/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class CuerpoPartida {
    private LinkedList<String> jugadores;
    private int rondas;
    private Scoring scoring;
    private Bonuses bonuses;

    public CuerpoPartida(
            LinkedList<String> jugadores,
            int rondas,
            Scoring scoring,
            Bonuses bonuses) {

        this.jugadores = jugadores;
        this.rondas = rondas;
        this.scoring = scoring;
        this.bonuses = bonuses;
    }

    public LinkedList<String> getJugadores() {
        return jugadores;
    }

    public int getRondas() {
        return rondas;
    }

    public Scoring getScoring() {
        return scoring;
    }

    public Bonuses getBonuses() {
        return bonuses;
    }
    
}
