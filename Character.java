import java.util.HashMap;
import java.util.Set;
/**
 * A class which can create characters for the World of Zuul.
 * This calls contains characters name, items in characterbag and character response
 *
 * @author Amanjit
 * @version 2017.12.08
 */
public class Character
{
    private String name; // the name of the character
    private String response; // the descriptive response of the character
    private HashMap<String, Item> characterBag; //holds all the items in the playerbag

    /**
     * Constructor for objects of class character
     */
    public Character(String name, String response)
    {
        this.name = name;
        this.response = response;
        characterBag = new HashMap<String, Item>();

    }

    /**
     * @return The name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * @return The description of the character
     */
    public String getFullDescription(){
        return name + ": '" + response + "'";
    }

    /**
     * Returns an item from the charactersBag
     * @param item The item which should be looked for
     * @return The item from the charactersBag
     */
    public Item getItem(String item) {
        return characterBag.get(item);
    }

    /**
     * Adds an item to the characters bag
     * @param item The item to be added
     */
    public void addItemToCharacterBag(Item item) {
        characterBag.put(item.getName(), item);
    }

    /**
     * Removes an item from the characters bag
     */
    public void removeItemFromCharacterBag(String item) {
        characterBag.remove(item);
    }

    /**
     * Checks if an item exists in the character bag or not
     * @return True if it exists, false if not
     */
    public boolean isInCharacterBag(String itemName)
    {
        return characterBag.containsKey(itemName);
    }
}