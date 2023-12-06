import java.io.FileReader;
import java.io.FileBuffer;
import java.io.IOException;
import java.awt.Point;

public class Day3 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new FileReader("day3_input.txt")); 
        int validPartNoSum = 0;
        ArrayList<char[]> schematic = new ArrayList<char[]>(); 
        String line = null;

        try {
            while((line = br.readLine()) != null) {
                char[] row = new char[line.length()]; 
                for(int i = 0; i < line.length(); i++) {
                    row[i] = line.charAt(i);
                }
                schematic.add(row);
            }
            Character[][] schematic2D = new Character[schematic.size()][];
            schematic2D = schematic.toArray(schematic2D);
            for(int i = 0; i < schematic2D.length; i++) {
                Point wordStart = null;
                Point wordEnd = null;
                for(int j = 0; j < schematic[i].length; j++) {
                    char schematicChar = schematic[i][j];
                    if(Character.digit(schematicChar,10)>=0) {
                        if(lineNo.isEmpty()) {
                           wordStart = new Point(i,j); 
                        }
                        lineNo = lineNo + schematicChar;
                    } else if(!lineNo.isEmpty()){
                        validPartNoSum += Integer.parseInt(lineNo);
                        wordEnd = new Point(i,j);
                    }
                }
            }
            System.out.println("Sum of schematic part numbers: "+validPartNoSum);
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }finally {
            br.close();
        }
    }

    public boolean
}
