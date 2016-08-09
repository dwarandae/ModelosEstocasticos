package com.dwarandae.estocasticos;

import java.util.Random;

public class Craps extends Thread {
	
	private final int SEVEN = 7;
	private final int ELEVEN = 11;
	private final int TWO = 2;
	private final int THREE = 3;
	private final int TWELVE = 12;
	private final Random random = new Random();
	private final long NUMBER_OF_SIMULATIONS;
	private long numberOfWins;
	private long numberOfLose;
	
	public Craps(long number) {
		NUMBER_OF_SIMULATIONS = number;
	}
	
	public long getNumberOfWins() {
		return this.numberOfWins;
	}
	
	public long getNumberOfLose() {
		return this.numberOfLose;
	}
	
	public void run() {
		simulate();
	}
	
	public void simulate() {

		for(long i = 0; i < NUMBER_OF_SIMULATIONS; i++ ) {
			
//			if(i%100000000==0) {
//				System.out.println("Experiment " + i);
//			}
			
			Result resultGame = Result.PLAYING;
			
			int dice1 = randomDice();
			int dice2 = randomDice();
			//System.out.println("dice1 "+ dice1 + ": dice2 " + dice2);
			
			int sumThrower = dice1 + dice2;
			
			if(sumThrower == SEVEN || sumThrower == ELEVEN) {
				resultGame = Result.WIN;
			}
			else if(sumThrower == TWO || sumThrower == THREE || sumThrower == TWELVE) {
				resultGame = Result.LOSE;
			}
			else {
				int point = sumThrower;
				do{
					dice1 = randomDice();
					dice2 = randomDice();
					sumThrower = dice1 + dice2;
					//System.out.println("dice1 "+ dice1 + ": dice2 " + dice2);
					if(sumThrower == point) 
						resultGame = Result.WIN;
					else if(sumThrower == SEVEN) 
						resultGame = Result.LOSE;
				}
				while(resultGame == Result.PLAYING);
			}

			if(resultGame == Result.WIN)
				numberOfWins++;
			else 
				numberOfLose++;
		}
		
	}
	
	private int randomDice() {
		return randomInt(1,6);
	}
	
	private int randomInt(int from, int to) {
		return from + random.nextInt(to);
	}
}
