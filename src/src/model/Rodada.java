package model;

import Exceptions.QtdPartidasInvalida;

import java.util.ArrayList;
import java.util.List;

public class Rodada {
    private int numero;
    private List<Partida> partidas;
    private static final int LIMITE_PARTIDAS = 10;

    public Rodada(int numero) {
        this.numero = numero;
        this.partidas = new ArrayList<>();
    }

    public void adicionarPartida(Partida partida) throws QtdPartidasInvalida {
        assert partida != null : "Partida não pode ser nula";
        if (partidas.size() >= LIMITE_PARTIDAS) {
            throw new QtdPartidasInvalida();
        }
        partidas.add(partida);
    }

    public List<Partida> getPartidas() {
        return this.partidas;
    }
}
