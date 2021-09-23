import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadZip {
    private static final int numOfThreads = 3;
    public void zipDownloader(ArrayList<String> listOfUrl) {
        ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);
        for (String element : listOfUrl) {
            executorService.execute(new Runnable() {
                public void run() {
                    String pathname = "./" + element.substring(element.lastIndexOf("/")); // I know, this solution with path is sordid, but i can't find better solution
                    try (BufferedInputStream inputStream = new BufferedInputStream(new URL(element).openStream());
                         FileOutputStream fileOS = new FileOutputStream(pathname)) {
                        byte[] data = new byte[1024];
                        int byteContent;
                        while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                            fileOS.write(data, 0, byteContent);
                        }
                        File f = new File(pathname);
                        if (f.exists()) {
                            System.out.println("Name: " + f.getName());
                            System.out.println("Size: " + f.length() + " bytes");
                        } else {
                            System.out.println("The File does not exist");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }executorService.shutdown();
    }
}
