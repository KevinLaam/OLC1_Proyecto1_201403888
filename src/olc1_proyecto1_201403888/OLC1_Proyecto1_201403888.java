/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package olc1_proyecto1_201403888;

/**
 *
 * @author Usuario
 */

import analizadores.Lexer;
import analizadores.Parser;
import java.io.FileReader;

public class OLC1_Proyecto1_201403888 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String ruta = "prueba.btl";

            Lexer lexer = new Lexer(new FileReader(ruta));
            Parser parser = new Parser(lexer);

            parser.parse();

            System.out.println("Analisis sintactico finalizado");
            System.out.println("Archivo aceptado correctamente");

        } catch (Exception e) {
            System.out.println("Error durante el analisis");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}
