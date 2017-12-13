package ui.modelBuilder;

import modelDesigner.ModifyImageUI;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import ui.CreatedMaizeProject;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

import rate.ImageProperties;



public class LoadPlotDataDialog extends Dialog {

	protected Object result;
	protected Shell shellPlotData;
	private Table table;
	public static Label lblImage;
	public static Combo comboStageDev;
	
	static RConnection c;
	static String saveToR="";
	private Text textAvTemp;
	private Text textDevRate;
	private Text textLocation;
	
	public static String miniX;
	public static String miniY;
	public static String maxiX;
	public static String maxiY;
	public static Button btnAdjustGraph;
	public static Button btnAdd;
	public static Menu menuTable;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public LoadPlotDataDialog(Shell parent, int style) {
		super(parent, style);
		setText("Load Plot Data");
	}

	public String getComboStageDev() {
		return comboStageDev.getText();
		//comboStageDev.getI
	}

	

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shellPlotData.open();
		shellPlotData.layout();
		Display display = getParent().getDisplay();
		while (!shellPlotData.isDisposed()) {
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
		shellPlotData = new Shell(getParent(), getStyle());
		shellPlotData.setSize(907, 625);
		shellPlotData.setText(getText());
		shellPlotData.setLayout(new FormLayout());
		
		Composite composite_1 = new Composite(shellPlotData, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		FormData fd_composite_1 = new FormData();
		fd_composite_1.top = new FormAttachment(0, 10);
		fd_composite_1.left = new FormAttachment(0, 10);
		fd_composite_1.right = new FormAttachment(100, -587);
		composite_1.setLayoutData(fd_composite_1);
		
		Composite composite_2 = new Composite(shellPlotData, SWT.NONE);
		FormData fd_composite_2 = new FormData();
		fd_composite_2.left = new FormAttachment(0, 10);
		fd_composite_2.right = new FormAttachment(100, -10);
		fd_composite_2.bottom = new FormAttachment(100, -10);
		composite_2.setLayoutData(fd_composite_2);
		
		Button btnCancel = new Button(composite_2, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shellPlotData.dispose();
				
			}
		});
		btnCancel.setBounds(779, 16, 92, 25);
		btnCancel.setText("Close");
		
