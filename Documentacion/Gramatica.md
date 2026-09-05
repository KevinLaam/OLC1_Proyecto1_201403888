# Gramática de Battle Script

## Organización de Lenguajes y Compiladores 1

---

## 1. Introducción

El presente documento describe la gramática formal utilizada por Battle Script.

La gramática se presenta utilizando la notación BNF (Backus-Naur Form), con el
objetivo de mostrar de forma ordenada y comprensible la estructura sintáctica
aceptada por el lenguaje.

### Convenciones utilizadas

- Los elementos escritos entre `< >` representan símbolos no terminales.
- Los elementos escritos entre comillas representan símbolos terminales.
- El símbolo `::=` indica una producción.
- El símbolo `|` representa diferentes alternativas de una producción.
- El símbolo `ε` representa una producción vacía.

---

## 2. Estructura general del programa

Un programa de Battle Script está compuesto por estrategias, partidas y una
sección principal.

```bnf
<inicio> ::= <lista_estrategias> <lista_partidas> <principal>
```

---

# 4. Estrategias

## 4.1 Lista de estrategias

El lenguaje permite definir una o más estrategias.

```bnf
<lista_estrategias> ::= <lista_estrategias> <estrategia>
                     | <estrategia>
```

Una estrategia puede pertenecer a un mago o a un guerrero.

```bnf
<estrategia> ::= <estrategia_mago>
               | <estrategia_guerrero>
```

---

## 4.2 Estrategia de mago

```bnf
<estrategia_mago> ::= "mage" ID "{" <cuerpo_mago> "}"
```

El cuerpo de un mago contiene una acción inicial, una lista de reglas y una
regla `else`.

```bnf
<cuerpo_mago> ::= "initial" ":" <accion_mago>
                  "rules" ":" "["
                  <lista_reglas_mago>
                  <regla_else_mago>
                  "]"
```

---

## 4.3 Estrategia de guerrero

```bnf
<estrategia_guerrero> ::= "warrior" ID "{" <cuerpo_guerrero> "}"
```

```bnf
<cuerpo_guerrero> ::= "initial" ":" <accion_guerrero>
                      "rules" ":" "["
                      <lista_reglas_guerrero>
                      <regla_else_guerrero>
                      "]"
```

---

# 5. Acciones

Una acción general puede corresponder a una acción de mago o una acción de
guerrero.

```bnf
<accion> ::= <accion_mago>
           | <accion_guerrero>
```

## 5.1 Acciones del mago

```bnf
<accion_mago> ::= "ARCANE_BOLT"
                | "FIREBALL"
                | "MAGIC_BARRIER"
                | "HEALING_RUNE"
                | "MEDITATE"
```

## 5.2 Acciones del guerrero

```bnf
<accion_guerrero> ::= "SLASH"
                    | "HEAVY_STRIKE"
                    | "SHIELD_BLOCK"
                    | "WAR_CRY"
                    | "REST"
```

---

# 6. Reglas de las estrategias

## 6.1 Regla de mago

```bnf
<regla_mago> ::= "if" <condicion> "then" <accion_mago> ","
```

## 6.2 Regla de guerrero

```bnf
<regla_guerrero> ::= "if" <condicion> "then" <accion_guerrero> ","
```

## 6.3 Regla else de mago

```bnf
<regla_else_mago> ::= "else" <accion_mago>
```

## 6.4 Regla else de guerrero

```bnf
<regla_else_guerrero> ::= "else" <accion_guerrero>
```

## 6.5 Lista de reglas de mago

```bnf
<lista_reglas_mago> ::= <lista_reglas_mago> <regla_mago>
                      | <regla_mago>
```

## 6.6 Lista de reglas de guerrero

```bnf
<lista_reglas_guerrero> ::= <lista_reglas_guerrero> <regla_guerrero>
                          | <regla_guerrero>
```

---

# 7. Condiciones

Las condiciones permiten utilizar operadores lógicos `||`, `&&` y `!`.

La estructura de la gramática establece distintos niveles para las operaciones
lógicas.

## 7.1 Operador OR

```bnf
<condicion> ::= <condicion> "||" <condicion_and>
              | <condicion_and>
```

## 7.2 Operador AND

```bnf
<condicion_and> ::= <condicion_and> "&&" <condicion_not>
                  | <condicion_not>
```

## 7.3 Operador NOT

```bnf
<condicion_not> ::= "!" <condicion_not>
                  | <condicion_base>
```

## 7.4 Condiciones base

Una condición puede ser una comparación o una condición agrupada entre
paréntesis.

```bnf
<condicion_base> ::= <comparacion>
                   | "(" <condicion> ")"
```

Esta organización permite establecer la precedencia:

1. `!`
2. `&&`
3. `||`

---

# 8. Operadores de comparación

```bnf
<operador_comparacion> ::= "=="
                         | "!="
                         | ">"
                         | "<"
                         | ">="
                         | "<="
```

