package ui.mapping;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import maizeTools.MaizeDevUtils;
import ui.CreatedMaizeProject;
import ui.modelBuilder.MainPageWizardPage;

import org.eclipse.swt.widgets.Combo;
/*
import configTool.MaizeDevUtils;
import ui.modeling.MainPageWizardPage;
*/
public class MappingDialog extends Dialog {

	protected Object result;
	protected Shell shlEpfMapping;
	private Text textMinTemp;
	private Text textMaxTemp;
	private Text textCordMinX;
	private Text textCordMaxX;
	private Text textcordMinY;
	private Text textCordMaxY;
	private Text textNbSubArea;
	private Text txtOutputfile;
	private Text textFilterMinT;
	private Text textFilterMaxT;
	public static Label lblStatus,lblFrequence;
	public static Label lblDisplayMap;
	static String saveToR="";
	static RConnection c;
	static Combo comboDevStage, comboPeriodicity;
	public static Text txtPeriodicity;	
	public static Button btnPeriodicity;
	
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MappingDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlEpfMapping.open();
		shlEpfMapping.layout();
		Display display = getParent().getDisplay();
		while (!shlEpfMapping.isDisposed()) {
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
		shlEpfMapping = new Shell(getParent(), getStyle());
		shlEpfMapping.setSize(1055, 668);
		shlEpfMapping.setText("V S Mapping");
		shlEpfMapping.setLayout(new FormLayout());
		
		Composite composite = new Composite(shlEpfMapping, SWT.BORDER);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.bottom = new FormAttachment(100, -51);
		fd_composite.right = new FormAttachment(0, 510);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new FormLayout());
		
		Group groupInputTemp = new Group(composite, SWT.NONE);
		groupInputTemp.setFont(SWTResourceManager.getFont("Times New Roman CE", 10, SWT.BOLD | SWT.ITALIC));
		groupInputTemp.setText("Climatic Data");
		groupInputTemp.setLayout(new GridLayout(2, false));
		FormData fd_groupInputTemp = new FormData();
		fd_groupInputTemp.top = new FormAttachment(0, 10);
		fd_groupInputTemp.left = new FormAttachment(0, 10);
		fd_groupInputTemp.bottom = new FormAttachment(0, 101);
		fd_groupInputTemp.right = new FormAttachment(0, 490);
		groupInputTemp.setLayoutData(fd_groupInputTemp);
		
		textMinTemp = new Text(groupInputTemp, SWT.BORDER);
		textMinTemp.setText("Select folder containing minimun Temperature");
		textMinTemp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnMinTemp = new Button(groupInputTemp, SWT.NONE);
		btnMinTemp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DirectoryDialog dialogTmin = new DirectoryDialog(shlEpfMapping);
				
				dialogTmin.setText("Min Temperature");
				dialogTmin.setMessage("Select the directory containing Minimun temperature");
				
				String pathDir = dialogTmin.open();
				
