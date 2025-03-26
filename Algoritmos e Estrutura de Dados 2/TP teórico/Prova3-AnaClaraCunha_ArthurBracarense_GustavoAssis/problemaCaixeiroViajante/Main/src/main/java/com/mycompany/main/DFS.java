package com.mycompany.main;

public class DFS {
    //Estados de um vértice:
    public static final byte branco = 0;
    public static byte cinza = 1;

    //Auxiliares:
    private int caminhoAtual[];     //Define o caminho em cada ciclo
    private int numVertices;        //Devemos passar por todos os vértices.
    private int[] melhorCaminho;    //Guarda o melhor caminho
    private int melhorCusto;        //Guarda o custo do melhor caminho

    public int caminhos = 0;           //conta o numero de caminho que passamos

    //Grafo:
    private XGrafo grafo;

    public DFS(XGrafo grafo) {
        this.grafo = grafo;
        this.numVertices = grafo.numVertices();
        this.melhorCaminho = new int[numVertices];
        this.melhorCusto = Integer.MAX_VALUE;
        caminhoAtual = new int[numVertices];
    }

    //private void resolverRecursivamente(int passo, int[] caminhoAtual, boolean[] visitado, int custoAtual) {
    private void caminhosForcaBruta(int v, int[] caminhoAtual, int cor[], int custoAtual) {
        for (int i = 1; i < numVertices; i++) {
            if (cor[i] == branco) { //se não foi visitado
                cor[i] = cinza;     //define como visitado
                caminhoAtual[v] = i;
                int pesoAresta = grafo.mat[caminhoAtual[v - 1]][i];
                //olha o próximo vértice
                caminhosForcaBruta(v + 1, caminhoAtual, cor, custoAtual + pesoAresta);
                //reseta o vértice poís ele pode vir a ser usado novamente:
                cor[i] = branco;
            }
        }

        if (v == numVertices) {
            caminhos++;
            // Chegou ao final do trajeto: verifica o custo total
            int custoTotal = custoAtual + grafo.mat[caminhoAtual[numVertices - 1]][caminhoAtual[0]];
            //se o custo for melhor do que o melhor caminho, o melhor caminho é atualizado
            if (custoTotal < melhorCusto) {
                melhorCusto = custoTotal;
                //faz a cópia:
                for (int n = 0; n < numVertices; n++) {
                    melhorCaminho[n] = caminhoAtual[n];
                }
            }
            return;
        }
    }

    public void forcaBruta() {
        int[] caminhoAtual = new int[numVertices];  //Define o caminho atual
        caminhoAtual[0] = 0;    //Inicia o caminho pelo primeiro vértice
        int cor[] = new int[numVertices];   //A cor de cada vértice define se já foi visitado ou não
        cor[0] = branco;    //inicia com branco o primeiro vértice
        this.caminhosForcaBruta(1, caminhoAtual, cor, 0);
    }

    public void imprimirMelhorCaminho() {
        System.out.println("Melhor caminho: ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(melhorCaminho[i] + " ");
        }
        System.out.println("0"); // Volta ao início
    }
}
