import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Point;
import java.util.regex.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.*;

public class Day3Pt2 {

    private static final Logger LOG = Logger.getLogger(Day3Pt2.class.getName());
    private static FileHandler fh;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("day3_input.txt")); 
        int gearRatioSum = 0;
        ArrayList<char[]> schematic = new ArrayList<char[]>(); 
        Map<Integer,Point> gearMap = new HashMap<Integer,Point>();
        String line = null;
        int lineInd = 0;
        int gearCount = 0;

        try {
            fh = new FileHandler("day3pt2.log");
            LOG.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
            Pattern pattern = Pattern.compile("[*]");
            while((line = br.readLine()) != null) {
                char[] row = new char[line.length()]; 
                for(int i = 0; i < line.length(); i++) {
                    if(pattern.matcher(""+line.charAt(i)).find()) {
                        gearMap.put(Integer.valueOf(gearCount), new Point(i, lineInd));
                        gearCount++;
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
                LOG.info("\n checking gear "+i+" in line "+gearY);
                int gearRatio = calculateGearRatio(gearX, gearY, schematic2D);
                gearRatioSum += gearRatio;
                LOG.info("Current gear ratio sum: " + gearRatioSum);
            }

        LOG.info("\n Sum of gear ratios: "+gearRatioSum);
        }catch(IOException e) {
            LOG.info(e.getMessage());
        }finally {
            br.close();
        }
    }

    public static int calculateGearRatio(int gearX, int gearY, char[][] schematic) {

        int gearRatio = 0;
        int bufferStartX = gearX - 1;
        int bufferStartY = gearY - 1;
        int bufferEndX = gearX + 1;
        int bufferEndY = gearY + 1;

        LOG.info("X: " + gearX + " , Y:" + gearY); 
        // accounting for edge cases
        // top of file
        if (gearY <= 0) { 
            bufferStartY = gearY;
        }
        // bottom of file
        if (gearY >= schematic.length-1) {
            bufferEndY = gearY;
        }
        // left
        if (gearX <= 0) {
            bufferStartX = gearX;
        }
        // right
        if (gearX >= schematic[0].length - 4) {
            bufferEndX = gearX;
        }
        LOG.info("bufferStartY: " + bufferStartY + " , bufferEndY: " + bufferEndY);
        LOG.info("bufferStartX: " + bufferStartX + " , bufferEndX: " + bufferEndX);

        Pattern digit = Pattern.compile("[0-9]");
 /*       
        String numOne = "";
        String numTwo = "";
        boolean numOneDone = false;
        boolean numTwoDone = false;
        */
        String number = "";
        int startInd = 0, endInd = 0;
        List<Integer> adjacentNumList = new ArrayList<Integer>();

        for (int y = bufferStartY; y <= bufferEndY; y++) {
            for (int x = bufferStartX; x <= bufferEndX; x++) {
                if(digit.matcher(""+schematic[y][x]).find()) {
                    // adjacent num found, complete num
                    number = findNum(x, schematic[y]); 
                    LOG.info("num creating.. : " + number);
                    adjacentNumList.add(Integer.valueOf(number));
                    x = x + number.length();
                }
            }
        }

        if(adjacentNumList.size() == 2) {
            gearRatio = adjacentNumList.get(0) * adjacentNumList.get(1);
        }
/*
                if((endPattern.matcher(""+schematic[y][x]).find() || x == bufferEndX) && !number.isEmpty()) {
                    endInd = x-1;
                    if(x == bufferEndX) {
                        endInd = x;
                    }
                    LOG.info("formed no: " + number + " with startX: " + startInd + ", endX: " + endInd);
                    if(startInd <= gearX+1 && endInd >= gearX-1 && !numOneDone) {
                        LOG.info("Formed first Num: " + number);
                        numOneDone = true;
                        numOne = number;
                    } else if (startInd <= gearX+1 && endInd >= gearX-1 && !numTwoDone) {
                        LOG.info("formed second Num: " + number);
                        numTwoDone = true;
                        numTwo = number;
                    }
                    number = "";
                    startInd = 0;
                    endInd = 0;
                }
            }
        }

        LOG.info("Num1: " + numOne + " , " + "Num2: " + numTwo);

        if(!numOne.isEmpty() && !numTwo.isEmpty()) {
            gearRatio = Integer.parseInt(numOne) * Integer.parseInt(numTwo);
        }
*/
        LOG.info("gearRatio: " + gearRatio);
        LOG.info("==================================================================================");
        return gearRatio;
    }

    public static String findNum(int x, char[] schematicLine) {
        StringBuffer sb = new StringBuffer();
        int xPos = x;
        LOG.info("xPos = " + xPos);
        Pattern endPattern = Pattern.compile("[^0-9]");
        while(!endPattern.matcher(""+schematicLine[xPos]).find() && xPos>0) {
            xPos--;
            LOG.info("xPos = " + xPos);
        }
        if(endPattern.matcher(""+schematicLine[xPos]).find()) {
            xPos++;
        }
        while(!endPattern.matcher(""+schematicLine[xPos]).find() && xPos<schematicLine.length) {
            sb.append(schematicLine[xPos]);
            xPos++;
            LOG.info("xPos = " + xPos);
        }
        return sb.toString();
    }
}
