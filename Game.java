import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes and Amanjit Somal
 * @version 2017.12.08
 */

public class Game 
{
    public static void main (String[] args) { 
        Game obj = new Game();
        obj.play();
    }

    private Parser parser;
    private Room currentRoom; // the currentRoom in which the player is in
    private Player player;
    private ArrayList<Room> rooms; // holds all the rooms in an arraylist
    private ArrayList<Room> rooms2; // holds certain rooms in an arraylist
    private boolean wantToQuit; //boolean declaration for quitting the game
    private ArrayList<Character> characterList; // holds all the characters in an arraylist

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        rooms = new ArrayList<Room>();
        rooms2 = new ArrayList<Room>();
        characterList = new ArrayList<Character>();
        createGame();
        parser = new Parser();
        play();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createGame()
    {
        Room outsideCastle1, outsideCastle2, castle, pub, witchesHouse, path, teleportForest, randomLocation, dungeon, riverDeath, riverDrug, home;   //castle locked door

        // create the rooms
        outsideCastle1 = new Room("Castle Black back entrance", "You are outside the back entrance of Castle Black", false);
        outsideCastle2 = new Room("Castle Black main entrance", " You are outside the main entrance of Castle Black", false);
        castle = new Room("Castle Black", " You are in the castle and find it prestigous for your brain to cooperate", false);
        pub = new Room("Moonraker Pub", " Where the finest ale is produced", false);
        witchesHouse = new Room("The Witches House", " You are in the presence of a great magical sorceress", false);
        path = new Room("Path", " The path will uncover hidden secrets and offer friends or foes", false);
        teleportForest = new Room("The forest", " The forest is a magical place, proceed with caution", false);
        randomLocation = new Room("At a random room in the game", "This which magically teleport you to another place in the game", true);
        dungeon = new Room("The dungeon!", "This is where the Stark Bow lies", false);

        riverDeath = new Room("River of Death", "Dead bodies floating in blood", false);
        riverDrug = new Room("River of Drug", "It looks murky: Do not be tempted by influence!", false);

        //rooms in a list

        rooms.add(outsideCastle1);
        rooms.add(outsideCastle2);
        rooms.add(castle);
        rooms.add(pub);
        rooms.add(witchesHouse);
        rooms.add(path);
        rooms.add(teleportForest);
        rooms.add(randomLocation);
        rooms.add(dungeon);
        rooms.add(riverDeath);
        rooms.add(riverDrug);

        //rooms in a list 2
        rooms2.add(outsideCastle1);
        rooms2.add(outsideCastle2);
        rooms2.add(castle);
        rooms2.add(pub);
        rooms2.add(witchesHouse);
        rooms2.add(path);
        rooms2.add(teleportForest);
        rooms2.add(randomLocation);
        rooms2.add(riverDeath);
        rooms2.add(riverDrug);

        // Setup outsideCastle 1 
        outsideCastle1.setExits("north", riverDeath);
        outsideCastle1.setExits("east", castle);
        outsideCastle1.addItem("sword", "A shiny sword lying under leaves and dirt, this might come in handy later on ", 25.0);
        outsideCastle1.getItem("sword ");
        outsideCastle1.addItem("waterskin", "A container filled with cold water ", 5.0); 
        outsideCastle1.getItem("waterskin").setDrink(true);
        outsideCastle1.getItem("waterskin").setAnEffect("Waterskin");
        outsideCastle1.addItem("statue", "The statue reads: 'The great Stark Warrior' and has no head ", 50.0);
        outsideCastle1.getItem("statue").setCanPickUp(false);
        outsideCastle1.addCharacter("raven","Squark Squark");

        // Setup castle
        castle.setExits("east", outsideCastle2);
        castle.setExits("west", outsideCastle1);
        castle.addCharacter("Ghost of Edgar Stark", "Finndddd the Sword");
        castle.addCharacter("Stray dog", "This smelly dog is unwanted everywhere");
        castle.addItem("coins", "5 Shiny golden coins might come in handy later on", 5.0);
        castle.getItem("coins").setAnEffect("coinstoman");

        // Setup outsideCastle 2 
        outsideCastle2.setExits("north", riverDeath);
        outsideCastle2.setExits("east", riverDrug);
        outsideCastle2.setExits("south-west", pub);
        outsideCastle2.setExits("west", castle);
        outsideCastle2.addItem("boxes", "The boxes are rotten with creatures crawling around them", 10.0);
        outsideCastle2.getItem("boxes").setCanPickUp(false);
        outsideCastle2.addCharacter("Hybrid eagle", "This eagle is dangerous...it looks like the outcome of a magically experiment gone wrong!");

        // Setup pub
        pub.setExits("north-east", outsideCastle2);
        pub.setExits("east", riverDrug);
        pub.setExits("south", witchesHouse);
        pub.addCharacter("Greg", "Oi lassie, you wan' to get drink some'ing?");
        pub.addCharacter("man", "Mate, can you spare me som' change?");
        pub.addItem("shot", "These are very intense shots, you risk losing the game", 1.0);
        pub.getItem("shot").setDrink(true);
        pub.getItem("shot").setAnEffect("shot");
        pub.addItem("beer", "Stale bread, but food is food", 5.0);
        pub.getItem("beer").setDrink(true);
        pub.getItem("beer").setAnEffect("beer");
        pub.addItem("chair", "very heavy piece of wood", 45.0);
        pub.getItem("chair");
        pub.addItem("table", "very heavy piece of wood", 55.0);
        pub.getItem("table");

        // Setup witchesHouse
        witchesHouse.setExits("north", pub);
        witchesHouse.setExits("north-east", path);
        witchesHouse.setExits("east", riverDrug);
        witchesHouse.setExits("south", randomLocation);
        witchesHouse.addItem("teleporter", "Teleporter from dungeon to castle.", 10); 
        witchesHouse.getItem("teleporter");
        witchesHouse.addCharacter("New Orleans Witch", "You require assistance, you must return the sword to the castle from the dungeon");

        // Setup path
        path.setExits("north", outsideCastle2);
        path.setExits("north-west", pub);
        path.setExits("north-west", riverDrug);
        path.setExits("west",  witchesHouse);
        path.setExits("east", teleportForest);

        // Setup riverDeath
        riverDeath.setExits("south-west", outsideCastle1);
        riverDeath.setExits("south", outsideCastle2);

        // Setup riverDrug
        riverDrug.setExits("north-west", outsideCastle2); 
        riverDrug.setExits("west", pub);
        riverDrug.setExits("south-west", witchesHouse);
        riverDrug.setExits("south", path);
        riverDrug.setExits("east", teleportForest);

        // Setup teleportForest
        teleportForest.setExits("west", riverDrug);
        teleportForest.setExits("east", dungeon);
        teleportForest.addCharacter("Good elf", "");
        teleportForest.addCharacter("Bad elf", "");

        // Setup dungeon 
        dungeon.setExits("west", teleportForest);
        dungeon.addItem("bow", "This sword possess magical qualities", 70.0);
        dungeon.getItem("bow");
        dungeon.addCharacter("Monster", "This bad guy guards the sword that you are after");

        //Player is set outside
        player = new Player(outsideCastle1);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            //Checks if the player is out of rounds!
            Command command = parser.getCommand();
            finished = (processCommand(command) || winGame() || loseGame());

        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Prints the Welcome messeges
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zamelot!");
        System.out.println("World of Zamelot is the best, exciting adventure game!");
        System.out.println("Your task is to find the Sword of Light and return it safely to it's owner");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println("Your commands are: " + parser.getAllCommands());  //a list of all the commands are printed out 
        System.out.println();

    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        //if the command word is unknown then message is printed
        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        //command words of the game call the methods in this class
        if (commandWord == CommandWord.HELP)
            printHelp();
        else if (commandWord == CommandWord.GO)
            goRoom(command);
        else if (commandWord == CommandWord.LOOK)
            look();
        else if (commandWord == CommandWord.PICKUP)
            pickUp(command);
        else if (commandWord == CommandWord.DROP)
            drop(command);
        else if (commandWord == CommandWord.PLAYERBAG)
            showPlayerbag();
        else if (commandWord == CommandWord.BACK)
            goBack();  
        else if (commandWord == CommandWord.GIVE)
            give(command);
        else if (commandWord == CommandWord.DRINK)
            drink(command);
        else if (commandWord == CommandWord.RESTART)
            restart(command);
        else if (commandWord == CommandWord.QUIT)
            wantToQuit = quit(command);

        return wantToQuit;
    }