---

# 9. Estados del sistema

Los estados numéricos disponibles para construir condiciones son:

```bnf
<estado_entero> ::= "round_number"
                  | "total_rounds"
                  | "self_health"
                  | "opponent_health"
                  | "self_resource"
                  | "opponent_resource"
                  | "self_score"
                  | "opponent_score"
```

---

# 10. Valores numéricos

```bnf
<valor_numerico> ::= <estado_entero>
                   | ENTERO
                   | "random"
                   | DECIMAL
                   | <funcion_entera>
```

También se permite realizar una resta dentro de una expresión numérica.

```bnf
<expresion_numerica> ::= <expresion_numerica> "-" <valor_numerico>
                       | <valor_numerico>
```

Ejemplo de una expresión aceptada:

```text
total_rounds - 1
```

---

# 11. Comparaciones

Una comparación puede realizarse entre expresiones numéricas, acciones o
listas de acciones.

```bnf
<comparacion> ::= <expresion_numerica>
                  <operador_comparacion>
                  <expresion_numerica>

                | <valor_accion> "==" <valor_accion>
                | <valor_accion> "!=" <valor_accion>

                | <valor_lista> "==" <valor_lista>
                | <valor_lista> "!=" <valor_lista>
```

---

# 12. Historial de movimientos

Los historiales disponibles son:

```bnf
<historial> ::= "self_history"
              | "opponent_history"
```

Estos valores permiten consultar las acciones realizadas anteriormente por los
jugadores.

---

# 13. Funciones del historial

## 13.1 GET_MOVE

Permite obtener una acción ubicada en una posición determinada del historial.

```bnf
<funcion_accion> ::= "get_move" "(" <historial> "," ENTERO ")"
```

## 13.2 LAST_MOVE

Permite obtener el último movimiento realizado.

```bnf
<funcion_accion> ::= "last_move" "(" <historial> ")"
```

Por lo tanto, la definición completa de una función que retorna una acción es:

```bnf
<funcion_accion> ::= "get_move" "(" <historial> "," ENTERO ")"
                   | "last_move" "(" <historial> ")"
```

---

## 13.3 GET_LAST_N_MOVES

Permite obtener los últimos `N` movimientos de un historial.

```bnf
<funcion_lista> ::= "get_last_n_moves"
                    "(" <historial> "," ENTERO ")"
```

---

## 13.4 Fuentes de historial

Una fuente utilizada por las funciones puede ser directamente un historial o
el resultado de una función que devuelve una lista.

```bnf
<fuente_historial> ::= <historial>
                     | <funcion_lista>
```

---

## 13.5 GET_MOVES_COUNT

Permite contar la cantidad de veces que una acción aparece dentro de una fuente
de historial.

```bnf
<funcion_entera> ::= "get_moves_count"
                     "("
                     <fuente_historial>
                     ","
                     <accion>
                     ")"
```

Esto permite realizar consultas tanto sobre un historial completo como sobre
una lista obtenida mediante `get_last_n_moves`.

---

# 14. Valores de acción

Un valor de acción puede ser directamente una acción o el resultado de una
función que devuelve una acción.

```bnf
<valor_accion> ::= <accion>
                 | <funcion_accion>
```

---

# 15. Listas de acciones

Una lista de acciones se escribe utilizando corchetes.

```bnf
<lista_acciones> ::= "[" <elementos_acciones> "]"
```

Los elementos de una lista se separan mediante comas.

```bnf
<elementos_acciones> ::= <elementos_acciones> "," <accion>
                       | <accion>
```

Un valor de tipo lista puede ser una lista escrita directamente o el resultado
de una función que retorna una lista.

```bnf
<valor_lista> ::= <lista_acciones>
                | <funcion_lista>
```

---

# 16. Partidas

Una partida se define utilizando la palabra reservada `match`, seguida de un
identificador.

```bnf
<partida> ::= "match" ID "{" <cuerpo_partida> "}"
```

El cuerpo de una partida está compuesto por los jugadores, la cantidad de
rondas, el sistema de puntuación y las bonificaciones.

```bnf
<cuerpo_partida> ::= <jugadores>
                     <rondas>
                     <scoring>
                     <bonuses>
```

---

## 16.1 Lista de partidas

El lenguaje permite definir una o más partidas.

```bnf
<lista_partidas> ::= <lista_partidas> <partida>
                   | <partida>
```

---

# 17. Jugadores

Cada partida contiene exactamente dos identificadores de jugadores.

```bnf
<jugadores> ::= "players" ":" "[" ID "," ID "]"
```

Ejemplo:

```text
players: [Aurora, Titan]
```

---

# 18. Rondas

La cantidad de rondas se especifica mediante un número entero.

```bnf
<rondas> ::= "rounds" ":" ENTERO
```

Ejemplo:

