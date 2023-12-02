import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Day1 {
    public static void main(String[] args) throws Exception {
        BufferedReader file1 = new BufferedReader(new FileReader("day1_input.txt"));
        try{
            String line = null;
            String firstDigit = "";
            String lastDigit = "";
            int sum = 0;
            while((line = file1.readLine()) != null) {
                for(int i = 0; i < line.length(); i++) {
                    if(Character.digit(line.charAt(i),10) >= 0) {
                        firstDigit = line.substring(i,i+1);
                        break;
                    }
                }
                for(int i = line.length()-1; i >=0; i--) {
                    if(Character.digit(line.charAt(i),10) >= 0) {
                        lastDigit = line.substring(i,i+1);
                        break;
                    }
                }
                String lineSum = firstDigit+lastDigit;
                sum += Integer.parseInt(lineSum);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            file1.close();
        }
    }
}
