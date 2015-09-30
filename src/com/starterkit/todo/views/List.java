package com.starterkit.todo.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.core.databinding.beans.BeanProperties;
import com.starterkit.todo.DataModel.Priority;
import com.starterkit.todo.DataModel.Status;
import com.starterkit.todo.DataModel.ToDoObject;
import com.starterkit.todo.ResultModel.ResultModel;
import com.starterkit.todo.Tracker.TableColumnTracker;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class List extends ViewPart {
	public List() {
	}

	public static final String ID = "com.starterkit.todo.views.List";

	private TableViewer viewer;
	private ToDoObject selectedTODO;
	private WritableList input;
	private Text searchText;

	Table table;
	private TableColumnTracker fColumnTracker;

	public void createPartControl(Composite parent) {

		GridLayout layout = new GridLayout(3, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.HORIZONTAL_ALIGN_FILL));
		createViewer(parent);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

	}

	private void createViewer(Composite parent) {
		{
			Button btnNewButton = new Button(parent, SWT.NONE);
			btnNewButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					ResultModel.getActiveTaskByText(searchText.getText());

				}
			});
			btnNewButton.setText("Search");
		}
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		table = viewer.getTable();

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		input = ResultModel.getActive();

		addContextMenu();

		ViewerSupport.bind(
				viewer,
				input,
				BeanProperties.values(new String[] { "task", "status",
						"priority" }));

		addSelectionListener();
		getSite().setSelectionProvider(viewer);
		// set the sorter for the table

		// define layout for the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 3;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	private void addContextMenu() {
/////////////////////////////////////////////////////////ACTIONS
		Action deleteAction = new Action("Delete") {
			public void run() {

				ResultModel.remove(selectedTODO);
			}
		};
		Action archAction = new Action("to Archive") {
			public void run() {
				ResultModel.MoveToArchive(selectedTODO);
			}
		};

		Action doneAction = new Action("Done ToDo") {
			public void run() {

				ResultModel.makeDone(selectedTODO);
			}
		};
		Action cancelAction = new Action("Cancel ToDo") {
			public void run() {

				ResultModel.changeStatus(selectedTODO, Status.Canceled);
			}
		};
		
		Action undonelAction = new Action("Undone ToDo") {
			public void run() {

				ResultModel.changeStatus(selectedTODO, Status.Undone);
			}
		};
		
		Action ongoingAction = new Action("Ongoing ToDo") {
			public void run() {

				ResultModel.changeStatus(selectedTODO, Status.Ongoing);
			}
		};
		Action highestPAction = new Action("Highest Priority") {
			public void run() {

				ResultModel.changePriority(selectedTODO, Priority.Highest);
			}
		};
		Action highPAction = new Action("High Priority") {
			public void run() {

				ResultModel.changePriority(selectedTODO, Priority.High);
			}
		};
		Action lowPAction = new Action("Low Priority") {
			public void run() {

				ResultModel.changePriority(selectedTODO, Priority.Low);
			}
		};
		
		Action lowestPAction = new Action("Lowest Priority") {
			public void run() {

				ResultModel.changePriority(selectedTODO, Priority.Lowest);
			}
		};
		Action NormalPAction = new Action("Normal Priority") {
			public void run() {

				ResultModel.changePriority(selectedTODO, Priority.Normal);
			}
		};
		MenuManager menuMgr = new MenuManager();
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		menuMgr.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {

				if (viewer.getSelection().isEmpty()) {
					return;
				}

				if (viewer.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) viewer
							.getSelection();

					selectedTODO = (ToDoObject) selection.getFirstElement();

						manager.add(deleteAction);
						manager.add(archAction);

					if (fColumnTracker.getSelectedColumnIndex() == 1) {
						manager.add(new Separator("Status Change"));
						manager.add(doneAction);
						manager.add(cancelAction);
						manager.add(ongoingAction);
						manager.add(undonelAction);
					}
					if (fColumnTracker.getSelectedColumnIndex() == 2) {
						manager.add(new Separator("Priority Change"));
						manager.add(highestPAction);
						manager.add(highPAction);
						manager.add(NormalPAction);
						manager.add(lowPAction);
						manager.add(lowestPAction);
					}
				}
			}

		});
		menuMgr.setRemoveAllWhenShown(true);
		viewer.getControl().setMenu(menu);
	}

	public TableViewer getViewer() {
		return viewer;
	}

	private void createColumns(final Composite parent, final TableViewer viewer) {

		int columnSize = 222;
		String[] titles = { "ToDo", "Status", "Priority" };
		int[] bounds = { columnSize, columnSize, columnSize };
		// first column is for the first name
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ToDoObject t = (ToDoObject) element;
				return t.getTask();
			}
		});

		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ToDoObject t = (ToDoObject) element;
				return t.getStatus().toString();
			}
		});

		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ToDoObject t = (ToDoObject) element;
				return t.getPriority().toString();
			}
		});

	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setAlignment(SWT.CENTER);
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void addSelectionListener() {
		getViewer().addSelectionChangedListener(
				new ISelectionChangedListener() {
					public void selectionChanged(
							final SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) event
								.getSelection();
						ToDoObject toDo = (ToDoObject) selection
								.getFirstElement();
						ResultModel.setSelectedToDoItem(toDo);

					}
				});

		fColumnTracker = new TableColumnTracker(getViewer());
	}
}