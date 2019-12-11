package com.tiedros.project.enums;

public enum KidFriendlyStatus {
	
	
	
	 APROVED("approved"),
	 REJECTED("rejected"),
	 UNKNOWN("unknown");
	
	
	private String name;
private KidFriendlyStatus(String name) {
	this.name=name;	
	}

public String getName() {
	return name();
}

}
