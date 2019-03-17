grammar TinyBasic;

program
    : line*
    ;

line
   : number statement CR                                    # programLine
   ;

statement
   : 'PRINT' exprlist  nonl=';'?                            # print
   | 'IF' expression relop expression 'THEN'? statement     # if
   | 'GOTO' number                                          # goto
   | 'INPUT' varlist                                        # input
   | 'LET'? var '=' expression                              # assignment
   | 'GOSUB' number                                         # gosub
   | 'RETURN'                                               # return
   | 'END'                                                  # end
   ;

exprlist
   : expression (',' expression)*
   ;

varlist
   : var (',' var)*
   ;

expression
   : sign=('+' | '-' ) expression                          # sign
   | name=ID '(' (expression (',' expression)*)? ')'            # functionCall
   | '(' expression ')'                                    # parentheses
   | expression op=('*' | '/') expression                  # multiplyDivide
   | expression op=('+' | '-') expression                  # addSubtract
   | value                                                 # literalOrVariable
   ;


value
   : var                                                    # variable
   | STRING                                                 # string
   | number                                                 # num
   ;

var
    : VAR
    ;

number
   : DIGIT +
   ;

relop
   : ('<' ('>' | '=' )?)                                    # lessThan
   | ('>' ('=' )?)                                          # greaterThan
   | '='                                                    # equals
   ;


STRING
   : '"' ~ ["\r\n]* '"'
   ;
DIGIT
   : '0' .. '9'
   ;
VAR
   : 'A' .. 'Z'
   ;
ID
   : [A-Z]+
   ;
CR
   : [\r\n]+
   ;
WS
   : [ \t] -> skip
   ;