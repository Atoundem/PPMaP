package ui;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import maizeTools.MaizeDevUtils;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;



public class CreatedMaizeProject extends Dialog {

	protected Object result;
	protected Shell shellMaizeDevRate;
	private Text txtTextprojetname;
	private Text textSpeciesName;
	private Text textAuthor;
	private Text textDate;
	private Text textStepFile;
	private Text textProjectLocation;
	
	
	File fileP;
	public static String strPathProject="";//"C:/Users/Ritter/Desktop/EPFA/totooo";
	private Text textDataFile;
	static RConnection c;
	
	static String saveToR="";
	
	public static String[] stageNames;//  new String[]{"Vegetative", "Maturity"};
	
	



	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CreatedMaizeProject(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shellMaizeDevRate.open();
		shellMaizeDevRate.layout();
		Display display = getParent().getDisplay();
		while (!shellMaizeDevRate.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shellMaizeDevRate = new Shell(getParent(), SWT.DIALOG_TRIM);
		shellMaizeDevRate.setSize(495, 376);
		shellMaizeDevRate.setText("V S Project");
		shellMaizeDevRate.setLayout(new FormLayout());
		
		Group grpProjectInfo = new Group(shellMaizeDevRate, SWT.BORDER);
		grpProjectInfo.setText("Project Info");
		GridLayout gl_grpProjectInfo = new GridLayout(2, false);
		gl_grpProjectInfo.horizontalSpacing = 6;
		gl_grpProjectInfo.verticalSpacing = 7;
		grpProjectInfo.setLayout(gl_grpProjectInfo);
		FormData fd_grpProjectInfo = new FormData();
		fd_grpProjectInfo.top = new FormAttachment(0, 10);
		fd_grpProjectInfo.left = new FormAttachment(0);
		fd_grpProjectInfo.right = new FormAttachment(100);
		grpProjectInfo.setLayoutData(fd_grpProjectInfo);
		
		Label lblProjectName = new Label(grpProjectInfo, SWT.NONE);
		lblProjectName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProjectName.setText("Project Name(*) : ");
		
		txtTextprojetname = new Text(grpProjectInfo, SWT.BORDER);
		txtTextprojetname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblSpeciesfungiName = new Label(grpProjectInfo, SWT.NONE);
		lblSpeciesfungiName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSpeciesfungiName.setText("Maize variety Name(*) :");
		
		textSpeciesName = new Text(grpProjectInfo, SWT.BORDER);
		textSpeciesName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblAuthor = new Label(grpProjectInfo, SWT.NONE);
		lblAuthor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAuthor.setText("Author :");
		
		textAuthor = new Text(grpProjectInfo, SWT.BORDER);
		textAuthor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDate = new Label(grpProjectInfo, SWT.NONE);
		lblDate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDate.setText("Date : ");
		
		textDate = new Text(grpProjectInfo, SWT.BORDER);
		textDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		textDate.setText(dateFormat.format(date));
		
		Button buttonStepFile = new Button(grpProjectInfo, SWT.NONE);
		buttonStepFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				FileDialog dialog = new FileDialog(shellMaizeDevRate, SWT.OPEN|SWT.SINGLE);
				dialog.setFilterExtensions(new String[]{"*.txt"});
				
				dialog.setFilterNames(new String[]{ "Text files (*.txt)"});
				dialog.open();
				
				String filePath = dialog.getFilterPath()+ File.separator+dialog.getFileName() ;
				textStepFile.setText(filePath); 
			}
		});
		GridData gd_buttonStepFile = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_buttonStepFile.widthHint = 132;
		buttonStepFile.setLayoutData(gd_buttonStepFile);
		buttonStepFile.setToolTipText("Load the file containing the beginning and the end of each step");
		buttonStepFile.setText("Phase File ...");
		
		textStepFile = new Text(grpProjectInfo, SWT.BORDER);
		textStepFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite = new Composite(shellMaizeDevRate, SWT.NONE);
		fd_grpProjectInfo.bottom = new FormAttachment(composite, -10);
		
		Button btnDataFile = new Button(grpProjectInfo, SWT.NONE);
		btnDataFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				FileDialog dialog = new FileDialog(shellMaizeDevRate, SWT.OPEN|SWT.SINGLE);
				dialog.setFilterExtensions(new String[]{"*.txt"});
				
				dialog.setFilterNames(new String[]{ "Text files (*.txt)"});
				dialog.open();
				
				String filePath = dialog.getFilterPath()+ File.separator+dialog.getFileName() ;
				textDataFile.setText(filePath); 
			}
		});
		GridData gd_btnDataFile = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnDataFile.widthHint = 129;
		btnDataFile.setLayoutData(gd_btnDataFile);
		btnDataFile.setText("Temperature File");
		
		textDataFile = new Text(grpProjectInfo, SWT.BORDER);
		textDataFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		
		
		Button btnLocation = new Button(grpProjectInfo, SWT.NONE);
		GridData gd_btnLocation = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnLocation.widthHint = 130;
		btnLocation.setLayoutData(gd_btnLocation);
		btnLocation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DirectoryDialog dialogTmax = new DirectoryDialog(shellMaizeDevRate);
				
				dialogTmax.setText("Proect location");
				dialogTmax.setMessage("Select the Location of the project");
				
				String pathDir = dialogTmax.open();
				
				if(pathDir !=null)
				{
					textProjectLocation.setText(pathDir);
					
				}
			}
		});
		btnLocation.setText("Project Location(*)");
		
		textProjectLocation = new Text(grpProjectInfo, SWT.BORDER);
		textProjectLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 289);
		fd_composite.bottom = new FormAttachment(100, -10);
		fd_composite.right = new FormAttachment(100, -10);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shellMaizeDevRate.dispose();
			}
		});
		btnCancel.setBounds(385, 10, 84, 25);
		btnCancel.setText("Cancel");
		
		Button btnFinish = new Button(composite, SWT.NONE);
		btnFinish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(txtTextprojetname.getText().equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shellMaizeDevRate, "Error", "You must enter the name of the project");
	    			return;
	    		}
				
				if(textSpeciesName.getText().equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shellMaizeDevRate, "Error", "You must enter the name of the Species studied!");
	    			return;
	    		}
				
				if(textProjectLocation.getText().equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shellMaizeDevRate, "Error", "Please Specify the location of the project!");
	    			return;
	    		}
				
				if(textStepFile.getText().equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shellMaizeDevRate, "Error", "Please Specify the file containing the stage of the speciess development!");
	    			return;
	    		}
				if(textDataFile.getText().equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shellMaizeDevRate, "Error", "Please Specify your Data file");
	    			return;
	    		}
				if(createProjectComponent())
				{
					MessageDialog.openInformation(new Shell(), "Create new Project", "Your project was successful created");
					shellMaizeDevRate.dispose();					
				}
					
				else
					MessageDialog.openInformation(new Shell(), "Create new Project", "Please!! Launch Rserve and try again!!");

			}
		});
		btnFinish.setBounds(289, 10, 90, 25);
		btnFinish.setText("Finish");

	}
	
	
	private boolean createProjectComponent() {
		
		boolean stateProjectCreation = false;
		fileP = new File(textProjectLocation.getText() + File.separator + txtTextprojetname.getText().trim());
		
		CreatedMaizeProject.setStrPathProject(new String(fileP.getAbsolutePath().replace('\\', '/')));
		//strPathProject = new String(fileP.getAbsolutePath().replace('\\', '/'));
		
		String fileStepPath = (textStepFile.getText()).replace('\\','/');
		String dataFilepPath = (textDataFile.getText()).replace('\\','/');
		
		try {
			c = new RConnection();
			c.eval("rm(list = ls())");
			
			System.out.println("connect");
			REXP x = c.eval("R.version.string");
			System.out.println(x.asString()); 
			
			
			//MaizeDevUtils.createTempScriptFile("/Scripts/maizeDevelopment.r");
			
			//MaizeDevUtils.createTempScriptFile("/Scripts/test.r");
			if(!fileP.exists()){
			fileP.mkdir();    
			
			MaizeDevUtils.createTempScriptFile("/Scripts/maizeDevelopment.r");
			
	        saveToR += "source('"+CreatedMaizeProject.getStrPathProject()+"tempScripFile.r'"+")"+"\r\n";	
	        c.eval("source('"+CreatedMaizeProject.getStrPathProject()+"tempScripFile.r'"+")");
	        System.out.println("charger");
	        
	        
			
	        saveToR += "datm <-TransformDAta('"+dataFilepPath+"','"+fileStepPath+"')" + "\r\n";
	        c.eval("datm <-TransformDAta('"+dataFilepPath+"','"+fileStepPath+"')");
	        
	        
	        saveToR += "stage = names(datm);for(i in 1:length(datm))write.table(datm[[i]],paste('"+CreatedMaizeProject.getStrPathProject()+"',stage[i],"+"\".txt\""+",sep=\""+"\"),row.names=FALSE)" + "\r\n";		
			c.eval("stage = names(datm);for(i in 1:length(datm))write.table(datm[[i]],paste('"+CreatedMaizeProject.getStrPathProject()+"',stage[i],"+"\".txt\""+",sep=\""+"\"),row.names=FALSE)");
			
			saveToR += "names(datm)" + "\r\n";
			stageNames = c.eval("stage <-names(datm)").asStrings();
			
		
			
			for (int j=0;j< stageNames.length;j++)
			{
				new File(fileP + File.separator + stageNames[j]).mkdir();
			}
				
				
//			File fileMortalityPath = new File(fileP + File.separator + "MaizeDevRate");
//			fileMortalityPath.mkdir();
//			new File(fileP + File.separator + "Mapping").mkdir();
			
//			String stg =new String(fileMortalityPath.getAbsolutePath().replace('\\', '/'));
//			MainPageWizardPage.setStrMortalityPath(stg);
//			System.out.println(MainPageWizardPage.getstrMortalityPath());
		}
		
			stateProjectCreation = true;
		c.close();
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageDialog.openError(shellMaizeDevRate, "Error", "Creation Failed! verify that rserve is running!");
			//return;
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stateProjectCreation;
	}
	
	private void crateFolders()
	{
		
		new File(fileP + File.separator + "MaizeDevRate").mkdir();
		new File(fileP + File.separator + "Mapping").mkdir();
		
	}
	
	public static String getStrPathProject() {
		return (strPathProject+File.separator).replace("\\","/");
	}

	public static void setStrPathProject(String strPathProject) {
		CreatedMaizeProject.strPathProject = strPathProject;
	}
	
	
	public static String[] getStageNames() {
		return stageNames;
	}


	public static void setStageNames(String[] stageNames) {
		CreatedMaizeProject.stageNames = stageNames;
	}
	
	
	
}
