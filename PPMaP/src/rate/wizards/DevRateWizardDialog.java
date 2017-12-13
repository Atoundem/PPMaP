package rate.wizards;

import rate.DevelopmentRate;
import org.eclipse.jface.wizard.Wizard;

public class DevRateWizardDialog extends Wizard {

	public DevRateWizardDialog() {
		setWindowTitle("Development Rate");
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
		if(DevelopmentRate.saveModelSelected())
			return true;
		else
			return false;
	}

	@Override
	public boolean canFinish() {
		return true;
	}
}
