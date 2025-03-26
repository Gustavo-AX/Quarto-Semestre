package com.mycompany.pratica2.gustavo;

public class ArvoreBinaria {

    private No raiz;
    public int count = 0; //conta o numero de vezes que a pesquisa se repetiu

    private static class No {

        Item reg;
        No esq, dir;
    }

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public Item pesquisa(Item reg) {
        return this.pesquisa(reg, this.raiz);
    }

    public void insere(Item reg) {
        this.raiz = this.insere(reg, this.raiz);
    }

    private Item pesquisa(Item reg, No p) {
        count++;
        if (p == null) { //Registro não encontrado
            return null;
        } else if (reg.compara(p.reg) < 0) {
            //pesquisa na para esquerda do último nó
            return pesquisa(reg, p.esq);
        } else if (reg.compara(p.reg) > 0) {
            //pesquisa na para direita do último nó
            return pesquisa(reg, p.dir);
        } else {
            //elemento encontrado.
            return p.reg;
        }
    }

    //metodo de inserção:
    private No insere(Item reg, No p) {
        //cria um nó se ele n existir
        if (p == null) {
            p = new No();
            p.reg = reg;
            p.esq = null;
            p.dir = null;
        } else if (reg.compara(p.reg) < 0) {
            //adiciona a esquerda do último nó
            p.esq = insere(reg, p.esq);
        } else if (reg.compara(p.reg) > 0) {
            //adiciona a direita do último nó
            p.dir = insere(reg, p.dir);
        } else {
            //caso o elemento já exista na árvore:
            System.out.println("Erro: Registro já existente");
        }
        return p;
    }
}
