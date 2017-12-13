package modelDesigner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Properties;

import maizeTools.ArrayConvertions;
import maizeTools.MaizeDevUtils;
import maizeTools.Rserve;
import maizeTools.ViewProjectsUI;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import ui.CreatedMaizeProject;
import ui.modelBuilder.MainActionMaizeDevRate;
import ui.modelBuilder.MainPageWizardPage;
import ui.modelBuilder.OneModelWizardPage1;
import ui.modelBuilder.OneModelWizardPage2;
//import modelDesigner.wizards.MainPageWizardPage;
import ui.modelBuilder.SeveralModelsWizardPage;

//import modelDesigner.wizards.MainPageWizardPage;
//import modelDesigner.wizards.SeveralModelsWizardPage;
//import modelDesigner.wizards.MainPageWizardPage;
//import modelDesigner.wizards.OneModelWizardPage1;
//import modelDesigner.wizards.MainPageWizardPage;
//import modelDesigner.wizards.OneModelWizardPage1;
//import modelDesigner.wizards.MainPageWizardPage;
//import modelDesigner.wizards.OneModelWizardPage1;
//import modelDesigner.wizards.MainPageWizardPage;
//import modelDesigner.wizards.OneModelWizardPage1;
//import modelDesigner.wizards.OneModelWizardPage2;
//import projects.clasesutils.IlcymUtils;

//import projects.clasesutils.IlcymUtils;

//import projects.clasesutils.IlcymUtils;

public class MaizeDevRate {
	
/*	
	public static String[] lstArrayNames = new String[]{"Anlytis", "Briere", "Deva", "Hilbert & Logan", "Janish", "Kontodimas", "Lactin",
		"Logan", "Rawtosky", "SharpeDeMichelle", "Stinner", "Other models"};
	
	public static String[][] lstMatrixSubNames = new String[][]{{"Anlytis 1","Anlytis 2","Anlytis 3"},{"Briere 1","Briere 2","Briere 3",
		"Briere 4"},{"Deva 1","Deva 2"},{"Hilbert & Logan 1","Hilbert & Logan 2","Hilbert & Logan 3"},{"Janish 1","Janish 2"},
		{"Kontodimas 1", "Kontodimas 2", "Kontodimas 3"},{"Lactin 1","Lactin 2","Lactin 3"},{"Logan 1","Logan 2","Logan 3","Logan 4","Logan 5"},
		{"Rawtosky 1", "Rawtosky 2"},{"SharpeDeMichelle 1", "SharpeDeMichelle 2", "SharpeDeMichelle 3", "SharpeDeMichelle 4","SharpeDeMichelle 5",
		"SharpeDeMichelle 6","SharpeDeMichelle 7", "SharpeDeMichelle 8", "SharpeDeMichelle 9", "SharpeDeMichelle 10","SharpeDeMichelle 11",
		"SharpeDeMichelle 12", "SharpeDeMichelle 13","SharpeDeMichelle 14"},{"Stinner 1","Stinner 2", "Stinner 3","Stinner 4"},{"Linear",
		"Exponential Simple","Tb Model","Exponential Model","Exponential Tb", "Davidson", "Pradham", "Angilletta Jr.", "Hilbert", "Allahyari",
		"Tanigoshi", "Wang-Lan-Ding","Taylor","Sigmoid or logistic"}};
	
	public static String[][] lstModelNames = new String[][]{{"SharpeDeMichelle 1", "SharpeDeMichelle 2", "SharpeDeMichelle 3", "SharpeDeMichelle 4", 
		"SharpeDeMichelle 5","SharpeDeMichelle 6","SharpeDeMichelle 7", "SharpeDeMichelle 8", "SharpeDeMichelle 9", "SharpeDeMichelle 10", 
		"SharpeDeMichelle 11","SharpeDeMichelle 12", "SharpeDeMichelle 13","SharpeDeMichelle 14","Deva 1","Deva 2","Logan 1","Logan 2","Logan 3",
		"Logan 4","Logan 5","Briere 1","Briere 2","Briere 3","Briere 4","Stinner 1","Stinner 2","Stinner 3","Stinner 4", "Lactin 1","Lactin 2","Lactin 3",
		"Kontodimas 1", "Kontodimas 2", "Kontodimas 3","Janish 1","Janish 2","Rawtosky 1", "Rawtosky 2","Anlytis 1","Anlytis 2","Anlytis 3",
		"Hilbert & Logan 1","Hilbert & Logan 2","Hilbert & Logan 3", "Linear","Exponential Simple", "Tb Model", "Exponential Model", "Exponential Tb",
		"Davidson", "Pradham", "Angilletta Jr.", "Hilbert", "Allahyari", "Tanigoshi","Wang-Lan-Ding","Taylor","Sigmoid or logistic"},{"1","2","3",
		"4","5", "6","7","8","9","10","11","12","13","14","15","16","17","18","52","53","54","19","20","40","41","21","33","50","51","23","35","58",
		"42","43","44","46","47","29","45","36","37","38","22","55","56","24","25","26","27","28","30","31","32","34","39","48","49","57","59"}};
*/	

	public static String[] lstArrayNames = new String[]{"Briere", "Gaussian", "Gompertz", "Linear","Polynomial","Quadratic", 
		"Taylor", "Wang", "Other models", "Deva"};
	
	public static String[][] lstMatrixSubNames = new String[][]{{"Briere 1","Briere 2"}, {"Gaussian denominator","Gaussian",
		"Simple gaussian","Gaussian with log"},{"Gompertz","Gompertz-Makeham"},{"Linear root","Negative Linear root",
		"Linear negative exponent"},{"Polynomial 1","Polynomial 2","Polynomial 3","Polynomial 4","Polynomial 5","Polynomial 6",
		"Polynomial 7","Polynomial 8","Polynomial 9","Polynomial 10","Polynomial 11","Polynomial 12","Polynomial 13"},
		{"Quadratic","Quadratic with negative exponent"},{"Taylor 1","Taylor 2"},{"Wang 1","Wang 2","Wang 3","Wang 4","Wang 5",
		"Wang 6","Wang 7","Wang 8","Wang 9","Wang 10"},{"Logarithmic", "SharpeDeMichelle","Marc","DeMoivre","Weibull","Analytis",
		"Janisch & Analytis"},{"Deva 1","Deva 2"}};
		//
	public static String[][] lstModelNames = new String[][]{{"Quadratic","Quadratic with negative exponent","Linear root",
		"Negative Linear root","Linear negative exponent","Gaussian denominator","Gaussian","Simple gaussian","Gaussian with log",
		"Polynomial 1","Polynomial 2","Polynomial 3","Polynomial 4","Polynomial 5","Polynomial 6","Polynomial 7","Polynomial 8",
		"Polynomial 9","Polynomial 10","Polynomial 11","Polynomial 12","Polynomial 13","Taylor 1","Taylor 2","Wang 1","Wang 2",
		"Wang 3","Wang 4","Wang 5","Wang 6","Wang 7","Wang 8","Wang 9","Wang 10","Gompertz","Gompertz-Makeham","Briere 1","Briere 2","Logarithmic", "SharpeDeMichelle",
		"Marc","DeMoivre","Weibull","Analytis","Janisch & Analytis","Deva 1","Deva 2"},{"1","4","2","3","5","7","8","9","10","11","12","13","14","15","16","17","18",
		"19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","44","45","37", "38","40","41","6","34","35","36","39","46","47"}};
	
	private final static int nbrSelectionCriteria = 6;
	public static String title = MainPageWizardPage.getComboDevStage();
	public static String imageName = title + ".png";
	static String saveToR="";
	static RConnection c;
	
	public static Parameters pars = new Parameters();
	
	static REXP rexpAnt, rexpNew;
	static boolean bolBackPars;
	static DecimalFormat df = new DecimalFormat("#####.##########");
	static Double[] dbStdpars;
	
	/** Metodo para definir las caracteristicas de la imagen resultante, axis, labels, title, legend **/
	public static void definirParamsImage(){
		try {
			ImageProperties ip = ModifyImageUI.ip;
			
			ip.setCorrX1("0");
			ip.setCorrX2("40");
			ip.setCorrY1("0");
			ip.setCorrY2("100");
			ip.setMini("0");
			ip.setMaxi("100");
			ip.setLabX("temperature (degree celsius)");
			ip.setLabY("Development Rate (%)");
			ip.setTitle("");
			
			c= new RConnection();
//			c=EPF.getConnection();  xRange    yRange
			
			saveToR+="corrx=c("+ip.getCorrX1()+','+ip.getCorrX2()+')'+"\r\n";
			c.eval("corrx=c("+ip.getCorrX1()+','+ip.getCorrX2()+')');
			
//			c.eval("corrx= xRange");
			
			
			saveToR+="corry=c("+ip.getCorrY1()+','+ip.getCorrY2()+')'+"\r\n";
			c.eval("corry=c("+ip.getCorrY1()+','+ip.getCorrY2()+')');
			
//			c.eval("corry=yRange");
			
			
			saveToR+="mini<-"+ip.getMini()+"\r\n";
			c.eval("mini<-"+ip.getMini());
			saveToR+="maxi<-"+ip.getMaxi()+"\r\n";
			c.eval("maxi<-"+ip.getMaxi());
			saveToR+="labx="+'"'+ip.getLabX()+'"'+"\r\n";
			c.eval("labx="+'"'+ip.getLabX()+'"');
			saveToR+="laby="+'"'+ip.getLabY()+'"'+"\r\n";
			c.eval("laby="+'"'+ip.getLabY()+'"');
			saveToR+="titulo="+'"'+ip.getTitle()+'"'+"\r\n";
			c.eval("titulo="+'"'+ip.getTitle()+'"');
			
			saveToR+="grises=FALSE"+"\r\n";
			c.eval("grises=FALSE");
			
			c.close();
		} catch (RserveException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to set the features of the graph");
		}
	}
/*	
	public static void prepareDataForMortality(){
		
		String strOpc="", strDs="", dbIntervalo="";
		String strConcat = "";String strConcatStateData = "";
		int nData;
		String arrayDatas[]=null;
		String temps[]=null;
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(MainActionMaizeDevRate.pathProj + File.separator + "Data" + File.separator+ "Parameters.r"));
			strOpc = prop.getProperty("Option");
			strDs = prop.getProperty("DataSet");
			dbIntervalo = prop.getProperty("EvaluationInterval");
			nData = Integer.valueOf(prop.getProperty("NumberData")).intValue();
			prop.clear();
			
			BufferedReader br = new BufferedReader(new FileReader(new File(MainActionMaizeDevRate.pathProj + File.separator + "Data" + File.separator+ "Parameters.r")));
			String str;
			ArrayList<String> lst = new ArrayList<String>();
			while ((str = br.readLine()) != null) {
				lst.add(str);
			}
			br.close();
			
			arrayDatas=new String[nData];
			temps=new String[nData];
			int c1=0;
			
			for(int i=lst.size()-1; i>=lst.size()-nData; i--){
				if(strOpc.equalsIgnoreCase("1")){
					arrayDatas[Integer.valueOf(lst.get(i).split("\t")[1])-1] = lst.get(i).substring(lst.get(i).lastIndexOf("/")+1, lst.get(i).lastIndexOf(":")).trim();
				}
				if(strOpc.equalsIgnoreCase("2")){
					arrayDatas[c1] = lst.get(i).substring(lst.get(i).lastIndexOf("/")+1, lst.get(i).lastIndexOf(":")).trim();
		        	temps[c1] = lst.get(i).split("\t")[1];
		        	c1++;
				}
			}
			
			lst.clear();
		} catch (FileNotFoundException e1) {
			MessageDialog.openError(new Shell(), title, "Problems while trying to get the parameters and datas from the project");
			e1.printStackTrace();
		} catch (IOException e1) {
			MessageDialog.openError(new Shell(), title, "Problems while trying to get the parameters and datas from the project");
			e1.printStackTrace();
		}
		
		try {
		saveToR="";
		saveToR+="##### MaizeDevRate Process #####"+"\r\n";
		
		OneModelWizardPage1.lblImageTemp.setBackgroundImage(null);
		
		if(!Platform.getOS().equalsIgnoreCase("macosx"))
			c = Rserve.launchRserve("",c);
		else{
			try {
				c=new RConnection();
			} catch (RserveException e2) {
				do{
					try {
						c = new RConnection();
					} catch (RserveException e) {
						e.printStackTrace();
					}
				}while (c == null || !c.isConnected());
			}
		}
		
		saveToR += "source('" + IlcymUtils.getLibPath() + "/mortality.r')" + "\r\n";
		c.eval("source('" + IlcymUtils.getLibPath() + "/mortality.r')");
		
		saveToR += "source('" + IlcymUtils.getLibPath() + "/developmentTime.r')" + "\r\n";
		c.eval("source('" + IlcymUtils.getLibPath() + "/developmentTime.r')");
		
		String strwd = (MainActionMaizeDevRate.pathProj + File.separator + "Data").replace('\\', '/');
		
		saveToR+= "setwd(" + '"' + strwd + '"'+ ')'+"\r\n";
		c.eval("setwd(" + '"' + strwd + '"'+ ')');
		
		saveToR+= "library(minpack.lm)"+"\r\n";
		c.eval("library(minpack.lm)");
		
		saveToR+= "library(MASS)"+"\r\n";
		c.eval("library(MASS)");
		
		// Procesos para datos por cohorte 
		if(strOpc.equalsIgnoreCase("1")){
			saveToR+="opc<-"+strOpc+"\r\n";
			c.eval("opc<-"+strOpc);
			
			for(int i=0;i<arrayDatas.length;i++){
				saveToR+= "data"+i+"<-read.table("+'"'+arrayDatas[i]+'"'+")"+"\r\n";
				c.eval("data"+i+"<-read.table("+'"'+arrayDatas[i]+'"'+")");
			}
			
			String[] arrayIm  =ArrayConvertions.StringtoArray(ViewProjectsUI.getLifeStages()[0].trim(), ",");
			String[] arrayMad  =ArrayConvertions.StringtoArray(ViewProjectsUI.getLifeStages()[1].trim(), ",");
			
			for(int i=0; i<arrayIm.length;i++){
				strConcatStateData += '"' + arrayIm[i].trim() + '"' + ',';
				strConcat += "data" + i + ',';
			}
			
			for(int i=0; i<arrayMad.length; i++){
				if(i == arrayMad.length-1){
					strConcatStateData += '"' + arrayMad[i].trim() + '"';
					strConcat += "data" + (arrayIm.length+i);
				}else{
					strConcatStateData += '"' + arrayMad[i].trim() + '"' + ',';
					strConcat += "data" + (arrayIm.length+i) + ',';
				}
				
			}
			
			saveToR+="estadios<-"+"c("+strConcatStateData+")"+"\r\n";
			c.eval("estadios<-"+"c("+strConcatStateData+")");
			
			saveToR+="est<-"+'"'+MainPageWizardPage.getStageSel()+'"'+"\r\n";
			c.eval("est<-"+'"'+MainPageWizardPage.getStageSel()+'"');
			
			saveToR+="datos<-list("+strConcat+")"+"\r\n";
			c.eval("datos<-list("+strConcat+")");
			
			saveToR+="temperatura<-temp(datos,estadios,est)"+"\r\n";
			c.eval("temperatura<-temp(datos,estadios,est)");
			
			saveToR+="tp<-temperatura$temperature"+"\r\n";
			c.eval("tp<-temperatura$temperature");
			
		}else if(strOpc.equalsIgnoreCase("2") && !strDs.equalsIgnoreCase("3")){
			saveToR+="opc<-"+strOpc+"\r\n";
			c.eval("opc<-"+strOpc);
			
			
			for(int i=0;i<arrayDatas.length;i++){
				if(i==arrayDatas.length-1)
					strConcat += "data" + i;
				else
					strConcat += "data" + i + ',';
				
				saveToR+= "data"+i+"<-read.table("+'"'+arrayDatas[i]+'"'+")"+"\r\n";
				c.eval("data"+i+"<-read.table("+'"'+arrayDatas[i]+'"'+")");
			}
			
			String[] arrayIm  =ArrayConvertions.StringtoArray(ViewProjectsUI.getLifeStages()[0].trim(), ",");
			String[] arrayMad  =ArrayConvertions.StringtoArray(ViewProjectsUI.getLifeStages()[1].trim(), ",");
			
			for(int i=0; i<arrayIm.length;i++){
				strConcatStateData += '"' + arrayIm[i].trim() + '"' + ',';
			}
			
			for(int i=0; i<arrayMad.length; i++){
				if(i == arrayMad.length-1)
					strConcatStateData += '"' + arrayMad[i].trim() + '"';
				else
					strConcatStateData += '"' + arrayMad[i].trim() + '"' + ',';
				
			}
			
			saveToR+="estadios<-"+"c("+strConcatStateData+")"+"\r\n";
			c.eval("estadios<-"+"c("+strConcatStateData+")");
			
			
			saveToR+="est<-"+'"'+MainPageWizardPage.getStageSel()+'"'+"\r\n";
			c.eval("est<-"+'"'+MainPageWizardPage.getStageSel()+'"');
			
			saveToR+="dataset<-"+strDs+"\r\n";
			c.eval("dataset<-"+strDs);
			
			saveToR+="data<-list("+ strConcat+")"+"\r\n";
			c.eval("data<-list("+ strConcat+")");
			
			saveToR+="tp<-"+"c("+ArrayConvertions.ArrayToString(temps, ",")+")"+"\r\n";
			c.eval("tp<-"+"c("+ArrayConvertions.ArrayToString(temps, ",")+")");
			
			saveToR+="intervalo<-"+dbIntervalo+"\r\n";
			c.eval("intervalo<-"+dbIntervalo);
			
			
			saveToR+="datos<-countdata(data,estadios,dataset,intervalo)"+"\r\n";
			c.eval("datos<-countdata(data,estadios,dataset,intervalo)");
			
		}else if(strDs.equalsIgnoreCase("3")){
			saveToR+="opc<-"+strOpc+"\r\n";
			c.eval("opc<-"+strOpc);
			
			
			for(int i=0;i<arrayDatas.length;i++){
				if(i==arrayDatas.length-1)
					strConcat += "data" + i;
				else
					strConcat += "data" + i + ',';
				
				saveToR+= "data"+i+"<-read.table("+'"'+arrayDatas[i]+'"'+")"+"\r\n";
				c.eval("data"+i+"<-read.table("+'"'+arrayDatas[i]+'"'+")");
			}
			
			String[] arrayIm  =ArrayConvertions.StringtoArray(ViewProjectsUI.getLifeStages()[0].trim(), ",");
			String[] arrayMad  =ArrayConvertions.StringtoArray(ViewProjectsUI.getLifeStages()[1].trim(), ",");
			
			for(int i=0; i<arrayIm.length;i++){
				strConcatStateData += '"' + arrayIm[i].trim() + '"' + ',';
			}
			
			for(int i=0; i<arrayMad.length; i++){
				if(i == arrayMad.length-1)
					strConcatStateData += '"' + arrayMad[i].trim() + '"';
				else
					strConcatStateData += '"' + arrayMad[i].trim() + '"' + ',';
				
			}
			
			saveToR+="estadios<-"+"c("+strConcatStateData+','+'"'+"male"+'"'+")"+"\r\n";
			c.eval("estadios<-"+"c("+strConcatStateData+','+'"'+"male"+'"'+")");
			
			
			saveToR+="est<-"+'"'+MainPageWizardPage.getStageSel()+'"'+"\r\n";
			c.eval("est<-"+'"'+MainPageWizardPage.getStageSel()+'"');
			
			saveToR+="dataset<-2"+"\r\n";
			c.eval("dataset<-2");
			
			saveToR+="data<-list("+ strConcat+")"+"\r\n";
			c.eval("data<-list("+ strConcat+")");
			
			saveToR+="tp<-"+"c("+ArrayConvertions.ArrayToString(temps, ",")+")"+"\r\n";
			c.eval("tp<-"+"c("+ArrayConvertions.ArrayToString(temps, ",")+")");
			
			saveToR+="intervalo<-"+dbIntervalo+"\r\n";
			c.eval("intervalo<-"+dbIntervalo);
			
			
			//saveToR+="datos<-countdata(data,estadios,dataset,intervalo)"+"\r\n";
			//c.eval("datos<-countdata(data,estadios,dataset,intervalo)");
			
		}
		
		saveToR+="mort<-mortality(opc, datos, estadios, est, tp)"+"\n";
		c.eval("mort<-mortality(opc, datos, estadios, est, tp)");
		
		saveToR+="datm<-mort$datam"+"\n";
		c.eval("datm<-mort$datam");
		
		c.close();
		}catch (RserveException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problem while setting the inicial variables in MaizeDevRate");
		}
	}
	*/
	
