package com.mycompany.main;

import java.util.ArrayList;
import java.util.HashMap;

public class ArvorePatricia {

    private static abstract class PatNo {
    }

    private static class PatNoInt extends PatNo {

        int index;
        PatNo esq, dir;
    }

    private static class PatNoExt extends PatNo {

        String chave;
    }

    private PatNo raiz;
    private final int nbitsChave;
    private static int posicao = 0;
    private final HashMap<String, ArrayList<Integer>> posicao_map = new HashMap<>();

    public ArvorePatricia(int nbitsChave) {
        this.raiz = null;
        this.nbitsChave = nbitsChave;
    }

    public void pesquisa(String k) {
        //caso a palavra seja maior que 16 letras, passo só as 16 primeiras:
        String aux = k;
        if (k.length() > 16) {
            aux = k.substring(0, 16); //conta só os primeiros 16 caracteres
        }

        this.pesquisa(aux, this.raiz);
    }

    public void insere(String k) {
        //caso a palavra seja maior que 16 letras, passo só as 16 primeiras:
        String aux = k;
        if (k.length() > 16) {
            aux = k.substring(0, 16); //conta só os primeiros 16 caracteres
        }

        this.raiz = this.insere(aux, this.raiz);
    }

    // Retorna o i-ésimo bit da chave k a partir da esquerda
    private int bit(int i, String k) {
        if (i == 0) {
            return 0;
        }
        //cada palavra é formada por 16 caracteres de 8 bits cada
        //logo, tem 128 bits cada palavra
        int aux = (i) / 8; //aux representa o caracter a ser analisado
        //palavras menores que 16 caracteres, terão seus bits alinhados a esquerda
        //e todos os bits não usados a direita serão 0;
        if (aux > k.length() - 1) {
            return 0;
        }
        //seleciono a letra a ser analisada:
        int c = (int) k.charAt(aux);
        for (int j = 1; j <= 8 - (i % 8); j++) {
            c = c / 2;
        }
        //retorno o bit correspondente
        return c % 2;
    }

    // Verifica se p é nó externo
    private boolean eExterno(PatNo p) {
        Class classe = p.getClass();
        return classe.getName().equals(PatNoExt.class.getName());
    }

    //método para criar nó interno:
    private PatNo criaNoInt(int i, PatNo esq, PatNo dir) {
        PatNoInt p = new PatNoInt();
        p.index = i;
        p.esq = esq;
        p.dir = dir;
        return p;
    }

    //método para criar nó externo:
    private PatNo criaNoExt(String k) {
        PatNoExt p = new PatNoExt();
        p.chave = k;
        return p;
    }

    //Método para pesquisa:
    private void pesquisa(String k, PatNo t) {
        if (this.eExterno(t)) {
            PatNoExt aux = (PatNoExt) t;
            if (aux.chave.equals(k)) {
                System.out.println("Elemento encontrado");
                //Busca no map as posições que ele apareceu:
                System.out.println("Nas posicoes: " + posicao_map.get(k));

            } else {
                System.out.println("Elemento nao encontrado");
            }
        } else {
            PatNoInt aux = (PatNoInt) t;
            if (this.bit(aux.index, k) == 0) {
                pesquisa(k, aux.esq);
            } else {
                pesquisa(k, aux.dir);
            }
        }
    }

    //ALGORITMO DE INSERÇÃO:
    private PatNo insereEntre(String k, PatNo t, int i) {
        PatNoInt aux = null;
        if (!this.eExterno(t)) {
            aux = (PatNoInt) t;
        }
        if (this.eExterno(t) || (i < aux.index)) { // Cria um novo nó externo
            PatNo p = this.criaNoExt(k);
            if (this.bit(i, k) == 1) {
                return this.criaNoInt(i, t, p);
            } else {
                return this.criaNoInt(i, p, t);
            }
        } else {
            if (this.bit(aux.index, k) == 1) {
                aux.dir = this.insereEntre(k, aux.dir, i);
            } else {
                aux.esq = this.insereEntre(k, aux.esq, i);
            }
            return aux;
        }
    }

    private PatNo insere(String k, PatNo t) {
        insereNoMap(k);
        if (t == null) {
            //primeiro ciclo:
            return this.criaNoExt(k);
        } else {
            PatNo p = t;
            while (!this.eExterno(p)) {
                PatNoInt aux = (PatNoInt) p;
                if (this.bit(aux.index, k) == 1) {
                    p = aux.dir;
                } else {
                    p = aux.esq;
                }
            }
            PatNoExt aux = (PatNoExt) p;
            int i = 1;
            // acha o primeiro bit diferente, 128 bits no total
            while ((i <= this.nbitsChave) && (this.bit(i, k) == this.bit(i, aux.chave))) {
                i++;
            }
            if (i > this.nbitsChave) {
                //System.out.println("Erro : chave ja esta na arvore");
                return t;
            } else {
                return this.insereEntre(k, t, i);
            }

        }
    }

    //método que insere no map de posições, que guardas as posições
    //que certa palavra apareceu no texto
    private void insereNoMap(String k) {
        posicao += 1;
        if (!posicao_map.containsKey(k)) { //não contem, logo adiciona a palavra e a posição
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(posicao);
            posicao_map.put(k, temp);
        } else { //contém, logo só incrementa a posição que apareceu
            ArrayList<Integer> temp = posicao_map.get(k);
            temp.add(posicao);
            posicao_map.replace(k, temp);
        }
    }
}
