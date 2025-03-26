package com.mycompany.main;

public class Main {

    public static void main(String[] args) {
        //A classe XGrafo é cria arestas direcionadas, no entanto, estamos lidando com um grafo
        //que é não direcionado, para resolver esse problema, podemos apenas fazer um 
        //caminho de ida e volta para cada aresta, isso é feito através do comando insereArestaDupla.

        //Grafo 1:
        //Cria um gráfico com 9 vértices: Aresta A=0, B= 1 e assim por diante
        
        System.out.println("Grafo 1:");
                
        XGrafo grafo = new XGrafo(9);

        grafo.insereArestaDupla(5, 6, 15);
        grafo.insereArestaDupla(6, 0, 10);
        grafo.insereArestaDupla(5, 1, 5);
        grafo.insereArestaDupla(0, 1, 5);
        grafo.insereArestaDupla(6, 2, 5);
        grafo.insereArestaDupla(2, 4, 10);
        grafo.insereArestaDupla(2, 3, 5);
        grafo.insereArestaDupla(6, 3, 10);
        grafo.insereArestaDupla(3, 0, 10);
        grafo.insereArestaDupla(3, 4, 5);
        grafo.insereArestaDupla(4, 0, 5);
        grafo.insereArestaDupla(4, 8, 10);
        grafo.insereArestaDupla(8, 1, 10);
        grafo.insereArestaDupla(8, 7, 20);
        grafo.insereArestaDupla(0, 7, 10);
        grafo.insereArestaDupla(7, 1, 5);

        //implementa a árvore agm
        XAGM xagm = new XAGM(grafo);
        try {
            xagm.obterAgm(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        xagm.imprime();

        System.out.println("");
        
        //Grafo 2:
        //Cria um gráfico com 8 vértices: Aresta A=0, B= 1 e assim por diante
        System.out.println("");
        System.out.println("Grafo 2:");
        
        grafo = new XGrafo(8);
        
        grafo.insereArestaDupla(1, 0, 8);
        grafo.insereArestaDupla(1, 2, 3);
        grafo.insereArestaDupla(1, 3, 2);
        grafo.insereArestaDupla(1, 4, 7);
        grafo.insereArestaDupla(1, 5, 5);
        grafo.insereArestaDupla(1, 7, 6);
        grafo.insereArestaDupla(0, 2, 2);
        grafo.insereArestaDupla(2, 3, 9);
        grafo.insereArestaDupla(3, 4, 6);
        grafo.insereArestaDupla(4, 5, 5);
        grafo.insereArestaDupla(5, 7, 4);
        grafo.insereArestaDupla(0, 6, 9);
        grafo.insereArestaDupla(2, 6, 6);
        grafo.insereArestaDupla(3, 6, 2);
        grafo.insereArestaDupla(4, 6, 4);
        grafo.insereArestaDupla(5, 6, 6);
        grafo.insereArestaDupla(7, 6, 3);
        
        //implementa a árvore agm
        xagm = new XAGM(grafo);
        try {
            xagm.obterAgm(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        xagm.imprime();
    }
}