	private static String framesSel[]= new String[nbrSelectionCriteria];
	static Shell[] arraysShell;
	
	/** Metodo que procesa todos los modelos seleccionados (cmpAllModelsPage) **/
	public static void proceesAllModelsNew(boolean bolExtremMin)
	{
//		String path = MainPageWizardPage.getstrMortalityPath() + File.separator + imageName;
		String path = CreatedMaizeProject.getStrPathProject() + File.separator + imageName;
		//String stageSel = MainPageWizardPage.getStageSel();
		String algo = MainPageWizardPage.getSelectAlgo();
		SeveralModelsWizardPage.tableCriterias.removeAll();
		SeveralModelsWizardPage.lblModelSelAll.setText("");
		SeveralModelsWizardPage.txtParametersEstimatedAll.setText("");
		String pathMotData = MainPageWizardPage.getFileDevStageData();
		Boolean charge = false;
		
		try {
			c = new RConnection();
//			c = EPF.getConnection();

			if(bolExtremMin){
				saveToR += "tx.min = c(" + MainPageWizardPage.getExtremValues()[0]+')'+"\r\n";
				c.eval("tx.min = c(" + MainPageWizardPage.getExtremValues()[0]+')');
				
				saveToR += "ty.min = c(" + MainPageWizardPage.getExtremValues()[1]+')'+"\r\n";
				c.eval("ty.min = c(" + MainPageWizardPage.getExtremValues()[1]+')');
				
				saveToR += "ty.min <- ty.min/100"+"\r\n";
				c.eval("ty.min <- ty.min/100");
				
				saveToR += "datm<-mort$datam"+"\r\n";
				c.eval("datm<-mort$datam");

				saveToR += "datm=rbind(data.frame(x=tx.min,y=ty.min),datm)"+"\r\n";
				c.eval("datm=rbind(data.frame(x=tx.min,y=ty.min),datm)");
			}
			c.close();
		}catch (RserveException e1) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
//			c.close();
			e1.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to show the model selected");
			c.close();
			return;
		}
		
		arraysShell = new Shell[MainPageWizardPage.lstSelectedModels.getItemCount()];
		
		for(int i=0; i<MainPageWizardPage.lstSelectedModels.getItemCount(); i++){
			try {
				
				int modelm = getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(i));
				Model1UI uiShape1 = new Model1UI(SeveralModelsWizardPage.container.getShell(), modelm, path, algo);
				uiShape1.createContents(MainPageWizardPage.lstSelectedModels.getItem(i));
				uiShape1.lblFunctImageDR.setBackgroundImage(null);
				
				//String  scrptFile = ("'"+(new File("./").getCanonicalPath()).concat("/RScripts/mortalityDesigner.r'")).replace('\\','/');
		        //System.out.println(scrptFile);
		        
				c = new RConnection();
//				c = EPF.getConnection();		
				
				//saveToR += "source('"+IlcymUtils.getScriptPath()+"/mortalityDesigner.r'"+")"+"\r\n";	
		        //--------------c.eval("source('"+IlcymUtils.getScriptPath()+"/mortalityDesigner.r'"+")");
		        
				if (charge == false)
				{
					saveToR+="datm<-read.table('"+pathMotData+"',header = T)"+"\n";
					c.eval("datm<-read.table('"+pathMotData+"',header = T)");
					
					MaizeDevUtils.createTempScriptFile("/Scripts/maizeDevelopment.r");
					
			        saveToR += "source('"+CreatedMaizeProject.getStrPathProject()+"tempScripFile.r'"+")"+"\r\n";	
			        c.eval("source('"+CreatedMaizeProject.getStrPathProject()+"tempScripFile.r'"+")");
			        System.out.println("charger again");
			        charge = true;
				}
				
				
				
				
				double nelt = c.eval("nrow(datm)").asDouble();
				System.out.println(nelt);
				
				saveToR += "limit<-"+'"'+ MainPageWizardPage.getLimits() +'"' +"\n";
				c.eval("limit<-"+'"'+ MainPageWizardPage.getLimits() +'"');
				
				estimateParameters(c, modelm, MainPageWizardPage.getSeveralModels(),  false,"", "", "", "", "","");

//changemn des abs
				
				saveToR += "corry <- c(min(datm[,2]),max(datm[,2]))" + "\r\n";
				c.eval("corry <- c(min(datm[,2]),max(datm[,2]))");
				
				saveToR +="pbmortal<-pruebamortal(modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)" +"\n";
				c.eval("pbmortal<-pruebamortal(modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)");
				c.eval("dev.off()");
				
//				saveToR +="pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)" +"\n";
//				c.eval("pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)");
//				c.eval("dev.off()");
				
				saveToR +="inim<-pbmortal$ini" +"\n";
				c.eval("inim<-pbmortal$ini");
/*				
				if(modelm < 7){
					saveToR+="alg<-" + '"' + "Newton" + '"'+"\n";
					c.eval("alg<-" + '"' + "Newton" + '"');
					
					saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
					c.eval("weight<-" + '"' + "LS" + '"');
				}else{
					saveToR+="alg<-" + '"' + "Marquardtr" + '"'+"\n";
					c.eval("alg<-" + '"' + "Marquardtr" + '"');
				
					}
				*/
				
//				saveToR +="inim<-pbmortal$ini" +"\n";
//				c.eval("inim<-pbmortal$ini");
				
				
				saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
				c.eval("weight<-" + '"' + "LS" + '"');
				
	
// res=dead_func(modelNumber, datm, alg, inim, weight,weights)			
				
				saveToR+="fmort<-dead_func("+ modelm+", datm,'"+algo+"', inim, weight,weights)"+"\n";
				c.eval("fmort<-dead_func("+ modelm+", datm,'"+algo+"', inim, weight,weights)");
					
				
	//			saveToR+="fmort<-dead_func(" + '"' + "mortal" + '"' +",modelm, datm, alg, inim, weight,weights)"+"\n";
	//			c.eval("fmort<-dead_func(" + '"' + "mortal" + '"' +",modelm, datm, alg, inim, weight,weights)");
				
				
				saveToR+="coefEstimated<-fmort$estimatedCoef"+"\n";
				c.eval("coefEstimated<-fmort$estimatedCoef");
				
				saveToR+="stdmortg <-  fmort$stdmort"+"\n";
				c.eval("stdmortg <-  fmort$stdmort");
				
				saveToR+="model<- fmort$model"+"\n";
				c.eval("model<- fmort$model");
				
				saveToR+="gg<-fmort$f"+"\n";
				c.eval("gg<-fmort$f");
				
				
				
				saveToR+="g<-fmort$ecua"+"\n";
				c.eval("g<-fmort$ecua");
				
				/*
				saveToR+="gg<-fmort$f"+"\n";
				c.eval("gg<-fmort$f");
				
				saveToR+="stdmortg <-  fmort$stdmort"+"\n";
				c.eval("stdmortg <-  fmort$stdmort");
				
				saveToR+="model<- fmort$model"+"\n";
				c.eval("model<- fmort$model");
				
				saveToR+="modelim<-fmort$model"+"\n";
				c.eval("modelim<-fmort$model");*/
				
				c.eval("sink("+'"'+MainPageWizardPage.getstrMortalityPath() + "/"+MainPageWizardPage.comboDevStage.getText()+"-Model" +modelm+".txt"+'"'+")");
				/*try {
					IlcymUtils.safeEval(c, "sink("+'"'+MainPageWizardPage.getstrMortalityPath() + "/Mort-Model" +modelm+".txt"+'"'+")");
				} catch (RException e) {
					c.close();
					e.printStackTrace();
				}*/
				
	
				//coefs = coef_mort(modelNumber,coefEstimated,stdmortg,model,gg,datm,alg,weight,weights)
				
				System.out.println(c.eval("sink.number()").asInteger());
				
				saveToR+="sol_mort<-coef_mort(modelm,coefEstimated, stdmortg, model,gg,datm,'"+ algo +"',weight,weights)"+"\n";
				c.eval("sol_mort<-coef_mort(modelm,coefEstimated, stdmortg, model,gg,datm,'"+ algo +"',weight,weights)");
				c.eval("sink()");
				
				saveToR+="frsel<-sol_mort$frames"+"\r\n";
				c.eval("frsel<-sol_mort$frames");
				
				int lList= c.eval("frsel").asList().keys().length;
				
				for(int ii=0; ii<lList;ii++)
				{
					framesSel[ii] = c.eval("frsel").asList().at(ii).asDouble()+"";
					System.out.println(framesSel[ii]);
					}

//grafmort(modelNumber, coefEstimated, g, datm, corrx=NULL, corry=NULL, mini, maxi, limit,tam,xLabel, yLabel, paste("model",as.character(j)),grises=FALSE)
				
				c.eval("png(file=" + '"' +MainPageWizardPage.getstrMortalityPath() + "/"+MainPageWizardPage.comboDevStage.getText()+"-Model"+ modelm+".png"+'"'+")");
				
				saveToR+="plot_mort<-grafmort(modelm,coefEstimated,g,datm,corrx,corry,mini,maxi,limit,1,labx,laby,titulo, grises)"+"\n";
				c.eval("plot_mort<-grafmort(modelm,coefEstimated,g,datm,corrx,corry,mini,maxi,limit,1,labx,laby,titulo, grises)");
				c.eval("dev.off()");
				
				uiShape1.lblFunctImageDR.setImage(new Image(Display.getCurrent(), MainPageWizardPage.getstrMortalityPath() + File.separator + MainPageWizardPage.comboDevStage.getText()+"-Model"+ modelm+".png"));
				uiShape1.lblFunctImageDR.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			  	
			  	MaizeDevUtils.createHTMLfile(MainPageWizardPage.getstrMortalityPath(), MainPageWizardPage.comboDevStage.getText()+"-Model"+modelm);
//			  	uiShape1.bwrResultDR.setUrl("file:///" + MainPageWizardPage.getstrMortalityPath() + File.separator + "Mort-Model"+modelm+".html");
			  	uiShape1.bwrResultDR.setUrl(MainPageWizardPage.getstrMortalityPath() + MainPageWizardPage.comboDevStage.getText()+"-Model"+modelm+".html");
//			  	file:///D:/TestSoftware/Mort-Model24.html
				TableItem ti = new TableItem(SeveralModelsWizardPage.tableCriterias, SWT.None);
				ti.setText(new String[]{getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(i))+"",MainPageWizardPage.lstSelectedModels.getItem(i),framesSel[0],framesSel[1],framesSel[2],framesSel[3],framesSel[4],framesSel[5]});
				
