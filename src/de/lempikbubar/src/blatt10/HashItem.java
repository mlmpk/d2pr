package de.lempikbubar.src.blatt10;

public class HashItem {

	private String key;
	private int info;

	public HashItem(String key ) {
		this.key = key;
		this.info = 1;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

}
