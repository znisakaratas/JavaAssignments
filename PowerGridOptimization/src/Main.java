import java.io.*;
import java.util.*;

/**
 * Main class
 */

public class Main {
    public static void main(String[] args) throws IOException {

       /** MISSION POWER GRID OPTIMIZATION BELOW **/

        String demandFile = args[0];
        ArrayList<Integer> demandValue = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(demandFile))) {
            while (scanner.hasNext()) {
                int number = scanner.nextInt();
                demandValue.add(number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PowerGridOptimization powerGridOptimization = new PowerGridOptimization(demandValue);
        OptimalPowerGridSolution optPowerGrid = powerGridOptimization.getOptimalPowerGridSolutionDP();
        int maxSatisfiedDemand  = optPowerGrid.getmaxNumberOfSatisfiedDemands();
        ArrayList<Integer> hoursToDischarge = new ArrayList<>(optPowerGrid.getHoursToDischargeBatteriesForMaxEfficiency());
        int totalDemand = 0;
        for (int num: demandValue) {
            totalDemand += num;
        }
        int notSatisfiedDemand = totalDemand - maxSatisfiedDemand;
        String hoursDis = "";
        for (int hours:hoursToDischarge) {
            hoursDis += hours + ", ";
        }
        hoursDis = hoursDis.substring(0,hoursDis.length()-2);
        System.out.println("##MISSION POWER GRID OPTIMIZATION##\n" +
                "The total number of demanded gigawatts: " + totalDemand + "\n" +
                "Maximum number of satisfied gigawatts: " + maxSatisfiedDemand + "\n" +
                "Hours at which the battery bank should be discharged: " + hoursDis + "\n" +
                "The number of unsatisfied gigawatts: " + notSatisfiedDemand + "\n" +
                "##MISSION POWER GRID OPTIMIZATION COMPLETED##");

        /** MISSION ECO-MAINTENANCE BELOW **/

        System.out.println("##MISSION ECO-MAINTENANCE##");
        String ESVFile = args[1];
        ArrayList<Integer> ESVValue = new ArrayList<>();
        int numberOfESV = 0;
        int energyCapacityOfESV = 0;
        try (Scanner scanner = new Scanner(new File(ESVFile))) {
            numberOfESV = scanner.nextInt();
            energyCapacityOfESV = scanner.nextInt();
            while (scanner.hasNext()) {
                int number = scanner.nextInt();
                ESVValue.add(number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); 
        }
        OptimalESVDeploymentGP optimalESVDeploymentGP = new OptimalESVDeploymentGP(ESVValue);
        int optESV = optimalESVDeploymentGP.getMinNumESVsToDeploy(numberOfESV,energyCapacityOfESV);
        if (optESV == -1){
            System.out.println("Warning: Mission Eco-Maintenance Failed.");
        }else {
            System.out.println("The minimum number of ESVs to deploy: " + optESV);
            for (int i = 0; i < optimalESVDeploymentGP.getMaintenanceTasksAssignedToESVs().size(); i++) {
                System.out.println("ESV "+( i+1) + " tasks: "+ optimalESVDeploymentGP.getMaintenanceTasksAssignedToESVs().get(i));
            }
        }
        System.out.println("##MISSION ECO-MAINTENANCE COMPLETED##");
    }
}
