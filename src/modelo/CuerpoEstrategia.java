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
public class CuerpoEstrategia {
    
    private String accionInicial;
    private LinkedList<Regla> reglas;

    public CuerpoEstrategia(String accionInicial, LinkedList<Regla> reglas) {
        this.accionInicial = accionInicial;
        this.reglas = reglas;
    }

    public String getAccionInicial() {
        return accionInicial;
    }

    public LinkedList<Regla> getReglas() {
        return reglas;
    }
    
}
