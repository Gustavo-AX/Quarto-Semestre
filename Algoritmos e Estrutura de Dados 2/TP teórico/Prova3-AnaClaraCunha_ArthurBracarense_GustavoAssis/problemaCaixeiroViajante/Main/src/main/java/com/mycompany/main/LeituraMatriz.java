package com.mycompany.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//CLASSE RESPOSÁVEL POR MANIPULAR A MATRIZ LIDA DO ARQUIVO
public class LeituraMatriz {
    
    //Retorna uma matriz, na qual é formata para ser uma diagonal superior
    public int[][] lerMatrizSuperior(String nomeArquivo, int tamanho) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(nomeArquivo));

        // Inicializa a matriz
        int[][] matriz = new int[tamanho][tamanho];

        // Lê os elementos da matriz
        for (int i = 0; i < tamanho; i++) {
            for (int j = i; j < tamanho; j++) {
                matriz[i][j] = scanner.nextInt();
            }
        }

        scanner.close();
        return matriz;
    }
    
    //Retorna uma matriz, na qual é formata para ser uma diagonal inferior
    public int[][] lerMatrizInferior(String nomeArquivo, int tamanho) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(nomeArquivo));

        // Inicializa a matriz
        int[][] matriz = new int[tamanho][tamanho];

        // Lê os elementos da matriz
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j <= i; j++) {
                matriz[i][j] = scanner.nextInt();
            }
        }

        scanner.close();
        return matriz;
    }
    
    //MÉTODO QUE COPIA A PARTE SUPERIOR PARA A INFERIOR DE FORMA SIMÉTRICA
    public int[][] simetriaSupInf(int[][] matriz) {
        int tamanho = matriz.length;

        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                // Coloca os elementos da parte superior na parte inferior
                matriz[j][i] = matriz[i][j];
            }
        }
        return matriz;
    }
    
    //MÉTODO QUE COPIA A PARTE INFERIOR PARA A SUPERIOR DE FORMA SIMÉTRICA
    public int[][] simetriaInfSup(int[][] matriz) {
        int tamanho = matriz.length;

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < i; j++) {
                // Coloca os elementos da parte inferior na parte superior
                matriz[j][i] = matriz[i][j];
            }
        }
        return matriz;
    }

}
