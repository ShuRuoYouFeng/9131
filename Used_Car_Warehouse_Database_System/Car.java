
/**
 * The Car class is used to create Car objects.
 * The Car class implements the method of setting and getting the various attributes of the car.
 * The Car class contains seven attributes:
 *     String type car registration number, integer type car year, string array type color array, 
 *     string type car manufacturer and car model, integer type car price, and number of car colors.
 * @author Jiakaixiang Duan
 * @version 1.1v 12/5/2021 23:06
 */
public class Car
{
    // instance variables - replace the example below with your own
    private String registrationNumber;
    private int yearMade;
    private String[] colours;
    private String carMake;
    private String carModel;
    private int price;
    public static final int COLOURS_SIZE = 3;
    /**
     * Default constructor for objects of class Car.
     */
    public Car()
    {
        // initialise instance variables
        registrationNumber = "";
        yearMade = 0;
        colours = new String[COLOURS_SIZE];
        carMake = "";
        carModel = "";
        price = 0;
    }
    
    /**
     * Constructors for objects of class Car that take parameters 
     */
    public Car(String newRegistrationNumber,
               int newYearMade,
               String[] newColours,
               String newCarMake,
               String newCarModel,
               int newPrice)
    {
        // initialise instance variables
        if (validRegistrationNumber(newRegistrationNumber))
            registrationNumber = newRegistrationNumber;
        else
            registrationNumber = "";
        if (newYearMade >= 1950 && newYearMade <= 2021)
            yearMade = newYearMade;
        else
            yearMade = 0;
        if (validColours(newColours))
            colours = newColours;
        else
            colours = new String[COLOURS_SIZE];
        if (validCarMake(newCarMake))
            carMake = newCarMake;
        else
            carMake = "";
        if (validCarModel(newCarModel))
            carModel = newCarModel;
        else
            carModel = "";
        if(newPrice >= 500 && newPrice <= 30000)
            price = newPrice;
        else
            price = 0;
    }
    
    /**
     * Allows an external function to call this method to get an array of colors for the car
     *
     * @param     no parameter
     * @return    string array of car colours
     */
    public String[] getArrayColours()
    {
        return colours;
    }
    
    /**
     * Allows an external function to call this method to get a String car make
     *
     * @param     no parameter
     * @return    string type car make
     */
    public String getCarMake()
    {
        return carMake;
    }
    
    /**
     * Allows an external function to call this method to get a String car model
     *
     * @param     no parameter
     * @return    string type car model
     */
    public String getCarModel()
    {
        return carModel;
    }
    
    /**
     * Allows an external function to call this method to get a String car colours
     *
     * @param     no parameter
     * @return    string type car colours
     */
    public String getColours()
    {
        StringBuffer coloursString = new StringBuffer("");
        for (String colour: colours)
        {
            coloursString.append(colour + ",");
        }
        coloursString.delete(coloursString.length() - 1, coloursString.length());
        return coloursString.toString();
    }
    
    /**
     * Allows an external function to call this method to get the number of colours
     *
     * @param     no parameter
     * @return    colours number
     */
    public static int getColoursSize()
    {
        return COLOURS_SIZE;
    }
    
    /**
     * Allows an external function to call this method to get the number of empty colours
     *
     * @param     no parameter
     * @return    empty colours number
     */
    public int getEmptyCarColoursSize()
    {
        int emptyColoursSize = 0;
        for (String colour: colours)
        {
            if (colour.equals(""))
                emptyColoursSize ++;
        }
        return emptyColoursSize;
    }
    
    /**
     * Allows an external function to call this method to get the price of the car
     *
     * @param     no parameter
     * @return    car price
     */
    public int getPrice()
    {
        return price;
    }
    
    /**
     * Allows an external function to call this method to get the registration number of the car
     *
     * @param     no parameter
     * @return    String type registration number 
     */
    public String getRegistrationNumber()
    {
        return registrationNumber;
    }
    
    /**
     * Allows an external function to call this method to get the year of car made
     *
     * @param     no parameter
     * @return    year of car made 
     */
    public int getYearMade()
    {
        return yearMade;
    }
    
    /**
     * Allows an external function to call this method to set the car make
     *
     * @param     String type newCarMake   Pass a valid car make name
     * @return    no return value 
     */
    public void setCarMake(String newCarMake)
    {
        if (validCarMake(newCarMake))
            carMake = newCarMake;
        else
            System.out.println("Error: You have to input valid Car Make");

    }
    
    /**
     * Allows an external function to call this method to set the car model
     *
     * @param     String type newCarModel   Pass a valid car make model
     * @return    no return value 
     */
    public void setCarModel(String newCarModel)
    {
        if (validCarModel(newCarModel))
            carModel = newCarModel;
        else
            System.out.println("Error: You have to input valid Car Model");
    }
    
    /**
     * Allows an external function to call this method to set the car colours
     *
     * @param     String array type newColours   Pass a colours array
     * @return    no return value 
     */
    public void setColours(String[] newColours)
    {
        if (validColours(newColours))
            colours = newColours;
        else
            System.out.println("Error: You have to input valid colour");
    }
    
