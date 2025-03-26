package com.mycompany.main;

//NOME: Gustavo de Assis Xavier

public class Main {

    public static void main(String[] args) {
        //Basicamente, o algoritmo consiste em dois métodos diferentes na classe XAEDSsMpas
        //um calcula o caminho mínimo de um ponto ao demais com relação ao peso, enquanto o
        //o outro método calcula com relação ao tempo
        
        
        //Grafo 1:  
        System.out.println("Grafo 1:");
        //Cria um gráfico com 8 vértices:  
        XGrafo grafo = new XGrafo(8);
        //insere as arestas,  lembrando que começamos no 0, então vertice 1 = 0, v2 =1...
        grafo.insereAresta(0, 1, 6, 3);
        grafo.insereAresta(0, 2, 4, 10);
        grafo.insereAresta(0, 3, 5, 2);
        grafo.insereAresta(1, 4, 9, 5);
        grafo.insereAresta(2, 4, 5, 2);
        grafo.insereAresta(2, 3, 1, 4);
        grafo.insereAresta(3, 2, 5, 7);
        grafo.insereAresta(3, 4, 2, 7);

        //imprime os caminhos:
        XAEDsMaps xaedsmaps = new XAEDsMaps(grafo);
        System.out.println("Caminho mais curto:");
        try {
            xaedsmaps.obterArvoreCMCpeso(0); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        xaedsmaps.imprimeCaminhopeso(0, 4);
        System.out.println("");
        System.out.println("Caminho mais rápido:");
        try {
            xaedsmaps.obterArvoreCMCtempo(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        xaedsmaps.imprimeCaminhotempo(0, 4);

        //Grafo 2:  
        //Cria um gráfico com 10 vértices:  
        grafo = new XGrafo(10);
        //insere as arestas, onde A=0, B=1 ...
        grafo.insereAresta(0, 1, 3, 3);
        grafo.insereAresta(0, 3, 5, 5);
        grafo.insereAresta(1, 2, 2, 6);
        grafo.insereAresta(1, 3, 2, 2);
        grafo.insereAresta(2, 4, 2, 2);
        grafo.insereAresta(3, 1, 3, 1);
        grafo.insereAresta(3, 2, 5, 4);
        grafo.insereAresta(3, 4, 9, 6);
        grafo.insereAresta(4, 0, 6, 3);
        grafo.insereAresta(4, 2, 4, 7);

        //imprime os caminhos:
        System.out.println("");
        System.out.println("------------------------");
        System.out.println("Grafo 2:");

        xaedsmaps = new XAEDsMaps(grafo);
        System.out.println("Caminho mais curto:");
        try {
            xaedsmaps.obterArvoreCMCpeso(3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        xaedsmaps.imprimeCaminhopeso(3, 0);

        System.out.println("");
        System.out.println("Caminho mais rápido:");
        try {
            xaedsmaps.obterArvoreCMCtempo(3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        xaedsmaps.imprimeCaminhotempo(3, 0);
    }
}
