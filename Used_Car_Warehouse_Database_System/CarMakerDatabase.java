import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * Write a description of class CarDatabase here.
 *
 * @author Jiakaixiang Duan
 * @version 1.1v 13/5/2021 1:52
 */
public class CarMakerDatabase
{
    // instance variables - replace the example below with your own
    private ArrayList<CarMaker> carMakerItems;
    
    /**
     * Default constructor for objects of class CarMakerDatabase.
     */
    public CarMakerDatabase()
    {
        carMakerItems = new ArrayList();
    }
    
    /**
     * read file
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
                String carMakerInformation;
                while (parser.hasNextLine())
                {
                    carMakerInformation = parser.nextLine();
                    String[] carMakerInfromationList = carMakerInformation.split(",");
                    setCarMakerItemsAdd(getCarMake(carMakerInfromationList),
                                        getCarModel(carMakerInfromationList));
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
     * Returns an object of type CarMaker by index
     *
     * @param     int carMakerIndex
     * @return    CarMaker carMakerItems.get(carMakerIndex)
     */
    public CarMaker getCarMaker(int carMakerIndex)
    {
        return carMakerItems.get(carMakerIndex - 1);
    }
    
    /**
     * Returns an String type Car Make by index
     *
     * @param     int index
     * @return    String carMakerItems.get(index - 1).getCarMake()
     */
    public String getCarMake(int index)
    {
        return carMakerItems.get(index - 1).getCarMake();
    }
    
    /**
     * Returns the total number of current Car Maker.
     * 
     * @param     no parameter
     * @return    int type carMakerItems.size()
     */
    public int getCarMakerListSize()
    {
        return carMakerItems.size();
    }
    
    /**
     * Find the car make from the array
     * 
     * @param     String[] carMakerInfromationList
     * @return    String type carMakerInfromationList[0]
     */
    public String getCarMake(String[] carMakerInfromationList)
    {
        return carMakerInfromationList[0];
    }
    
    /**
     * Returns the model at the corresponding location based on the index
     * 
     * @param     int carMakeIndex, int carModelIndex
     * @return    String type carMakerItems.get(carMakeIndex - 1).getCarModel(carModelIndex)
     */
    public String getCarModel(int carMakeIndex, int carModelIndex)
    {
        return carMakerItems.get(carMakeIndex - 1).getCarModel(carModelIndex);
    }
    
    /**
     * Find the array list type models from the array
     * 
     * @param     String[] carMakerInformation
     * @return    ArrayList<String> type carModelList
     */
    public ArrayList<String> getCarModel(String[] carMakerInformation)
    {
        ArrayList<String> carModelList = new ArrayList();
        for (int i = 1; i < carMakerInformation.length; i++)
        {
            carModelList.add(carMakerInformation[i]);
        }
        return carModelList;
    }
    
    /**
     * Returns the number of models currently in Car Make
     * 
     * @param     CarMaker carMake
     * @return    int type carMake.getCarModelListSize()
     */
    public int getCarModelSize(CarMaker carMake)
    {
        return carMake.getCarModelListSize();
    }
    
    /**
     * Add car make and the model array list to the database.
     * 
     * @param     String newCarMake, ArrayList<String> newCarModels
     * @return    no return value
     */
    public void setCarMakerItemsAdd(String newCarMake,
                                    ArrayList<String> newCarModels)
    {
        CarMaker carMaker;
        carMaker = new CarMaker(newCarMake,
                                newCarModels);
        carMakerItems.add(carMaker);
    }
    
    /**
     * Print out Car Make from the database
     * 
     * @param     no param
     * @return    no return value
     */
    public void showCarsMake()
    {
        int carsMakeLine = 0;
        for (CarMaker carMakerInformation: carMakerItems)
        {
            System.out.println("   (" +
                               ++carsMakeLine +
                               ") " +
                               carMakerInformation.getCarMake());
            
        }
    }
    
    /**
     * Print out Car models from the database
     * 
     * @param     int carMakeIndex
     * @return    no return value
     */
    public void showCarsModel(int carMakeIndex)
    {
        int carsModelLine = 0;
        CarMaker carMakerInformation = carMakerItems.get(carMakeIndex - 1);
        String[] carModelList = carMakerInformation.getCarModels().split(",");
        for (String carModel: carModelList)
        {
            System.out.println("   (" +
                               ++carsModelLine +
                               ") " +
                               carModel);
        }
    }
    
}
