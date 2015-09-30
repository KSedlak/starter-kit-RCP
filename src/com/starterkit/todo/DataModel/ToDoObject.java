package com.starterkit.todo.DataModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

public class ToDoObject {

	private int ID;
	private Date creationDate;
	private Priority priority;
	private String task;
	private Status status;
	private Date endDate;
	private Boolean isMovedToArchive;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public ToDoObject(String task) {
		super();
		this.priority = Priority.Normal;
		this.task = task;
		this.status = Status.Undone;
		this.creationDate = new Date();
		this.isMovedToArchive = false;
	}

	public ToDoObject(String task, Priority pr) {
		super();
		this.priority = pr;
		this.task = task;
		this.status = Status.Undone;
		this.creationDate = new Date();
		this.isMovedToArchive = false;
	}

	public ToDoObject(Priority priority, String task, Status status) {
		super();
		this.priority = priority;
		this.task = task;
		this.status = status;
		this.creationDate = new Date();
		this.isMovedToArchive = false;
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		propertyChangeSupport.firePropertyChange("creationDate",
				this.creationDate, this.creationDate = creationDate);
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		propertyChangeSupport.firePropertyChange("priority", this.priority,
				this.priority = priority);
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		propertyChangeSupport.firePropertyChange("task", this.task,
				this.task = task);

	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		propertyChangeSupport.firePropertyChange("status", this.status,
				this.status = status);
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		propertyChangeSupport.firePropertyChange("endDate", this.endDate,
				this.endDate = endDate);
	}

	public Boolean getIsMovedToArchive() {
		return isMovedToArchive;
	}

	public void setIsMovedToArchive(Boolean isMovedToArchive) {
		propertyChangeSupport
				.firePropertyChange("isMovedToArchive", this.isMovedToArchive,
						this.isMovedToArchive = isMovedToArchive);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		propertyChangeSupport.firePropertyChange("ID", this.ID, this.ID = iD);
	}
}
