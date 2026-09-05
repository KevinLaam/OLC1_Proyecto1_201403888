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
    private LinkedList<InstruccionRun> runs;

    public Principal(
            LinkedList<InstruccionRun> runs) {

        this.runs = runs;
    }

    public LinkedList<InstruccionRun> getRuns() {
        return runs;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "runs=" + runs +
                '}';
    }
    
}
