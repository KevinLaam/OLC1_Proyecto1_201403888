/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Regla {
    
    private Condicion condicion;
    private String accion;
    private boolean esElse;

    public Regla(Condicion condicion, String accion, boolean esElse) {
        this.condicion = condicion;
        this.accion = accion;
        this.esElse = esElse;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public String getAccion() {
        return accion;
    }

    public boolean isEsElse() {
        return esElse;
    }

    @Override
    public String toString() {
        if (esElse) {
            return "Regla{else -> " + accion + "}";
        }

        return "Regla{condicion='" + condicion
                + "', accion='" + accion + "'}";
    }
    
}
