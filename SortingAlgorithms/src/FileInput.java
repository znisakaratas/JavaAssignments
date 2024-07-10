import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInput {
    public static int[] getData(String fileName){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<Integer> trafficFlow = new ArrayList<>();
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null){
                String[] splitted = line.split(",");
                trafficFlow.add(Integer.parseInt(splitted[6]));
            }
            int[] result = new int[trafficFlow.size()];
            for (int i = 0; i < trafficFlow.size(); i++) {
                result[i] = trafficFlow.get(i);
            }
            return result;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