				if(pathDir !=null)
				{
					textMinTemp.setText(pathDir);
					String[] locExtent = GenerateMap.getLocationExtent(textMinTemp.getText());
					
					textCordMinX.setText(locExtent[0]);
					textCordMaxX.setText(locExtent[1]);
					textcordMinY.setText(locExtent[2]);
					textCordMaxY.setText(locExtent[3]);
					
					
				}
				
			}
		});
		btnMinTemp.setText("Temp min");
		
		textMaxTemp = new Text(groupInputTemp, SWT.BORDER);
		textMaxTemp.setText("Select folder containing Maximun temperature");
		textMaxTemp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnMaxTemp = new Button(groupInputTemp, SWT.NONE);
		btnMaxTemp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DirectoryDialog dialogTmax = new DirectoryDialog(shlEpfMapping);
				
				dialogTmax.setText("Max Temperature");
				dialogTmax.setMessage("Select the directory containing Maximun temperature");
				
				String pathDir = dialogTmax.open();
				
				if(pathDir !=null)
				{
					textMaxTemp.setText(pathDir);
					
				}
				
			}
		});
		btnMaxTemp.setText("Temp max");
		
		Group grpCoordinates = new Group(composite, SWT.NONE);
		grpCoordinates.setFont(SWTResourceManager.getFont("Times New Roman CE", 10, SWT.BOLD | SWT.ITALIC));
		grpCoordinates.setText("Coordinate");
		grpCoordinates.setLayout(new GridLayout(6, false));
		FormData fd_grpCoordinates = new FormData();
		fd_grpCoordinates.top = new FormAttachment(groupInputTemp);
		fd_grpCoordinates.left = new FormAttachment(0, 10);
		fd_grpCoordinates.right = new FormAttachment(100, -6);
		grpCoordinates.setLayoutData(fd_grpCoordinates);
		
		Label lblNewLabel = new Label(grpCoordinates, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Times New Roman Baltic", 9, SWT.BOLD | SWT.ITALIC));
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 62;
		gd_lblNewLabel.heightHint = 24;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("MinLong");
		
		textCordMinX = new Text(grpCoordinates, SWT.BORDER);
		GridData gd_textCordMinX = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textCordMinX.widthHint = 43;
		textCordMinX.setLayoutData(gd_textCordMinX);
		
		Label lblNewLabel_1 = new Label(grpCoordinates, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Times New Roman Baltic", 9, SWT.BOLD | SWT.ITALIC));
		GridData gd_lblNewLabel_1 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_1.widthHint = 59;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setText("MinLat");
		
		textcordMinY = new Text(grpCoordinates, SWT.BORDER);
		GridData gd_textcordMinY = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textcordMinY.widthHint = 44;
		textcordMinY.setLayoutData(gd_textcordMinY);
		
		Button btnNewButton = new Button(grpCoordinates, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 92;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("from Layer");
		
		Button btnNewButton_1 = new Button(grpCoordinates, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton_1.setText("Get Rectangle");
		
		Label lblMaxx = new Label(grpCoordinates, SWT.NONE);
		GridData gd_lblMaxx = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblMaxx.widthHint = 68;
		lblMaxx.setLayoutData(gd_lblMaxx);
		lblMaxx.setFont(SWTResourceManager.getFont("Times New Roman Baltic", 9, SWT.BOLD | SWT.ITALIC));
		lblMaxx.setText("MaxLong");
		
		textCordMaxX = new Text(grpCoordinates, SWT.BORDER);
		GridData gd_textCordMaxX = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textCordMaxX.widthHint = 42;
		textCordMaxX.setLayoutData(gd_textCordMaxX);
		
		Label lblNewLabel_2 = new Label(grpCoordinates, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Times New Roman Baltic", 9, SWT.BOLD | SWT.ITALIC));
		GridData gd_lblNewLabel_2 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_2.widthHint = 58;
		lblNewLabel_2.setLayoutData(gd_lblNewLabel_2);
		lblNewLabel_2.setText("MaxLat");
		
		textCordMaxY = new Text(grpCoordinates, SWT.BORDER);
		GridData gd_textCordMaxY = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textCordMaxY.widthHint = 48;
		textCordMaxY.setLayoutData(gd_textCordMaxY);
		
		Button btnNewButton_2 = new Button(grpCoordinates, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(textMinTemp.getText().equalsIgnoreCase("Select folder containing minimun Temperature") || textMinTemp.getText().equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shlEpfMapping, "Error", "You must select the Min temperature path");
	    			return;
	    		}else{
				String[] locExtent = GenerateMap.getLocationExtent(textMinTemp.getText());
				
				textCordMinX.setText(locExtent[0]);
				textCordMaxX.setText(locExtent[1]);
				textcordMinY.setText(locExtent[2]);
				textCordMaxY.setText(locExtent[3]);
	    		}
				
			}
		});
		GridData gd_btnNewButton_2 = new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 1);
		gd_btnNewButton_2.widthHint = 88;
		btnNewButton_2.setLayoutData(gd_btnNewButton_2);
		btnNewButton_2.setText("from Data ");
		
		Button btnNewButton_3 = new Button(grpCoordinates, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				textCordMinX.setText(-180+"");
				textCordMaxX.setText(180+"");
				textcordMinY.setText(-90+"");
				textCordMaxY.setText(90+"");
			}
		});
		btnNewButton_3.setText("Maximum Extend");
		
		Group grpTempname = new Group(composite, SWT.NONE);
		fd_grpCoordinates.bottom = new FormAttachment(grpTempname, -18);
		grpTempname.setFont(SWTResourceManager.getFont("Times New Roman CE", 10, SWT.BOLD | SWT.ITALIC));
		grpTempname.setLayout(new FormLayout());
		FormData fd_grpTempname = new FormData();
		fd_grpTempname.top = new FormAttachment(0, 216);
		fd_grpTempname.right = new FormAttachment(100, -10);
		fd_grpTempname.left = new FormAttachment(0, 16);
		grpTempname.setLayoutData(fd_grpTempname);
		
		Group grpOutput = new Group(composite, SWT.NONE);
		fd_grpTempname.bottom = new FormAttachment(grpOutput, -9);
		grpOutput.setFont(SWTResourceManager.getFont("Times New Roman CE", 10, SWT.BOLD | SWT.ITALIC));
		grpOutput.setText("Output");
		grpOutput.setLayout(new GridLayout(2, false));
		FormData fd_grpOutput = new FormData();
		fd_grpOutput.left = new FormAttachment(0, 10);
		fd_grpOutput.right = new FormAttachment(100, -6);
		fd_grpOutput.top = new FormAttachment(0, 294);
		
		Button btnTemperatureFilter = new Button(grpTempname, SWT.CHECK);
		btnTemperatureFilter.setSelection(true);
		btnTemperatureFilter.setTouchEnabled(true);
		btnTemperatureFilter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				/*if(btnTemperatureFilter.getSelection()) 
					grpTempname.setEnabled(true);
					//btnTemperatureFilter.setEnabled(true);
					//btnTemperatureFilter.setSelection(false);
				else
					grpTempname.setEnabled(false);
					//btnTemperatureFilter.setEnabled(false);*/
			}
		});
		FormData fd_btnTemperatureFilter = new FormData();
		fd_btnTemperatureFilter.left = new FormAttachment(0, 7);
		fd_btnTemperatureFilter.right = new FormAttachment(0, 152);
		fd_btnTemperatureFilter.top = new FormAttachment(0, -16);
		btnTemperatureFilter.setLayoutData(fd_btnTemperatureFilter);
		btnTemperatureFilter.setFont(SWTResourceManager.getFont("Times New Roman CE", 10, SWT.BOLD | SWT.ITALIC));
		btnTemperatureFilter.setText("Temperature filter");
