package model;

public class Partida {
    private Time timeDaCasa;
    private Time visitante;
    private int golsDaCasa;
    private int golsVisitante;
    private boolean realizada;

    public Partida(Time mandante, Time visitante) {
        this.timeDaCasa = mandante;
        this.visitante = visitante;
        this.golsDaCasa = 0;
        this.golsVisitante = 0;
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
