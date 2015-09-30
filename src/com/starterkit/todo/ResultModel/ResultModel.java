package com.starterkit.todo.ResultModel;

import java.util.Date;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.WritableValue;

import com.starterkit.todo.DataModel.Priority;
import com.starterkit.todo.DataModel.Status;
import com.starterkit.todo.DataModel.ToDoObject;
import com.starterkit.todo.Repository.ToDoRepository;

public class ResultModel {

	private static WritableList active;
	private static WritableList archiveList;
	private static ToDoRepository repo;
	private static WritableValue selectedToDoItem;
	private static final ResultModel result = new ResultModel();

	private ResultModel() {
		repo = ToDoRepository.getInstance();
		active = new WritableList();
		archiveList = new WritableList();
		selectedToDoItem = new WritableValue();
		getActiveTask();
		getArchive();
	};

	public static WritableValue getSelectedToDoItem() {
		return selectedToDoItem;
	}

	public static void setSelectedToDoItem(ToDoObject todo) {
		ResultModel.selectedToDoItem.setValue(todo);
	}

	public static WritableList getArchiveList() {
		return archiveList;
	}

	public static ResultModel getResult() {
		return result;
	}

	public void getAllToDos() {
		active.clear();
		active.addAll(repo.getAllToDos());
	}

	public static void getArchive() {
		archiveList.clear();
		archiveList.addAll(repo.getArchive());

	}

	public static void getActiveTask() {
		active.clear();
		active.addAll(repo.getActiveTask());

	}

	public void getTaskByPriority(Priority p, WritableList list) {

		list.clear();
		list.addAll(repo.getTaskByPriority(p));

	}

	public void getTaskByStatus(Status s, WritableList list) {

		list.clear();
		list.addAll(repo.getTaskByStatus(s));

	}

	public static WritableList getActive() {
		return active;
	}

	public static void setActive(WritableList active) {
		ResultModel.active = active;
	}

	public static void setArchive(WritableList archive) {
		ResultModel.archiveList = archive;
	}

	public static void getActiveTaskByText(String s) {

		active.clear();
		active.addAll(repo.getActiveByTaskText(s));

	}

	public void getTaskEndBeforeDate(Date d, WritableList list) {

		list.clear();
		list.addAll(repo.getTaskEndBeforeDate(d));

	}

	public static ResultModel getInstance() {
		return result;
	}

	public static void getArchiveTaskByText(String text) {
		archiveList.clear();
		archiveList.addAll(repo.getArchiveByTaskText(text));

	}

	public static void remove(ToDoObject x) {
		repo.remove(x);
		getArchive();
		getActiveTask();
	}
	public static void add(ToDoObject x) {
		repo.save(x);
		getArchive();
		getActiveTask();
	}

	public static void makeDone(ToDoObject selectedTODO) {
		selectedTODO.setEndDate(new Date());
		selectedTODO.setStatus(Status.Done);
		getArchive();
		getActiveTask();
		
	}
	public static void changeStatus(ToDoObject x, Status s) {
		x.setStatus(s);
		getArchive();
		getActiveTask();
	}

	public static void changePriority(ToDoObject x, Priority s) {
		x.setPriority(s);
		getArchive();
		getActiveTask();
	}

	public static void MoveToArchive(ToDoObject selectedTODO) {
			selectedTODO.setIsMovedToArchive(true);
			getArchive();
			getActiveTask();	
	}
}
