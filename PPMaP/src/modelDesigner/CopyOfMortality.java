package modelDesigner;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import maizeTools.Rserve;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

//import projects.clasesutils.IlcymUtils;

//import projects.clasesutils.IlcymUtils;

public class CopyOfMortality 
{
	public static String[] lstArrayNames = new String[]{"Briere", "Gaussian", "Gompertz", "Linear","Polynomial","Quadratic", 
		"Taylor", "Wang", "Other models"};
	
	public static String[][] lstMatrixSubNames = new String[][]{{"Briere 1","Briere 2"}, {"Gaussian denominator","Gaussian",
		"Simple gaussian","Gaussian with log"},{"Gompertz","Gompertz-Makeham"},{"Linear root","Negative Linear root",
		"Linear negative exponent"},{"Polynomial 1","Polynomial 2","Polynomial 3","Polynomial 4","Polynomial 5","Polynomial 6",
		"Polynomial 7","Polynomial 8","Polynomial 9","Polynomial 10","Polynomial 11","Polynomial 12","Polynomial 13"},
		{"Quadratic","Quadratic with negative exponent"},{"Taylor 1","Taylor 2"},{"Wang 1","Wang 2","Wang 3","Wang 4","Wang 5",
		"Wang 6","Wang 7","Wang 8","Wang 9","Wang 10"},{"Logarithmic", "SharpeDeMichelle","Marc","DeMoivre","Weibull","Analytis",
		"Janisch & Analytis"}};
		
	public static String[][] lstModelNames = new String[][]{{"Quadratic","Quadratic with negative exponent","Linear root",
		"Negative Linear root","Linear negative exponent","Gaussian denominator","Gaussian","Simple gaussian","Gaussian with log",
		"Polynomial 1","Polynomial 2","Polynomial 3","Polynomial 4","Polynomial 5","Polynomial 6","Polynomial 7","Polynomial 8",
		"Polynomial 9","Polynomial 10","Polynomial 11","Polynomial 12","Polynomial 13","Taylor 1","Taylor 2","Wang 1","Wang 2",
		"Wang 3","Wang 4","Wang 5","Wang 6","Wang 7","Wang 8","Wang 9","Wang 10","Gompertz","Gompertz-Makeham","Briere 1","Briere 2","Logarithmic", "SharpeDeMichelle",
		"Marc","DeMoivre","Weibull","Analytis","Janisch & Analytis"},{"1","4","2","3","5","7","8","9","10","11","12","13","14","15","16","17","18",
		"19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","44","45","37", "38","40","41","6","34","35","36","39","42","43"}};
	
	public static String title = "MaizeDevRate";
	public static String imageName = title + ".png";
	static String saveToR="";
	static RConnection c;
	
	public static Parameters pars = new Parameters();
	
	static REXP rexpAnt, rexpNew;
	static boolean bolBackPars;
	static DecimalFormat df = new DecimalFormat("#####.##########");
	static Double[] dbStdpars;
	private static String framesSel[]= new String[5];
	static Shell[] arraysShell;
	//public static String dirMortalityPath;
	
	
	//this function allow to settle properties of image file
	public static void definirParamsImage(ImageProperties ip)
	{
		try {
			// = ModifyImageUI.ip;
			
			ip.setCorrX1("0");
			ip.setCorrX2("40");
			ip.setCorrY1("0");
			ip.setCorrY2("100");
			ip.setMini("0");
			ip.setMaxi("100");
			ip.setLabX("temperature (degree celsius)");
			ip.setLabY("mortality (%)");
			ip.setTitle("");
			
			c= new RConnection();
			
			saveToR+="corrx=c("+ip.getCorrX1()+','+ip.getCorrX2()+')'+"\r\n";
			c.eval("corrx=c("+ip.getCorrX1()+','+ip.getCorrX2()+')');
			saveToR+="corry=c("+ip.getCorrY1()+','+ip.getCorrY2()+')'+"\r\n";
			c.eval("corry=c("+ip.getCorrY1()+','+ip.getCorrY2()+')');
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
			//Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to set the features of the graph");
		}
	}//end defineParameterImage
	
	
	
