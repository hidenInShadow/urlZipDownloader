
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class urlZipDownloader {

    public static void main(String args[]) {
        int i = 0;
        ArrayList<String> listOfUrl = new ArrayList<String>();
        try {
            File myObj = new File("/Users/Vitali/Desktop/listOfUrls.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                listOfUrl.add(data);
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for(String element : listOfUrl) {
            try (BufferedInputStream inputStream = new BufferedInputStream(new URL(element).openStream());
                 FileOutputStream fileOS = new FileOutputStream("/Users/Vitali/Desktop/test" + element.substring(element.lastIndexOf("/"), element.length()))) {
                byte data[] = new byte[1024];
                int byteContent;
                while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                    fileOS.write(data, 0, byteContent);
                }
                File f = new File("/Users/Vitali/Desktop/test" + element.substring(element.lastIndexOf("/"), element.length()));
                if(f.exists()){
                    System.out.println("Name: "+ f.getName());
                    //System.out.println("Path: "+ f.getAbsolutePath());
                    System.out.println("Size: "+ f.length() +" bytes");
                    //System.out.println("Writeable: "+ f.canWrite());
                    //System.out.println("Readable: "+ f.canRead());
                }else{
                    System.out.println("The File does not exist");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

