package com.mycompany.pratica2.gustavo;

import java.util.ArrayList;
import java.util.Collections;

public class Pratica2Gustavo {

    public static void main(String[] args) {
        System.out.println("Ordenada:");
        //Pesquisa em árvores ordenadas:
        ArvoreBinaria arvore = new ArvoreBinaria();
        for (int i = 1; i < 9; i++) {
            //Inserção dos itens:
            for (int n = (i * 1000); n < ((i + 1) * 1000); n++) {
                Item temp = new Item(n);
                arvore.insere(temp);
            }
            //pesquisa de um elemento inexistente
            long startTime = System.nanoTime();
            arvore.pesquisa(new Item(10000));
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("Execution time in nanoseconds: " + timeElapsed + "O numero de vezes que pesquisou é: " + arvore.count);
            arvore.count = 0;
        }

        System.out.println("Aleatória:");
        for (int i = 1; i < 9; i++) {
            arvore = new ArvoreBinaria();
            ArrayList<Integer> numeros = new ArrayList<>();
            //Inserção dos itens: Insiro previamente em um arrayList para não ter números repetidos
            //tendo em vista que a classe Random gera numeros aleatórios, porém repetidos.
            for (int n = 1000; n < ((i + 1) * 1000); n++) {
                numeros.add(n);
            }
            //embaralha os numeros:
            Collections.shuffle(numeros);
            //coloca eles na arvore:
            for (Integer numero : numeros) {
                //System.out.println(numero);
                Item temp = new Item(numero);
                arvore.insere(temp);
            }
            //pesquisa de um elemento inexistente
            long startTime = System.nanoTime();
            arvore.pesquisa(new Item(10000));
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("Execution time in nanoseconds: " + timeElapsed + "O numero de vezes que pesquisou é: " + arvore.count);
            arvore.count = 0;
        }
    }
}
