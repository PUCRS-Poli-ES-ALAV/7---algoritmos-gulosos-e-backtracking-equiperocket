public class ProblemaDoTroco {
    public static int count = 0;

    public static int[] troco(int valor, int[] moedas) {
        int resultado[] = new int[moedas.length];

        for (int i = 0; i < moedas.length; i++) {
            count++;
            resultado[i] = valor / moedas[i];
            valor = valor % moedas[i];
        }
        return resultado;
    }

    public static int[] insertSortReverse(int[] array){
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j] > array[j-1]) {
                count++;
                int aux = array[j];
                array[j] = array[j-1];
                array[j-1] = aux;
                j--;
            }
        }
        return array;
    }
}