import java.util.ArrayList;

/**
 * The CarMaker class is used to create CarMaker objects.
 * The CarMaker class implements the method of setting and getting the attributes of the CarMaker.
 * The CarMaker class contains seven attributes:
 *     String type carMake, ArrayList<String> type carModels.
 *
 * @author Jiakaixiang Duan
 * @version 1.1v 13/5/2021 0:16
 */
public class CarMaker
{
    // instance variables - replace the example below with your own
    private String carMake;
    private ArrayList<String> carModels;
    
    /**
     * default constructor of CarMaker
     */
    public CarMaker()
    {
        carModels = new ArrayList();
        carMake = "";
    }
    
    /**
     * Constructors for objects of class Car that take newCarMake and newCarModels
     */
    public CarMaker(String newCarMake, ArrayList<String> newCarModels)
    {
        carMake = newCarMake;
        carModels = newCarModels;
    }
    
    /**
     * Allows an external function to call this method to get car make
     *
     * @param     no parameter
     * @return    string type carMake
     */
    public String getCarMake()
    {
        return carMake;
    }
    
    /**
     * Allows an external function to call this method to get 
     * the number of car models under special car maker
     *
     * @param     no parameter
     * @return    int type carModels.size()
     */
    public int getCarModelListSize()
    {
        return carModels.size();
    }
    
    /**
     * Allows an external function to call this method to get car model by index
     *
     * @param     int type index     pass an index to get car model
     * @return    carModels.get(index - 1) or "Error: invalid index"
     */
    public String getCarModel(int index)
    {
        if (index > carModels.size() ||
            index < 0)
            return "Error: invalid index";
        else
            return carModels.get(index - 1);
    }
    
    /**
     * Allows an external function to call this method to get String type car models
     *
     * @param     no parameter
     * @return    string type in.toString()
     */
    public String getCarModels()
    {
        StringBuffer in = new StringBuffer("");
        for (int i = 0; i < carModels.size(); i ++)
        {
            in.append(carModels.get(i));
            in.append(",");
        }
        in.delete(in.length()-1, in.length());
        return in.toString();
    }
    
    /**
     * Allows an external function to call this method to set car make by pass a new car make
     *
     * @param     String type newCarMake
     * @return    no return value
     */
    public void setCarMake(String newCarMake)
    {
        carMake = newCarMake;
    }
    
    /**
     * Allows an external function to call this method to set car models by pass a new car models list
     *
     * @param     ArrayList<String> type newCarModels
     * @return    no return value
     */
    public void setCarModels(ArrayList<String> newCarModels)
    {
        carModels = newCarModels;
    }
    
    /**
     * Prints the current object as a string
     * 
     * @param     no parameter
     * @return    String type carMakerString
     */
    public String toString()
    {
        StringBuffer in = new StringBuffer("");
        for (int i = 0; i < carModels.size(); i ++)
        {
            in.append(",");
            in.append(carModels.get(i));
        }
        String carMakerString = carMake +
                    in.toString();
        return carMakerString;
    }
}