    public void give(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Give what?");
        }
        if(!command.hasThirdWord()) {
            System.out.println("Give item to whom?");
        }
        String item = command.getSecondWord();
        String character = command.getThirdWord();

        if (player.getItem(item).getAnEffect().equals("coinstoman")){
            {
                if (!(player.isInPlayerBag(item))){
                    System.out.println("You cannot do this!");
                }
                player.getCurrentRoom().getCharacter("man").addItemToCharacterBag(player.getItem(item));
                player.removeItemFromPlayerBag(item);
                player.getCurrentRoom().getCharacter("man");
                System.out.println("Man: 'Thanks for the coins! You know what you should do? Head east from that ol' Witches House and you will find the dungeon eastwardssss!");
                return;
            }
        }
        return;
    }
    
    
    
    private void drink(Command command)
    {
        // checks what item the player wants to eat 
        if(!command.hasSecondWord()) {
            System.out.println("Drink what?");
            return;
        }
        String item = command.getSecondWord(); 

        //check's if item is in playerbag
        if(!player.isInPlayerBag(item)){
            System.out.println("You do not drink this item, idiot!");
            return;
        }
        //check's if item is drinkable
        if(!player.getItem(item).canDrink()){
            System.out.println("You cannot drink this item, idiot!");
            return;
        }
        //check's if item that is drinkable has an effect
        if (player.getItem(item).getAnEffect()!=null){
            if (player.getItem(item).getAnEffect().equals("shot"))
            {
                player.decreasePlayerStrength(10);
                player.removeItemFromPlayerBag(item);
                System.out.println("You have drunk some strong stuff...you feel dizzy and slightly weak!");
                return;
            }
            if (player.getItem(item).getAnEffect().equals("beer"))
            {
                player.decreasePlayerStrength(5);
                player.removeItemFromPlayerBag(item);
                System.out.println("You have drunk some beer, you feel a bit woozy!");
                return;
            }
            if (player.getItem(item).getAnEffect().equals("waterSkin"))
            {
                player.increasePlayerStrength(50);
                player.removeItemFromPlayerBag(item);
                System.out.println("You have eaten some bread and feel a burst of energy!");
                return;
            }
        }
        System.out.println("You have drunk " + item); 
    }

    /**
     * Print out some help information.
     * Some basic information, number of rounds left to play and valid commands.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the mysterious and dangerous land of Zamelot.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getAllCommands());
    }

    /**
     * To pick up an item from a room
     * @param command The item to be picked up
     */
    private void pickUp(Command command) {
        wantToQuit = false;
        if(!command.hasSecondWord()) {
            System.out.println("Pick up what?");
            return;
        }

        String item = command.getSecondWord();

        Room currentRoom = player.getCurrentRoom();
        Item itemToBePicked = currentRoom.getItem(item);
        //Check if the item exists
        if (itemToBePicked==null) {
            System.out.println("My dear, you must be delusional...there is no such item");
            return;
        } else if (!currentRoom.ItemCanPickUp(itemToBePicked)) {
            System.out.println("You are not allowed to take this item");
            return;
        }
        else if (!player.isAbleToCarry(currentRoom.getItem(item)))
        {
            System.out.println("This item is to heavey for you to pick up!");
            return;
        }

        currentRoom.removeItem(item);
        player.addItemToPlayerBag(itemToBePicked);

        System.out.println("You successfully picked up " + item);
        return;
    }

    /**
     * Drops an item from playerbag in the current room
     * @param command The item that should be dropped
     */
    private void drop(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        } 
        String item = command.getSecondWord();
        Room currentRoom = player.getCurrentRoom();
        //Check if the item exists
        if (!player.isInPlayerBag(item)) {
            System.out.println("You dont have this item!");
            return;
        }
        currentRoom.addItem(player.getItem(item));
        player.removeItemFromPlayerBag(item);
        System.out.println("You successfully dropped " + item);

    }

    /**
     * Displays the players bag
     */
    private void showPlayerbag() {
        System.out.println(player.getPlayerBagString());
    }

    /**
     * Looks around in the room
     */
    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println();
    }

    /**
     * Moves the player backwards from current room to previous room
     */
    private void goBack() {
        if (player.movePreviousRoom())
        {         
            System.out.println(player.getCurrentRoom().getLongDescription().toLowerCase().trim());
        }
        else
        {
            System.out.println("You cant go back from here");
        }
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param coomand The chossen exit 
     */
    private void goRoom(Command command) 
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room roomToLeave = player.getCurrentRoom();
        Room nextRoom = roomToLeave.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no exit!");
        }

        else {
            player.movePlayer(nextRoom);
            if(player.getCurrentRoom().getTeleport())
            {   
                Random randomGenerator = new Random();
                player.teleport(rooms2.get(randomGenerator.nextInt(rooms2.size())));
                System.out.println("You have been magically transported to another room!");
            }
            System.out.println(player.getCurrentRoom().getLongDescription().toLowerCase().trim());
            System.out.println();

        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param Command the Quit command.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * "Restart" was entered
     * @param Command the Restart command
     * @return, the game is initialised again from the start 
     */
    private void restart(Command command)
    {
        rooms = new ArrayList<Room>();
        createGame();
        parser = new Parser();
        play();
    }

    /**
     * The player wins the game if the player is in dungeon and has item bow
     * in playerbag
     * @return quit the game, the player has won
     */
    public boolean winGame()
    {
        wantToQuit = false;

        if (player.isInPlayerBag("bow")==true){
            System.out.println("You have won the game!");
            wantToQuit = true;
        }
        return wantToQuit;
    }

    /**
     * The player loses the game if the player's strength is 0 or below
     * @return quit the game, the player has lost 
     */
    public boolean loseGame()
    {
        wantToQuit = false;
        if (player.playerStrength <= 0){
            System.out.println("You have lost the game!");
            wantToQuit = true;
        }
        return wantToQuit;
    }

    
}