    /**
     * OverLoding 
     * Allows an external function to call this method to
     * set the car Sets the color of the given index.
     *
     * @param     String type newColour  integer type colourIndex   Pass a colour and its index
     * @return    no return value 
     */
    public void setColour(String newColour, int colourIndex)
    {
        String colour = newColour.trim();
        if (colourIndex >= COLOURS_SIZE)
        {
            System.out.println("Error: You have to input valid Index");
            return;
        }
        if (colour.length() > 0)
        {
            if (Character.isLetter(colour.charAt(0)))
            {
                for (int i = 0; i < colour.length(); i++)
                {
                    if (colour.charAt(i) != ' ' &&
                        !Character.isLetter(colour.charAt(i)) &&
                        !Character.isDigit(colour.charAt(i)))
                        break;
                    if (i == colour.length() - 1)
                    {
                        colours[colourIndex] = newColour;
                        return;
                    }
                }
            }
            System.out.println("Error: You have to input valid colour");
        }
        else
            colours[colourIndex] = newColour;
    }
    
    /**
     * Allows an external function to call this method to set the new car price
     *
     * @param     integer type newPrice   Pass a new price
     * @return    no return value 
     */
    public void setPrice(int newPrice)
    {
        if(newPrice < 500 || newPrice > 30000)
        {
            System.out.println("Error: the price should between 500-30000 (inclusive).");
            return;
        }
        price = newPrice;
    }
    
    /**
     * Allows an external function to call this method to set the registration numbe
     *
     * @param     String type newRegistrationNumbe   Pass a registration numbe
     * @return    no return value 
     */
    public void setRegistrationNumber(String newRegistrationNumbe)
    {
        if (validRegistrationNumber(newRegistrationNumbe))
            registrationNumber = newRegistrationNumbe;
        else
            System.out.println("You car registration number is invalid" + 
                           "(Only “A”-“Z”, “a”-“z” or “0-9 and maximum 6-character”)");
    }
    
    /**
     * Allows an external function to call this method to set the year of car made
     *
     * @param     integer type newYearMade   Pass a year of car made
     * @return    no return value 
     */
    public void setYearMade(int newYearMade)
    {
        if (newYearMade < 1950 || newYearMade > 2021)
        {
            System.out.println("Error: the year should between 1950-2021 (inclusive).");
            return;
        }
        yearMade = newYearMade;
    }
    
    /**
     * Prints the current object as a string
     * 
     * @param     no parameter
     * @return    String type carString 
     */
    public String toString()
    {
        StringBuffer coloursString = new StringBuffer("");
        for (String colour: colours)
        {
            coloursString.append(colour + ",");
        }
        String carString = registrationNumber +
                    "," +
                    yearMade + 
                    "," +
                    coloursString.toString() +
                    carMake + 
                    "," + 
                    carModel + 
                    "," + 
                    price;
        return carString;
    }
    
    /**
     * Determine if the entered name is valid
     *
     * @param     String type newCarMake   Pass a valid car make name
     * @return    boolean 
     */
    public boolean validCarMake(String newCarMake)
    {
        newCarMake = newCarMake.trim();
        for (int i = 0; i < newCarMake.length(); i++)
        {
            if (newCarMake.charAt(i) != ' ' &&
                !Character.isLetter(newCarMake.charAt(i)))
                return false;
        }
        return true;
    }
    
    /**
     * Determine if the entered car model is valid
     *
     * @param     String type newCarModel   Pass a valid car make model
     * @return    boolean 
     */
    public boolean validCarModel(String newCarModel)
    {
        newCarModel = newCarModel.trim();
        for (int i = 0; i < newCarModel.length(); i++)
        {
            if (newCarModel.charAt(i) != ' ' &&
                !Character.isLetter(newCarModel.charAt(i)) &&
                !Character.isDigit(newCarModel.charAt(i)))
                return false;
        }
        return true;
    }
    
    /**
     * Determine if the entered car colours list is valid
     *
     * @param     String[] newColours
     * @return    no return value 
     */
    public boolean validColours(String[] newColours)
    {
        for (String colour: newColours)
        {
            if (colour.length() > 0)
            {
                if (Character.isLetter(colour.charAt(0)))
                {
                    for (int i = 0; i < colour.length(); i++)
                    {
                        if (colour.charAt(i) != ' ' &&
                            !Character.isLetter(colour.charAt(i)) &&
                            !Character.isDigit(colour.charAt(i)))
                            return false;
                    }
                }
                else
                    return false;
            }
        }
        return true;
    }
    
    /**
     * Determine if the registration numbe is valid
     *
     * @param     String type newRegistrationNumbe   Pass a registration numbe
     * @return    boolean 
     */
    public boolean validRegistrationNumber(String newRegistrationNumbe)
    {
        newRegistrationNumbe = newRegistrationNumbe.trim();
        if (newRegistrationNumbe.length() <= 6 &&
            newRegistrationNumbe.length() > 0)
        {
            for (int i = 0; i < newRegistrationNumbe.length(); i++)
            {
                if (!Character.isDigit(newRegistrationNumbe.charAt(i)) &&
                    !Character.isLetter(newRegistrationNumbe.charAt(i)))
                    break;
                if (i == newRegistrationNumbe.length() - 1)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
