public class NRainhas {  

    public static void main(String[] args) {  
        int n = 6;  
        int[] resultado = nRainhas(n);  
        if (resultado != null) {  
            printResultadoTabuleiro(resultado);  
        } else {
            System.out.println("Não foi possível colocar as rainhas."); 
        }
    }

    public static int[] nRainhas(int n) {  
        int[] resultado = new int[n];  // Cria um array de tamanho n para armazenar a posição das rainhas
        for (int i = 0; i < n; i++) {  
            resultado[i] = -1;
        }
        if (nRainhasRec(n, 0, resultado)) {  // Chama a função recursiva para tentar encontrar uma solução
            return resultado;  // Se encontrar uma solução, retorna o array 'resultado' com as posições das rainhas
        }
        return null; 
    }

    public static boolean nRainhasRec(int n, int coluna, int[] resultado) { 
        if (coluna == n) {  // Se todas as colunas forem preenchidas, significa que todas as rainhas foram posicionadas
            return true;  
        }
        for (int i = 0; i < n; i++) {  
            if (podeColocarRainha(i, coluna, resultado)) {  // Verifica se é seguro colocar a rainha na posição (i, coluna)
                resultado[coluna] = i; 
                if (nRainhasRec(n, coluna + 1, resultado)) {  // Chama recursivamente para a próxima coluna
                    return true;  // Se a chamada recursiva retornar true, significa que encontrou uma solução
                }
                resultado[coluna] = -1;  // Se não encontrar uma solução, desfaz a posição da rainha (backtrack)
            }
        }
        return false;  // Se não conseguir posicionar a rainha em nenhuma linha, retorna false
    }

    public static boolean podeColocarRainha(int linha, int coluna, int[] resultado) { 
        for (int i = 0; i < coluna; i++) {  // Itera sobre as colunas já preenchidas
            if (resultado[i] == linha) {  // Verifica se já existe uma rainha na mesma linha
                return false;  // Se já existir, retorna false
            }
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
}
