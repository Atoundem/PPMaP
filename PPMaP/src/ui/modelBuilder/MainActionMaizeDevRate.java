package ui.modelBuilder;

//package org.cgiar.cip.ilcym.modelbuilder.mortality;

import java.util.ArrayList;
import java.util.List;

import maizeTools.ViewProjectsUI;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
//import ui.modeling.MainPageWizardPage;
//import ui.modeling.MortalityWizardDialog;
//import configTool.ArrayConvertions;
//import configTool.ViewProjectsUI;
import maizeTools.ArrayConvertions;

public class MainActionMaizeDevRate {

	//public static String nameProject, pathProj; // original initialisation of the variables
	public static String nameProject;
	public static String pathProj;// = "D:/TestSoftware/output"; // Define just for the need of testing
	public static List<String> arrayStages=new ArrayList<String>();
	
	public static void launchWizard()
	{
		if(ViewProjectsUI.nameProject == null){
			MessageDialog.openError(new Shell(), "Error", "You must select a project");
			return;
		}else{
			nameProject = ViewProjectsUI.nameProject;
			pathProj = ViewProjectsUI.pathProject.replace('\\', '/');
		}
		
		WizardDialog w = new WizardDialog(new Shell(), new MaizeDevRateWizardDialog());
		w.setPageSize(900, 555);
		w.create();
		MainPageWizardPage.txtSelectedProject.setText(nameProject);
		String[] stages = ArrayConvertions.StringtoArray(ViewProjectsUI.getLifeStages()[0],",");
		
		arrayStages=new ArrayList<String>();
		for(int i=0;i<stages.length;i++){
			arrayStages.add(stages[i]);
			MainPageWizardPage.stageArrayButtons[i].setText(stages[i].trim());
			MainPageWizardPage.stageArrayButtons[i].setVisible(true);
        }
		w.open();
	}
}

