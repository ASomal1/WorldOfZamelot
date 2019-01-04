import java.util.Stack;
import java.util.HashMap;
import java.util.Set;

/**
 * A class which holds information for the player in the World of Zamelot.
 * It holds the information about the player, the previousRoom the player has visited and the items the player holds in its player bag.
 * 
 * @author Amanjit
 * @version 2017.12.08
 */
public class Player
{

    private Room currentRoom; // the current room the player is in
    private Stack<Room> previousRooms; // contains all the previous rooms the player has been to 
    private HashMap<String, Item> playerBag; //holds all the items in the player's bag
    public double playerStrength=15; //declares the strength of the player is 15 to begin the game with 
    


    /**
     * Constructor for objects of class Player
     */
    public Player(Room currentRoom)
    {
        this.currentRoom = currentRoom;
        previousRooms = new Stack<Room>();
        playerBag = new HashMap<String, Item>();
        playerStrength = 15;
    }

    /**
     * the last room visited by player
     */
    public Room getLastRoom()
    {
        return previousRooms.peek();
    }

    /**
     * Increases the strength of the player
     * @param the strength increases by increment
     */
    public void increasePlayerStrength(double increment)
    {
        this.playerStrength += increment;
        
    }
    
    
    /**
     * Decreases the strength of the player
     * @param the strength decreases by decrement
     */
    public void decreasePlayerStrength(double decrement)
    {
        this.playerStrength -= decrement;
    }
    
    
    /**
     * Adds an item to the players bag
     * @param item 
     */
    public void addItemToPlayerBag(Item item) {
        playerBag.put(item.getName(), item);
    }

    /**
     * Removes item from player's bag
     * @param item
     */
    public void removeItemFromPlayerBag(String item) {
        playerBag.remove(item);
    }

    /**
     * Returns an item from the players bag
     */
    public Item getItem(String item) {
        return playerBag.get(item);
    }
    
    /**
     * Returns string of items in the player's bag 
     */
    public String getPlayerBagString() {
        String playerBagString = "PlayerBag:";
        if(!playerBag.isEmpty()){

            Set<String> itemNames = playerBag.keySet(); 
            for(String itemName : itemNames){
                playerBagString = playerBagString + "\n  " + playerBag.get(itemName).getDescriptionString();
            }

            playerBagString = playerBagString + "\nCarrying " + getTotalWeightPlayerBag() + " of the maximum " + getMaxPlayerBagWeight();

            return playerBagString;
        }      
        else
            return playerBagString = playerBagString + "\n Is Empty";  

    }

    /**
     * Total weight of the player's bag 
     */
    public double getTotalWeightPlayerBag() {
        double totalWeight = 0;
        if(!playerBag.isEmpty()){
            Set<String> itemNames = playerBag.keySet();
            for(String itemName : itemNames){
                totalWeight = totalWeight + playerBag.get(itemName).getWeight();
            }
        }
        return totalWeight;
    }

    /**
     * The room which the player is currently in
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Checks the palyerbag to see if item is there 
     */
    public boolean isInPlayerBag(String itemName)
    {
        return playerBag.containsKey(itemName);
    }

    /**
     * Calculates if the player can carry a given item or not
     */
    public boolean isAbleToCarry(Item item) {
        if (item.getWeight()+ getTotalWeightPlayerBag() <= getMaxPlayerBagWeight()) {
            return true;
        }
        return false;
    }

    
    /**
     * This is the maximum weight that the player can hold
     */
    public double getMaxPlayerBagWeight()
    {
        double maxPlayerBagWeight = 100;
        return maxPlayerBagWeight;
    }

    /**
     * Moves the player to another room
     * @param nextRoom The room to player should be moved to
     */
    public void movePlayer(Room nextRoom) {   
        previousRooms.push(currentRoom);
        currentRoom = nextRoom;

    }

    /**
     * Move the player to the latest room
     * @return true if possible, false if not
     */
    public boolean movePreviousRoom()
    {
        if (previousRooms.empty())
        {
            return false;
        }
        else
        {
            Room lastRoom = previousRooms.peek();  //returns  previousRoom, the last item, at the top of this stack which is the previousRoom the player was in as it is now the first lastRoom 
            currentRoom = previousRooms.pop(); //removes previousRoom at the top of stack and places it into currentRoom
            return true;
        }
    }
    
    /**
     * The player is moved to a random room if the player enters the randomLocation room 
     * @param the room which the player enters and is then randomly moved to another room
     */
    public void teleport(Room randomLocation)
    {
        currentRoom = randomLocation;
    }
}