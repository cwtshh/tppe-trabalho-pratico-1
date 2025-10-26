package model;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private String nome;
    private int pontos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsMarcados;
    private int golsSofridos;
    private int jogos;
    private List<Partida> historico;

    public Time(String nome) {
        this.nome = nome;
        this.pontos = 0;
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        this.golsMarcados = 0;
        this.golsSofridos = 0;
        this.jogos = 0;
        this.historico = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public int getGolsSofridos() {
        return golsSofridos;
    }

    public int getSaldoGols() {
        return golsMarcados - golsSofridos;
    }

    public int getJogos() {
        return jogos;
    }

    public void processarResultado(Partida partida) {
        // Pré-condição: partida diferente de nulo
        assert partida != null : "Partida não pode ser nula";
        historico.add(partida);
        jogos++;

        int golsTime;
        int golsAdversario;

        // Define corretamente quem é o time neste contexto
        if (partida.getMandante().getNome().equals(this.nome)) {
            golsTime = partida.getGolsMandante();
            golsAdversario = partida.getGolsVisitante();
        } else if (partida.getVisitante().getNome().equals(this.nome)) {
            golsTime = partida.getGolsVisitante();
            golsAdversario = partida.getGolsMandante();
        } else {
            // Contrato violado: time não está na partida
            throw new IllegalArgumentException("O time não participa desta partida.");
        }

        golsMarcados += golsTime;
        golsSofridos += golsAdversario;
        assert golsMarcados >= 0 && golsSofridos >= 0 : "Gols não podem ser negativos";

        if (golsTime > golsAdversario) {
            vitorias++;
            pontos += 3;
        } else if (golsTime == golsAdversario) {
            empates++;
            pontos += 1;
        } else {
            derrotas++;
        }
        assert pontos >= 0 : "Pontos não podem ser negativos";
    }
}
