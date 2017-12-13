package ui.modelBuilder;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import ui.CreatedMaizeProject;
import org.eclipse.wb.swt.SWTResourceManager;

public class ModifyImage extends Dialog {

	protected Object result;
	protected Shell shlModifyImage;
	private Text textMinX;
	private Text textMinY;
	private Text textMaxY;
	private Text textMaxX;
	
	static RConnection c;
	static String saveToR="";
	private Text txtXlabel;
	private Text txtYlabel;
	private Text txtGraphtitle;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ModifyImage(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlModifyImage.open();
		shlModifyImage.layout();
		Display display = getParent().getDisplay();
		while (!shlModifyImage.isDisposed()) {
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
		shlModifyImage = new Shell(getParent(), getStyle());
		shlModifyImage.setSize(446, 298);
		shlModifyImage.setText("Modify Image");
		shlModifyImage.setLayout(new FormLayout());
		
		Composite composite = new Composite(shlModifyImage, SWT.BORDER);
		composite.setLayout(new GridLayout(4, false));
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.right = new FormAttachment(100, -10);
		composite.setLayoutData(fd_composite);
		
		Composite composite_1 = new Composite(shlModifyImage, SWT.NONE);
		
		Label lblMinX = new Label(composite, SWT.NONE);
		lblMinX.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblMinX.setAlignment(SWT.CENTER);
		GridData gd_lblMinX = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_lblMinX.widthHint = 47;
		lblMinX.setLayoutData(gd_lblMinX);
		lblMinX.setText("Min X :");
		
		textMinX = new Text(composite, SWT.BORDER);
		GridData gd_textMinX = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textMinX.widthHint = 106;
		textMinX.setLayoutData(gd_textMinX);
		
		Label lblMinY = new Label(composite, SWT.NONE);
		lblMinY.setAlignment(SWT.CENTER);
		lblMinY.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblMinY.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblMinY.setText("Min Y :");
		
		textMinY = new Text(composite, SWT.BORDER);
		GridData gd_textMinY = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textMinY.widthHint = 132;
		textMinY.setLayoutData(gd_textMinY);
		
		Label lblMaxX = new Label(composite, SWT.NONE);
		lblMaxX.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		GridData gd_lblMaxX = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblMaxX.widthHint = 44;
		lblMaxX.setLayoutData(gd_lblMaxX);
		lblMaxX.setText("Max X :");
		
		textMaxX = new Text(composite, SWT.BORDER);
		GridData gd_textMaxX = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_textMaxX.widthHint = 144;
		textMaxX.setLayoutData(gd_textMaxX);
		
		Label lblMaxY = new Label(composite, SWT.NONE);
		lblMaxY.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblMaxY.setAlignment(SWT.CENTER);
		GridData gd_lblMaxY = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblMaxY.widthHint = 68;
		lblMaxY.setLayoutData(gd_lblMaxY);
		lblMaxY.setText("Max Y :");
		
		textMaxY = new Text(composite, SWT.BORDER);
		textMaxY.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(100, -10);
		fd_composite_1.right = new FormAttachment(composite, 0, SWT.RIGHT);
		fd_composite_1.left = new FormAttachment(0, 10);
		composite_1.setLayoutData(fd_composite_1);
		
		Button btnCancel = new Button(composite_1, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlModifyImage.dispose();
				
			}
		});
		btnCancel.setBounds(315, 10, 95, 25);
		btnCancel.setText("Close");
		
