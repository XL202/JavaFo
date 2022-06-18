import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class test {
    public static void main(String[] args) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("./blossom5", "-e", "GRAPH1.txt", "-w", "out.txt");
        processBuilder.inheritIO();
        processBuilder.start();
        //"-e GRAPH1.TXT -w out.txt"
        /*try {

            //Process process = processBuilder.start();
            Process process = Runtime.getRuntime().exec("./blossom5");
            /*BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            */
           /* int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }


}

