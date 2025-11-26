package model;

import Exceptions.QtdPartidasInvalida;

import java.util.List;

public class GeradorDeTurno {
    private final List<Time> times;
    private final List<Rodada> rodadas;
    private final int rodadaInicial;
    private final boolean inverterMandoCampo;

    private static final int NUMERO_PARTIDAS = 19;
    private static final int NUMERO_TIMES = 20;

    public GeradorDeTurno(List<Time> times, List<Rodada> rodadas, int rodadaInicial, boolean inverterMandoCampo) {
        this.times = times;
        this.rodadas = rodadas;
        this.rodadaInicial = rodadaInicial;
        this.inverterMandoCampo = inverterMandoCampo;
    }

    public void gerar() throws QtdPartidasInvalida {
        assert times.size() == NUMERO_TIMES : "O número de times deve ser 20";

        for (int rodada = 0; rodada < NUMERO_PARTIDAS; rodada++) {
            Rodada r = new Rodada(rodadaInicial + rodada);

            criarPartidasDaRodada(r);
            rodadas.add(r);

            rotacionarTimes();
        }
    }

    private void criarPartidasDaRodada(Rodada r) throws QtdPartidasInvalida {
        int numTimes = times.size();

        for (int i = 0; i < numTimes / 2; i++) {
            Time mandante = inverterMandoCampo ? times.get(numTimes - 1 - i) : times.get(i);
            Time visitante = inverterMandoCampo ? times.get(i) : times.get(numTimes - 1 - i);

            Partida p = new Partida(mandante, visitante, 0, 0);
            r.adicionarPartida(p);
        }
    }

    private void rotacionarTimes() {
        if (times.size() > 2) {
            Time ultimo = times.remove(times.size() - 1);
            times.add(1, ultimo);
        }
    }
}
