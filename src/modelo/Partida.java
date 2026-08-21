/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Partida {
     private String nombre;
    private String jugador1;
    private String jugador2;
    private int rondas;
    private Scoring scoring;
    private Bonuses bonuses;

    public Partida(
            String nombre,
            String jugador1,
            String jugador2,
            int rondas,
            Scoring scoring,
            Bonuses bonuses) {

        this.nombre = nombre;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.rondas = rondas;
        this.scoring = scoring;
        this.bonuses = bonuses;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getJugador1() {
        return jugador1;
    }

    public String getJugador2() {
        return jugador2;
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

    @Override
    public String toString() {
        return "Partida{" +
                "nombre='" + nombre + '\'' +
                ", jugador1='" + jugador1 + '\'' +
                ", jugador2='" + jugador2 + '\'' +
                ", rondas=" + rondas +
                ", scoring=" + scoring +
                ", bonuses=" + bonuses +
                '}';
    }
    
}
