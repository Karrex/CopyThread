import java.io.*;
import java.util.ArrayList;

public class Copier {
    public void copyFiles(File from, File to) {
        File[] files = from.listFiles();
        if (!to.exists()) {
            to.mkdirs();
        }
        ArrayList<Thread> threads = new ArrayList<>();
        for (File f : files) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Copy file: " + f.getName());
                    copyFile(f, to);
                }
            }, "copyFile" + f.getName());
            thread.start();
            threads.add(thread);
        }
        waitThreads(threads);
        System.out.println("Copying is finished");
    }

    private void waitThreads(ArrayList<Thread> threads) {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyFile(File f, File to) {
        if (!f.isDirectory()) {
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(f));
                 BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(to, f.getName())))) {
                byte[] buff = new byte[2048];
                while (inputStream.read(buff) != -1) {
                    outputStream.write(buff);
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
