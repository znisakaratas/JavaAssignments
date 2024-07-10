// Class representing the mission of Genesis
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissionGenesis {


    // Private fields
    private MolecularData molecularDataHuman; // Molecular data for humans
    private MolecularData molecularDataVitales; // Molecular data for Vitales

    // Getter for human molecular data
    public MolecularData getMolecularDataHuman() {
        return molecularDataHuman;
    }

    // Getter for Vitales molecular data
    public MolecularData getMolecularDataVitales() {
        return molecularDataVitales;
    }
    public void readXML(String filename) {
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList humanList = doc.getElementsByTagName("HumanMolecularData");
            Node humanNode = humanList.item(0);
            NodeList humanMoleculeList = ((Element) humanNode).getElementsByTagName("Molecule");
            List<Molecule> humanMolecules = parseMolecules(humanMoleculeList);
            NodeList vitalesList = doc.getElementsByTagName("VitalesMolecularData");
            Node vitalesNode = vitalesList.item(0);
            NodeList vitalesMoleculeList = ((Element) vitalesNode).getElementsByTagName("Molecule");
            List<Molecule> vitalesMolecules = parseMolecules(vitalesMoleculeList);
            molecularDataHuman = new MolecularData(humanMolecules);
            molecularDataVitales = new MolecularData(vitalesMolecules);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private List<Molecule> parseMolecules(NodeList nodeList) {
        List<Molecule> molecules = new ArrayList<>();
        Map<String, Molecule> moleculeMap = new HashMap<>(); // Map to store molecules by ID
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String id = element.getElementsByTagName("ID").item(0).getTextContent();
                int bondStrength = Integer.parseInt(element.getElementsByTagName("BondStrength").item(0).getTextContent());
                NodeList bondsList = element.getElementsByTagName("Bonds");
                List<String> bonds = new ArrayList<>();
                for (int j = 0; j < bondsList.getLength(); j++) {
                    Node bondNode = bondsList.item(j);
                    if (bondNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element bondElement = (Element) bondNode;
                        NodeList moleculeIdList = bondElement.getElementsByTagName("MoleculeID");
                        for (int k = 0; k < moleculeIdList.getLength(); k++) {
                            String bondId = moleculeIdList.item(k).getTextContent();
                            bonds.add(bondId);
                        }
                    }
                }
                Molecule molecule = moleculeMap.get(id);
                if (molecule == null) {
                    molecule = new Molecule(id, bondStrength, bonds);
                    molecules.add(molecule);
                    moleculeMap.put(id, molecule);
                }
            }

        }
        return molecules;
    }
}