	//This function transform mortality data in the format useful for fitting
	public static void prepareDataForMortality(String pathDataFile)
	{
		try 
		{
			c = new RConnection();
			System.out.println("R connexion");
			String parentFolder = new File("./").getCanonicalPath();
			
            String  scrptFile = (parentFolder.concat("/RScripts/mortalityDesigner.r")).replace('\\','/');
            System.out.println(scrptFile);
			//c.eval("source('"+parentFolder+"/RScripts/mortalityDesigner.r')");
            
            
			
            saveToR += "source('"+scrptFile+"')" + "\r\n";	
            c.eval("source('"+scrptFile+"')");
			//c.eval("source('/RScripts/mortalityDesigner.r')");
			
			saveToR += "datm <-formatMortalityData("+pathDataFile+")" + "\r\n";		
			c.eval("datm <-formatMortalityData("+pathDataFile+")");
			
			
			
			
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			MessageDialog.openError(new Shell(), title, "Problems while trying to get data from the project");
			e.printStackTrace();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			MessageDialog.openError(new Shell(), title, "Problems while trying to get data from the project");
			e.printStackTrace();
		}
		
		
			
	}// End of function prepareDataForMortality
	
	
	//This function is use to define initial parameter of a modle before using it to properly fit data
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
		
		switch (model) 
		{
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
	} //End estimate parameter
	

