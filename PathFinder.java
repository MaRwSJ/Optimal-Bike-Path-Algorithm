
public class PathFinder {
	/**
	 * @author Marwa Jamali
	 * During the process of the bike path finder assignment, I decided to use an approach I am now becoming familiar with due to the past assignments, by using map data and appropriate path finding techniques, 
	 * this Java project may design the optimum route for a bike path as well as the other functions this code has. Keeping in mind that the quality and accuracy of the map data I utilized, as well as the 
	 * technological implementation, will most definitely determine how well your bike path project turns out and is a technique I used.
	 * The code created required me to build a network of bike lanes that link the park's various natural gems, including beautiful vantage spots, picnic spaces, and historical monuments.
	 * To accomplish this, I had to create a software that determines the best bike pathways while taking the topography and security of the park into account using various
	 * constructors and creating 2 new classes that were the base of the programs output. 
	 */
	
		// This is a reference to an object of the provided class Map that represents the chambers of the Pyramid Falls National Park.
	    private Map pyramidMap;
	    
	    /**
	     * Constructor for the class, to create a map from the text file to find a path for
	     * @param fileName which is the name file
	     * @throws InvalidMapCharacterException if an invalid character is found
	     */
	    public PathFinder(String fileName) {
	        try {
	            pyramidMap = new Map(fileName);
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }
	    /**
	     * Finds a path leading to all treasures in the map and stores it in a stack
	     * @return the stack containing all of the chambers
	     */
	    @SuppressWarnings("rawtypes")
		public DLStack path() {
	        DLStack<Chamber> stack = new DLStack<Chamber>();
	        int N = pyramidMap.getNumTreasures();
	        int treasuresFound = 0;

	        stack.push(pyramidMap.getEntrance());
	        stack.peek().markPushed();

	        while (!stack.isEmpty() && treasuresFound != N) {
	            Chamber c = stack.peek();

	            // Setting the current chamber as the best adjacent chamber and push it into the stack
	            c = bestChamber(c);

	            if (c != null) {
	                stack.push(c);
	                stack.peek().markPushed();
	                if (stack.peek().isTreasure()) treasuresFound++;
	            }

	            // Popping the current chamber if no adjacent chamber 
	                if (c == null) {
	                stack.peek().markPopped();
	                stack.pop();
	            }
	        }

	        return stack;
	    }

	    /**
	     * @return the value of pyramidMap
	     */
	    public Map getMap() {
	        return pyramidMap;
	    }

	    /**
	     * Checking whether the current chamber is dim or not
	     * @param currentChamber 
	     * @return true if chamber should be considered dim and is false otherwise
	     */
	    public boolean isDim(Chamber currentChamber) {
	        boolean dim = false;
	        if (currentChamber != null && !currentChamber.isSealed() && !currentChamber.isLighted()) {
	            for (int i = 0; i <= 5; i++) {
	                if (currentChamber.getNeighbour(i) != null)
	                    if (currentChamber.getNeighbour(i).isLighted()) {
	                        dim = true;
	                        break;
	                    }
	            }
	        }

	        return dim;
	    }
	    /**
	     * Selects the best chamber to move to from currentChamber according to these 
	     * restrictions specified in the introduction
	     * @param currentChamber the current chamber
	     * @return the best neighboring chamber to travel to
	     */
	    public Chamber bestChamber (Chamber currentChamber) {
	        boolean treasureFound = false;
	        for (int i = 0; i <= 5; i++) {
	            // Checking if the neighbor chamber is treasure
	        	// Checking if not marked also
	            if(currentChamber.getNeighbour(i) != null) {
	                if (currentChamber.getNeighbour(i).isTreasure() && !currentChamber.getNeighbour(i).isMarked()) {
	                    treasureFound = true;
	                    return currentChamber.getNeighbour(i);
	                }
	            }
	        }
	        if (!treasureFound) {
	        	// If treasure is not found check chambers
	            for (int i = 0; i <= 5; i++) {
	                if(currentChamber.getNeighbour(i) != null) {
	                    if (currentChamber.getNeighbour(i).isLighted() && !currentChamber.getNeighbour(i).isMarked()) {
	                        return currentChamber.getNeighbour(i);
	                    } else if (isDim(currentChamber.getNeighbour(i)) && !currentChamber.getNeighbour(i).isMarked()) {
	                        return currentChamber.getNeighbour(i);
	                    }
	                }
	            }
	        }
	        return null;
	    }

	}
	    

	    


	    
