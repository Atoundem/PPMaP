package ui.modelBuilder;

//import modelDesigner.MainActionMortality;
import java.io.File;

import modelDesigner.MaizeDevRate;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import ui.CreatedMaizeProject;

public class MainPageWizardPage extends WizardPage {

	static Button btnMultipleSelection, btnSingleSelection;
	public static Text txtSelectedProject;
	public static Button[] stageArrayButtons;
	public static List lstSelectedModels;
	private static Button chkTmin, chkLimits;
	static Text txtTminTemp, txtTminValue;
	
	static boolean bolModelSelected;
	private static String strMortalityPath="";// = "D:/TestSoftware/";
	private static String stageSel="";
	private static Combo comboSelectAlgo;
	public static Combo comboDevStage;
	
	/**
	 * Create the wizard.
	 */
	public MainPageWizardPage() {
		super("MainPageWizardPage");
		setTitle("Maize Developmental Rate Model");
		setDescription("Choose Algorithm, Developmental phase and Models");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	@Override
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FormLayout());
		
		Label lblSelectedProject = new Label(container, SWT.NONE);
		lblSelectedProject.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblSelectedProject = new FormData();
		fd_lblSelectedProject.left = new FormAttachment(0, 10);
		fd_lblSelectedProject.top = new FormAttachment(0, 20);
		lblSelectedProject.setLayoutData(fd_lblSelectedProject);
		lblSelectedProject.setText("Selected Project :");
		
		txtSelectedProject = new Text(container, SWT.NONE);
		txtSelectedProject.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		txtSelectedProject.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_txtSelectedProject1 = new FormData();
		fd_txtSelectedProject1.top = new FormAttachment(0, 20);
		fd_txtSelectedProject1.right = new FormAttachment(100, -217);
		fd_txtSelectedProject1.left = new FormAttachment(lblSelectedProject, 10);
		txtSelectedProject.setLayoutData(fd_txtSelectedProject1);
		
		Label lblSelectAlgorithm = new Label(container, SWT.NONE);
		lblSelectAlgorithm.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblSelectAlgorithm = new FormData();
		fd_lblSelectAlgorithm.top = new FormAttachment(lblSelectedProject, 7);
		fd_lblSelectAlgorithm.left = new FormAttachment(lblSelectedProject, 0, SWT.LEFT);
		lblSelectAlgorithm.setLayoutData(fd_lblSelectAlgorithm);
		lblSelectAlgorithm.setText("Algorithm :");
        
        //stageArrayButtons = new Button[]{btnStage1,btnStage2,btnStage3,btnStage4,btnStage5,btnStage6,btnStage7,btnStage8};
        
        Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(lblSelectAlgorithm, 27);
		fd_label.left = new FormAttachment(lblSelectedProject, 0, SWT.LEFT);
		fd_label.right = new FormAttachment(100, -5);
		label.setLayoutData(fd_label);
		
		btnMultipleSelection = new Button(container, SWT.RADIO);
		btnMultipleSelection.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_btnMultipleSelection = new FormData();
		fd_btnMultipleSelection.top = new FormAttachment(label, 10);
		fd_btnMultipleSelection.left = new FormAttachment(0, 10);
		btnMultipleSelection.setLayoutData(fd_btnMultipleSelection);
		btnMultipleSelection.setText("Multiple Selection");
		
		btnSingleSelection = new Button(container, SWT.RADIO);
		btnSingleSelection.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_btnSingleSelection = new FormData();
		fd_btnSingleSelection.bottom = new FormAttachment(btnMultipleSelection, 0, SWT.BOTTOM);
		fd_btnSingleSelection.left = new FormAttachment(btnMultipleSelection, 32);
		btnSingleSelection.setLayoutData(fd_btnSingleSelection);
		btnSingleSelection.setText("Single selection");
		
		Label lblModels = new Label(container, SWT.NONE);
		lblModels.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblModels = new FormData();
		fd_lblModels.top = new FormAttachment(btnMultipleSelection, 30);
		fd_lblModels.left = new FormAttachment(lblSelectedProject, 0, SWT.LEFT);
		lblModels.setLayoutData(fd_lblModels);
		lblModels.setText("Models");
		
		final List lstModels = new List(container, SWT.BORDER | SWT.V_SCROLL);
		lstModels.setFont(SWTResourceManager.getFont("Comic Sans MS", 8, SWT.NORMAL));
		FormData fd_lstModels = new FormData();
		fd_lstModels.right = new FormAttachment(btnMultipleSelection, 55, SWT.RIGHT);
		fd_lstModels.bottom = new FormAttachment(lblModels, 220, SWT.BOTTOM);
		fd_lstModels.top = new FormAttachment(lblModels, 6);
		fd_lstModels.left = new FormAttachment(0, 9);
		lstModels.setLayoutData(fd_lstModels);
		
