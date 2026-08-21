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
public class Bonuses {
    private LinkedList<String> mageCombo;
    private int mageComboPoints;

    private LinkedList<String> warriorCombo;
    private int warriorComboPoints;

    private int lowHealthVictory;

    public Bonuses(
            LinkedList<String> mageCombo,
            int mageComboPoints,
            LinkedList<String> warriorCombo,
            int warriorComboPoints,
            int lowHealthVictory) {

        this.mageCombo = mageCombo;
        this.mageComboPoints = mageComboPoints;
        this.warriorCombo = warriorCombo;
        this.warriorComboPoints = warriorComboPoints;
        this.lowHealthVictory = lowHealthVictory;
    }

    public LinkedList<String> getMageCombo() {
        return mageCombo;
    }

    public int getMageComboPoints() {
        return mageComboPoints;
    }

    public LinkedList<String> getWarriorCombo() {
        return warriorCombo;
    }

    public int getWarriorComboPoints() {
        return warriorComboPoints;
    }

    public int getLowHealthVictory() {
        return lowHealthVictory;
    }

    @Override
    public String toString() {
        return "Bonuses{" +
                "mageCombo=" + mageCombo +
                ", mageComboPoints=" + mageComboPoints +
                ", warriorCombo=" + warriorCombo +
                ", warriorComboPoints=" + warriorComboPoints +
                ", lowHealthVictory=" + lowHealthVictory +
                '}';
    }
    
}