		Button btnOk = new Button(composite_2, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(CreatedMaizeProject.getStrPathProject().equalsIgnoreCase("/"))
				{
	    			MessageDialog.openError(shellPlotData, "Error", "You must first created a new project before start simulation");
	    			shellPlotData.dispose();
	    		}
				
				try {
					c = new RConnection();
					System.out.println("connect");
					
					//--recuperer la valeur du compobos
					
					//--lire le ficher corespondant o combobox
					
					saveToR += "datm<-read.table(paste('"+CreatedMaizeProject.getStrPathProject()+"','"+comboStageDev.getText()+"',"+"\".txt\""+",sep=\""+"\"),header =T)" + "\r\n";
					c.eval("datm<-read.table(paste('"+CreatedMaizeProject.getStrPathProject()+"','"+comboStageDev.getText()+"',"+"\".txt\""+",sep=\""+"\"),header =T)");
					
					
					saveToR += "nrow(datm)" + "\r\n";	
					double nelt = c.eval("nrow(datm)").asDouble();
					System.out.println(nelt);
					
					table.removeAll();
				
					
					
					//lst.lastElement(); lst.firstElement();
					
					for(int i = 1;i<=nelt; i++)
					{
						TableItem ti = new TableItem(table, SWT.None);
						ti.setText(new String[]{i+"", c.eval("datm["+i+",1]").asString(), c.eval("datm["+i+",2]").asString(), c.eval("datm["+i+",3]").asString()});
						
					}	
					
					
					saveToR += "mini <- min(datm[,1])" + "\r\n";
					c.eval("mini <- min(datm[,1])");
					setMiniX(c.eval("mini").asString());
					
					
					saveToR += "maxi <- max(datm[,1])" + "\r\n";
					c.eval("maxi <- max(datm[,1])");
					setMaxiX(c.eval("maxi").asString());
					
					
					saveToR += "miniY <- min(datm[,2])" + "\r\n";
					c.eval("miniY <- min(datm[,2])");
					setMiniX(c.eval("miniY").asString());
					
					
					saveToR += "maxiY <- max(datm[,2])" + "\r\n";
					c.eval("maxiY <- max(datm[,2])");
					setMaxiX(c.eval("maxiY").asString());
					
					
					
					
					saveToR += "xRange <- c(mini,maxi)" + "\r\n";
					c.eval("xRange <- c(mini,maxi)");
										
					saveToR += "yRange <- c(miniY,maxiY)" + "\r\n";
					c.eval("yRange <- c(miniY,maxiY)");
					
					saveToR += "xLabel <-'Temperature(T)'" + "\r\n";
					c.eval("xLabel <-'Temperature(T)'");
					
					saveToR += "yLabel <-'Development Rate'" + "\r\n";
					c.eval("yLabel <-'Development Rate'");
					
					//String strImgOutput = MainPageWizardPage.getstrMortalityPath()+ "MortalityData.png";
					
					String strImgOutput = CreatedMaizeProject.getStrPathProject()+ comboStageDev.getText()+".png";
					
					c.eval("png(file=" + '"' +strImgOutput +'"'+", width = 480, height = 480)");
					//c.eval("plot(datm[,1],datm[,2]*100,frame=F,pch=19,col=4,cex=1.3,xlim=xRange,ylim=yRange,axes=F,xaxt = 'n',xlab=xLabel,ylab=yLabel,main=graphTitle);axis(1, xaxp=c(xRange,5));axis(2,las=2);");
					c.eval("plot(datm[,1],datm[,2],frame=T,pch=19,cex=1.3,col=4,xlab='Temperature(T)',ylab='Development Rate',main='Development Rate for Temperature',axes=F,xaxt = 'n',ylim=yRange,xlim=xRange);axis(1, xaxp=c(xRange,5));axis(2,las=2)");
					c.eval("dev.off()");
												
					Image imge = new Image(Display.getDefault(),CreatedMaizeProject.getStrPathProject()+"/"+comboStageDev.getText()+".png");
					lblImage.setImage(imge);
					
					btnAdd.setEnabled(true);
					btnAdjustGraph.setEnabled(true);
					c.close();
				} catch (RserveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					MessageDialog.openError(shellPlotData, "Error", "Impossible to connect! verify that rserve is running!");
				} catch (REXPMismatchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnOk.setBounds(641, 16, 123, 25);
		btnOk.setText("Load and Plot");
		
		lblImage = new Label(shellPlotData, SWT.NONE);
		fd_composite_2.top = new FormAttachment(lblImage, 6);
		lblImage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_lblImage = new FormData();
		fd_lblImage.top = new FormAttachment(0, 10);
		fd_lblImage.bottom = new FormAttachment(100, -67);
		fd_lblImage.left = new FormAttachment(composite_1, 13);
		fd_lblImage.right = new FormAttachment(100, -10);
	
		btnAdjustGraph = new Button(composite_2, SWT.NONE);
		btnAdjustGraph.setEnabled(false);
		btnAdjustGraph.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ModifyImage mdimg = new ModifyImage(shellPlotData, getStyle());
//				ModifyImageUI mdimg = new ModifyImageUI(shellPlotData, getStyle());
				mdimg.open();
			}
		});
		btnAdjustGraph.setToolTipText("Adjust the graph ");
		btnAdjustGraph.setBounds(497, 16, 117, 25);
		btnAdjustGraph.setText("Adjust graph");
		
		Label lblStageDisplay = new Label(composite_1, SWT.NONE);
		lblStageDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		FormData fd_lblStageDisplay = new FormData();
		fd_lblStageDisplay.top = new FormAttachment(0, 10);
		fd_lblStageDisplay.left = new FormAttachment(0, 10);
		lblStageDisplay.setLayoutData(fd_lblStageDisplay);
		lblStageDisplay.setText("Choose a Phase");
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		fd_lblStageDisplay.bottom = new FormAttachment(table, -13);
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.top = new FormAttachment(0, 46);
		fd_table.left = new FormAttachment(0, 10);
		fd_table.right = new FormAttachment(100);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(33);
		tblclmnId.setText("ID");
		
		TableColumn tblclmnTempc = new TableColumn(table, SWT.NONE);
		tblclmnTempc.setWidth(83);
		tblclmnTempc.setText("Av. Temp(\u00B0C)");
		
		TableColumn tblclmnDevRate = new TableColumn(table, SWT.NONE);
		tblclmnDevRate.setWidth(69);
		tblclmnDevRate.setText("Dev Rate");
		
		TableColumn tblclmnLocation = new TableColumn(table, SWT.NONE);
		tblclmnLocation.setWidth(119);
		tblclmnLocation.setText("Location");
		
		comboStageDev = new Combo(composite_1, SWT.NONE);
		fd_lblStageDisplay.right = new FormAttachment(comboStageDev, -30);
		FormData fd_comboStageDev = new FormData();
		fd_comboStageDev.top = new FormAttachment(0, 10);
		fd_comboStageDev.left = new FormAttachment(100, -160);
		fd_comboStageDev.right = new FormAttachment(100, -10);
		comboStageDev.setLayoutData(fd_comboStageDev);
