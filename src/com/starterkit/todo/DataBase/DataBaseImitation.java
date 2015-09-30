package com.starterkit.todo.DataBase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.starterkit.todo.DataModel.Priority;
import com.starterkit.todo.DataModel.Status;
import com.starterkit.todo.DataModel.ToDoObject;

public class DataBaseImitation {// singleton instance with toDo´s

	private static final DataBaseImitation database = new DataBaseImitation();

	private int nextId = 1;
	private List<ToDoObject> listOfTask;

	private DataBaseImitation() {
		listOfTask = new ArrayList<ToDoObject>();

		ToDoObject t1 = new ToDoObject(Priority.High, "Do rcp project",
				Status.Ongoing);
		ToDoObject t2 = new ToDoObject(Priority.Normal, "Make view class",
				Status.Done);
		ToDoObject t3 = new ToDoObject(Priority.Low, "Task 1", Status.Ongoing);
		ToDoObject t4 = new ToDoObject(Priority.High, "Task 2", Status.Canceled);
		ToDoObject t5 = new ToDoObject(Priority.Highest, "Task 3", Status.Done);

		t2.setIsMovedToArchive(true);

		Date d = new Date();
		t4.setCreationDate(setDateToDate(d, 2011));
		Date dateE=new Date();
		t5.setEndDate(setDateToDate(dateE, 2016));

		add(t1);
		add(t2);
		add(t3);
		add(t4);
		add(t5);

	}

	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}

	public static DataBaseImitation getInstance() {
		return database;
	}

	public List<ToDoObject> getListOfTask() {
		return listOfTask;
	}

	public Date setDateToDate(Date d, int year) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.YEAR, year);
		d = c.getTime();
		return d;
	}
	public void add(ToDoObject t){
		t.setID(getNextId());
		listOfTask.add(t);
		nextId=nextId+1;
	}
}