//		btnTemperatureFilter.setSelection(true);
		
		Label lblTmin = new Label(grpTempname, SWT.NONE);
		lblTmin.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		FormData fd_lblTmin = new FormData();
		fd_lblTmin.top = new FormAttachment(0, 13);
		fd_lblTmin.left = new FormAttachment(0, 7);
		lblTmin.setLayoutData(fd_lblTmin);
		lblTmin.setText("Tmin : ");
		
		textFilterMinT = new Text(grpTempname, SWT.BORDER);
		fd_btnTemperatureFilter.bottom = new FormAttachment(textFilterMinT, -6);
		textFilterMinT.setText("0");
		FormData fd_textFilterMinT = new FormData();
		fd_textFilterMinT.left = new FormAttachment(lblTmin, 5);
		fd_textFilterMinT.bottom = new FormAttachment(lblTmin, 0, SWT.BOTTOM);
		textFilterMinT.setLayoutData(fd_textFilterMinT);
		
		Label lblTmax = new Label(grpTempname, SWT.NONE);
		fd_textFilterMinT.right = new FormAttachment(lblTmax, -22);
		lblTmax.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		FormData fd_lblTmax = new FormData();
		fd_lblTmax.top = new FormAttachment(textFilterMinT, 0, SWT.TOP);
		fd_lblTmax.left = new FormAttachment(0, 174);
		lblTmax.setLayoutData(fd_lblTmax);
		lblTmax.setText("Tmax : ");
		
		textFilterMaxT = new Text(grpTempname, SWT.BORDER);
		textFilterMaxT.setText("40");
		FormData fd_textFilterMaxT = new FormData();
		fd_textFilterMaxT.right = new FormAttachment(lblTmax, 105, SWT.RIGHT);
		fd_textFilterMaxT.bottom = new FormAttachment(lblTmin, 0, SWT.BOTTOM);
		fd_textFilterMaxT.left = new FormAttachment(lblTmax, 6);
		textFilterMaxT.setLayoutData(fd_textFilterMaxT);
		grpOutput.setLayoutData(fd_grpOutput);
		
		Label lblSubAreasOf = new Label(grpOutput, SWT.NONE);
		lblSubAreasOf.setFont(SWTResourceManager.getFont("Times New Roman Baltic", 9, SWT.BOLD | SWT.ITALIC));
		lblSubAreasOf.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblSubAreasOf.setText("Sub areas of the map");
		
		textNbSubArea = new Text(grpOutput, SWT.BORDER);
		textNbSubArea.setText("4");
		textNbSubArea.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Button btnOutputFile = new Button(grpOutput, SWT.NONE);
		btnOutputFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialog = new FileDialog(shlEpfMapping, SWT.SAVE);
				dialog.setFilterExtensions(new String[]{"*.asc"});
				dialog.setFilterNames(new String[]{"ASC file (*.asc)"});
				dialog.open();
				
				txtOutputfile.setText("");
				File fTemp = new File(dialog.getFilterPath() + File.separator + dialog.getFileName());
				boolean bol = false;
				if(fTemp.exists())
					bol = MessageDialog.openConfirm(shlEpfMapping, "Mapping", dialog.getFileName() + " alredy exists. Do you want to replace it?");
				
				if(!bol)
					txtOutputfile.setText(fTemp.getPath());
				
			}
		});
		btnOutputFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnOutputFile.setText("Output File");
		
		txtOutputfile = new Text(grpOutput, SWT.BORDER);
		txtOutputfile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		fd_grpOutput.bottom = new FormAttachment(btnCancel, -63);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlEpfMapping.dispose();
			}
		});
		FormData fd_btnCancel = new FormData();
		fd_btnCancel.bottom = new FormAttachment(100, -22);
		fd_btnCancel.right = new FormAttachment(100, -6);
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText("Cancel");
		
		Button btnCalculate = new Button(composite, SWT.NONE);
		btnCalculate.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		fd_btnCancel.left = new FormAttachment(0, 423);
		btnCalculate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				
				// we collect input parameters
				String strOutput = (txtOutputfile.getText()).replace("\\","/");
				
