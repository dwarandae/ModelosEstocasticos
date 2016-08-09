package com.dwarandae.estocasticos;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

	private final static int THREADS = 250_000_000;
	private final static long EXPERIMENTS_BY_THREAD = 4;
	private final static long NUMBER_OF_EXPERIMENTS = THREADS * EXPERIMENTS_BY_THREAD;

	public static void main(String[] args) throws InterruptedException {
		
		long start = System.nanoTime();
		long totalWins = 0;
		long totalLose = 0;
		long[][] results = new long[THREADS][2];
		
		List<Craps> threads = new ArrayList<>(THREADS);
		for(int i = 0; i < THREADS; i++) 
			threads.add(new Craps(EXPERIMENTS_BY_THREAD));
		for(Craps craps : threads)
			craps.start();
		for(Craps craps : threads)
			craps.join();
		for(int i = 0; i < THREADS; i++) {
			results[i][0] = threads.get(i).getNumberOfLose();
			results[i][1] = threads.get(i).getNumberOfWins();
		}	
		for(int i = 0; i < THREADS; i++) {
			totalLose += results[i][0];
			totalWins += results[i][1];
		}
		assert(totalWins+totalLose == NUMBER_OF_EXPERIMENTS);
		
		double probability = (double)totalWins/(double)NUMBER_OF_EXPERIMENTS;
		
		System.out.println("Lose: "+totalLose);
		System.out.println("Wins: "+totalWins);
		System.out.println("Probability: "+probability);
		
		long end = System.nanoTime();
		System.out.println("Time: " + (end-start)/(double)1000000000);
	}
}
