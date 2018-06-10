%{
 #include<stdio.h>
%}

%token COMPOUND WORD DOT
%%
S1 :S S1
   |S
   ;
S : WORD_LIST COMPOUND WORD_LIST DOT {printf("Above Statement is compound statement.\n");}
   | WORD_LIST DOT {printf("Above Statement is simple statement.\n");}
WORD_LIST:WORD WORD_LIST
    | WORD
    ;
%%
extern FILE *yyin;
int main(){
  yyin=fopen("sample","r");
  yyparse();
  return 0;
}
int yyerror(){
  return 0;
}
