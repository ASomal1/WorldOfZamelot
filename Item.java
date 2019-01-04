/**
 * The item class holds all the items that will be present 
 * in the game world of Zamelot 
 * 
 * @author Amanjit 
 * @version 2017.12.08
 */
public class Item
{
    private String name; //name of item 
    private String description; //description of item
    private double weight; // the weight of item
    private boolean canPickUp=true; //holds the item to be picked up is true
    private boolean canDrink; // whether the item is drinkable or not
    private String anEffect=null; // the effect of an object

    /**
     * Constructor
     * 
     * @param name the name of the object
     * @param description the description of the object
     * @param weight how much space object takes in bag 
     */
    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Sets whether the player can or cannot pick up item
     * @param condition True if pick it up, false if not
     */
    public void setCanPickUp(boolean condition) {
        canPickUp = condition;
    }

    
   /**
    * Sets whether the item has a certain effect or not
    * @param string effect 
    */
    public void setAnEffect(String effect)
    {
        anEffect = effect;
    }
    
    
    /**
     * Sets whether the player can or cannot d 
     * @param true if can drink, false if otherwise
     */
    public void setDrink(boolean condition)
    {
        canDrink = condition;
    }
    
    /**
     * returns if the player can drink this item or not 
     */
    public boolean canDrink()
    {
        return canDrink;
    }
    
    /**
     * returns if the item has an effect
     */
    public String getAnEffect()
    {
        return anEffect;
    }
    
    /**
     * @return the weight of the item
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the name of the item
     * @return A string with the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns if the player is able to loot this item or not
     * @return True if can pick up, false if not
     */
    public boolean canPickUp() {
        return canPickUp;
    }

    /**
     * Returns an description of the item.
     * @return A string with the description
     */
    public String getDescriptionString()
    {
        return name + " - " + description + ". Weight: " + weight;
    }
}