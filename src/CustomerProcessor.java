import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomerProcessor {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "/Users/danwinambros-francis/IdeaProjects/CustomerProcessor/newCustomers.csv";

        File fr = new File(filePath);

        Scanner readFile = new Scanner(fr);

        while (readFile.hasNextLine()){
            String data = readFile.nextLine();
            System.out.println(data);

            String [] newData = data.split(",");
            
            for (String i : newData) {
                System.out.println(i);
            }
        }
    }
}