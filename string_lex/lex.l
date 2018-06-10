%{
    #include<stdio.h>
    int line=0,words=0,c_letters=0,num=0,spl_chr=0,total=0;
%}
%%
\n { line++;words++;}
[\t ' '] words++;
[a-zA-Z] c_letters++;
[0-9] num++;
. spl_chr++;
%%

int main(){
 yyin=fopen("sample","r");
 yylex();
 total=c_letters+num+spl_chr;
 printf("This File Contains:");
 printf("\n\t%d lines",line);
 printf("\n\t%d words",words);
 printf("\n\t%d characters",c_letters);
 printf("\n\t%d digits",num);
 printf("\n\t%d special character",spl_chr);
 printf("\n\ttotal %d characters.\n",total);
}
int yywrap(){
	return 1;

}
