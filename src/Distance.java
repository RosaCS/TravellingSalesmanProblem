
public class Distance 
{

	public static void main(String[] args) 
	{
		
		// 2D array with 10*10 index slots
		//Empty Distance Array
		int[][] distance = new int[10][10];		
		
//################################### Create Distance Table  ###################################
		for(int y=0; y< distance.length;y++) 
		{
			//Initialize random distance in first loop to gurantee that first entry is always 0
			double randomDistance = 0;
			//gives out the Header
			for (int x = y; x < distance[y].length; x++) 
			{
				//fill from left to right
				distance[y][x] = (int) randomDistance;
				//fill from top to down 
				distance[x][y] = (int) randomDistance;
				//change randomdistance
				randomDistance = Math.ceil(Math.random()*45);

			}			
		}
//######################################   End   ###############################################	
		outputDistanceTable(distance);
		
		int[] trip = {1,0,3,2,5,4,7,6,9,8};		
		//calculates the totaldistance of the current trip
		int totalDistance = calculateRoundTrip(trip, distance);
		outputTrip(trip);
		System.out.println("TotalDistance: " + totalDistance);
		
		
		
		int[] optTrip = optimizeTrip(totalDistance, trip, distance);
		
		int optDistance = calculateRoundTrip(optTrip, distance);
		
		outputTrip(optTrip);
		
		System.out.println("TotalDistance: " + optDistance);
		
	}
	
	
	//Puts out the Distancetable to console
	public static void outputDistanceTable(int[][] distance) {
		
		System.out.println("Distancetable for Citites");
		String[] cities = {"0","1","2","3","4","5","6","7","8","9"};
	
		System.out.print("\t");
		for(String city:cities)
		{
			 System.out.print("  " +city+"  \t");
		}
		System.out.println();
		
		for (int y = 0; y < distance.length; y++) {
			System.out.print(cities[y] + "\t");
			for (int x = 0; x < distance.length; x++) {
				
				if(distance[y][x] < 10) {
					System.out.print("| " + distance[y][x] + "  |\t");
				}else {;
					System.out.print("| " + distance[y][x] + " |\t");
				}

			}
			System.out.println();
		}
	}
	
	//puts out trip to console
	public static void outputTrip(int[] trip) {
		
		System.out.println("Trip: ");
		for (int i : trip) {
			System.out.print("("+ i + ") ");
		}
	}
	//Aufgabe 2 kalkulation des Trips
	public static int calculateRoundTrip(int[] trip, int[][] distance) {
		
		int totalDistance = 0;
		
		for (int i = 0; i < trip.length; i++) {
			
			int destA = trip[i];
			//wenn der index noch nicht die letzte destination hat
			if(i != (trip.length-1)) {
				//next destination
				int destB = trip[i+1];
				//addiert die dtsianz an den koordinaten destA und destB
				totalDistance += distance[destA][destB];
//				System.out.print(destA + " -> " + destB +" = "  + distance[destA][destB] +" | ");
			}else {
				
				int startCity = trip[0];
				totalDistance += distance[destA][startCity];
//				System.out.print(destA + " -> " + destB +" = "  + distance[destA][destB]);

			}
			
		}
		
		return totalDistance;
		
	}
	
	//Aufgabe 3 is optimizing a trip and returns the new trip
	public static int[] optimizeTrip(int currentDistance, int[] trip, int[][] distance ) {

		boolean notBetterTrip = true;
		
		while(notBetterTrip) {
			int randomCity1 = (int) (Math.random()*(trip.length-1));
			int randomCity2 = (int) (Math.random()*(trip.length-1));
			
			//Zwischenspeicher der Werte innerhalb des Trips
			int temp1 =	trip[randomCity1];
			int temp2 = trip[randomCity2];
			//Austausch der Werte über die Tempwerte
			trip[randomCity1] = temp2;
			trip[randomCity2] = temp1;
			
			int optimizedDistance = calculateRoundTrip(trip, distance);
			System.out.println("Versuch");
			
			if(optimizedDistance < currentDistance) {
				notBetterTrip = false;
			}
			
		}
		//returns optTrip
		return trip;
		
	}

}

