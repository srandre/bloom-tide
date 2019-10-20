package bloom.tide;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

class read
{
    private static Scanner input;
    
    private static Vector <Vector <Float>> data = null;
    
    read()
    {
        openFile();
        readRecords();
        closeFile();
    }
    
    public static void openFile()
    {
        try
        {
            input = new Scanner(Paths.get("/Users/andre/Desktop/bloom-tide/bloom-tide/src/bloom/tide/a.txt"));
        }
        catch(IOException ioException)
        {
            System.err.println("Error opening file.");
            System.exit(1);
        }
    }
    
    public static void readRecords()
    {
        String a = "";
        int le;
        int opt = 0;
        try
        {
            int cont=0;
            Vector <Float> temp = null;
            while(input.hasNext())
            {
                temp.add(input.nextFloat());
                System.out.println(input.nextFloat());
                if(cont<33)
                    cont++;
                else
                {
                    data.add(temp);
                    temp = null;
                    cont = 0;
                }
            }
            System.out.println("");
        }
        catch(NoSuchElementException elementException)
        {
            System.err.println("File improperly formed.");
        }
        catch(IllegalStateException stateException)
        {
            System.err.println("Error reading from file.");
        }
    }
    
    public static void closeFile()
    {
        input.close();
    }
}
public class BloomTide 
{
    public static void main(String[] args) 
    {
        read a = new read();
    }   
}