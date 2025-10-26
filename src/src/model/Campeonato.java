package model;

import Exceptions.QtdPartidasInvalida;
import Exceptions.QtdTimesInvalida;

import java.util.ArrayList;
import java.util.Collections;
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

    public void sortearRodadas() throws QtdPartidasInvalida {
        rodadas.clear();

        List<Time> timesTmp = new ArrayList<>(times);
        Collections.shuffle(timesTmp);

        gerarTurno(timesTmp, 1, false);
        gerarTurno(timesTmp, 20, true);
    }

    private void gerarTurno(List<Time> times, int rodadaInicial, boolean inverterMandoCampo) throws QtdPartidasInvalida {
        int numTimes = times.size();
        assert numTimes == NUMERO_TIMES : "O número de times deve ser 20";

        for (int rodada = 0; rodada < 19; rodada++) {
            Rodada r = new Rodada(rodadaInicial + rodada);
            for (int i = 0; i < numTimes / 2; i++) {
                Time mandante = inverterMandoCampo ? times.get(numTimes - 1 - i) : times.get(i);
                Time visitante = inverterMandoCampo ? times.get(i) : times.get(numTimes - 1 - i);
                Partida partida = new Partida(mandante, visitante, 0, 0);
                r.adicionarPartida(partida); // Contrato: partidas.size() <= 10
            }
            rodadas.add(r);
            rotacionarTimes(times);
        }
    }

    private void rotacionarTimes(List<Time> times) {
        if (times.size() > 2) {
            Time ultimo = times.remove(times.size() - 1);
            times.add(1, ultimo);
        }
    }
}
