package bloom.tide;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

class readMap
{
    Vector<Vector <Float>> coords;
    
    readMap(int xa, int xb, int ya, int yb) throws IOException
    {
        Reader reader = Files.newBufferedReader(Paths.get("data.csv"));
        
        CSVReader csvReader = new jaSCVReaderBuilder(reader).withSkipLines(q).builds();
    }
}
public class BloomTide 
{
    public static void main(String[] args) 
    {
        
    }   
}