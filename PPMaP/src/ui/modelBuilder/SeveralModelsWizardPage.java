package ui.modelBuilder;

//package org.cgiar.cip.ilcym.modelbuilder.mortality.wizards;

import java.util.ArrayList;
import java.util.List;

import maizeTools.MaizeDevUtils;

//import projects.clasesutils.IlcymUtils;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class SeveralModelsWizardPage extends WizardPage {

	public static Table tableCriterias;
	public static Composite container;
	public static Text txtSelectedLifeStage;
	public static Label lblModelSelAll, txtParametersEstimatedAll;
	static Text txtStageselected;
	
	/**
	 * Create the wizard.
	 */
	public SeveralModelsWizardPage() {
		super("SeveralModelsWizardPage");
		setPageComplete(false);
		setTitle("Several models");
		setDescription("Select the best model");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FormLayout());
		
		Label lblSelectedAlgo = new Label(container, SWT.NONE);
		lblSelectedAlgo.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
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
		
		tableCriterias = new Table(container, SWT.BORDER);
		tableCriterias.setHeaderVisible(true);
		tableCriterias.setLinesVisible(true);
		tableCriterias.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NONE));
		FormData fd_tableCriterias = new FormData();
		fd_tableCriterias.top = new FormAttachment(txtSelectedLifeStage, 55);
		fd_tableCriterias.left = new FormAttachment(0, 183);
		fd_tableCriterias.right = new FormAttachment(100, -153);
		tableCriterias.setLayoutData(fd_tableCriterias);
		
		TableColumn tCol0 = new TableColumn(tableCriterias, SWT.CENTER);
		tCol0.setText("ID");
		tCol0.setWidth(40);
		
		TableColumn tCol1 = new TableColumn(tableCriterias, SWT.CENTER);
		tCol1.setText("Models");
		tCol1.setWidth(160);
		
		tCol1 = new TableColumn(tableCriterias, SWT.CENTER);
		tCol1.setText("R2");
		tCol1.setWidth(65);
		
		tCol1 = new TableColumn(tableCriterias, SWT.CENTER);
		tCol1.setText("R2_Adj");
		tCol1.setWidth(65);
		
		tCol1 = new TableColumn(tableCriterias, SWT.CENTER);
		tCol1.setText("SSR");
		tCol1.setWidth(65);
		
		tCol1 = new TableColumn(tableCriterias, SWT.CENTER);
		tCol1.setText("AICc");
		tCol1.setWidth(65);
		
		tCol1 = new TableColumn(tableCriterias, SWT.CENTER);
		tCol1.setText("MSC");
		tCol1.setWidth(65);
		
		tCol1 = new TableColumn(tableCriterias, SWT.CENTER);
		tCol1.setText("RMSE");
		tCol1.setWidth(65);
		
		final Button getBestModelButton = new Button(container, SWT.NONE);
		fd_tableCriterias.bottom = new FormAttachment(getBestModelButton, -1);
		getBestModelButton.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.NONE));
		final FormData formData_1 = new FormData();
		formData_1.right = new FormAttachment(tableCriterias, 0, SWT.RIGHT);
		formData_1.top = new FormAttachment(0, 200);
		getBestModelButton.setLayoutData(formData_1);
		getBestModelButton.setText("Indicate best model");
		
		Label lblModel = new Label(container, SWT.NONE);
		lblModel.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblModel = new FormData();
		fd_lblModel.top = new FormAttachment(tableCriterias, 80);
		fd_lblModel.left = new FormAttachment(0, 10);
		lblModel.setLayoutData(fd_lblModel);
		lblModel.setText("Model selected :");
		
		lblModelSelAll = new Label(container, SWT.NONE);
		lblModelSelAll.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NONE));
		FormData fd_lblSelModel = new FormData();
		fd_lblSelModel.right = new FormAttachment(44);
		fd_lblSelModel.top = new FormAttachment(lblModel, 0, SWT.TOP);
		fd_lblSelModel.left = new FormAttachment(lblModel, 50);
		lblModelSelAll.setLayoutData(fd_lblSelModel);
		lblModelSelAll.setText("");
		
		Label lblParametros = new Label(container, SWT.NONE);
		lblParametros.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblParametros = new FormData();
		fd_lblParametros.top = new FormAttachment(lblModel, 10);
		fd_lblParametros.left = new FormAttachment(0, 10);
		lblParametros.setLayoutData(fd_lblParametros);
		lblParametros.setText("Parameters estimated :");
		
		txtParametersEstimatedAll = new Label(container, SWT.NONE);
		txtParametersEstimatedAll.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_txtParametersEstimated = new FormData();
		fd_txtParametersEstimated.top = new FormAttachment(lblParametros, 0, SWT.TOP);
		fd_txtParametersEstimated.right = new FormAttachment(100);
		fd_txtParametersEstimated.left = new FormAttachment(lblModelSelAll, 0, SWT.LEFT);
		txtParametersEstimatedAll.setLayoutData(fd_txtParametersEstimated);
		
		Label lblStageSelected = new Label(container, SWT.NONE);
		lblStageSelected.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblStageSelected = new FormData();
		fd_lblStageSelected.right = new FormAttachment(lblSelectedAlgo, 0, SWT.RIGHT);
		fd_lblStageSelected.top = new FormAttachment(lblSelectedAlgo, 6);
		fd_lblStageSelected.left = new FormAttachment(lblSelectedAlgo, 0, SWT.LEFT);
		lblStageSelected.setLayoutData(fd_lblStageSelected);
		lblStageSelected.setText("     Stage selected :");
		
		txtStageselected = new Text(container, SWT.NONE);
		txtStageselected.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		txtStageselected.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_txtStageselected = new FormData();
		fd_txtStageselected.bottom = new FormAttachment(txtSelectedLifeStage, 24, SWT.BOTTOM);
		fd_txtStageselected.top = new FormAttachment(txtSelectedLifeStage, 6);
		fd_txtStageselected.right = new FormAttachment(txtSelectedLifeStage, 0, SWT.RIGHT);
		fd_txtStageselected.left = new FormAttachment(lblStageSelected, 6);
		txtStageselected.setLayoutData(fd_txtStageselected);
		
		getBestModelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				List<Double> vals = new ArrayList<Double>();
				for(int cols = 2; cols < tableCriterias.getColumnCount(); cols++){
					vals = new ArrayList<Double>();
					for(int rows = 0; rows < tableCriterias.getItemCount(); rows++){
						vals.add(Double.valueOf(tableCriterias.getItem(rows).getText(cols)).doubleValue());
					}
					int row=0;
					if(cols == 2 || cols == 3/* || cols == 5*/){
						row = MaizeDevUtils.MaxValueTable(vals);
					}
					if(cols == 4 || cols == 5 || cols == 6){
						row = MaizeDevUtils.MinValueTable(vals);
					}
					
					tableCriterias.getItem(row).setForeground(cols, Display.getCurrent().getSystemColor(SWT.COLOR_RED));
				}
			
			}
		});
	}
}
