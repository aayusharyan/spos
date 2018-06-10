%{
   #include<stdio.h>
  #include"y.tab.h"
%}
%token BUILTIN ID NUM SC COMMA OPEN_SQ CLOSE_SQ EQ NEW
%%
start : BUILTIN ID EQ NUM SC  {printf("Declaration is valid");} | BUILTIN OPEN_SQ CLOSE_SQ ID EQ NEW BUILTIN OPEN_SQ NUM CLOSE_SQ SC
{printf("Declaration is valid");}
%%
int main(){
  printf("Enter a statement:");
  yyparse();
  return 1;
}
int yywrap(){
 return 1;
}
yyerror(char *s){
 fprintf(stderr,"%s\n",s);
}