		final Table tableSubModels = new Table(container, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
		tableSubModels.setLinesVisible(true);
		tableSubModels.setHeaderVisible(true);
		tableSubModels.setFont(SWTResourceManager.getFont("Comic Sans MS", 8, SWT.NORMAL));
		FormData fd_tableSubModels = new FormData();
		fd_tableSubModels.bottom = new FormAttachment(lstModels, 0, SWT.BOTTOM);
		fd_tableSubModels.right = new FormAttachment(lstModels, 250, SWT.RIGHT);
		fd_tableSubModels.top = new FormAttachment(lstModels, 0, SWT.TOP);
		fd_tableSubModels.left = new FormAttachment(lstModels, 42);
		tableSubModels.setLayoutData(fd_tableSubModels);
		
		TableColumn tblclmnId = new TableColumn(tableSubModels, SWT.NONE);
		tblclmnId.setWidth(40);
		tblclmnId.setText("ID");
		
		TableColumn tblclmnSubmodels = new TableColumn(tableSubModels, SWT.NONE);
		tblclmnSubmodels.setWidth(130);
		tblclmnSubmodels.setText("Sub-models");
		
		Label lblSubmodels = new Label(container, SWT.NONE);
		lblSubmodels.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblSubmodels = new FormData();
		fd_lblSubmodels.left = new FormAttachment(lblModels, 182);
		fd_lblSubmodels.bottom = new FormAttachment(lblModels, 0, SWT.BOTTOM);
		lblSubmodels.setLayoutData(fd_lblSubmodels);
		lblSubmodels.setText("Sub-models");
		
		final Button btnAll = new Button(container, SWT.NONE);
		btnAll.setToolTipText("Select all sub-models");
		btnAll.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_btnAll = new FormData();
		fd_btnAll.right = new FormAttachment(tableSubModels, 47, SWT.RIGHT);
		fd_btnAll.top = new FormAttachment(label, 151);
		fd_btnAll.left = new FormAttachment(tableSubModels, 17);
		btnAll.setLayoutData(fd_btnAll);
		btnAll.setText(">>");
		
		Button btnAddModel = new Button(container, SWT.NONE);
		btnAddModel.setToolTipText("Add selected sub-model");
		btnAddModel.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_btnAddModel = new FormData();
		fd_btnAddModel.right = new FormAttachment(btnAll, 0, SWT.RIGHT);
		fd_btnAddModel.top = new FormAttachment(btnAll, 6);
		fd_btnAddModel.left = new FormAttachment(tableSubModels, 16);
		btnAddModel.setLayoutData(fd_btnAddModel);
		btnAddModel.setText(">");
		
		Button btnDeleteModel = new Button(container, SWT.NONE);
		btnDeleteModel.setToolTipText("Delete selected model");
		btnDeleteModel.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_btnDeleteModel = new FormData();
		fd_btnDeleteModel.right = new FormAttachment(btnAll, 0, SWT.RIGHT);
		fd_btnDeleteModel.top = new FormAttachment(btnAddModel, 6);
		fd_btnDeleteModel.left = new FormAttachment(btnAll, 0, SWT.LEFT);
		btnDeleteModel.setLayoutData(fd_btnDeleteModel);
		btnDeleteModel.setText("<");
		
		lstSelectedModels = new List(container, SWT.BORDER | SWT.V_SCROLL);
		lstSelectedModels.setFont(SWTResourceManager.getFont("Comic Sans MS", 8, SWT.NORMAL));
		FormData fd_lstSelectedModels = new FormData();
		fd_lstSelectedModels.bottom = new FormAttachment(lstModels, 0, SWT.BOTTOM);
		fd_lstSelectedModels.top = new FormAttachment(lstModels, 0, SWT.TOP);
		fd_lstSelectedModels.left = new FormAttachment(btnAll, 25);
		lstSelectedModels.setLayoutData(fd_lstSelectedModels);
		
/*		int nbmodel = MaizeDevRate.lstModelNames[0].length;
		System.out.println(nbmodel);
		
		Button rbSelectAllModels = new Button(container, SWT.PUSH);
		rbSelectAllModels.setText("Select all " + MaizeDevRate.lstModelNames[0].length + " models");
		rbSelectAllModels.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_rbSelectAllModels = new FormData();
		fd_rbSelectAllModels.left = new FormAttachment(lstSelectedModels, 5, SWT.RIGHT);
		fd_rbSelectAllModels.top = new FormAttachment(lstSelectedModels, 0, SWT.TOP);
		rbSelectAllModels.setLayoutData(fd_rbSelectAllModels);
		
*/		
		Button rbSelectAllModels = new Button(container, SWT.PUSH);
//		rbSelectAllModels.setText("Select all " + MaizeDevRate.lstModelNames[0].length + " models");
		rbSelectAllModels.setText("Select all " + "" + " models");
		rbSelectAllModels.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_rbSelectAllModels = new FormData();
		fd_rbSelectAllModels.left = new FormAttachment(lstSelectedModels, 5, SWT.RIGHT);
		fd_rbSelectAllModels.top = new FormAttachment(lstSelectedModels, 0, SWT.TOP);
		rbSelectAllModels.setLayoutData(fd_rbSelectAllModels);
		
		
		Label lblSelectedModels = new Label(container, SWT.NONE);
		fd_lstSelectedModels.right = new FormAttachment(lblSelectedModels, 87, SWT.RIGHT);
		lblSelectedModels.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblSelectedModels = new FormData();
		fd_lblSelectedModels.left = new FormAttachment(lblSubmodels, 163);
		fd_lblSelectedModels.bottom = new FormAttachment(lblModels, 0, SWT.BOTTOM);
		lblSelectedModels.setLayoutData(fd_lblSelectedModels);
		lblSelectedModels.setText("Selected models");
		
		Button btnDeselectAll = new Button(container, SWT.NONE);
		btnDeselectAll.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_btnDeselectAll = new FormData();
		fd_btnDeselectAll.top = new FormAttachment(btnDeleteModel, 6);
		fd_btnDeselectAll.right = new FormAttachment(btnAll, 0, SWT.RIGHT);
		fd_btnDeselectAll.left = new FormAttachment(btnAll, 0, SWT.LEFT);
		btnDeselectAll.setLayoutData(fd_btnDeselectAll);
		btnDeselectAll.setText("<<");
		
		chkTmin = new Button(container, SWT.CHECK);
	    chkTmin.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
	    chkTmin.setText("");
	    FormData fd_chkTmin = new FormData();
	    fd_chkTmin.top = new FormAttachment(lstModels, 15);
	    fd_chkTmin.left = new FormAttachment(lblSelectedProject, 5, SWT.LEFT);
	    chkTmin.setLayoutData(fd_chkTmin);
		
		Group grpExtremeValues = new Group(container, SWT.NONE);
		grpExtremeValues.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NONE));
		grpExtremeValues.setText("   Additional values");
		grpExtremeValues.setLayout(new GridLayout(4, false));
	    FormData fd_grpExtremeValues = new FormData();
	    fd_grpExtremeValues.right = new FormAttachment(100, -10);
	    fd_grpExtremeValues.top = new FormAttachment(lstModels, 15);
	    fd_grpExtremeValues.left = new FormAttachment(lblSelectedProject, 0, SWT.LEFT);
	    grpExtremeValues.setLayoutData(fd_grpExtremeValues);
	    
	    Label lTempMin = new Label(grpExtremeValues, SWT.CENTER);
	    lTempMin.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
	    lTempMin.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NONE));
	    lTempMin.setText("Temp.");
	    txtTminTemp = new Text(grpExtremeValues, SWT.BORDER);
		txtTminTemp.setEditable(false);
		
	    Label lValueMin = new Label(grpExtremeValues, SWT.CENTER);
	    lValueMin.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
	    lValueMin.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NONE));
	    lValueMin.setText("Value");
	    txtTminValue = new Text(grpExtremeValues, SWT.BORDER);
		txtTminValue.setEditable(false);
		
		chkLimits = new Button(container, SWT.CHECK);
		chkLimits.setSelection(true);
		FormData fd_chkLimits = new FormData();
		fd_chkLimits.top = new FormAttachment(grpExtremeValues, 15);
		fd_chkLimits.left = new FormAttachment(0, 10);
		chkLimits.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		chkLimits.setText("Limits");
		chkLimits.setLayoutData(fd_chkLimits);
		
		comboSelectAlgo = new Combo(container, SWT.READ_ONLY);
		comboSelectAlgo.setToolTipText("List of Algorithm");
		comboSelectAlgo.setItems(new String[] {"Marquardtr", "Newton"});
		FormData fd_comboSelectAlgo = new FormData();
		fd_comboSelectAlgo.top = new FormAttachment(lblSelectedProject, 6);
		fd_comboSelectAlgo.right = new FormAttachment(lblSelectAlgorithm, 124, SWT.RIGHT);
		fd_comboSelectAlgo.left = new FormAttachment(lblSelectAlgorithm, 25);
		comboSelectAlgo.setLayoutData(fd_comboSelectAlgo);
		comboSelectAlgo.select(0);
		
		Label lblDevStage = new Label(container, SWT.NONE);
		FormData fd_lblDevStage = new FormData();
		fd_lblDevStage.top = new FormAttachment(lblSelectAlgorithm, 5, SWT.TOP);
		fd_lblDevStage.right = new FormAttachment(comboSelectAlgo, 139, SWT.RIGHT);
		fd_lblDevStage.left = new FormAttachment(comboSelectAlgo, 73);
		lblDevStage.setLayoutData(fd_lblDevStage);
		lblDevStage.setText("Dev Phase :");
		
		comboDevStage = new Combo(container, SWT.READ_ONLY);
		comboDevStage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		FormData fd_comboDevStage = new FormData();
		fd_comboDevStage.right = new FormAttachment(lblDevStage, 142, SWT.RIGHT);
		fd_comboDevStage.top = new FormAttachment(txtSelectedProject, 6);
		fd_comboDevStage.left = new FormAttachment(lblDevStage, 25);
		comboDevStage.setLayoutData(fd_comboDevStage);
