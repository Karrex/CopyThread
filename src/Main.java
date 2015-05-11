import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Copier copier = new Copier();
        copier.copyFiles(new File(inputSourcePath()), new File(inputDestinationPath()));
    }

    private static String inputSourcePath() {
        Scanner scan = new Scanner(System.in);
        String sourcePath;
        System.out.println("Enter Path Source Folder");
        sourcePath = scan.nextLine();
        return sourcePath;
    }

    private static String inputDestinationPath() {
        Scanner scan = new Scanner(System.in);
        String destinationPath;
        System.out.println("Enter Path Destination Folder");
        destinationPath = scan.nextLine();
        return destinationPath;
    }
}