		Button btnOK = new Button(composite_1, SWT.NONE);
		btnOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				
		/*					
				String tStepX = textStepX.getText();
				if(tStepX.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shlModifyImage, "Error", "Please enter the value of Average Temperature");
	    			return;
	    		}
				
				
				String tStepY = textStepY.getText();
				if( tStepY.equalsIgnoreCase(""))
				{
	    			MessageDialog.openError(shlModifyImage, "Error", "Please enter the value of Average Temperature");
	    			return;
	    		}
				
				
		*/					
				try {
					c = new RConnection();
					System.out.println("connect");
					
					//--recuperer la valeur du compobos
					
					//--lire le ficher corespondant o combobox
					
					saveToR += "datm<-read.table(paste('"+CreatedMaizeProject.getStrPathProject()+"','"+LoadPlotDataDialog.comboStageDev.getText()+"',"+"\".txt\""+",sep=\""+"\"),header =T)" + "\r\n";
					c.eval("datm<-read.table(paste('"+CreatedMaizeProject.getStrPathProject()+"','"+LoadPlotDataDialog.comboStageDev.getText()+"',"+"\".txt\""+",sep=\""+"\"),header =T)");
					
			/*		
					saveToR += "xLabel <-'Temperature(T)'" + "\r\n";
					c.eval("xLabel <-'Temperature(T)'");
					
					saveToR += "yLabel <-'Development Rate'" + "\r\n";
					c.eval("yLabel <-'Development Rate'");
					
					saveToR += "title <-'Development Rate for Temperature'" + "\r\n";
					c.eval("title <-'Development Rate for Temperature'");
				*/	
					//Min x
					String tMinX = textMinX.getText();
					if( !tMinX.equalsIgnoreCase(""))
					{
						
						saveToR+="corrx[1]="+Float.parseFloat(tMinX) +"\r\n";
						c.eval("corrx[1]="+Float.parseFloat(tMinX) );
						
							    			
		    		}
					
										
					//Min Y
					String tMinY = textMinY.getText();
					if( !tMinY.equalsIgnoreCase(""))
					{
						saveToR+="corry[1]="+Float.parseFloat(tMinY) +"\r\n";
						c.eval("corry[1]="+Float.parseFloat(tMinY) );
						
						
		    		}
					
							
					//Max Y
					
					String tMaxY = textMaxY.getText();
					if( !tMaxY.equalsIgnoreCase(""))
					{
						saveToR+="corry[2]="+Float.parseFloat(tMaxY) +"\r\n";
						c.eval("corry[2]="+Float.parseFloat(tMaxY) );
											
		    		}
					
					//Max X
										
					String tMaxX = textMaxX.getText();
					if( !tMaxX.equalsIgnoreCase(""))
					{
						saveToR+="corrx[2]="+Float.parseFloat(tMaxX)+"\r\n";
						c.eval("corrx[2]="+Float.parseFloat(tMaxX));
											
		    		}
					
					String xLab = txtXlabel.getText();
					if( !xLab.equalsIgnoreCase(""))
					{
						saveToR+="xLabel='"+xLab+"'" +"\r\n";
						c.eval("xLabel='"+xLab+"'" );
											
		    		}
					
					String yLab = txtYlabel.getText();
					if( !yLab.equalsIgnoreCase(""))
					{
						saveToR+="yLabel='"+yLab+"'" +"\r\n";
						c.eval("yLabel='"+yLab+"'" );
											
		    		}
					
					
					String gphTitle = txtGraphtitle.getText();
					if( !gphTitle.equalsIgnoreCase(""))
					{
						saveToR+="title='"+gphTitle+"'" +"\r\n";
						c.eval("title='"+gphTitle+"'" );
											
		    		}
					/*else
					{
						saveToR += "title <-'Development Rate for Temperature'" + "\r\n";
						c.eval("title <-'Development Rate for Temperature'");
						
					}*/
					
					saveToR += "xRange <- corrx" + "\r\n";
					c.eval("xRange <- corrx");
					
					saveToR += "yRange <- corry" + "\r\n";
					c.eval("yRange <- corry");
					
										
					//String strImgOutput = MainPageWizardPage.getstrMortalityPath()+ "MortalityData.png";
					
					String strImgOutput = CreatedMaizeProject.getStrPathProject()+ LoadPlotDataDialog.comboStageDev.getText()+".png";
					
					c.eval("png(file=" + '"' +strImgOutput +'"'+", width = 480, height = 480)");
					//c.eval("plot(datm[,1],datm[,2]*100,frame=F,pch=19,col=4,cex=1.3,xlim=xRange,ylim=yRange,axes=F,xaxt = 'n',xlab=xLabel,ylab=yLabel,main=graphTitle);axis(1, xaxp=c(xRange,5));axis(2,las=2);");
					c.eval("plot(datm[,1],datm[,2],frame=T,pch=19,cex=1.3,col=4,xlab=xLabel,ylab=yLabel,main=title,axes=F,xaxt = 'n',ylim=yRange,xlim=xRange);axis(1, xaxp=c(xRange,5));axis(2,las=2)");
					c.eval("dev.off()");
					
									
					Image imge = new Image(Display.getDefault(),CreatedMaizeProject.getStrPathProject()+"/"+LoadPlotDataDialog.comboStageDev.getText()+".png");
					LoadPlotDataDialog.lblImage.setImage(imge);
					
					textMaxX.setText(""); textMaxY.setText(""); textMinX.setText(""); textMinY.setText("");
					txtYlabel.setText(""); txtXlabel.setText(""); txtGraphtitle.setText("");
					
					c.close();
				} catch (RserveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					MessageDialog.openError(shlModifyImage, "Error", "Impossible to connect! verify that rserve is running!");
				}
			}
		});
		btnOK.setBounds(184, 10, 111, 25);
		btnOK.setText("Ok");
		
		Composite composite_2 = new Composite(shlModifyImage, SWT.BORDER);
		fd_composite_1.top = new FormAttachment(composite_2, 32);
		fd_composite.bottom = new FormAttachment(100, -195);
		composite_2.setLayout(new GridLayout(2, false));
		FormData fd_composite_2 = new FormData();
		fd_composite_2.top = new FormAttachment(composite, 6);
		fd_composite_2.right = new FormAttachment(composite, 0, SWT.RIGHT);
		fd_composite_2.left = new FormAttachment(0, 10);
		fd_composite_2.bottom = new FormAttachment(100, -84);
		composite_2.setLayoutData(fd_composite_2);
		
		Label lblXLabel = new Label(composite_2, SWT.BORDER);
		lblXLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblXLabel.setAlignment(SWT.CENTER);
		GridData gd_lblXLabel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblXLabel.widthHint = 57;
		gd_lblXLabel.heightHint = 22;
		lblXLabel.setLayoutData(gd_lblXLabel);
		lblXLabel.setText("X Label :");
		
		txtXlabel = new Text(composite_2, SWT.BORDER);
		txtXlabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblYLabel = new Label(composite_2, SWT.NONE);
		lblYLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblYLabel.setAlignment(SWT.CENTER);
		GridData gd_lblYLabel = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblYLabel.heightHint = 24;
		lblYLabel.setLayoutData(gd_lblYLabel);
		lblYLabel.setText("Y Label :");
		
		txtYlabel = new Text(composite_2, SWT.BORDER);
		txtYlabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblGraphTitle = new Label(composite_2, SWT.NONE);
		lblGraphTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblGraphTitle.setAlignment(SWT.CENTER);
		GridData gd_lblGraphTitle = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_lblGraphTitle.widthHint = 78;
		gd_lblGraphTitle.heightHint = 23;
		lblGraphTitle.setLayoutData(gd_lblGraphTitle);
		lblGraphTitle.setText("Graph Title :");
		
		txtGraphtitle = new Text(composite_2, SWT.BORDER);
		txtGraphtitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}
}
