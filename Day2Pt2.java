import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class Day1 {

    public static void main(String[] args) throws Exception {
        
        BufferedReader file1 = new BufferedReader(new FileReader("day2_input.txt"));
        try{
            String line = null;
            int powerSum = 0;
            
            while((line = file1.readLine()) != null) {
                String[] headerBody = line.split(":");
                //int gameNo = Integer.parseInt(headerBody[0].split(" ")[1]);
                String[] bodyData = headerBody[1].split(";");
                int minRed = 0;
                int minGreen = 0;
                int minBlue = 0;

                for(int i = 0; i < bodyData.length; i++) {
                    String[] bodyDataSet = bodyData[i].split(",");
                    for(int j = 0; j < bodyDataSet.length; j++) {
                        String gameSet = bodyDataSet[j];
                        gameSet.replaceAll("\\s","");
                        //SELF REMINDER: REGEX NEGATING CARROT GOES INSIDE []
                        if(bodyDataSet[j].indexOf("red")!=-1) {
                            int redCount = Integer.parseInt(bodyDataSet[j].replaceAll("[^0-9]", ""));
                            if(redCount>minRed) {
                                minRed = redCount;
                            }
                        }else if(bodyDataSet[j].indexOf("green")!=-1) {
                            int greenCount = Integer.parseInt(bodyDataSet[j].replaceAll("[^0-9]", ""));
                            if(greenCount>minGreen) {
                                minGreen = greenCount;
                            }
                        }else if(bodyDataSet[j].indexOf("blue")!=-1) {
                            int blueCount = Integer.parseInt(bodyDataSet[j].replaceAll("[^0-9]", ""));
                            if(blueCount>minBlue) {
                                minBlue = blueCount;
                            }
                        }
                    }
                }
                powerSum += minRed*minGreen*minBlue;
            }

            System.out.println("Sum of powers: "+powerSum);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            file1.close();
        }
    }
}
