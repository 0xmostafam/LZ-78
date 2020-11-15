import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

public class Main {
    public static String fileToCompress = "/home/mostafa/Downloads/longString.txt";
    public static String compressedFile = "/home/mostafa/Downloads/compressed.txt";
    public static String decompressedFile = "/home/mostafa/Downloads/decompressed.txt";
    public static void main(String[] args) {
        String content = null;
        try {
            createFile(compressedFile);
            createFile(decompressedFile);
            Path orginalPath = Paths.get(fileToCompress);
            Path decompressPath = Paths.get(compressedFile);
            content = Files.readString(orginalPath , StandardCharsets.UTF_8);
            LZ78 compressor = new LZ78();
            compressor.compress(content);
            writeToFile(compressor.returnTags() , compressedFile);
            content = Files.readString(decompressPath , StandardCharsets.UTF_8);
            writeToFile(compressor.decompress(content) , decompressedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFile(String path){
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void writeToFile(String data , String path){
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
