package model;

public class Partida {
    private Time timeDaCasa;
    private Time visitante;
    private int golsDaCasa;
    private int golsVisitante;
    private boolean realizada;

    public Partida(Time mandante, Time visitante, int golsDaCasa, int golsVisitante) {
        this.timeDaCasa = mandante;
        this.visitante = visitante;
        this.golsDaCasa = golsDaCasa;
        this.golsVisitante = golsVisitante;
        this.realizada = false;
    }

    public Time getMandante() {
        return timeDaCasa;
    }

    public Time getVisitante() {
        return visitante;
    }

    public int getGolsMandante() {
        return golsDaCasa;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public boolean isRealizada() {
        return realizada;
    }
}