				c.close();
				arraysShell[i] = uiShape1.shell;
				uiShape1.shell.open();
					
				
			}catch (RserveException e1) {
				Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
				try {
					c.eval("sink()");
				} catch (RserveException e) {
					c.close();
					e.printStackTrace();
				}
				c.close();
				e1.printStackTrace();
				
				MessageDialog.openError(new Shell(), title, "Problems of convergence of the initial parameter values of "+MainPageWizardPage.lstSelectedModels.getItem(i)+" model");
				//MessageDialog.openError(new Shell(), title, e1.getMessage()+MainPageWizardPage.lstSelectedModels.getItem(i)+" model");
			}catch (REXPMismatchException e) {
				Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
				try {
					c.eval("sink()");
				} catch (RserveException e1) {
					c.close();
					e1.printStackTrace();
					MessageDialog.openError(new Shell(), title, e1.getMessage()+MainPageWizardPage.lstSelectedModels.getItem(i)+" model");
				}
				c.close();
				e.printStackTrace();
				MessageDialog.openError(new Shell(), title, "Problems of convergence of the initial parameter values of "+MainPageWizardPage.lstSelectedModels.getItem(i)+" model");
				//MessageDialog.openError(new Shell(), title, e.getMessage()+MainPageWizardPage.lstSelectedModels.getItem(i)+" model");
			}
		}
		c.close();
	}
	
	/** Metodo que guarda el modelo seleccionado (cmpAllModelsPage) **/
	public static void setModelSelected(int modelm){
		Double[] dbpars = null;
		DecimalFormat df = new DecimalFormat("##.###");
		String algo = MainPageWizardPage.getSelectAlgo();
		
		try {
			c = new RConnection();
			
			estimateParameters(c, modelm, MainPageWizardPage.getSeveralModels(),  false,"", "", "", "", "","");
			
			saveToR +="pbmortal<-pruebamortal(modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)" +"\n";
			c.eval("pbmortal<-pruebamortal(modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)");
			c.eval("dev.off()");
			
			saveToR +="inim<-pbmortal$ini" +"\n";
			c.eval("inim<-pbmortal$ini");
			
		/*	if(modelm < 7){
				saveToR+="alg<-" + '"' + "Newton" + '"'+"\n";
				c.eval("alg<-" + '"' + "Newton" + '"');
				
				saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
				c.eval("weight<-" + '"' + "LS" + '"');
			}else{
				saveToR+="alg<-" + '"' + "Marquardtr" + '"'+"\n";
				c.eval("alg<-" + '"' + "Marquardtr" + '"');
				
				saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
				c.eval("weight<-" + '"' + "LS" + '"');
			}*/
			saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
			c.eval("weight<-" + '"' + "LS" + '"');
			
			saveToR+="fmort<-dead_func("+ modelm+", datm,'"+algo+"', inim, weight,weights)"+"\n";
			c.eval("fmort<-dead_func("+ modelm+", datm,'"+algo+"', inim, weight,weights)");
				
			saveToR+="coefEstimated<-fmort$estimatedCoef"+"\n";
			c.eval("coefEstimated<-fmort$estimatedCoef");
			
			saveToR+="stdmortg <-  fmort$stdmort"+"\n";
			c.eval("stdmortg <-  fmort$stdmort");
			
			saveToR+="model<- fmort$model"+"\n";
			c.eval("model<- fmort$model");
			
			saveToR+="gg<-fmort$f"+"\n";
			c.eval("gg<-fmort$f");
			
					
			saveToR+="g<-fmort$ecua"+"\n";
			c.eval("g<-fmort$ecua");
			
			saveToR+="alg<-'"+algo+"'"+"\n";
			c.eval("alg<-'"+algo+"'");
			
			

			/*
			saveToR+="coefEstimated<-fmort$estimados"+"\n";
			c.eval("coefEstimated<-fmort$estimados");
			
			saveToR+="g<-fmort$ecua"+"\n";
			c.eval("g<-fmort$ecua");
			
			saveToR+="gg<-fmort$f"+"\n";
			c.eval("gg<-fmort$f");
			
			saveToR+="stdmortg <-  fmort$stdmort"+"\n";
			c.eval("stdmortg <-  fmort$stdmort");
			
			saveToR+="modelm<- fmort$modelm"+"\n";
			c.eval("modelm<- fmort$modelm");
			
			saveToR+="modelim<-fmort$modelm"+"\n";
			c.eval("modelim<-fmort$modelm");*/
			
			c.eval("sink("+'"'+MainPageWizardPage.getstrMortalityPath() + "/" +"MaizeDevRate-"+ MainPageWizardPage.comboDevStage.getText() + ".txt"+'"'+")");
			saveToR+="sol_mort<-coef_mort(modelm,coefEstimated, stdmortg, model,gg,datm,'"+ algo +"',weight,weights)"+"\n";
			c.eval("sol_mort<-coef_mort(modelm,coefEstimated, stdmortg, model,gg,datm,'"+ algo +"',weight,weights)");
			c.eval("sink()");
			
			saveToR+="frsel<-sol_mort$frames"+"\r\n";
			c.eval("frsel<-sol_mort$frames");
			
			saveToR+="Std.Error<-sol_mort$Std.Error"+"\r\n";
			c.eval("Std.Error<-sol_mort$Std.Error");
			
			c.eval("png(file=" + '"' + MainPageWizardPage.getstrMortalityPath() + "/" +"MaizeDevRate-"+MainPageWizardPage.comboDevStage.getText() + ".png" + '"' + ")");
			saveToR+="plot_mort<-grafmort(modelm,coefEstimated,g,datm,corrx,corry,mini,maxi,limit,1,labx,laby,titulo, grises)"+"\n";
			c.eval("plot_mort<-grafmort(modelm,coefEstimated,g,datm,corrx,corry,mini,maxi,limit,1,labx,laby,titulo, grises)");
			c.eval("dev.off()");
			
			saveToR += "valxs=plot_mort$valxs";
			c.eval("valxs=plot_mort$valxs");
			
			dbpars= saveFinalPar("coefEstimated", modelm);
			pars.setParameters(dbpars);
			pars.setStdParameters(dbStdpars);
			
			MaizeDevUtils.createHTMLfile(MainPageWizardPage.getstrMortalityPath(), "MaizeDevRate-"+ MainPageWizardPage.comboDevStage.getText());
			//File temp = new File(MainPageWizardPage.getstrMortalityPath()+ File.separator + title + ".txt");
			//temp.delete();
			
			//here we have to save R workSave in for the use for the mapping
			saveToR+="save(list = ls(all.names = TRUE), file = "+ '"' + MainPageWizardPage.getstrMortalityPath() + "/" + "MaizeDevRate-"+MainPageWizardPage.comboDevStage.getText() + ".RData"+'"'+", envir = .GlobalEnv)"+"\n";
			c.eval("save(list = ls(all.names = TRUE), file = "+ '"' + MainPageWizardPage.getstrMortalityPath() + "/" + "MaizeDevRate-"+MainPageWizardPage.comboDevStage.getText() + ".RData"+'"'+", envir = .GlobalEnv)");
			
			c.close();
		} catch (RserveException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			try {
				c.eval("sink()");
			} catch (RserveException e1) {
				c.close();
				e1.printStackTrace();
			}
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to set the model selected");
		} catch (REXPMismatchException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			try {
				c.eval("sink()");
			} catch (RserveException e1) {
				c.close();
				e1.printStackTrace();
			}
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to set the model selected");
		}
		
		SeveralModelsWizardPage.lblModelSelAll.setText(getModelName(modelm+""));
		pars.setStrModel(getModelName(modelm+""));
		String str="";
		
		for(int i=0; i<dbpars.length; i++){
			if(!pars.getParametersName()[i].equalsIgnoreCase("")){
				if(i == dbpars.length-1)
					str+= pars.getParametersName()[i] + " = " + df.format(dbpars[i]);
				else
					str+= pars.getParametersName()[i] + " = " + df.format(dbpars[i]) + ", ";
			}else{
				str = str.substring(0, str.length()-2);
				break;
			}
		}
		SeveralModelsWizardPage.txtParametersEstimatedAll.setText(str);
		
		for(int i=0;i<arraysShell.length;i++){
			if(arraysShell[i]!= null)
				if(!arraysShell[i].isDisposed())
					arraysShell[i].close();
		}
	}

	/** Metodo para  procesar modelo uno a uno**/
	public static void selectionModelOneByOne(int modelm, boolean bolExtremMin){//Combo listener
		String pathMotData = CreatedMaizeProject.getStrPathProject();
		
		try {
			c = new RConnection();
			OneModelWizardPage1.lblImageTemp.setImage(null);
			
			saveToR+="datm<-read.table('"+pathMotData+"',header = T)"+"\n";
			c.eval("datm<-read.table('"+pathMotData+"',header = T)");
			
			double nelt = c.eval("nrow(datm)").asDouble();
			System.out.println(nelt);
		/*
			if(bolExtremMin){
				saveToR += "tx.min = c(" + OneModelWizardPage1.getExtremValuesOne()[0]+')'+"\r\n";
				c.eval("tx.min = c(" + OneModelWizardPage1.getExtremValuesOne()[0]+')');
				
				saveToR += "ty.min = c(" + OneModelWizardPage1.getExtremValuesOne()[1]+')'+"\r\n";
				c.eval("ty.min = c(" + OneModelWizardPage1.getExtremValuesOne()[1]+')');
				
				saveToR += "ty.min <- ty.min/100"+"\r\n";
				c.eval("ty.min <- ty.min/100");
				
				saveToR += "datm<-mort$datam"+"\r\n";
				c.eval("datm<-mort$datam");
	
				saveToR += "datm=rbind(data.frame(x=tx.min,y=ty.min),datm)"+"\r\n";
				c.eval("datm=rbind(data.frame(x=tx.min,y=ty.min),datm)");
			}else{
				saveToR+="datm<-mort$datam"+"\n";
				c.eval("datm<-mort$datam");
			}
			*/
			estimateParameters(c, modelm, true,  false,"", "", "", "", "","");
			
			saveToR += "inim1=inim;niv=1" + "\r\n";
			c.eval("inim1=inim;niv=1");
			
			c.eval("png(file=" + '"' +MainPageWizardPage.getstrMortalityPath() + "/Mort-Model"+ (modelm)+".png"+'"'+")");
			saveToR +="pbmortal<-pruebamortal(modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)" +"\n";
			c.eval("pbmortal<-pruebamortal(modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)");
			c.eval("dev.off()");
			
			saveToR +="inim<-pbmortal$ini" +"\n";
			c.eval("inim<-pbmortal$ini");
			
			//System.out.println(c.eval("Std.Error").asDoubles()[0]);
			
			OneModelWizardPage1.txtAdjust.setText("1");
			Double[] dbpars= saveTempar("inim", modelm);
			
			OneModelWizardPage1.txtAdjust.setText("1");
			OneModelWizardPage1.lblImageTemp.setImage(new Image(Display.getCurrent(), MainPageWizardPage.getstrMortalityPath() + "/Mort-Model"+ (modelm)+".png"));
			
			OneModelWizardPage1.txtPar1.setText(df.format(dbpars[0]));
		  	OneModelWizardPage1.txtPar2.setText(df.format(dbpars[1]));
			OneModelWizardPage1.txtPar3.setText(df.format(dbpars[2]));
			OneModelWizardPage1.txtPar4.setText(df.format(dbpars[3]));
			OneModelWizardPage1.txtPar5.setText(df.format(dbpars[4]));
			OneModelWizardPage1.txtPar6.setText(df.format(dbpars[5]));
		  	
			c.close();
			
		}catch (RserveException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			try {
				c.eval("sink()");
			} catch (RserveException e1) {
				c.close();
				e1.printStackTrace();
			}
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to process one model");
		} catch (REXPMismatchException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			try {
				c.eval("sink()");
			} catch (RserveException e1) {
				c.close();
				e1.printStackTrace();
			}
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to process one model");
		}catch (Exception e) {
			try {
				c.eval("sink()");
			} catch (RserveException e1) {
				c.close();
				e1.printStackTrace();
			}
			c.close();
		}
	}

	
	/** Metodo para estimar los parametros modificando los valores d los parametros **/
	public static void spinnerListener(int model, String str1, String str2, String str3, String str4, String str5, String str6, boolean bolExtremMin/*, boolean bolExtremMax*/){
		try {
			c = new RConnection();
			
			if(bolExtremMin){
				saveToR += "tx.min = c(" + OneModelWizardPage1.getExtremValuesOne()[0]+')'+"\r\n";
				c.eval("tx.min = c(" + OneModelWizardPage1.getExtremValuesOne()[0]+')');
				
				saveToR += "ty.min = c(" + OneModelWizardPage1.getExtremValuesOne()[1]+')'+"\r\n";
				c.eval("ty.min = c(" + OneModelWizardPage1.getExtremValuesOne()[1]+')');
				
				saveToR += "ty.min <- ty.min/100"+"\r\n";
				c.eval("ty.min <- ty.min/100");
				
				saveToR += "datm<-mort$datam"+"\r\n";
				c.eval("datm<-mort$datam");
	
				saveToR += "datm=rbind(data.frame(x=tx.min,y=ty.min),datm)"+"\r\n";
				c.eval("datm=rbind(data.frame(x=tx.min,y=ty.min),datm)");
			}else{
				saveToR+="datm<-mort$datam"+"\n";
				c.eval("datm<-mort$datam");
			}
			
			c.eval("png(file=" + '"' +MainPageWizardPage.getstrMortalityPath() + "/Mort-Model"+ (model)+".png"+'"'+")");
			estimateParameters(c,model, false, false, str1, str2, str3, str4, str5, str6);
			
			saveToR +="pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)" +"\n";
			c.eval("pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)");
			
			saveToR +="inim<-pbmortal$ini" +"\n";
			c.eval("inim<-pbmortal$ini");
			
			c.eval("dev.off()");
			OneModelWizardPage1.lblImageTemp.setImage(new Image(Display.getCurrent(), MainPageWizardPage.getstrMortalityPath()+ "/Mort-Model"+ (model)+".png"));
			c.close();
		}catch (RserveException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to estimate the parameters separately");
			c.close();
		} 
	}

	/** Metodo que guarda el modelo seleccionado (cmpOneModelPage) **/
	public static void setModelSelectedOne(int modelm, String str1, String str2, String str3, String str4, String str5, String str6){
		Double[] dbpars = null;
		try {
			c = new RConnection();
			
			estimateParameters(c, modelm, false,  false,str1, str2, str3, str4, str5, str6);
			
			saveToR +="pbmortal<-pruebamortal(modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)" +"\n";
			c.eval("pbmortal<-pruebamortal(modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)");
			c.eval("dev.off()");
			/*
			saveToR +="pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)" +"\n";
			c.eval("pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)");
			c.eval("dev.off()");
			*/
			saveToR +="inim<-pbmortal$ini" +"\n";
			c.eval("inim<-pbmortal$ini");
			
			c.eval("limit <- " + '"'+OneModelWizardPage1.getLimitsOne()+'"');
			
			/*iweightlo < 7){
				saveToR+="alg<-" + '"' + "Newton" + '"'+"\n";
				c.eval("alg<-" + '"' + "Newton" + '"');
				
				saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
				c.eval("weight<-" + '"' + "LS" + '"');
			}else{
				saveToR+="alg<-" + '"' + "Marquardtr" + '"'+"\n";
				c.eval("alg<-" + '"' + "Marquardtr" + '"');
				
			}*/
			
			saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
			c.eval("weight<-" + '"' + "LS" + '"');
			
			
			saveToR+="fmort<-dead_func("+ modelm+", datm, '"+ MainPageWizardPage.getSelectAlgo() +"', inim, weight,weights)"+"\n";
			c.eval("fmort<-dead_func("+ modelm+", datm, '"+MainPageWizardPage.getSelectAlgo()+"', inim, weight,weights)");
			
//			saveToR+="fmort<-dead_func(" + '"' + "mortal" + '"' +",modelm, datm, alg, inim, weight,weights)"+"\n";
//			c.eval("fmort<-dead_func(" + '"' + "mortal" + '"' +",modelm, datm, alg, inim, weight,weights)");
			
			
			saveToR+="coefEstimated<-fmort$estimatedCoef"+"\n";
			c.eval("coefEstimated<-fmort$estimatedCoef");
			
			saveToR+="stdmortg <-  fmort$stdmort"+"\n";
			c.eval("stdmortg <-  fmort$stdmort");
			
			saveToR+="model<- fmort$model"+"\n";
			c.eval("model<- fmort$model");
			
			saveToR+="gg<-fmort$f"+"\n";
			c.eval("gg<-fmort$f");
			
			
				
			c.eval("sink("+'"'+MainPageWizardPage.getstrMortalityPath() + "/"+title + ".txt"+'"'+")");
			saveToR+="sol_mort<-coef_mort(modelm,coefEstimated, stdmortg, model,gg,datm,'"+ MainPageWizardPage.getSelectAlgo() +"',weight,weights)"+"\n";
			c.eval("sol_mort<-coef_mort(modelm,coefEstimated, stdmortg, model,gg,datm,'"+ MainPageWizardPage.getSelectAlgo() +"',weight,weights)");
			
			/*
			c.eval("sink()");
			
			saveToR+="sol_mort<-coef_mort(" + '"' + "mortal" + '"' +",modelm,coefEstimated, stdmortg, modelo,modelim,datm,alg,weight,weights)"+"\n";
			c.eval("sol_mort<-coef_mort(" + '"' + "mortal" + '"' +",modelm,coefEstimated, stdmortg, modelo,modelim,datm,alg,weight,weights)");
			*/
			
			
			saveToR+="Std.Error<-sol_mort$Std.Error"+"\r\n";
			c.eval("Std.Error<-sol_mort$Std.Error");
			
			saveToR+="param<-sol_mort$parmor"+"\r\n";
			c.eval("param<-sol_mort$parmor");
			
			
			c.eval("sink()");
			
			c.eval("png(file=" + '"' +MainPageWizardPage.getstrMortalityPath() + "/" + title +".png"+'"'+")");
			
			saveToR+="plot_mort<-grafmort(modelm,coefEstimated,g,datm,corrx,corry,mini,maxi,limit,1,labx,laby,titulo, grises)"+"\n";
			c.eval("plot_mort<-grafmort(modelm,coefEstimated,g,datm,corrx,corry,mini,maxi,limit,1,labx,laby,titulo, grises)");
			c.eval("dev.off()");
			
						
			saveToR += "valxs=plot_mort$valxs";
			c.eval("valxs=plot_mort$valxs");
			
			dbpars= saveFinalPar("coefEstimated", modelm);
			pars.setParameters(dbpars);
			pars.setStrModel(getModelName(modelm+""));
			pars.setStdParameters(dbStdpars);
			
			MaizeDevUtils.createHTMLfile(MainPageWizardPage.getstrMortalityPath(), "MaizeDevRate-"+MainPageWizardPage.comboDevStage.getText());
//			File temp = new File(MainPageWizardPage.getstrMortalityPath()+ File.separator + title + ".txt");
//			temp.delete();
			
			OneModelWizardPage2.lblImageFinal.setImage(new Image(Display.getCurrent(), MainPageWizardPage.getstrMortalityPath()+ File.separator +imageName));
	//		OneModelWizardPage2.brwModelSel.setUrl("file:///" + MainPageWizardPage.getstrMortalityPath()+ File.separator + title + ".html");
			OneModelWizardPage2.brwModelSel.setUrl(MainPageWizardPage.getstrMortalityPath()+ File.separator + title + ".html");
			OneModelWizardPage2.lblModelSelOne.setText(pars.getStrModel());
			
			//here we have to save R workSave in for the use for the mapping
			saveToR+="save(list = ls(all.names = TRUE), file = "+ '"' + MainPageWizardPage.getstrMortalityPath() + "/" + title + ".RData"+'"'+", envir = .GlobalEnv)"+"\n";
			c.eval("save(list = ls(all.names = TRUE), file = "+ '"' + MainPageWizardPage.getstrMortalityPath() + "/" + title + ".RData"+'"'+", envir = .GlobalEnv)");
			
			c.close();
		}catch (RserveException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			try {
				c.eval("sink()");
			} catch (RserveException e1) {
				c.close();
				e1.printStackTrace();
			}
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to set one model selected");
		} catch (REXPMismatchException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			try {
				c.eval("sink()");
			} catch (RserveException e1) {
				c.close();
				e1.printStackTrace();
			}
			c.close();
			e.printStackTrace();
		}
	}
	
	

	/** Metodo que modifica los paramteros aleatoriamente **/
	public static void randomParameters(){
		try {
			c = new RConnection();
			
			rexpAnt = rexpNew;
			
			c.eval("niv=" + OneModelWizardPage1.txtAdjust.getText());
			c.eval("png(file=" + '"' + MainPageWizardPage.getstrMortalityPath().replace("\\", "/") + "/" + imageName + '"' + ")");
			
			
			c.eval("iniback <- inim1");
			c.eval("inim1=recalc(inim,niv=niv"+")");
			c.eval("pbmortal<-pruebamortal(modelm,datm,inim1,corrx,corry,mini,maxi,labx,laby,titulo)");
			c.voidEval("dev.off()");
			
			OneModelWizardPage1.lblImageTemp.setImage(new Image(Display.getCurrent(), MainPageWizardPage.getstrMortalityPath() + File.separator+ imageName));
			
			rexpNew = c.eval("inim1");
			try {
				OneModelWizardPage1.txtPar1.setText(df.format(c.eval("inim1["+'"'+OneModelWizardPage1.lblPar1.getText()+'"'+"]").asList().at(0).asDouble())+"");
				OneModelWizardPage1.txtPar2.setText(df.format(c.eval("inim1["+'"'+OneModelWizardPage1.lblPar2.getText()+'"'+"]").asList().at(0).asDouble())+"");
				OneModelWizardPage1.txtPar3.setText(df.format(c.eval("inim1["+'"'+OneModelWizardPage1.lblPar3.getText()+'"'+"]").asList().at(0).asDouble())+"");
				if(!OneModelWizardPage1.lblPar4.getText().equalsIgnoreCase(""))
					OneModelWizardPage1.txtPar4.setText(df.format(c.eval("inim1["+'"'+OneModelWizardPage1.lblPar4.getText()+'"'+"]").asList().at(0).asDouble())+"");
				if(!OneModelWizardPage1.lblPar5.getText().equalsIgnoreCase(""))
					OneModelWizardPage1.txtPar5.setText(df.format(c.eval("inim1["+'"'+OneModelWizardPage1.lblPar5.getText()+'"'+"]").asList().at(0).asDouble())+"");
				
			} catch (REXPMismatchException e) {
				Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
				c.close();
				e.printStackTrace();
			}catch (Exception e) {
				MessageDialog.openError(new Shell(), title, "Problems while trying to random the parameters");
				c.close();
				e.printStackTrace();
			}
			
			bolBackPars = false;
			c.close();
		} catch (RserveException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			c.close();
			MessageDialog.openError(new Shell(), title, "Problems while trying to random the parameters");
			e.printStackTrace();
		}
	}
	/** Metodo que devuelve los parametros anteriores **/
	public static void backParameters(){
		try{
			c = new RConnection();
			
			if(rexpNew == null){
				c.close();
				return;
			}
			
			try {
				OneModelWizardPage1.txtPar1.setText(df.format(rexpAnt.asList().at(OneModelWizardPage1.lblPar1.getText()).asDouble()));
				OneModelWizardPage1.txtPar2.setText(df.format(rexpAnt.asList().at(OneModelWizardPage1.lblPar2.getText()).asDouble()));
				OneModelWizardPage1.txtPar3.setText(df.format(rexpAnt.asList().at(OneModelWizardPage1.lblPar3.getText()).asDouble()));
				if(!OneModelWizardPage1.lblPar4.getText().equalsIgnoreCase(""))
					OneModelWizardPage1.txtPar4.setText(df.format(rexpAnt.asList().at(OneModelWizardPage1.lblPar4.getText()).asDouble()));
				if(!OneModelWizardPage1.lblPar5.getText().equalsIgnoreCase(""))
					OneModelWizardPage1.txtPar5.setText(df.format(rexpAnt.asList().at(OneModelWizardPage1.lblPar5.getText()).asDouble()));
				
			} catch (REXPMismatchException e) {
				Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
				c.close();
				e.printStackTrace();
			}catch (Exception e) {
				MessageDialog.openError(new Shell(), title, "Problems while trying to get the previous parameters");
				c.close();
				e.printStackTrace();
			}
			
			c.eval("png(file=" + '"' + MainPageWizardPage.getstrMortalityPath().replace("\\", "/") + "/" + imageName + '"' + ")");
			c.eval("pbmortal<-pruebamortal(modelm,datm,iniback,corrx,corry,mini,maxi,labx,laby,titulo)");
			c.voidEval("dev.off()");
			
			OneModelWizardPage1.lblImageTemp.setImage(new Image(Display.getCurrent(), MainPageWizardPage.getstrMortalityPath() + File.separator + imageName));
			bolBackPars = true;
			c.close();
			
		} catch (RserveException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to get the previous parameters");
		}	
	}
	/** Metodo que guarda los parametros momentaneamente **/
	public static void setParameters(){
		try{
			c = new RConnection();
			
			if(bolBackPars)
				c.eval("inim=iniback;niv=niv/2");
			else
				c.eval("inim=inim1;niv=niv/2");
			
			c.eval("inim<-pbmortal$ini");
			
			OneModelWizardPage1.txtAdjust.setText(c.eval("niv").asDouble()+"");
			c.close();
		}catch (RserveException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to set the parameters");
		} catch (REXPMismatchException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to set the parameters");
		}	
	}
	
	/** Guarda en el archivo resume el modelo seleccionado **/
	public static boolean saveModelSelected(){
		if(MainPageWizardPage.getSeveralModels()){
			if(SeveralModelsWizardPage.lblModelSelAll.getText().equalsIgnoreCase("") 
				&& SeveralModelsWizardPage.txtParametersEstimatedAll.getText().equalsIgnoreCase("")){
				MessageDialog.openError(SeveralModelsWizardPage.container.getShell(), title, 
				"You must to select correctly the model");
				return false;
			}
		}
		deleteAllTempFiles();
		guardarResume();
	//	newSaveProgress();
		return true;
	}
	
	/** elimina los archivos temporales que se generan a la hora de procesar los modelos **/
	private static void deleteAllTempFiles(){
		String[] arrayFiles = new File(MainPageWizardPage.getstrMortalityPath()).list();
		
		for(int i=0; i<arrayFiles.length; i++){
			if(new File(MainPageWizardPage.getstrMortalityPath().replace('/', '\\')+File.separator+arrayFiles[i]).isFile())
				if(!arrayFiles[i].startsWith("MaizeDevRate"))
					new File(MainPageWizardPage.getstrMortalityPath().replace('/', '\\')+ File.separator+arrayFiles[i]).delete();
		}
	}
	/** guarda el archivo resume con el modelo y parametros seleccionados **/
	private static void guardarResume(){
		File fResume = new File(MainPageWizardPage.getstrMortalityPath().replace('/', '\\')+"resume.mzdvr");  //file.createNewFile()
		try {
			fResume.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
				//new File("\""+(MainActionMaizeDevRate.pathProj + File.separator +"resume.ilcym").replace("\\", "/")+"\"");//modified for testing
		System.out.println(fResume);
		//File fResume = new File(MainActionMaizeDevRate.pathProj + File.separator+title + File.separator +"resume.epfa");
		//String numStages=ViewProjectsUI.getNumStage(MainPageWizardPage.getStageSel());
		String p="", std="";
		
		try{
			c = new RConnection();
			for(int i=0;i<pars.getParametersName().length;i++){
				if(!pars.getParametersName()[i].equalsIgnoreCase("")){
					p += pars.getParametersName()[i] + "=" + pars.getParameters()[i] + " ";
					std += pars.getParametersName()[i] + "=" + pars.getStdParameters()[i] + " ";
				}
				
			}
			
			Properties prop = new Properties();
			try {
				prop.load(new FileInputStream(fResume));
//				prop.setProperty("Stage"+numStages, MainPageWizardPage.getStageSel());
				
				prop.setProperty("Algorithm", MainPageWizardPage.getSelectAlgo());
				prop.setProperty("Model", pars.getStrModel());
				prop.setProperty("Parameters", p.trim());
				prop.setProperty("Std.Error", std.trim());
				prop.setProperty("Formula", functToString(c.eval("toString(gg)")));
				prop.store(new FileOutputStream(fResume), "MaizeDevRate Resume");
				prop.clear();
				
				c.close();
			} catch (FileNotFoundException e1) {
				MessageDialog.openError(new Shell(), title, "Problems while trying to save the resume sheet");
				c.close();
				e1.printStackTrace();
			} catch (IOException e1) {
				MessageDialog.openError(new Shell(), title, "Problems while trying to save the resume sheet");
				c.close();
				e1.printStackTrace();
			} catch (RserveException e) {
				Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
				c.close();
				e.printStackTrace();
			} catch (REXPMismatchException e) {
				Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
				c.close();
				e.printStackTrace();
			}
			
		}catch (Exception e) {
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while saving the parameters in the resume sheet");
			return;
		}
		
	
	}
	/** guarda el proceso completado en el archivo progress **/
	private static void newSaveProgress(){
		File fileProg = new File(MainActionMaizeDevRate.pathProj + File.separator + "Progress.epfa");
		try {
			if(!fileProg.exists())
				fileProg.createNewFile();
			
			BufferedReader in = new BufferedReader(new FileReader(fileProg));
	        String str,newfile="";
	        while ((str = in.readLine()) != null) {
	        	String[]lineT = ArrayConvertions.StringtoArray(str, "\t");
	        	if(lineT[0].trim().equalsIgnoreCase(MainPageWizardPage.getStageSel())){
	        		lineT[4] = "true";
	        	}
	        	
	        	if(ViewProjectsUI.getRate().contains("Age") || ViewProjectsUI.getRate().contains("Temperature"))
	        		newfile+=lineT[0]+"\t"+lineT[1]+"\t"+lineT[2]+"\t"+lineT[3]+"\t"+lineT[4]+"\t"+lineT[5]+"\t"+lineT[6]+"\t"+lineT[7]+"\r\n";
	        	else
	        		newfile+=lineT[0]+"\t"+lineT[1]+"\t"+lineT[2]+"\t"+lineT[3]+"\t"+lineT[4]+"\t"+lineT[5]+"\t"+lineT[6]+"\r\n";
	        }
	        
	        BufferedWriter bw = new BufferedWriter(new FileWriter(fileProg));
	        bw.write(newfile);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to save the progress project");
		}	
	}
	
	/** Metodo para reiniciar los valores  de las caracteristicas de la imagen resultante**/
	//public static void restoreImage(String stageLabel, String pathImage, Label lblImage, int model)
	public static void restoreImage( String pathImage, Label lblImage, int model){
		try {
			c=new RConnection();
			c.eval("png(file=" + '"' +pathImage.replace('\\', '/') +'"'+")");
			
			if(MainPageWizardPage.getSeveralModels())
				estimateParameters(c,model, true, false, "", "", "", "", "","");
			else{
				String str1,str2,str3,str4,str5, str6;
				str1 = pars.getParameters()[0];
				str2 = pars.getParameters()[1];
				str3 = pars.getParameters()[2];
				str4 = pars.getParameters()[3];
				str5 = pars.getParameters()[4];
				str6 = pars.getParameters()[5];
				estimateParameters(c,model, false, true, str1, str2, str3, str4, str5, str6);
			}
			
			c.eval("pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)");
			c.eval("inim<-pbmortal$ini");
			
			c.eval("fmort<-dead_func(" + '"' + "mortal" + '"' +",modelm, datm, alg, inim, weight,weights)");
			c.eval("coefEstimated<-fmort$estimados");
			c.eval("g<-fmort$ecua");
			c.eval("gg<-fmort$f");
			c.eval("stdmortg <-  fmort$stdmort");
			c.eval("modelo<- fmort$modelo");
			c.eval("modelim<-fmort$modelo");
			c.eval("sol_mort<-coef_mort(" + '"' + "mortal" + '"' +",modelm,coefEstimated, stdmortg, modelo,modelim,datm,alg,weight,weights)");
			c.eval("plot_mort<-grafmort(" + '"' + "mortal" + '"' + ",modelm,coefEstimated,g,datm,corrx=c(0,40),corry=c(0,100),0,100,limit,1," + "labx="+'"'+"temperature (degree celsius)"+'"'+",laby="+'"'+"mortality (%)"+'"'+",titulo="+'"'+""+'"'+
					",grises=FALSE)");
			
			c.eval("dev.off()");
			lblImage.setImage(new Image(Display.getCurrent(), pathImage.replace('/', '\\')));
			c.close();
		} catch (RserveException e) {
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to restore the graph");
		}
	}
	
	
	public static void estimateParameters(RConnection c, int model, boolean many, boolean modify, String str1, String str2, String str3, String str4, String str5, String str6, String str7){
		try{
			saveToR+="\r\n"+ "model<-"+ model +"\r\n";
			c.eval("model<-"+model);
			
			switch (model) {
			case 1:
				if(many){
					saveToR+="xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]"+"\r\n";
					c.eval("xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]");
					
					saveToR+="yha<-(1/(datashap[,1]+273.15))[xha1 != -100]"+"\r\n";
					c.eval("yha<-(1/(datashap[,1]+273.15))[xha1 != -100]");
					
					saveToR+="beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+="xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+="yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+="cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+="ini <- list(Ha = -beta*1.987,Tl = -cof[1]/cof[2]+2, Hl =-100000 ,Hh =200000, Th =datashap[(order(datashap[,2]))[nrow(datashap)],1]+273.15-2)"+"\r\n";
					c.eval("ini <- list(Ha = -beta*1.987,Tl = -cof[1]/cof[2]+2, Hl =-100000 ,Hh =200000, Th =datashap[(order(datashap[,2]))[nrow(datashap)],1]+273.15-2)");
				}else{
					saveToR+="ini <- list(Ha = " + str3 + ",Tl = " + str1 + ", Hl ="+ str4 + ",Hh ="+ str5 +", Th =" + str2 + ")"+"\r\n";
					c.eval("ini <- list(Ha = " + str3 + ",Tl = " + str1 + ", Hl ="+ str4 + ",Hh ="+ str5 +", Th =" + str2 + ")");
					
					if(modify)
						c.eval("ini <- list(Ha = " + str5 + ",Tl = " + str3 + ", Hl ="+ str6 + ",Hh ="+ str7 +", Th =" + str4 + ")");
				}
				break;
			case 2:
				if(many){
					saveToR+="xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]"+"\r\n";
					c.eval("xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]");
					
					saveToR+="yha<-(1/(datashap[,1]+273.15))[xha1 != -100]"+"\r\n";
					c.eval("yha<-(1/(datashap[,1]+273.15))[xha1 != -100]");
					
					saveToR+= "beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+= "xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+= "yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+= "cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+= "ini <- list(To=median(datashap[,1])+273.15,Ha = -beta*1.987,Tl = -cof[1]/cof[2]+1, Hl =-10000 ,Hh =200000, Th =datashap[(order(datashap[,2]))[nrow(datashap)],1]+273.15+2)"+"\r\n";
					c.eval("ini <- list(To=median(datashap[,1])+273.15,Ha = -beta*1.987,Tl = -cof[1]/cof[2]+1, Hl =-10000 ,Hh =200000, Th =datashap[(order(datashap[,2]))[nrow(datashap)],1]+273.15+2)");
				}else{
					saveToR+="ini <- list(To=" + str1 + ",Ha =" + str4 + ",Tl =" + str2 + ", Hl =" + str5 + ",Hh =" + str6 + ", Th =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(To=" + str1 + ",Ha =" + str4 + ",Tl =" + str2 + ", Hl =" + str5 + ",Hh =" + str6 + ", Th =" + str3 + ")");
					
					if(modify)
						c.eval("ini <- list(To=" + str2 + ",Ha =" + str5 + ",Tl =" + str3 + ", Hl =" + str6 + ",Hh =" + str7 + ", Th =" + str4 + ")");
				}
				
				break;
			case 3:
				if(many){
					saveToR+="xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]"+"\r\n";
					c.eval("xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]");
					
					saveToR+="yha<-(1/(datashap[,1]+273.15))[xha1 != -100]"+"\r\n";
					c.eval("yha<-(1/(datashap[,1]+273.15))[xha1 != -100]");
					
					saveToR+= "beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+= "xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+= "yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+= "cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+= "ini <- list(p=median(datashap[,2]),To=median(datashap[,1])+273.15,Ha = -beta* 1.987,Tl =-cof[1]/cof[2] +2, Hl =-100000 ,Hh =200000, Th =datashap[(order(datashap[,2]))[nrow(datashap)],1]+273.15)"+"\n";
					c.eval("ini <- list(p=median(datashap[,2]),To=median(datashap[,1])+273.15,Ha = -beta* 1.987,Tl =-cof[1]/cof[2] +2, Hl =-100000 ,Hh =200000, Th =datashap[(order(datashap[,2]))[nrow(datashap)],1]+273.15)");
				}else{
					saveToR+="ini <- list(p=" + str1 + ",To=" + str2 + ",Ha =" + str5 + ",Tl =" + str3 + ", Hl =" + str6 + ",Hh =" + str7 + ", Th =" + str4 + ")"+"\r\n";
					c.eval("ini <- list(p=" + str1 + ",To=" + str2 + ",Ha =" + str5 + ",Tl =" + str3 + ", Hl =" + str6 + ",Hh =" + str7 + ", Th =" + str4 + ")");
				}
				break;
	
			case 4:
				if(many){
					saveToR+="xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]"+"\r\n";
					c.eval("xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]");
					
					saveToR+="yha<-(1/(datashap[,1]+273.15))[xha1 != -100]"+"\r\n";
					c.eval("yha<-(1/(datashap[,1]+273.15))[xha1 != -100]");
					
					saveToR+="beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+="xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+="yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+="cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+="ini <- list(p=median(datashap[,2]),To=median(datashap[,1])+273.15,Ha = -beta* 1.987)"+"\r\n";
					c.eval("ini <- list(p=median(datashap[,2]),To=median(datashap[,1])+273.15,Ha = -beta* 1.987)");
				}else{
					saveToR+="ini <- list(p=" + str1 + ",To=" + str2 + ",Ha =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(p=" + str1 + ",To=" + str2 + ",Ha =" + str3 + ")");
				}
				break;
			case 5:
				if(many){
					saveToR+="xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]"+"\r\n";
					c.eval("xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]");
					
					saveToR+="yha<-(1/(datashap[,1]+273.15))[xha1 != -100]"+"\r\n";
					c.eval("yha<-(1/(datashap[,1]+273.15))[xha1 != -100]");
					
					saveToR+="beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+="xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+="yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+="cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+="ini <- list(p=median(datashap[,2]),To=median(datashap[,1])+273.15,Ha = -beta* 1.987,Tl =-cof[1]/cof[2] +2, Hl =-100000)"+"\r\n";
					c.eval("ini <- list(p=median(datashap[,2]),To=median(datashap[,1])+273.15,Ha = -beta* 1.987,Tl =-cof[1]/cof[2] +2, Hl =-100000)");
				}else{
					saveToR+="ini <- list(p=" + str1 + ",To=" + str2 + ",Ha =" + str4 + ",Tl =" + str3 + ", Hl =" + str5 + ")"+"\r\n";
					c.eval("ini <- list(p=" + str1 + ",To=" + str2 + ",Ha =" + str4 + ",Tl =" + str3 + ", Hl =" + str5 + ")");
				}
				break;
			case 6:
				if(many){
					saveToR+="xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]"+"\r\n";
					c.eval("xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]");
					
					saveToR+="yha<-(1/(datashap[,1]+273.15))[xha1 != -100]"+"\r\n";
					c.eval("yha<-(1/(datashap[,1]+273.15))[xha1 != -100]");
					
					saveToR+="beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+="xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+="yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+="cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+="ini <- list(p=median(datashap[,2]),To=median(datashap[,1])+273.15,Ha = -beta* 1.987,Hh =200000, Th =datashap[(order(datashap[,2]))[nrow(datashap)],1]+273.15)"+"\r\n";
					c.eval("ini <- list(p=median(datashap[,2]),To=median(datashap[,1])+273.15,Ha = -beta* 1.987,Hh =200000, Th =datashap[(order(datashap[,2]))[nrow(datashap)],1]+273.15)");
				}else{
					saveToR+="ini <- list(p=" + str1 + ",To=" + str2 + ",Ha =" + str4  + ",Hh =" + str5 + ", Th =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(p=" + str1 + ",To=" + str2 + ",Ha =" + str4  + ",Hh =" + str5 + ", Th =" + str3 + ")");
				}
				break;
			case 7:
				if(many){
					saveToR+="xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]"+"\r\n";
					c.eval("xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]");
					
					saveToR+="yha<-(1/(datashap[,1]+273.15))[xha1 != -100]"+"\r\n";
					c.eval("yha<-(1/(datashap[,1]+273.15))[xha1 != -100]");
					
					saveToR+="beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+="xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+="yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+="cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+="ini <- list(To=median(datashap[,1])+273.15,Ha = -beta* 1.987)"+"\r\n";
					c.eval("ini <- list(To=median(datashap[,1])+273.15,Ha = -beta* 1.987)");
				}else{
					saveToR+="ini <- list(To=" + str1 + ",Ha =" + str2 + ")"+"\r\n";
					c.eval("ini <- list(To=" + str1 + ",Ha =" + str2 + ")");
					
					if(modify)
						c.eval("ini <- list(To=" + str2 + ",Ha =" + str3 + ")");
				}
				break;
			case 8:
				if(many){
					saveToR+="xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]"+"\r\n";
					c.eval("xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]");
					
					saveToR+="yha<-(1/(datashap[,1]+273.15))[xha1 != -100]"+"\r\n";
					c.eval("yha<-(1/(datashap[,1]+273.15))[xha1 != -100]");
					
					saveToR+= "beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+= "xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+= "yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+= "cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+= "ini <- list(To=median(datashap[,1])+273.15,Ha = -beta* 1.987,Tl =-cof[1]/cof[2] +2, Hl =-100000 )"+"\r\n";
					c.eval("ini <- list(To=median(datashap[,1])+273.15,Ha = -beta* 1.987,Tl =-cof[1]/cof[2] +2, Hl =-100000 )");
				}else{
					saveToR+="ini <- list(To=" + str1 + ",Ha =" + str3 + ",Tl =" + str2 + ", Hl =" + str4 + ")"+"\r\n";
					c.eval("ini <- list(To=" + str1 + ",Ha =" + str3 + ",Tl =" + str2 + ", Hl =" + str4 + ")");
					
					if(modify)
						c.eval("ini <- list(To=" + str2 + ",Ha =" + str4 + ",Tl =" + str3 + ", Hl =" + str5 + ")");
				}
				break;
			case 9:
				if(many){
					saveToR+="xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]"+"\r\n";
					c.eval("xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]");
					
					saveToR+="yha<-(1/(datashap[,1]+273.15))[xha1 != -100]"+"\r\n";
					c.eval("yha<-(1/(datashap[,1]+273.15))[xha1 != -100]");
					
					saveToR+= "beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+= "xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+= "yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+= "cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+= "ini <- list(To=median(datashap[,1])+273.15,Ha = -beta* 1.987,Hh =200000, Th =as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]+273.15)"+"\r\n";
					c.eval("ini <- list(To=median(datashap[,1])+273.15,Ha = -beta* 1.987,Hh =200000, Th =datashap[(order(datashap[,2]))[nrow(datashap)],1]+273.15)");
				}else{
					saveToR+="ini <- list(To=" + str1 + ",Ha =" + str3  + ",Hh =" + str4 + ", Th =" + str2 + ")"+"\r\n";
					c.eval("ini <- list(To=" + str1 + ",Ha =" + str3  + ",Hh =" + str4 + ", Th =" + str2 + ")");
					
					if(modify)
						c.eval("ini <- list(To=" + str2 + ",Ha =" + str4  + ",Hh =" + str5 + ", Th =" + str3 + ")");
				}
				break;
			case 10:
				if(many){
					saveToR+="xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]"+"\r\n";
					c.eval("xha1<-log(datashap[,2]);xha=xha1[xha1 != -100]");
					
					saveToR+="yha<-(1/(datashap[,1]+273.15))[xha1 != -100]"+"\r\n";
					c.eval("yha<-(1/(datashap[,1]+273.15))[xha1 != -100]");
					
					saveToR+= "beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+= "xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+= "yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+= "cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+= "ini <- list(p=median(datashap[,2]),Ha = -beta* 1.987,Tl =-cof[1]/cof[2] +2, Hl =-100000)"+"\r\n";
					c.eval("ini <- list(p=median(datashap[,2]),Ha = -beta* 1.987,Tl =-cof[1]/cof[2] +2, Hl =-100000)");
				}else{
					saveToR+= "ini <- list(p=" + str1 + ",Ha ="+ str3 + ",Tl ="+ str2 +", Hl ="+str4 +")"+"\r\n";
					c.eval("ini <- list(p=" + str1 + ",Ha ="+ str3 + ",Tl ="+ str2 +", Hl ="+str4 +")");
				}
				break;
			case 11:
				if(many){
					saveToR+="yha<-1/(datashap[,1]+273.15)"+"\r\n";
					c.eval("yha<-1/(datashap[,1]+273.15)");
					
					saveToR+="xha<-log(datashap[,2])"+"\r\n";
					c.eval("xha<-log(datashap[,2])");
					
					saveToR+="beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+="xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+="yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+="cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+="ini <- list(p=median(datashap[,2]),Ha = -beta* 1.987,Tl =-cof[1]/cof[2] +2, Hl =-100000 ,Hh =200000, Th =as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]+273.15)"+"\r\n";
					c.eval("ini <- list(p=median(datashap[,2]),Ha = -beta* 1.987,Tl =-cof[1]/cof[2] +2, Hl =-100000 ,Hh =200000, Th =as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]+273.15)");
				}else{
					saveToR+="ini <- list(p=" + str1 + ",Ha =" + str4 + ",Tl =" + str2 + ", Hl =" + str5 + ",Hh =" + str6 + ", Th =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(p=" + str1 + ",Ha =" + str4 + ",Tl =" + str2 + ", Hl =" + str5 + ",Hh =" + str6 + ", Th =" + str3 + ")");
				}
				break;
			case 12:
				if(many){
					saveToR+="yha<-1/(datashap[,1]+273.15)"+"\r\n";
					c.eval("yha<-1/(datashap[,1]+273.15)");
					
					saveToR+="xha<-log(datashap[,2])"+"\r\n";
					c.eval("xha<-log(datashap[,2])");
					
					saveToR+="beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+="xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+="yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+="cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+="ini <- list(Ha = -beta* 1.987, Hl =-100000 , Tl =-cof[1]/cof[2] +2, Hh =200000, Th =300)"+"\r\n";
					c.eval("ini <- list(Ha = -beta* 1.987, Hl =-100000 , Tl =-cof[1]/cof[2] +2, Hh =200000, Th =300)");
				}else{
					saveToR+="ini <- list(Hh=" + str5 + ",Tl=" + str1 + ",Ha =" + str3  + ",Hl =" + str4 + ", Th =" + str2 + ")"+"\r\n";
					c.eval("ini <- list(Hh=" + str5 + ",Tl=" + str1 + ",Ha =" + str3  + ",Hl =" + str4 + ", Th =" + str2 + ")");
				}
				break;
			case 13:
				if(many){
					saveToR+="yha<-1/(datashap[,1]+273.15)"+"\r\n";
					c.eval("yha<-1/(datashap[,1]+273.15)");
					
					saveToR+="xha<-log(datashap[,2])"+"\r\n";
					c.eval("xha<-log(datashap[,2])");
					
					saveToR+="beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+="xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+="yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+="cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+="ini <- list(p=median(datashap[,2]),Ha = -beta* 1.987, Hl =-100000 ,Tl =-cof[1]/cof[2] +2)"+"\r\n";
					c.eval("ini <- list(p=median(datashap[,2]),Ha = -beta* 1.987, Hl =-100000 ,Tl =-cof[1]/cof[2] +2)");
				}else{
					saveToR+= "ini <- list(p=" + str1 + ",Ha ="+ str3 + ",Tl ="+ str2 +", Hl ="+str4 +")"+"\r\n";
					c.eval("ini <- list(p=" + str1 + ",Ha ="+ str3 + ",Tl ="+ str2 +", Hl ="+str4 +")");
				}
				break;
			case 14:
				if(many){
					saveToR+="yha<-1/(datashap[,1]+273.15)"+"\r\n";
					c.eval("yha<-1/(datashap[,1]+273.15)");
					
					saveToR+="xha<-log(datashap[,2])"+"\r\n";
					c.eval("xha<-log(datashap[,2])");
					
					saveToR+="beta<-as.numeric(coef(lm(xha~yha))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(xha~yha))[2])");
					
					saveToR+="xli<-(datashap[,1]+273.15)"+"\r\n";
					c.eval("xli<-(datashap[,1]+273.15)");
					
					saveToR+="yli<-datashap[,2]"+"\r\n";
					c.eval("yli<-datashap[,2]");
					
					saveToR+="cof<-as.numeric(coef(lm(yli~xli)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli~xli)))");
					
					saveToR+="ini <- list(p=median(datashap[,2]),Ha = -beta* 1.987, Hh =200000, Th =300)"+"\r\n";
					c.eval("ini <- list(p=median(datashap[,2]),Ha = -beta* 1.987, Hh =200000, Th =300)");
				}else{
					saveToR+= "ini <- list(p=" + str1 + ",Ha ="+ str3 + ",Th ="+ str2 +", Hh ="+str4 +")"+"\r\n";
					c.eval("ini <- list(p=" + str1 + ",Ha ="+ str3 + ",Th ="+ str2 +", Hh ="+str4 +")");
				}
				break;
			case 15:/**Deva 1**/
				if(many){
					saveToR+= "xli<-datashap[,1][2:9]"+"\r\n";
					c.eval("xli<-datashap[,1][2:9]");
					
					saveToR+= "yli<-datashap[,2][2:9]"+"\r\n";
					c.eval("yli<-datashap[,2][2:9]");
					
					saveToR+= "beta<-as.numeric(coef(lm(yli~xli))[2])"+"\r\n";
					c.eval("beta<-as.numeric(coef(lm(yli~xli))[2])");
					
					saveToR+= "ini <- list(Tmin = min(datashap[,1]), b = beta)"+"\r\n";
					c.eval("ini <- list(Tmin = min(datashap[,1]), b = beta)");
				}else{
					saveToR+="ini <- list(Tmin=" + str1 + ",b=" + str2 + ")"+"\r\n";
					c.eval("ini <- list(Tmin=" + str1 + ",b=" + str2 + ")");
				}
				break;
			case 16:/**Deva 2**/
				if(many){
					saveToR+= "ini <- list(b1=0.0001630595,b2=-74.79061,b3=-38.76691,b4=-3.6201e-05,b5=-0.3245052 )"+"\r\n";
					c.eval("ini <- list(b1=0.0001630595,b2=-74.79061,b3=-38.76691,b4=-3.6201e-05,b5=-0.3245052)");
				}else{
					saveToR+="ini <- list(b1=" + str1 + ",b2=" + str2 + ",b4 =" + str4  + ",b5 =" + str5 + ", b3 =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(b1=" + str1 + ",b2=" + str2 + ",b4 =" + str4  + ",b5 =" + str5 + ", b3 =" + str3 + ")");
				}
				break;
			case 17:/**Logan 1**/
				if(many){
					saveToR+= "ini <- list(Y=min(datashap[,2]),Tmax = max(datashap[,1]), p = median(datashap[,2]), v=3.5)"+"\r\n";
					c.eval("ini <- list(Y=min(datashap[,2]),Tmax = max(datashap[,1]), p = median(datashap[,2]), v=3.5)");
				}else{
					saveToR+="ini <- list(Y=" + str1 + ",p=" + str3 + ",v =" + str4  + ", Tmax =" + str2 + ")"+"\r\n";
					c.eval("ini <- list(Y=" + str1 + ",p=" + str3 + ",v =" + str4  + ", Tmax =" + str2 + ")");
				}
				break;
			case 18:/**Logan 2**/
				if(many){
					saveToR+= "ini <- list(alfa=max(datashap[,2])+.2*max(datashap[,2]),k=100,Tmax = max(datashap[,1]), p = median(datashap[,2]), v=0.05)"+"\r\n";
					c.eval("ini <- list(alfa=max(datashap[,2])+.2*max(datashap[,2]),k=100,Tmax = max(datashap[,1]), p = median(datashap[,2]), v=0.05)");
				}else{
					saveToR+="ini <- list(alfa=" + str1 + ",k=" + str2 + ",Tmax =" + str3  + ",v =" + str5 + ", p =" + str4 + ")"+"\r\n";
					c.eval("ini <- list(alfa=" + str1 + ",k=" + str2 + ",Tmax =" + str3  + ",v =" + str5 + ", p =" + str4 + ")");
				}
				break;
			case 19:/**Briere 1**/
				if(many){
					saveToR+= "ini <- list(aa=0.00003,To=min(datashap[,1])-4,Tmax =max(datashap[,1])+90)"+"\r\n";
					c.eval("ini <- list(aa=0.00003,To=min(datashap[,1])-4,Tmax =max(datashap[,1])+90)");
				}else{
					saveToR+="ini <- list(aa=" + str1 + ",To=" + str2 + ",Tmax =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(aa=" + str1 + ",To=" + str2 + ",Tmax =" + str3 + ")");
				}
				break;
			case 20:/**Briere 2**/
				if(many){
					saveToR+= "ini <- list(aa=0.00001,To=min(datashap[,1])-4,Tmax =max(datashap[,1])+90,d=0.5)"+"\r\n";
					c.eval("ini <- list(aa=0.00001,To=min(datashap[,1])-4,Tmax =max(datashap[,1])+90,d=0.5)");
				}else{
					saveToR+="ini <- list(aa=" + str1 + ",To=" + str2 + ",d =" + str4  + ", Tmax =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(aa=" + str1 + ",To=" + str2 + ",d =" + str4  + ", Tmax =" + str3 + ")");
				}
				break;
			case 21:/**Stinner 1**/
				if(many){
					saveToR+= "xstinner<-c(min(datashap[,1]),max(datashap[,1]))"+"\r\n";
					c.eval("xstinner<-c(min(datashap[,1]),max(datashap[,1]))");
					
					saveToR+= "ystinner<-c(log(as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]/min(datashap[,2])-1),log(as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]/max(datashap[,2])-1))"+"\r\n";
					c.eval("ystinner<-c(log(as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]/min(datashap[,2])-1),log(as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]/max(datashap[,2])-1))");
					
					saveToR+= "ks<-as.numeric(coef(lm(ystinner~xstinner)))"+"\r\n";
					c.eval("ks<-as.numeric(coef(lm(ystinner~xstinner)))");
					
					saveToR+= "ini <- list(Rmax = max(datashap[,2]),Topc=as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1],k1 = ks[1], k2 = ks[2])"+"\r\n";
					c.eval("ini <- list(Rmax = max(datashap[,2]),Topc=as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1],k1 = ks[1], k2 = ks[2])");
				}else{
					saveToR+="ini <- list(Rmax=" + str1 + ",Topc=" + str2 + ",k2 =" + str4  + ", k1 =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(Rmax=" + str1 + ",Topc=" + str2 + ",k2 =" + str4  + ", k1 =" + str3 + ")");
				}
				break;
			case 22:/**Hilbert y Logan**/
				if(many){
					saveToR+= "ini <- list(d=2*(max(datashap[,1])-min(datashap[,1])),Y=3*max(datashap[,2]),Tmax = max(datashap[,1]), v=0.01)"+"\r\n";
					c.eval("ini <- list(d=2*(max(datashap[,1])-min(datashap[,1])),Y=3*max(datashap[,2]),Tmax = max(datashap[,1]), v=0.01)");
				}else{
					saveToR+="ini <- list(Y=" + str2 + ",d=" + str1 + ",v =" + str4  + ", Tmax =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(Y=" + str2 + ",d=" + str1 + ",v =" + str4  + ", Tmax =" + str3 + ")");
				}
				break;
			case 23:/**Lactin**/
				if(many){
					saveToR+= "ini <- list(Tl = max(datashap[,1]), p = min(datashap[,2]), dt= 0.8,L=-1)"+"\n";
					c.eval("ini <- list(Tl = max(datashap[,1]), p = min(datashap[,2]), dt= 0.8,L=-1)");
				}else{
					saveToR+="ini <- list(Tl=" + str1 + ",p=" + str2 + ",L =" + str4  + ", dt =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(Tl=" + str1 + ",p=" + str2 + ",L =" + str4  + ", dt =" + str3 + ")");
				}
				break;
			case 24:/** linear **/
				if(many){
					saveToR+="xli1<-datashap[,1]"+"\r\n";
					c.eval("xli1<-datashap[,1]");
					
					saveToR+="yli1<-datashap[,2]"+"\r\n";
					c.eval("yli1<-datashap[,2]");
					
					saveToR+="cof<-as.numeric(coef(lm(yli1~xli1)))"+"\r\n";
					c.eval("cof<-as.numeric(coef(lm(yli1~xli1)))");
					
					saveToR+="ini <- list(Inter=cof[1],Slop=cof[2])"+"\r\n";
					c.eval("ini <- list(Inter=cof[1],Slop=cof[2])");
				}else{
					saveToR+="ini <- list(Inter=" + str1 + ",Slop =" + str2 + ")"+"\r\n";
					c.eval("ini <- list(Inter=" + str1 + ",Slop =" + str2 + ")");
				}
				break;
			case 25:/** exponential simple **/
				if(many){
					saveToR+="ini=c(b1=0.019,b2=0.086)"+"\r\n";
					c.eval("ini=c(b1=0.019,b2=0.086)");
				}else{
					saveToR+="ini <- list(b1=" + str1 + ",b2 =" + str2 + ")"+"\r\n";
					c.eval("ini <- list(b1=" + str1 + ",b2 =" + str2 + ")");
				}
				break;
			case 26:/** Tb Model (Logan) **/
				if(many){
					saveToR+="ini=c(sy=0.32,b=0.0924,Tb=max(datashap[,1]),DTb=0.084)"+"\r\n";
					c.eval("ini=c(sy=0.32,b=0.0924,Tb=max(datashap[,1]),DTb=0.084)");
				}else{
					saveToR+="ini <- list(sy=" + str1 + ",b=" + str2 + ",Tb =" + str3  + ", DTb =" + str4 + ")"+"\r\n";
					c.eval("ini <- list(sy=" + str1 + ",b=" + str2 + ",Tb =" + str3  + ", DTb =" + str5 + ")");
				}
				break;
			case 27:/** Exponential Model (Logan) **/
				if(many){
					saveToR+="ini=c(sy=0.2567,b=0.086,Tb=min(datashap[,1]))"+"\r\n";
					c.eval("ini=c(sy=0.2567,b=0.086,Tb=min(datashap[,1]))");
				}else{
					saveToR+="ini <- list(sy=" + str1 + ",b=" + str2 + ",Tb =" + str3 + ")"+"\r\n";
					c.eval("ini <- list(sy=" + str1 + ",b=" + str2 + ",Tb =" + str3 + ")");
				}
				break;
			case 28:/** Exponential Tb (Logan) **/
				if(many){
					saveToR+="ini=c(b=0.0087,Tmin=min(datashap[,1]))"+"\r\n";
					c.eval("ini=c(b=0.0087,Tmin=min(datashap[,1]))");
				}else{
					saveToR+="ini <- list(b=" + str1 + ",Tmin =" + str2 + ")"+"\r\n";
					c.eval("ini <- list(b=" + str1 + ",Tmin =" + str2 + ")");
				}
				break;
			case 29:/** Square root model of Ratkowsky **/
				if(many){
					saveToR+="ini=c(b=0.000008487,Tb=-150.881)"+"\r\n";
					c.eval("ini=c(b=0.000008487,Tb=-150.881)");
				}else{
					saveToR+="ini <- list(b=" + str1 + ",Tb =" + str2 + ")"+"\r\n";
					c.eval("ini <- list(b=" + str1 + ",Tb =" + str2 + ")");
				}
				break;
			case 30:/** Davidson **/
				if(many){
					saveToR+="ini=c(k=0.5,a=1,b=-0.05)"+"\r\n";
					c.eval("ini=c(k=0.5,a=1,b=-0.05)");
				}else{
					saveToR+="ini <- list(k=" + str1 + ",a=" + str2 + ",b =" + str3+ ")"+"\r\n";
					c.eval("ini <- list(k=" + str1 + ",a=" + str2 + ",b =" + str3 + ")");
				}
				break;
			case 31:/** Pradham **/
				if(many){
					saveToR+="ini=c(R=0.3,Tm=mean(datashap[,1]),To=(-1)*min(datashap[,1]))"+"\r\n";
					c.eval("ini=c(R=0.3,Tm=mean(datashap[,1]),To=(-1)*min(datashap[,1]))");
				}else{
					saveToR+="ini <- list(R=" + str1 + ",Tm=" + str2 + ",To =" + str3+ ")"+"\r\n";
					c.eval("ini <- list(R=" + str1 + ",Tm=" + str2 + ",To =" + str3 + ")");
				}
				break;
			case 32:/** Angilletta Jr. **/
				if(many){
					saveToR+="ini=c(a=0.3,b=mean(datashap[,1]),c=(-1)*min(datashap[,1]),d=1)"+"\r\n";
					c.eval("ini=c(a=0.3,b=mean(datashap[,1]),c=(-1)*min(datashap[,1]),d=1)");
				}else{
					saveToR+="ini <- list(a=" + str1 + ",b=" + str2 + ",c =" + str3+", d="+str4+ ")"+"\r\n";
					c.eval("ini <- list(a=" + str1 + ",b=" + str2 + ",c =" + str3+", d="+str4 + ")");
				}
				break;
			case 33:/** Stinner 2 **/
				if(many){
					saveToR+="xstinner<-c(min(datashap[,1]),max(datashap[,1]))"+"\r\n";
					c.eval("xstinner<-c(min(datashap[,1]),max(datashap[,1]))");
					
					saveToR+="ystinner<-c(log(as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]/min(datashap[,2])-1),log(as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]/max(datashap[,2])-1))"+"\r\n";
					c.eval("ystinner<-c(log(as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]/min(datashap[,2])-1),log(as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1]/max(datashap[,2])-1))");
					
					saveToR+="ks<-as.numeric(coef(lm(ystinner~xstinner)))"+"\r\n";
					c.eval("ks<-as.numeric(coef(lm(ystinner~xstinner)))");
					
					saveToR+="ini=c(Rmax=max(datashap[,2]),k1=ks[1],k2=ks[2],Topc=as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1])"+"\r\n";
					c.eval("ini=c(Rmax=max(datashap[,2]),k1=ks[1],k2=ks[2],Topc=as.numeric(subset(datashap,max(datashap[,2])<=datashap[,2]))[1])");
				}else{
					saveToR+="ini <- list(Rmax=" + str1 + ",k1=" + str2 + ",k2 =" + str3+", Topc="+str4+ ")"+"\r\n";
					c.eval("ini <- list(Rmax=" + str1 + ",k1=" + str2 + ",k2 =" + str3+", Topc="+str4 + ")");
				}
				break;
			case 34:/** Hilbert **/
				if(many){
					saveToR+="ini=c(Tb=0.01,Tmax=max(datashap[,1]),d=2*(max(datashap[,1])-min(datashap[,1])),Y=3*max(datashap[,2]),v=0.01)"+"\r\n";
					c.eval("ini=c(Tb=0.01,Tmax=max(datashap[,1]),d=2*(max(datashap[,1])-min(datashap[,1])),Y=3*max(datashap[,2]),v=0.01)");
				}else{
					saveToR+="ini <- list(Tb=" + str1 + ",Tmax=" + str2 + ",d =" + str3+", Y="+str4+", v="+str5+ ")"+"\r\n";
					c.eval("ini <- list(Tb=" + str1 + ",Tmax=" + str2 + ",d =" + str3+", Y="+str4+", v="+str5 + ")");
				}
				break;
			case 35:/** Lactin 2 **/
				if(many){
					saveToR+="ini=c(Tl=max(datashap[,1])-23, p=min(datashap[,2]), dt=-40)"+"\r\n";
					c.eval("ini=c(Tl=max(datashap[,1])-23, p=min(datashap[,2]), dt=-40)");
				}else{
					saveToR+="ini <- list(Tl=" + str1 + ",p=" + str2 + ",dt =" + str3+ ")"+"\r\n";
					c.eval("ini <- list(Tl=" + str1 + ",p=" + str2 + ",dt =" + str3 + ")");
				}
				break;
			case 36:/** Anlytis-1 **/
				if(many){
					saveToR+="ini=c(P=1.2,Tmax=max(datashap[,1]), Tmin=min(datashap[,1]),n=1,m=1)"+"\r\n";
					c.eval("ini=c(P=1.2,Tmax=max(datashap[,1]), Tmin=min(datashap[,1]),n=1,m=1)");
				}else{
					saveToR+="ini <- list(P=" + str1 + ",Tmax=" + str2 + ",Tmin =" + str3+", n="+str4+", m="+str5+ ")"+"\r\n";
					c.eval("ini <- list(P=" + str1 + ",Tmax=" + str2 + ",Tmin =" + str3+", n="+str4+", m="+str5 + ")");
				}
				break;
			case 37:/** Anlytis-2 **/
				if(many){
					saveToR+="ini=c(P=1.2,Tmax=max(datashap[,1]), Tmin=min(datashap[,1]),n=1,m=1)"+"\r\n";
					c.eval("ini=c(P=1.2,Tmax=max(datashap[,1]), Tmin=min(datashap[,1]),n=1,m=1)");
				}else{
					saveToR+="ini <- list(P=" + str1 + ",Tmax=" + str2 + ",Tmin =" + str3+", n="+str4+", m="+str5+ ")"+"\r\n";
					c.eval("ini <- list(P=" + str1 + ",Tmax=" + str2 + ",Tmin =" + str3+", n="+str4+", m="+str5 + ")");
				}
				break;
			case 38:/** Anlytis-3 **/
				if(many){
					saveToR+="ini=c(a=0.00124,Tmax=max(datashap[,1])+46, Tmin=min(datashap[,1])-9.16696,n=1.582,m=0.9658)"+"\r\n";
					c.eval("ini=c(a=0.00124,Tmax=max(datashap[,1])+46, Tmin=min(datashap[,1])-9.16696,n=1.582,m=0.9658)");
				}else{
					saveToR+="ini <- list(a=" + str1 + ",Tmax=" + str2 + ",Tmin =" + str3+", n="+str4+", m="+str5+ ")"+"\r\n";
					c.eval("ini <- list(a=" + str1 + ",Tmax=" + str2 + ",Tmin =" + str3+", n="+str4+", m="+str5 + ")");
				}
				break;
			case 39:/** Allahyari **/
				if(many){
					saveToR+="ini=c(P=1.2,Tmax=max(datashap[,1]), Tmin=min(datashap[,1])-9,n=1,m=0.5)"+"\r\n";
					c.eval("ini=c(P=1.2,Tmax=max(datashap[,1]), Tmin=min(datashap[,1])-9,n=1,m=0.5)");
				}else{
					saveToR+="ini <- list(P=" + str1 + ",Tmax=" + str2 + ",Tmin =" + str3+", n="+str4+", m="+str5+ ")"+"\r\n";
					c.eval("ini <- list(P=" + str1 + ",Tmax=" + str2 + ",Tmin =" + str3+", n="+str4+", m="+str5 + ")");
				}
				break;
			case 40:/** Briere 3 **/
				if(many){
					saveToR+="ini=c(aa=0.0078,To=min(datashap[,1])-2, Tmax=max(datashap[,1])+1)"+"\r\n";
					c.eval("ini=c(aa=0.0078,To=min(datashap[,1])-2, Tmax=max(datashap[,1])+1)");
				}else{
					saveToR+="ini <- list(aa=" + str1 + ",To=" + str2 + ",Tmax =" + str3+ ")"+"\r\n";
					c.eval("ini <- list(aa=" + str1 + ",To=" + str2 + ",Tmax =" + str3 + ")");
				}
				break;
			case 41:/** Briere 4 **/
				if(many){
					saveToR+="ini=c(aa=0.0083,To=min(datashap[,1])-1, Tmax=max(datashap[,1])+1,n=1.9)"+"\r\n";
					c.eval("ini=c(aa=0.0083,To=min(datashap[,1])-1, Tmax=max(datashap[,1])+1,n=1.9)");
				}else{
					saveToR+="ini <- list(aa=" + str1 + ",To=" + str2 + ",Tmax =" + str3+", n="+str4+ ")"+"\r\n";
					c.eval("ini <- list(aa=" + str1 + ",To=" + str2 + ",Tmax =" + str3+", n="+str4 + ")");
				}
				break;
			case 42:/** Kontodimas-1 **/
				if(many){
					saveToR+="ini=c(aa=0.00013,Tmin=min(datashap[,1])-4, Tmax=max(datashap[,1])+1)"+"\r\n";
					c.eval("ini=c(aa=0.00013,Tmin=min(datashap[,1])-4, Tmax=max(datashap[,1])+1)");
				}else{
					saveToR+="ini <- list(aa=" + str1 + ",Tmin=" + str2 + ",Tmax =" + str3+ ")"+"\r\n";
					c.eval("ini <- list(aa=" + str1 + ",Tmin=" + str2 + ",Tmax =" + str3 + ")");
				}
				break;
			case 43:/** Kontodimas-2 **/
				if(many){
					saveToR+="ini=c(Dmin=4,Topt=mean(datashap[,1])+8,K=10,lmda=0.06)"+"\r\n";
					c.eval("ini=c(Dmin=4,Topt=mean(datashap[,1])+8,K=10,lmda=0.06)");
				}else{
					saveToR+="ini <- list(Dmin=" + str1 + ",Topt=" + str2 + ",K =" + str3+", lmda="+str4+ ")"+"\r\n";
					c.eval("ini <- list(Dmin=" + str1 + ",Topt=" + str2 + ",K =" + str3+", lmda="+str4 + ")");
				}
				break;
			case 44:/** Kontodimas-3 **/
				if(many){
					saveToR+="ini=c(a1=0.2,b1=35,c1=1,d1=10,f1=1,g1=10)"+"\r\n";
					c.eval("ini=c(a1=0.2,b1=35,c1=1,d1=10,f1=1,g1=10)");
				}else{
					saveToR+="ini <- list(a1=" + str1 + ",b1 =" + str2 + ",c1 =" + str3 + ", d1 =" + str4 + ",f1 =" + str5 + ", g1 =" + str6 + ")"+"\r\n";
					c.eval("ini <- list(a1=" + str1 + ",b1 =" + str2 + ",c1 =" + str3 + ", d1 =" + str4 + ",f1 =" + str5 + ", g1 =" + str6 + ")");
				}
				break;
			case 45:/** Ratkowsky 2 **/
				if(many){
					saveToR+="ini=c(aa=0.00065,Tmin=min(datashap[,1])-1,Tmax=max(datashap[,1])+38,b=0.09)"+"\r\n";
					c.eval("ini=c(aa=0.00065,Tmin=min(datashap[,1])-1,Tmax=max(datashap[,1])+38,b=0.09)");
				}else{
					saveToR+="ini <- list(aa=" + str1 + ",Tmin=" + str2 + ",Tmax =" + str3+", b="+str4+ ")"+"\r\n";
					c.eval("ini <- list(aa=" + str1 + ",Tmin=" + str2 + ",Tmax =" + str3+", b="+str4 + ")");
				}
				break;
			case 46:/** Janish-1 **/
				if(many){
					saveToR+="ini=c(Dmin=2.8,Topt=mean(datashap[,1]),K=0.17)"+"\r\n";
					c.eval("ini=c(Dmin=2.8,Topt=mean(datashap[,1]),K=0.17)");
				}else{
					saveToR+="ini <- list(Dmin=" + str1 + ",Topt=" + str2 + ",K =" + str3+ ")"+"\r\n";
					c.eval("ini <- list(Dmin=" + str1 + ",Topt=" + str2 + ",K =" + str3 + ")");
				}
				break;
			case 47:/** Janish-2 **/
				if(many){
					saveToR+="ini=c(c=0.2151377,a=4.97447,b=1.077405,Tm=max(datashap[,1]))"+"\r\n";
					c.eval("ini=c(c=0.2151377,a=4.97447,b=1.077405,Tm=max(datashap[,1]))");
				}else{
					saveToR+="ini <- list(c=" + str1 + ",a=" + str2 + ",b =" + str3+", Tm="+str4+ ")"+"\r\n";
					c.eval("ini <- list(c=" + str1 + ",a=" + str2 + ",b =" + str3+", Tm="+str4 + ")");
				}
				break;
			case 48:/** Tanigoshi **/
				if(many){
					saveToR+="xTan<-datashap[,1]"+"\r\n";
					c.eval("xTan<-datashap[,1]");
					
					saveToR+="yTan<-datashap[,2]"+"\r\n";
					c.eval("yTan<-datashap[,2]");
					
					saveToR+="ks<-as.numeric(coef(lm(yTan~xTan+I(xTan^2)+I(xTan^3))))"+"\r\n";
					c.eval("ks<-as.numeric(coef(lm(yTan~xTan+I(xTan^2)+I(xTan^3))))");
					
					saveToR+="ini=c(a0=ks[1],a1=ks[2],a2=ks[3],a3=ks[4])"+"\r\n";
					c.eval("ini=c(a0=ks[1],a1=ks[2],a2=ks[3],a3=ks[4])");
				}else{
					saveToR+="ini <- list(a0=" + str1 + ",a1=" + str2 + ",a2 =" + str3+", a3="+str4+ ")"+"\r\n";
					c.eval("ini <- list(a0=" + str1 + ",a1=" + str2 + ",a2 =" + str3+", a3="+str4 + ")");
				}
				break;
			case 49:/** Wang-Lan-Ding **/
				if(many){
					saveToR+="ini=c(k=0.4,a=0.46,b=1,c=1,Tmin=min(datashap[,1]),Tmax=max(datashap[,1]),r=0.53)"+"\r\n";
					c.eval("ini=c(k=0.4,a=0.46,b=1,c=1,Tmin=min(datashap[,1]),Tmax=max(datashap[,1]),r=0.53)");
				}else{
					saveToR+="ini <- list(k=" + str1 + ",a=" + str2 + ",b =" + str3 + ",c =" + str4 + ", Tmin =" + str5 + ",Tmax =" + str6 + ", r =" + str7 + ")"+"\r\n";
					c.eval("ini <- list(k=" + str1 + ",a=" + str2 + ",b =" + str3 + ",c =" + str5 + ", Tmin =" + str5 + ",Tmax =" + str6 + ", r =" + str7 + ")");
				}
				break;
			case 50:/** Stinner-3 **/
				if(many){
					saveToR+="ini=c(c1=11.7,k1=5.783,k2=-0.0516)"+"\r\n";
					c.eval("ini=c(c1=11.7,k1=5.783,k2=-0.0516)");
				}else{
					saveToR+="ini <- list(c1=" + str1 + ",k1=" + str2 + ",k2 =" + str3+ ")"+"\r\n";
					c.eval("ini <- list(c1=" + str1 + ",k1=" + str2 + ",k2 =" + str3 + ")");
				}
				break;
			case 51:/** Stinner-4 **/
				if(many){
					saveToR+="ini=c(c1=-0.962,c2=0.2545,k1=-3.91,k2=0.54,To=7.77)"+"\r\n";
					c.eval("ini=c(c1=-0.962,c2=0.2545,k1=-3.91,k2=0.54,To=7.77)");
				}else{
					saveToR+="ini <- list(c1=" + str1 + ",c2=" + str2 + ",k1 =" + str3+", k2="+str4+", To="+str5+ ")"+"\r\n";
					c.eval("ini <- list(c1=" + str1 + ",c2=" + str2 + ",k1 =" + str3+", k2="+str4+", To="+str5 + ")");
				}
				break;
			case 52:/** Logan-3 **/
				if(many){
					saveToR+="ini=c(sy=0.08145,b=0.0627,Tmin=2.3183,Tmax=29.594,DTb=0.1448)"+"\r\n";
					c.eval("ini=c(sy=0.08145,b=0.0627,Tmin=2.3183,Tmax=29.594,DTb=0.1448)");
				}else{
					saveToR+="ini <- list(sy=" + str1 + ",b=" + str2 + ",Tmin =" + str3+", Tmax="+str4+", DTb="+str5+ ")"+"\r\n";
					c.eval("ini <- list(sy=" + str1 + ",b=" + str2 + ",Tmin =" + str3+", Tmax="+str4+", DTb="+str5 + ")");
				}
				break;
			case 53:/** Logan-4 **/
				if(many){
					saveToR+="ini=c(alph=23.269,k=8778,b=0.1505,Tmin=-11.12,Tmax=52.85,Dt=3.66)"+"\r\n";
					c.eval("ini=c(alph=23.269,k=8778,b=0.1505,Tmin=-11.12,Tmax=52.85,Dt=3.66)");
				}else{
					saveToR+="ini <- list(alph=" + str1 + ",k =" + str2 + ",b =" + str3 + ", Tmin =" + str4 + ",Tmax =" + str5 + ", Dt =" + str6 + ")"+"\r\n";
					c.eval("ini <- list(alph=" + str1 + ",k =" + str2 + ",b =" + str3 + ", Tmin =" + str4 + ",Tmax =" + str5 + ", Dt =" + str6 + ")");
				}
				break;
			case 54:/** Logan-5 **/
				if(many){
					saveToR+="ini=c(alph=0.14,k=30,b=0.1314,Tmax=35,Dt=0.489)"+"\r\n";
					c.eval("ini=c(alph=0.14,k=30,b=0.1314,Tmax=35,Dt=0.489)");
				}else{
					saveToR+="ini <- list(alph=" + str1 + ",k=" + str2 + ",b =" + str3+", Tmax="+str4+", Dt="+str5+ ")"+"\r\n";
					c.eval("ini <- list(alph=" + str1 + ",k=" + str2 + ",b =" + str3+", Tmax="+str4+", Dt="+str5 + ")");
				}
				break;
			case 55:/** Hilber y logan 2 **/
				if(many){
					saveToR+="ini=c(trid=10.145,D=12457,Tmax=24.698,Dt=1.3818)"+"\r\n";
					c.eval("ini=c(trid=10.145,D=12457,Tmax=24.698,Dt=1.3818)");
				}else{
					saveToR+="ini <- list(trid=" + str1 + ",D=" + str2 + ",Tmax =" + str3+", Dt="+str4+ ")"+"\r\n";
					c.eval("ini <- list(trid=" + str1 + ",D=" + str2 + ",Tmax =" + str3+", Dt="+str4 + ")");
				}
				break;
			case 56:/** Hilber y logan 3 **/
				if(many){
					saveToR+="ini=c(trid=32.237,Tmax=31.506,Tmin=3.04,D=78305,Dt=0.356,Smin=0.07577)"+"\r\n";
					c.eval("ini=c(trid=32.237,Tmax=31.506,Tmin=3.04,D=78305,Dt=0.356,Smin=0.07577)");
				}else{
					saveToR+="ini <- list(trid=" + str1 + ",Tmax =" + str2 + ",Tmin =" + str3 + ", D =" + str4 + ",Dt =" + str5 + ", Smin =" + str6 + ")"+"\r\n";
					c.eval("ini <- list(trid=" + str1 + ",Tmax =" + str2 + ",Tmin =" + str3 + ", D =" + str4 + ",Dt =" + str5 + ", Smin =" + str6 + ")");
				}
				break;
			case 57:/** Taylor **/
				if(many){
					saveToR+="ini=c(rm=0.339,Topt=21.81,Troh=7.897,Smin=0.0657)"+"\r\n";
					c.eval("ini=c(rm=0.339,Topt=21.81,Troh=7.897,Smin=0.0657)");
				}else{
					saveToR+="ini <- list(rm=" + str1 + ",Topt=" + str2 + ",Troh =" + str3+", Smin="+str4+ ")"+"\r\n";
					c.eval("ini <- list(rm=" + str1 + ",Topt=" + str2 + ",Troh =" + str3+", Smin="+str4 + ")");
				}
				break;
			case 58:/** Lactin 3 **/
				if(many){
					saveToR+="ini=c(p=-0.01689, Tl=0.001931, dt=-12.306,lamb=0.006475)"+"\r\n";
					c.eval("ini=c(p=-0.01689, Tl=0.001931, dt=-12.306,lamb=0.006475)");
				}else{
					saveToR+="ini <- list(p=" + str1 + ",Tl=" + str2 + ",dt =" + str3+", lamb="+str4+ ")"+"\r\n";
					c.eval("ini <- list(p=" + str1 + ",Tl=" + str2 + ",dt =" + str3+", lamb="+str4 + ")");
				}
				break;
			case 59:/** Sigmoid or logistic **/
				if(many){
					saveToR+="ini=c(c1=0.4305,a=3.33407,b=-0.19878)"+"\r\n";
					c.eval("ini=c(c1=0.4305,a=3.33407,b=-0.19878)");
				}else{
					saveToR+="ini <- list(c1=" + str1 + ",a=" + str2 + ",b =" + str3+ ")"+"\r\n";
					c.eval("ini <- list(c1=" + str1 + ",a=" + str2 + ",b =" + str3 + ")");
				}
				break;
	
			default:
				break;
			}
		}catch (RserveException e) {
//			Rserve.saveIlcymError(MainPageWizardPage.getStrPathProjDR(), title, saveToR);
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying converge the parameters in the model: " + getModelName(model+""));
		}
		
	}
	
	
	public static void estimateParameters(RConnection c, int model, boolean many, boolean modify, String str1, String str2, String str3, String str4, String str5, String str6) throws RserveException{
		saveToR +="modelm<-"+model +"\n";
		c.eval("modelm<-"+model);
		if(model < 7){
			if(!many){
				saveToR +="inim<-c(a=" + str1 + ",b=" + str2 + ",c=" + str3 + ")" +"\n";
				c.eval("inim<-c(a=" + str1 + ",b=" + str2 + ",c=" + str3 + ")");
			}else{
				saveToR +="inicial<-pr6mortal(modelm,datm)" +"\n";
				c.eval("inicial<-pr6mortal(modelm,datm)");
				
				saveToR +="inim<-inicial$ini" +"\n";
				c.eval("inim<-inicial$ini");
			}
		}
		
		switch (model) {
		case 7:
			if(!many){
				saveToR +="inim<-c(a=" + str1 + ",b=" + str2 + ",c=" + str3 + ",d=" + str4 + ")" +"\n";
				c.eval("inim<-c(a=" + str1 + ",b=" + str2 + ",c=" + str3 + ",d=" + str4 + ")");
			}else{
				saveToR +="inim<-c(a=5,b=15,c=18,d=10)" +"\n";
				c.eval("inim<-c(a=5,b=15,c=18,d=10)");
			}
			break;
		case 8:
			if(!many){
				saveToR +="inim<-c(a=" + str1 + ",b=" + str2 + ",xo=" + str3 + ",c=" + str4 + ")" +"\n";
				c.eval("inim<-c(a=" + str1 + ",b=" + str2 + ",xo=" + str3 + ",c=" + str4 + ")");
			}else{
				saveToR +="inim<-c(a=0.2,b=-17485,xo=18,c=-1400)" +"\n";
				c.eval("inim<-c(a=0.2,b=-17485,xo=18,c=-1400)");
			}
			break;
		case 9:
			if(!many){
				saveToR +="inim<-c(y0=" + str1 + ",a=" + str2 + ",x0=" + str3 + ",b=" + str4 + ")" +"\n";
				c.eval("inim<-c(y0=" + str1 + ",a=" + str2 + ",x0=" + str3 + ",b=" + str4 + ")");
			}else{
				saveToR +="inim<-c(y0=0.8,a=-0.6,x0=18,b=5)" +"\n";
				c.eval("inim<-c(y0=0.8,a=-0.6,x0=18,b=5)");
			}
			break;
		case 10:
			if(!many){
				saveToR +="inim<-c(y0=" + str1 + ",a=" + str2 + ",x0=" + str3 + ",b=" + str4 + ")" +"\n";
				c.eval("inim<-c(y0=" + str1 + ",a=" + str2 + ",x0=" + str3 + ",b=" + str4 + ")");
			}else{
				saveToR +="inim<-c(y0=0.2,a=0.6,x0=20,b=0.2)" +"\n";
				c.eval("inim<-c(y0=0.2,a=0.6,x0=20,b=0.2)");
			}
			break;
		case 11:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",d=" + str4 + ")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",d=" + str4 + ")");
			}else{
				saveToR +="inim=c(b1=1.85,b2=-0.104,b3=0.000255,d=2.559)" +"\n";
				c.eval("inim=c(b1=1.85,b2=-0.104,b3=0.000255,d=2.559)");
			}
			break;
		case 12:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ")");
			}else{
				saveToR +="inim=c(b1=2.358,b2=-0.297,b3=0.00585)" +"\n";
				c.eval("inim=c(b1=2.358,b2=-0.297,b3=0.00585)");
			}
			break;
		case 13:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+ str5+")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+str5+")");
			}else{
				saveToR +="inim=c(b1=7.4,b2=-0.6,b3=0.0118,b4=1.156,b5=0.65)" +"\n";
				c.eval("inim=c(b1=7.4,b2=-0.6,b3=0.0118,b4=1.156,b5=0.65)");
			}
			break;
		case 14:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ")");
			}else{
				saveToR +="inim=c(b1=8.48,b2=0.388,b3=-3.91)" +"\n";
				c.eval("inim=c(b1=8.48,b2=0.388,b3=-3.91)");
			}
			break;
		case 15:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+ str5+")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+str5+")");
			}else{
				saveToR +="inim=c(b1=24.2,b2=1.015,b3=-10.16,b4=1.154,b5=1.617)" +"\n";
				c.eval("inim=c(b1=24.2,b2=1.015,b3=-10.16,b4=1.154,b5=1.617)");
			}
			break;
		case 16:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ")");
			}else{
				saveToR +="inim=c(b1=-39.97,b2=0.817,b3=92.57)" +"\n";
				c.eval("inim=c(b1=-39.97,b2=0.817,b3=92.57)");
			}
			break;
		case 17:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+ str5+")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+str5+")");
			}else{
				saveToR +="inim=c(b1=-10.75,b2=0.14,b3=34,b4=87,b5=141)" +"\n";
				c.eval("inim=c(b1=-10.75,b2=0.14,b3=34,b4=87,b5=141)");
			}
			break;
		case 18:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ")");
			}else{
				saveToR +="inim=c(b1=-28.15,b2=0.67,b3=214.55)" +"\n";
				c.eval("inim=c(b1=-28.15,b2=0.67,b3=214.55)");
			}
			break;
		case 19:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+ str5+")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+str5+")");
			}else{
				saveToR +="inim=c(b1=-9.27,b2=0.255,b3=104.55,b4=77.48,b5=24)" +"\n";
				c.eval("inim=c(b1=-9.27,b2=0.255,b3=104.55,b4=77.48,b5=24)");
			}
			break;
		case 20:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",d=" + str4 + ")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",d=" + str4 + ")");
			}else{
				saveToR +="inim=c(b1=1.6,b2=-0.17,b3=0.000023,d=3.37)" +"\n";
				c.eval("inim=c(b1=1.6,b2=-0.17,b3=0.000023,d=3.37)");
			}
			break;
		case 21:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+ str5+ ",d="+ str6+")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+str5+ ",d="+ str6+")");
			}else{
				saveToR +="inim=c(b1=6.244,b2=-0.455,b3=0.000765,b4=1.042,b5=0.938,d=2.66)" +"\n";
				c.eval("inim=c(b1=6.244,b2=-0.455,b3=0.000765,b4=1.042,b5=0.938,d=2.66)");
			}
			break;
		case 22:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ")");
			}else{
				saveToR +="inim=c(b1=7.86,b2=0.16,b3=-4.1)" +"\n";
				c.eval("inim=c(b1=7.86,b2=0.16,b3=-4.1)");
			}
			break;
		case 23:
			if(!many){
				saveToR +="inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+ str5+")" +"\n";
				c.eval("inim<-c(b1=" + str1 + ",b2=" + str2 + ",b3=" + str3 + ",b4=" + str4 + ",b5="+str5+")");
			}else{
				saveToR +="inim=c(b1=19.75,b2=0.268,b3=-6.724,b4=3.583,b5=0.0288)" +"\n";
				c.eval("inim=c(b1=19.75,b2=0.268,b3=-6.724,b4=3.583,b5=0.0288)");
			}
			break;
		case 24:
			if(!many){
				saveToR +="inim<-c(rm=" + str1 + ",Topt=" + str2 + ",Troh=" + str3 + ")" +"\n";
				c.eval("inim<-c(rm=" + str1 + ",Topt=" + str2 + ",Troh=" + str3 + ")");
			}else{
				saveToR +="inim=c(rm=0.807,Topt=25.87,Troh=9.2)" +"\n";
				c.eval("inim=c(rm=0.807,Topt=25.87,Troh=9.2)");
			}
			break;
		case 25:
			if(!many){
				saveToR +="inim<-c(rm=" + str1 + ",Topt=" + str2 + ",Troh=" + str3 + ")" +"\n";
				c.eval("inim=c(rm=" + str1 + ",Topt=" + str2 + ",Troh=" + str3 + ")");
			}else{
				saveToR +="inim=c(rm=0.786,Topt=24.8,Troh=0.452)" +"\n";
				c.eval("inim=c(rm=0.786,Topt=24.8,Troh=0.452)");
			}
			break;
		case 26:
			if(!many){
				saveToR +="inim<-c(Topt=" + str1 + ",B=" + str2 + ",H=" + str3 + ")" +"\n";
				c.eval("inim=c(Topt=" + str1 + ",B=" + str2 + ",H=" + str3 + ")");
			}else{
				saveToR +="inim=c(Topt=25.53,B=4.338,H=0.06)" +"\n";
				c.eval("inim=c(Topt=25.53,B=4.338,H=0.06)");
			}
			break;
		case 27:
			if(!many){
				saveToR +="inim<-c(Tl=" + str1 + ",Th=" + str2 + ",B=" + str3 + ",H=" + str4 + ")" +"\n";
				c.eval("inim<-c(Tl=" + str1 + ",Th=" + str2 + ",B=" + str3 + ",H=" + str4 + ")");
			}else{
				saveToR +="inim=c(Tl=25.5,Th=25.53,B=4.338,H=0.061)" +"\n";
				c.eval("inim=c(Tl=25.5,Th=25.53,B=4.338,H=0.061)");
			}
			break;
		case 28:
			if(!many){
				saveToR +="inim<-c(Topt=" + str1 + ",Bl=" + str2 + ",Bh=" + str3 + ",H=" + str4 + ")" +"\n";
				c.eval("inim<-c(Topt=" + str1 + ",Bl=" + str2 + ",Bh=" + str3 + ",H=" + str4 + ")");
			}else{
				saveToR +="inim=c(Topt=27.9,Bl=5.01,Bh=3.15,H=0.06)" +"\n";
				c.eval("inim=c(Topt=27.9,Bl=5.01,Bh=3.15,H=0.06)");
			}
			break;
		case 29:
			if(!many){
				saveToR +="inim<-c(Tl=" + str1 + ",Th=" + str2 + ",Bl=" + str3 + ",Bh=" + str4 + ",H="+ str5+")" +"\n";
				c.eval("inim<-c(Tl=" + str1 + ",Th=" + str2 + ",Bl=" + str3 + ",Bh=" + str4 + ",H="+str5+")");
			}else{
				saveToR +="inim=c(Tl=23.47,Th=30.16,Bl=4.75,Bh=2.82,H=0.119)" +"\n";
				c.eval("inim=c(Tl=23.47,Th=30.16,Bl=4.75,Bh=2.82,H=0.119)");
			}
			break;
		case 30:
			if(!many){
				saveToR +="inim<-c(Topt=" + str1 + ",B=" + str2 + ",H=" + str3 + ")" +"\n";
				c.eval("inim=c(Topt=" + str1 + ",B=" + str2 + ",H=" + str3 + ")");
			}else{
				saveToR +="inim=c(Topt=17.55,B=5.78,H=10.1)" +"\n";
				c.eval("inim=c(Topt=17.55,B=5.78,H=10.1)");
			}
			break;
		case 31:
			if(!many){
				saveToR +="inim<-c(Tl=" + str1 + ",Th=" + str2 + ",B=" + str3 + ",H=" + str4 + ")" +"\n";
				c.eval("inim<-c(Tl=" + str1 + ",Th=" + str2 + ",B=" + str3 + ",H=" + str4 + ")");
			}else{
				saveToR +="inim=c(Tl=14.04,Th=37.23,B=5.388,H=2.75)" +"\n";
				c.eval("inim=c(Tl=14.04,Th=37.23,B=5.388,H=2.75)");
			}
			break;
		case 32:
			if(!many){
				saveToR +="inim<-c(Topt=" + str1 + ",Bl=" + str2 + ",Bh=" + str3 + ",H=" + str4 + ")" +"\n";
				c.eval("inim<-c(Topt=" + str1 + ",Bl=" + str2 + ",Bh=" + str3 + ",H=" + str4 + ")");
			}else{
				saveToR +="inim=c(Topt=16.93,Bl= 3.07,Bh=4.69,H=9.64)" +"\n";
				c.eval("inim=c(Topt=16.93,Bl= 3.07,Bh=4.69,H=9.64)");
			}
			break;
		case 33:
			if(!many){
				saveToR +="inim<-c(Tl=" + str1 + ",Th=" + str2 + ",Bl=" + str3 + ",Bh=" + str4 + ",H="+ str5+")" +"\n";
				c.eval("inim<-c(Tl=" + str1 + ",Th=" + str2 + ",Bl=" + str3 + ",Bh=" + str4 + ",H="+str5+")");
			}else{
				saveToR +="inim=c(Tl=13.33,Th=36.06,Bl=4.7,Bh=2.96,H=2.39)" +"\n";
				c.eval("inim=c(Tl=13.33,Th=36.06,Bl=4.7,Bh=2.96,H=2.39)");
			}
			break;
		case 34:
			if(!many){
				saveToR +="inim<-c(Hl=" + str1 + ",Tl=" + str2 + ",Hh=" + str3 + ",Th=" + str4 + ",Bm="+ str5+")" +"\n";
				c.eval("inim<-c(Hl=" + str1 + ",Tl=" + str2 + ",Hh=" + str3 + ",Th=" + str4 + ",Bm="+str5+")");
			}else{
				saveToR +="inim=c(Hl=-60000,Tl=287,Hh=100000,Th=308,Bm=0.193)" +"\n";
				c.eval("inim=c(Hl=-60000,Tl=287,Hh=100000,Th=308,Bm=0.193)");
			}
			break;
		case 35:
			if(!many){
				saveToR +="inim<-c(a1=" + str1 + ",b1=" + str2 + ",a2=" + str3 + ",b2=" + str4 + ")" +"\n";
				c.eval("inim<-c(a1=" + str1 + ",b1=" + str2 + ",a2=" + str3 + ",b2=" + str4 + ")");
			}else{
				saveToR +="inim=c(a1=3,b1=-0.2,a2=-20,b2=0.7)" +"\n";
				c.eval("inim=c(a1=3,b1=-0.2,a2=-20,b2=0.7)");
			}
			break;
		case 36:
			break;
		case 37:
			if(!many){
				saveToR +="inim<-c(a1=" + str1 + ",b1=" + str2 + ",a2=" + str3 + ",b2=" + str4 + ")" +"\n";
				c.eval("inim<-c(a1=" + str1 + ",b1=" + str2 + ",a2=" + str3 + ",b2=" + str4 + ")");
			}else{
				saveToR +="inim=c(a1=3,b1=-0.2,a2=-20,b2=0.7)" +"\n";
				c.eval("inim=c(a1=3,b1=-0.2,a2=-20,b2=0.7)");
			}
			break;
		case 38:
			if(!many){
				saveToR +="inim<-c(a1=" + str1 + ",b1=" + str2 + ",a2=" + str3 + ",b2=" + str4 + ",c1="+ str5+")" +"\n";
				c.eval("inim<-c(a1=" + str1 + ",b1=" + str2 + ",a2=" + str3 + ",b2=" + str4 + ",c1="+str5+")");
			}else{
				saveToR +="inim=c(a1=8e-08,b1=0.5,a2=29,b2=-0.337,c1=0.001)" +"\n";
				c.eval("inim=c(a1=8e-08,b1=0.5,a2=29,b2=-0.337,c1=0.001)");
			}
			break;
		case 39:
			if(!many){
				saveToR +="inim<-c(a=" + str1 + ",b=" + str2 + ",nn=" + str3 + ")" +"\n";
				c.eval("inim=c(a=" + str1 + ",b=" + str2 + ",nn=" + str3 + ")");
			}else{
				saveToR +="inim=c(a=0.005,b=24,nn=2)" +"\n";
				c.eval("inim=c(a=0.005,b=24,nn=2)");
			}
			break;
		case 40:
			if(!many){
				saveToR +="inim<-c(a=" + str1 + ",To=" + str2 + ",Tl=" + str3 + ",d=" + str4 + ")" +"\n";
				c.eval("inim<-c(a=" + str1 + ",To=" + str2 + ",Tl=" + str3 + ",d=" + str4 + ")");
			}else{
				saveToR +="inim=c(a=0.0013,To=1,Tl=37,d=-0.57)" +"\n";
				c.eval("inim=c(a=0.0013,To=1,Tl=37,d=-0.57)");
			}
			break;
		case 41:
			if(!many){
				saveToR +="inim<-c(a=" + str1 + ",To=" + str2 + ",Tl=" + str3 + ",d=" + str4 + ")" +"\n";
				c.eval("inim<-c(a=" + str1 + ",To=" + str2 + ",Tl=" + str3 + ",d=" + str4 + ")");
			}else{
				saveToR +="inim=c(a=0.0004,To=31.52,Tl=57.5,d=0.983)" +"\n";
				c.eval("inim=c(a=0.0004,To=31.52,Tl=57.5,d=0.983)");
			}
			break;
		case 42:
			if(!many){
				saveToR +="inim<-c(a=" + str1 + ",Tmax=" + str2 + ",Tmin=" + str3 + ",n=" + str4 + ",m="+ str5+")" +"\n";
				c.eval("inim<-c(a=" + str1 + ",Tmax=" + str2 + ",Tmin=" + str3 + ",n=" + str4 + ",m="+str5+")");
			}else{
				saveToR +="inim=c(a=36.02,Tmax=32.2, Tmin=8.313,n=-1.57,m=-0.89)" +"\n";
				c.eval("inim=c(a=36.02,Tmax=32.2, Tmin=8.313,n=-1.57,m=-0.89)");
			}
			break;
		case 43:
			if(!many){
				saveToR +="inim<-c(Dmin=" + str1 + ",k=" + str2 + ",Tp=" + str3 + ",lamb=" + str4 + ")" +"\n";
				c.eval("inim<-cDmina=" + str1 + ",k=" + str2 + ",Tp=" + str3 + ",lamb=" + str4 + ")");
			}else{
				saveToR +="inim=c(Dmin=0.3,k=0.088,Tp=-15.5,lamb=0.116)" +"\n";
				c.eval("inim=c(Dmin=0.3,k=0.088,Tp=-15.5,lamb=0.116)");
			}
			break;
		case 44:
			if(!many){
				saveToR +="inim<-c(a=" + str1 + ",Tl=" + str2 + ",Th=" + str3 + ",B=" + str4 + ")" +"\n";
				c.eval("inim<-a=" + str1 + ",Tl=" + str2 + ",Th=" + str3 + ",B=" + str4 + ")");
			}else{
				saveToR +="inim=c(a=0.2,Tl=1, Th=70,B=-50)" +"\n";
				c.eval("inim=c(a=0.2,Tl=1, Th=70,B=-50)");
			}
			break;
		case 45:
			if(!many){
				saveToR +="inim<-c(a=" + str1 + ",Tl=" + str2 + ",Th=" + str3 + ",Bl=" + str4 + ",Bh="+ str5+")" +"\n";
				c.eval("inim<-c(a=" + str1 + ",Tl=" + str2 + ",Th=" + str3 + ",Bl=" + str4 + ",Bh="+str5+")");
			}else{
				saveToR +="inim=c(a=-22.23, Tl=12.4, Th=61.68,Bl=3,Bh=12)" +"\n";
				c.eval("inim=c(a=-22.23, Tl=12.4, Th=61.68,Bl=3,Bh=12)");
			}
			break;
		}
	}
	
	private static Double[] saveTempar(String tempar, int model) throws RserveException, REXPMismatchException{
		Double[] dbpars=new Double[]{0.0,0.0,0.0,0.0,0.0,0.0};
		String[] parsName = new String[]{"","","","","",""};
//		dbStdpars = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0};
		
		if(model < 7)
		{
			if(tempar.equalsIgnoreCase("inim")){
				dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
				dbpars[1] = c.eval(tempar + "["+'"'+"b"+'"'+"]").asList().at(0).asDouble();
				dbpars[2] = c.eval(tempar + "["+'"'+"c"+'"'+"]").asList().at(0).asDouble();
				return dbpars;
			}
			
			dbpars[0] = c.eval(tempar).asDoubles()[0];
			dbpars[1] = c.eval(tempar).asDoubles()[1];
			dbpars[2] = c.eval(tempar).asDoubles()[2];
			
/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
*/			
			parsName[0] = "a";
			parsName[1] = "b";
			parsName[2] = "c";
		}
		if(model == 7){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"c"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"d"+'"'+"]").asList().at(0).asDouble();
/*			
			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
*/			
			parsName[0] = "a";
			parsName[1] = "b";
			parsName[2] = "c";
			parsName[3] = "d";
		}
		if(model == 8){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"xo"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"c"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
*/			
			parsName[0] = "a";
			parsName[1] = "b";
			parsName[2] = "xo";
			parsName[3] = "c";
		}
		if(model ==9 || model==10){//models 9 & 10
			dbpars[0] = c.eval(tempar + "["+'"'+"y0"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"x0"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"b"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
*/			
			parsName[0] = "y0";
			parsName[1] = "a";
			parsName[2] = "x0";
			parsName[3] = "b";
		}
		if(model == 11 || model == 20){
			dbpars[0] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"b3"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"d"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
*/			
			parsName[0] = "b1";
			parsName[1] = "b2";
			parsName[2] = "b3";
			parsName[3] = "d";
		}
		if(model == 12 || model == 14 || model == 16 || model == 18 || model == 22){
			dbpars[0] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"b3"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
*/			
			parsName[0] = "b1";
			parsName[1] = "b2";
			parsName[2] = "b3";
		}
		if(model == 13 || model == 15 || model == 17 || model == 19 || model == 23){
			dbpars[0] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"b3"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"b4"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"b5"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
			dbStdpars[5] = c.eval("Std.Error").asDoubles()[5];
*/			
			parsName[0] = "b1";
			parsName[1] = "b2";
			parsName[2] = "b3";
			parsName[3] = "b4";
			parsName[4] = "b5";
		}
		if(model == 21){
			dbpars[0] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"b3"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"b4"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"b5"+'"'+"]").asList().at(0).asDouble();
			dbpars[5] = c.eval(tempar + "["+'"'+"d"+'"'+"]").asList().at(0).asDouble();
			
/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
			dbStdpars[5] = c.eval("Std.Error").asDoubles()[5];
			dbStdpars[6] = c.eval("Std.Error").asDoubles()[6];
*/			
			parsName[0] = "b1";
			parsName[1] = "b2";
			parsName[2] = "b3";
			parsName[3] = "b4";
			parsName[4] = "b5";
			parsName[5] = "d";
		}
		if(model == 24 || model == 25){
			dbpars[0] = c.eval(tempar + "["+'"'+"rm"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Topt"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Troh"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
*/			
			parsName[0] = "rm";
			parsName[1] = "Topt";
			parsName[2] = "Troh";
		}
		if(model == 26 || model == 30){
			dbpars[0] = c.eval(tempar + "["+'"'+"Topt"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"B"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"H"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
*/			
			parsName[0] = "Topt";
			parsName[1] = "B";
			parsName[2] = "H";
		}
		if(model == 27 || model == 31){
			dbpars[0] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Th"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"B"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"H"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
*/			
			parsName[0] = "Tl";
			parsName[1] = "Th";
			parsName[2] = "B";
			parsName[3] = "H";
		}
		if(model == 28 || model == 32){
			dbpars[0] = c.eval(tempar + "["+'"'+"Topt"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Bl"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Bh"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"H"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
*/			
			parsName[0] = "Topt";
			parsName[1] = "Bl";
			parsName[2] = "Bh";
			parsName[3] = "H";
		}
		if(model == 29 || model == 33){
			dbpars[0] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Th"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Bl"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"Bh"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"H"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
*/			
			parsName[0] = "Tl";
			parsName[1] = "Th";
			parsName[2] = "Bl";
			parsName[3] = "Bh";
			parsName[4] = "H";
		}
		if(model == 34){
			dbpars[0] = c.eval(tempar + "["+'"'+"Hl"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Hh"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"Th"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"Bm"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
*/			
			parsName[0] = "Hl";
			parsName[1] = "Tl";
			parsName[2] = "Hh";
			parsName[3] = "Th";
			parsName[4] = "Bm";
		}
		if(model == 35 || model == 37){
			dbpars[0] = c.eval(tempar + "["+'"'+"a1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"a2"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
*/			
			parsName[0] = "a1";
			parsName[1] = "b1";
			parsName[2] = "a2";
			parsName[3] = "b2";
		}
		if(model == 38){
			dbpars[0] = c.eval(tempar + "["+'"'+"a1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"a2"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"c1"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
*/			
			parsName[0] = "a1";
			parsName[1] = "b1";
			parsName[2] = "a2";
			parsName[3] = "b2";
			parsName[4] = "c1";
		}
		if(model == 39){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"nn"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
*/			
			parsName[0] = "a";
			parsName[1] = "b";
			parsName[2] = "nn";
		}
		if(model == 40){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"To"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"d"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
*/			
			parsName[0] = "a";
			parsName[1] = "To";
			parsName[2] = "Tl";
			parsName[3] = "d";
		}
		if(model == 41){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"To"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"d"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
*/			
			parsName[0] = "a";
			parsName[1] = "To";
			parsName[2] = "Tl";
			parsName[3] = "d";
		}
		if(model == 42){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Tmax"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Tmin"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"n"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"m"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
*/			
			parsName[0] = "a";
			parsName[1] = "Tmax";
			parsName[2] = "Tmin";
			parsName[3] = "n";
			parsName[4] = "m";
		}
		if(model == 43){
			dbpars[0] = c.eval(tempar + "["+'"'+"Dmin"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"k"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Tp"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"lamb"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
*/			
			parsName[0] = "Dmin";
			parsName[1] = "k";
			parsName[2] = "Tp";
			parsName[3] = "lamb";
		}
		if(model == 44){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Th"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"B"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
*/			
			parsName[0] = "a";
			parsName[1] = "Tl";
			parsName[2] = "Th";
			parsName[3] = "B";
		}
		if(model == 45){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Th"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"Bl"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"Bh"+'"'+"]").asList().at(0).asDouble();

/*			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
*/			
			parsName[0] = "a";
			parsName[1] = "Tl";
			parsName[2] = "Th";
			parsName[3] = "Bl";
			parsName[4] = "Bh";
		}
		
		pars.setParametersName(parsName);
		return dbpars;
	}
	
	private static Double[] saveFinalPar(String tempar, int model) throws RserveException, REXPMismatchException{
		Double[] dbpars=new Double[]{0.0,0.0,0.0,0.0,0.0,0.0};
		String[] parsName = new String[]{"","","","","",""};
		dbStdpars = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0};
		
		if(model < 7)
		{
			if(tempar.equalsIgnoreCase("inim")){
				dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
				dbpars[1] = c.eval(tempar + "["+'"'+"b"+'"'+"]").asList().at(0).asDouble();
				dbpars[2] = c.eval(tempar + "["+'"'+"c"+'"'+"]").asList().at(0).asDouble();
				return dbpars;
			}
			
			dbpars[0] = c.eval(tempar).asDoubles()[0];
			dbpars[1] = c.eval(tempar).asDoubles()[1];
			dbpars[2] = c.eval(tempar).asDoubles()[2];
			
			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			
			parsName[0] = "a";
			parsName[1] = "b";
			parsName[2] = "c";
		}
		if(model == 7){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"c"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"d"+'"'+"]").asList().at(0).asDouble();
			
			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			
			parsName[0] = "a";
			parsName[1] = "b";
			parsName[2] = "c";
			parsName[3] = "d";
		}
		if(model == 8){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"xo"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"c"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			
			parsName[0] = "a";
			parsName[1] = "b";
			parsName[2] = "xo";
			parsName[3] = "c";
		}
		if(model ==9 || model==10){//models 9 & 10
			dbpars[0] = c.eval(tempar + "["+'"'+"y0"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"x0"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"b"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			
			parsName[0] = "y0";
			parsName[1] = "a";
			parsName[2] = "x0";
			parsName[3] = "b";
		}
		if(model == 11 || model == 20){
			dbpars[0] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"b3"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"d"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			
			parsName[0] = "b1";
			parsName[1] = "b2";
			parsName[2] = "b3";
			parsName[3] = "d";
		}
		if(model == 12 || model == 14 || model == 16 || model == 18 || model == 22){
			dbpars[0] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"b3"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			
			parsName[0] = "b1";
			parsName[1] = "b2";
			parsName[2] = "b3";
		}
		if(model == 13 || model == 15 || model == 17 || model == 19 || model == 23){
			dbpars[0] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"b3"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"b4"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"b5"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
			dbStdpars[5] = c.eval("Std.Error").asDoubles()[5];
			
			parsName[0] = "b1";
			parsName[1] = "b2";
			parsName[2] = "b3";
			parsName[3] = "b4";
			parsName[4] = "b5";
		}
		if(model == 21){
			dbpars[0] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"b3"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"b4"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"b5"+'"'+"]").asList().at(0).asDouble();
			dbpars[5] = c.eval(tempar + "["+'"'+"d"+'"'+"]").asList().at(0).asDouble();
			
			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
			dbStdpars[5] = c.eval("Std.Error").asDoubles()[5];
			dbStdpars[6] = c.eval("Std.Error").asDoubles()[6];
			
			parsName[0] = "b1";
			parsName[1] = "b2";
			parsName[2] = "b3";
			parsName[3] = "b4";
			parsName[4] = "b5";
			parsName[5] = "d";
		}
		if(model == 24 || model == 25){
			dbpars[0] = c.eval(tempar + "["+'"'+"rm"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Topt"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Troh"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			
			parsName[0] = "rm";
			parsName[1] = "Topt";
			parsName[2] = "Troh";
		}
		if(model == 26 || model == 30){
			dbpars[0] = c.eval(tempar + "["+'"'+"Topt"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"B"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"H"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			
			parsName[0] = "Topt";
			parsName[1] = "B";
			parsName[2] = "H";
		}
		if(model == 27 || model == 31){
			dbpars[0] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Th"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"B"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"H"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			
			parsName[0] = "Tl";
			parsName[1] = "Th";
			parsName[2] = "B";
			parsName[3] = "H";
		}
		if(model == 28 || model == 32){
			dbpars[0] = c.eval(tempar + "["+'"'+"Topt"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Bl"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Bh"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"H"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			
			parsName[0] = "Topt";
			parsName[1] = "Bl";
			parsName[2] = "Bh";
			parsName[3] = "H";
		}
		if(model == 29 || model == 33){
			dbpars[0] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Th"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Bl"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"Bh"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"H"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
			
			parsName[0] = "Tl";
			parsName[1] = "Th";
			parsName[2] = "Bl";
			parsName[3] = "Bh";
			parsName[4] = "H";
		}
		if(model == 34){
			dbpars[0] = c.eval(tempar + "["+'"'+"Hl"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Hh"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"Th"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"Bm"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
			
			parsName[0] = "Hl";
			parsName[1] = "Tl";
			parsName[2] = "Hh";
			parsName[3] = "Th";
			parsName[4] = "Bm";
		}
		if(model == 35 || model == 37){
			dbpars[0] = c.eval(tempar + "["+'"'+"a1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"a2"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			
			parsName[0] = "a1";
			parsName[1] = "b1";
			parsName[2] = "a2";
			parsName[3] = "b2";
		}
		if(model == 38){
			dbpars[0] = c.eval(tempar + "["+'"'+"a1"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b1"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"a2"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"b2"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"c1"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
			
			parsName[0] = "a1";
			parsName[1] = "b1";
			parsName[2] = "a2";
			parsName[3] = "b2";
			parsName[4] = "c1";
		}
		if(model == 39){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"b"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"nn"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			
			parsName[0] = "a";
			parsName[1] = "b";
			parsName[2] = "nn";
		}
		if(model == 40){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"To"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"d"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			
			parsName[0] = "a";
			parsName[1] = "To";
			parsName[2] = "Tl";
			parsName[3] = "d";
		}
		if(model == 41){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"To"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"d"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			
			parsName[0] = "a";
			parsName[1] = "To";
			parsName[2] = "Tl";
			parsName[3] = "d";
		}
		if(model == 42){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Tmax"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Tmin"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"n"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"m"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
			
			parsName[0] = "a";
			parsName[1] = "Tmax";
			parsName[2] = "Tmin";
			parsName[3] = "n";
			parsName[4] = "m";
		}
		if(model == 43){
			dbpars[0] = c.eval(tempar + "["+'"'+"Dmin"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"k"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Tp"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"lamb"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			
			parsName[0] = "Dmin";
			parsName[1] = "k";
			parsName[2] = "Tp";
			parsName[3] = "lamb";
		}
		if(model == 44){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Th"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"B"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			
			parsName[0] = "a";
			parsName[1] = "Tl";
			parsName[2] = "Th";
			parsName[3] = "B";
		}
		if(model == 45){
			dbpars[0] = c.eval(tempar + "["+'"'+"a"+'"'+"]").asList().at(0).asDouble();
			dbpars[1] = c.eval(tempar + "["+'"'+"Tl"+'"'+"]").asList().at(0).asDouble();
			dbpars[2] = c.eval(tempar + "["+'"'+"Th"+'"'+"]").asList().at(0).asDouble();
			dbpars[3] = c.eval(tempar + "["+'"'+"Bl"+'"'+"]").asList().at(0).asDouble();
			dbpars[4] = c.eval(tempar + "["+'"'+"Bh"+'"'+"]").asList().at(0).asDouble();

			dbStdpars[0] = c.eval("Std.Error").asDoubles()[0];
			dbStdpars[1] = c.eval("Std.Error").asDoubles()[1];
			dbStdpars[2] = c.eval("Std.Error").asDoubles()[2];
			dbStdpars[3] = c.eval("Std.Error").asDoubles()[3];
			dbStdpars[4] = c.eval("Std.Error").asDoubles()[4];
			
			parsName[0] = "a";
			parsName[1] = "Tl";
			parsName[2] = "Th";
			parsName[3] = "Bl";
			parsName[4] = "Bh";
		}
		
		pars.setParametersName(parsName);
		return dbpars;
	}
	
	
	
	public static int getModelNumber(String StrNameModel){
		int strModelNumber=-1;
		for(int i=0; i<lstModelNames[0].length; i++){
			if(lstModelNames[0][i].equalsIgnoreCase(StrNameModel)){
				strModelNumber = Integer.valueOf(lstModelNames[1][i]);
				break;
			}
		}
		return strModelNumber;
	}
	public static String getModelName(String StrNumberModel){
		String strModelNumber="";
		for(int i=0; i<lstModelNames[0].length; i++){
			if(lstModelNames[1][i].equalsIgnoreCase(StrNumberModel)){
				strModelNumber = lstModelNames[0][i];
				break;
			}
		}
		return strModelNumber;
	}
	
	public static String functToString(REXP func) throws RserveException, REXPMismatchException{
		  
		   String newValue="";
		   String strfunc = func.asString();
		   String[] arrayfunc = ArrayConvertions.StringtoArray(strfunc, ",");
		   
		   newValue = arrayfunc[1].trim() + ' '+ arrayfunc[0].trim() + ' ';
		   
		   for(int i=2;i<arrayfunc.length; i++){
			   if(i==arrayfunc.length-1)
				   newValue += arrayfunc[i];
			   else
				   newValue += arrayfunc[i]+',';
		   }
		   
		   return newValue;
	   }
	
	
	
}
