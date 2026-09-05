/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.LinkedList;
import java.util.Random;
/**
 *
 * @author Usuario
 */

public class Batalla {
    private Partida partida;
    private Estrategia estrategiaJugador1;
    private Estrategia estrategiaJugador2;
    private EstadoJugador jugador1;
    private EstadoJugador jugador2;
    //para seed 
    private Random randomJugador1;
    private Random randomJugador2;
    private int seed;
    private StringBuilder salida = new StringBuilder();

    public Batalla(
            Partida partida,
            Estrategia estrategiaJugador1,
            Estrategia estrategiaJugador2,
            int seed) {

        this.partida = partida;
        this.estrategiaJugador1 = estrategiaJugador1;
        this.estrategiaJugador2 = estrategiaJugador2;
        this.seed = seed;

        this.jugador1 = new EstadoJugador(
                estrategiaJugador1.getNombre(),
                estrategiaJugador1.getTipo()
        );

        this.jugador2 = new EstadoJugador(
                estrategiaJugador2.getNombre(),
                estrategiaJugador2.getTipo()
        );
        
        //jugador1.setRecurso(20);
    }

    public void iniciar() {
        salida.setLength(0);
        
        imprimir("===== INICIO DE BATALLA =====");
        imprimir("Partida: " + partida.getNombre());
        imprimir("Rondas: " + partida.getRondas());
        imprimir("Seed: " + seed);
        randomJugador1 = new Random(seed);
        randomJugador2 = new Random(seed + 1);
        imprimir("");

        imprimir("Jugador 1:");
        imprimir(jugador1.toString());

        System.out.println();

        imprimir("Jugador 2:");
        imprimir(jugador2.toString());

        imprimir("============================");
        
        
        // EJECUTAR RONDAS
       
        //jugador1.setRecurso(20);
        //jugador2.setRecurso(20);
        
        for (int ronda = 1; ronda <= partida.getRondas(); ronda++) {
            //pra randoms seed
            double valorRandom1 = 0.0;
            double valorRandom2 = 0.0;
            //jugador1.setDefendiendo(false);
            //jugador2.setDefendiendo(false);
            
            imprimir(
                    "===== RONDA " + ronda + " ====="
            );

            String accionJugador1;
            String accionJugador2;

            // En la primera ronda usamos
            // la acción inicial de cada estrategia
            if (ronda == 1) {
                
                accionJugador1 = estrategiaJugador1.getAccionInicial();
                //accionJugador2 = "SLASH";
                accionJugador2 = estrategiaJugador2.getAccionInicial();
             /*   
            } else {
                 if (ronda == 2) {

                jugador1.setVida(20);

 

                accionJugador1 = "HEALING_RUNE";
                accionJugador2 = "HEAVY_STRIKE";
                        
            }  else if (ronda == 3) {

                accionJugador1 = "ARCANE_BOLT";
                accionJugador2 = "HEAVY_STRIKE";
              */   
            }  else {
                
            /* PRUEBAS TEMPORALES DE WAR_CRY
            if (ronda == 2) {

              jugador1.setRecurso(70);
                jugador1.setRecurso(90);
                accionJugador1 = "HEALING_RUNE";
                accionJugador2 = "REST";
                

            } else {

                accionJugador1 = "ARCANE_BOLT";
                accionJugador2 = "HEAVY_STRIKE";
            }    
                
            //prruebas para las rondas 
            ///accionJugador1 = "ARCANE_BOLT";
            //accionJugador2 = "WAR_CRY";
            
            //accionJugador2 = "SHIELD_BLOCK";
            //accionJugador1 = "MAGIC_BARRIER";
            //accionJugador1 = "ARCANE_BOLT";
            //accionJugador2 = "SLASH";
            */    
            //random de seed para la 2da ronda
            valorRandom1 = randomJugador1.nextDouble();
            valorRandom2 = randomJugador2.nextDouble();
            
            imprimir("Random " + jugador1.getNombre() + ": " + valorRandom1);

            imprimir("Random " + jugador2.getNombre() + ": " + valorRandom2);
            
            accionJugador1 = seleccionarAccion(
                    estrategiaJugador1,
                    jugador1,
                    jugador2,
                    valorRandom1,
                    ronda -1,
                    partida.getRondas()
            );

            accionJugador2 = seleccionarAccion(
                    estrategiaJugador2,
                    jugador2,
                    jugador1,
                    valorRandom2,
                    ronda -1,
                    partida.getRondas()
            ); 
            //aca comentar y descomentar para pruebas
         //}
      }
            
            imprimir(
                    jugador1.getNombre()
                    + " selecciona: "
                    + accionJugador1
            );

            imprimir(
                    jugador2.getNombre()
                    + " selecciona: "
                    + accionJugador2
            );
            
            //jugador1.agregarMovimiento(accionJugador1);
            //jugador2.agregarMovimiento(accionJugador2);
            
            //verificarMageCombo(jugador1, partida);
            //verificarMageCombo(jugador2, partida);
            
            //verificarWarriorCombo(jugador1, partida);
            //verificarWarriorCombo(jugador2, partida);
            /*
            ejecutarAccion(
                    accionJugador1,
                    jugador1,
                    jugador2
            );

            ejecutarAccion(
                    accionJugador2,
                    jugador2,
                    jugador1
            );
            
            */
            
            int prioridad1 =
                    obtenerPrioridad(accionJugador1);

            int prioridad2 =
                    obtenerPrioridad(accionJugador2);
            
            if (prioridad1 > prioridad2) {

                ejecutarAccion(
                        accionJugador1,
                        jugador1,
                        jugador2
                );

                 // Si jugador2 sobrevivió, puede actuar
                if (jugador2.getVida() > 0) {

                    ejecutarAccion(
                            accionJugador2,
                            jugador2,
                            jugador1
                    );
                }

            } else if (prioridad2 > prioridad1) {

                ejecutarAccion(
                        accionJugador2,
                        jugador2,
                        jugador1
                );

                 // Si jugador1 sobrevivió, puede actuar
                if (jugador1.getVida() > 0) {

                    ejecutarAccion(
                            accionJugador1,
                            jugador1,
                            jugador2
                    );
                }

            } else {

                // Misma prioridad -> desempatar por velocidad
                int velocidad1 = jugador1.getVelocidad();
                int velocidad2 = jugador2.getVelocidad();

                if (velocidad1 > velocidad2) {

                    ejecutarAccion(
                            accionJugador1,
                            jugador1,
                            jugador2
                    );

                    // Jugador 2 solo actúa si sigue vivo
                    if (jugador2.getVida() > 0) {

                        ejecutarAccion(
                                accionJugador2,
                                jugador2,
                                jugador1
                        );
                    }
                    
                    imprimir(
                            "Empate de prioridad -> "
                            + jugador1.getNombre()
                            + " actua primero por velocidad"
                    );

                } else if (velocidad2 > velocidad1) {

                    ejecutarAccion(
                            accionJugador2,
                            jugador2,
                            jugador1
                    );

                    // Jugador 1 solo actúa si sigue vivo
                    if (jugador1.getVida() > 0) {

                        ejecutarAccion(
                                accionJugador1,
                                jugador1,
                                jugador2
                        );
                    }

                } else {

                    // Misma prioridad + misma velocidad
                    // Jugador 1 actúa primero
                    ejecutarAccion(
                            accionJugador1,
                            jugador1,
                            jugador2
                    );

                    if (jugador2.getVida() > 0) {

                        ejecutarAccion(
                                accionJugador2,
                                jugador2,
                                jugador1
                        );
                    }
                }
            }
           
            //victoria directa por vida = 0 antes de que termine la partida
            if (jugador1.getVida() <= 0
                    || jugador2.getVida() <= 0) {

                imprimir(
                        "La batalla termino por derrota directa."
                );

                break;
            }

            imprimir(
                "Estado despues de la ronda:"
            );

            imprimir(jugador1.toString());
            imprimir(jugador2.toString());
            
            // Los efectos defensivos duran solamente esta ronda
            jugador1.setDefendiendo(false);
            jugador2.setDefendiendo(false);

                // Temporalmente seguimos utilizando
                // la acción inicial.
                // Después aquí evaluaremos las reglas.

                //accionJugador1 =
                  //      estrategiaJugador1.getAccionInicial();

                //accionJugador2 =
                  //      estrategiaJugador2.getAccionInicial();
            

            imprimir("");
        }
        /*
        jugador1.setScore(30);
        jugador2.setScore(30);

        jugador1.setVida(50);
        jugador2.setVida(50);

        jugador1.setRecurso(50);
        jugador2.setRecurso(50);
        */
        // FINALIZAR BATALLA
        //prueba para low_healt victori----
        //jugador1.setVida(20);
        //jugador2.setVida(30);
        determinarGanador();
        imprimir(
                "===== ESTADO FINAL ====="
        );

        imprimir(jugador1.toString());
        imprimir(jugador2.toString());

        imprimir(
                "==============================="
        );
    
    }
        private String seleccionarAccion(
            Estrategia estrategia,
            EstadoJugador propio,
            EstadoJugador oponente,
            double valorRandom,
            int roundNumber,
            int totalRounds) {

        for (Regla regla : estrategia.getReglas()) {

            // Si es ELSE, se utiliza si ninguna
            // condición anterior se cumplió
            if (regla.isEsElse()) {
                return regla.getAccion();
            }

            Condicion condicion = regla.getCondicion();

            if (condicion != null) {

                boolean cumple = evaluarCondicion(
                        condicion,
                        propio,
                        oponente,
                        valorRandom,
                        roundNumber,
                        totalRounds
                );

                if (cumple) {
                    return regla.getAccion();
                }
            }
        }

        // Seguridad: si por alguna razón ninguna
        // regla aplica, usamos la acción inicial.
        return estrategia.getAccionInicial();
    }
    
