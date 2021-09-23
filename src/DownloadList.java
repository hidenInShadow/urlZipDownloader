import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadList {

    public static void downloadList(ArrayList<String> listOfUrl) {
        try {
            URL myObj = new URL("http://aloteq-test-tasks.s3-website.eu-central-1.amazonaws.com/list.txt");
            Scanner myReader = new Scanner(myObj.openStream());
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                listOfUrl.add(data);
                System.out.println(data);
            }
            myReader.close();

            DownloadZip downloadZip = new DownloadZip();
            downloadZip.zipDownloader(listOfUrl);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
