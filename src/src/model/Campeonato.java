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
        new GeradorDeTurno(times, rodadas, rodadaInicial, inverterMandoCampo).gerar();
    }

    private void rotacionarTimes(List<Time> times) {
        if (times.size() > 2) {
            Time ultimo = times.remove(times.size() - 1);
            times.add(1, ultimo);
        }
    }
}
