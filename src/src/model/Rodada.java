package model;

import java.util.ArrayList;
import java.util.List;

public class Rodada {
    private int numero;
    private List<Partida> partidas;

    public Rodada(int numero) {
        this.numero = numero;
        this.partidas = new ArrayList<>();
    }
}
