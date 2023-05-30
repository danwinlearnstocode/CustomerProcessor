import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class CustomerProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String filePath = "/Users/danwinambros-francis/IdeaProjects/CustomerProcessor/newCustomers.csv";

        try {
            File customerFile = new File(filePath);
            Scanner fr = new Scanner(customerFile);
            String customerRecord;
            int recordLen = 4;

            while (fr.hasNext()){
                customerRecord = fr.nextLine();

                String [] temp = new String[recordLen];
                temp = customerRecord.split(",");
                String tempDate = temp[recordLen-1];
                Date df = new SimpleDateFormat("yyyyMMdd").parse(tempDate);

                Boolean valInd;

                //This step calculates user age.
                int userAge = calcAge(LocalDate.ofInstant(df.toInstant(), ZoneId.systemDefault()));
                String custKey = generateCustomerKey(temp[2],temp[0],temp[1]);

                System.out.println(custKey + "|" + "|" + userAge + "|" + customerRecord);

            }
        } catch (FileNotFoundException fe) {
            System.out.println(fe + " not found. Check path and update accordingly.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    private static String generateCustomerKey(String s, String s1, String s2) {
        int lastLen, firstLen;
        lastLen = s.length();
        firstLen = s1.length();

        String req1 = s.substring(0, 4);
        String req2 = Integer.toString(lastLen = req1.length());
        String req3 = String.valueOf(s1.charAt(0));
        String req4 = Integer.toString(firstLen = req3.length());
        String req5;
        if (s2 == ""){
            req5 = "0";
        } else {
            req5 = String.valueOf(s2.charAt(0));
        }

        return req1 + req2 + req3 + req4 + req5;
    }

    public static int calcAge (LocalDate dob) {
        LocalDate curDate = LocalDate.now();
        return Period.between(dob, curDate).getYears();
    }
}