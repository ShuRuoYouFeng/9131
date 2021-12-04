import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * Write a description of class ColoursDatabase here.
 *
 * @author Jiakaixiang Duan
 * @version 1.0v 13/5/2021 20:07
 */
public class ColoursDatabase
{
    private ArrayList<String> carColours;

    /**
     * Default constructor for objects of class ColoursDatabase.
     */
    public ColoursDatabase()
    {
        carColours = new ArrayList();
    }

    /**
     * Be matchColorByName ().Determine if any of the displayed colors have the same color. 
     * If so, return true. If not, return false.
     *
     * @param     array type listName, String type colourName
     * @return    boolean
     */
    public boolean contain(String[] listName, String itemName)
    {
        for (int i = 0; i < listName.length; i++)
        {
            if (listName[i] != null)
                if (listName[i].equals(itemName))
                    return true;
        }
        return false;
    }
    
    /**
     * Search for color by name. If true is returned in the database, false is not returned.
     *
     * @param     String type colourName
     * @return    boolean
     */
    public boolean contains(String itemName)
    {
        for (String colour: carColours)
        {
            if (colour.toUpperCase().equals(itemName.toUpperCase()))
                return true;
        }
        return false;
    }
    
    /**
     * Receive a color name and calls contains() to determine if the color is in the database.
     *
     * @param     String type colourName
     * @return    boolean
     */
    public boolean findColourByName(String colourName)
    {
        return contains(colourName);
    }
    
    /**
     * Receive a color name and search the database for a color matching the same name.
     * If not, print out up to 20 colors with similar names.
     *
     * @param     String type colourName
     * @return    no return value
     */
    public void matchColorByName(String colourName)
    {
        String[] tempColours = new String[20];
        System.out.println("#######");
        System.out.println("      Unable to match the color you entered with the color in the database,");
        System.out.println("      You may want to enter the following colors instead:");
        int colourNumber = 0;
        for (int i = colourName.length(); i > 0; i--)
        {
            String matchColour = colourName.substring(0, i);
            for (String colour: carColours)
            {
                if (colourNumber >= 20)  // Displays only the top 20 color names at most
                    break;
                if (colour.toUpperCase().startsWith(matchColour.toUpperCase()) &&
                    !contain(tempColours, colour))
                {
                    tempColours[colourNumber] = colour;
                    System.out.println("         (" + ++colourNumber + ")  " + colour);
                }
            }
            if (colourNumber >= 20)
                    break;
        }
    }
    
    /**
     * Read the color file and save it to the database.
     *
     * @param     String type filename
     * @return    no return value
     */
    public void readFile(String filename)
    {
        try
        {
            FileReader inputFile = new FileReader(filename);
            try
            {
                System.out.println("------Reading file: " + filename);
                Scanner parser = new Scanner(inputFile);
                while (parser.hasNextLine())
                {
                    String colour = parser.nextLine().split("#")[0].trim().split("\t")[1];
                    setCarColourItemAdd(colour);
                }
                System.out.println("------" + filename + " has been read.");
            }
            finally
            {
                System.out.println("------" + filename + " is closed.");
                inputFile.close();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println(filename + " not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected I/O error occurred");
        }
    }
    
    /**
     * return the size of the colour list length
     *
     * @param     no parameter
     * @return    return int type carColours.size()
     */
    public int getColoursSize()
    {
        return carColours.size();
    }
    
    /**
     * Add color according to index
     *
     * @param     String type colour
     * @return    no return value
     */
    public void setCarColourItemAdd(String colour)
    {
        carColours.add(colour);
    }
    
    /**
     * Delete color according to index
     *
     * @param     int type index
     * @return    no return value
     */
    public void setCarColourItemRemove(int index)
    {
        if (index < carColours.size())
        {
            carColours.remove(index);
        }
    }
}
