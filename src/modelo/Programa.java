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
public class Programa {
     private LinkedList<Estrategia> estrategias;
    private LinkedList<Partida> partidas;
    private Principal principal;

    public Programa(
            LinkedList<Estrategia> estrategias,
            LinkedList<Partida> partidas,
            Principal principal) {

        this.estrategias = estrategias;
        this.partidas = partidas;
        this.principal = principal;
    }

    public LinkedList<Estrategia> getEstrategias() {
        return estrategias;
    }

    public LinkedList<Partida> getPartidas() {
        return partidas;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public Estrategia buscarEstrategia(String nombre) {

        for (Estrategia estrategia : estrategias) {

            if (estrategia.getNombre().equals(nombre)) {
                return estrategia;
            }
        }

        return null;
    }

    public Partida buscarPartida(String nombre) {

        for (Partida partida : partidas) {

            if (partida.getNombre().equals(nombre)) {
                return partida;
            }
        }

        return null;
    }
    
}
