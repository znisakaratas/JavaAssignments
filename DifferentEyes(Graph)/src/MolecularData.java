import java.util.*;

// Class representing molecular data
public class MolecularData {

    // Private fields
    private final List<Molecule> molecules; // List of molecules

    // Constructor
    public MolecularData(List<Molecule> molecules) {
        this.molecules = molecules;
    }

    // Getter for molecules
    public List<Molecule> getMolecules() {
        return molecules;
    }

    public List<MolecularStructure> identifyMolecularStructures() {
        ArrayList<MolecularStructure> structures = new ArrayList<>();
        for (Molecule molecule : molecules) {
            boolean added = false;
            //List<String> allBondsIncluded ;
            for (MolecularStructure structure : structures) {
                if (structure.hasMolecule(molecule.getId()) ) {
                    added = true;
                    break;
                }
                for (String bonds: molecule.getBonds()) {
                    if (structure.hasMolecule(bonds)){
                        addConnectedMolecules(molecule,structure);
                        added=true;
                        break;
                    }
                }
            }
            if (!added) {
                MolecularStructure newStructure = new MolecularStructure();
                addConnectedMolecules(molecule, newStructure);
                structures.add(newStructure);
            }
        }
        for (MolecularStructure structure:structures){
            structure.getMolecules().sort(Molecule::compareTo);

        }
        return structures;
    }
    private void addConnectedMolecules(Molecule molecule, MolecularStructure structure) {
        structure.addMolecule(molecule);
        for (String bond : molecule.getBonds()) {
            Molecule connected = findMolecule(bond);
            if (connected != null && !structure.hasMolecule(connected.getId())) {
                addConnectedMolecules(connected, structure);
            }
        }
    }
    private Molecule findMolecule(String id) {
        for (Molecule molecule : molecules) {
            if (molecule.getId().equals(id)) {
                return molecule;
            }
        }
        return null;
    }



    // Method to print given molecular structures
    public void printMolecularStructures(List<MolecularStructure> molecularStructures, String species) {
        System.out.println(molecularStructures.size() + " molecular structures have been discovered in " + species + ".");
        for (int i = 0; i < molecularStructures.size(); i++) {
            System.out.print("Molecules in Molecular Structure " + (i + 1) + ": [");
            List<Molecule> molecules = molecularStructures.get(i).getMolecules();
            for (int j = 0; j < molecules.size(); j++) {
                System.out.print(molecules.get(j).getId());
                if (j < molecules.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        /* YOUR CODE HERE */ 

    }

    // Method to identify anomalies given a source and target molecular structure
    public static ArrayList<MolecularStructure> getVitalesAnomaly(List<MolecularStructure> sourceStructures, List<MolecularStructure> targetStructures) {
        ArrayList<MolecularStructure> anomalyList = new ArrayList<>();
        for (MolecularStructure targetStructure : targetStructures) {
            if (!sourceStructures.contains(targetStructure)) {
                anomalyList.add(targetStructure);
            }
        }
        /* YOUR CODE HERE */ 

        return anomalyList;
    }

    // Method to print Vitales anomalies
    public void printVitalesAnomaly(List<MolecularStructure> molecularStructures) {
        System.out.println("Molecular structures unique to Vitales individuals:");
        for (MolecularStructure structure : molecularStructures) {
            List<Molecule> molecules = structure.getMolecules();
            System.out.print("[");
            for (int i = 0; i < molecules.size(); i++) {
                System.out.print(molecules.get(i).getId());
                if (i < molecules.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        /* YOUR CODE HERE */ 

    }
}
