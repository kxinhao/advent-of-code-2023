import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Day1 {
    public static void main(String[] args) throws Exception {

        BufferedReader file1 = new BufferedReader(new FileReader("day1_input.txt"));

        try{
            String line = null;
            String firstDigit = "";
            String lastDigit = "";
            int sum = 0;

            HashMap<Integer, String> numberMap = new HashMap<Integer,String>();  
            mapNumbers(numberMap);

            while((line = file1.readLine()) != null) {
                int firstDigitInd = 0;
                int lastDigitInd = 0;
                int firstNoStrInd = line.length()-1;
                int lastNoStrInd = 0;

                System.out.println("###############################");
                System.out.println("## Line read is: " + line);
                for(int i = 0; i < line.length(); i++) {
                    if(Character.digit(line.charAt(i),10) >= 0) {
                        firstDigit = line.substring(i,i+1);
                        firstDigitInd = i;
                        System.out.println("firstDigit: "+firstDigit+" at index: "+firstDigitInd);
                        break;
                    }
                }
                for(int i = line.length()-1; i >=0; i--) {
                    if(Character.digit(line.charAt(i),10) >= 0) {
                        lastDigit = line.substring(i,i+1);
                        lastDigitInd = i;
                        System.out.println("lastDigit: " + lastDigit+" at index: "+lastDigitInd);
                        break;
                    }
                }
                //checking word representation of number
                for(int i = 1; i <= numberMap.size(); i++) {
                    int firstNoInd = line.indexOf(numberMap.get(i)); 
                    int lastNoInd = line.lastIndexOf(numberMap.get(i));
                    if(firstNoInd == -1 || lastNoInd == -1) {
                        continue;
                    }
                    if(firstNoInd<firstDigitInd && firstNoInd<firstNoStrInd) {
                        firstNoStrInd = firstNoInd;
                        firstDigit = String.valueOf(i);
                        System.out.println("word number found before first digit, replacing as: " + firstDigit+" at index: "+firstNoStrInd);
                    }
                    if(lastNoInd>lastDigitInd && lastNoInd>lastNoStrInd) {
                        lastNoStrInd = lastNoInd;
                        lastDigit = String.valueOf(i);
                        System.out.println("word number found after last digit, replacing as: " + lastDigit+" at index: "+lastNoStrInd);
                    }
                }
                String lineSum = firstDigit+lastDigit;
                System.out.println("lineSum: " + lineSum);
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
                sum += Integer.parseInt(lineSum);
            }
            System.out.println("sum of calibration values is: " + sum);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            file1.close();
        }
    }

    public static void mapNumbers(Map<Integer, String> numberMap) {
        numberMap.put(1, "one");
        numberMap.put(2, "two");
        numberMap.put(3, "three");
        numberMap.put(4, "four");
        numberMap.put(5, "five");
        numberMap.put(6, "six");
        numberMap.put(7, "seven");
        numberMap.put(8, "eight");
        numberMap.put(9, "nine");
    }
}
