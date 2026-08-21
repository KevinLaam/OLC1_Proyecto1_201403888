/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Scoring {
    private int damagePoint;
    private int healingPoint;
    private int successfulDefense;
    private int victoryBonus;
    private int failedActionPenalty;

    public Scoring(
            int damagePoint,
            int healingPoint,
            int successfulDefense,
            int victoryBonus,
            int failedActionPenalty) {

        this.damagePoint = damagePoint;
        this.healingPoint = healingPoint;
        this.successfulDefense = successfulDefense;
        this.victoryBonus = victoryBonus;
        this.failedActionPenalty = failedActionPenalty;
    }

    public int getDamagePoint() {
        return damagePoint;
    }

    public int getHealingPoint() {
        return healingPoint;
    }

    public int getSuccessfulDefense() {
        return successfulDefense;
    }

    public int getVictoryBonus() {
        return victoryBonus;
    }

    public int getFailedActionPenalty() {
        return failedActionPenalty;
    }

    @Override
    public String toString() {
        return "Scoring{" +
                "damagePoint=" + damagePoint +
                ", healingPoint=" + healingPoint +
                ", successfulDefense=" + successfulDefense +
                ", victoryBonus=" + victoryBonus +
                ", failedActionPenalty=" + failedActionPenalty +
                '}';
    }
    
}
