package com.dwarandae.estocasticos;

public enum Result {
	
	PLAYING("Playing"), WIN("Win"), LOSE("Lose");
	
	private final String name;
	
	private Result(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
