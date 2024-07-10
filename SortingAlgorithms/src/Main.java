import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws IOException {
        int[][] insertion = generateSubsets(FileInput.getData("C:\\Users\\Zeynep Nisa\\IdeaProjects\\assignment1\\src\\TrafficFlowDataset.csv"));
        int[][] merge = generateSubsets(FileInput.getData("C:\\Users\\Zeynep Nisa\\IdeaProjects\\assignment1\\src\\TrafficFlowDataset.csv"));
        int[][] counting = generateSubsets(FileInput.getData("C:\\Users\\Zeynep Nisa\\IdeaProjects\\assignment1\\src\\TrafficFlowDataset.csv"));
        double[][] insertionTime = new double[3][10];
        double[][] mergeTime = new double[3][10];
        double[][] countingTime = new double[3][10];
        double[][] searchTime = new double[3][10];
        for (int i = 0; i < 10; i++) {
            insertionTime[0][i] = SortingAlgo.runInsertionSortExperiment(insertion[i]);
            mergeTime[0][i] = SortingAlgo.runMergeSortExperiment(merge[i]);
            countingTime[0][i] = SortingAlgo.runCountingSortExperiment(counting[i]);
        }
        for(int i = 0; i < 10; i++) {
            insertionTime[1][i] = SortingAlgo.runInsertionSortExperiment(insertion[i]);
            mergeTime[1][i] = SortingAlgo.runMergeSortExperiment(insertion[i]);
            countingTime[1][i] = SortingAlgo.runCountingSortExperiment(insertion[i]);
        }

        for (int i = 0; i < 10; i++) {
            insertion[i] = reverseArray(insertion[i]);
        }
        for (int i = 0; i < 10; i++) {
            insertionTime[2][i] = SortingAlgo.runInsertionSortExperiment(insertion[i]);
            mergeTime[2][i] = SortingAlgo.runMergeSortExperiment(insertion[i]);
            countingTime[2][i] = SortingAlgo.runCountingSortExperiment(insertion[i]);
        }
        for (int i = 0; i < 10; i++) {
            insertion[i] = reverseArray(insertion[i]);
        }
        for (int i = 0; i < 10; i++) {
            searchTime[0][i] = SearchAlgo.runLinearSearchRandomExperiment(merge[i]);//random data linear search
            searchTime[1][i] = SearchAlgo.runLinearSearchSortedExperiment(insertion[i]); //sorted data linear search
            searchTime[2][i] = SearchAlgo.runBinarySearchExperiment(insertion[i]); //sorted data binary search
        }

        int[] xData = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};

        double[][] yAxisRandom = new double[3][10];
        yAxisRandom[0] = Arrays.copyOfRange(insertionTime[0],0,10);
        yAxisRandom[1] = Arrays.copyOfRange(mergeTime[0],0,10);
        yAxisRandom[2] = Arrays.copyOfRange(countingTime[0],0,10);
        showAndSaveChart("Random Input Data Timing", xData, yAxisRandom,"Insertion Sort", "Merge Sort", "Counting Sort","Time in Milliseconds");

        double[][] yAxisSorted = new double[3][10];
        yAxisSorted[0] = Arrays.copyOfRange(insertionTime[1],0,10);
        yAxisSorted[1] = Arrays.copyOfRange(mergeTime[1],0,10);
        yAxisSorted[2] = Arrays.copyOfRange(countingTime[1],0,10);
        showAndSaveChart("Sorted Input Data Timing", xData, yAxisSorted,"Insertion Sort", "Merge Sort", "Counting Sort","Time in Milliseconds");

        double[][] yAxisReverse = new double[3][10];
        yAxisReverse[0] = Arrays.copyOfRange(insertionTime[2],0,10);
        yAxisReverse[1] = Arrays.copyOfRange(mergeTime[2],0,10);
        yAxisReverse[2] = Arrays.copyOfRange(countingTime[2],0,10);
        showAndSaveChart("Reversely Sorted Input Data Timing", xData, yAxisReverse, "Insertion Sort", "Merge Sort", "Counting Sort","Time in Milliseconds");

        double[][] yAxisSearch = new double[3][10];
        yAxisSearch[0] = Arrays.copyOfRange(searchTime[0],0,10);
        yAxisSearch[1] = Arrays.copyOfRange(searchTime[1],0,10);
        yAxisSearch[2] = Arrays.copyOfRange(searchTime[2],0,10);
        showAndSaveChart("Search Algorithms Running Time", xData, yAxisSearch,"Linear Search(random)", "Linear Search(sorted)","Binary Search(sorted)","Time in Nanoseconds");

    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis,String inp1, String inp2, String inp3,String inp4) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle(inp4).xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries(inp1, doubleX, yAxis[0]);
        chart.addSeries(inp2, doubleX, yAxis[1]);
        chart.addSeries(inp3, doubleX, yAxis[2]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
    public static int[] reverseArray(int[] array){
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
        return array;
    }
    private static int[][] generateSubsets(int[] result) {
        int[][] subsets = new int[10][]; // Number of subsets = 10
        int[] sizes = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            subsets[i] = Arrays.copyOfRange(result,0,size);
        }
        return subsets;
    }

}