        private boolean evaluarCondicion(
            Condicion condicion,
            EstadoJugador propio,
            EstadoJugador oponente,
            double valorRandom,
            int roundNumber,
            int totalRounds) {
            
        //corto circuito
        if (condicion.getOperador().equals("AND")) {

            Condicion izquierda =
                    (Condicion) condicion.getIzquierda();

            Condicion derecha =
                    (Condicion) condicion.getDerecha();

            boolean resultadoIzquierda =
                    evaluarCondicion(
                            izquierda,
                            propio,
                            oponente,
                            valorRandom,
                            roundNumber,
                            totalRounds
                    );

            // CORTO CIRCUITO &&
            if (!resultadoIzquierda) {
                return false;
            }

            return evaluarCondicion(
                    derecha,
                    propio,
                    oponente,
                    valorRandom,
                    roundNumber,
                    totalRounds
            );
        }
        
        if (condicion.getOperador().equals("OR")) {

            Condicion izquierda =
                    (Condicion) condicion.getIzquierda();

            Condicion derecha =
                    (Condicion) condicion.getDerecha();

            boolean resultadoIzquierda =
                    evaluarCondicion(
                            izquierda,
                            propio,
                            oponente,
                            valorRandom,
                            roundNumber,
                            totalRounds
                    );

            // CORTO CIRCUITO ||
            if (resultadoIzquierda) {
                return true;
            }

            return evaluarCondicion(
                    derecha,
                    propio,
                    oponente,
                    valorRandom,
                    roundNumber,
                    totalRounds
            );
        }
        

            
            

        String estadoIzquierda = condicion.getIzquierda().toString();
        //para last move
          if (estadoIzquierda.startsWith("LAST_MOVE:")) {

            String tipoHistorial =
                    estadoIzquierda.substring(
                            "LAST_MOVE:".length()
                    );

            LinkedList<String> historial;

            if (tipoHistorial.equalsIgnoreCase("self_history")) {

                historial = propio.getHistorial();

            } else if (tipoHistorial.equalsIgnoreCase("opponent_history")) {

                historial = oponente.getHistorial();

            } else {

                return false;
            }

            String ultimaAccion =
                    lastMove(historial);

            String accionDerecha =
                    condicion.getDerecha().toString();

            switch (condicion.getOperador()) {

                case "==":
                    return ultimaAccion.equals(accionDerecha);

                case "!=":
                    return !ultimaAccion.equals(accionDerecha);

                default:
                    return false;
            }
        }
        // para get move
        if (estadoIzquierda.startsWith("GET_MOVE:")) {

            String contenido =
                    estadoIzquierda.substring(
                            "GET_MOVE:".length()
                    );

            String[] partes =
                    contenido.split(":");

            String tipoHistorial = partes[0];
            int indice = Integer.parseInt(partes[1]);

            LinkedList<String> historial;

            if (tipoHistorial.equalsIgnoreCase("self_history")) {

                historial = propio.getHistorial();

            } else if (tipoHistorial.equalsIgnoreCase("opponent_history")) {

                historial = oponente.getHistorial();

            } else {

                return false;
            }

            String accionObtenida =
                    getMove(historial, indice);

            String accionDerecha =
                    condicion.getDerecha().toString();

            switch (condicion.getOperador()) {

                case "==":
                    return accionObtenida.equals(accionDerecha);

                case "!=":
                    return !accionObtenida.equals(accionDerecha);

                default:
                    return false;
            }
        }
       
        //para get n moves 
        if (estadoIzquierda.startsWith("GET_LAST_N_MOVES:")) {

            String contenido =
                    estadoIzquierda.substring(
                            "GET_LAST_N_MOVES:".length()
                    );

            String[] partes = contenido.split(":");

            String tipoHistorial = partes[0];
            int n = Integer.parseInt(partes[1]);

            LinkedList<String> historial;

            if (tipoHistorial.equalsIgnoreCase("self_history")) {

                historial = propio.getHistorial();

            } else if (tipoHistorial.equalsIgnoreCase("opponent_history")) {

                historial = oponente.getHistorial();

            } else {

                return false;
            }

            LinkedList<String> ultimos =
                    getLastNMoves(historial, n);

            String derecha =
                    condicion.getDerecha().toString();

            if (!derecha.startsWith("LISTA:")) {
                return false;
            }

            String contenidoLista =
                    derecha.substring("LISTA:".length());

            LinkedList<String> listaComparar =
                    new LinkedList<>();

            if (!contenidoLista.isEmpty()) {

                String[] acciones =
                        contenidoLista.split(",");

                for (String accion : acciones) {
                    listaComparar.add(accion);
                }
            }

            switch (condicion.getOperador()) {

                case "==":
                    return ultimos.equals(listaComparar);

                case "!=":
                    return !ultimos.equals(listaComparar);

                default:
                    return false;
            }
        }
        double izquierda;
        //para get count
        if (estadoIzquierda.startsWith("GET_MOVES_COUNT:")) {

            String contenido =
                    estadoIzquierda.substring(
                            "GET_MOVES_COUNT:".length()
                    );

            String fuente;
            String accionBuscada;

            // Caso anidado
            if (contenido.startsWith("GET_LAST_N_MOVES:")) {

                int ultimoDosPuntos =
                        contenido.lastIndexOf(":");

                fuente =
                        contenido.substring(
                                0,
                                ultimoDosPuntos
                        );

                accionBuscada =
                        contenido.substring(
                                ultimoDosPuntos + 1
                        );

            } else {

                // Caso simple
                String[] partes =
                        contenido.split(":");

                fuente = partes[0];
                accionBuscada = partes[1];
            }

            LinkedList<String> historial =
                    resolverFuenteHistorial(
                            fuente,
                            propio,
                            oponente
                    );

            izquierda =
                    getMovesCount(
                            historial,
                            accionBuscada
                    );

        } else if (estadoIzquierda.equalsIgnoreCase("random")) {

            izquierda = valorRandom;

        } else if (estadoIzquierda.equalsIgnoreCase("round_number")) {

            izquierda = roundNumber;

        } else if (estadoIzquierda.equalsIgnoreCase("total_rounds")) {

            izquierda = totalRounds;

        } else {

            izquierda = obtenerValorEstado(
                    estadoIzquierda,
                    propio,
                    oponente
            );
        }

        // Ahora aceptamos enteros y decimales
        double derecha = Double.parseDouble(
                condicion.getDerecha().toString()
        );

        switch (condicion.getOperador()) {

            case "==":
                return izquierda == derecha;

            case "!=":
                return izquierda != derecha;

            case ">":
                return izquierda > derecha;

            case "<":
                return izquierda < derecha;

            case ">=":
                return izquierda >= derecha;

            case "<=":
                return izquierda <= derecha;

            default:
                return false;
        }
    }
        
