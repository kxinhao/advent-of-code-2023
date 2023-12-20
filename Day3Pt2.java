import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Point;
import java.util.regex.*;
import java.util.ArrayList;

public class Day3Pt2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("day3_input.txt")); 
        FileWriter fw = new FileWriter("Day3Pt2_Output.txt");
        int gearRatioSum = 0;
        ArrayList<char[]> schematic = new ArrayList<char[]>(); 
        Map<Integer,Point> gearMap = new HashMap<Integer,Point>();
        String line = null;
        int lineInd = 0;
        int gearCount = 0;

        try {
            Pattern pattern = Pattern.compile("[*]");
            while((line = br.readLine()) != null) {
                char[] row = new char[line.length()]; 
                for(int i = 0; i < line.length(); i++) {
                    if(pattern.matcher(""+line.CharAt(i)).find()) {
                        gearCount++;
                        gearMap.add(gearCount, new Point(lineInd, i));
                    }
                    row[i] = line.charAt(i);
                }
                schematic.add(row);
                lineInd++;
            }
            char[][] schematic2D = new char[schematic.size()][];
            schematic2D = schematic.toArray(schematic2D);
            // check each gear for adjacent numbers
            for(int i = 0; i < gearMap.size(); i++) {
                Point gearCoords = gearMap.get(i);
                int gearX = (int)gearCoords.getX();
                int gearY = (int)gearCoords.getY();
                int gearRatio = getGearRatio();
                gearRatioSum += gearRatio;
            }

            for(int i = 0; i < schematic2D.length; i++) {
                Point wordStart = null;
                Point wordEnd = null;
                String lineNo = "";
                for(int j = 0; j < schematic2D[i].length; j++) {
                    char schematicChar = schematic2D[i][j];
                    int xPoint = j - 1;
                    int yPoint = i;
                    //System.out.println("checking char: " + schematicChar);
                    if(Character.digit(schematicChar,10)>=0) {
                        if(lineNo.isEmpty()) {
                            wordStart = new Point(j,i); 
                        }
                        lineNo = lineNo + schematicChar;
                        // for nums with last digit at EOL
                        if(j == schematic2D[i].length - 1) {
                            xPoint = j;
                            wordEnd = new Point(xPoint, yPoint);
                            fw.write("\n !! completed eol number is: " + lineNo);
                            if(isValidNum(wordStart, wordEnd, schematic2D)) {
                                fw.write("\n lineNo: " + lineNo + " is valid, adding to sum: " + validPartNoSum);
                                validPartNoSum += Integer.parseInt(lineNo);
                            } else {
                                fw.write("\n lineNo: " + lineNo + " is not valid");
                            }
                            lineNo = "";
                        }
                    } else if(!lineNo.isEmpty()){
                        wordEnd = new Point(xPoint, yPoint);
                        fw.write("\n !! completed number is: " + lineNo);
                        if(isValidNum(wordStart, wordEnd, schematic2D)) {
                            fw.write("\n lineNo: " + lineNo + " is valid, adding to sum: " + validPartNoSum);
                            validPartNoSum += Integer.parseInt(lineNo);
                        } else {
                            fw.write("\n lineNo: " + lineNo + " is not valid");
                        }
                        lineNo = "";
                    } 
                }
                fw.write("\n ###### end processing of line " + (i+1) + ", valid value is: " + validPartNoSum);
            }
            fw.write("\n Sum of schematic part numbers: "+validPartNoSum);
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }finally {
            br.close();
            fw.close();
        }
    }

    public static boolean isValidNum(Point startPoint, Point endPoint, char[][] schematic) {

        // accounting for single digit numbers
        int startX = (int)startPoint.getX();
        int startY = (int)startPoint.getY();
        int endX = (int)endPoint.getX();
        int endY = (int)endPoint.getY();
        //System.out.println("start coords are: " + startX + ", " + startY);
        //System.out.println("end coords are: " + endX + ", " + endY);
        int bufferStartX = startX - 1;
        int bufferStartY = startY - 1;
        int bufferEndX = endX + 1;
        int bufferEndY = endY + 1;

        boolean isValidNum = false;

        // accounting for edge cases
        // top of file
        if (startY <= 0 || endY <= 0) { 
            bufferStartY = startY;
        }
        // bottom of file
        if (startY >= schematic.length-1 || endY >= schematic.length-1) {
            bufferEndY = endY;
        }
        // left
        if (startX <= 0 || endX <= 0) {
            bufferStartX = startX;
        }
        // right
        if (startX >= schematic[0].length - 1 || endX >= schematic[0].length - 1) {
            bufferEndX = endX;
        }

        Pattern pattern = Pattern.compile("[^0-9.]");

        for (int y = bufferStartY; y <= bufferEndY; y++) {
            for (int x = bufferStartX; x <= bufferEndX; x++) {
                if(pattern.matcher(""+schematic[y][x]).find()) {
                    isValidNum = true;
                    return isValidNum;
                }
            }
        }
        return isValidNum;
    }
}