//		comboDevStage.setItems(new String[]{"Vegetative", "Maturity"});
		
		for(int i=0; i<CreatedMaizeProject.getStageNames().length; i++){
			comboDevStage.add(CreatedMaizeProject.getStageNames()[i]);
		}

		
//The real block for initialiszation

		 comboDevStage.select(0);
		 //comboDevStage.s
		for(int i=0; i<MaizeDevRate.lstArrayNames.length; i++){
			lstModels.add(MaizeDevRate.lstArrayNames[i]);
		}
		

		rbSelectAllModels.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		lstSelectedModels.removeAll();
	    		for(int i=0; i<MaizeDevRate.lstModelNames[0].length; i++){
	    			lstSelectedModels.add(MaizeDevRate.lstModelNames[0][i]);
	    		}
	    	}
	    });
		
		chkTmin.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		if(chkTmin.getSelection()){
	    			txtTminTemp.setEditable(true);
	    			txtTminValue.setEditable(true);
	    		}else{
	    			txtTminTemp.setEditable(false);
	    			txtTminValue.setEditable(false);
	    		}
	    	}
	    });
	    
	    lstModels.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableSubModels.removeAll();
				for(int i=0; i<MaizeDevRate.lstMatrixSubNames[lstModels.getSelectionIndex()].length; i++){
					TableItem it = new TableItem(tableSubModels, SWT.NONE);
					it.setText(new String[]{MaizeDevRate.getModelNumber(MaizeDevRate.lstMatrixSubNames[lstModels.getSelectionIndex()][i])+"",MaizeDevRate.lstMatrixSubNames[lstModels.getSelectionIndex()][i]});
				}
			}
		});
		
		btnAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean bolExists=false;
				System.out.println(comboSelectAlgo.getText());
				for(int i=0; i<tableSubModels.getItemCount(); i++){
					for(int j=0; j<lstSelectedModels.getItemCount(); j++){
						if(tableSubModels.getItem(i).getText(1).equalsIgnoreCase(lstSelectedModels.getItem(j))){
							bolExists = true;
							break;
						}else
							bolExists = false;						
					}
					if(!bolExists)
						lstSelectedModels.add(tableSubModels.getItem(i).getText(1));
				}
			}
		});
		btnDeselectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lstSelectedModels.removeAll();
			}
		});
		
		btnAddModel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(comboSelectAlgo.getText());
				if(btnSingleSelection.getSelection()){
					if(lstSelectedModels.getItemCount() > 0)
						lstSelectedModels.remove(0);
					lstSelectedModels.add(tableSubModels.getItem(tableSubModels.getSelectionIndex()).getText(1));
					
				}else{
				boolean bolExists=false;
					for(int i=0; i<lstSelectedModels.getItemCount(); i++){
						if(tableSubModels.getItem(tableSubModels.getSelectionIndex()).getText(1).equalsIgnoreCase(lstSelectedModels.getItem(i)))
							bolExists = true;
					}
					if(!bolExists)
					lstSelectedModels.add(tableSubModels.getItem(tableSubModels.getSelectionIndex()).getText(1));
				}
			}
		});
		btnDeleteModel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lstSelectedModels.remove(lstSelectedModels.getSelectionIndex());
			}
		});
		
		btnMultipleSelection.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnMultipleSelection.getSelection())
					btnAll.setEnabled(true);
			}
		});
		btnSingleSelection.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lstSelectedModels.removeAll();
				if(btnSingleSelection.getSelection())
					btnAll.setEnabled(false);
			}
		});
	}
	
	public static String getComboDevStage() {
		return comboDevStage.getText();
	}

	public static void setComboDevStage(Combo comboDevStage) {
		MainPageWizardPage.comboDevStage = comboDevStage;
	}

	public static boolean getSeveralModels(){
		if(btnMultipleSelection.getSelection())
			return btnMultipleSelection.getSelection();
		else
			return false;
	}
	
	public static boolean getSingleModel(){
		if(btnSingleSelection.getSelection())
			return btnSingleSelection.getSelection();
		else
			return false;
	}
	
	@Override
	public IWizardPage getNextPage() {
		if(!bolModelSelected){
			SeveralModelsWizardPage.txtSelectedLifeStage.setText(getSelectAlgo());
			OneModelWizardPage1.txtSelectedLifeStage.setText(getSelectAlgo());
			OneModelWizardPage2.txtSelectedLifeStage.setText(getSelectAlgo());
			
			SeveralModelsWizardPage.txtStageselected.setText(MainPageWizardPage.getComboDevStage());
			OneModelWizardPage1.textStageSelected.setText(MainPageWizardPage.getComboDevStage());
			OneModelWizardPage2.txtStageselected.setText(MainPageWizardPage.getComboDevStage());
			
			if(getSeveralModels()){
				if(lstSelectedModels.getItemCount()>0){
					MaizeDevRate.definirParamsImage(); 
					MaizeDevRate.proceesAllModelsNew(chkTmin.getSelection());
					lstSelectedModels.removeAll();
					return super.getWizard().getPage("SeveralModelsWizardPage");
				}else
					return super.getWizard().getPage("MainPageWizardPage");
			}else{
				if(lstSelectedModels.getItemCount()>0){
					OneModelWizardPage1.lblSelectedFunctionAlone.setText(lstSelectedModels.getItem(0));
					MaizeDevRate.definirParamsImage(); 
					MaizeDevRate.selectionModelOneByOne(MaizeDevRate.getModelNumber(lstSelectedModels.getItem(0)), chkTmin.getSelection());
					OneModelWizardPage1.showParametersLabel(MaizeDevRate.getModelNumber(lstSelectedModels.getItem(0)));
					return super.getWizard().getPage("OneModelWizardPage1");
				}else
					return super.getWizard().getPage("MainPageWizardPage");
				
			}
		}else{
			//SelectedModelWizardPage.txtSelectedLifeStage.setText(stageSel);
			return super.getWizard().getPage("SelectedModelWizardPage");
		}
	}

