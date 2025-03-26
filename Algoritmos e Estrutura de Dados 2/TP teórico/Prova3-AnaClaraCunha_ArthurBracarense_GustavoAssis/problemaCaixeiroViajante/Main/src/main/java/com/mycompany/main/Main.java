package com.mycompany.main;

//NOMES: Ana Clara, Gustavo Assis, Arthur Bracarense
//FINALIZADO
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
//Método força bruta:
        //O problema é repetido 10 vezes para obter a média dos resultados.
        long tempoTotal[] = new long[11];
        for (int p = 0; p < 10; p++) {

            //cria instâncias de 1 a 12 com pesos aleatório que variam de 0 a 10:
            for (int n = 2; n < 13; n++) {
                XGrafo grafo = new XGrafo(n);

                //System.out.print("\n===========================================\n");

                //System.out.println("Teste = " + p + ", n de cidades = " + n);
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        int peso = (int) (Math.random() * 10);
                        grafo.insereAresta(i, j, peso);
                        //System.out.println(i + ", " + j + ", " + peso);
                    }
                }

                long start = System.currentTimeMillis();
                DFS dfs = new DFS(grafo);
                dfs.forcaBruta();
                //dfs.imprimirMelhorCaminho();
                long elapsed = System.currentTimeMillis() - start;
                tempoTotal[n - 2] += elapsed;
                //System.out.println("Elapsed: " + elapsed);
                //System.out.println("\nO número de caminhos analisado é: " + dfs.caminhos);

            }

        }

        //System.out.print("\n===========================================\n");

        for (int i = 0; i < 11; i++) {
            double tempoMedio = tempoTotal[i] / 10;
            System.out.println("O tempo para n = " + (i + 2) + " cidades é de: " + tempoMedio/1000 + " segundos");
        }

        //Método heurístico:
        try {
            XGrafo grafo = new XGrafo(535);

            //si535.tsp é uma matriz diagonal superior com 535 vértices
            String nomeArquivo = "si535.txt";
            //Leitura da Matriz Diagonal Superior:
            LeituraMatriz leitorMatriz = new LeituraMatriz();
            int[][] matriz = leitorMatriz.lerMatrizSuperior(nomeArquivo, 535);
            //A matriz que obtemos é uma diagonal superior, mas na nossa implementação de grafo isso
            //significa que todas as arestas do grafo são direcionandas, e não queremos isso
            //para representar um grafo de arestas não direcionada temos que ter uma matriz simétrica
            //por isso, fazemos essa matriz ficar simétrica com o método abaixo:
            matriz = (leitorMatriz.simetriaSupInf(matriz));

            //colocamos nossa matriz já simetrica na classe grafo:
            grafo.setMat(matriz);

            //chamamos a classe responsável pela heuristica do vizinho mais próximo:
            Heuristica heuristica = new Heuristica(grafo);
            heuristica.heuristicaMaisProximo();
            heuristica.imprimeCaminho();

            //========================================================================================
            grafo = new XGrafo(561);

            //pa561.tsp é uma matriz diagonal inferior com 561 vértices
            nomeArquivo = "pa561.txt";
            //Leitura da Matriz
            leitorMatriz = new LeituraMatriz();
            matriz = leitorMatriz.lerMatrizInferior(nomeArquivo, 561);
            
            //A matriz que obtemos é uma diagonal inferior, mas na nossa implementação de grafo isso
            //significa que todas as arestas do grafo são direcionandas, e não queremos isso
            //para representar um grafo de arestas não direcionada temos que ter uma matriz simétrica
            //por isso, fazemos essa matriz ficar simétrica com o método abaixo:
            matriz = (leitorMatriz.simetriaInfSup(matriz));
            
            /*for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.print("\n");
            }*/
            //colocamos nossa matriz já simetrica na classe grafo:
            grafo.setMat(matriz);

            //chamamos a classe responsável pela heuristica do vizinho mais próximo:
            heuristica = new Heuristica(grafo);
            heuristica.heuristicaMaisProximo();
            heuristica.imprimeCaminho();
            
            //========================================================================================
            grafo = new XGrafo(1032);

            //si1032.tsp é uma matriz diagonal inferior com 1032 vértices
            nomeArquivo = "si1032.txt";
            //Leitura da Matriz Diagonal Superior:
            leitorMatriz = new LeituraMatriz();
            matriz = leitorMatriz.lerMatrizSuperior(nomeArquivo, 1032);
            
            //A matriz que obtemos é uma diagonal superior, mas na nossa implementação de grafo isso
            //significa que todas as arestas do grafo são direcionandas, e não queremos isso
            //para representar um grafo de arestas não direcionada temos que ter uma matriz simétrica
            //por isso, fazemos essa matriz ficar simétrica com o método abaixo:
            matriz = (leitorMatriz.simetriaSupInf(matriz));
            
            //colocamos nossa matriz já simetrica na classe grafo:
            grafo.setMat(matriz);

            //chamamos a classe responsável pela heuristica do vizinho mais próximo:
            heuristica = new Heuristica(grafo);
            heuristica.heuristicaMaisProximo();
            heuristica.imprimeCaminho();
            
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não encontrado.");
            e.printStackTrace();
        }

    }
}
