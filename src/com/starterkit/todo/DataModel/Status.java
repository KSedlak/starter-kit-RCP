package com.starterkit.todo.DataModel;

public enum Status {
	Undone, Ongoing, Done, Canceled;

	public static String[] getValues() {
		String[] items = new String[Status.values().length];
		for (int i = 0; i < Status.values().length; i++) {
			items[i] = Status.values()[i].toString();
		}
		return items;

	}
}
