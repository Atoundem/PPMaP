//package modelDesigner;
package ui.modelBuilder;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class SelectedModelWizardPage extends WizardPage {

	public static Text txtSelectedLifeStage;
	public static Table tableParameters;
	public static Label lblImageFunction, lblImageSelected, lblModelSel;
	public static Browser bwrTextSelected;
	private Text textStageSelected;
	
	/**
	 * Create the wizard.
	 */
	public SelectedModelWizardPage() {
		super("SelectedModelWizardPage");
		setPageComplete(false);
		setTitle("Model selected");
		setDescription("");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	@Override
	public void createControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FormLayout());
		
		Label lblSelectedAlgo = new Label(container, SWT.NONE);
		lblSelectedAlgo.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_lblSelectedAlgo = new FormData();
		fd_lblSelectedAlgo.top = new FormAttachment(0, 10);
		lblSelectedAlgo.setLayoutData(fd_lblSelectedAlgo);
		lblSelectedAlgo.setText("Algorithm selected:");
		
		txtSelectedLifeStage = new Text(container, SWT.NONE);
		fd_lblSelectedAlgo.right = new FormAttachment(100, -443);
		txtSelectedLifeStage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		txtSelectedLifeStage.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_txtSelectedProject1 = new FormData();
		fd_txtSelectedProject1.top = new FormAttachment(lblSelectedAlgo, 0, SWT.TOP);
		fd_txtSelectedProject1.left = new FormAttachment(lblSelectedAlgo, 6);
		fd_txtSelectedProject1.right = new FormAttachment(100, -217);
		txtSelectedLifeStage.setLayoutData(fd_txtSelectedProject1);
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
	    FormData fd_tabFolder = new FormData();
	    fd_tabFolder.top = new FormAttachment(txtSelectedLifeStage, 16);
	    fd_tabFolder.bottom = new FormAttachment(100);
	    fd_tabFolder.left = new FormAttachment(45);
	    fd_tabFolder.right = new FormAttachment(100 ,-1);
	    tabFolder.setLayoutData(fd_tabFolder);
	    
	    TabItem tbtmGraphic = new TabItem(tabFolder, SWT.NONE);
	    tbtmGraphic.setText("Graphic");
	    
	    Composite composite = new Composite(tabFolder, SWT.NONE);
	    tbtmGraphic.setControl(composite);
	    composite.setLayout(new FormLayout());
	    
	    lblImageSelected = new Label(composite, SWT.BORDER | SWT.CENTER);
	    lblImageSelected.setBackground(SWTResourceManager.getColor(255, 255, 255));
	    FormData fd_lblImageSelected = new FormData();
	    fd_lblImageSelected.top = new FormAttachment(0);
	    fd_lblImageSelected.bottom = new FormAttachment(100, -1);
	    fd_lblImageSelected.left = new FormAttachment(0);
	    fd_lblImageSelected.right = new FormAttachment(100, -1);
	    lblImageSelected.setLayoutData(fd_lblImageSelected);
	    
	    TabItem tbtmOutputText = new TabItem(tabFolder, SWT.NONE);
	    tbtmOutputText.setText("Output text");
	    
	    Composite composite_1 = new Composite(tabFolder, SWT.NONE);
	    tbtmOutputText.setControl(composite_1);
	    composite_1.setLayout(new FormLayout());
	    
	    bwrTextSelected = new Browser(composite_1, SWT.BORDER);
	    FormData fd_bwrTextSelected = new FormData();
	    fd_bwrTextSelected.top = new FormAttachment(0);
	    fd_bwrTextSelected.left = new FormAttachment(0);
	    fd_bwrTextSelected.right = new FormAttachment(100, -1);
	    fd_bwrTextSelected.bottom = new FormAttachment(100, -1);
	    bwrTextSelected.setLayoutData(fd_bwrTextSelected);
	    
	    lblImageFunction = new Label(container, SWT.BORDER | SWT.CENTER);
	    lblImageFunction.setBackground(SWTResourceManager.getColor(255, 255, 255));
	    FormData fd_lblImageFunction = new FormData();
	    fd_lblImageFunction.top = new FormAttachment(lblSelectedAlgo, 45);
	    fd_lblImageFunction.left = new FormAttachment(0, 10);
	    fd_lblImageFunction.right = new FormAttachment(tabFolder, -6);
	    lblImageFunction.setLayoutData(fd_lblImageFunction);
	    lblImageFunction.setText("");
	    
	    Label lblModel = new Label(container, SWT.NONE);
	    fd_lblImageFunction.bottom = new FormAttachment(lblModel, -20);
		lblModel.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblModel = new FormData();
		fd_lblModel.top = new FormAttachment(0, 269);
		fd_lblModel.left = new FormAttachment(0, 10);
		lblModel.setLayoutData(fd_lblModel);
		lblModel.setText("Model selected :");
		
		lblModelSel = new Label(container, SWT.NONE);
		lblModelSel.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NONE));
		FormData fd_lblSelModel = new FormData();
		fd_lblSelModel.right = new FormAttachment(44);
		fd_lblSelModel.top = new FormAttachment(lblModel, 0, SWT.TOP);
		fd_lblSelModel.left = new FormAttachment(lblModel, 10);
		lblModelSel.setLayoutData(fd_lblSelModel);
		lblModelSel.setText("");
		
		Label lblParametros = new Label(container, SWT.NONE);
		lblParametros.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblParametros = new FormData();
		fd_lblParametros.top = new FormAttachment(lblModel, 10);
		fd_lblParametros.left = new FormAttachment(0, 10);
		lblParametros.setLayoutData(fd_lblParametros);
		lblParametros.setText("Parameters estimated :");
		
		tableParameters = new Table(container, SWT.BORDER);
		tableParameters.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		tableParameters.setLinesVisible(true);
		FormData fd_tableParameters = new FormData();
		fd_tableParameters.left = new FormAttachment(0, 10);
		fd_tableParameters.bottom = new FormAttachment(100, -13);
		fd_tableParameters.top = new FormAttachment(lblParametros, 6);
		tableParameters.setLayoutData(fd_tableParameters);
		
		TableColumn tcol1 = new TableColumn(tableParameters, SWT.NONE);
		tcol1.setWidth(50);
		TableColumn tcol2 = new TableColumn(tableParameters, SWT.NONE);
		tcol2.setWidth(110);
		
		Button btnResetModel = new Button(container, SWT.NONE);
		btnResetModel.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_btnResetModel = new FormData();
		fd_btnResetModel.top = new FormAttachment(lblModelSel, 6);
		fd_btnResetModel.left = new FormAttachment(lblParametros, 6);
		btnResetModel.setLayoutData(fd_btnResetModel);
		btnResetModel.setText("&Reset Model");
		
		Label lblStageSelected = new Label(container, SWT.NONE);
		lblStageSelected.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_lblStageSelected = new FormData();
		fd_lblStageSelected.right = new FormAttachment(0, 99);
		fd_lblStageSelected.top = new FormAttachment(lblSelectedAlgo, 4);
		fd_lblStageSelected.left = new FormAttachment(0, 14);
		lblStageSelected.setLayoutData(fd_lblStageSelected);
		lblStageSelected.setText("Stage selected:");
		
		textStageSelected = new Text(container, SWT.NONE);
		textStageSelected.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		textStageSelected.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_textStageSelected = new FormData();
		fd_textStageSelected.right = new FormAttachment(tabFolder, -6);
		fd_textStageSelected.top = new FormAttachment(lblSelectedAlgo, 4);
		fd_textStageSelected.left = new FormAttachment(lblStageSelected, 6);
		textStageSelected.setLayoutData(fd_textStageSelected);
		
		btnResetModel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/*boolean reset = MessageDialog.openConfirm(new Shell(), MaizeDevRate.title, "Are you sure you want to reset the model selected?");
				if(reset){
					File fResume = new File(MainActionMaizeDevRate.pathProj + File.separator+ MaizeDevRate.title + File.separator +"resume.ilcym");
					String numStages=ViewProjectsUI.getNumStage(MainPageWizardPage.getStageSel());
					Properties prop = new Properties();
					
					try{
						prop.load(new FileInputStream(fResume));
						prop.setProperty("Stage"+numStages, "");
						prop.setProperty("Model"+numStages, "");
						prop.setProperty("Parameters"+numStages, "");
						prop.setProperty("Formula"+numStages, "");
						prop.store(new FileOutputStream(fResume), "MaizeDevRate Resume");
						prop.clear();
						
						BufferedReader br = new BufferedReader(new FileReader(MainActionMaizeDevRate.pathProj + File.separator + "Progress.ilcym"));
						String str="", str2="";
						String[] temp;
						while ((str = br.readLine()) != null) {
							temp = ArrayConvertions.StringtoArray(str, "\t");
							
							if(temp[0].equalsIgnoreCase(MainPageWizardPage.getStageSel())){
								temp[4] = "false";
								str = ArrayConvertions.ArrayToString(temp, "\t");
							}
							str2 += str + "\r\n";
					    }

						br.close();
						
						BufferedWriter bw = new BufferedWriter(new FileWriter(MainActionMaizeDevRate.pathProj + File.separator + "Progress.ilcym"));
						bw.write(str2);
						bw.close();
						
						MainPageWizardPage.bolModelSelected = false;
						
					}catch (FileNotFoundException e1) {
						MessageDialog.openError(container.getShell(), MaizeDevRate.title, "Problems while trying to reset the selected model");
						e1.printStackTrace();
					} catch (IOException e1) {
						MessageDialog.openError(container.getShell(), MaizeDevRate.title, "Problems while trying to reset the selected model");
						e1.printStackTrace();
					}
				}*/
			}
		});
	}

}
