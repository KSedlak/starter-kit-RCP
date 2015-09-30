package com.starterkit.todo.DataModel;

public enum Priority {
	Lowest, Low, Normal, High, Highest;

	public static String[] getValues() {
		String[] items = new String[Priority.values().length];
		for (int i = 0; i < Priority.values().length; i++) {
			items[i] = Priority.values()[i].toString();
		}
		return items;

	}
}
