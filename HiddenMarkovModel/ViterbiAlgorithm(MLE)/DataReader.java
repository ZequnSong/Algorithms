import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Data Read Class
 * Get Original Data From Given URL
 *
 * @author Zequn Song
 * GWid: G37811414
 * email: zsong73@gwu.edu
 */
public class DataReader {
    public static Object[] getData(String fileName){
        //get file's URL
        FileReader file = null;
        try {
            file = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(file);
        Object[] data = new Object[3];
        double p;//not Switch Probability
        double[][] ep = new double[3][3];//emission Probability matrix
        int[] e;//emission(observations) sequence
        try {
            String line;
            //Read data line by line
            while ((line = br.readLine()) != null) {
                // get p : not Switch Probability
                if (line.equals("# Probability of not switching")) {
                    line = br.readLine();
                    p = Double.valueOf(line);
                    data[0] = p;
                    continue;
                }
                //get ep : emission Probability matrix
                if (line.equals("# Emission probabilities")) {
                    int i =0;
                    String []sp;
                    while(i<=2){
                        line = br.readLine();

                        sp = line.split(",");
                        ep[i][0]= Double.valueOf(sp[0]);
                        ep[i][1]= Double.valueOf(sp[1]);
                        ep[i][2]= Double.valueOf(sp[2]);
                        i++;
                    }
                    data[1] = ep;
                    continue;
                }
                //get e : emission(observations) sequence
                if (line.equals("# Emissions")) {
                    String []sp;
                    line = br.readLine();
                    sp = line.substring(1,line.length()-1).split(", ");
                    e = new int[sp.length];
                    int i = 0;
                    while(i<sp.length){
                        e[i] = Integer.valueOf(sp[i]);
                        i++;
                    }
                    data[2] = e;
                }
            }
        }catch (IOException e1) {
            e1.printStackTrace();
        }
        return data;
    }
}
