import java.util.Scanner;

/**
 * Write a description of class CarWarehouse here.
 *        
 * @author Jiakaixiang Duan
 * @version 1.1v  12/5/2021  18:46
 */
public class CarWarehouse
{
    private CarDatabase carDatabase;
    private CarMakerDatabase carMakerDatabase;
    private ColoursDatabase carColoursDatabase;
    private String usedcarsFilename;
    private String carmakersFilename;
    private String coloursFilename;

    /**
     * Constructor for objects of class CarWarehouse with default file
     */
    public CarWarehouse()
    {
        carDatabase = new CarDatabase();
        carMakerDatabase = new CarMakerDatabase();
        carColoursDatabase = new ColoursDatabase();
        usedcarsFilename = "usedcars.txt";
        carmakersFilename = "carmakers.txt";
        coloursFilename = "colours.txt";
        importFile(usedcarsFilename, carmakersFilename, coloursFilename);
    }
    
    /**
     * Constructor for objects of class CarWarehouse
     */
    public CarWarehouse(String newUsedcarsFilename, String newCarmakersFilename)
    {
        carDatabase = new CarDatabase();
        carMakerDatabase = new CarMakerDatabase();
        carColoursDatabase = new ColoursDatabase();
        usedcarsFilename = newUsedcarsFilename;
        carmakersFilename = newCarmakersFilename;
        coloursFilename = "colours.txt";
        importFile(usedcarsFilename, carmakersFilename, coloursFilename);
    }

    /**
     * Start the system, show the main menu and recive options from user
     *
     * @param     no param
     * @return    no return value
     */
    public void begin()
    {
        Scanner input = new Scanner(System.in);
        boolean exitSystem = false;
        String choice = "";
        do
        {
            mainMenuDisplay();
            choice = input.nextLine().trim();
            System.out.println();
            while (!InputIsDigit(choice) ||
                   Integer.parseInt(choice) == 0 ||
                   Integer.parseInt(choice) > 5) // Verify that the input meets the requirements
            {
                System.out.println("Error: You have to enter a number, and it's between 1-5");
                System.out.println();
                System.out.println("Please enter an options:");
                choice = input.nextLine().trim();
                System.out.println();
            }
            int option = Integer.parseInt(choice);
            exitSystem = mainMenuOptions(option);
        }while (!exitSystem);
        System.out.println();
        System.out.println("Thanks for using the Used Car Warehouse Database System");
        carDatabase.writeFile(usedcarsFilename); // System shut down, write file
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("The information was saved successfully");
        System.out.println("The system has been safely exited");
    }
    
    /**
     * Load the file
     *
     * @param     String carsFileNmae,
     *            String carmakerFilename,
     *            String coloursFileName
     * @return    no return value
     */
    public void importFile(String carsFileNmae,
                           String carmakerFilename,
                           String coloursFileName)
    {
        carDatabase.readFile(carsFileNmae);
        carMakerDatabase.readFile(carmakerFilename);
        carColoursDatabase.readFile(coloursFileName);
    }
    
    /**
     * judeg whether this string is full of numbers
     *
     * @param     String inputString
     * @return    boolean
     */
    public boolean InputIsDigit(String inputString)
    {
        if (inputString.length() == 0)
            return false;
        for (int i = 0; i < inputString.length(); i++)
        {
            if (!Character.isDigit(inputString.charAt(i)))
                return false;
        }
        return true;
    }
    
    /**
     * show main menu
     *
     * @param     no param
     * @return    no return value
     */
    public void mainMenuDisplay()
    {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Welcome to the Used Car Warehouse Database System");
        System.out.println("");
        System.out.println("    (1) Search Cars");
        System.out.println("    (2) Add Carl");
        System.out.println("    (3) Delete Car");
        System.out.println("    (4) Edit Car");
        System.out.println("    (5) Exit System");
        System.out.println();
        System.out.println("Please enter your options:");
    }
    
