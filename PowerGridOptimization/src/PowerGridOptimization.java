import java.util.ArrayList;

/**
 * This class accomplishes Mission POWER GRID OPTIMIZATION
 */
public class PowerGridOptimization {
    private ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour;
    public PowerGridOptimization(ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour){
        this.amountOfEnergyDemandsArrivingPerHour = amountOfEnergyDemandsArrivingPerHour;
    }
    public ArrayList<Integer> getAmountOfEnergyDemandsArrivingPerHour() {
        return amountOfEnergyDemandsArrivingPerHour;
    }
    public OptimalPowerGridSolution getOptimalPowerGridSolutionDP() {
        int size = amountOfEnergyDemandsArrivingPerHour.size(); // Total number of hours
        int[] solution = new int[size + 1]; // Array to store optimal solutions
        ArrayList<ArrayList<Integer>> hours = new ArrayList<>(); // Array to store hours at which battery should be discharged
        solution[0] = 0;
        hours.add(new ArrayList<>()); // For hour 0, there are no hours to discharge
        for (int j = 1; j <= size; j++) {
            int maxSatNum = 0;
            int dischargeHour = -1;

            for (int i = 0; i < j; i++) {
                int satisfiedNums = solution[i] + Math.min(amountOfEnergyDemandsArrivingPerHour.get(j - 1), (j - i) * (j - i));
                if (satisfiedNums > maxSatNum) {
                    maxSatNum = satisfiedNums;
                    dischargeHour = i;
                }
            }
            // Updating SOL and HOURS for hour j
            solution[j] = maxSatNum;
            ArrayList<Integer> prevDischargeHours = new ArrayList<>(hours.get(dischargeHour));
            prevDischargeHours.add(j);
            hours.add(prevDischargeHours);
        }
        int maxSatDemands = solution[size];
        ArrayList<Integer> hoursToDischargeBatteries = hours.get(size);
        return new OptimalPowerGridSolution(maxSatDemands, hoursToDischargeBatteries);
    }
}