	//This function is used to estimate parameter when many function are selected
	public static void proceesAllModelsNew(RConnection c,boolean bolExtremMin, String algo,String limit, List lstSelectedModels, String dirMortalityPath)
	{
		String path = dirMortalityPath + File.separator + imageName;
		//String stageSel =  MainPageWizardPage.getStageSel();
		//SeveralModelsWizardPage.tableCriterias.removeAll();
		//SeveralModelsWizardPage.lblModelSelAll.setText("");
		//SeveralModelsWizardPage.txtParametersEstimatedAll.setText("");
		
		try {
		//	c = new RConnection();

			if(bolExtremMin){
				saveToR += "tx.min = c(10 )"+"\r\n";//+ MainPageWizardPage.getExtremValues()[0]+'
				c.eval("tx.min = c(10 )");// + MainPageWizardPage.getExtremValues()[0]+')');
				
				saveToR += "ty.min = c(0)"+"\r\n";//+ MainPageWizardPage.getExtremValues()[0]+'
				c.eval("ty.min = c(0)");
				
			/*saveToR += "ty.min = c(" + MainPageWizardPage.getExtremValues()[1]+')'+"\r\n";
				c.eval("ty.min = c(" + MainPageWizardPage.getExtremValues()[1]+')');
				*/
				saveToR += "ty.min <- ty.min/100"+"\r\n";
				c.eval("ty.min <- ty.min/100");
				
				saveToR += "datm<-mort$datam"+"\r\n";  // modify here by putting the function to transfrom data
				c.eval("datm<-mort$datam");

				saveToR += "datm=rbind(data.frame(x=tx.min,y=ty.min),datm)"+"\r\n";
				c.eval("datm=rbind(data.frame(x=tx.min,y=ty.min),datm)");
			}
			c.close();
		}catch (RserveException e1) {
			//Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			Rserve.saveIlcymError(dirMortalityPath, title, saveToR);			
			c.close();
			e1.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to show the model selected");
			return;
		}
		
		//arraysShell = new Shell[MainPageWizardPage.lstSelectedModels.getItemCount()];
		arraysShell = new Shell[lstSelectedModels.getItemCount()];
		
		for(int i=0; i<lstSelectedModels.getItemCount(); i++){
			try {
				//int model = getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(i));
				int modelm = getModelNumber(lstSelectedModels.getItem(i));
				/*Model1UI uiShape1 = new Model1UI(SeveralModelsWizardPage.container.getShell(), modelm, path, stageSel);
				uiShape1.createContents(lstSelectedModels.getItem(i));
				uiShape1.lblFunctImageDR.setBackgroundImage(null);*/
				
				//c = new RConnection(); // we decided to take the connection as an input
				/*saveToR += "limit<-"+'"'+ MainPageWizardPage.getLimits() +'"' +"\n";
				c.eval("limit<-"+'"'+ MainPageWizardPage.getLimits() +'"');*/
				
				saveToR += "limit<-"+'"'+limit +'"' +"\n";
				c.eval("limit<-"+'"'+ limit +'"');
				
				//estimateParameters(c, model, MainPageWizardPage.getSeveralModels(),  false,"", "", "", "", "","");
				estimateParameters(c, modelm, true,  false,"", "", "", "", "","");
				/*
				saveToR +="pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)" +"\n";
				c.eval("pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)");
				c.eval("dev.off()");
				*/ // this section is to explore again
				saveToR +="inim<-pbmortal$ini" +"\n";
				c.eval("inim<-pbmortal$ini");
				
				/*
				if(model < 7){
					saveToR+="alg<-" + '"' + "Newton" + '"'+"\n";
					c.eval("alg<-" + '"' + "Newton" + '"');
					
					saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
					c.eval("weight<-" + '"' + "LS" + '"');
				}else{
					saveToR+="alg<-" + '"' + "Marquardtr" + '"'+"\n";
					c.eval("alg<-" + '"' + "Marquardtr" + '"');
				}
				*/	
					saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
					c.eval("weight<-" + '"' + "LS" + '"');
				
				saveToR+="fmort<-dead_func("+ modelm+", datm, "+algo+", inim, weight,weights)"+"\n";
				c.eval("fmort<-dead_func("+ modelm+", datm, "+algo+", inim, weight,weights)");
				
				
				saveToR+="coefEstimated<-fmort$estimatedCoef"+"\n";
				c.eval("coefEstimated<-fmort$estimatedCoef");
				
				saveToR+="stdmortg <-  fmort$stdmort"+"\n";
				c.eval("stdmortg <-  fmort$stdmort");
				
				saveToR+="model<- fmort$model"+"\n";
				c.eval("model<- fmort$model");
				
				saveToR+="gg<-fmort$f"+"\n";
				c.eval("gg<-fmort$f");
				
				/*
				
				saveToR+="modelim<-fmort$modelo"+"\n";
				c.eval("modelim<-fmort$modelo");
				*/
				
				c.eval("sink("+'"'+dirMortalityPath + "/Mort-Model" +modelm+".txt"+'"'+")");
				/*try {
					IlcymUtils.safeEval(c, "sink("+'"'+MainPageWizardPage.getstrMortalityPath() + "/Mort-Model" +model+".txt"+'"'+")");
				} catch (RException e) {
					c.close();
					e.printStackTrace();
				}*/
				
				System.out.println(c.eval("sink.number()").asInteger());
				
				saveToR+="sol_mort<-coef_mort("+ modelm +",coefEstimated, stdmortg, model,gg,datm,"+algo+",weight,weights)"+"\n";
				c.eval("sol_mort<-coef_mort("+ modelm +",coefEstimated, stdmortg, model,gg,datm,"+algo+",weight,weights)");
				c.eval("sink()");
				
			/*	
				saveToR+="frsel<-sol_mort$frames"+"\r\n";
				c.eval("frsel<-sol_mort$frames");
				
				saveToR+="frsel<-sol_mort$frames"+"\r\n";
				c.eval("frsel<-sol_mort$frames");
				
				int lList= c.eval("frsel").asList().keys().length;
				for(int ii=0; ii<lList;ii++)
					framesSel[ii] = c.eval("frsel").asList().at(ii).asDouble()+"";
				
				c.eval("png(file=" + '"' +MainPageWizardPage.getstrMortalityPath() + "/Mort-Model"+ modelm+".png"+'"'+")");
				saveToR+="plot_mort<-grafmort(" + '"' + "mortal" + '"' + ",modelm,estimor,g,datm,corrx,corry,mini,maxi,limit,1,labx,laby,titulo, grises)"+"\n";
				c.eval("plot_mort<-grafmort(" + '"' + "mortal" + '"' + ",modelm,estimor,g,datm,corrx,corry,mini,maxi,limit,1,labx,laby,titulo, grises)");
				c.eval("dev.off()");
				
				uiShape1.lblFunctImageDR.setImage(new Image(Display.getCurrent(), MainPageWizardPage.getstrMortalityPath() + File.separator + "Mort-Model"+ model+".png"));
				uiShape1.lblFunctImageDR.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			  	
			  	IlcymUtils.createHTMLfile(MainPageWizardPage.getstrMortalityPath(), "Mort-Model"+model);
			  	uiShape1.bwrResultDR.setUrl("file:///" + MainPageWizardPage.getstrMortalityPath() + File.separator + "Mort-Model"+model+".html");
				
				TableItem ti = new TableItem(SeveralModelsWizardPage.tableCriterias, SWT.None);
				ti.setText(new String[]{getModelNumber(MainPageWizardPage.lstSelectedModels.getItem(i))+"",MainPageWizardPage.lstSelectedModels.getItem(i),framesSel[0],framesSel[1],framesSel[2],framesSel[3],framesSel[4]});
				*/
				c.close();
				/*arraysShell[i] = uiShape1.shell;
				uiShape1.shell.open();*/
					
				
			}catch (RserveException e1) {
				Rserve.saveIlcymError(dirMortalityPath, title, saveToR);
				try {
					c.eval("sink()");
				} catch (RserveException e) {
					c.close();
					e.printStackTrace();
				}
				c.close();
				e1.printStackTrace();
				MessageDialog.openError(new Shell(), title, "Problems of convergence of the initial parameter values of "+lstSelectedModels.getItem(i)+" model");
			}catch (REXPMismatchException e) {
				Rserve.saveIlcymError(dirMortalityPath, title, saveToR);
				try {
					c.eval("sink()");
				} catch (RserveException e1) {
					c.close();
					e1.printStackTrace();
				}
				c.close();
				e.printStackTrace();
				MessageDialog.openError(new Shell(), title, "Problems of convergence of the initial parameter values of "+lstSelectedModels.getItem(i)+" model");
			}
		}
	}
	
	
	
//this function estimate parameter and  fit data to selected function
	public static void setModelSelectedOne(RConnection c,int modelm,String algo, String limit,String dirMortalityPath, String str1, String str2, String str3, String str4, String str5, String str6){
		Double[] dbpars = null;
		try {
			//c = new RConnection();
			
			estimateParameters(c, modelm, false,  false,str1, str2, str3, str4, str5, str6);
			
//			saveToR +="pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)" +"\n";
//			c.eval("pbmortal<-pruebamortal(" + '"' + "mortal" + '"' + ",modelm,datm,inim,corrx,corry,mini,maxi,labx,laby,titulo)");
//			c.eval("dev.off()");
			
			saveToR +="inim<-pbmortal$ini" +"\n";
			c.eval("inim<-pbmortal$ini");
			
			//c.eval("limit <- " + '"'+OneModelWizardPage1.getLimitsOne()+'"');
			c.eval("limit <- " + '"'+limit+'"');
			
			/*
			if(modelo < 7){
				saveToR+="alg<-" + '"' + "Newton" + '"'+"\n";
				c.eval("alg<-" + '"' + "Newton" + '"');
				
				saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
				c.eval("weight<-" + '"' + "LS" + '"');
			}else{
				saveToR+="alg<-" + '"' + "Marquardtr" + '"'+"\n";
				c.eval("alg<-" + '"' + "Marquardtr" + '"');
				}
			*/	
				saveToR+="weight<-" + '"' + "LS" + '"'+"\n";
				c.eval("weight<-" + '"' + "LS" + '"');
			
			
			//res=dead_func(modelNumber, datm, alg, inim, weight,weights)  weight
			saveToR+="fmort<-dead_func("+ modelm+", datm, "+algo+", inim, weight,weights)"+"\n";
			c.eval("fmort<-dead_func("+ modelm+", datm, "+algo+", inim, weight,weights)");
			
			//coefEstimated = res$estimatedCoef ;stdmortg = res$stdmort; model = res$model ; gg= res$f
		
			saveToR+="coefEstimated<-fmort$estimatedCoef"+"\n";
			c.eval("coefEstimated<-fmort$estimatedCoef");
			
			saveToR+="stdmortg <-  fmort$stdmort"+"\n";
			c.eval("stdmortg <-  fmort$stdmort");
			
			saveToR+="model<- fmort$model"+"\n";
			c.eval("model<- fmort$model");
			
			saveToR+="gg<-fmort$f"+"\n";
			c.eval("gg<-fmort$f");
			
			
			
			
			
		/*	
			saveToR+="g<-fmort$ecua"+"\n";
			c.eval("g<-fmort$ecua");
						
			
			saveToR+="modelim<-fmort$modelo"+"\n";
			c.eval("modelim<-fmort$modelo");
			*/
			
			c.eval("sink("+'"'+dirMortalityPath + "/"+title + ".txt"+'"'+")");
			saveToR+="sol_mort<-coef_mort("+ modelm +",coefEstimated, stdmortg, model,gg,datm,"+algo+",weight,weights)"+"\n";
			c.eval("sol_mort<-coef_mort("+ modelm +",coefEstimated, stdmortg, model,gg,datm,"+algo+",weight,weights)");
			
			/*cf = coefs$parmor
				rslt = data.frame(ModelName = "sfsd", fleExpression = cf$models, d.f. = cf$d.f.,   F= cf$F, P.value = cf$P.value, 
				R2 = cf$R2,  R2_Adj= cf$R2_Adj, SSR = cf$SSR, AIC= cf$AIC, MSC = cf$MSC, RMSE = cf$RMSE )
			 	finalOutput = rbind(finalOutput,rslt)
			 	
			 	*/
			
			saveToR+="Std.Error<-sol_mort$Std.Error"+"\r\n";
			c.eval("Std.Error<-sol_mort$Std.Error");
			
			saveToR+="param<-sol_mort$parmor"+"\r\n";
			c.eval("param<-sol_mort$parmor");
			
			
			c.eval("sink()");
			/*
			c.eval("png(file=" + '"' +MainPageWizardPage.getstrMortalityPath() + "/" + title +".png"+'"'+")");
			saveToR+="plot_mort<-grafmort(" + '"' + "mortal" + '"' + ",modelm,coefEstimated,g,datm,corrx,corry,mini,maxi,limit,1,labx,laby,titulo, grises)"+"\n";
			c.eval("plot_mort<-grafmort(" + '"' + "mortal" + '"' + ",modelm,coefEstimated,g,datm,corrx,corry,mini,maxi,limit,1,labx,laby,titulo, grises)");
			c.eval("dev.off()");
			
			saveToR += "valxs=plot_mort$valxs";
			c.eval("valxs=plot_mort$valxs");
			
			dbpars= saveTempar("coefEstimated", modelm);
			pars.setParameters(dbpars);
			pars.setStrModel(getModelName(modelm+""));
			pars.setStdParameters(dbStdpars);
			
			IlcymUtils.createHTMLfile(MainPageWizardPage.getstrMortalityPath(), title);
			File temp = new File(MainPageWizardPage.getstrMortalityPath()+ File.separator + title + ".txt");
			temp.delete();
			
			OneModelWizardPage2.lblImageFinal.setImage(new Image(Display.getCurrent(), MainPageWizardPage.getstrMortalityPath()+ File.separator +imageName));
			OneModelWizardPage2.brwModelSel.setUrl("file:///" + MainPageWizardPage.getstrMortalityPath()+ File.separator + title + ".html");
			OneModelWizardPage2.lblModelSelOne.setText(pars.getStrModel());
			*/
			c.close();
		}catch (RserveException e) {
			Rserve.saveIlcymError(dirMortalityPath, title, saveToR);
			try {
				c.eval("sink()");
			} catch (RserveException e1) {
				c.close();
				e1.printStackTrace();
			}
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to set one model selected");
		}
	} // End function setModelSelectOne
	
	
	private static Double[] saveTempar(String tempar, int model) throws RserveException, REXPMismatchException{
		Double[] dbpars=new Double[]{0.0,0.0,0.0,0.0,0.0,0.0};
		String[] parsName = new String[]{"","","","","",""};
		dbStdpars = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0};
		