//				String strOutput = "D:/TestSoftware/output/resSimul.asc";
				if(strOutput.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shlEpfMapping, "Error", "You must select the output file path");
	    			return;
	    		}
				
				String minTemptPath = (textMinTemp.getText()).replace("\\","/");
//				String minTemptPath = "D:/Icipe/MoukamRcode/datos mundo - 2000 - 10 mints/Tmin";
				if(minTemptPath.equalsIgnoreCase("Select folder containing minimun Temperature") ||minTemptPath.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shlEpfMapping, "Error", "You must select the Min temperature path");
	    			return;
	    		}
				
				String maxTempPath  = (textMaxTemp.getText()).replace("\\","/");
//				String maxTempPath  ="D:/Icipe/MoukamRcode/datos mundo - 2000 - 10 mints/Tmax";

				if(maxTempPath.equalsIgnoreCase("Select folder containing Maximun temperature") || maxTempPath.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shlEpfMapping, "Error", "You must to select the Max temperature path");
	    			return;
	    		}
				
				String strPath = new File(strOutput).getParent().replace("\\", "/");
		    	String strName = MaizeDevUtils.ExtractPath(new File(strOutput).getName());
				
				Boolean rbFilter =  btnTemperatureFilter.getSelection();
				
				String tmin = textFilterMinT.getText();
				if(rbFilter == true && tmin.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shlEpfMapping, "Error", "You must enter value of the Min temperature frilter");
	    			return;
	    		}
				
				
				String tmax = textFilterMaxT.getText();
				if(rbFilter == true && tmax.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shlEpfMapping, "Error", "You must enter value of the Max temperature frilter");
	    			return;
	    		}
				
				
				String season ="";
				if(btnPeriodicity.getSelection())
				{
					
					if(txtPeriodicity.getText().equalsIgnoreCase(""))
					{
		    			MessageDialog.openError(shlEpfMapping, "Error", "You must enter values of the targeted period");
		    			return;
		    		}else{
		    			season = txtPeriodicity.getText();
		    			
		    		}
				}
				
				
