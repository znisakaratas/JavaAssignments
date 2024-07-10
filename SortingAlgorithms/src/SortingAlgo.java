public class SortingAlgo {
    public static int[] insertionSort(int[] A) {
        for (int j = 1; j < A.length; j++) {
            int key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i] > key) {
                A[i + 1] = A[i];
                i = i - 1;
            }
            A[i + 1] = key;
        }
        return A;
    }
    public static int[] mergeSort(int[] B) {
        int n = B.length;
        if (n <= 1) {
            return B;
        }

        int[] left = new int[n / 2];
        int[] right = new int[n - n / 2];

        for (int i = 0; i < n / 2; i++) {
            left[i] = B[i];
        }
        for (int i = n / 2; i < n; i++) {
            right[i - n / 2] = B[i];
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    public static int[] merge(int[] A, int[] B) {
        int[] C = new int[A.length + B.length];
        int i = 0, j = 0, k = 0;

        while (i < A.length && j < B.length) {
            if (A[i] <= B[j]) {
                C[k] = A[i];
                i++;
            } else {
                C[k] = B[j];
                j++;
            }
            k++;
        }

        while (i < A.length) {
            C[k] = A[i];
            i++;
            k++;
        }

        while (j < B.length) {
            C[k] = B[j];
            j++;
            k++;
        }

        return C;
    }
    public static int[] countingSort(int[] A, int k) {
        int[] count = new int[k + 1];
        int[] output = new int[A.length];
        int size = A.length;

        for (int i = 0; i < size; i++) {
            int j = A[i];
            count[j]++;
        }

        for (int i = 1; i <= k; i++) {
            count[i] += count[i - 1];
        }

        for (int i = size - 1; i >= 0; i--) {
            int j = A[i];
            count[j]--;
            output[count[j]] = A[i];
        }

        return output;
    }
    public static long runInsertionSortExperiment(int[] data) {
        long startTime = System.nanoTime();
        insertionSort(data);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // turns nanosecond to miliseconds.
    }

    public static long runMergeSortExperiment(int[] data) {
        long startTime = System.nanoTime();
        mergeSort(data);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // turns nanosecond to miliseconds.
    }

    public static int findMax(int[] data){
        int max = 0;
        for (int datas: data) {
            if (datas > max){
                max = datas;
            }
        }
        return max;
    }
    public static long runCountingSortExperiment(int[] data) {
        long startTime = System.nanoTime();
        int k = findMax(data);
        countingSort(data, k);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // turns nanosecond to miliseconds.
    }
}