		if(model < 7){
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
	} //End function saveTempar()
	
	
	// this function allow you to return the number of a model given its name
	public static int getModelNumber(String StrNameModel){
		int strModelNumber=-1;
		for(int i=0; i<lstModelNames[0].length; i++){
			if(lstModelNames[0][i].equalsIgnoreCase(StrNameModel)){
				strModelNumber = Integer.valueOf(lstModelNames[1][i]);
				break;
			}
		}
		return strModelNumber;
	} // End function getModelNumber()
	
	
	
	//this function allow you to retrive the name of a model given its number.
	public static String getModelName(String StrNumberModel){
		String strModelNumber="";
		for(int i=0; i<lstModelNames[0].length; i++){
			if(lstModelNames[1][i].equalsIgnoreCase(StrNumberModel)){
				strModelNumber = lstModelNames[0][i];
				break;
			}
		}
		return strModelNumber;
	} // End function getModelName(...).
	
	
	//This function is used to save the selected model.
	public static boolean saveModelSelected(){
		/*if(MainPageWizardPage.getSeveralModels()){
			if(SeveralModelsWizardPage.lblModelSelAll.getText().equalsIgnoreCase("") 
				&& SeveralModelsWizardPage.txtParametersEstimatedAll.getText().equalsIgnoreCase("")){
				MessageDialog.openError(SeveralModelsWizardPage.container.getShell(), title, 
				"You must to select correctly the model");
				return false;
			}
		}
		deleteAllTempFiles();
		guardarResume();
		newSaveProgress();*/
		return true;
	}
	
	
	public static void randomParameters(){
		/*try {
			c = new RConnection();
			
			rexpAnt = rexpNew;
			
			c.eval("niv=" + OneModelWizardPage1.txtAdjust.getText());
			c.eval("png(file=" + '"' + MainPageWizardPage.getstrMortalityPath().replace("\\", "/") + "/" + imageName + '"' + ")");
			
			
			c.eval("iniback <- inim1");
			c.eval("inim1=recalc(inim,niv=niv"+")");
			c.eval("pbmortal<-pruebamortal("+'"'+"mortal"+'"'+",modelm,datm,inim1,corrx,corry,mini,maxi,labx,laby,titulo)");
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
		}*/
	}
	
	
	public static void backParameters(){
		/*try{
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
			c.eval("pbmortal<-pruebamortal("+'"'+"mortal"+'"'+",modelm,datm,iniback,corrx,corry,mini,maxi,labx,laby,titulo)");
			c.voidEval("dev.off()");
			
			OneModelWizardPage1.lblImageTemp.setImage(new Image(Display.getCurrent(), MainPageWizardPage.getstrMortalityPath() + File.separator + imageName));
			bolBackPars = true;
			c.close();
			
		} catch (RserveException e) {
			Rserve.saveIlcymError(MainPageWizardPage.getstrMortalityPath(), title, saveToR);
			c.close();
			e.printStackTrace();
			MessageDialog.openError(new Shell(), title, "Problems while trying to get the previous parameters");
		}	*/
	}
	
	
	public static void spinnerListener(int model, String str1, String str2, String str3, String str4, String str5, String str6, boolean bolExtremMin/*, boolean bolExtremMax*/){
		/*try {
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
		} */
	}
	
	
	public static void selectionModelOneByOne(int modelo, boolean bolExtremMin){}
	
	public static void setModelSelectedOne(int modelo, String str1, String str2, String str3, String str4, String str5, String str6){}
	
} // EndClass
