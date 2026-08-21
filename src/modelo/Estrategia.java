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
public class Estrategia {
    
    private String nombre;
    private String tipo;
    private String accionInicial;
    private LinkedList<Regla> reglas;
    
    public Estrategia(String nombre, String tipo, String accionInicial) {
    this.nombre = nombre;
    this.tipo = tipo;
    this.accionInicial = accionInicial;
    this.reglas = new LinkedList<>();
    }

    public Estrategia(String nombre, String tipo, String accionInicial,LinkedList<Regla> reglas ) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.accionInicial = accionInicial;
        this.reglas = reglas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAccionInicial() {
        return accionInicial;
    }

     public LinkedList<Regla> getReglas() {
        return reglas;
    }
     
    @Override
    public String toString() {
        return "Estrategia{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", accionInicial='" + accionInicial + '\'' +
                ", reglas=" + reglas +
                '}';
    }
    
}
