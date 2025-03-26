package com.mycompany.main;

public class Heuristica {

    private XGrafo grafo;       //grafo a ser analisado
    private int numVertices;    //numero de vértices do grafo
    private int caminho[];      //caminho do método
    private int custo;          //custo do método
    boolean[] checked;         //no método força bruta usamos o esquema de cores
    //no entanto, por ser só duas cores que precisamos
    //podemos usar um boolean

    //A classe recebe um grafo
    public Heuristica(XGrafo grafo) {
        this.grafo = grafo;
        this.numVertices = grafo.numVertices();
        this.caminho = new int[numVertices + 1]; //inclui a volta para o inicio
        this.checked = new boolean[numVertices]; 
        this.custo = 0;
    }

    public void heuristicaMaisProximo() {
        checked[0] = true; //começamos no vértice 0
        caminho[0] = 0;
        for (int i = 1; i < grafo.numVertices(); i++) {
            int verticeAtual = caminho[i - 1];    //olha o útimo vértice para definir o atual
            int proximo = maisProximo(verticeAtual, checked);  //pega o vértice mais próximo
            caminho[i] = proximo;
            checked[proximo] = true;   //define o vertice que estamos como já passado
            custo += grafo.mat[verticeAtual][proximo];
        }
        custo += grafo.mat[caminho[numVertices - 1]][0];    //adiciona o custo para voltar ao início
        caminho[numVertices] = 0; //volta para o ponto inicial
    }

    
    private int maisProximo(int verticeAtual, boolean[] checked) {
        int proximo = -1;   //se n for encontrado nenhum, retorna -1 (um elemento que dará erro no array)
        int menorDistancia = Integer.MAX_VALUE; //define distância com o valor maximo pois será usado como comparação

        for (int i = 0; i < grafo.numVertices(); i++) { //todas as cidades estão ligadas, ent verificamos todos os vértices
            if (!checked[i] && grafo.existeAresta(verticeAtual, i)) { //se o vértice vizinho n foi checked
                int distancia = grafo.mat[verticeAtual][i]; //pega a distância
                if (distancia < menorDistancia) {   //se a distância do vertice analisado for menor que a do menor, ent é feito a substituição
                    menorDistancia = distancia;
                    proximo = i;
                }
            }
        }
        return proximo;
    }
    
    public void imprimeCaminho() {
        System.out.println("Caminho:");
        for (int vertice : caminho) {
            System.out.print(vertice + " ");
        }
        System.out.println("\nCusto Total do Caminho: " + custo);
        System.out.println("");
        
    }


}
