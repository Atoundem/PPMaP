package ui.modelBuilder;

//package org.cgiar.cip.ilcym.modelbuilder.mortality.wizards;

import modelDesigner.MaizeDevRate;

import org.eclipse.jface.wizard.Wizard;

public class MaizeDevRateWizardDialog extends Wizard {

	public MaizeDevRateWizardDialog() {
		setWindowTitle("MaizeDevRate");
	}

	@Override
	public void addPages() {
		addPage(new MainPageWizardPage());
		addPage(new SeveralModelsWizardPage());
		addPage(new OneModelWizardPage1());
		addPage(new OneModelWizardPage2());
		addPage(new SelectedModelWizardPage());
	}

	@Override
	public boolean performFinish() {
		if(MaizeDevRate.saveModelSelected())
			return true;
		else
			return false;
	}

	@Override
	public boolean canFinish() {
		return true;
	}

	
}