```text
rounds: 6
```

---

# 19. Sistema de puntuación

La sección `scoring` contiene los valores utilizados para calcular la
puntuación durante una batalla.

```bnf
<scoring> ::= "scoring" ":" "{" <lista_scoring> "}"
```

Su estructura es:

```bnf
<lista_scoring> ::=
      "damage_point" ":" ENTERO ","
      "healing_point" ":" ENTERO ","
      "successful_defense" ":" ENTERO ","
      "victory_bonus" ":" ENTERO ","
      "failed_action_penalty" ":" ENTERO
```

Los elementos deben aparecer siguiendo esta estructura.

---

# 20. Bonificaciones

La sección de bonificaciones se define de la siguiente manera:

```bnf
<bonuses> ::= "bonuses" ":" "{" <lista_bonuses> "}"
```

La configuración completa de bonificaciones es:

```bnf
<lista_bonuses> ::=
      "mage_combo" ":" <lista_acciones_mago> ","
      "mage_combo_points" ":" ENTERO ","
      "warrior_combo" ":" <lista_acciones_guerrero> ","
      "warrior_combo_points" ":" ENTERO ","
      "low_health_victory" ":" ENTERO
```

---

# 21. Combo del mago

Una lista de combo del mago únicamente puede contener acciones correspondientes
a este tipo de personaje.

```bnf
<lista_acciones_mago> ::= "[" <elementos_acciones_mago> "]"
```

```bnf
<elementos_acciones_mago> ::=
      <elementos_acciones_mago> "," <accion_mago>
    | <accion_mago>
```

Ejemplo:

```text
mage_combo: [ARCANE_BOLT, ARCANE_BOLT, FIREBALL]
```

---

# 22. Combo del guerrero

Una lista de combo del guerrero únicamente puede contener acciones
correspondientes a este tipo de personaje.

```bnf
<lista_acciones_guerrero> ::= "[" <elementos_acciones_guerrero> "]"
```

```bnf
<elementos_acciones_guerrero> ::=
      <elementos_acciones_guerrero> "," <accion_guerrero>
    | <accion_guerrero>
```

Ejemplo:

```text
warrior_combo: [SLASH, SLASH, HEAVY_STRIKE]
```

---

# 23. Sección principal

La sección principal del programa se define mediante `main`.

```bnf
<principal> ::= "main" "{" <cuerpo_principal> "}"
```

El cuerpo principal contiene una o más instrucciones `run`.

```bnf
<cuerpo_principal> ::= <cuerpo_principal> <instruccion_run>
                     | <instruccion_run>
```

---

# 24. Instrucción RUN

Una instrucción `run` determina las partidas que deben ejecutarse y el `seed`
que se utilizará durante dicha ejecución.

```bnf
<instruccion_run> ::=
      "run"
      "["
      <lista_partidas_run>
      "]"
      "with"
      "{"
      "seed" ":" ENTERO
      "}"
```

Ejemplo:

```text
run [PartidaUno, PartidaDos] with {
    seed: 55
}
```

---

# 25. Lista de partidas de RUN

Una instrucción `run` puede ejecutar una o más partidas.

```bnf
<lista_partidas_run> ::= <lista_partidas_run> "," ID
                       | ID
```

Por ejemplo:

```text
run [PartidaUno, PartidaDos] with {
    seed: 55
}
```

---

# 26. Múltiples instrucciones RUN

Debido a que `<cuerpo_principal>` permite contener múltiples
`<instruccion_run>`, una sección `main` puede ejecutar diferentes grupos de
partidas utilizando diferentes valores de `seed`.

Ejemplo:

```text
main {
    run [PartidaUno, PartidaDos] with {
        seed: 55
    }

    run [PartidaUno] with {
        seed: 53
    }
}
```

El orden de ejecución corresponde al orden en el que aparecen las instrucciones
`run` dentro de `main`.

---

# 27. Ejemplo general

Un programa simplificado que cumple con la estructura de la gramática es:

```text
mage Merlin {
    initial: ARCANE_BOLT
    rules: [
        if self_resource <= 20 then MEDITATE,
        else ARCANE_BOLT
    ]
}

warrior Ragnar {
    initial: SLASH
    rules: [
        if self_resource <= 20 then REST,
        else SLASH
    ]
}

match DueloInicial {
    players: [Merlin, Ragnar]
    rounds: 3

    scoring: {
        damage_point: 10,
        healing_point: 5,
        successful_defense: 3,
        victory_bonus: 20,
        failed_action_penalty: 2
    }

    bonuses: {
        mage_combo: [ARCANE_BOLT, FIREBALL],
        mage_combo_points: 15,
        warrior_combo: [SLASH, HEAVY_STRIKE, SLASH],
        warrior_combo_points: 15,
        low_health_victory: 10
    }
}

main {
    run [DueloInicial] with {
        seed: 123
    }
}
```

---
