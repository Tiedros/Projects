package com.tiedros.project.enums;

public enum Gender {

	 MALE(0),
	 FEMALE(1),
	 TRANSGENDER(2);
	

	private Gender(int value) {
		this.value=value;
	}
	private int value;
	public int getName() {
		return value;
	}
	
}