// A modifier	
	
	public static String getstrMortalityPath(){
//		return (strMortalityPath+File.separator).replace("\\","/");
		File filePat = new File(CreatedMaizeProject.getStrPathProject() + File.separator + comboDevStage.getText());
		
		//strPathProject = new String(fileP.getAbsolutePath().replace('\\', '/'));;
		
		return new String(filePat.getAbsolutePath().replace('\\', '/') +"/");//CreatedMaizeProject.getStrPathProject();
		
		
//		return CreatedMaizeProject.getStrPathProject();
	}
	
	
	public static String getstrMortalityPathMapping(String subFolder){
//		return (strMortalityPath+File.separator).replace("\\","/");
		File filePat = new File(CreatedMaizeProject.getStrPathProject() + File.separator + subFolder);
		
		//strPathProject = new String(fileP.getAbsolutePath().replace('\\', '/'));;
		
		return new String(filePat.getAbsolutePath().replace('\\', '/') +"/");//CreatedMaizeProject.getStrPathProject();
		
		
//		return CreatedMaizeProject.getStrPathProject();
	}
	
	public static void setStrMortalityPath(String strMortalityPath) {
		MainPageWizardPage.strMortalityPath = strMortalityPath;
	}

	public static String getStageSel(){
		return stageSel;
	}
	
	public static String getSelectAlgo(){
		return comboSelectAlgo.getText();
	}
	
	public static String[] getExtremValues(){
		return new String[]{txtTminTemp.getText(), txtTminValue.getText()};
	}
	
	public static String getLimits(){
		if(chkLimits.getSelection())
			return "yes";
		else
			return "no";
	}
	
	public static String getFileDevStageData() {
		//return MainPageWizardPage.getstrMortalityPath() + File.separator + fileMortalityData;
		return CreatedMaizeProject.getStrPathProject() + comboDevStage.getText()+".txt";
	}

/*	public static void setFileDevStageData(String fileMortalityData) {
		LoadPlotData.fileMortalityData = fileMortalityData;
	}*/
}