    /**
     * receive one choice from user and calls the relevant method,
     * If option is 5, return true
     * 
     * @param     int option
     * @return    boolean 
     */
    public boolean mainMenuOptions(int option)
    {
        switch (option)
        {
            case 1:
                optionSearchCars(); break;
            case 2:
                optionAddCar(); break;
            case 3:
                optionDeleteCar(); break;
            case 4:
                optionEditCar(); break;
            case 5:
                return true;
            default:
                System.out.println("no this option"); break;
        }
        return false;
    }

    /**
     * main menu option 2
     * Add Car
     * 
     * @param     no param
     * @return    no return value
     */
    public void optionAddCar()
    {
        Scanner input = new Scanner(System.in);
        boolean numberExists = false;
        String registrationNumber;
        do
        {
            System.out.println("Please enter new car's registration number:");
            registrationNumber = input.nextLine().trim().toUpperCase();
            while (!validRegistrationNumber(registrationNumber))
            {
                System.out.println("Please enter vehicle registration number" + 
                                   "(Only “A”-“Z”, “a”-“z” or “0-9 and maximum 6-character”):");
                registrationNumber = input.nextLine().trim().toUpperCase();
            }
            // Determine whether the registration number exists
            numberExists = carDatabase.findCarByRegistrationNumber(registrationNumber);
            if (numberExists)
                System.out.println("The car already exists");
        }while (numberExists);
        //----Select manufacturer and model
        System.out.println();
        System.out.println("Please select Car Make:");
        carMakerDatabase.showCarsMake(); // Displays Car Make information
        System.out.println();
        String choice = input.nextLine().trim();
        System.out.println();
        while (!InputIsDigit(choice) ||
               Integer.parseInt(choice) == 0 ||
               Integer.parseInt(choice) > carMakerDatabase.getCarMakerListSize())
        {
            System.out.printf("Error: You have to enter a number, and it's between 1-%d:",
                              carMakerDatabase.getCarMakerListSize());
            System.out.println();
            System.out.println("Please select Car Make:");
            choice = input.nextLine().trim();
            System.out.println();
        }
        int optionCarMake = Integer.parseInt(choice);
        System.out.println("Please select Car Model:");
        carMakerDatabase.showCarsModel(optionCarMake); // Show car model information according to car make
        choice = input.nextLine().trim();
        System.out.println();
        while (!InputIsDigit(choice) ||
               Integer.parseInt(choice) == 0 ||
               Integer.parseInt(choice) >
               carMakerDatabase.getCarModelSize(carMakerDatabase.getCarMaker(optionCarMake)))
        {
            System.out.printf("Error: You have to enter a number, and it's between 1-%d:",
                              carMakerDatabase.getCarModelSize(carMakerDatabase.getCarMaker(optionCarMake)));
            System.out.println();
            System.out.println("Please select Car Model:");
            choice = input.nextLine().trim();
            System.out.println();
        }
        int optionCarModel = Integer.parseInt(choice);
        //----add car's colour
        String[] colours = new String[carDatabase.getCarColourSize()];
        boolean hasColour = false;
        do
        {
            int emptyColour = 0;  // Record the number of empty colors
            for (int i = 0; i < carDatabase.getCarColourSize(); i++)
            {
                System.out.printf("Please add car's No.%s colour:", i + 1);
                System.out.println();
                String colour = input.nextLine().trim();
                System.out.println();
                if (colour.length() == 0)
                {
                    emptyColour ++;
                    colours[i] = "";
                    continue;
                }
                while (!validColourName(colour))
                {
                    System.out.println("Error: You have to input valid colour");
                    System.out.println("Please add car's colour:");
                    colour = input.nextLine().trim();
                    System.out.println();
                    if (colour.length() == 0)
                        break;
                }
                colours[i] = colour;
            }
            if (emptyColour == carDatabase.getCarColourSize())  // If no color is entered
            {
                System.out.println();
                System.out.println("The current car does not have any color.");
                System.out.println("Do you want to save it? Y (YES) N (NO).");
                String sure = input.nextLine().trim();
                while (!sure.equals("Y") &&
                       !sure.equals("N"))
                {
                    System.out.println("Please enter either Y (Yes) or N (No):");
                    sure = input.nextLine().trim();
                    System.out.println();
                }
                if (sure.equals("Y"))
                    hasColour = true;
                else
                    hasColour = false;
            }
            else
                hasColour = true;
        }while (!hasColour);
        //----price
        System.out.println("Please enter car's price:");
        String price = input.nextLine().trim();
        System.out.println();
        while (!InputIsDigit(price) ||
               Integer.parseInt(price) < 500 ||
               Integer.parseInt(price) > 30000)
        {
            System.out.println("Error: the price should between 500-30000 (inclusive).");
            System.out.println("Please enter car's price:");
            price = input.nextLine().trim();
            System.out.println();
        }
        //--add year
        System.out.println("Please enter car's year made:");
        String year = input.nextLine().trim();
        System.out.println();
        while (!InputIsDigit(year) ||
               Integer.parseInt(year) < 1950 ||
               Integer.parseInt(year) > 2021)
        {
            System.out.println("Error: the year should between 1950-2021 (inclusive).");
            System.out.println("Please enter car's year:");
            year = input.nextLine().trim();
            System.out.println();
        }
        //-----add car
        // Adds vehicle information to the database
        carDatabase.setCarItemsAdd(registrationNumber,
                                   Integer.parseInt(year),
                                   colours,
                                   carMakerDatabase.getCarMake(optionCarMake),
                                   carMakerDatabase.getCarModel(optionCarMake, optionCarModel),
                                   Integer.parseInt(price));
        System.out.println("You have successfully added Car " +
                                   registrationNumber);
        System.out.println("        " + carDatabase.getCar(registrationNumber));
    }
    
