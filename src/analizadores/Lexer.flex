package analizadores;

import java_cup.runtime.Symbol;

%%

%class Lexer
%public
%cup
%unicode
%line
%column

%{

    private Symbol symbol(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1, yytext());
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }

%}

/* ---------- EXPRESIONES REGULARES ---------- */

LETRA       = [a-zA-Z_]
ID_INVALIDO = [0-9]+[a-zA-Z_][a-zA-Z0-9_]*
DIGITO      = [0-9]
ID          = {LETRA}({LETRA}|{DIGITO})*

ENTERO      = {DIGITO}+
DECIMAL     = {DIGITO}+"."{DIGITO}+

BLANCO      = [ \t\r\n]+

COMENTARIO_LINEA = "//".*
COMENTARIO_MULTI = "/*"([^*]|\*+[^*/])*\*+"/"
%%

/* ---------- PALABRAS RESERVADAS ---------- */

"mage"                  {return symbol(sym.MAGE);}

"warrior"               {return symbol(sym.WARRIOR);}

"initial"               {return symbol(sym.INITIAL);}

"rules"                 {return symbol(sym.RULES);}

"if"                    {return symbol(sym.IF);}

"then"                  {return symbol(sym.THEN);}

"else"                  {return symbol(sym.ELSE);}

/* ---------- PARTIDAS ---------- */

"match"                 { return symbol(sym.MATCH); }
"players"               { return symbol(sym.PLAYERS); }
"rounds"                { return symbol(sym.ROUNDS); }
"scoring"               { return symbol(sym.SCORING); }
"bonuses"               { return symbol(sym.BONUSES); }

/* ---------- PUNTO DE ENTRADA ---------- */

"main"                  { return symbol(sym.MAIN); }
"run"                   { return symbol(sym.RUN); }
"with"                  { return symbol(sym.WITH); }
"seed"                  { return symbol(sym.SEED); }

/* ---------- SCORING ---------- */

"damage_point"            { return symbol(sym.DAMAGE_POINT); }
"healing_point"           { return symbol(sym.HEALING_POINT); }
"successful_defense"      { return symbol(sym.SUCCESSFUL_DEFENSE); }
"victory_bonus"           { return symbol(sym.VICTORY_BONUS); }
"failed_action_penalty"   { return symbol(sym.FAILED_ACTION_PENALTY); }

/* ---------- BONUSES ---------- */

"mage_combo"              { return symbol(sym.MAGE_COMBO); }
"mage_combo_points"       { return symbol(sym.MAGE_COMBO_POINTS); }
"warrior_combo"           { return symbol(sym.WARRIOR_COMBO); }
"warrior_combo_points"    { return symbol(sym.WARRIOR_COMBO_POINTS); }
"low_health_victory"      { return symbol(sym.LOW_HEALTH_VICTORY); }

/* ---------- FUNCIONES DEL SISTEMA ---------- */

"get_move"            { return symbol(sym.GET_MOVE); }
"last_move"           { return symbol(sym.LAST_MOVE); }
"get_moves_count"     { return symbol(sym.GET_MOVES_COUNT); }
"get_last_n_moves"    { return symbol(sym.GET_LAST_N_MOVES); }

/* ---------- ESTADOS DEL SISTEMA ---------- */

"round_number"        { return symbol(sym.ROUND_NUMBER); }
"total_rounds"        { return symbol(sym.TOTAL_ROUNDS); }

"self_health"         { return symbol(sym.SELF_HEALTH); }
"opponent_health"     { return symbol(sym.OPPONENT_HEALTH); }

"self_resource"       { return symbol(sym.SELF_RESOURCE); }
"opponent_resource"   { return symbol(sym.OPPONENT_RESOURCE); }

"self_score"          { return symbol(sym.SELF_SCORE); }
"opponent_score"      { return symbol(sym.OPPONENT_SCORE); }

"self_history"        { return symbol(sym.SELF_HISTORY); }
"opponent_history"    { return symbol(sym.OPPONENT_HISTORY); }

"random"              { return symbol(sym.RANDOM); }

/* ---------- ACCIONES ---------- */

"ARCANE_BOLT"      { return symbol(sym.ARCANE_BOLT); }
"FIREBALL"         { return symbol(sym.FIREBALL); }
"MAGIC_BARRIER"    { return symbol(sym.MAGIC_BARRIER); }
"HEALING_RUNE"     { return symbol(sym.HEALING_RUNE); }
"MEDITATE"         { return symbol(sym.MEDITATE); }
"SLASH"            { return symbol(sym.SLASH); }
"HEAVY_STRIKE"     { return symbol(sym.HEAVY_STRIKE); }
"SHIELD_BLOCK"     { return symbol(sym.SHIELD_BLOCK); }
"WAR_CRY"          { return symbol(sym.WAR_CRY); }
"REST"             { return symbol(sym.REST); }

/* ---------- OPERADORES DE COMPARACION ---------- */

"=="        { return symbol(sym.IGUAL_IGUAL); }
"!="        { return symbol(sym.DIFERENTE); }

">="        { return symbol(sym.MAYOR_IGUAL); }
"<="        { return symbol(sym.MENOR_IGUAL); }

">"         { return symbol(sym.MAYOR); }
"<"         { return symbol(sym.MENOR); }

/* ---------- OPERADORES BOOLEANOS ---------- */

"&&"        { return symbol(sym.AND); }
"||"        { return symbol(sym.OR); }
"!="        { return symbol(sym.DIFERENTE); }

/* ---------- SIMBOLOS Y SIGNOS DE AGRUPACION ---------- */

"{"         { return symbol(sym.LLAVE_A); }
"}"         { return symbol(sym.LLAVE_C); }

"["         { return symbol(sym.CORCHETE_A); }
"]"         { return symbol(sym.CORCHETE_C); }

"("         { return symbol(sym.PAR_A); }
")"         { return symbol(sym.PAR_C); }

":"         { return symbol(sym.DOS_PUNTOS); }
","         { return symbol(sym.COMA); }

/* ---------- VALORES ---------- */

{DECIMAL}   {return symbol(sym.DECIMAL,Double.parseDouble(yytext()));}

{ENTERO}    {    return symbol(sym.ENTERO,Integer.parseInt(yytext()));}

{ID_INVALIDO} {
    System.out.println(
        "Error léxico: identificador inválido '"
        + yytext()
        + "' en línea "
        + (yyline + 1)
        + ", columna "
        + (yycolumn + 1)
    );
}

{ID}        {return symbol(sym.ID,yytext());}

/* ---------- COMENTARIOS Y ESPACIOS ---------- */

{COMENTARIO_LINEA}  { }
{COMENTARIO_MULTI}  { }
{BLANCO}            { }

/* ---------- ERROR LEXICO ---------- */

. {
    System.out.println(
        "Error léxico: carácter no reconocido '"
        + yytext()
        + "' en línea "
        + (yyline + 1)
        + ", columna "
        + (yycolumn + 1)
    );
}