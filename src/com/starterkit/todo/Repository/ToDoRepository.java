package com.starterkit.todo.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.starterkit.todo.DataBase.DataBaseImitation;
import com.starterkit.todo.DataModel.Priority;
import com.starterkit.todo.DataModel.Status;
import com.starterkit.todo.DataModel.ToDoObject;

public class ToDoRepository {
	
	 private static final DataBaseImitation database=DataBaseImitation.getInstance();
	 
	 
	 
	 public List<ToDoObject> getAllToDos(){
		return database.getListOfTask();
	 }
	
	 public ToDoObject getById(int id){
		 
			return database.getListOfTask().stream()
					.filter(task->task.getID()==id).findFirst().get();
			
					
		 }
	 public List<ToDoObject> getArchive(){
		 
			return database.getListOfTask().stream()
					.filter(task->task.getIsMovedToArchive().equals(true))
					.collect(Collectors.toList());
					
		 }
	
	 public List<ToDoObject> getActiveTask(){
		 
			return database.getListOfTask().stream()
					.filter(task->task.getIsMovedToArchive().equals(false))
					.collect(Collectors.toList());
					
		 }
	
	 public List<ToDoObject> getTaskByPriority(Priority p){
		 
			return database.getListOfTask().stream()
					.filter(task->task.getPriority().equals(p))
					.collect(Collectors.toList());
					
		 }
	 
	 public List<ToDoObject> getTaskByStatus(Status s){
		 
			return database.getListOfTask().stream()
					.filter(task->task.getStatus().equals(s))
					.collect(Collectors.toList());
					
		 }
	 public List<ToDoObject> getTaskEndBeforeDate(Date d){
		 
			return database.getListOfTask().stream()
					.filter(task->task.getEndDate().before(d))
					.collect(Collectors.toList());
					
		 }
	 
	 public ToDoObject save(ToDoObject t){
		 int id=database.getNextId();
		 t.setID(id);
		 database.getListOfTask().add(t);
		 database.setNextId(id+1);
		 return getById(id);
	 }
	 
	 public void remove(ToDoObject t){
		 database.getListOfTask().remove(t);
	 }
	 public ToDoObject update(ToDoObject t){
		 int id=t.getID();
		 ToDoObject edited=getById(id);
		 edited.setCreationDate(t.getCreationDate());
		 edited.setEndDate(t.getEndDate());
		 edited.setIsMovedToArchive(t.getIsMovedToArchive());
		 edited.setPriority(t.getPriority());
		 edited.setStatus(t.getStatus());
		 edited.setTask(t.getTask());
		 return edited;
	 }
	 
}
