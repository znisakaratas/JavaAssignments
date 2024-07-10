import java.util.*;

// Class representing the Mission Synthesis
public class MissionSynthesis {

    // Private fields
    private final List<MolecularStructure> humanStructures; // Molecular structures for humans
    private final ArrayList<MolecularStructure> diffStructures; // Anomalies in Vitales structures compared to humans
    private List<Molecule> lowestHuman;
    private List<Molecule> lowestDiff ;

    // Constructor
    public MissionSynthesis(List<MolecularStructure> humanStructures, ArrayList<MolecularStructure> diffStructures) {
        this.humanStructures = humanStructures;
        this.diffStructures = diffStructures;
    }

    // Method to synthesize bonds for the serum
    public List<Bond> synthesizeSerum() {
        List<Bond> serum = new ArrayList<>();
        List<Double> bonds = new ArrayList<>();
        List<Molecule> lowestMolecules = new ArrayList<>();
        lowestHuman = findMin(humanStructures);
        lowestDiff = findMin(diffStructures);
        lowestMolecules.addAll(lowestHuman);
        lowestMolecules.addAll(lowestDiff);
        for (int i = 0; i < lowestMolecules.size(); i++) {
            for (int j = 0; j < lowestMolecules.size(); j++) {
                if (i==j){
                    continue;
                }
                double bondStrength = (double) (lowestMolecules.get(j).getBondStrength() + lowestMolecules.get(i).getBondStrength())/2;
                bonds.add(bondStrength);
            }
            for (int j = 0; j < lowestMolecules.size(); j++) {
                if (i==j){
                    continue;
                }
                if ((double) (lowestMolecules.get(j).getBondStrength() + lowestMolecules.get(i).getBondStrength())/2 == Collections.min(bonds)){
                    if (lowestMolecules.get(i).isAssigned() && lowestMolecules.get(j).isAssigned()){
                        continue;
                    }
                    lowestMolecules.get(i).assigned = true;
                    lowestMolecules.get(j).assigned = true;
                    Bond bond = new Bond(lowestMolecules.get(j),lowestMolecules.get(i),Collections.min(bonds));
                    serum.add(bond);

                    break;
                }
            }
            bonds.clear();
        }
        /* YOUR CODE HERE */

        return serum;
    }

    public List<Molecule> getLowestDiff() {
        return lowestDiff;
    }

    public List<Molecule> getLowestHuman() {
        return lowestHuman;
    }
    public void printSynthesis(List<Bond> serum) {
        System.out.println("Typical human molecules selected for synthesis: " + getLowestHuman());
        System.out.println("Vitales molecules selected for synthesis: " + getLowestDiff());
        System.out.println("Synthesizing the serum...");
        for (Bond bond : serum) {
            if (bond.getFrom().compareTo(bond.getTo()) > 0){
                System.out.printf("Forming a bond between %s - %s with strength %.2f%n", bond.getTo(), bond.getFrom(), bond.getWeight());
            }else {
                System.out.printf("Forming a bond between %s - %s with strength %.2f%n", bond.getFrom(), bond.getTo(), bond.getWeight());
            }
        }

        double totalBondStrength = serum.stream().mapToDouble(Bond::getWeight).sum();
        System.out.printf("The total serum bond strength is %.2f%n", totalBondStrength);

    }

    private List<Molecule> findMin(List<MolecularStructure> structures){
        List<Molecule> lowestMolecules = new ArrayList<>();
        for (MolecularStructure structure: structures) {
            lowestMolecules.add(structure.getMoleculeWithWeakestBondStrength());
        }
        return lowestMolecules;
    }
}
