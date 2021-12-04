import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * Write a description of class CarDatabase here.
 *
 * @author Jiakaixiang Duan
 * @version 1.1v 13/5/2021 0:37
 */
public class CarDatabase
{    
    private ArrayList<Car> carItems;
    
    /**
     * Default constructor for objects of class CarDatabase.
     */
    public CarDatabase()
    {
        carItems = new ArrayList();
    }
    
    /**
     * In the database to find the same carMake and carModel vehicle information,
     * if there are returns true, there is no return false
     *
     * @param     String type carMake, String type carModel
     * @return    boolean
     */
    public boolean findCarByCarMakeAndCarModel(String carMake, String carModel)
    {
        for (Car carInformation: carItems)
        {
            if (carInformation.getCarMake().equals(carMake))
                if (carInformation.getCarModel().equals(carModel))
                    return true;
        }
        return false;
    }
    
    /**
     * Search the database for the same registration number of vehicle information, 
     * if there is a return true, return false if there is no
     *
     * @param     String type registrationNumber
     * @return    boolean
     */
    public boolean findCarByRegistrationNumber(String registrationNumber)
    {
        for (Car carInformation: carItems)
        {
            if (carInformation.getRegistrationNumber().equals(registrationNumber))
                return true;
        }
        return false;
    }

    /**
     * Search the database for the same registration number of vehicle information,
     * if there is a return CAR type object, no return null
     *
     * @param     String type registrationNumber
     * @return    Car type carInformation
     */
    public Car getCar(String registrationNumber)
    {
        for (Car carInformation: carItems)
        {
            if (carInformation.getRegistrationNumber().equals(registrationNumber))
                return carInformation;
        }
        return null;
    }

    /**
     * Returns the color string for the vehicle with the current registration number
     * 
     * @param     String type registrationNumber
     * @return    String type Colour
     */
    public String getCarColour(String registrationNumber)
    {
        return getCar(registrationNumber).getColours();
    }
    
    /**
     * Returns the color string for the vehicle with the current registration number
     * 
     * @param     String type registrationNumber
     * @return    String type Colour
     */
    public String[] getCarColours(String registrationNumber)
    {
        return getCar(registrationNumber).getArrayColours();
    }
    
    /**
     * Returns the car colour size
     * 
     * @param     no parameter
     * @return    int type Car.getColoursSize()
     */
    public int getCarColourSize()
    {
        return Car.getColoursSize();
    }

    /**
     * Returns the total number of current items.
     * 
     * @param     no parameter
     * @return    int type carItems.size()
     */
    public int getCarListSize()
    {
        return carItems.size();
    }
    
    /**
     * Returns the car make string for the vehicle with the current registration number
     * 
     * @param     String type registrationNumber
     * @return    String type getCar(registrationNumber).getCarMake()
     */
    public String getCarMake(String registrationNumber)
    {
        return getCar(registrationNumber).getCarMake();
    }
    
    /**
     * Returns the car models string for the vehicle with the current registration number
     * 
     * @param     String type registrationNumber
     * @return    String type getCar(registrationNumber).getCarModel()
     */
    public String getCarModel(String registrationNumber)
    {
        return getCar(registrationNumber).getCarModel();
    }
    
    /**
     * Returns the car price for the vehicle with the current registration number
     * 
     * @param     String type registrationNumber
     * @return    int type getCar(registrationNumber).getPrice()
     */
    public int getCarPrice(String registrationNumber)
    {
        return getCar(registrationNumber).getPrice();
    }
    
    /**
     * Returns the car make for the vehicle with the index
     * 
     * @param     int type index
     * @return    String type carItems.get(index).getRegistrationNumber()
     */
    public String getCarRego(int index)
    {
        return carItems.get(index).getRegistrationNumber();
    }
    
