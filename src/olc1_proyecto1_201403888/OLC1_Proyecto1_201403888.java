/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package olc1_proyecto1_201403888;
import interfaz.FramePrincipal;

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
          java.awt.EventQueue.invokeLater(() -> {
            new FramePrincipal().setVisible(true);
        });
        /**
        try {
            String ruta = "prueba.btl";
            
            Lexer.listaTokens.clear();
            Lexer.listaErrores.clear();

            Lexer lexer = new Lexer(new FileReader(ruta));
            Parser parser = new Parser(lexer);

            parser.parse();
            
            System.out.println("===== TOKENS =====");

            for (modelo.Token t : Lexer.listaTokens) {
                System.out.println(
                    t.getToken()
                    + " | " + t.getLexema()
                    + " | Linea: " + t.getLinea()
                    + " | Columna: " + t.getColumna()
                );
            }

            System.out.println("===== ERRORES LEXICOS =====");

            for (modelo.ErrorToken error : Lexer.listaErrores) {
                System.out.println(
                    error.getTipo()
                    + " | " + error.getDescripcion()
                    + " | Linea: " + error.getLinea()
                    + " | Columna: " + error.getColumna()
                );
            }

            System.out.println("Analisis sintactico finalizado");
            System.out.println("Archivo aceptado correctamente");

        } catch (Exception e) {
            System.out.println("Error durante el analisis");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        */
    }
    
}
