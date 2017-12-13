package ui.modelBuilder;

//package org.cgiar.cip.ilcym.modelbuilder.mortality.wizards;

import java.awt.image.BufferedImage;
import java.io.File;

import maizeTools.ImageSelection;
import maizeTools.MaizeDevUtils;
import modelDesigner.ImageProperties;
//import modelDesigner.ModifyImageUI;
import modelDesigner.MaizeDevRate;
//import projects.clasesutils.IlcymUtils;
//import projects.clasesutils.ImageSelection;
import modelDesigner.ModifyImageUI;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class OneModelWizardPage2 extends WizardPage {

	public static Text txtSelectedLifeStage;
	public static Label lblModelSelOne, lblImageFinal;
	public static Browser brwModelSel;
	static Text txtStageselected;
	
	/**
	 * Create the wizard.
	 */
	public OneModelWizardPage2() {
		super("OneModelWizardPage2");
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
		lblSelectedAlgo.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblSelectedAlgo = new FormData();
		fd_lblSelectedAlgo.top = new FormAttachment(0, 10);
		lblSelectedAlgo.setLayoutData(fd_lblSelectedAlgo);
		lblSelectedAlgo.setText("Algorithm selected:");
		
		txtSelectedLifeStage = new Text(container, SWT.NONE);
		txtSelectedLifeStage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		txtSelectedLifeStage.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_txtSelectedProject1 = new FormData();
		fd_txtSelectedProject1.top = new FormAttachment(lblSelectedAlgo, 0, SWT.TOP);
		fd_txtSelectedProject1.left = new FormAttachment(lblSelectedAlgo, 6);
		fd_txtSelectedProject1.right = new FormAttachment(100, -221);
		txtSelectedLifeStage.setLayoutData(fd_txtSelectedProject1);
		
		Label lblModel = new Label(container, SWT.NONE);
		fd_lblSelectedAlgo.left = new FormAttachment(lblModel, 0, SWT.LEFT);
		lblModel.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblModel = new FormData();
		fd_lblModel.top = new FormAttachment(lblSelectedAlgo, 40);
		fd_lblModel.left = new FormAttachment(0, 10);
		lblModel.setLayoutData(fd_lblModel);
		lblModel.setText("Model selected :");
		
		lblModelSelOne = new Label(container, SWT.NONE);
		lblModelSelOne.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NONE));
		FormData fd_lblSelModel = new FormData();
		fd_lblSelModel.right = new FormAttachment(44);
		fd_lblSelModel.top = new FormAttachment(lblModel, 0, SWT.TOP);
		fd_lblSelModel.left = new FormAttachment(lblModel, 50);
		lblModelSelOne.setLayoutData(fd_lblSelModel);
		lblModelSelOne.setText("");
		
		lblImageFinal = new Label(container, SWT.BORDER | SWT.CENTER);
		lblImageFinal.setAlignment(SWT.CENTER);
		lblImageFinal.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblImageTemp = new FormData();
		fd_lblImageTemp.left = new FormAttachment(0,10);
		fd_lblImageTemp.right = new FormAttachment(50, -5);
		fd_lblImageTemp.top = new FormAttachment(lblModel, 10, SWT.BOTTOM);
		fd_lblImageTemp.bottom = new FormAttachment(100, -10);
		lblImageFinal.setLayoutData(fd_lblImageTemp);
		
		brwModelSel = new Browser(container, SWT.BORDER | SWT.CENTER);
		brwModelSel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_brwModelSel = new FormData();
		fd_brwModelSel.left = new FormAttachment(50,5);
		fd_brwModelSel.right = new FormAttachment(100, -5);
		fd_brwModelSel.top = new FormAttachment(lblModel, 10, SWT.BOTTOM);
		fd_brwModelSel.bottom = new FormAttachment(100, -10);
		brwModelSel.setLayoutData(fd_brwModelSel);
		
		final Menu menu = new Menu(lblImageFinal);
		lblImageFinal.setMenu(menu);
		
		final MenuItem copyMenuItem = new MenuItem(menu, SWT.NONE);
		copyMenuItem.setText("Copy");
		final MenuItem propImgMenuItem = new MenuItem(menu, SWT.NONE);
		propImgMenuItem.setText("Properties");
		final MenuItem restoreImgMenuItem = new MenuItem(menu, SWT.NONE);
		restoreImgMenuItem.setText("Restore");
		
		Label lblStageSelected = new Label(container, SWT.NONE);
		lblStageSelected.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_lblStageSelected = new FormData();
		fd_lblStageSelected.bottom = new FormAttachment(lblSelectedAlgo, 24, SWT.BOTTOM);
		fd_lblStageSelected.top = new FormAttachment(lblSelectedAlgo, 6);
		fd_lblStageSelected.right = new FormAttachment(lblSelectedAlgo, 0, SWT.RIGHT);
		fd_lblStageSelected.left = new FormAttachment(0, 10);
		lblStageSelected.setLayoutData(fd_lblStageSelected);
		lblStageSelected.setText("      Stage selected:");
		
		txtStageselected = new Text(container, SWT.NONE);
		txtStageselected.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		txtStageselected.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.NORMAL));
		FormData fd_txtStageselected = new FormData();
		fd_txtStageselected.right = new FormAttachment(txtSelectedLifeStage, 0, SWT.RIGHT);
		fd_txtStageselected.top = new FormAttachment(txtSelectedLifeStage, 6);
		fd_txtStageselected.left = new FormAttachment(txtSelectedLifeStage, 0, SWT.LEFT);
		txtStageselected.setLayoutData(fd_txtStageselected);
		
		propImgMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ModifyImageUI ui = new ModifyImageUI(container.getShell());
				ui.model = (MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0)));
				ImageProperties ip = ModifyImageUI.ip;
				ui.ModifyImageUIVar(MainPageWizardPage.getstrMortalityPath() + File.separator + MaizeDevRate.imageName, lblImageFinal,
						ip.getCorrX1(),ip.getCorrX2(),ip.getCorrY1(),ip.getCorrY2(),ip.getMini(),
						ip.getMaxi(), ip.getLabX(),ip.getLabY(),ip.getTitle(),ip.getLegX(),ip.getLegY());
				ui.open();
			}
		});
		restoreImgMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//MaizeDevRate.restoreImage(MainPageWizardPage.getStageSel(), MainPageWizardPage.getstrMortalityPath() + File.separator + MaizeDevRate.imageName, lblImageFinal, (MaizeDevRate.getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(0))));
			}
		});
		copyMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BufferedImage image = MaizeDevUtils.convertToAWT(new ImageData(MainPageWizardPage.getstrMortalityPath() + "/" + MaizeDevRate.imageName));
				ImageSelection.copyImageToClipboard(image);
			}
		});
	}

}
