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
                String lineNo = "";
                for(int j = 0; j < schematic[i].length; j++) {
                    char schematicChar = schematic[j][i];
                    if(Character.digit(schematicChar,10)>=0) {
                        if(lineNo.isEmpty()) {
                           wordStart = new Point(j,i); 
                        }
                        lineNo = lineNo + schematicChar;
                    } else if(!lineNo.isEmpty()){
                        wordEnd = new Point(j-1,i-1);
                        if(
                        validPartNoSum += Integer.parseInt(lineNo);
                        lineNo = "";
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

    public boolean checkEdges(Point startPoint, Point endPoint, Character[][] schematic) {

        // accounting for single digit numbers
        int startX = (int)startPoint.getX();
        int startY = (int)startPoint.getY();
        int endX = (int)endPoint.getX();
        int endY = (int)endPoint.getY();

        if(startY > 0 && endY < schematic.length-1 && startX > 0 && endX < schematic[0].length-1) {
            
        }

        if(startY <= 0 || endY <= 0) {

        } else if(startY >= schematic.length-1 || endY >= schematic.length-1) {

        } else if(startX <= 0 || endX <= 0) {

        } else if(startX >= schematic[0].length-1 || endX >= schematic[0].length-1) {

        }

    }
}
