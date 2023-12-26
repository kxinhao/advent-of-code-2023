import java.io.*;
import java.awt.Point;
import java.util.*;
import java.util.regex.*;
import java.util.logging.*;

public class Day3Pt2 {

    private static final Logger LOG = Logger.getLogger(Day3Pt2.class.getName());
    private static FileHandler fh;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("day3_input.txt"));
        int gearRatioSum = 0;
        ArrayList<char[]> schematic = new ArrayList<char[]>();
        Map<Integer, Point> gearMap = new HashMap<Integer, Point>();
        String line = null;
        int lineInd = 0;
        int gearCount = 0;

        try {
            fh = new FileHandler("day3pt2.log");
            LOG.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
            Pattern pattern = Pattern.compile("[*]");
            while ((line = br.readLine()) != null) {
                char[] row = new char[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    if (pattern.matcher("" + line.charAt(i)).find()) {
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
            for (int i = 0; i < gearMap.size(); i++) {
                Point gearCoords = gearMap.get(i);
                int gearX = (int) gearCoords.getX();
                int gearY = (int) gearCoords.getY();
                LOG.info("\n ================================================================");
                LOG.info("\n checking gear " + (i+1) + " in line " + (gearY+1));
                int gearRatio = calculateGearRatio(gearX, gearY, schematic2D);
                gearRatioSum += gearRatio;
                LOG.info("Current gear ratio sum: " + gearRatioSum);
            }

            LOG.info("\n Sum of gear ratios: " + gearRatioSum);
        } catch (IOException e) {
            LOG.info(e.getMessage());
        } finally {
            br.close();
        }
    }

    public static int calculateGearRatio(int gearX, int gearY, char[][] schematic) {

        int gearRatio = 0;
        int bufferStartX = gearX - 1;
        int bufferStartY = gearY - 1;
        int bufferEndX = gearX + 1;
        int bufferEndY = gearY + 1;

        LOG.info("X: " + (gearX+1) + " , Y:" + (gearY+1));
        // accounting for edge cases
        // top of file
        if (gearY <= 0) {
            bufferStartY = gearY;
        }
        // bottom of file
        if (gearY >= schematic.length - 1) {
            bufferEndY = gearY;
        }
        // left
        if (gearX <= 0) {
            bufferStartX = gearX;
        }
        // right
        if (gearX >= schematic[0].length - 1) {
            bufferEndX = gearX;
        }
        LOG.info("bufferStart: (" + (bufferStartX+1) + ", " + (bufferStartY+1) + ") , bufferEnd: (" + (bufferEndX+1) +", " + (bufferEndY+1)+")");

        Pattern digit = Pattern.compile("[0-9]");
        List<Integer> adjacentNumList = new ArrayList<Integer>();

        for (int y = bufferStartY; y <= bufferEndY; y++) {
            for (int x = bufferStartX; x <= bufferEndX; x++) {
                LOG.info("buffer check position: (" + (x+1) + ", " + (y+1) + ")");
                if (digit.matcher("" + schematic[y][x]).find()) {
                    // adjacent num found, complete num
                    int xPos = findNum(x, schematic[y], adjacentNumList);
                    x = xPos;
                }
            }
        }

        if (adjacentNumList.size() == 2) {
            gearRatio = adjacentNumList.get(0) * adjacentNumList.get(1);
        }
        LOG.info("gearRatio: " + gearRatio);
        return gearRatio;
    }

    // using position of digit adjacent to gear, travel back and forth to form number and return x value after num
    public static int findNum(int x, char[] schematicLine, List<Integer> adjacentNumList) {
        StringBuffer sb = new StringBuffer();
        int xPos = x;
        Pattern endPattern = Pattern.compile("[^0-9]");
        while (xPos > 0 && !endPattern.matcher("" + schematicLine[xPos]).find()) {
            xPos--;
        }
        if (endPattern.matcher("" + schematicLine[xPos]).find()) {
            xPos++;
        }
        while (xPos < schematicLine.length && !endPattern.matcher("" + schematicLine[xPos]).find()) {
            sb.append(schematicLine[xPos]);
            xPos++;
        }
        LOG.info("Num found is: " + sb.toString());
        // increment x value by number length; fix to prevent duplicate count and overshot of x increment
        x = Math.min(xPos, schematicLine.length-1);
        LOG.info("Next x position is: " + x);
        adjacentNumList.add(Integer.valueOf(sb.toString()));
        return x; 
    }
}
