import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DirectoryList {
    public static void listRecursive(String relativePath) {
        String basePath = System.getProperty("user.dir");
        String fullPath = basePath + File.separator + relativePath;

        File dir = new File(fullPath);

        if (!dir.exists()) {
            System.out.println("The directory does not exist! " + relativePath);
            return;
        }
        if (!dir.isDirectory()) {
            System.out.println("The path does not belong to any directory! " + relativePath);
            return;
        }

        System.out.println("Recursive listing of directory: " + relativePath);
        listRecursiveHelper(dir, "");
    }

    private static void listRecursiveHelper(File dir, String prefix) {
        File[] contents = dir.listFiles();
        if (contents == null) return;

        Arrays.sort(contents, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));

        for (File file : contents) {
            String type = file.isDirectory() ? "[D]" : "[F]";
            String date = formatDate(file.lastModified());
            System.out.println(prefix + type + " " + file.getName() + " - " + date);
            if (file.isDirectory()) {
                listRecursiveHelper(file, prefix + "    ");
            }
        }
    }

    private static String formatDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date(millis));
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the directory path as an argument!");
            return;
        }
        listRecursive(args[0]);
    }
}



