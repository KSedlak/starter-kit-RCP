package com.starterkit.todo.DataBase;

import java.util.ArrayList;
import java.util.List;

import com.starterkit.todo.DataModel.Priority;
import com.starterkit.todo.DataModel.Status;
import com.starterkit.todo.DataModel.ToDoObject;

public class DataBaseImitation {//singleton instance with toDo´s


	    private static final DataBaseImitation database= new DataBaseImitation(); 
	    
	    private int nextId=1;
	    private List<ToDoObject> listOfTask;
	    


	      private DataBaseImitation() { 
	    	  listOfTask=new ArrayList<ToDoObject>();
	    	  
	    	  //sample objects
	    	  ToDoObject t1=new ToDoObject(Priority.High, "Do rcp project", Status.Ongoing);
	    	  ToDoObject t2=new ToDoObject(Priority.Normal, "Make view class", Status.Done);
	    	  ToDoObject t3=new ToDoObject(Priority.Low, "Task 1", Status.Ongoing);
	    	  ToDoObject t4=new ToDoObject(Priority.High, "Task 2", Status.Canceled);  
	    	  ToDoObject t5=new ToDoObject(Priority.Highest, "Task 3", Status.Done);
	 
	    	  //set id and move to archive
	    	  t1.setID(1);
	    	  t2.setID(2);
	    	  t3.setID(3);
	    	  t4.setID(4);
	    	  t5.setID(5);
	    	  
	    	  t2.setIsMovedToArchive(true);
	    	  
	    	  listOfTask.add(t1);
	     	  listOfTask.add(t2);
	     	  listOfTask.add(t3);
	     	  listOfTask.add(t4);
	     	  listOfTask.add(t5);
	     	 nextId=6;
	      
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
		
	      
	}