//				System.out.println(btnLimitDifference.getSelection());
				System.out.println(textFilterMinT.getText());
				
				minTemptPath = minTemptPath +"/";
				maxTempPath = maxTempPath +"/";
				String nbrSubArea =  textNbSubArea.getText();
				
				String dbMinX = textCordMinX.getText();
				String dbMaxX = textCordMaxX.getText();
				String dbMinY = textcordMinY.getText();
				String dbMaxY =	textCordMaxY.getText();
				
				if(dbMinX.equalsIgnoreCase("") ||dbMaxX.equalsIgnoreCase("")|| dbMinY.equalsIgnoreCase("") ||dbMaxY.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shlEpfMapping, "Error", "You must specified coordinated value of MinX,MinY,MaxX and MaxY");
	    			return;
	    		}
				if(btnCalculate.getText().equalsIgnoreCase("  Calculate  "))
				{
				
					System.out.println("start calculate");	
					lblDisplayMap.setImage(null);	
					//lblStatus.setText(lblStatus.getText()+ "In process...Please wait");
					lblStatus.setText( "In process...Please wait");
					GenerateMap.calculateIndices( nbrSubArea, minTemptPath, maxTempPath, dbMinX, dbMaxX, dbMinY, dbMaxY, strOutput, true, tmin, tmax,season);
					
					if(GenerateMap.isStateMapCreation())
					{
						lblStatus.setText("Status : PROCESS END!!!");
						btnCalculate.setText("Plot Map");
						MessageDialog.openInformation(shlEpfMapping, "Infos", "Process Mapp Creation Successful!");
						//openError(shlEpfMapping, "Error", "You must specified coordinated value of MinX,MinY,MaxX and MaxY");
		    			return;
					}
						
					else
					{
						
						lblStatus.setText("Status :ERROR! Unable to create Map. Have you Model selected Phase? ");
						MessageDialog.openError(shlEpfMapping, "Error", "Unable to create Map. Have you Model selected Phase?");
		    			return;
					}
						
					
					
	//				GenerateMap.calculateIndices(strR, pathTempMin, pathTempMax, dbMinX, dbMaxX, dbMinY, dbMaxY, strOutput, rbFilter, tmin, tmax);
				}
				else if((btnCalculate.getText()).equalsIgnoreCase("Plot Map"))
				{					
					
			    	Image imge = new Image(Display.getDefault(),strPath+"/"+strName+".png");
					lblDisplayMap.setImage(imge);
					btnCalculate.setText("  Calculate  ");
					GenerateMap.setStateMapCreation(false);

				}
				else if ((btnCalculate.getText()).equalsIgnoreCase("Close"))
					shlEpfMapping.dispose();					
							
			}
		});		FormData fd_btnCalculate = new FormData();
		fd_btnCalculate.top = new FormAttachment(btnCancel, 0, SWT.TOP);
		fd_btnCalculate.right = new FormAttachment(btnCancel, -8);
		fd_btnCalculate.left = new FormAttachment(0, 319);
		
		comboDevStage = new Combo(grpOutput, SWT.READ_ONLY);
		comboDevStage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		//comboDevStage.setItems(new String[]{"Vegetative", "Maturity"}); // just for the test
				
				for(int i=0; i<CreatedMaizeProject.getStageNames().length; i++){
					comboDevStage.add(CreatedMaizeProject.getStageNames()[i]);
				}
				
