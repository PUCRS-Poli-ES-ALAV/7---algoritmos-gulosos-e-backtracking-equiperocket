import java.util.ArrayList;
import java.util.List;

public class SomaDosSubconjuntos {
    public static void main(String[] args) {
        int[] conjunto = { -7, -3, -2, 5, 8 };
        List<Integer> resultado = encontrarSubconjuntoSomaZero(conjunto);

        if (!resultado.isEmpty()) {
            System.out.println("Subconjunto encontrado: " + resultado);
        } else {
            System.out.println("Nenhum subconjunto com soma zero encontrado.");
        }
    }

    // Encontra um subconjunto cuja soma seja zero
    public static List<Integer> encontrarSubconjuntoSomaZero(int[] conjunto) {
        List<Integer> subconjunto = new ArrayList<>();
        if (backtrack(conjunto, 0, subconjunto, 0)) {
            return subconjunto;
        }
        return new ArrayList<>(); // Nenhum subconjunto encontrado
    }

    // Método recursivo de backtracking para encontrar um subconjunto com soma zero.
    private static boolean backtrack(int[] conjunto, int index, List<Integer> subconjunto, int soma) {
        if (soma == 0 && !subconjunto.isEmpty()) {
            return true; // Subconjunto com soma zero encontrado
        }
        if (index >= conjunto.length) {
            return false; // Não há mais elementos para processar
        }

        // Inclui o elemento atual no subconjunto
        subconjunto.add(conjunto[index]);
        if (backtrack(conjunto, index + 1, subconjunto, soma + conjunto[index])) {
            return true; // Caminho válido encontrado
        }

        // Exclui o elemento atual e tenta o próximo
        subconjunto.remove(subconjunto.size() - 1);
        return backtrack(conjunto, index + 1, subconjunto, soma);
    }
}