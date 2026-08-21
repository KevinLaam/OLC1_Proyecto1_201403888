/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Condicion {
    
    private Object izquierda;
    private String operador;
    private Object derecha;

    public Condicion(
            Object izquierda,
            String operador,
            Object derecha) {

        this.izquierda = izquierda;
        this.operador = operador;
        this.derecha = derecha;
    }

    public Object getIzquierda() {
        return izquierda;
    }

    public String getOperador() {
        return operador;
    }

    public Object getDerecha() {
        return derecha;
    }
    
    @Override
    public String toString() {
        return "Condicion{" +
                "izquierda=" + izquierda +
                ", operador='" + operador + '\'' +
                ", derecha=" + derecha +
                '}';
    }
    
}