    private int obtenerValorEstado(String estado,EstadoJugador propio,EstadoJugador oponente) {
        switch (estado) {

            case "SELF_HEALTH":
                return propio.getVida();

            case "OPPONENT_HEALTH":
                return oponente.getVida();

            case "SELF_RESOURCE":
                return propio.getRecurso();

            case "OPPONENT_RESOURCE":
                return oponente.getRecurso();

            case "SELF_SCORE":
                return propio.getScore();

            case "OPPONENT_SCORE":
                return oponente.getScore();
            case "self_score":
                return propio.getScore();

            case "opponent_score":
                return oponente.getScore();    
                

            default:
                return 0;
        }
    }
    
    private void ejecutarAccion(
        String accion,
        EstadoJugador atacante,
        EstadoJugador defensor) {

    switch (accion) {

        case "ARCANE_BOLT":
            //defensor.setVida(defensor.getVida() - 10);
            //atacante.setRecurso(atacante.getRecurso() - 10);
            //break;
             if (atacante.getRecurso() >= 10) {

                atacante.setRecurso(
                        atacante.getRecurso() - 10
                );

                //int dano = 12;
                
                int dano = calcularDanoMagico(
                        atacante,
                        defensor,
                        12
                );

                if (defensor.isDefendiendo()) {
                    dano = dano / 2;
                    //PUNTOS POR DEFENSA 
                    defensor.sumarScore(
                            partida.getScoring().getSuccessfulDefense()
                    );
                   

                }
                
                // no queda vida quede negativa
                defensor.setVida(
                        Math.max(0, defensor.getVida() - dano)
                );

                
                atacante.sumarScore(
                         partida.getScoring().getDamagePoint()
                );
                
                // SOLO si la acción fue exitosa
                registrarAccionExitosa(
                        atacante,
                        accion,
                        partida
                );
            } else {

                atacante.restarScore(
                        partida.getScoring().getFailedActionPenalty()
                );
            }   
            break;

        case "FIREBALL":
            //defensor.setVida(defensor.getVida() - 20);
            //atacante.setRecurso(atacante.getRecurso() - 20);
            if (atacante.getRecurso() >= 30) {

                atacante.setRecurso(
                        atacante.getRecurso() - 30
                );
                //int dano = 25;               
                int dano = calcularDanoMagico(
                        atacante,
                        defensor,
                        25
                );

                if (defensor.isDefendiendo()) {
                    dano = dano / 2;
                    //PUNTDOS DEFENSA
                    defensor.sumarScore(
                            partida.getScoring().getSuccessfulDefense()
                    );
                }

                // no queda vida quede negativa
                defensor.setVida(
                        Math.max(0, defensor.getVida() - dano)
                );
                
                atacante.sumarScore(
                        partida.getScoring().getDamagePoint()
                );
                
                // SOLO si la acción fue exitosa
                registrarAccionExitosa(
                        atacante,
                        accion,
                        partida
                );
                
                   
                
            } else {

                atacante.restarScore(
                        partida.getScoring().getFailedActionPenalty()
                );
            }   
            
            break;

        case "MAGIC_BARRIER":
            //atacante.setRecurso(atacante.getRecurso() - 10);
             if (atacante.getRecurso() >= 20) {

                atacante.setRecurso(
                        atacante.getRecurso() - 20
                );

                atacante.setDefendiendo(true);
                
                // SOLO si la acción fue exitosa
                registrarAccionExitosa(
                        atacante,
                        accion,
                        partida
                );
          } else {

                atacante.restarScore(
                        partida.getScoring().getFailedActionPenalty()
                );
            }   

            break;

        case "HEALING_RUNE":
            //atacante.setVida(atacante.getVida() + 15);
            //atacante.setRecurso(atacante.getRecurso() - 15);
            if (atacante.getRecurso() >= 30) {

                atacante.setRecurso(
                        atacante.getRecurso() - 30
                );

                atacante.setVida(
                        Math.min(
                                100,
                                atacante.getVida() + 25
                        )
                );
                
                atacante.sumarScore(
                    partida.getScoring().getHealingPoint()
                );

                        // SOLO si la acción fue exitosa
                registrarAccionExitosa(
                        atacante,
                        accion,
                        partida
                );
            } else {

                atacante.restarScore(
                        partida.getScoring().getFailedActionPenalty()
                );
            }   
            break;

        case "MEDITATE":
            //atacante.setRecurso(atacante.getRecurso() + 20);
             atacante.setRecurso(
                Math.min(
                    120,
                    atacante.getRecurso() + 25
                )     
                );
                     
                // SOLO si la acción fue exitosa
                registrarAccionExitosa(
                        atacante,
                        accion,
                        partida

                    );
            break;

        case "SLASH":
            //defensor.setVida(defensor.getVida() - 10);
            //atacante.setRecurso(atacante.getRecurso() - 10);
            //break;
             if (atacante.getRecurso() >= 10) {

                atacante.setRecurso(
                        atacante.getRecurso() - 10
                );

                //int dano = 12;
                int dano = calcularDanoFisico(
                        atacante,
                        defensor,
                        12
                );
                // war cry ya fue considerado para el calculo de daño fisico
                if (atacante.isMejoraAtaque()) {

                   // dano += 10;

                    // Se consume WAR_CRY
                    atacante.setMejoraAtaque(false);
                }

                if (defensor.isDefendiendo()) {
                    dano = dano / 2;
                    //puntos por defensa exitosa
                     defensor.sumarScore(
                        partida.getScoring().getSuccessfulDefense()
                    );
                }

                 // Aplicar daño y evitar vida negativa
                defensor.setVida(
                        Math.max(0, defensor.getVida() - dano)
                );
                
                atacante.sumarScore(
                        partida.getScoring().getDamagePoint()
                );
                
                        // SOLO si la acción fue exitosa
                registrarAccionExitosa(
                        atacante,
                        accion,
                        partida
                );
                
                  
            } else {

                atacante.restarScore(
                        partida.getScoring().getFailedActionPenalty()
                );
            }   


            break;
            
        case "HEAVY_STRIKE":
            //defensor.setVida(defensor.getVida() - 20);
            //atacante.setRecurso(atacante.getRecurso() - 20);
            if (atacante.getRecurso() >= 25) {

                atacante.setRecurso(
                        atacante.getRecurso() - 25
                );

                //int dano = 25;
                int dano = calcularDanoFisico(
                        atacante,
                        defensor,
                        25
                );
                if (atacante.isMejoraAtaque()) {
                    //dano += 10;
                    atacante.setMejoraAtaque(false);
                }
                if (defensor.isDefendiendo()) {
                    dano = dano / 2;
                    //PUNTOS DEFENSA
                    defensor.sumarScore(
                            partida.getScoring().getSuccessfulDefense()
                    );
                }
                // Aplicar daño y evitar vida negativa
                defensor.setVida(
                        Math.max(0, defensor.getVida() - dano)
                );
                
                atacante.sumarScore(
                        partida.getScoring().getDamagePoint()
                );
                
                // SOLO si la acción fue exitosa
                registrarAccionExitosa(
                        atacante,
                        accion,
                        partida
                );
                
                
            } else {
                
                //System.out.println(
                 //       atacante.getNombre()
                 //       + " fallo HEAVY_STRIKE por recurso insuficiente"
               // );

                atacante.restarScore(
                        partida.getScoring().getFailedActionPenalty()
                );
            }   

            break;

        case "SHIELD_BLOCK":
            //atacante.setRecurso(atacante.getRecurso() - 10);
            if (atacante.getRecurso() >= 15) {

                atacante.setRecurso(
                        atacante.getRecurso() - 15
                );

                atacante.setDefendiendo(true);
                
                    // SOLO si la acción fue exitosa
                registrarAccionExitosa(
                        atacante,
                        accion,
                        partida
                );
            } else {

                atacante.restarScore(
                        partida.getScoring().getFailedActionPenalty()
                );
            } 
            
                
            break;

        case "WAR_CRY":
            //atacante.setRecurso(atacante.getRecurso() - 10);
            if (atacante.getRecurso() >= 20) {

                atacante.setRecurso(
                        atacante.getRecurso() - 20
                );

                atacante.setMejoraAtaque(true);
                
                    // SOLO si la acción fue exitosa
                registrarAccionExitosa(
                        atacante,
                        accion,
                        partida
                );
            } else {

                atacante.restarScore(
                        partida.getScoring().getFailedActionPenalty()
                );
            }
            break;

        case "REST":
            //atacante.setRecurso(atacante.getRecurso() + 20);
            atacante.setRecurso(
                Math.min(
                    100,
                    atacante.getRecurso() + 25
                )
                    );
                    
                    // SOLO si la acción fue exitosa
        registrarAccionExitosa(
                atacante,
                accion,
                partida
        
            );

            break;
        }

        // Evitar valores negativos en recursos
        if (atacante.getRecurso() < 0) {
            atacante.setRecurso(0);
        }

        if (atacante.getTipo().equalsIgnoreCase("mage")) {

            if (atacante.getRecurso() > 120) {
                atacante.setRecurso(120);
            }

            if (atacante.getVida() > 100) {
                atacante.setVida(100);
            }

        } else if (atacante.getTipo().equalsIgnoreCase("warrior")) {

            if (atacante.getRecurso() > 100) {
                atacante.setRecurso(100);
            }

            if (atacante.getVida() > 140) {
                atacante.setVida(140);
            }
        }
        //evita vida negativa
        if (defensor.getVida() < 0) {
            defensor.setVida(0);
        }
    }
    
