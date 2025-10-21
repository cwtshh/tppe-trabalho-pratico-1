package model;

import Exceptions.QtdTimesInvalida;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private List<Time> times;
    private List<Rodada> rodadas;
    private static final int NUMERO_RODADAS = 38;
    private static final int NUMERO_TIMES = 20;

    public Campeonato(List<Time> times)  throws QtdTimesInvalida {
        if(times.size() != NUMERO_TIMES) {
            throw new QtdTimesInvalida();
        }
        this.times = new ArrayList<>(times);
        this.rodadas = new ArrayList<>();
    }

    public List<Time> getTimes() {
        return times;
    }

    public List<Rodada> getRodadas() {
        return rodadas;
    }
}
