package com.starterkit.todo.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import swing2swt.layout.BorderLayout;

public class MainView extends ViewPart {

	public static final String ID = "com.starterkit.todo.views.MainView"; //$NON-NLS-1$
	private Table table;

	public MainView() {
		setPartName("ToDo");
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new BorderLayout(0, 0));
		{
			Label lblNewLabel = new Label(parent, SWT.NONE);
			lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 36, SWT.BOLD));
			lblNewLabel.setAlignment(SWT.CENTER);
			lblNewLabel.setLayoutData(BorderLayout.NORTH);
			lblNewLabel.setText("New Label");
		}
		{
			table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
			table.setLayoutData(BorderLayout.CENTER);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
		}

		createActions();
		initializeToolBar();
		initializeMenu();
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