    private int obtenerPrioridad(String accion) {

        switch (accion) {

            case "MAGIC_BARRIER":
                return 7;

            case "SHIELD_BLOCK":
                return 7;

            case "WAR_CRY":
                return 6;

            case "HEALING_RUNE":
                return 5;

            case "ARCANE_BOLT":
                return 4;

            case "SLASH":
                return 4;

            case "FIREBALL":
                return 2;

            case "HEAVY_STRIKE":
                return 2;

            case "MEDITATE":
                return 1;

            case "REST":
                return 1;

            default:
                return 0;
        }
    }
    
    private void determinarGanador() {
             EstadoJugador ganador = null;

    // Comparar SCORE
    if (jugador1.getScore() > jugador2.getScore()) {

        ganador = jugador1;

    } else if (jugador2.getScore() > jugador1.getScore()) {

        ganador = jugador2;

    } else {

        // Si empatan en score, comparar VIDA
        if (jugador1.getVida() > jugador2.getVida()) {

            ganador = jugador1;

        } else if (jugador2.getVida() > jugador1.getVida()) {

            ganador = jugador2;

        } else {

            // 3. Si también empatan en vida, comparar RECURSO
            if (jugador1.getRecurso() > jugador2.getRecurso()) {

                ganador = jugador1;

            } else if (jugador2.getRecurso() > jugador1.getRecurso()) {

                ganador = jugador2;
            }
        }
    }

    // Si no se encontró ganador, es empate
    if (ganador == null) {

        System.out.println(
                "Resultado: EMPATE"
        );

        return;
    }

    // Victory bonus
    ganador.sumarScore(
            partida.getScoring().getVictoryBonus()
    );

    // Low health victory
    aplicarLowHealthVictory(
            ganador,
            partida
    );

    System.out.println(
            "Ganador: " + ganador.getNombre()
    );
        
    }
    
