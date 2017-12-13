package ui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import rate.wizards.DevRateWizardDialog;
import ui.mapping.MappingDialog;
import ui.modelBuilder.LoadPlotDataDialog;
import ui.modelBuilder.MaizeDevRateWizardDialog;



public class MaizeDevRateMainFrame {

	protected Shell shellMaizeDevRate;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MaizeDevRateMainFrame window = new MaizeDevRateMainFrame();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellMaizeDevRate.open();
		shellMaizeDevRate.layout();
		while (!shellMaizeDevRate.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shellMaizeDevRate = new Shell();
		shellMaizeDevRate.setSize(800, 600);
		shellMaizeDevRate.setText("Maize Development Rate Software");
		shellMaizeDevRate.setLayout(new FormLayout());
		
		Menu menu = new Menu(shellMaizeDevRate, SWT.BAR);
		shellMaizeDevRate.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		
		
		MenuItem mntmNew = new MenuItem(menu_1, SWT.CASCADE);
		mntmNew.setText("New");
		
		Menu menu_2 = new Menu(mntmNew);
		mntmNew.setMenu(menu_2);
		
		MenuItem mntmProject = new MenuItem(menu_2, SWT.PUSH);
		mntmProject.setText("Project");		
		mntmProject.addSelectionListener(new OpenProject());
		
		new MenuItem(menu, SWT.SEPARATOR);
		
		MenuItem mntmEdit = new MenuItem(menu, SWT.CASCADE);
		mntmEdit.setText("Edit");
		
		Menu menu_3 = new Menu(mntmEdit);
		mntmEdit.setMenu(menu_3);
		
		new MenuItem(menu, SWT.SEPARATOR);
		
		MenuItem mntmWindows = new MenuItem(menu, SWT.CASCADE);
		mntmWindows.setText("Windows");
		
		Menu menu_4 = new Menu(mntmWindows);
		mntmWindows.setMenu(menu_4);
		
		new MenuItem(menu, SWT.SEPARATOR);
		
		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help?");
		
		Menu menu_5 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_5);
		
		ToolBar toolBar = new ToolBar(shellMaizeDevRate, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.top = new FormAttachment(0);
		fd_toolBar.left = new FormAttachment(0);
		fd_toolBar.right = new FormAttachment(0, 784);
		fd_toolBar.bottom = new FormAttachment(0, 31);
		toolBar.setLayoutData(fd_toolBar);
		
		ToolItem tltmPlotdata = new ToolItem(toolBar, SWT.NONE);
		tltmPlotdata.setText("Plotting");
		tltmPlotdata.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println(CreatedMaizeProject.getStrPathProject());
				if(CreatedMaizeProject.getStrPathProject().equalsIgnoreCase("/"))
				{
	    			MessageDialog.openError(shellMaizeDevRate, "Error", "You must first created a new project before start simulation");
	    		//	shellPlotData.dispose();
	    		}
				else
				{
					LoadPlotDataDialog plotWindow = new LoadPlotDataDialog(shellMaizeDevRate, SWT.SHELL_TRIM);
					plotWindow.open();
				}
				
			}
		});
		tltmPlotdata.setToolTipText("Clic here to display Data");
		tltmPlotdata.setImage(SWTResourceManager.getImage(MaizeDevRateMainFrame.class, "/Img/PlotDataIcon.png"));
		
		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);
		
		ToolItem tltmMdlbuildevrate = new ToolItem(toolBar, SWT.NONE);
		tltmMdlbuildevrate.setImage(SWTResourceManager.getImage(MaizeDevRateMainFrame.class, "/Img/MortalityIcon.png"));
		tltmMdlbuildevrate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println(CreatedMaizeProject.getStrPathProject());
				if(CreatedMaizeProject.getStrPathProject().equalsIgnoreCase("/"))
				{
	    			MessageDialog.openError(shellMaizeDevRate, "Notification", "You must first created or select a new project before proceed this operation");
	    		//	shellPlotData.dispose();
	    		}
				else
				{
					WizardDialog dialog = new WizardDialog(shellMaizeDevRate, new DevRateWizardDialog());//DevRateWizardDialog
					dialog.create();
					dialog.setBlockOnOpen(true);
					dialog.open();
					
				}
			}
		});
		tltmMdlbuildevrate.setText("Modeling");
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);
		
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.SEPARATOR);
		
		ToolItem tltmMapping = new ToolItem(toolBar, SWT.NONE);
		tltmMapping.setText("Mapping");
		tltmMapping.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println(CreatedMaizeProject.getStrPathProject());
				if(CreatedMaizeProject.getStrPathProject().equalsIgnoreCase("/"))
				{
	    			MessageDialog.openError(shellMaizeDevRate, "Notification", "You must first created or select a new project before proceed this operation");
	    		//	shellPlotData.dispose();
	    		}
				else
				{
					MappingDialog mapWing = new MappingDialog(shellMaizeDevRate,SWT.SHELL_TRIM);
					//fenetreFille.setSize(200, 200);
					mapWing.open();
				}
				
			}
		});
		tltmMapping.setImage(SWTResourceManager.getImage(MaizeDevRateMainFrame.class, "/Img/MappingIcon.png"));
		
		
		
	

	}
	
	class OpenProject extends SelectionAdapter{

		
			// TODO Auto-generated method stub
		@Override
		public void widgetSelected(SelectionEvent event) {
			
			CreatedMaizeProject creteProj = new CreatedMaizeProject(shellMaizeDevRate, SWT.SHELL_TRIM);
			creteProj.open();
		}
		
		
		}// end class
}
