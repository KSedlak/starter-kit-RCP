package com.starterkit.todo.views;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
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

import com.starterkit.todo.DataModel.Priority;
import com.starterkit.todo.ResultModel.ResultModel;

public class DetailsEdit extends ViewPart {

	public static final String ID = "com.starterkit.todo.views.DetailsEdit"; //$NON-NLS-1$
	private Text text;
	private Label lblIdValue;
	DateTime creat_dateTime;

	public DetailsEdit() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	
private	ResultModel resultManager = ResultModel.getInstance();
	private Combo priorityCombo;
	private Button btnCheckButton;
	private DateTime EndDateTime;
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
		
			lblIdValue = new Label(container, SWT.NONE);
			lblIdValue.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
			lblIdValue.setText("ids");
		}
		{
			Label lblNewLabel_2 = new Label(container, SWT.NONE);
			lblNewLabel_2.setAlignment(SWT.RIGHT);
			lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel_2.setText("CreateDate: ");
		}
		{
			creat_dateTime = new DateTime(container, SWT.BORDER);
			GridData gd_dateTime = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_dateTime.widthHint = 197;
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
			priorityCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			priorityCombo.select(Priority.Normal.ordinal());
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
			Combo combo = new Combo(container, SWT.NONE);
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
			gd_dateTime.widthHint = 199;
			EndDateTime.setLayoutData(gd_dateTime);
		}
		new Label(container, SWT.NONE);
		{
			btnCheckButton = new Button(container, SWT.CHECK);
			btnCheckButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			btnCheckButton.setText("is moved to Archive");
		}

		createActions();
		initializeToolBar();
		initializeMenu();
		
		DataBindingContext ctx = new DataBindingContext();

		ctx.bindValue(
				WidgetProperties.text(SWT.Modify).observe(text), 
				BeanProperties.value("task")
				.observeDetail(resultManager.getSelectedToDoItem()));

		
		
		ctx.bindValue(
				WidgetProperties.selection().observe(btnCheckButton), 
				BeanProperties.value("isMovedToArchive")
				.observeDetail(resultManager.getSelectedToDoItem()));

		DateTimeSelectionProperty dateTimeSelectionProperty = new DateTimeSelectionProperty();
		 ISWTObservableValue creaDateTimeObservableValue = dateTimeSelectionProperty.observe(creat_dateTime);
		 ISWTObservableValue endDateTimeObservableValue = dateTimeSelectionProperty.observe(EndDateTime);
		 
		ctx.bindValue(		
			creaDateTimeObservableValue,
				BeanProperties.value("creationDate")
				.observeDetail(resultManager.getSelectedToDoItem()));
		
		ctx.bindValue(		
				creaDateTimeObservableValue,
					BeanProperties.value("endDate")
					.observeDetail(resultManager.getSelectedToDoItem()));


			
				 
		
		
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