    private void verificarMageCombo(EstadoJugador jugador,Partida partida) {

        // Solo aplica a magos
        if (!jugador.getTipo().equalsIgnoreCase("mage")) {
            return;
        }

        LinkedList<String> historial = jugador.getHistorial();

        LinkedList<String> combo = partida.getBonuses().getMageCombo();

        // Si todavía no tiene suficientes movimientos
        if (historial.size() < combo.size()) {
            return;
        }

        // Revisamos solamente los últimos movimientos
        int inicio = historial.size() - combo.size();

        for (int i = 0; i < combo.size(); i++) {

            if (!historial.get(inicio + i).equals(combo.get(i))) {
                return;
            }
        }

        // Si llegó hasta aquí, completó el combo
        jugador.sumarScore(
                partida.getBonuses().getMageComboPoints()
        );

        System.out.println(
                jugador.getNombre()
                + " activa MAGE_COMBO: +"
                + partida.getBonuses().getMageComboPoints()
                + " puntos"
        );
    }
    
    private void verificarWarriorCombo(
            EstadoJugador jugador,
            Partida partida) {

        // Solo aplica a guerreros
        if (!jugador.getTipo().equalsIgnoreCase("warrior")) {
            return;
        }

        LinkedList<String> historial = jugador.getHistorial();

        LinkedList<String> combo =
                partida.getBonuses().getWarriorCombo();

        // Todavía no tiene suficientes movimientos
        if (historial.size() < combo.size()) {
            return;
        }

        // Revisar solamente las últimas acciones
        int inicio = historial.size() - combo.size();

        for (int i = 0; i < combo.size(); i++) {

            if (!historial.get(inicio + i).equals(combo.get(i))) {
                return;
            }
        }

        // Combo completado
        int puntos =
                partida.getBonuses().getWarriorComboPoints();

        jugador.sumarScore(puntos);

        System.out.println(
                jugador.getNombre()
                + " activa WARRIOR_COMBO: +"
                + puntos
                + " puntos"
        );
    }

