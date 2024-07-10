import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Eco-Maintenance
 */
public class OptimalESVDeploymentGP
{
    private ArrayList<Integer> maintenanceTaskEnergyDemands;
    private ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = new ArrayList<>();

    ArrayList<ArrayList<Integer>> getMaintenanceTasksAssignedToESVs() {
        return maintenanceTasksAssignedToESVs;
    }

    public OptimalESVDeploymentGP(ArrayList<Integer> maintenanceTaskEnergyDemands) {
        this.maintenanceTaskEnergyDemands = maintenanceTaskEnergyDemands;
    }

    public ArrayList<Integer> getMaintenanceTaskEnergyDemands() {
        return maintenanceTaskEnergyDemands;
    }

    /**
     *
     * @param maxNumberOfAvailableESVs the maximum number of available ESVs to be deployed
     * @param maxESVCapacity the maximum capacity of ESVs
     * @return the minimum number of ESVs required using first fit approach over reversely sorted items.
     * Must return -1 if all tasks can't be satisfied by the available ESVs
     */
    public int getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity)
    {
        Collections.sort(maintenanceTaskEnergyDemands, Collections.reverseOrder());
        ArrayList<Integer> remainingEnergyCapacities = new ArrayList<>();
        for (int i = 0; i < maxNumberOfAvailableESVs; i++) {
            remainingEnergyCapacities.add(maxESVCapacity);
        }

        for (int demand : maintenanceTaskEnergyDemands) {
            boolean alreadyAdded = false;
            for (int i = 0; i < maxNumberOfAvailableESVs; i++) {
                if (demand <= remainingEnergyCapacities.get(i)) {
                    if (maintenanceTasksAssignedToESVs.size() <= i) {
                        maintenanceTasksAssignedToESVs.add(new ArrayList<>());
                    }
                    maintenanceTasksAssignedToESVs.get(i).add(demand);

                    // Updating the remaining energy capacity of this ESV
                    remainingEnergyCapacities.set(i, remainingEnergyCapacities.get(i) - demand);

                    alreadyAdded = true;
                    break;
                }
            }
            if (!alreadyAdded) {
                return -1;
            }
        }
        return maintenanceTasksAssignedToESVs.size();
    }
}
