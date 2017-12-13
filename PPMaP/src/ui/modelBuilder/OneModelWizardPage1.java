package ui.modelBuilder;
//package org.cgiar.cip.ilcym.modelbuilder.mortality.wizards;

import modelDesigner.MaizeDevRate;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class OneModelWizardPage1 extends WizardPage {

	public static Text txtSelectedLifeStage;
	public static Label lblSelectedFunctionAlone;
	public static Text txtAdjust, txtPar1, txtPar2, txtPar3, txtPar4, txtPar5, txtPar6;
	static Text txtTminTempOne, txtTminValueOne;
	public static Label lblImageTemp, lblPar1, lblPar2, lblPar3, lblPar4, lblPar5, lblPar6;
	private static Button chkLimitsOne;
	private Label lblStageSelected;
	static Text textStageSelected;
	
	/**
	 * Create the wizard.
	 */
	public OneModelWizardPage1() {
		super("OneModelWizardPage1");
		setTitle("One model selected");
		setDescription("Change the parameters or set this model");
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
		
		Label lblSelectedAlgo = new Label(container, SWT.NONE);
		lblSelectedAlgo.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblSelectedAlgo = new FormData();
		fd_lblSelectedAlgo.left = new FormAttachment(0, 10);
		fd_lblSelectedAlgo.top = new FormAttachment(0, 10);
		lblSelectedAlgo.setLayoutData(fd_lblSelectedAlgo);
		lblSelectedAlgo.setText("Algorithm selected:");
		
		txtSelectedLifeStage = new Text(container, SWT.NONE);
		txtSelectedLifeStage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		txtSelectedLifeStage.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_txtSelectedProject1 = new FormData();
		fd_txtSelectedProject1.top = new FormAttachment(lblSelectedAlgo, 0, SWT.TOP);
		fd_txtSelectedProject1.left = new FormAttachment(lblSelectedAlgo, 10);
		fd_txtSelectedProject1.right = new FormAttachment(100, -217);
		txtSelectedLifeStage.setLayoutData(fd_txtSelectedProject1);
		
		Label lblFunctions = new Label(container, SWT.NONE);
		lblFunctions.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblFunctions = new FormData();
		fd_lblFunctions.right = new FormAttachment(0, 125);
		fd_lblFunctions.top = new FormAttachment(lblSelectedAlgo, 30);
		fd_lblFunctions.left = new FormAttachment(0, 10);
		lblFunctions.setLayoutData(fd_lblFunctions);
		lblFunctions.setText("Selected function:");
		
		lblSelectedFunctionAlone = new Label(container, SWT.NONE);
		lblSelectedFunctionAlone.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NONE));
		FormData fd_cboFunctions = new FormData();
		fd_cboFunctions.left = new FormAttachment(lblFunctions, 6);
		fd_cboFunctions.right = new FormAttachment(44);
		lblSelectedFunctionAlone.setLayoutData(fd_cboFunctions);
		
		lblImageTemp = new Label(container, SWT.BORDER | SWT.CENTER);
		lblImageTemp.setAlignment(SWT.CENTER);
		lblImageTemp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblImageTemp = new FormData();
		fd_lblImageTemp.bottom = new FormAttachment(100);
		fd_lblImageTemp.left = new FormAttachment(45);
		fd_lblImageTemp.right = new FormAttachment(100, -5);
		lblImageTemp.setLayoutData(fd_lblImageTemp);
		
		Group grpParameters = new Group(container, SWT.NONE);
		grpParameters.setText("Parameters");
		grpParameters.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		grpParameters.setLayout(new GridLayout(8, false));
		FormData fd_grpParameters = new FormData();
		fd_grpParameters.top = new FormAttachment(lblFunctions, 6);
		fd_grpParameters.right = new FormAttachment(44);
		fd_grpParameters.left = new FormAttachment(0, 10);
		grpParameters.setLayoutData(fd_grpParameters);
		
		lblPar1 = new Label(grpParameters, SWT.NONE);
		lblPar1.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		lblPar1.setText("Tl        ");
		
		txtPar1 = new Text(grpParameters, SWT.BORDER);
		txtPar1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpParameters, SWT.NONE);
		
		lblPar2 = new Label(grpParameters, SWT.NONE);
		lblPar2.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		lblPar2.setText("Th        ");
		
		txtPar2 = new Text(grpParameters, SWT.BORDER);
		txtPar2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpParameters, SWT.NONE);
		
		lblPar3 = new Label(grpParameters, SWT.NONE);
		lblPar3.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		lblPar3.setText("Ha        ");
		
		txtPar3 = new Text(grpParameters, SWT.BORDER);
		txtPar3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblPar4 = new Label(grpParameters, SWT.NONE);
		lblPar4.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		lblPar4.setText("Hl        ");
		
		txtPar4 = new Text(grpParameters, SWT.BORDER);
		txtPar4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpParameters, SWT.NONE);
		
		lblPar5 = new Label(grpParameters, SWT.NONE);
		lblPar5.setText("Hh        ");
		lblPar5.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		
		txtPar5 = new Text(grpParameters, SWT.BORDER);
		txtPar5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpParameters, SWT.NONE);
		
		lblPar6 = new Label(grpParameters, SWT.NONE);
		lblPar6.setText("          ");
		lblPar6.setVisible(false);
		lblPar6.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		
		txtPar6 = new Text(grpParameters, SWT.BORDER);
		txtPar6.setVisible(false);
		txtPar6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group grpAdjustment = new Group(container, SWT.NONE);
		grpAdjustment.setLayout(new FormLayout());
		grpAdjustment.setText("Auto Adjustment");
		grpAdjustment.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_grpAdjustment = new FormData();
		fd_grpAdjustment.right = new FormAttachment(44);
		fd_grpAdjustment.top = new FormAttachment(grpParameters, 6);
		new Label(grpParameters, SWT.NONE);
		new Label(grpParameters, SWT.NONE);
		new Label(grpParameters, SWT.NONE);
		new Label(grpParameters, SWT.NONE);
		new Label(grpParameters, SWT.NONE);
		new Label(grpParameters, SWT.NONE);
		new Label(grpParameters, SWT.NONE);
		new Label(grpParameters, SWT.NONE);
		fd_grpAdjustment.left = new FormAttachment(0, 10);
		grpAdjustment.setLayoutData(fd_grpAdjustment);
		
		Label label = new Label(grpAdjustment, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0,5);
		fd_label.left = new FormAttachment(0);
		label.setLayoutData(fd_label);
		label.setText("Adjustment");
		
		Button btnReAdjust = new Button(grpAdjustment, SWT.NONE);
		btnReAdjust.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_btnReAdjust = new FormData();
		fd_btnReAdjust.right = new FormAttachment(100);
		fd_btnReAdjust.top = new FormAttachment(0);
		btnReAdjust.setLayoutData(fd_btnReAdjust);
		btnReAdjust.setText("Readjust");

		/*Text */txtAdjust = new Text(grpAdjustment, SWT.BORDER);
		FormData fd_txtAdjust = new FormData();
		fd_txtAdjust.left = new FormAttachment(label,10);
		fd_txtAdjust.right = new FormAttachment(btnReAdjust, -5);
		fd_txtAdjust.top = new FormAttachment(0, 5);
		txtAdjust.setLayoutData(fd_txtAdjust);
		
		Button btnSet = new Button(grpAdjustment, SWT.NONE);
		btnSet.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_btnSet = new FormData();
		fd_btnSet.left = new FormAttachment(btnReAdjust, 0, SWT.LEFT);
		fd_btnSet.right = new FormAttachment(100);
		fd_btnSet.top = new FormAttachment(btnReAdjust, 5);
		btnSet.setLayoutData(fd_btnSet);
		btnSet.setText("Set");
		
		Button btnBack = new Button(grpAdjustment, SWT.NONE);
		btnBack.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NORMAL));
		FormData fd_btnBack = new FormData();
		fd_btnBack.left = new FormAttachment(btnSet, -60, SWT.LEFT);
		fd_btnBack.right = new FormAttachment(btnSet, -1);
		fd_btnBack.top = new FormAttachment(btnSet, 0, SWT.TOP);
		btnBack.setLayoutData(fd_btnBack);
		btnBack.setText("Back");
		
		final Button chkTmin = new Button(container, SWT.CHECK);
		chkTmin.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_chkTmin = new FormData();
		fd_chkTmin.left = new FormAttachment(0, 15);
		fd_chkTmin.top = new FormAttachment(grpAdjustment, 15);
		chkTmin.setLayoutData(fd_chkTmin);
		
		Group grpExtremeValues = new Group(container, SWT.NONE);
		grpExtremeValues.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NONE));
		grpExtremeValues.setText("   Additional values");
		grpExtremeValues.setLayout(new GridLayout(5, false));
	    FormData fd_grpExtremeValues = new FormData();
	    fd_grpExtremeValues.left = new FormAttachment(0, 10);
	    fd_grpExtremeValues.right = new FormAttachment(lblImageTemp, -6);
	    fd_grpExtremeValues.top = new FormAttachment(grpAdjustment, 15);
	    grpExtremeValues.setLayoutData(fd_grpExtremeValues);
	    
	    Label l1 = new Label(grpExtremeValues, SWT.NONE);
	    l1.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NONE));
		l1.setText("Temp.");
		
		txtTminTempOne= new Text(grpExtremeValues, SWT.BORDER);
		txtTminTempOne.setEditable(false);
		
		Label l2 = new Label(grpExtremeValues, SWT.NONE);
	    l2.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NONE));
		l2.setText("Value");
		
		txtTminValueOne = new Text(grpExtremeValues, SWT.BORDER);
		txtTminValueOne.setEditable(false);
		
		Button btnInclude = new Button(grpExtremeValues, SWT.PUSH);
		l2.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NONE));
		btnInclude.setText("Include values");
		
		chkLimitsOne = new Button(container, SWT.CHECK);
		chkLimitsOne.setSelection(true);
		FormData fd_chkLimits = new FormData();
		fd_chkLimits.left = new FormAttachment(grpExtremeValues, 0, SWT.LEFT);
		fd_chkLimits.top = new FormAttachment(grpExtremeValues, 20);
		chkLimitsOne.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		chkLimitsOne.setText("Limits");
		chkLimitsOne.setLayoutData(fd_chkLimits);
		
		lblStageSelected = new Label(container, SWT.NONE);
		lblStageSelected.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblStageSelected = new FormData();
		fd_lblStageSelected.right = new FormAttachment(lblSelectedAlgo, 0, SWT.RIGHT);
		fd_lblStageSelected.top = new FormAttachment(lblSelectedAlgo, 6);
		fd_lblStageSelected.left = new FormAttachment(lblSelectedAlgo, 0, SWT.LEFT);
		lblStageSelected.setLayoutData(fd_lblStageSelected);
		lblStageSelected.setText("      Stage selected:");
		
		textStageSelected = new Text(container, SWT.NONE);
		fd_cboFunctions.top = new FormAttachment(textStageSelected, 6);
		fd_lblImageTemp.top = new FormAttachment(textStageSelected, 6);
		textStageSelected.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		textStageSelected.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		FormData fd_textStageSelected = new FormData();
		fd_textStageSelected.right = new FormAttachment(txtSelectedLifeStage, 0, SWT.RIGHT);
		fd_textStageSelected.bottom = new FormAttachment(txtSelectedLifeStage, 24, SWT.BOTTOM);
		fd_textStageSelected.top = new FormAttachment(txtSelectedLifeStage, 6);
		fd_textStageSelected.left = new FormAttachment(lblStageSelected, 10);
		textStageSelected.setLayoutData(fd_textStageSelected);
		
		btnReAdjust.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MaizeDevRate.randomParameters();
			}
		});
		btnSet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//MaizeDevRate.setParameters();
			}
		});
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MaizeDevRate.backParameters();
			}
		});
		
		btnInclude.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!txtTminTempOne.getText().equalsIgnoreCase("") && !txtTminValueOne.getText().equalsIgnoreCase(""))
				MaizeDevRate.spinnerListener(MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0)), txtPar1.getText(), txtPar2.getText(),
						txtPar3.getText(), txtPar4.getText(), txtPar5.getText(), txtPar6.getText(), chkTmin.getSelection());
						
			}
		});
		
		
		chkTmin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(chkTmin.getSelection()){
					txtTminTempOne.setEditable(true);
					txtTminValueOne.setEditable(true);
				}else{
					txtTminTempOne.setEditable(false);
					txtTminValueOne.setEditable(false);
					MaizeDevRate.spinnerListener(MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0)), txtPar1.getText(), txtPar2.getText(),
							txtPar3.getText(), txtPar4.getText(), txtPar5.getText(), txtPar6.getText(), chkTmin.getSelection());
				}
			}
		});
		
		txtPar1.addKeyListener(new org.eclipse.swt.events.KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == 16777296)
					MaizeDevRate.spinnerListener(MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0)), txtPar1.getText(), txtPar2.getText(),
							txtPar3.getText(), txtPar4.getText(), txtPar5.getText(), txtPar6.getText(), chkTmin.getSelection());
			}
		});
		txtPar2.addKeyListener(new org.eclipse.swt.events.KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == 16777296)
					MaizeDevRate.spinnerListener(MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0)), txtPar1.getText(), txtPar2.getText(),
							txtPar3.getText(), txtPar4.getText(), txtPar5.getText(), txtPar6.getText(), chkTmin.getSelection());
			}
		});
		txtPar3.addKeyListener(new org.eclipse.swt.events.KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == 16777296)
					MaizeDevRate.spinnerListener(MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0)), txtPar1.getText(), txtPar2.getText(),
							txtPar3.getText(), txtPar4.getText(), txtPar5.getText(), txtPar6.getText(), chkTmin.getSelection());
			}
		});
		txtPar4.addKeyListener(new org.eclipse.swt.events.KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == 16777296)
					MaizeDevRate.spinnerListener(MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0)), txtPar1.getText(), txtPar2.getText(),
							txtPar3.getText(), txtPar4.getText(), txtPar5.getText(), txtPar6.getText(), chkTmin.getSelection());
			}
		});
		txtPar5.addKeyListener(new org.eclipse.swt.events.KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == 16777296)
					MaizeDevRate.spinnerListener(MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0)), txtPar1.getText(), txtPar2.getText(),
							txtPar3.getText(), txtPar4.getText(), txtPar5.getText(), txtPar6.getText(), chkTmin.getSelection());
			}
		});
		txtPar6.addKeyListener(new org.eclipse.swt.events.KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode == 16777296)
					MaizeDevRate.spinnerListener(MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0)), txtPar1.getText(), txtPar2.getText(),
							txtPar3.getText(), txtPar4.getText(), txtPar5.getText(), txtPar6.getText(), chkTmin.getSelection());
			}
		});
	}
	
	public static String[] getExtremValuesOne()
	{
		return new String[]{txtTminTempOne.getText(), txtTminValueOne.getText()};
	}

	public static String getLimitsOne()
	{
		if(chkLimitsOne.getSelection())
			return "yes";
		else
			return "no";
	}
	
	/** Metodo que muestra los nombres de los parametros correspondientes, segun el numero del modelo **/
	public static void showParametersLabel(int model){
		if(model < 7){
			lblPar1.setText("a");
			lblPar2.setText("b");
			lblPar3.setText("c");
			lblPar4.setText("");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(false);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(false);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 7){
			lblPar1.setText("a");
			lblPar2.setText("b");
			lblPar3.setText("c");
			lblPar4.setText("d");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 8){
			lblPar1.setText("a");
			lblPar2.setText("b");
			lblPar3.setText("xo");
			lblPar4.setText("c");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 9){
			lblPar1.setText("y0");
			lblPar2.setText("a");
			lblPar3.setText("x0");
			lblPar4.setText("b");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 10){
			lblPar1.setText("y0");
			lblPar2.setText("a");
			lblPar3.setText("x0");
			lblPar4.setText("b");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 11 || model == 20){
			lblPar1.setText("b1");
			lblPar2.setText("b2");
			lblPar3.setText("b3");
			lblPar4.setText("d");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 12 || model == 14 || model == 16 || model == 18 || model == 22){
			lblPar1.setText("b1");
			lblPar2.setText("b2");
			lblPar3.setText("b3");
			lblPar4.setText("");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(false);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(false);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 13 || model == 15 || model == 17 || model == 19 || model == 23){
			lblPar1.setText("b1");
			lblPar2.setText("b2");
			lblPar3.setText("b3");
			lblPar4.setText("b4");
			lblPar5.setText("b5");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(true);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(true);
			txtPar6.setVisible(false);
		}
		if(model == 21){
			lblPar1.setText("b1");
			lblPar2.setText("b2");
			lblPar3.setText("b3");
			lblPar4.setText("b4");
			lblPar5.setText("b5");
			lblPar6.setText("b6");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(true);
			lblPar6.setVisible(true);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(true);
			txtPar6.setVisible(true);
		}
		if(model == 24 || model == 25){
			lblPar1.setText("rm");
			lblPar2.setText("Topt");
			lblPar3.setText("Troh");
			lblPar4.setText("");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(false);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(false);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 26 || model == 30){
			lblPar1.setText("Topt");
			lblPar2.setText("B");
			lblPar3.setText("H");
			lblPar4.setText("");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(false);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(false);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 27 || model == 31){
			lblPar1.setText("Tl");
			lblPar2.setText("Th");
			lblPar3.setText("B");
			lblPar4.setText("H");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 28 || model == 32){
			lblPar1.setText("Topt");
			lblPar2.setText("Bl");
			lblPar3.setText("Bh");
			lblPar4.setText("H");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 29 || model == 33){
			lblPar1.setText("Tl");
			lblPar2.setText("Th");
			lblPar3.setText("Bl");
			lblPar4.setText("Bh");
			lblPar5.setText("H");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(true);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(true);
			txtPar6.setVisible(false);
		}
		if(model == 34){
			lblPar1.setText("Hl");
			lblPar2.setText("Tl");
			lblPar3.setText("Hh");
			lblPar4.setText("Th");
			lblPar5.setText("Bm");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(true);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(true);
			txtPar6.setVisible(false);
		}
		if(model == 35 || model == 37){
			lblPar1.setText("a1");
			lblPar2.setText("b1");
			lblPar3.setText("a2");
			lblPar4.setText("b2");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 36){/** TODO queda pendiente modificar numero de modelos **/
			lblPar1.setText("w");
			lblPar2.setText("");
			lblPar3.setText("");
			lblPar4.setText("");
			lblPar5.setText("");
			
			lblPar3.setVisible(false);
			lblPar4.setVisible(false);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(false);
			txtPar4.setVisible(false);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 38){
			lblPar1.setText("a1");
			lblPar2.setText("b1");
			lblPar3.setText("a2");
			lblPar4.setText("b2");
			lblPar5.setText("c1");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(true);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(true);
			txtPar6.setVisible(false);
		}
		if(model == 39){
			lblPar1.setText("a");
			lblPar2.setText("b");
			lblPar3.setText("nn");
			lblPar4.setText("");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(false);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(false);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 40){
			lblPar1.setText("a");
			lblPar2.setText("To");
			lblPar3.setText("Tl");
			lblPar4.setText("d");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 41){
			lblPar1.setText("a");
			lblPar2.setText("To");
			lblPar3.setText("Tl");
			lblPar4.setText("d");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 42){
			lblPar1.setText("a");
			lblPar2.setText("Tmax");
			lblPar3.setText("Tmin");
			lblPar4.setText("n");
			lblPar5.setText("m");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(true);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(true);
			txtPar6.setVisible(false);
		}
		if(model == 43){
			lblPar1.setText("Dmin");
			lblPar2.setText("k");
			lblPar3.setText("Tp");
			lblPar4.setText("lamb");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 44){
			lblPar1.setText("a");
			lblPar2.setText("Tl");
			lblPar3.setText("Th");
			lblPar4.setText("B");
			lblPar5.setText("");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(false);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(false);
			txtPar6.setVisible(false);
		}
		if(model == 45){
			lblPar1.setText("a");
			lblPar2.setText("Tl");
			lblPar3.setText("Th");
			lblPar4.setText("Bl");
			lblPar5.setText("Bh");
			
			lblPar3.setVisible(true);
			lblPar4.setVisible(true);
			lblPar5.setVisible(true);
			lblPar6.setVisible(false);
			
			txtPar3.setVisible(true);
			txtPar4.setVisible(true);
			txtPar5.setVisible(true);
			txtPar6.setVisible(false);
		}
		
	}
	
	@Override
	public IWizardPage getNextPage() {
		MaizeDevRate.setModelSelectedOne(MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0)), txtPar1.getText(), txtPar2.getText(), txtPar3.getText(), txtPar4.getText(),
				txtPar5.getText(), txtPar6.getText());
		return super.getWizard().getPage("OneModelWizardPage2");
	}

	@Override
	public IWizardPage getPreviousPage() {
		return super.getPreviousPage();
	}

}