    private void registrarAccionExitosa(
            EstadoJugador jugador,
            String accion,
            Partida partida) {

        jugador.agregarMovimiento(accion);

        verificarMageCombo(jugador, partida);
        verificarWarriorCombo(jugador, partida);
    }
    
    private void aplicarLowHealthVictory(EstadoJugador ganador, Partida partida) {

        int limiteVida;

        if (ganador.getTipo().equalsIgnoreCase("mage")) {

            limiteVida = 25;

        } else if (ganador.getTipo().equalsIgnoreCase("warrior")) {

            limiteVida = 35;

        } else {
            return;
        }

        if (ganador.getVida() <= limiteVida) {

            int puntos =
                    partida.getBonuses().getLowHealthVictory();

            ganador.sumarScore(puntos);

            System.out.println(
                    ganador.getNombre()
                    + " activa LOW_HEALTH_VICTORY: +"
                    + puntos
                    + " puntos"
            );
        }
    }
    
    private String lastMove(LinkedList<String> historial) {

        if (historial == null || historial.isEmpty()) {

            System.out.println(
                    "Error de ejecucion: last_move sobre historial vacio."
            );

            throw new RuntimeException(
                    "last_move requiere un historial no vacio"
            );
        }

        return historial.getLast();
    }
    
    private String getMove(
        LinkedList<String> historial,
            int indice) {

        if (historial == null) {

            throw new RuntimeException(
                    "Error de ejecucion: historial nulo en get_move"
            );
        }

        if (indice < 0 || indice >= historial.size()) {

            System.out.println(
                    "Error de ejecucion: indice invalido en get_move: "
                    + indice
            );

            throw new RuntimeException(
                    "get_move fuera de rango"
            );
        }

        return historial.get(indice);
    }
    