//		The real block for initialiszation
		
		comboDevStage.select(0);
		
		
		new Label(grpOutput, SWT.NONE);
		btnCalculate.setLayoutData(fd_btnCalculate);
		btnCalculate.setText("  Calculate  ");
		
		Composite composite_1 = new Composite(shlEpfMapping, SWT.BORDER);
		composite_1.setLayout(new FormLayout());
		FormData fd_composite_1 = new FormData();
		fd_composite_1.top = new FormAttachment(composite, 6);
		fd_composite_1.bottom = new FormAttachment(100, -10);
		fd_composite_1.left = new FormAttachment(0, 10);
		composite_1.setLayoutData(fd_composite_1);
		
		lblStatus = new Label(composite_1, SWT.NONE);
		lblStatus.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.BOLD));
		FormData fd_lblStatus = new FormData();
		fd_lblStatus.bottom = new FormAttachment(0, 25);
		fd_lblStatus.right = new FormAttachment(100, -10);
		fd_lblStatus.left = new FormAttachment(0);
		fd_lblStatus.top = new FormAttachment(0);
		lblStatus.setLayoutData(fd_lblStatus);
		lblStatus.setText("Status :");
		
		lblDisplayMap = new Label(shlEpfMapping, SWT.BORDER);
		fd_composite_1.right = new FormAttachment(lblDisplayMap, -14);
		lblDisplayMap.setAlignment(SWT.CENTER);
		FormData fd_lblDisplayMap = new FormData();
		fd_lblDisplayMap.left = new FormAttachment(composite, 14);
		
		Group groupPeriodicity = new Group(composite, SWT.NONE);
		groupPeriodicity.setTouchEnabled(true);
		groupPeriodicity.setLayout(new FormLayout());
		FormData fd_groupPeriodicity = new FormData();
		fd_groupPeriodicity.right = new FormAttachment(btnCalculate, -38);
		fd_groupPeriodicity.bottom = new FormAttachment(grpOutput, 105, SWT.BOTTOM);
		fd_groupPeriodicity.top = new FormAttachment(grpOutput, 6);
		fd_groupPeriodicity.left = new FormAttachment(0, 10);
		groupPeriodicity.setLayoutData(fd_groupPeriodicity);
		
		btnPeriodicity = new Button(groupPeriodicity, SWT.CHECK);
		btnPeriodicity.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(btnPeriodicity.getSelection())
				{
					comboPeriodicity.setEnabled(true);
					lblFrequence.setEnabled(true);
					txtPeriodicity.setEnabled(true);
					
				}else{
					comboPeriodicity.setEnabled(false);
					lblFrequence.setEnabled(false);
					txtPeriodicity.setEnabled(false);
					
				}
			}
		});
		btnPeriodicity.setFont(SWTResourceManager.getFont("Times New Roman Baltic", 9, SWT.BOLD | SWT.ITALIC));
		FormData fd_btnPeriodicity = new FormData();
		fd_btnPeriodicity.right = new FormAttachment(0, 118);
		fd_btnPeriodicity.top = new FormAttachment(0, -20);
		fd_btnPeriodicity.left = new FormAttachment(0, 7);
		btnPeriodicity.setLayoutData(fd_btnPeriodicity);
		btnPeriodicity.setText("Periodicity");
		
		comboPeriodicity = new Combo(groupPeriodicity, SWT.READ_ONLY);
		comboPeriodicity.setEnabled(false);
		comboPeriodicity.setFont(SWTResourceManager.getFont("Times New Roman Baltic", 10, SWT.BOLD | SWT.ITALIC));
		comboPeriodicity.setItems(new String[] {"Monthly", "Weekly"});
		FormData fd_comboPeriodicity = new FormData();
		fd_comboPeriodicity.left = new FormAttachment(100, -141);
		fd_comboPeriodicity.top = new FormAttachment(0, 14);
		fd_comboPeriodicity.right = new FormAttachment(100, -40);
		comboPeriodicity.setLayoutData(fd_comboPeriodicity);
		comboPeriodicity.select(0);
		
		lblFrequence = new Label(groupPeriodicity, SWT.NONE);
		lblFrequence.setEnabled(false);
		lblFrequence.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.NORMAL));
		FormData fd_lblFrequence = new FormData();
		fd_lblFrequence.top = new FormAttachment(btnPeriodicity, 16);
		fd_lblFrequence.right = new FormAttachment(btnPeriodicity, -13, SWT.RIGHT);
		fd_lblFrequence.left = new FormAttachment(btnPeriodicity, 0, SWT.LEFT);
		lblFrequence.setLayoutData(fd_lblFrequence);
		lblFrequence.setText("Frequency\r\n");
		
		txtPeriodicity = new Text(groupPeriodicity, SWT.BORDER);
		txtPeriodicity.setEnabled(false);
		FormData fd_txtPeriodicity = new FormData();
		fd_txtPeriodicity.right = new FormAttachment(0, 261);
		fd_txtPeriodicity.bottom = new FormAttachment(100, -5);
		fd_txtPeriodicity.left = new FormAttachment(0, 10);
		txtPeriodicity.setLayoutData(fd_txtPeriodicity);
		fd_lblDisplayMap.right = new FormAttachment(100, -10);
		fd_lblDisplayMap.top = new FormAttachment(0, 10);
		fd_lblDisplayMap.bottom = new FormAttachment(100, -10);
		lblDisplayMap.setLayoutData(fd_lblDisplayMap);
		lblDisplayMap.setText("Final Map here");
		
		Label label = new Label(shlEpfMapping, SWT.SEPARATOR | SWT.VERTICAL);
		FormData fd_label = new FormData();
		fd_label.left = new FormAttachment(0, 508);
		fd_label.right = new FormAttachment(lblDisplayMap, -14);
		fd_label.top = new FormAttachment(100, -407);
		fd_label.bottom = new FormAttachment(100, -65);
		label.setLayoutData(fd_label);
		
		

	}
}
