package se.lexicon.exceptions.workshop.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// COMMA SEPARATED VALUES - CSV
public class CSVReader_Writer {
	 /**
     * This method getMaleFirstNames should use a try-catch-finally without resources
     * Should catch FileNotFoundException and IOException
     * You should also close the Buffered reader in the finally block
     * @return List<String>of male firstnames
     */
    public static List<String> getMaleFirstNames(){

        BufferedReader reader = null;
        List <String> names = null;


        try {
            reader = Files.newBufferedReader(Paths.get("firstname_males.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());

        } catch (FileNotFoundException ex) {
            System.out.println("OPS! File could not be found!");
            ex.printStackTrace();
        } catch (IOException exception) {
            System.out.println("Ops! Something with the IO went wrong");
            exception.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Ops! Something with the went wrong");
            ex.printStackTrace();
        } catch (Throwable ex) {
            System.out.println("Ops!");
            ex.printStackTrace();
        } finally {
            try {

                reader.close();

            } catch (IOException ex) {
                System.out.println("Ops! could not close connection!");
                ex.printStackTrace();
            }
        }

        return names;
    }



    /**
     * This method getFemaleFirstNames should make use of a try-catch with resources
     * <p>
     * AutoCloses the resources!
     *
     * @return
     */
    public static List<String> getFemaleFirstNames() {

        List<String> names = null;


        try (BufferedReader reader = Files.newBufferedReader(Paths.get("firstname_female.txt"))) {

            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());

        } catch (FileNotFoundException ex) {
            System.out.println("OPS! File could not be found!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Ops! Something with the IO went wrong");
            ex.printStackTrace();
        } finally {
            // "Finally" is ALWAYS running at the end of the "try".
            // If we would like to do anything lets do it here ;) Party?
            // Anything to rollback?

        }


        return names;
    }


    /**
     * This method fetches strings from a file and put them into a list
     * This method might throw IOException which due to the throws clause need to
     * be handled by the caller.
     * @return List <String> of last names
     * @throws IOException
     */
    public static List<String> getLastNames() throws IOException{

        List<String> names = null;
        BufferedReader reader = null;

            reader = Files.newBufferedReader(Paths.get("lastnames.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());

        return names;
    }


    public static void saveLastNames(List<String> lastNames) {

        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get("lastnames.txt"));

            for (String toWrite : lastNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFemaleNames(List<String> femaleNames) {


        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"));

            for (String toWrite : femaleNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void saveMaleNames(List<String> maleNames) {

        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"))){
            for (String toWrite : maleNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }


}