    private int getMovesCount(
            LinkedList<String> historial,
            String accion) {

        int contador = 0;

        for (String movimiento : historial) {

            if (movimiento.equals(accion)) {
                contador++;
            }
        }

        return contador;
    }
    
    private LinkedList<String> getLastNMoves(LinkedList<String> historial, int n) {

        if (n <= 0) {
            throw new RuntimeException(
                    "Error de ejecucion: get_last_n_moves requiere n > 0"
            );
        }

        if (n > historial.size()) {
            throw new RuntimeException(
                    "Error de ejecucion: get_last_n_moves solicita mas movimientos de los disponibles"
            );
        }

        LinkedList<String> resultado = new LinkedList<>();

        int inicio = historial.size() - n;

        for (int i = inicio; i < historial.size(); i++) {
            resultado.add(historial.get(i));
        }

        return resultado;
    }
    
    //para funciones anidadas 
    private LinkedList<String> resolverFuenteHistorial(String fuente, EstadoJugador propio, EstadoJugador oponente) {

        // Historial directo del jugador
        if (fuente.equalsIgnoreCase("self_history")) {
            return propio.getHistorial();
        }

        // Historial directo del oponente
        if (fuente.equalsIgnoreCase("opponent_history")) {
            return oponente.getHistorial();
        }

        // Función anidada GET_LAST_N_MOVES
        if (fuente.startsWith("GET_LAST_N_MOVES:")) {

            String contenido =
                    fuente.substring(
                            "GET_LAST_N_MOVES:".length()
                    );

            String[] partes = contenido.split(":");

            String tipoHistorial = partes[0];
            int n = Integer.parseInt(partes[1]);

            LinkedList<String> historialBase =
                    resolverFuenteHistorial(
                            tipoHistorial,
                            propio,
                            oponente
                    );

            return getLastNMoves(
                    historialBase,
                    n
            );
        }

        throw new RuntimeException(
                "Fuente de historial no reconocida: " + fuente
        );
    }
    
    private int calcularDanoFisico(EstadoJugador atacante, EstadoJugador defensor, int poderAccion) {

        int bonusWarCry = 0;

        if (atacante.isMejoraAtaque()) {
            bonusWarCry = 10;
        }

        int dano =
                poderAccion
                + atacante.getAtaqueFisico()
                + bonusWarCry
                - defensor.getArmadura();

        return Math.max(1, dano);
    }
    
    private int calcularDanoMagico(EstadoJugador atacante, EstadoJugador defensor, int poderAccion) {

        int dano =
                poderAccion
                + atacante.getPoderMagico()
                - defensor.getResistenciaMagica();

        return Math.max(1, dano);
    }
    
    private void imprimir(String texto) {

        System.out.println(texto);
        salida.append(texto).append("\n");
    }
    
    public String getSalida() {
        return salida.toString();
    }
    
    
    
    

}
