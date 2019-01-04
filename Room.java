import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes and Amanjit Somal
 * @version 2017.12.08
 */
public class Room 
{
    private String description; // the description of the room the player enters
    private String roomName; // the name of the room the player enters
    private HashMap<String, Room> exits; // holds all the exits of the rooms
    private HashMap<String, Item> items; // holds all the items in the rooms
    private HashMap<String, Character> characters; //holds all the characters in the room
    private boolean teleport; //the room which teleports the player to a random location 

    /**
     * Create a room described "description", roomName and boolean teleport 
     * @param description The room's description, roomName The room's name, teleport The room that teleports
     * player to random location .
     */
    public Room(String roomName, String description, boolean teleport) 
    {
        this.roomName = roomName;
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, Item>();
        characters = new HashMap<String, Character>();
        this.teleport = teleport;

    }

     /**
     * Accsessor method
     * @return The name of the room
     */
    public String getName()
    {
        return roomName;
    }
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

     /**
     * Return the character in the room
     * @param direction The name of the character
     * @return The name of the character in the room
     */
    public Character getCharacter(String name) {
        return characters.get(name);
    }

    /**
     * Return a description of the room's exist,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit: keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Adds a item to the room
     * @param name The name of the item
     * @param description The description of the item
     * @param weight The weight of the item
     */
    public void addItem(String name, String description, double weight)
    {
        items.put(name, new Item(name, description, weight));
    }

    /**
     * Adds a item to the room
     * @param item The item to be added
     */
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    /**
     * Adds a character to the room
     * @param name The name of the character added
     * @param description The description of the character added
     */
    public void addCharacter(String name, String description) {
         characters.put(name, new Character(name, description));
    }

    /**
     * Remove a item from the room
     * @param item The name of the item to be removed
     */
    public void removeItem(String item) {
        items.remove(item);
    }

    /**
     * Returns an item the exits is the room
     * @param itemName The name of the item that should be returned
     * @return The item!
     */
    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    /**
     * Define the exits from this room.
     * to another room or is null (no exit there).
     * @param direction The direction of the exit.
     * @param neighbor The room in the fiven diretion.
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return description of this room
     * @return A description of the room, includning exits.
     */
    public String getLongDescription()
    {   
        String returnString;

        returnString= "You are at " + roomName + " " + description + "."; 
        returnString += getItemString();
        returnString +="\n"+ getCharacterString();
        returnString +="\n\n"+ getExitString();
        return returnString;
    }

    /**
     * Returns String contaning the items in the room
     * @return A string with item information
     */
    public String getItemString(){
        String itemString = "\nItems:";
        if(!items.isEmpty()){

            Set<String> itemNames = items.keySet(); 
            for(String itemName : itemNames){
                itemString += "\n  " + getItem(itemName).getDescriptionString();
            }
            return itemString;
        }      
        else
            return itemString + "\nNone!";  
    }

    /**
     * Returns a String regarding the characters in the room
     * @return A string with characters information
     */
    public String getCharacterString(){
        String charactersString = "Characters:";
        if(!characters.isEmpty()){

            Set<String> characterNames = characters.keySet(); 
            for(String characterName : characterNames){
                charactersString += "\n  " + getCharacter(characterName).getFullDescription();
            }
            return charactersString;
        }      
        else
            return charactersString + "\nNone!"; 
    }

    /**
     * Checks if the player is allowed to take a certain item
     * @param item The item to check
     * @return if allowed true, false if not
     */
    public boolean ItemCanPickUp(Item item)
    {
        return item.canPickUp();
    }
    
    /**
     * Checks if the player is randomly teleported from this room
     * @return if true then player is teleported, if not then false
     */
    public boolean getTeleport()
    {
        return teleport;
    }
}