    /**
     * Returns the car year for the vehicle with the current registration number
     * 
     * @param     String type registrationNumber
     * @return    int type getCar(registrationNumber).getYearMade()
     */
    public int getCarYear(String registrationNumber)
    {
        return getCar(registrationNumber).getYearMade();
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
                String carInformation;
                while (parser.hasNextLine())
                {
                    carInformation = parser.nextLine();
                    String[] carInfromationList = carInformation.split(",");
                    setCarItemsAdd(carInfromationList);
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
     * Receive an array of vehicle information and add it to the database.
     * 
     * @param     array type carInformation
     * @return    no return
     */
    public void setCarItemsAdd(String[] carInformation)
    {
        Car usedCar = new Car();
        String[] carColourList = new String[usedCar.getColoursSize()];
        for (int i = 2; i < usedCar.getColoursSize() + 2; i++)
        {
            carColourList[i - 2] = carInformation[i];
        }
        
        usedCar = new Car(carInformation[0],
                          Integer.parseInt(carInformation[1]),
                          carColourList,
                          carInformation[usedCar.getColoursSize() + 2],
                          carInformation[usedCar.getColoursSize() + 3],
                          Integer.parseInt(carInformation[usedCar.getColoursSize() + 4]));
        carItems.add(usedCar);
    }
    
    /**
     * overloding
     * Receive some params of vehicle information and add it to the database.
     * 
     * @param     String newRegistrationNumber,
     *                         int newYearMade,
     *                         String[] colours,
     *                         String newCarMake,
     *                         String newCarModel,
     *                         int newPrice
     * @return    no return
     */
    public void setCarItemsAdd(String newRegistrationNumber,
                               int newYearMade,
                               String[] colours,
                               String newCarMake,
                               String newCarModel,
                               int newPrice)
    {
        Car usedCar = new Car();
        
        usedCar = new Car(newRegistrationNumber,
                          newYearMade,
                          swapColours(colours),
                          newCarMake,
                          newCarModel,
                          newPrice);
        carItems.add(usedCar);
    }
    
    /**
     * remove the car with the current registration number
     * 
     * @param     String type currentRegistrationNumber
     * @return    no return value
     */
    public void setCarItemsRamove(String currentRegistrationNumber)
    {
        int carIndex = 0;
        for (Car carInformation: carItems)
        {
            if (carInformation.getRegistrationNumber().equals(currentRegistrationNumber))
            {
                carItems.remove(carIndex);
                break;
            }
            carIndex ++;
        }
    }
    
    /**
     * set the new colours with the currentRegistrationNumber and colour index
     * 
     * @param     String currentRegistrationNumber, String newColours, int colourIndex
     * @return    no return value
     */
    public void setNewColour(String currentRegistrationNumber,
                             String newColours,
                             int colourIndex)
    {
        for (Car carInformation: carItems)
        {
            if (carInformation.getRegistrationNumber().equals(currentRegistrationNumber))
            {
                carInformation.setColour(newColours, colourIndex);
                break;
            }   
        }
        
    }
    
    /**
     * set the new price with the currentRegistrationNumber 
     * 
     * @param     String currentRegistrationNumber, int newPrice
     * @return    no return value
     */
    public void setNewPrice(String currentRegistrationNumber, int newPrice)
    {
        for (Car carInformation: carItems)
        {
            if (carInformation.getRegistrationNumber().equals(currentRegistrationNumber))
            {
                carInformation.setPrice(newPrice);
                break;
            }   
        }
    }
    
    /**
     * to display vehicle information by matches carmake,If present, print vehicle information
     * 
     * @param     String carMake
     * @return    no return value
     */
    public void showCarByCarMake(String carMake)
    {
        int findCar = 0;
        for (Car carInformation: carItems)
        {
            if (carInformation.getCarMake().equals(carMake))
            {
                System.out.println(carInformation);
                findCar ++;
            }       
        }
        if (findCar == 0)
            System.out.print("no this car in system");
    }
    
    /**
     * to display vehicle information by matches carmake and carModel,
     * If present, print vehicle information
     * 
     * @param     String carMake, String carModel
     * @return    no return value
     */
    public void showCarByCarMakeAndCarModel(String carMake, String carModel)
    {
        int findCar = 0;
        for (Car carInformation: carItems)
        {
            if (carInformation.getCarMake().equals(carMake))
                if (carInformation.getCarModel().equals(carModel))
                {
                    System.out.println(carInformation);
                    findCar ++;
                }       
        }
        if (findCar == 0)
            System.out.print("no this car in system");
    }
    
    /**
     * to display vehicle information by Minimum price and maximum price,
     * If present, print vehicle information
     * 
     * @param     int minPrice, int maxPrice
     * @return    no return value
     */
    public void showCarByPrice(int minPrice, int maxPrice)
    {
        int findCar = 0;
        for (Car carInformation: carItems)
        {
            if (carInformation.getPrice() >= minPrice &&
                carInformation.getPrice() <= maxPrice)
            {
                System.out.println(carInformation);
                findCar ++;
            }       
        }
        if (findCar == 0)
            System.out.print("no this price in system");
    }
    
    /**
     * to display vehicle information by car Age,
     * If present, print vehicle information
     * 
     * @param     int carAge
     * @return    no return value
     */
    public void showCarByAge(int carAge)
    {
        int findCar = 0;
        for (Car carInformation: carItems)
        {
            if (carInformation.getYearMade() + carAge >= 2021)
            {
                System.out.println(carInformation);
                findCar ++;
            }       
        }
        if (findCar == 0)
            System.out.print("no this age in system");
    }
    
    /**
     * Receive an array of colors, placing the empty color after the even
     * 
     * @param     String[] colours
     * @return    String[] colours
     */
    public String[] swapColours(String[] colours)
    {
        int noColourLocation = 0;
        // Find the position where the first color is empty
        for (int i = 0; i < colours.length; i++)
        {
            if (colours[i].equals(""))
            {
                noColourLocation = i;
                break;
            }
        }
        // 1.If the position of the empty color is equal to the length of the color minus one,
        //   then all previous positions are colored
        // 2.If noColourLocation is 0 and location 0 is already colored, then all locations are colored
        // Don't need to swap color positions in these two cases
        // ----Rule 1 prevents the following for loop from overflowing.
        // ----Rule 2 prevents unnecessary loops
        if (noColourLocation == colours.length - 1 ||
            (noColourLocation == 0 &&
             !colours[0].equals("")))
            return colours;
        // Swap locations, swapping noColourLocation with the color-followed location
        for (int i = noColourLocation + 1; i < colours.length; i++)
        {
            if (!colours[i].equals(""))
            {
                colours[noColourLocation] = colours[i];
                colours[i] = "";
                noColourLocation ++;
            }
        }
        return colours;
    }
    
    /**
     * write file
     */
    public void writeFile(String filename)
    {
        try
        {
            PrintWriter outputFile = new PrintWriter(filename);
            try
            {
                System.out.println();
                System.out.println("------Writing file: " + filename);
                for (Car carInformation: carItems)
                {
                    outputFile.println(carInformation);
                }
                System.out.println("------" + filename + " has been written.");
            }
            finally
            {
                System.out.println("------" + filename + " is closed.");
                outputFile.close();
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
}
