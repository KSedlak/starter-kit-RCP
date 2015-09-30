package com.starterkit.todo.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.starterkit.todo.DataModel.Priority;
import com.starterkit.todo.DataModel.Status;
import com.starterkit.todo.DataModel.ToDoObject;
import com.starterkit.todo.Repository.ToDoRepository;
import com.starterkit.todo.ResultModel.ResultModel;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddTask extends ViewPart{

	public static final String ID = "com.starterkit.todo.perspectives.AddTask"; //$NON-NLS-1$
	
	public AddTask() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	
	private Button addButton;
	private Combo  priorityCombo;
	private Combo  statusCombo;
	private Text text;


	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		{
			Label lblNewLabel = new Label(parent, SWT.NONE);
			lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
			lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel.setText("ToDo: ");
		}
		{
			text = new Text(parent, SWT.BORDER);
			GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			gd_text.widthHint = 70;
			text.setLayoutData(gd_text);
		}
		{
			Label lblNewLabel_1 = new Label(parent, SWT.NONE);
			lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
			lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel_1.setText("Priority: ");
		}
		{
			priorityCombo = new Combo(parent, SWT.NONE);
			priorityCombo.setItems(Priority.getValues());
			priorityCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			priorityCombo.select(2);
		}
		{
			Label lblNewLabel_2 = new Label(parent, SWT.NONE);
			lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
			lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel_2.setText("Status: ");
		}
		{
			statusCombo = new Combo(parent, SWT.NONE);
			statusCombo.setItems(Status.getValues());
			statusCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			statusCombo.select(0);
		}
		new Label(parent, SWT.NONE);
		{
			addButton= new Button(parent, SWT.NONE);
			addButton.setText("Add");
			GridData gd_addButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
			gd_addButton.widthHint = 86;
			addButton.setLayoutData(gd_addButton);
			addButton.addSelectionListener(new SelectionAdapter() {
		
		
				@Override
				public void widgetSelected(SelectionEvent e) {
				  	ToDoObject toAdd=new ToDoObject(
		        			Priority.valueOf(priorityCombo.getText()),
		        			text.getText(),
		        			Status.valueOf(statusCombo.getText())
		        			);
		        	//to repo
				  	ResultModel.add(toAdd);
				}
			});
			addButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
			GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_btnNewButton.widthHint = 322;

			 
		}
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
