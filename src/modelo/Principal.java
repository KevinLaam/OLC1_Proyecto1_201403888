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
public class Principal {
    private LinkedList<String> partidas;
    private int seed;

    public Principal(LinkedList<String> partidas, int seed) {
        this.partidas = partidas;
        this.seed = seed;
    }

    public LinkedList<String> getPartidas() {
        return partidas;
    }

    public int getSeed() {
        return seed;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "partidas=" + partidas +
                ", seed=" + seed +
                '}';
    }
    
}
