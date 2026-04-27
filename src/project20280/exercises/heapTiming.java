package project20280.exercises;

import project20280.priorityqueue.HeapPriorityQueue;

public class heapTiming {

    private static void pqSort() {
        for (int n = 1000; n <= 1000000; n += 100000) {
            long totalTime = 0;
            int trials = 10;

            for (int i = 0; i < trials; i++) {
                Integer[] data = new Integer[n];
                for (int j = 0; j < n; j++) data[j] = (int) (Math.random() * n);

                HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>();

                long start = System.nanoTime();

                for (Integer val : data) {
                    pq.insert(val, val);
                }

                while (!pq.isEmpty()) {
                    pq.removeMin();
                }

                long end = System.nanoTime();
                totalTime += (end - start);
            }

            double avgTime = (double) totalTime / trials;
            System.out.println(n + "," + avgTime);
        }
    }

    private static void inPlaceHeapSort() {
        for (int n = 1000; n <= 1000000; n += 100000) {
            long totalTime = 0;
            int trials = 10;

            for (int i = 0; i < trials; i++) {
                Integer[] data = new Integer[n];
                for (int j = 0; j < n; j++) data[j] = (int) (Math.random() * n);

                long start = System.nanoTime();

                heapSort(data);

                long end = System.nanoTime();
                totalTime += (end - start);
            }

            double avgTime = (double) totalTime / trials;
            System.out.println(n + "," + avgTime);
        }
    }

    private static void heapSort(Integer[] data) {
        for (int j = data.length / 2 - 1; j >= 0; j--) {
            downheapInPlace(data, j, data.length);
        }

        for (int j = data.length - 1; j > 0; j--) {
            swapInPlace(data, 0, j);
            downheapInPlace(data, 0, j);
        }
    }

    private static void swapInPlace(Integer[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private static int left(int j) {
        return 2 * j + 1;
    }

    private static int right(int j) {
        return 2 * j + 2;
    }

    private static void downheapInPlace(Integer[] data, int j, int limit) {
        while (left(j) < limit) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;

            if (right(j) < limit) {
                int rightIndex = right(j);
                if (data[leftIndex] < data[rightIndex]) {
                    smallChildIndex = rightIndex;
                }
            }

            if (data[smallChildIndex] <= data[j]) {
                break;
            }

            swapInPlace(data, j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    public static void main(String[] args) {
        System.out.println("Q6: PQSort Timing Measurements");
        System.out.println("n,average_time_nanos");
        pqSort();

        System.out.println();

        System.out.println("Q7: InPlace heapSort Timing Measurements");
        System.out.println("n,average_time_nanos");
        inPlaceHeapSort();
    }
}