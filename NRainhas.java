import java.util.ArrayList;
import java.util.List;

public class NRainhas {
    static long instrucoes = 0;
    static long iteracoes = 0;

    public static void main(String[] args) {
        int n = 6;
        long tempoInicial = System.nanoTime();
        List<int[]> todasSolucoes = nRainhas(n);  // Encontra todas as soluções possíveis para o problema das N rainhas
        long tempoFinal = System.nanoTime();
        double tempoTotal = (tempoFinal - tempoInicial) / 1_000_000.0;

        if (!todasSolucoes.isEmpty()) {
            int count = 1;
            for (int[] solucao : todasSolucoes) {
                System.out.println("Solução " + (count++) + ":");
                printResultadoTabuleiro(solucao);  // Exibe a solução atual
                System.out.println();
            }
        } else {
            System.out.println("Não foi possível encontrar nenhuma solução.");  // Se nenhuma solução for encontrada
        }

        String[][] resultados = {
            {"NRainhas", String.valueOf(n), String.valueOf(iteracoes), String.valueOf(instrucoes), String.valueOf(tempoTotal)}
        };
        printTable(resultados);  // Exibe tabela com métricas de desempenho
    }

    public static List<int[]> nRainhas(int n) {
        instrucoes++;
        List<int[]> solucoes = new ArrayList<>();
        int[] resultado = new int[n];  // Cria um array de tamanho n para armazenar a posição das rainhas
        for (int i = 0; i < n; i++) {
            resultado[i] = -1;
            instrucoes++;
        }
        instrucoes++;
        nRainhasRec(n, 0, resultado, solucoes);  // Chama a função recursiva para encontrar todas as soluções
        return solucoes;
    }

    public static void nRainhasRec(int n, int coluna, int[] resultado, List<int[]> solucoes) {
        instrucoes++;
        if (coluna == n) {  // Se todas as colunas forem preenchidas, significa que todas as rainhas foram posicionadas
            instrucoes++;
            solucoes.add(resultado.clone());  // Adiciona uma cópia da solução encontrada
            return;
        }

        for (int i = 0; i < n; i++) {
            iteracoes++;
            instrucoes++;
            if (podeColocarRainha(i, coluna, resultado)) {  // Verifica se é seguro colocar a rainha na posição (i, coluna)
                resultado[coluna] = i;
                instrucoes++;
                nRainhasRec(n, coluna + 1, resultado, solucoes);  // Chama recursivamente para a próxima coluna
                resultado[coluna] = -1;  // Se não encontrar uma solução, desfaz a posição da rainha (backtrack)
                instrucoes++;
            }
        }
    }

    public static boolean podeColocarRainha(int linha, int coluna, int[] resultado) {
        for (int i = 0; i < coluna; i++) {  // Itera sobre as colunas já preenchidas
            instrucoes++;
            instrucoes++;
            if (resultado[i] == linha) {  // Verifica se já existe uma rainha na mesma linha
                return false;  // Se já existir, retorna false
            }
            instrucoes++;
            if (Math.abs(resultado[i] - linha) == Math.abs(i - coluna)) {  // Verifica se as rainhas estão na mesma diagonal
                return false;  // Se estiverem na mesma diagonal, retorna false
            }
        }
        return true;
    }

    public static void printTabuleiro(int[] resultado) {
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado.length; j++) {
                if (resultado[j] == i) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void printResultado(int[] resultado) {
        for (int i = 0; i < resultado.length; i++) {
            System.out.println("Rainha " + i + ": " + resultado[i]);
        }
    }

    public static void printResultadoTabuleiro(int[] resultado) {
        for (int i = 0; i < resultado.length; i++) {
            System.out.println("Rainha " + i + ": " + resultado[i]);
        }
        printTabuleiro(resultado);
    }

    public static void printTable(String[][] rows) {
        String[] headers = {"Algoritmo", "N", "Iterações", "Instruções", "Tempo (ms)"};
        int[] widths = {15, 2, 11, 13, 12};

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
