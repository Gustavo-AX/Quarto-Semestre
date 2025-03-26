package com.mycompany.main;

import java.util.StringTokenizer;
import java.io.*;

public class ExtraiPalavra {

    private BufferedReader arqDelim, arqTxt;
    private StringTokenizer palavras;
    private String delimitadores, palavraAnt, palavra;

    private boolean eDelimitador(char ch) {
        return (this.delimitadores.indexOf(ch) >= 0);
    }

    public ExtraiPalavra(String nomeArqDelim, String nomeArqTxt)
            throws Exception {
        this.arqDelim = new BufferedReader(new FileReader(nomeArqDelim));
        this.arqTxt = new BufferedReader(new FileReader(nomeArqTxt));
        // Os delimitadores devem estar juntos em uma única linha do arquivo
        this.delimitadores = arqDelim.readLine() + " \r \n";
        this.palavras = null;
        this.palavra = null;
        this.palavraAnt = " ";
    }

    public String proximaPalavra() throws Exception {
        String palavraTemp = "";
        String resultado = "";
        if (this.palavra != null) {
            palavraTemp = palavra;
            palavra = null;
            palavraAnt = palavraTemp;
            return palavraTemp;
        }
        if (palavras == null || !palavras.hasMoreTokens()) {
            String linha = arqTxt.readLine();
            if (linha == null) {
                return null;
            }
            linha += " \n";
            this.palavras = new StringTokenizer(linha);
        }
        String aux = this.palavras.nextToken();
        while (eDelimitador(aux.charAt(0)) && palavras.hasMoreTokens()) {
            palavraTemp += aux;
            aux = this.palavras.nextToken();
        }
        if (palavraTemp.length() == 0) {
            resultado = aux;
        } else {
            this.palavra = aux;
            if (palavraTemp.length() == 1 && palavraTemp.equals(" ")
                    && palavraAnt.length() > 0 && palavra.length() > 0
                    && !eDelimitador(palavraAnt.charAt(0))
                    && !eDelimitador(palavra.charAt(0))) {
                palavraTemp = palavraTemp.trim();
            }
            resultado = palavraTemp;
        }
        this.palavraAnt = resultado;
        return resultado;
    }

    public void fecharArquivos() throws Exception {
        this.arqDelim.close();
        this.arqTxt.close();
    }
}
