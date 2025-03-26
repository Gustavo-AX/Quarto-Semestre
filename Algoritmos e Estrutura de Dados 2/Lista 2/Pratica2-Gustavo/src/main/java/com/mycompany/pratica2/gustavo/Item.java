package com.mycompany.pratica2.gustavo;

public class Item {
    private int chave;

    public Item(int chave) {
        this.chave = chave;
    }

    public int getChave() {
        return chave;
    }
    
    public int compara(Item it){
        Item item = it;
        if(this.chave < item.chave)
            return -1;
        else if (this.chave > item.chave)
            return 1;
        return 0;
    }
}