//		comboStageDev.setItems(CreatedMaizeProject.getStageNames());
//		comboStageDev.setItems(new String[] {"Vegetative", "Maturity"});
		
		for(int i=0; i<CreatedMaizeProject.getStageNames().length; i++){
			comboStageDev.add(CreatedMaizeProject.getStageNames()[i]);
		}
		comboStageDev.select(0);
		
		lblImage.setLayoutData(fd_lblImage);
		lblImage.setText("Image");
		
		Group grpAddValue = new Group(shellPlotData, SWT.NONE);
		fd_composite_1.bottom = new FormAttachment(grpAddValue, -6);
		grpAddValue.setText("Add Value");
		grpAddValue.setLayout(new GridLayout(2, false));
		FormData fd_grpAddValue = new FormData();
		fd_grpAddValue.top = new FormAttachment(0, 357);
		fd_grpAddValue.bottom = new FormAttachment(composite_2, -6);
		fd_grpAddValue.right = new FormAttachment(composite_1, -7, SWT.RIGHT);
		fd_grpAddValue.left = new FormAttachment(0, 10);
		grpAddValue.setLayoutData(fd_grpAddValue);
		
		Label lblAvTemp = new Label(grpAddValue, SWT.NONE);
		lblAvTemp.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblAvTemp.setAlignment(SWT.CENTER);
		GridData gd_lblAvTemp = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblAvTemp.widthHint = 84;
		gd_lblAvTemp.heightHint = 20;
		lblAvTemp.setLayoutData(gd_lblAvTemp);
		lblAvTemp.setText("Av Temp:");
		
		textAvTemp = new Text(grpAddValue, SWT.BORDER);
		textAvTemp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDevRate = new Label(grpAddValue, SWT.NONE);
		lblDevRate.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblDevRate.setAlignment(SWT.CENTER);
		GridData gd_lblDevRate = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblDevRate.heightHint = 24;
		lblDevRate.setLayoutData(gd_lblDevRate);
		lblDevRate.setText("Dev Rate");
		
		textDevRate = new Text(grpAddValue, SWT.BORDER);
		textDevRate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblLocation = new Label(grpAddValue, SWT.NONE);
		lblLocation.setAlignment(SWT.CENTER);
		lblLocation.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		GridData gd_lblLocation = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblLocation.heightHint = 29;
		lblLocation.setLayoutData(gd_lblLocation);
		lblLocation.setText("Location");
		
		textLocation = new Text(grpAddValue, SWT.BORDER);
		textLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpAddValue, SWT.NONE);
		
		btnAdd = new Button(grpAddValue, SWT.NONE);
		btnAdd.setEnabled(false);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				
				String AvTemp = textAvTemp.getText();
				if( AvTemp.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shellPlotData, "Error", "Please enter the value of Average Temperature");
	    			return;
	    		}
				
				
				String DevRate = textDevRate.getText();
				if( DevRate.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shellPlotData, "Error", "Please enter the value of the Developmental rate");
	    			return;
	    		}
				
				String location = textLocation.getText();
				if( location.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shellPlotData, "Error", "Please enter the value of the location concerned");
	    			return;
	    		}
				
				
				
			try {
				c = new RConnection();
				System.out.println("connect");
				
				saveToR += "ncol(datm)" + "\r\n";	
				double nelt = c.eval("ncol(datm)").asDouble();
				
				for(int j = 1;j<=nelt; j++)
				{
					saveToR += "datm[,"+j+"] = as.character(datm[,"+j+"])" + "\r\n";
					c.eval("datm[,"+j+"] = as.character(datm[,"+j+"])");
				}
				
				saveToR += "datm <-rbind(datm,c(T ='"+AvTemp+"', StepDevelpRate='"+DevRate+"',Location='"+location+"', StepName='"+comboStageDev.getText()+"'))"+ "\r\n";
				c.eval("datm <-rbind(datm,c(T ='"+AvTemp+"', StepDevelpRate='"+DevRate+"',Location='"+location+"', StepName='"+comboStageDev.getText()+"'))");
				
				double nel = c.eval("nrow(datm)").asDouble();
				System.out.println(nel);
			//	reecriture du fichier
				saveToR += "write.table(datm,paste('"+CreatedMaizeProject.getStrPathProject()+"',"+comboStageDev.getText()+","+"\".txt\""+",sep=\""+"\"),row.names=FALSE)" + "\r\n";		
				c.eval("write.table(datm,paste('"+CreatedMaizeProject.getStrPathProject()+"','"+comboStageDev.getText()+"',"+"\".txt\""+",sep=\""+"\"),row.names=FALSE)");
				
				
				int i= table.getItemCount()+1;
				TableItem ti = new TableItem(table, SWT.None);
				ti.setText(new String[]{i+"",AvTemp , DevRate,location });				
				c.close();
				
			} catch (RserveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MessageDialog.openError(shellPlotData, "Error", "Problem when trying to add new values! verify that rserve is running!");
			} catch (REXPMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			textAvTemp.setText(""); textDevRate.setText(""); textLocation.setText("");
				
			}
		});
		GridData gd_btnAdd = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnAdd.widthHint = 98;
		btnAdd.setLayoutData(gd_btnAdd);
		btnAdd.setText("Add");
		
		menuTable = new Menu(table);
		table.setMenu(menuTable);
