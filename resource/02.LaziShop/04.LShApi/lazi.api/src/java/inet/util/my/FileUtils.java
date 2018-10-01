package inet.util.my;

import java.io.File;

/**
 *
 * @author W
 */
public class FileUtils {
    public static boolean delete(String dirPath, long days, String fileExtension) {
        try {
            File folder = new File(dirPath);
            if (folder.exists()) {
                File[] listFiles = folder.listFiles();
                long eligibleForDeletion = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000);
                for (File file: listFiles) {
                    if (file.getName().endsWith(fileExtension) &&
                        file.lastModified() < eligibleForDeletion) {
                        if( file.delete() )
                            System.out.println("Deleted File [OK]: " + file.getName());
                        else
                            System.out.println("Deleted File [FAIL]: " + file.getName());
                    }
                }
            }
            return true;
        }catch(Exception ex) {
            System.out.println("FileUtils.delete() ==> ex: " + ex.toString());
        }
        return false;
    }
}
