/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;
import analizadores.Lexer;
import analizadores.Parser;

import java.io.StringReader;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Usuario
 */
public class FramePrincipal extends JFrame{
    private JTextArea editor;
    private JTextArea consola;
    
    private JTable tablaTokens;
    private DefaultTableModel modeloTokens;
    private JTable tablaErrores;
    private DefaultTableModel modeloErrores;
    private JButton btnAbrir;
    private JButton btnAnalizar;
    private JButton btnLimpiar;
    
    public FramePrincipal() {

        setTitle("Battle Language - OLC1");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        inicializarComponentes();
    }
    
     private void inicializarComponentes() {

        // ---------------- BOTONES ----------------
        btnAbrir = new JButton("Abrir .btl");
        btnAnalizar = new JButton("Analizar");
        btnLimpiar = new JButton("Limpiar");
        btnAnalizar.addActionListener(e -> analizar());

        JPanel panelBotones = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
        );

        panelBotones.add(btnAbrir);
        panelBotones.add(btnAnalizar);
        panelBotones.add(btnLimpiar);

        // ---------------- EDITOR ----------------
        editor = new JTextArea();
        editor.setTabSize(4);

        JScrollPane scrollEditor =
                new JScrollPane(editor);

        // ---------------- CONSOLA ----------------
        consola = new JTextArea();
        consola.setEditable(false);

        JScrollPane scrollConsola =
                new JScrollPane(consola);
        //------------------TABLA TOKENS------------
        modeloTokens = new DefaultTableModel(
        new Object[]{"No.", "Token", "Lexema", "Linea", "Columna"},
        0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaTokens = new JTable(modeloTokens);

        JScrollPane scrollTokens =
                new JScrollPane(tablaTokens);
        
        //----------------TABLA ERRORES
        modeloErrores = new DefaultTableModel(
        new Object[]{"No.", "Tipo", "Descripcion", "Linea", "Columna"},
        0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaErrores = new JTable(modeloErrores);

        JScrollPane scrollErrores =
                new JScrollPane(tablaErrores);
        

        // ---------------- CENTRO ----------------
        JPanel panelCentro = new JPanel(
                new GridLayout(4, 1, 5, 5)
        );

        panelCentro.add(scrollEditor);
        panelCentro.add(scrollConsola);
        panelCentro.add(scrollTokens);
        panelCentro.add(scrollErrores);
        

        // ---------------- FRAME ----------------
        setLayout(new BorderLayout(5, 5));

        add(panelBotones, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        
        btnAbrir.addActionListener(e -> abrirArchivo());

        btnLimpiar.addActionListener(e -> limpiar());
    }
     
    private void abrirArchivo() {

           JFileChooser selector = new JFileChooser();

           FileNameExtensionFilter filtro =
                   new FileNameExtensionFilter(
                           "Archivos Battle Language (*.btl)",
                           "btl"
                   );

           selector.setFileFilter(filtro);

           int resultado = selector.showOpenDialog(this);

           if (resultado == JFileChooser.APPROVE_OPTION) {

               File archivo = selector.getSelectedFile();

               try (BufferedReader br =
                       new BufferedReader(new FileReader(archivo))) {

                   editor.setText("");

                   String linea;

                   while ((linea = br.readLine()) != null) {
                       editor.append(linea + "\n");
                   }

                   consola.setText(
                           "Archivo cargado: "
                           + archivo.getName()
                           + "\n"
                   );

               } catch (Exception ex) {

                   JOptionPane.showMessageDialog(
                           this,
                           "No se pudo abrir el archivo:\n"
                           + ex.getMessage(),
                           "Error",
                           JOptionPane.ERROR_MESSAGE
                   );
               }
           }
       }

    private void limpiar() {

            editor.setText("");
            consola.setText("");
            
            modeloTokens.setRowCount(0);
            modeloErrores.setRowCount(0);
    }
        
    private void analizar() {

        String entrada = editor.getText();

        if (entrada.trim().isEmpty()) {
            consola.setText("No hay codigo para analizar.\n");
            return;
        }

        // LIMPIAR RESULTADOS DE ANALISIS ANTERIORES
        Lexer.listaTokens.clear();
        Lexer.listaErrores.clear();

        try {

            // EL LEXER TOMA EL TEXTO DIRECTAMENTE DEL EDITOR
            Lexer lexer = new Lexer(
                    new StringReader(entrada)
            );

            Parser parser = new Parser(lexer);

            parser.parse();

        } catch (Exception ex) {

            // No reemplazamos la consola aquí,
            // porque queremos mostrar el resumen de errores abajo.
            System.out.println(
                    "El parser finalizo con errores: "
                    + ex.getMessage()
            );
        }

        // IMPORTANTE:
        // Se llenan las tablas aunque el parser haya fallado
        llenarTablaTokens();
        llenarTablaErrores();

        // CONTAR ERRORES POR TIPO
        int erroresLexicos = 0;
        int erroresSintacticos = 0;

        for (modelo.ErrorToken error : Lexer.listaErrores) {

            if (error.getTipo().equals("LEXICO")) {
                erroresLexicos++;
            }

            if (error.getTipo().equals("SINTACTICO")) {
                erroresSintacticos++;
            }
        }

        // ---------------- RESULTADO ----------------

        StringBuilder salida = new StringBuilder();

        salida.append("===== ANALISIS FINALIZADO =====\n");

        salida.append(
                "Tokens reconocidos: "
                + Lexer.listaTokens.size()
                + "\n"
        );

        salida.append(
                "Errores lexicos: "
                + erroresLexicos
                + "\n"
        );

        salida.append(
                "Errores sintacticos: "
                + erroresSintacticos
                + "\n"
        );

        if (Lexer.listaErrores.isEmpty()) {

            salida.append(
                    "Archivo aceptado correctamente.\n"
            );

        } else {

            salida.append(
                    "El archivo contiene errores.\n"
            );
        }

        consola.setText(salida.toString());
    }
    
    //-------------LLENAR TOKENS Y ERRORES
    private void llenarTablaTokens() {

    modeloTokens.setRowCount(0);

    int numero = 1;

        for (modelo.Token token : Lexer.listaTokens) {

            modeloTokens.addRow(
                    new Object[]{
                        numero,
                        token.getToken(),
                        token.getLexema(),
                        token.getLinea(),
                        token.getColumna()
                    }
            );

            numero++;
        }
    }
    
    private void llenarTablaErrores() {

    modeloErrores.setRowCount(0);

    int numero = 1;

        for (modelo.ErrorToken error : Lexer.listaErrores) {

            modeloErrores.addRow(
                    new Object[]{
                        numero,
                        error.getTipo(),
                        error.getDescripcion(),
                        error.getLinea(),
                        error.getColumna()
                    }
            );

            numero++;
        }
    }
    
    

}
