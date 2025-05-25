import java.util.*;

public class SomaDosSubconjuntos {
    static long instrucoes = 0;
    static long iteracoes = 0;

    public static void main(String[] args) {
        int[] conjunto = {1, 3, 5, -2, -3, 2, 0, 32};
        long tempoInicial = System.nanoTime();
        Set<List<Integer>> resultadosValidos = encontrarTodosSubconjuntosSomaZero(conjunto);
        long tempoFinal = System.nanoTime();
        double tempoTotal = (tempoFinal - tempoInicial) / 1_000_000.0;

        if (!resultadosValidos.isEmpty()) {
            int count = 1;
            for (List<Integer> solucao : resultadosValidos) {
                System.out.println("Subconjunto " + (count++) + ": " + solucao);
            }
        } else {
            System.out.println("Nenhum subconjunto com soma zero encontrado.");
        }

        String[][] resultados = {
            {
                "SomaDosSubconjuntos",
                Arrays.toString(conjunto),
                String.valueOf(iteracoes),
                String.valueOf(instrucoes),
                String.format("%.3f", tempoTotal)
            }
        };
        printTable(resultados);
    }

    // Encontra todos os subconjuntos únicos cuja soma seja zero
    public static Set<List<Integer>> encontrarTodosSubconjuntosSomaZero(int[] conjunto) {
        instrucoes++;
        Set<List<Integer>> todasSolucoes = new HashSet<>();
        List<Integer> subconjuntoAtual = new ArrayList<>();
        instrucoes++;
        backtrack(conjunto, 0, subconjuntoAtual, 0, todasSolucoes);
        return todasSolucoes;
    }

    private static void backtrack(int[] conjunto, int index, List<Integer> subconjunto, int soma, Set<List<Integer>> solucoes) {
        iteracoes++;
        instrucoes++;

        if (soma == 0 && !subconjunto.isEmpty()) {
            instrucoes++;
            List<Integer> copiaOrdenada = new ArrayList<>(subconjunto);
            Collections.sort(copiaOrdenada);  // ordena para garantir unicidade
            solucoes.add(copiaOrdenada);      // adiciona subconjunto ordenado ao Set
        }

        instrucoes++;
        if (index >= conjunto.length) {
            instrucoes++;
            return;
        }

        // inclui o elemento atual
        subconjunto.add(conjunto[index]);
        instrucoes++;
        backtrack(conjunto, index + 1, subconjunto, soma + conjunto[index], solucoes);

        // exclui o elemento atual (backtrack)
        subconjunto.remove(subconjunto.size() - 1);
        instrucoes++;
        backtrack(conjunto, index + 1, subconjunto, soma, solucoes);
    }

    public static void printTable(String[][] rows) {
        String[] headers = {"Algoritmo", "Conjunto", "Iterações", "Instruções", "Tempo (ms)"};
        int[] widths = {19, 27, 11, 13, 12};

        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%-" + widths[i] + "s", headers[i]);
            if (i < headers.length - 1) System.out.print(" | ");
        }
        System.out.println();

        for (int width : widths) {
            System.out.print("-".repeat(width));
            System.out.print("-+-");
        }
        System.out.println();

        String lastCategory = "";
        for (String[] row : rows) {
            String currentCategory = row[0];
            if (!lastCategory.equals("") && !currentCategory.equals(lastCategory)) {
                for (int width : widths) {
                    System.out.print("-".repeat(width));
                    System.out.print("-+-");
                }
                System.out.println();
            }
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%-" + widths[i] + "s", row[i]);
                if (i < row.length - 1) System.out.print(" | ");
            }
            System.out.println();
            lastCategory = currentCategory;
        }
    }
}