    /**
     * main menu option 3
     * Delete Car
     * 
     * @param     no param
     * @return    no return value
     */
    public void optionDeleteCar()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter vehicle registration number:");
        String registrationNumber = input.nextLine().trim().toUpperCase();
        while (!validRegistrationNumber(registrationNumber))
        {
            System.out.println("Please enter vehicle registration number" + 
                               "(Only “A”-“Z”, “a”-“z” or “0-9 and maximum 6-character”):");
            registrationNumber = input.nextLine().trim().toUpperCase();
        }
        // Determine whether the current registration number exists
        boolean numberExists = carDatabase.findCarByRegistrationNumber(registrationNumber);
        if (numberExists)
        {
            System.out.printf("The details of the car registration number %s are ",
                              registrationNumber);
            System.out.println();
            System.out.println("    " + carDatabase.getCar(registrationNumber));
            System.out.println("Are you sure you want to delete this car?");
            System.out.println("Please enter either Y (Yes) or N (No):");
            String sure = input.nextLine().trim();
            System.out.println();
            while (!sure.equals("Y") &&
                   !sure.equals("N"))
            {
                System.out.println("Please enter either Y (Yes) or N (No):");
                sure = input.nextLine().trim();
                System.out.println();
            }
            if (sure.equals("Y"))
            {
                carDatabase.setCarItemsRamove(registrationNumber);
                System.out.println("The car has been successfully deleted.");
            }
            else
                System.out.println("The user cancels and deletes this car.");
        }
        else
            System.out.println("No such car with this Registration Number");
    }
    
    /**
     * main menu option 4
     * Edit Car
     * 
     * @param     no param
     * @return    no return value
     */
    public void optionEditCar()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter vehicle registration number:");
        String registrationNumber = input.nextLine().trim().toUpperCase();
        while (!validRegistrationNumber(registrationNumber))
        {
            System.out.println("Please enter vehicle registration number" + 
                               "(Only “A”-“Z”, “a”-“z” or “0-9 and maximum 6-character”):");
            registrationNumber = input.nextLine().trim().toUpperCase();
        }
        // Determine whether the current registration number exists
        boolean numberExists = carDatabase.findCarByRegistrationNumber(registrationNumber);
        if (numberExists)
        {
            System.out.printf("The details of the car registration number %s are ",
                              registrationNumber);
            System.out.println();
            System.out.println("    " + carDatabase.getCar(registrationNumber));
            System.out.println("             ----------");
            System.out.println("Please enter the result you want to change at the corresponding prompt.");
            System.out.println("The colour can be changed three times.");
            System.out.println("The price can only be changed once.");
            System.out.println("If you do not want to change an item, please hit Enter to skip.");
            System.out.println("If you want to remove a colour, please enter 'D'.");
            boolean doEdit = false;
            boolean hasColour = true;
            do
            {
                for (int i = 0; i < carDatabase.getCarColourSize(); i++)
                {
                    System.out.printf("Please edit car's No.%s colour:", i + 1);
                    System.out.println();
                    String colour = input.nextLine().trim();
                    System.out.println();
                    if (colour.length() == 0)
                    {
                        // No color has been entered for the current color position
                        continue;
                    }
                    //If type "D", the color of the current position is removed.
                    if (colour.equals("D")) 
                        colour = "";
                    else
                    {
                        while(!validColourName(colour))
                        {
                            System.out.println("Error: You have to input valid colour");
                            System.out.println("Please add car's colour:");
                            colour = input.nextLine().trim();
                            System.out.println();
                            if (colour.length() == 0)
                                break;
                        }
                        if (colour.length() == 0)
                            continue;
                    }
                    // Save the color at this location (i) under the registration number
                    carDatabase.setNewColour(registrationNumber, colour, i);
                    doEdit = true;
                }
                // Sort the colors by putting the location where the colors are stored first
                // eg. red,,yellow -> red,yellow,,
                carDatabase.swapColours(carDatabase.getCarColours(registrationNumber));
                if (carDatabase.getCar(registrationNumber).getEmptyCarColoursSize() ==
                    carDatabase.getCarColourSize())
                {
                    // If there is no color after the change
                    System.out.println();
                    System.out.println("The current change will cause the Car color to be empty.");
                    System.out.println("Do you want to save it? Y (YES) N (NO).");
                    String sure = input.nextLine().trim();
                    while(!sure.equals("Y") &&
                          !sure.equals("N"))
                    {
                        System.out.println("Please enter either Y (Yes) or N (No):");
                        sure = input.nextLine().trim();
                        System.out.println();
                    }
                    if (sure.equals("Y"))
                        break;
                    else
                        hasColour = false;
                }
            }while (!hasColour);
            //edit price
            System.out.println("Please edit car's price:");
            String price = input.nextLine().trim();
            System.out.println();
            if (price.length() != 0)
            {
                while (!InputIsDigit(price) ||
                       Integer.parseInt(price) < 500 ||
                       Integer.parseInt(price) > 30000)
                {
                    System.out.println("Error: the price should between 500-30000 (inclusive).");
                    System.out.println("Please enter car's price:");
                    price = input.nextLine().trim();
                    System.out.println();
                }
                int carPrice = Integer.parseInt(price);
                carDatabase.setNewPrice(registrationNumber, carPrice);
                doEdit = true;
            }
            if (!doEdit)
                System.out.println("You haven't made any changes.");
            else
            {
                System.out.println("The latest information for Car " +
                                   registrationNumber);
                System.out.println("        " + carDatabase.getCar(registrationNumber));                   
            }
        }
        else
            System.out.println("No such car with this Registration Number");
    }
    
    /**
     * main menu option 1
     * 
     * @param     no param
     * @return    no return value
     */
    public void optionSearchCars()
    {
        Scanner input = new Scanner(System.in);
        boolean backMainMenu = false;
        String choice = "";
        do
        {
            searchCarsMenuDisplay();  // submenu
            choice = input.nextLine().trim();
            System.out.println();
            while (!InputIsDigit(choice) ||
                   Integer.parseInt(choice) == 0 ||
                   Integer.parseInt(choice) > 5)
            {
                System.out.println("Error: You have to enter a number, and it's between 1-5");
                System.out.println();
                System.out.println("Please enter an options:");
                choice = input.nextLine().trim();
                System.out.println();
            }
            int option = Integer.parseInt(choice);
            backMainMenu = searchCarsOptions(option);
        }while (!backMainMenu);
        
    }
    
    /**
     * search Cars By CarMake And CarModel
     * 
     * @param     no param
     * @return    no return value
     */
    public void searchCarsByCarMakeAndCarModel()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please select Car Make:");
        carMakerDatabase.showCarsMake();  // Displays Car Make information
        String choice = input.nextLine().trim();
        System.out.println();
        while (!InputIsDigit(choice) ||
               Integer.parseInt(choice) == 0 ||
               Integer.parseInt(choice) > carMakerDatabase.getCarMakerListSize())
        {
            System.out.printf("Error: You have to enter a number, and it's between 1-%d:",
                              carMakerDatabase.getCarMakerListSize());
            System.out.println();
            System.out.println("Please select Car Make:");
            choice = input.nextLine().trim();
            System.out.println();
        }
        int optionCarMake = Integer.parseInt(choice);
        System.out.println("Please select Car Model:");
        // Displays the Car Model by passing in the location in the database you want to access
        carMakerDatabase.showCarsModel(optionCarMake);
        choice = input.nextLine().trim();
        System.out.println();
        while (!choice.toUpperCase().equals("ANY") &&
               (!InputIsDigit(choice) ||
                Integer.parseInt(choice) < 1 ||
                Integer.parseInt(choice) > 
                carMakerDatabase.getCarModelSize(carMakerDatabase.getCarMaker(optionCarMake))))
        {
            System.out.printf("Error: You have to enter a number, and it's between 1-%d or 'ANY' to show the whole model under this maker:",
                              carMakerDatabase.getCarModelSize(carMakerDatabase.getCarMaker(optionCarMake)));
            System.out.println();
            System.out.println("Please select Car Model:");
            choice = input.nextLine().trim();
            System.out.println();
        }
        // Type 'any' to display vehicle information for all models
        if (choice.toUpperCase().equals("ANY"))
            carDatabase.showCarByCarMake(carMakerDatabase.getCarMake(optionCarMake));
        else
        {
            // Returns the location of the list of models in the carmaker object with the option
            int optionCarModel = Integer.parseInt(choice);
            carDatabase.showCarByCarMakeAndCarModel(carMakerDatabase.getCarMake(optionCarMake),
                                                    carMakerDatabase.getCarModel(optionCarMake, optionCarModel));
        }
    }
    
    /**
     * search Cars By Age
     * 
     * @param     no param
     * @return    no return value
     */
    public void searchCarsByAge()
    {
        boolean ageEqual = false;
        String age;
        do
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter Cars' age:");
            age = input.nextLine().trim();
            System.out.println();
            while (!InputIsDigit(age))
            {
                System.out.println("Error: You have to input a number, and it's a non-negative integer");
                System.out.println("Please enter Cars' age:");
                age = input.nextLine().trim();
                System.out.println();
            }
            System.out.println("Please enter Cars' age again:");
            String ageAgain = input.nextLine().trim();
            System.out.println();
            if (!ageAgain.equals(age))
            {
                ageEqual = false;
                System.out.println("The two inputs are not equal. Please enter again.");
            }
            else
                ageEqual = true;
        }while (!ageEqual);
        carDatabase.showCarByAge(Integer.parseInt(age));
    }
    
    /**
     * search Cars By Price
     * 
     * @param     no param
     * @return    no return value
     */
    public void searchCarsByPrice()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter Cars' minimum price:");
        String minPrice = input.nextLine().trim();
        while (!InputIsDigit(minPrice) ||
               Integer.parseInt(minPrice) < 500 ||
               Integer.parseInt(minPrice) >= 30000 )
        {
            System.out.println("Error: the minimum price should between 500(inclusive) - 30000 (not inclusive)");
            System.out.println("Please enter Cars' minimum price:");
            minPrice = input.nextLine().trim();
            System.out.println();
        }
        System.out.println("Please enter Cars' maximum price:");
        String maxPrice = input.nextLine().trim();
        System.out.println();
        while (!InputIsDigit(maxPrice) ||
               Integer.parseInt(maxPrice) <= 500 ||
               Integer.parseInt(maxPrice) > 30000 )
        {
            System.out.println("Error: the maximum price should between 500(not inclusive) - 30000 (inclusive)");
            System.out.println("Please enter Cars' maximum price:");
            maxPrice = input.nextLine().trim();
            System.out.println();
        }
        while (Integer.parseInt(minPrice) >= Integer.parseInt(maxPrice))
        {
            System.out.println("Error: the maximum price should be greater than the minimum price");
            System.out.println("Please enter Cars' maximum price:");
            maxPrice = input.nextLine().trim();
            System.out.println();
        }
        carDatabase.showCarByPrice(Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
    }
    
    /**
     * search Cars By Registration Number
     * 
     * @param     no param
     * @return    no return value
     */
    public void searchCarsByRegistrationNumber()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter vehicle registration number:");
        String registrationNumber = input.nextLine().trim().toUpperCase();
        while (!validRegistrationNumber(registrationNumber))
        {
            System.out.println("Please enter vehicle registration number" + 
                               "(Only “A”-“Z”, “a”-“z” or “0-9 and maximum 6-character”):");
            registrationNumber = input.nextLine().trim().toUpperCase();
        }
        boolean numberExists = carDatabase.findCarByRegistrationNumber(registrationNumber);
        if (numberExists)
        {
            System.out.printf("The details of the car registration number %s are ",
                              registrationNumber);
            System.out.println();
            System.out.println("    " + carDatabase.getCar(registrationNumber));
        }
        else
            System.out.println("No such car with this Registration Number");
    }
    
    /**
     * menu of Search Cars
     * 
     * @param     no param
     * @return    no return value
     */
    public void searchCarsMenuDisplay()
    {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Car Searching Options:");
        System.out.println();
        System.out.println("    (1) By Registration Number");
        System.out.println("    (2) By Car Make and Car Model");
        System.out.println("    (3) By Car Age");
        System.out.println("    (4) By Price (range)");
        System.out.println("    (5) Back to Main Menu");
        System.out.println();
        System.out.println("Please enter your options:");
    }
    
    /**
     * receive one choice from user to calls submenu relevant methd
     * 
     * @param     int type option
     * @return    boolean
     */
    public boolean searchCarsOptions(int option)
    {
        switch (option)
        {
            case 1:
                searchCarsByRegistrationNumber(); break;
            case 2:
                searchCarsByCarMakeAndCarModel(); break;
            case 3:
                searchCarsByAge(); break;
            case 4:
                searchCarsByPrice(); break;
            case 5:
                return true;
            default:
                System.out.println("no this option"); break;
        }
        return false;
    }
    
    /**
     * This method is called when the input name matches the color rule.
     * Returns true if the entered color name is correct
     * Otherwise, the match method of CarcoloursDatabase is called, 
     * showing colors with a similar name.
     * 
     * @param     String colourName
     * @return    boolean
     */
    public boolean validColour(String colourName)
    {
        if (carColoursDatabase.findColourByName(colourName))
            return true;
        else
        {
            carColoursDatabase.matchColorByName(colourName);
            return false;
        }
    }
    
    /**
     * judeg whether the string meets the requirements of rule of name.
     * Start with a letter.
     * Returns validColour() if the rule is valid, otherwise return false.
     * 
     * @param     String colourName
     * @return    boolean
     */
    public boolean validColourName(String colourName)
    {
        String colour = colourName.trim();
        if (!Character.isLetter(colour.charAt(0)))
            return false;
        for (int i = 0; i < colour.length(); i++)
        {
            if (colour.charAt(i) == ' ' ||
                Character.isLetter(colour.charAt(i)) ||
                Character.isDigit(colour.charAt(i)))
                return validColour(colour);
        }
        return validColour(colour);
    }
    
    /**
     * Determine whether the input string meet the rules of registration number
     * 
     * @param     String input
     * @return    boolean
     */
    public boolean validRegistrationNumber(String input)
    {
        if (input.length() > 6 ||
            input.length() == 0)
            return false;
        for (int i = 0; i < input.length(); i++)
        {
            if (!Character.isDigit(input.charAt(i)) &&
                !Character.isLetter(input.charAt(i)))
                return false;
        }
        return true;
    }
    
}
