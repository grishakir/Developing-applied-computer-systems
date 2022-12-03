import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static long folderSize(File directory) {
        long size = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                size += file.length();
            else
                size += folderSize(file);
        }
        return size;
    }
    public static void main(String[] args) {
        try {
            File file = new File(args[0]);
            long size = folderSize(file);
            System.out.println(args[0] + " --------- " + size + " bytes");
        } catch (NullPointerException e) {
            System.out.println("Dir error");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There's not enough arguments");
        }
    }
}