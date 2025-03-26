package com.mycompany.main;

public class XAEDsMaps {

    private int antecessor[];
    private double p[];
    private XGrafo grafo;
    private double somatorio = 0; //ajuda nos métodos de imprimir

    public XAEDsMaps(XGrafo grafo) {
        this.grafo = grafo;
    }

    //Método que obtem o caminho minimo, com relação ao peso, a partir de uma raiz recebida
    //como parâmetro.
    public void obterArvoreCMCpeso(int raiz) throws Exception {
        int n = this.grafo.numVertices();
        this.p = new double[n]; // peso ou tempo dos vértices
        int vs[] = new int[n + 1]; // vértices
        this.antecessor = new int[n];
        for (int u = 0; u < n; u++) {
            this.antecessor[u] = -1;
            p[u] = Double.MAX_VALUE; // ∞
            vs[u + 1] = u; // Heap indireto a ser construído
        }
        p[raiz] = 0;
        FPHeapMinIndireto heap = new FPHeapMinIndireto(p, vs);
        heap.constroi();
        while (!heap.vazio()) {
            int u = heap.retiraMin();
            if (!this.grafo.listaAdjVazia(u)) {
                XGrafo.Aresta adj = grafo.primeiroListaAdj(u);
                while (adj != null) {
                    int v = adj.v2();
                    if (this.p[v] > (this.p[u] + adj.peso())) {
                        antecessor[v] = u;
                        heap.diminuiChave(v, this.p[u] + adj.peso());
                    }
                    adj = grafo.proxAdj(u);
                }
            }
        }
    }

    //Método que obtem o caminho minimo, com relação ao tempo, a partir de uma raiz recebida
    //como parâmetro.
    public void obterArvoreCMCtempo(int raiz) throws Exception {
        int n = this.grafo.numVertices();
        this.p = new double[n]; // peso dos vértices
        int vs[] = new int[n + 1]; // vértices
        this.antecessor = new int[n];
        for (int u = 0; u < n; u++) {
            this.antecessor[u] = -1;
            p[u] = Double.MAX_VALUE; // ∞
            vs[u + 1] = u; // Heap indireto a ser construído
        }
        p[raiz] = 0;
        FPHeapMinIndireto heap = new FPHeapMinIndireto(p, vs);
        heap.constroi();
        while (!heap.vazio()) {
            int u = heap.retiraMin();
            if (!this.grafo.listaAdjVazia(u)) {
                XGrafo.Aresta adj = grafo.primeiroListaAdj(u);
                while (adj != null) {
                    int v = adj.v2();
                    if (this.p[v] > (this.p[u] + adj.tempo())) {
                        antecessor[v] = u;
                        heap.diminuiChave(v, this.p[u] + adj.tempo());
                    }
                    adj = grafo.proxAdj(u);
                }
            }
        }
    }

    public int antecessor(int u) {
        return this.antecessor[u];
    }

    public double peso(int u) {
        return this.p[u];
    }

    //Métodos de impressão: Nem de longe otimizados, mas funcionam
    public void imprimeCaminhopeso(int origem, int v) {
        //esse algoritmo soma todos os pesos anteriores,com isso, para conseguirmos
        //o peso específico de cada aresta, devemos faz (peso_atual - somatorio) 
        somatorio = 0;
        printCaminhopeso(origem, v);
        System.out.println("Peso total= " + somatorio);
    }

    public void imprimeCaminhotempo(int origem, int v) {
        //esse algoritmo soma todos os pesos anteriores,com isso, para conseguirmos
        //o peso específico de cada aresta, devemos faz (tempo_atual - somatorio) 
        somatorio = 0;
        printCaminhotempo(origem, v);
        System.out.println("Tempo total= " + somatorio);
    }

    private void printCaminhopeso(int origem, int v) {
        if (origem == v) {
            //faz nada
        } else if (this.antecessor[v] == -1) {
            System.out.println("Nao existe caminho de " + origem + " ate " + v);
        } else {
            printCaminhopeso(origem, this.antecessor[v]);
            System.out.println(" (" + antecessor[v] + " ," + v + ") -- peso: " + (peso(v) - somatorio));
            somatorio = peso(v);
        }
    }

    private void printCaminhotempo(int origem, int v) {
        if (origem == v) {
            //faz nada
        } else if (this.antecessor[v] == -1) {
            System.out.println("Nao existe caminho de " + origem + " ate " + v);
        } else {
            printCaminhotempo(origem, this.antecessor[v]);
            System.out.println(" (" + antecessor[v] + " ," + v + ") -- tempo: " + (peso(v) - somatorio));
            somatorio = peso(v);
        }
    }
}
