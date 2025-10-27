package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TabelaClassificacao {

    private final Comparator<Time> criterioClassificacao;

    public TabelaClassificacao() {
        this.criterioClassificacao = new Comparator<Time>() {
            @Override
            public int compare(Time t1, Time t2) {
                int comparacaoPontos = Integer.compare(t2.getPontos(), t1.getPontos());
                if (comparacaoPontos != 0) {
                    return comparacaoPontos;
                }
                int comparacaoVitorias = Integer.compare(t2.getVitorias(), t1.getVitorias());
                if (comparacaoVitorias != 0) {
                    return comparacaoVitorias;
                }
                int comparacaoSaldo = Integer.compare(t2.getSaldoGols(), t1.getSaldoGols());
                if (comparacaoSaldo != 0) {
                    return comparacaoSaldo;
                }
                int comparacaoGolsMarcados = Integer.compare(t2.getGolsMarcados(), t1.getGolsMarcados());
                if (comparacaoGolsMarcados != 0) {
                    return comparacaoGolsMarcados;
                }
                return t1.getNome().compareTo(t2.getNome());
            }
        };
    }

    public void ordenar(List<Time> times) {
        Collections.sort(times, criterioClassificacao);
    }

    public void exibirClassificacao(List<Time> times) {
        ordenar(times);

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              TABELA DE CLASSIFICAÇÃO - BRASILEIRÃO SÉRIE A 2025           ║");
        System.out.println("╠════╦═══════════════════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═══════╣");
        System.out.println("║ Pos║ Time              ║  Pts║    J║    V║    E║    D║   GM║   GS║     SG║");
        System.out.println("╠════╬═══════════════════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╬═══════╣");

        for (int i = 0; i < times.size(); i++) {
            Time time = times.get(i);
            String zona = getZonaClassificacao(i + 1);

            System.out.printf("║ %2d%s║ %-17s ║ %4d║ %4d║ %4d║ %4d║ %4d║ %4d║ %4d║ %6d║\n",
                    (i + 1),
                    zona,
                    time.getNome(),
                    time.getPontos(),
                    time.getJogos(),
                    time.getVitorias(),
                    time.getEmpates(),
                    time.getDerrotas(),
                    time.getGolsMarcados(),
                    time.getGolsSofridos(),
                    time.getSaldoGols());
        }

        System.out.println("╚════╩═══════════════════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═══════╝");
        System.out.println("\nLegenda:");
        System.out.println(" Libertadores (Fase de Grupos)");
        System.out.println(" Libertadores (Pré-Libertadores)");
        System.out.println(" Sul-Americana");
        System.out.println(" Zona de Rebaixamento");
    }

    private String getZonaClassificacao(int posicao) {
        if (posicao <= 4) {
            return "Libertadores";
        } else if (posicao <= 6) {
            return "Pré-Libertadores";
        } else if (posicao <= 12) {
            return "ul-Americana";
        } else if (posicao >= 17) {
            return "Zona de Rebaixamento";
        }
        return "  ";
    }

    public Time getCampeao(List<Time> times) {
        if (times.isEmpty()) {
            return null;
        }
        ordenar(times);
        return times.get(0);
    }

    public List<Time> getTimesLibertadores(List<Time> times) {
        ordenar(times);
        return times.subList(0, Math.min(6, times.size()));
    }

    public List<Time> getTimesSulAmericana(List<Time> times) {
        ordenar(times);
        return times.subList(6, Math.min(12, times.size()));
    }

    public List<Time> getTimesRebaixados(List<Time> times) {
        ordenar(times);
        int tamanho = times.size();
        return times.subList(Math.max(0, tamanho - 4), tamanho);
    }
}
