package com.mycompany.main;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        try {
            ArvorePatricia ap = new ArvorePatricia(128);
            
            //TODO: Fazer com que o arquivo seja lido palavra por palavra 
            //e sendo inserido na arvore patrícia, depois, fazer a pesquisa das 
            //palavras indicadas no arquivo
           
            //exemplo do funcionamento:
            ap.insere("otorrinolaringologista");
            ap.insere("vida");
            ap.insere("teste");
            ap.insere("nado");
            ap.insere("otorrinolaringologista");
            
            ap.pesquisa("vida");
            ap.pesquisa("teste");
            ap.pesquisa("nado");
            ap.pesquisa("otorrinolaringologista");
            ap.pesquisa("otorrinolaringologisto");
            
            
        } catch (Exception e) {
            System.out.println("Deu ruim");
        }
    }
}
