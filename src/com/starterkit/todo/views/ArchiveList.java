package com.starterkit.todo.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.starterkit.todo.ApplicationWorkbenchWindowAdvisor;
import com.starterkit.todo.DataModel.ToDoObject;
import com.starterkit.todo.Repository.ToDoRepository;

import org.eclipse.swt.widgets.Button;




public class ArchiveList extends ViewPart {
	public ArchiveList() {
	}

  public static final String ID = "com.starterkit.todo.views.List";

  private TableViewer viewer;

private java.util.List<ToDoObject> tableData=ToDoRepository.getInstance().getArchive();



public void createPartControl(Composite parent) {

    GridLayout layout = new GridLayout(3, false);
    parent.setLayout(layout);
    Label searchLabel = new Label(parent, SWT.NONE);
    searchLabel.setText("Search: ");
    final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
    searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
        | GridData.HORIZONTAL_ALIGN_FILL));
    createViewer(parent);

    
  }
  private void createViewer(Composite parent) {
    {
    	Button btnNewButton = new Button(parent, SWT.NONE);
    	btnNewButton.setText("szukaj");
    }
    viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
        | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
    createColumns(parent, viewer);
    final Table table = viewer.getTable();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    viewer.setContentProvider(new ArrayContentProvider());
    // get the content for the viewer, setInput will call getElements in the
    // contentProvider
    viewer.setInput(tableData);
    // make the selection available to other views
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

  public TableViewer getViewer() {
    return viewer;
  }

  // create the columns for the table
  private void createColumns(final Composite parent, final TableViewer viewer) {
	  int column=3;
	  int columnSize=100;
    String[] titles = { "Task", "Status", "Priority"};
    int[] bounds = { columnSize,columnSize, columnSize};

    // first column is for the first name
    TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
    col.setLabelProvider(new ColumnLabelProvider() {
      @Override
      public String getText(Object element) {
        ToDoObject t = (ToDoObject) element;
        return t.getTask();
      }
    });

    // second column is for the last name
    col = createTableViewerColumn(titles[1], bounds[1], 1);
    col.setLabelProvider(new ColumnLabelProvider() {
      @Override
      public String getText(Object element) {
          ToDoObject t = (ToDoObject) element;
          return t.getStatus().toString();
        }
    });

    // now the gender
    col = createTableViewerColumn(titles[2], bounds[2], 2);
    col.setLabelProvider(new ColumnLabelProvider() {
      @Override
      public String getText(Object element) {
          ToDoObject t = (ToDoObject) element;
          return t.getPriority().toString();
        }
    });





  }

  private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
    final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
        SWT.NONE);
    final TableColumn column = viewerColumn.getColumn();
    column.setText(title);
    column.setWidth(bound);
    column.setResizable(true);
    column.setMoveable(true);
    return viewerColumn;
  }

  public void setFocus() {
    viewer.getControl().setFocus();
  }
} 