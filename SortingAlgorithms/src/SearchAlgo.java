import java.util.Random;

public class SearchAlgo {
    public static int linearSearch(int[] A, int x) {
        int size = A.length;
        for (int i = 0; i < size; i++) {
            if (A[i] == x) {
                return i;
            }
        }
        return -1;
    }
    public static int binarySearch(int[] A, int x) {
        int low = 0;
        int high = A.length - 1;

        while (high - low > 1) {
            int mid = (high + low) / 2;
            if (A[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        if (A[low] == x) {
            return low;
        } else if (A[high] == x) {
            return high;
        }
        return -1;
    }
    public static long runLinearSearchRandomExperiment(int[] data) {
        long totalTime = 0;
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int searchValue = data[random.nextInt(data.length)];
            long startTime = System.nanoTime();
            linearSearch(data, searchValue);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / 1_000;
    }
    public static long runLinearSearchSortedExperiment(int[] data) {
        long totalTime = 0;
        Random random = new Random();
        data = SortingAlgo.insertionSort(data);
        for (int i = 0; i < 1000; i++) {
            int searchValue = data[random.nextInt(data.length)];
            long startTime = System.nanoTime();
            linearSearch(data, searchValue);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / 1_000;
    }

    public static long runBinarySearchExperiment(int[] data) {
        long totalTime = 0;
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int searchValue = data[random.nextInt(data.length)];
            long startTime = System.nanoTime();
            binarySearch(data, searchValue);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / 1_000;
    }

}
