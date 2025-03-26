package com.mycompany.main;

public class XGrafo {

    public static class Aresta {

        private int v1, v2, peso, tempo; //a aresta agora carrega peso e tempo

        public Aresta(int v1, int v2, int peso, int tempo) {
            this.v1 = v1;
            this.v2 = v2;
            this.peso = peso;
            this.tempo = tempo;
        }

        public int peso() {
            return this.peso;
        }

        public int tempo() {
            return this.tempo;
        }

        public int v1() {
            return this.v1;
        }

        public int v2() {
            return this.v2;
        }
    }
    private int mat[][]; // pesos do tipo inteiro

    private int tempo[][]; //tempos do tipo inteiro

    private int numVertices;
    private int pos[]; // posição atual ao se percorrer os adjs de um vértice v

    //Construtor: 
    public XGrafo(int numVertices) {
        //cria a matriz n x n
        this.mat = new int[numVertices][numVertices];
        this.tempo = new int[numVertices][numVertices]; //inicializa a matriz de tempos
        this.pos = new int[numVertices];
        this.numVertices = numVertices;
        //Inicializa a matriz de adjacência com zero:
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                this.tempo[i][j] = 0;   //inicializa todos os tempos com 0
                this.mat[i][j] = 0;
            }
            this.pos[i] = -1;
        }
    }

    //Insere a aresta especificada 
    public void insereAresta(int v1, int v2, int peso, int tempo) {
        this.mat[v1][v2] = peso;    //insere o peso na matriz de pesos
        this.tempo[v1][v2] = tempo; //insere o tempo na matriz de tempo
    }

    //Retorna true se existe a aresta com as especificações e com peso != 0 no grafo
    public boolean existeAresta(int v1, int v2) {
        return (this.mat[v1][v2] > 0);
    }

    public boolean listaAdjVazia(int v) {
        for (int i = 0; i < this.numVertices; i++) {
            if (this.mat[v][i] > 0) {
                return false;
            }
        }
        return true;
    }

    public Aresta primeiroListaAdj(int v) {
// Retorna a primeira aresta que o vértice v participa ou
// null se a lista de adjacência de v for vazia
        this.pos[v] = -1;
        return this.proxAdj(v);
    }

    public Aresta proxAdj(int v) {
// Retorna a próxima aresta que o vértice v participa ou
// null se a lista de adjacência de v estiver no fim
        this.pos[v]++;
        while ((this.pos[v] < this.numVertices)
                && (this.mat[v][this.pos[v]] == 0)) {
            this.pos[v]++;
        }
        if (this.pos[v] == this.numVertices) {
            return null;
        } else {
            return new Aresta(v, this.pos[v], this.mat[v][this.pos[v]], this.tempo[v][this.pos[v]]);
        }
    }

    //Retira uma aresta existente
    public Aresta retiraAresta(int v1, int v2) {
        if (this.mat[v1][v2] == 0) {
            return null; // Aresta não existe
        } else {
            Aresta aresta = new Aresta(v1, v2, this.mat[v1][v2], this.tempo[v1][v2]);
            this.mat[v1][v2] = 0;
            this.tempo[v1][v2] = 0;
            return aresta;
        }
    }

    //Método que imprime a matriz de adjacẽncia:
    public void imprime_peso() {
        System.out.print("  ");
        for (int i = 0; i < this.numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < this.numVertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < this.numVertices; j++) {
                System.out.print(this.mat[i][j] + " "); 
            }
            System.out.println();
        }
    }
    public void imprime_tempo() {
        System.out.print("  ");
        for (int i = 0; i < this.numVertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < this.numVertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < this.numVertices; j++) {
                System.out.print(this.tempo[i][j] + " "); 
            }
            System.out.println();
        }
    }

    public int numVertices() {
        return this.numVertices;
    }

    //cria uma aresta não direcionada ao fazer duas arestas direcionadas opostas entre dois vértices
    public void insereArestaDupla(int a, int b, int peso, int tempo) {
        this.insereAresta(a, b, peso, tempo);
        this.insereAresta(b, a, peso, tempo);
    }
}
