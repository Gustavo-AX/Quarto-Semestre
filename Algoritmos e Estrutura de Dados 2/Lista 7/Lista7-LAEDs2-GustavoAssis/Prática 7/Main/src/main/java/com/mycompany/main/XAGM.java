package com.mycompany.main;

public class XAGM {

    private int antecessor[];
    private double p[];
    private XGrafo grafo;

    public XAGM(XGrafo grafo) {
        this.grafo = grafo;
    }

    //obtem, através do método prim, a agm
    public void obterAgm(int raiz) throws Exception {
        int n = this.grafo.numVertices();
        this.p = new double[n]; // peso dos vértices
        int vs[] = new int[n + 1]; // vértices
        boolean itensHeap[] = new boolean[n];
        this.antecessor = new int[n];
        for (int u = 0; u < n; u++) {
            this.antecessor[u] = -1;
            p[u] = Double.MAX_VALUE; // ∞
            vs[u + 1] = u; // Heap indireto a ser construído
            itensHeap[u] = true;
        }
        p[raiz] = 0;
        FPHeapMinIndireto heap = new FPHeapMinIndireto(p, vs);
        heap.constroi();
        while (!heap.vazio()) {
            int u = heap.retiraMin();
            itensHeap[u] = false;
            if (!this.grafo.listaAdjVazia(u)) {
                XGrafo.Aresta adj = grafo.primeiroListaAdj(u);
                while (adj != null) {
                    int v = adj.v2();
                    if (itensHeap[v] && (adj.peso() < this.peso(v))) {
                        antecessor[v] = u;
                        heap.diminuiChave(v, adj.peso());
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

    //imprime as arestas e pesos da agm
    public void imprime() {
        int peso_Arvore = 0;
        for (int u = 0; u < this.p.length; u++) {
            if (this.antecessor[u] != -1) {
                System.out.println(" ( " + antecessor[u] + " ," + u + ") −− p: " + peso(u));
            peso_Arvore += peso(u);
            }
        }
        System.out.println("Peso total da árvore: " + peso_Arvore);
    }
}
