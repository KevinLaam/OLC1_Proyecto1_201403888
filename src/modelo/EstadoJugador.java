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
public class EstadoJugador {
    
     private String nombre;
    private String tipo;
    private int ataqueFisico;
    private int poderMagico;
    private int armadura;
    private int resistenciaMagica;
    private int vida;
    private int recurso;
    private int score;
    
    private int velocidad;
    
    
    private boolean defendiendo;
    private boolean mejoraAtaque;

    private LinkedList<String> historial;

    public EstadoJugador(String nombre, String tipo) {

        this.nombre = nombre;
        this.tipo = tipo;

        //this.vida = 100;
        //this.recurso = 100;
        if (tipo.equalsIgnoreCase("mage")) {

            this.vida = 100;
            this.recurso = 120;
            
            this.ataqueFisico = 8;
            this.poderMagico = 25;
            this.armadura = 8;
            this.resistenciaMagica = 18;
            this.velocidad = 14;

        } else if (tipo.equalsIgnoreCase("warrior")) {

            this.vida = 140;
            this.recurso = 100;
            
            this.ataqueFisico = 22;
            this.poderMagico = 0;
            this.armadura = 20;
            this.resistenciaMagica = 8;
            this.velocidad = 10;
        }
        
        this.score = 0;
        //para combos
        this.historial = new LinkedList<>();
        
        this.defendiendo = false;
        this.mejoraAtaque = false;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getRecurso() {
        return recurso;
    }

    public int getScore() {
        return score;
    }

    public LinkedList<String> getHistorial() {
        return historial;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void agregarMovimiento(String accion) {
        historial.add(accion);
    }
    
    public boolean isDefendiendo() {
    return defendiendo;
    }

    public void setDefendiendo(boolean defendiendo) {
        this.defendiendo = defendiendo;
    }
    
    public boolean isMejoraAtaque() {
    return mejoraAtaque;
    }

    public void setMejoraAtaque(boolean mejoraAtaque) {
        this.mejoraAtaque = mejoraAtaque;
    }
    
    public int getAtaqueFisico() {
    return ataqueFisico;
    }

    public int getPoderMagico() {
        return poderMagico;
    }

    public int getArmadura() {
        return armadura;
    }

    public int getResistenciaMagica() {
        return resistenciaMagica;
    }

    public int getVelocidad() {
        return velocidad;
    }
    
    // PARA PUNTOS
    public void sumarScore(int puntos) {
    this.score += puntos;
    }
    public void restarScore(int puntos) {
    this.score -= puntos;
    }
  
    
    @Override
    public String toString() {
        return "EstadoJugador{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", vida=" + vida +
                ", recurso=" + recurso +
                ", score=" + score +
                ", historial=" + historial +
                ", defendiento" + defendiendo +
                 ", mejoraAtaque" + mejoraAtaque +
                '}';
    }
    
}
