package model;

public class EstatisticasTime {
    private int pontos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsMarcados;
    private int golsSofridos;
    private int jogos;

    public void registrarPartida(int golsFeitos, int golsTomados) {
        jogos++;
        golsMarcados += golsFeitos;
        golsSofridos += golsTomados;

        if (golsFeitos > golsTomados) {
            vitorias++;
            pontos += 3;
        } else if (golsFeitos == golsTomados) {
            empates++;
            pontos += 1;
        } else {
            derrotas++;
        }
    }

    public int getPontos() { return pontos; }
    public int getVitorias() { return vitorias; }
    public int getEmpates() { return empates; }
    public int getDerrotas() { return derrotas; }
    public int getGolsMarcados() { return golsMarcados; }
    public int getGolsSofridos() { return golsSofridos; }
    public int getSaldoGols() { return golsMarcados - golsSofridos; }
    public int getJogos() { return jogos; }
}
