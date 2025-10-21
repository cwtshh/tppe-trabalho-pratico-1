package model;

import Exceptions.QtdPartidasInvalida;

import java.util.ArrayList;
import java.util.List;

public class Rodada {
    private int numero;
    private List<Partida> partidas;

    public Rodada(int numero) {
        this.numero = numero;
        this.partidas = new ArrayList<>();
    }

    public void adicionarPartida(Partida partida) throws QtdPartidasInvalida {
        if(partidas.size() >= 10) {
            throw new QtdPartidasInvalida();
        }
        partidas.add(partida);
    }

    public List<Partida> getPartidas() {
        return this.partidas;
    }
}
