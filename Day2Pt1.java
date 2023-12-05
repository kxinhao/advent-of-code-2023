import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class Day2 {
    public static final int RED_LIMIT = 12;
    public static final int GREEN_LIMIT = 13;
    public static final int BLUE_LIMIT = 14;

    public static void main(String[] args) throws Exception {
        
        BufferedReader file1 = new BufferedReader(new FileReader("day2_input.txt"));
        try{
            String line = null;
            int idSum = 0;
            
            while((line = file1.readLine()) != null) {
                String[] headerBody = line.split(":");
                int gameNo = Integer.parseInt(headerBody[0].split(" ")[1]);
                String[] bodyData = headerBody[1].split(";");
                boolean validGame = true;

                for(int i = 0; i < bodyData.length; i++) {
                    String[] bodyDataSet = bodyData[i].split(",");
                    for(int j = 0; j < bodyDataSet.length; j++) {
                        String gameSet = bodyDataSet[j];
                        gameSet.replaceAll("\\s","");
                        //SELF REMINDER: REGEX NEGATING CARROT GOES INSIDE []
                        if(bodyDataSet[j].indexOf("red")!=-1) {
                            int redCount = Integer.parseInt(bodyDataSet[j].replaceAll("[^0-9]", ""));
                            if(redCount>RED_LIMIT) {
                                validGame = false;
                            }
                        }else if(bodyDataSet[j].indexOf("green")!=-1) {
                            int greenCount = Integer.parseInt(bodyDataSet[j].replaceAll("[^0-9]", ""));
                            if(greenCount>GREEN_LIMIT) {
                                validGame = false;
                            }
                        }else if(bodyDataSet[j].indexOf("blue")!=-1) {
                            int blueCount = Integer.parseInt(bodyDataSet[j].replaceAll("[^0-9]", ""));
                            if(blueCount>BLUE_LIMIT) {
                                validGame = false;
                            }
                        }
                    }
                }
                if(validGame) {
                    idSum += gameNo;
                }
            }

            System.out.println("Sum of valid game ids is: "+idSum);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            file1.close();
        }
    }
}
