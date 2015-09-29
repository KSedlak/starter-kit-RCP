package com.starterkit.todo.views;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.internal.databinding.swt.DateTimeSelectionProperty;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.starterkit.todo.Converters.MyConverter;
import com.starterkit.todo.DataModel.Priority;
import com.starterkit.todo.DataModel.Status;
import com.starterkit.todo.ResultModel.ResultModel;

public class DetailsEdit extends ViewPart {

	public static final String ID = "com.starterkit.todo.views.DetailsEdit"; //$NON-NLS-1$
	private Text text;
	DateTime creat_dateTime;

	public DetailsEdit() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	
	private WritableValue selected=ResultModel.getSelectedToDoItem();
	private Combo priorityCombo;
	private Button btnCheckButton;
	private DateTime EndDateTime;
	private Combo statusCombo;
	private Text text_ID;
	@Override
	public void createPartControl(Composite parent) {

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		{
			Label lblNewLabel = new Label(container, SWT.NONE);
			lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel.setAlignment(SWT.RIGHT);
			lblNewLabel.setText("ID: ");
		}
		{
			text_ID = new Text(container, SWT.BORDER);
			text_ID.setEnabled(false);
			GridData gd_text_ID = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			gd_text_ID.widthHint = 39;
			text_ID.setLayoutData(gd_text_ID);
		}
		{
			Label lblNewLabel_2 = new Label(container, SWT.NONE);
			lblNewLabel_2.setAlignment(SWT.RIGHT);
			lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel_2.setText("CreateDate: ");
		}
		{
			creat_dateTime = new DateTime(container, SWT.BORDER);
			GridData gd_dateTime = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
			gd_dateTime.widthHint = 133;
			creat_dateTime.setLayoutData(gd_dateTime);
		}
		{
			Label lblNewLabel_3 = new Label(container, SWT.NONE);
			lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel_3.setAlignment(SWT.RIGHT);
			lblNewLabel_3.setText("Priority");
		}
		{
	
			priorityCombo =new Combo(container, SWT.NONE);
			priorityCombo.setItems(Priority.getValues());
			GridData gd_priorityCombo = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
			gd_priorityCombo.widthHint = 191;
			gd_priorityCombo.heightHint = 5;
			priorityCombo.setLayoutData(gd_priorityCombo);

		}
		{
			Label lblNewLabel_4 = new Label(container, SWT.NONE);
			lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel_4.setAlignment(SWT.RIGHT);
			lblNewLabel_4.setText("Task");
		}
		{
			text = new Text(container, SWT.BORDER);
			text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		}
		{
			Label lblNewLabel_5 = new Label(container, SWT.NONE);
			lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel_5.setAlignment(SWT.RIGHT);
			lblNewLabel_5.setText("Status");
		}
		{
			statusCombo = new Combo(container, SWT.NONE);
			statusCombo.setItems(Status.getValues());
			statusCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		}
		{
			Label lblEndDate = new Label(container, SWT.NONE);
			lblEndDate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblEndDate.setAlignment(SWT.RIGHT);
			lblEndDate.setText("End date: ");
		}
		{
			EndDateTime = new DateTime(container, SWT.BORDER);
			GridData gd_dateTime = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_dateTime.widthHint = 135;
			EndDateTime.setLayoutData(gd_dateTime);
		}
		new Label(container, SWT.NONE);
		{
			btnCheckButton = new Button(container, SWT.CHECK);
			

			btnCheckButton.setText("is moved to Archive");
		
		}

		createActions();
		initializeToolBar();
		initializeMenu();
		
		DataBindingContext ctx = new DataBindingContext();

		ctx.bindValue(
				WidgetProperties.text(SWT.Modify).observe(text), 
				BeanProperties.value("task")
		        .observeDetail(selected));


		
		ctx.bindValue(
				WidgetProperties.selection().observe(btnCheckButton), 
				BeanProperties.value("isMovedToArchive")
		 .observeDetail(selected));
		
		DateTimeSelectionProperty dateTimeSelectionProperty = new DateTimeSelectionProperty();
		 ISWTObservableValue creaDateTimeObservableValue = dateTimeSelectionProperty.observe(creat_dateTime);
		 ISWTObservableValue endDateTimeObservableValue = dateTimeSelectionProperty.observe(EndDateTime);
		 
		ctx.bindValue(		
			creaDateTimeObservableValue,
				BeanProperties.value("creationDate")
				 .observeDetail(selected));
		
		ctx.bindValue(		
				creaDateTimeObservableValue,
					BeanProperties.value("endDate")
				 .observeDetail(selected));





		
	    UpdateValueStrategy pioToInt = new UpdateValueStrategy();
	    pioToInt.setConverter(MyConverter.createPriorityToIntConverter());

	    UpdateValueStrategy intToPrio = new UpdateValueStrategy();
	    intToPrio.setConverter(MyConverter.createIntToPriority());
	  
	    ctx.bindValue(
	    		WidgetProperties.singleSelectionIndex().observe(priorityCombo),
	    		BeanProperties.value("priority").observeDetail(selected),
	    		intToPrio,pioToInt);

	
	    
	    UpdateValueStrategy statToInt = new UpdateValueStrategy();
	    statToInt.setConverter(MyConverter.createStatusToIntConverter());

	    UpdateValueStrategy intToStat = new UpdateValueStrategy();
	    intToStat.setConverter(MyConverter.createIntToStatus());
	  
	    ctx.bindValue(
	    		WidgetProperties.singleSelectionIndex().observe(statusCombo),
	    		BeanProperties.value("status").observeDetail(selected),
	    		intToStat,statToInt);


	    UpdateValueStrategy stringToInt = new UpdateValueStrategy();
	    stringToInt.setConverter(MyConverter.createStringToInt());

	    UpdateValueStrategy intToString = new UpdateValueStrategy();
	    intToString.setConverter(MyConverter.createIntToString());
	  
	    ctx.bindValue(
	    		WidgetProperties.text().observe(text_ID),
	    		BeanProperties.value("ID").observeDetail(selected),
	    stringToInt,intToString);	
			
		
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				

					ResultModel.getActiveTask();//refresh list
					ResultModel.getArchive();
					
				
			}
		});
	}
	



	
	

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}


}