/*		
		MenuItem item = new MenuItem(menu, SWT.PUSH);
	    item.setText("Delete Selection");
	    item.addListener(SWT.Selection, new Listener() {
	      public void handleEvent(Event event) {
	        table.remove(table.getSelectionIndices());
			
						
	      }
	    });
	    
 */
		
		final MenuItem deleteMenuItem = new MenuItem(menuTable, SWT.NONE);
		deleteMenuItem.setText("Delete");
//		final MenuItem ModifyMenuItem = new MenuItem(menuTable, SWT.NONE);
//		ModifyMenuItem.setText("Modify");
		
		
		deleteMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				String tempString = "";
				
				System.out.println(table.getSelectionIndices());				
			//	System.out.println(table.getSelection()[0].getText());
				
				TableItem[] selection = table.getSelection();
				 System.out.println("selection long : "+selection.length);
				 for (int i = 0; i < selection.length; i++){
			          tempString += selection[i].getText() + " ";
			        System.out.println("Selection={" + tempString + "}");
			        System.out.println("Selection1={" + selection[i].getText());
			        System.out.println("Selection2={" + selection[i].getText(1));
				 }
		//        for (int i = 0; i < selection.length; i++)
		 //         String  temp = selection[1].toString();
		  //      System.out.println("Selection={" + temp + "}");
				
								
				
			       
				
				try {
					c = new RConnection();
					System.out.println("connect");

/*					
					saveToR += "ncol(datm)" + "\r\n";	
					double nelt = c.eval("ncol(datm)").asDouble();
					
					for(int j = 1;j<=nelt; j++)
					{
						saveToR += "datm[,"+j+"] = as.character(datm[,"+j+"])" + "\r\n";
						c.eval("datm[,"+j+"] = as.character(datm[,"+j+"])");
					}
					datm = datm[-which(datm$T==23.50),]
					
					saveToR += "datm <-rbind(datm,c(T ='"+AvTemp+"', StepDevelpRate='"+DevRate+"',Location='"+location+"', StepName='"+comboStageDev.getText()+"'))"+ "\r\n";
					c.eval("datm <-rbind(datm,c(T ='"+AvTemp+"', StepDevelpRate='"+DevRate+"',Location='"+location+"', StepName='"+comboStageDev.getText()+"'))");

*/		
					
					
					saveToR += "datm <-datm[-which(datm$T=="+ selection[0].getText(1) +"),]"+ "\r\n";
					c.eval("datm <-datm[-which(datm$T=="+ selection[0].getText(1) +"),]");
				
					double nel = c.eval("nrow(datm)").asDouble();
					System.out.println(nel);
				//	reecriture du fichier
					saveToR += "write.table(datm,paste('"+CreatedMaizeProject.getStrPathProject()+"',"+comboStageDev.getText()+","+"\".txt\""+",sep=\""+"\"),row.names=FALSE)" + "\r\n";		
					c.eval("write.table(datm,paste('"+CreatedMaizeProject.getStrPathProject()+"','"+comboStageDev.getText()+"',"+"\".txt\""+",sep=\""+"\"),row.names=FALSE)");
					
					
			//		int i= table.getItemCount()+1;
			//		TableItem ti = new TableItem(table, SWT.None);
			//		ti.setText(new String[]{i+"",AvTemp , DevRate,location });				
					c.close();
					
				} catch (RserveException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageDialog.openError(shellPlotData, "Error", "Problem when trying to add new values! verify that rserve is running!");
				} catch (REXPMismatchException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				 table.remove(table.getSelectionIndices());
				
			}
		});

	}

	public static String getMiniX() {
		return miniX;
	}

	public static void setMiniX(String miniX) {
		LoadPlotDataDialog.miniX = miniX;
	}

	public static String getMiniY() {
		return miniY;
	}

	public static void setMiniY(String miniY) {
		LoadPlotDataDialog.miniY = miniY;
	}

	public static String getMaxiX() {
		return maxiX;
	}

	public static void setMaxiX(String maxiX) {
		LoadPlotDataDialog.maxiX = maxiX;
	}

	public static String getMaxiY() {
		return maxiY;
	}

	public static void setMaxiY(String maxiY) {
		LoadPlotDataDialog.maxiY = maxiY;
	}
}
