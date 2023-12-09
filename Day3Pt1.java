import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Point;
import java.util.regex.*;
import java.util.ArrayList;

public class Day3 {
    public static void main(String[] args) throws Exception {
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
            char[][] schematic2D = new char[schematic.size()][];
            schematic2D = schematic.toArray(schematic2D);
            for(int i = 0; i < schematic2D.length; i++) {
                System.out.println("********** processing line " + i);
                Point wordStart = null;
                Point wordEnd = null;
                String lineNo = "";
                for(int j = 0; j < schematic2D[i].length; j++) {
                    char schematicChar = schematic2D[i][j];
                    //System.out.println("checking char: " + schematicChar);
                    if(Character.digit(schematicChar,10)>=0) {
                        if(lineNo.isEmpty()) {
                           wordStart = new Point(j,i); 
                        }
                        lineNo = lineNo + schematicChar;
                    } else if(!lineNo.isEmpty()){
                        int xPoint = j - 1;
                        int yPoint = i;
                        wordEnd = new Point(xPoint, yPoint);
                        System.out.println("completed number is: " + lineNo);
                        System.out.println("checking num validity");
                        if(isValidNum(wordStart, wordEnd, schematic2D)) {
                            System.out.println("lineNo: " + lineNo + " is valid, adding to sum: " + validPartNoSum);
                            validPartNoSum += Integer.parseInt(lineNo);
                        } else {
                            System.out.println("lineNo: " + lineNo + " is not valid");
                        }
                        lineNo = "";
                        System.out.println("checking cleared string buffer for lineNo: " + lineNo);
                    }
                }
                System.out.println("valid line sum of line " + i + " is: " + validPartNoSum);
                System.out.println("########## end processing for line: " + i);
            }
            System.out.println("Sum of schematic part numbers: "+validPartNoSum);
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }finally {
            br.close();
        }
    }

    public static boolean isValidNum(Point startPoint, Point endPoint, char[][] schematic) {

        System.out.println("!!! entered isValidNum method");
        // accounting for single digit numbers
        int startX = (int)startPoint.getX();
        int startY = (int)startPoint.getY();
        int endX = (int)endPoint.getX();
        int endY = (int)endPoint.getY();
        System.out.println("start coords are: " + startX + ", " + startY);
        System.out.println("end coords are: " + endX + ", " + endY);

        boolean isValidNum = false;

        Pattern pattern = Pattern.compile("[^0-9.\s]");

        // 1 space buffer minimum between edges of file and number
        if(startY > 0 && endY < schematic.length-1 && startX > 0 && endX < schematic[0].length-1) {
            System.out.println("$$ entered non-edge case");
            //numbuffer:
            for(int y = startY-1; y <= endY + 1; y++) {
                for(int x = startX-1; x <= endX + 1; x++) {
                    if(pattern.matcher(""+schematic[y][x]).find()) {
                        isValidNum = true;
                        //break numbuffer;
                        return isValidNum;
                    }
                }
            }
        // top of file
        } else if(startY <= 0 || endY <= 0) {
            // top left of file
            if(startX <= 0 || endX <= 0) { 
                System.out.println("$$ entered top left case");
                for(int y = startY; y <= endY + 1; y++) {
                    for(int x = startX; x <= endX + 1; x++) {
                        if(pattern.matcher(""+schematic[y][x]).find()) {
                            isValidNum = true;
                            return isValidNum;
                        }
                    }
                }
             // top right of file
            } else if(startX >= schematic[0].length-1 || endX >= schematic[0].length-1) {  
                System.out.println("$$ entered top right case");
                for(int y = startY; y <= endY + 1; y++) {
                    for(int x = startX-1; x <= endX; x++) {
                        if(pattern.matcher(""+schematic[y][x]).find()) {
                            isValidNum = true;
                            return isValidNum;
                        }
                    }
                }
            } else {
                System.out.println("$$ entered top line case");
                for(int y = startY; y <= endY + 1; y++) {
                    for(int x = startX - 1; x <= endX + 1; x++) {
                        if(pattern.matcher(""+schematic[y][x]).find()) {
                            isValidNum = true;
                            return isValidNum;
                        }
                    }
                }
            }

        // bottom of file
        } else if(startY >= schematic.length-1 || endY >= schematic.length-1) {
            // && bottom left of file ||
            if(startX <= 0 || endX <= 0) { 
                System.out.println("$$ entered bottom left case");
                for(int y = startY - 1; y <= endY; y++) {
                    for(int x = startX; x <= endX + 1; x++) {
                        if(pattern.matcher(""+schematic[y][x]).find()) {
                            isValidNum = true;
                            return isValidNum;
                        }
                    }
                }
            // && bottom right of file
            } else if(startX >= schematic[0].length-1 || endX >= schematic[0].length-1) {  
                System.out.println("$$ entered bottom right case");
                for(int y = startY - 1; y <= endY; y++) {
                    for(int x = startX - 1; x <= endX; x++) {
                        if(pattern.matcher(""+schematic[y][x]).find()) {
                            isValidNum = true;
                            return isValidNum;
                        }
                    }
                }
            } else {
                System.out.println("$$ entered bottom line case");
                for(int y = startY - 1; y <= endY; y++) {
                    for(int x = startX - 1; x <= endX + 1; x++) {
                        if(pattern.matcher(""+schematic[y][x]).find()) {
                            isValidNum = true;
                            return isValidNum;
                        }
                    }
                }
            }
        // leftmost of file
        } else if(startX <= 0 || endX <= 0) {
            System.out.println("$$ entered leftmost case");
            for(int y = startY - 1; y <= endY + 1; y++) {
                for(int x = startX; x <= endX + 1; x++) {
                    if(pattern.matcher(""+schematic[y][x]).find()) {
                            isValidNum = true;
                            return isValidNum;
                    }
                }
            }
        // rightmost of file
        } else if(startX >= schematic[0].length-1 || endX >= schematic[0].length-1) {
            System.out.println("$$ entered rightmost case");
            for(int y = startY - 1; y <= endY + 1; y++) {
                for(int x = startX - 1; x <= endX; x++) {
                    if(pattern.matcher(""+schematic[y][x]).find()) {
                            isValidNum = true;
                            return isValidNum;
                    }
                }
            }
        }

        return isValidNum;
    }
}
