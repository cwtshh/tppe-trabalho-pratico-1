package model;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private String nome;
    private List<Partida> historico;
    private EstatisticasTime estatisticas = new EstatisticasTime();

    public Time(String nome) {
        this.nome = nome;
        this.historico = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
    public int getPontos() { return estatisticas.getPontos(); }
    public int getVitorias() { return estatisticas.getVitorias(); }
    public int getEmpates() { return estatisticas.getEmpates(); }
    public int getDerrotas() { return estatisticas.getDerrotas(); }
    public int getGolsMarcados() { return estatisticas.getGolsMarcados(); }
    public int getGolsSofridos() { return estatisticas.getGolsSofridos(); }
    public int getSaldoGols() { return estatisticas.getSaldoGols(); }
    public int getJogos() { return estatisticas.getJogos(); }

    public void processarResultado(Partida partida) {
        assert partida != null : "Partida não pode ser nula";
        historico.add(partida);

        int golsTime;
        int golsAdversario;

        if(partida.getMandante().getNome().equals(this.nome)) {
            golsTime = partida.getGolsMandante();
            golsAdversario = partida.getGolsVisitante();
        } else if(partida.getVisitante().getNome().equals(this.nome)) {
            golsTime = partida.getGolsVisitante();
            golsAdversario = partida.getGolsMandante();
        } else {
            throw new IllegalArgumentException("O time não participa desta partida.");
        }

        estatisticas.registrarPartida(golsTime, golsAdversario);
    }
}
