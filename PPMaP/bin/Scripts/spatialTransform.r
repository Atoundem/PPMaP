library(MASS)
library(sp)
library(maptools)
library(rgdal)
library(maps)
library(doRNG)


#This function divided the area of study caracterized by "ilat" in R part.

p.area<-function(ilat,R)
{
####################################################################################################
# ilat  is a vector of length 2 of the of the extreme values of latitude.
#
##################################################################################################
  R=R+1
  lats=seq(ilat[1],ilat[2],length.out=R)+0.0000000001
  mat.lat=matrix(NA,R-1,2)
  for(i in 2:R) 
  {
    mat.lat[i-1,]=c(lats[i-1],lats[i])
  }
  return(mat.lat)
}


#########################################################


#Indices joining of separate areas according to latitude
#########################################################
zone.div<-function(dir1,dir2,ilon,ilat,R,dir.out,name.out=name.out,out,filtro,fmle = NULL,coefEstimated = NULL,alg,season = NULL)
{
  
  lats=p.area(ilat,R)
  regRemoved = NULL
  #############################################
  # Running through each area and store your data
  #############################################
  
  for(j in R:1){
    
    TempsAll=GenMatriz(dir1,dir2,ilon,lats[j,],season)
    
    coords=TempsAll$coords;nfil=nrow(coords)
	if(nfil == 0)
	{ 
		regRemoved = c(regRemoved,j)
		next
	}	
    
    x1=TempsAll$x1
    
    y1=TempsAll$y1
    
    
    RFinal=matrix(NA,nfil,1) ## change the dimension
    
    ################################
    
    # Corriendo por punto del Area j
	#Area running point j
    
    ################################
    
    #system.time(
    
    for(i in 1:nfil){
       
      RFinal[i,]= GenActIndex(i,TempsAll=TempsAll,coords=coords,x1=x1,y1=y1,out,filtro,fmle,coefEstimated,alg)$indices 
    }
    
    #)
    
    rm(TempsAll);rm(x1);rm(y1)
    
    Inds=data.frame(coords[1:nfil,],RFinal)
    
    rm(RFinal)
    
    ##################################################################
    
    # Generating header files: Lon - Lat - GI - AI - ERI
    
    ##################################################################
    
    write.table(Inds,paste(dir.out,"file",j,".txt",sep=""),row.names = F)
    
    rm(Inds)
  }
  
  ###########################################
  
  # Running through each area and joining their data
  
 
#In this part before trying to read the file for the result we insure that it has been create.
	Inds = NULL

	for(j in R:1)
	{
	  print(j)
	  if (!(j %in% regRemoved))
	  {
		TempInds=read.table(paste(dir.out,"file",j,".txt",sep=""),header=T)
		Inds=rbind(Inds,TempInds)
	  }
	  
	}

  
   rm(TempInds)
  gridded(Inds) = ~x+y ## Creating the Object Grid
  
  writeAsciiGrid(Inds["RFinal"], na.value = -9999,paste(dir.out,"IMT.asc",sep="")) 
  
	#IMT = readAsciiGrid(paste(dir.out,"IMT.asc",sep=""))
	#spplot(IMT[c("IMT.asc")])

}

#############################################
#
# function generation the index
########################################

GenActIndex<-function(posic,TempsAll=TempsAll,coords=coords,x1=x1,y1=y1,out,filtro, fmle = NULL, coefEstimated = NULL,alg)
{
#######################################################################################
#  coords=coords(a set of coordinate),x1=x1( longitude of the giving coordinate),y1=y1(latitude of using coordinate),
#  out(model to use) ,filtro(a vector of length 2 will be used to filter inpuut value of input variable of the function)
#
##########################################################################################

  d1<-1:length(x1);d2<-1:length(y1);filtroin=TRUE
  plon=d1[x1==coords[posic,1]]
  plat=d2[y1==coords[posic,2]]
  
  Table=data.frame(mini=c(TempsAll$zTmin1[plon,plat],TempsAll$zTmin2[plon,plat],TempsAll$zTmin3[plon,plat],TempsAll$zTmin4[plon,plat],TempsAll$zTmin5[plon,plat],TempsAll$zTmin6[plon,plat],TempsAll$zTmin7[plon,plat],TempsAll$zTmin8[plon,plat],TempsAll$zTmin9[plon,plat],TempsAll$zTmin10[plon,plat],TempsAll$zTmin11[plon,plat],TempsAll$zTmin12[plon,plat],TempsAll$zTmin13[plon,plat],TempsAll$zTmin14[plon,plat],TempsAll$zTmin15[plon,plat],TempsAll$zTmin16[plon,plat],TempsAll$zTmin17[plon,plat],TempsAll$zTmin18[plon,plat],TempsAll$zTmin19[plon,plat],TempsAll$zTmin20[plon,plat],TempsAll$zTmin21[plon,plat],TempsAll$zTmin22[plon,plat],TempsAll$zTmin23[plon,plat],TempsAll$zTmin24[plon,plat],TempsAll$zTmin25[plon,plat],TempsAll$zTmin26[plon,plat],TempsAll$zTmin27[plon,plat],TempsAll$zTmin28[plon,plat],TempsAll$zTmin29[plon,plat],TempsAll$zTmin30[plon,plat],TempsAll$zTmin31[plon,plat],TempsAll$zTmin32[plon,plat],TempsAll$zTmin33[plon,plat],TempsAll$zTmin34[plon,plat],TempsAll$zTmin35[plon,plat],TempsAll$zTmin36[plon,plat],TempsAll$zTmin37[plon,plat],TempsAll$zTmin38[plon,plat],TempsAll$zTmin39[plon,plat],TempsAll$zTmin40[plon,plat],TempsAll$zTmin41[plon,plat],TempsAll$zTmin42[plon,plat],TempsAll$zTmin43[plon,plat],TempsAll$zTmin44[plon,plat],TempsAll$zTmin45[plon,plat],TempsAll$zTmin46[plon,plat],TempsAll$zTmin47[plon,plat],TempsAll$zTmin48[plon,plat],TempsAll$zTmin49[plon,plat],TempsAll$zTmin50[plon,plat],TempsAll$zTmin51[plon,plat],TempsAll$zTmin52[plon,plat]),
                   maxi=c(TempsAll$zTmax1[plon,plat],TempsAll$zTmax2[plon,plat],TempsAll$zTmax3[plon,plat],TempsAll$zTmax4[plon,plat],TempsAll$zTmax5[plon,plat],TempsAll$zTmax6[plon,plat],TempsAll$zTmax7[plon,plat],TempsAll$zTmax8[plon,plat],TempsAll$zTmax9[plon,plat],TempsAll$zTmax10[plon,plat],TempsAll$zTmax11[plon,plat],TempsAll$zTmax12[plon,plat],TempsAll$zTmax13[plon,plat],TempsAll$zTmax14[plon,plat],TempsAll$zTmax15[plon,plat],TempsAll$zTmax16[plon,plat],TempsAll$zTmax17[plon,plat],TempsAll$zTmax18[plon,plat],TempsAll$zTmax19[plon,plat],TempsAll$zTmax20[plon,plat],TempsAll$zTmax21[plon,plat],TempsAll$zTmax22[plon,plat],TempsAll$zTmax23[plon,plat],TempsAll$zTmax24[plon,plat],TempsAll$zTmax25[plon,plat],TempsAll$zTmax26[plon,plat],TempsAll$zTmax27[plon,plat],TempsAll$zTmax28[plon,plat],TempsAll$zTmax29[plon,plat],TempsAll$zTmax30[plon,plat],TempsAll$zTmax31[plon,plat],TempsAll$zTmax32[plon,plat],TempsAll$zTmax33[plon,plat],TempsAll$zTmax34[plon,plat],TempsAll$zTmax35[plon,plat],TempsAll$zTmax36[plon,plat],TempsAll$zTmax37[plon,plat],TempsAll$zTmax38[plon,plat],TempsAll$zTmax39[plon,plat],TempsAll$zTmax40[plon,plat],TempsAll$zTmax41[plon,plat],TempsAll$zTmax42[plon,plat],TempsAll$zTmax43[plon,plat],TempsAll$zTmax44[plon,plat],TempsAll$zTmax45[plon,plat],TempsAll$zTmax46[plon,plat],TempsAll$zTmax47[plon,plat],TempsAll$zTmax48[plon,plat],TempsAll$zTmax49[plon,plat],TempsAll$zTmax50[plon,plat],TempsAll$zTmax51[plon,plat],TempsAll$zTmax52[plon,plat]))
  
  Table=Table/10;Table2=cbind(id=1:nrow(Table),Table) ## filter at this temperature  // for original worldclim temperature input data 
 # Table2=cbind(id=1:nrow(Table),Table) ## filter at this temperature    // use when temperature data are in their normal forma!! data are not divided by 10
 
  
  if(length(Table[is.na(Table)])==0)
  {
    if(!is.null(filtro))
	{
		tmm=apply(Table,2,mean,na.rm=TRUE);
		if(tmm[1] > filtro[1] && tmm[2] < filtro[2]){filtroin=TRUE}else{filtroin=FALSE}
	}
    if(filtroin)
    {
      
    
    
      IME=mean(tmm)  # we compute the mean value using the min and the max temperature for the giving coordinate.
      
	 if (alg == "Newton")
	  {IMT = predict(out, list(x = IME))}  #we apply the model to the mean of extracted temperature for each location to compute the value of mortality for the giving location in case of newton algo
	  
	  if(alg == "Marquardtr") 
	{
		x = IME
		
		ind <- as.list(coefEstimated)
		  for (i in names(ind))
		  {
			temp <- coefEstimated[[i]]
			storage.mode(temp) <- "double"
			assign(i, temp)
			
		  }
		
		forex <- fmle[[length(fmle)]]  # #we apply the model to the mean of extracted temperature for each location to compute the value of mortality for the giving location  in case of Marquardtr algo.
		ylexpx<-as.expression(forex)
		IMT<-round(1/eval(ylexpx))
	
    
    }
	  
   

      indices=c(IMT=IMT)
      return(list(indices=indices))
    }else{indices=c(IMT=NA);return(list(indices=indices))}
  }else{
    
    indices=c(IMT=NA)
    return(list(indices=indices))
  }
  
}
##########################################################
# This function read temperature data and construct the matrix of location will be used to 
# extract temperature data .
#####=================================================================================
GenMatriz<-function(dir1,dir2,ilon,ilat,season = NULL){
  
  zTmin1=NULL;zTmin2=NULL;zTmin3=NULL;zTmin4=NULL;zTmin5=NULL;zTmin6=NULL;zTmin7=NULL;zTmin8=NULL;zTmin9=NULL;zTmin10=NULL;zTmin11=NULL;zTmin12=NULL;zTmax1=NULL;zTmax2=NULL;zTmax3=NULL;zTmax4=NULL;zTmax5=NULL;zTmax6=NULL;zTmax7=NULL;zTmax8=NULL;zTmax9=NULL;zTmax10=NULL;zTmax11=NULL;zTmax12=NULL;
  zTmin13=NULL;zTmin14=NULL;zTmin15=NULL;zTmin16=NULL;zTmin17=NULL;zTmin18=NULL;zTmin19=NULL;zTmin20=NULL;zTmin21=NULL;zTmin22=NULL;zTmin23=NULL;zTmin24=NULL;zTmax13=NULL;zTmax14=NULL;zTmax15=NULL;zTmax16=NULL;zTmax17=NULL;zTmax18=NULL;zTmax19=NULL;zTmax20=NULL;zTmax21=NULL;zTmax22=NULL;zTmax23=NULL;zTmax24=NULL;
  zTmin25=NULL;zTmin26=NULL;zTmin27=NULL;zTmin28=NULL;zTmin29=NULL;zTmin30=NULL;zTmin31=NULL;zTmin32=NULL;zTmin33=NULL;zTmin34=NULL;zTmin35=NULL;zTmin36=NULL;zTmax25=NULL;zTmax26=NULL;zTmax27=NULL;zTmax28=NULL;zTmax29=NULL;zTmax30=NULL;zTmax31=NULL;zTmax32=NULL;zTmax33=NULL;zTmax34=NULL;zTmax35=NULL;zTmax36=NULL;
  zTmin37=NULL;zTmin38=NULL;zTmin39=NULL;zTmin40=NULL;zTmin41=NULL;zTmin42=NULL;zTmin43=NULL;zTmin44=NULL;zTmin45=NULL;zTmin46=NULL;zTmin47=NULL;zTmin48=NULL; zTmax37=NULL;zTmax38=NULL;zTmax39=NULL;zTmax40=NULL;zTmax41=NULL;zTmax42=NULL;zTmax43=NULL;zTmax44=NULL;zTmax45=NULL;zTmax46=NULL;zTmax47=NULL;zTmax48=NULL;
  zTmin49=NULL;zTmin50=NULL;zTmin51=NULL;zTmin52=NULL;zTmax49=NULL;zTmax50=NULL;zTmax51=NULL;zTmax52=NULL;
  coords=NULL;x1=NULL;y1=NULL
  
  
  archivos1=list.files(dir1,pattern="flt");archivos1=paste(dir1,"/",archivos1,sep="")
  archivos2=list.files(dir2,pattern="flt");archivos2=paste(dir2,"/",archivos2,sep="")
  
  Tmin1=readGDAL(archivos1[1])
  
  # for the extraction of data use the function:
  
  geodat=data.frame(coordinates(Tmin1))
  
  ###########################################
  ## Extraction of longitudes and latitudes
  
  x <- c(geodat[,1]);x <- unique(x)
  n1=length(x) ## tamaño
  
  y <- c(geodat[,2]); y <- unique(y)
  n2=length(y)
  
  #rm(geodat)
  
  #######################################################################
  ## Extraction of resolutions for both length to latitude
  
  v1=Tmin1@grid@cellsize[1]/2
  v2=Tmin1@grid@cellsize[2]/2
  
  
  ##############################################################
  ## Defining ranges in the longitude and latitude with position
  
  r11=ilon[1];r12=ilon[2];r21=ilat[1];r22=ilat[2]
  
  k1=rownames(geodat[geodat[,1] >= (r11-v1) & geodat[,1] <= (r12+v1),])
  k2=rownames(geodat[geodat[,2] >= (r21-v2) & geodat[,2] <= (r22+v2),])
  
  sector=intersect(k1,k2)
  sector=as.numeric(sector)
  
  coords=geodat[sector,]
  
  rm(geodat)
  
  
  #################################################
  ## Defining ranges in the longitude and latitude 
  
  ind1<-1:n1
  d1=x>=(r11-v1) & x<=(r12+v1)
  x1=x[d1]
  ind1=ind1[d1]
  
  
  ind2<-1:n2
  d2=y>=(r21-v2) & y<=(r22+v2)
  y1=y[d2]
  ind2=ind2[d2]
  
  
  ####################################################################################
  ## Creating the array containing the values of the variable for each coordinate
  if( is.null(season))
  {
    z <- c(Tmin1@data[,1]);zTmin1=matrix(z,n1,n2);rm(Tmin1)
    zTmin1=zTmin1[ind1,ind2]; rownames(zTmin1)=x1; colnames(zTmin1)=y1
    Tmin2=readGDAL(archivos1[2])
    z <- c(Tmin2@data[,1]);zTmin2=matrix(z,n1,n2);rm(Tmin2)
    zTmin2=zTmin2[ind1,ind2]; rownames(zTmin2)=x1; colnames(zTmin2)=y1
    Tmin3=readGDAL(archivos1[3])
    z <- c(Tmin3@data[,1]);zTmin3=matrix(z,n1,n2);rm(Tmin3)
    zTmin3=zTmin3[ind1,ind2]; rownames(zTmin3)=x1; colnames(zTmin3)=y1
    Tmin4=readGDAL(archivos1[4])
    z <- c(Tmin4@data[,1]);zTmin4=matrix(z,n1,n2);rm(Tmin4)
    zTmin4=zTmin4[ind1,ind2]; rownames(zTmin4)=x1; colnames(zTmin4)=y1
    
    Tmin5=readGDAL(archivos1[5])
    z <- c(Tmin5@data[,1]);zTmin5=matrix(z,n1,n2);rm(Tmin5)
    zTmin5=zTmin5[ind1,ind2]; rownames(zTmin5)=x1; colnames(zTmin5)=y1
    Tmin6=readGDAL(archivos1[6])
    z <- c(Tmin6@data[,1]);zTmin6=matrix(z,n1,n2);rm(Tmin6)
    zTmin6=zTmin6[ind1,ind2]; rownames(zTmin6)=x1; colnames(zTmin6)=y1
    Tmin7=readGDAL(archivos1[7])
    z <- c(Tmin7@data[,1]);zTmin7=matrix(z,n1,n2);rm(Tmin7)
    zTmin7=zTmin7[ind1,ind2]; rownames(zTmin7)=x1; colnames(zTmin7)=y1
    Tmin8=readGDAL(archivos1[8])
    z <- c(Tmin8@data[,1]);zTmin8=matrix(z,n1,n2);rm(Tmin8)
    zTmin8=zTmin8[ind1,ind2]; rownames(zTmin8)=x1; colnames(zTmin8)=y1
    Tmin9=readGDAL(archivos1[9])
    z <- c(Tmin9@data[,1]);zTmin9=matrix(z,n1,n2);rm(Tmin9)
    zTmin9=zTmin9[ind1,ind2]; rownames(zTmin9)=x1; colnames(zTmin9)=y1
    Tmin10=readGDAL(archivos1[10])
    z <- c(Tmin10@data[,1]);zTmin10=matrix(z,n1,n2);rm(Tmin10)
    zTmin10=zTmin10[ind1,ind2]; rownames(zTmin10)=x1; colnames(zTmin10)=y1
    Tmin11=readGDAL(archivos1[11])
    z <- c(Tmin11@data[,1]);zTmin11=matrix(z,n1,n2);rm(Tmin11)
    zTmin11=zTmin11[ind1,ind2]; rownames(zTmin11)=x1; colnames(zTmin11)=y1
    Tmin12=readGDAL(archivos1[12])
    z <- c(Tmin12@data[,1]);zTmin12=matrix(z,n1,n2);rm(Tmin12)
    zTmin12=zTmin12[ind1,ind2]; rownames(zTmin12)=x1; colnames(zTmin12)=y1
    rm(z)
    
    Tmax1=readGDAL(archivos2[1])
    z <- c(Tmax1@data[,1]);zTmax1=matrix(z,n1,n2);rm(Tmax1)
    zTmax1=zTmax1[ind1,ind2]; rownames(zTmax1)=x1; colnames(zTmax1)=y1
    Tmax2=readGDAL(archivos2[2])
    z <- c(Tmax2@data[,1]);zTmax2=matrix(z,n1,n2);rm(Tmax2)
    zTmax2=zTmax2[ind1,ind2]; rownames(zTmax2)=x1; colnames(zTmax2)=y1
    Tmax3=readGDAL(archivos2[3])
    z <- c(Tmax3@data[,1]);zTmax3=matrix(z,n1,n2);rm(Tmax3)
    zTmax3=zTmax3[ind1,ind2]; rownames(zTmax3)=x1; colnames(zTmax3)=y1
    Tmax4=readGDAL(archivos2[4])
    z <- c(Tmax4@data[,1]);zTmax4=matrix(z,n1,n2);rm(Tmax4)
    zTmax4=zTmax4[ind1,ind2]; rownames(zTmax4)=x1; colnames(zTmax4)=y1
    
    Tmax5=readGDAL(archivos2[5])
    z <- c(Tmax5@data[,1]);zTmax5=matrix(z,n1,n2);rm(Tmax5)
    zTmax5=zTmax5[ind1,ind2]; rownames(zTmax5)=x1; colnames(zTmax5)=y1
    Tmax6=readGDAL(archivos2[6])
    z <- c(Tmax6@data[,1]);zTmax6=matrix(z,n1,n2);rm(Tmax6)
    zTmax6=zTmax6[ind1,ind2]; rownames(zTmax6)=x1; colnames(zTmax6)=y1
    Tmax7=readGDAL(archivos2[7])
    z <- c(Tmax7@data[,1]);zTmax7=matrix(z,n1,n2);rm(Tmax7)
    zTmax7=zTmax7[ind1,ind2]; rownames(zTmax7)=x1; colnames(zTmax7)=y1
    Tmax8=readGDAL(archivos2[8])
    z <- c(Tmax8@data[,1]);zTmax8=matrix(z,n1,n2);rm(Tmax8)
    zTmax8=zTmax8[ind1,ind2]; rownames(zTmax8)=x1; colnames(zTmax8)=y1
    Tmax9=readGDAL(archivos2[9])
    z <- c(Tmax9@data[,1]);zTmax9=matrix(z,n1,n2);rm(Tmax9)
    zTmax9=zTmax9[ind1,ind2]; rownames(zTmax9)=x1; colnames(zTmax9)=y1
    Tmax10=readGDAL(archivos2[10])
    z <- c(Tmax10@data[,1]);zTmax10=matrix(z,n1,n2);rm(Tmax10)
    zTmax10=zTmax10[ind1,ind2]; rownames(zTmax10)=x1; colnames(zTmax10)=y1
    Tmax11=readGDAL(archivos2[11])
    z <- c(Tmax11@data[,1]);zTmax11=matrix(z,n1,n2);rm(Tmax11)
    zTmax11=zTmax11[ind1,ind2]; rownames(zTmax11)=x1; colnames(zTmax11)=y1
    Tmax12=readGDAL(archivos2[12])
    z <- c(Tmax12@data[,1]);zTmax12=matrix(z,n1,n2);rm(Tmax12)
    zTmax12=zTmax12[ind1,ind2]; rownames(zTmax12)=x1; colnames(zTmax12)=y1
    rm(z)
    
    
    result = list(zTmin1=zTmin1,zTmin2=zTmin2,zTmin3=zTmin3,zTmin4=zTmin4,zTmin5=zTmin5,zTmin6=zTmin6,zTmin7=zTmin7,zTmin8=zTmin8,zTmin9=zTmin9,zTmin10=zTmin10,zTmin11=zTmin11,zTmin12=zTmin12,zTmax1=zTmax1,zTmax2=zTmax2,zTmax3=zTmax3,zTmax4=zTmax4,zTmax5=zTmax5,zTmax6=zTmax6,zTmax7=zTmax7,zTmax8=zTmax8,zTmax9=zTmax9,zTmax10=zTmax10,zTmax11=zTmax11,zTmax12=zTmax12,coords=coords,x1=x1,y1=y1)
    
  }else
  {
    for(k in 1:length(season))
    {
      if(season[k]== 1)
      {
        z <- c(Tmin1@data[,1]);zTmin1=matrix(z,n1,n2);rm(Tmin1)
        zTmin1=zTmin1[ind1,ind2]; rownames(zTmin1)=x1; colnames(zTmin1)=y1
        
        Tmax1=readGDAL(archivos2[1])
        z <- c(Tmax1@data[,1]);zTmax1=matrix(z,n1,n2);rm(Tmax1)
        zTmax1=zTmax1[ind1,ind2]; rownames(zTmax1)=x1; colnames(zTmax1)=y1
        
      }else if(season[k]== 2)
      {
        Tmin2=readGDAL(archivos1[2])
        z <- c(Tmin2@data[,1]);zTmin2=matrix(z,n1,n2);rm(Tmin2)
        zTmin2=zTmin2[ind1,ind2]; rownames(zTmin2)=x1; colnames(zTmin2)=y1
        
        Tmax2=readGDAL(archivos2[2])
        z <- c(Tmax2@data[,1]);zTmax2=matrix(z,n1,n2);rm(Tmax2)
        zTmax2=zTmax2[ind1,ind2]; rownames(zTmax2)=x1; colnames(zTmax2)=y1
        
      }else if(season[k]== 3)
      {
        Tmin3=readGDAL(archivos1[3])
        z <- c(Tmin3@data[,1]);zTmin3=matrix(z,n1,n2);rm(Tmin3)
        zTmin3=zTmin3[ind1,ind2]; rownames(zTmin3)=x1; colnames(zTmin3)=y1
        
        
        Tmax3=readGDAL(archivos2[3])
        z <- c(Tmax3@data[,1]);zTmax3=matrix(z,n1,n2);rm(Tmax3)
        zTmax3=zTmax3[ind1,ind2]; rownames(zTmax3)=x1; colnames(zTmax3)=y1
        
        
      }else if(season[k]== 4)
      {
        Tmin4=readGDAL(archivos1[4])
        z <- c(Tmin4@data[,1]);zTmin4=matrix(z,n1,n2);rm(Tmin4)
        zTmin4=zTmin4[ind1,ind2]; rownames(zTmin4)=x1; colnames(zTmin4)=y1
        
        Tmax4=readGDAL(archivos2[4])
        z <- c(Tmax4@data[,1]);zTmax4=matrix(z,n1,n2);rm(Tmax4)
        zTmax4=zTmax4[ind1,ind2]; rownames(zTmax4)=x1; colnames(zTmax4)=y1
        
        
        
      }else if(season[k]== 5)
      {
        
        Tmin5=readGDAL(archivos1[5])
        z <- c(Tmin5@data[,1]);zTmin5=matrix(z,n1,n2);rm(Tmin5)
        zTmin5=zTmin5[ind1,ind2]; rownames(zTmin5)=x1; colnames(zTmin5)=y1
        
        Tmax5=readGDAL(archivos2[5])
        z <- c(Tmax5@data[,1]);zTmax5=matrix(z,n1,n2);rm(Tmax5)
        zTmax5=zTmax5[ind1,ind2]; rownames(zTmax5)=x1; colnames(zTmax5)=y1
        
        
      }else if(season[k]== 6)
      {
        Tmin6=readGDAL(archivos1[6])
        z <- c(Tmin6@data[,1]);zTmin6=matrix(z,n1,n2);rm(Tmin6)
        zTmin6=zTmin6[ind1,ind2]; rownames(zTmin6)=x1; colnames(zTmin6)=y1
        
        Tmax6=readGDAL(archivos2[6])
        z <- c(Tmax6@data[,1]);zTmax6=matrix(z,n1,n2);rm(Tmax6)
        zTmax6=zTmax6[ind1,ind2]; rownames(zTmax6)=x1; colnames(zTmax6)=y1
        
        
      }else if(season[k]== 7)
      {
        Tmin7=readGDAL(archivos1[7])
        z <- c(Tmin7@data[,1]);zTmin7=matrix(z,n1,n2);rm(Tmin7)
        zTmin7=zTmin7[ind1,ind2]; rownames(zTmin7)=x1; colnames(zTmin7)=y1
        
        Tmax7=readGDAL(archivos2[7])
        z <- c(Tmax7@data[,1]);zTmax7=matrix(z,n1,n2);rm(Tmax7)
        zTmax7=zTmax7[ind1,ind2]; rownames(zTmax7)=x1; colnames(zTmax7)=y1
        
        
      }else if(season[k]== 8)
      {
        Tmin8=readGDAL(archivos1[8])
        z <- c(Tmin8@data[,1]);zTmin8=matrix(z,n1,n2);rm(Tmin8)
        zTmin8=zTmin8[ind1,ind2]; rownames(zTmin8)=x1; colnames(zTmin8)=y1
        
        Tmax8=readGDAL(archivos2[8])
        z <- c(Tmax8@data[,1]);zTmax8=matrix(z,n1,n2);rm(Tmax8)
        zTmax8=zTmax8[ind1,ind2]; rownames(zTmax8)=x1; colnames(zTmax8)=y1
        
        
      }else if(season[k]== 9)
      {
        Tmin9=readGDAL(archivos1[9])
        z <- c(Tmin9@data[,1]);zTmin9=matrix(z,n1,n2);rm(Tmin9)
        zTmin9=zTmin9[ind1,ind2]; rownames(zTmin9)=x1; colnames(zTmin9)=y1
        
        Tmax9=readGDAL(archivos2[9])
        z <- c(Tmax9@data[,1]);zTmax9=matrix(z,n1,n2);rm(Tmax9)
        zTmax9=zTmax9[ind1,ind2]; rownames(zTmax9)=x1; colnames(zTmax9)=y1
        
        
      }else if(season[k]== 10)
      {
        Tmin10=readGDAL(archivos1[10])
        z <- c(Tmin10@data[,1]);zTmin10=matrix(z,n1,n2);rm(Tmin10)
        zTmin10=zTmin10[ind1,ind2]; rownames(zTmin10)=x1; colnames(zTmin10)=y1
        
        Tmax10=readGDAL(archivos2[10])
        z <- c(Tmax10@data[,1]);zTmax10=matrix(z,n1,n2);rm(Tmax10)
        zTmax10=zTmax10[ind1,ind2]; rownames(zTmax10)=x1; colnames(zTmax10)=y1
        
        
      }else if(season[k]== 11)
      {
        Tmin11=readGDAL(archivos1[11])
        z <- c(Tmin11@data[,1]);zTmin11=matrix(z,n1,n2);rm(Tmin11)
        zTmin11=zTmin11[ind1,ind2]; rownames(zTmin11)=x1; colnames(zTmin11)=y1
        
        Tmax11=readGDAL(archivos2[11])
        z <- c(Tmax11@data[,1]);zTmax11=matrix(z,n1,n2);rm(Tmax11)
        zTmax11=zTmax11[ind1,ind2]; rownames(zTmax11)=x1; colnames(zTmax11)=y1
        
        
      }else if(season[k]== 12)
      {
        Tmin12=readGDAL(archivos1[12])
        z <- c(Tmin12@data[,1]);zTmin12=matrix(z,n1,n2);rm(Tmin12)
        zTmin12=zTmin12[ind1,ind2]; rownames(zTmin12)=x1; colnames(zTmin12)=y1
        
        Tmax12=readGDAL(archivos2[12])
        z <- c(Tmax12@data[,1]);zTmax12=matrix(z,n1,n2);rm(Tmax12)
        zTmax12=zTmax12[ind1,ind2]; rownames(zTmax12)=x1; colnames(zTmax12)=y1
        
        
      }else if(season[k]== 13)
      {
        Tmin13=readGDAL(archivos1[13])
        z <- c(Tmin13@data[,1]);zTmin13=matrix(z,n1,n2);rm(Tmin13)
        zTmin13=zTmin13[ind1,ind2]; rownames(zTmin13)=x1; colnames(zTmin13)=y1
        
        Tmax13=readGDAL(archivos2[13])
        z <- c(Tmax13@data[,1]);zTmax13=matrix(z,n1,n2);rm(Tmax13)
        zTmax13=zTmax13[ind1,ind2]; rownames(zTmax13)=x1; colnames(zTmax13)=y1
        
        
        
      }else if(season[k]== 14)
      {
        Tmin14=readGDAL(archivos1[14])
        z <- c(Tmin14@data[,1]);zTmin14=matrix(z,n1,n2);rm(Tmin14)
        zTmin14=zTmin14[ind1,ind2]; rownames(zTmin14)=x1; colnames(zTmin14)=y1
        
        Tmax14=readGDAL(archivos2[14])
        z <- c(Tmax14@data[,1]);zTmax14=matrix(z,n1,n2);rm(Tmax14)
        zTmax14=zTmax14[ind1,ind2]; rownames(zTmax14)=x1; colnames(zTmax14)=y1
        
        
      }else if(season[k]== 15)
      {
        Tmin15=readGDAL(archivos1[15])
        z <- c(Tmin15@data[,1]);zTmin15=matrix(z,n1,n2);rm(Tmin15)
        zTmin15=zTmin15[ind1,ind2]; rownames(zTmin15)=x1; colnames(zTmin15)=y1
        
        Tmax15=readGDAL(archivos2[15])
        z <- c(Tmax15@data[,1]);zTmax15=matrix(z,n1,n2);rm(Tmax15)
        zTmax15=zTmax15[ind1,ind2]; rownames(zTmax15)=x1; colnames(zTmax15)=y1
        
        
      }else if(season[k]== 16)
      {
        Tmin16=readGDAL(archivos1[16])
        z <- c(Tmin16@data[,1]);zTmin16=matrix(z,n1,n2);rm(Tmin16)
        zTmin16=zTmin16[ind1,ind2]; rownames(zTmin16)=x1; colnames(zTmin16)=y1
        
        Tmax16=readGDAL(archivos2[16])
        z <- c(Tmax16@data[,1]);zTmax16=matrix(z,n1,n2);rm(Tmax16)
        zTmax16=zTmax16[ind1,ind2]; rownames(zTmax16)=x1; colnames(zTmax16)=y1
        
      }else if(season[k]== 17)
      {
        Tmin17=readGDAL(archivos1[17])
        z <- c(Tmin17@data[,1]);zTmin17=matrix(z,n1,n2);rm(Tmin17)
        zTmin17=zTmin17[ind1,ind2]; rownames(zTmin17)=x1; colnames(zTmin17)=y1
        
        Tmax17=readGDAL(archivos2[17])
        z <- c(Tmax17@data[,1]);zTmax17=matrix(z,n1,n2);rm(Tmax17)
        zTmax17=zTmax17[ind1,ind2]; rownames(zTmax17)=x1; colnames(zTmax17)=y1
        
        
      }else if(season[k]== 18)
      {
        Tmin18=readGDAL(archivos1[18])
        z <- c(Tmin18@data[,1]);zTmin18=matrix(z,n1,n2);rm(Tmin18)
        zTmin18=zTmin18[ind1,ind2]; rownames(zTmin18)=x1; colnames(zTmin18)=y1
        
        Tmax18=readGDAL(archivos2[18])
        z <- c(Tmax18@data[,1]);zTmax18=matrix(z,n1,n2);rm(Tmax18)
        zTmax18=zTmax18[ind1,ind2]; rownames(zTmax18)=x1; colnames(zTmax18)=y1
        
      }else if(season[k]== 19)
      {
        Tmin19=readGDAL(archivos1[19])
        z <- c(Tmin19@data[,1]);zTmin19=matrix(z,n1,n2);rm(Tmin19)
        zTmin19=zTmin19[ind1,ind2]; rownames(zTmin19)=x1; colnames(zTmin19)=y1
        
        Tmax19=readGDAL(archivos2[19])
        z <- c(Tmax19@data[,1]);zTmax19=matrix(z,n1,n2);rm(Tmax19)
        zTmax19=zTmax19[ind1,ind2]; rownames(zTmax19)=x1; colnames(zTmax19)=y1
        
      }else if(season[k]== 20)
      {
        Tmin20=readGDAL(archivos1[20])
        z <- c(Tmin20@data[,1]);zTmin20=matrix(z,n1,n2);rm(Tmin20)
        zTmin20=zTmin20[ind1,ind2]; rownames(zTmin20)=x1; colnames(zTmin20)=y1
        
        Tmax20=readGDAL(archivos2[20])
        z <- c(Tmax20@data[,1]);zTmax20=matrix(z,n1,n2);rm(Tmax20)
        zTmax20=zTmax20[ind1,ind2]; rownames(zTmax20)=x1; colnames(zTmax20)=y1
        
      }else if(season[k]== 21)
      {
        Tmin21=readGDAL(archivos1[21])
        z <- c(Tmin21@data[,1]);zTmin21=matrix(z,n1,n2);rm(Tmin21)
        zTmin21=zTmin21[ind1,ind2]; rownames(zTmin21)=x1; colnames(zTmin21)=y1
        
        Tmax21=readGDAL(archivos2[21])
        z <- c(Tmax21@data[,1]);zTmax21=matrix(z,n1,n2);rm(Tmax21)
        zTmax21=zTmax21[ind1,ind2]; rownames(zTmax21)=x1; colnames(zTmax21)=y1
        
      }else if(season[k]== 22)
      {
        Tmin22=readGDAL(archivos1[22])
        z <- c(Tmin22@data[,1]);zTmin22=matrix(z,n1,n2);rm(Tmin22)
        zTmin22=zTmin22[ind1,ind2]; rownames(zTmin22)=x1; colnames(zTmin22)=y1
        
        Tmax22=readGDAL(archivos2[22])
        z <- c(Tmax22@data[,1]);zTmax22=matrix(z,n1,n2);rm(Tmax22)
        zTmax22=zTmax22[ind1,ind2]; rownames(zTmax22)=x1; colnames(zTmax22)=y1
        
      }else if(season[k]== 23)
      {
        Tmin23=readGDAL(archivos1[23])
        z <- c(Tmin23@data[,1]);zTmin23=matrix(z,n1,n2);rm(Tmin23)
        zTmin23=zTmin23[ind1,ind2]; rownames(zTmin23)=x1; colnames(zTmin23)=y1
        
        Tmax23=readGDAL(archivos2[23])
        z <- c(Tmax23@data[,1]);zTmax23=matrix(z,n1,n2);rm(Tmax23)
        zTmax23=zTmax23[ind1,ind2]; rownames(zTmax23)=x1; colnames(zTmax23)=y1
        
      }else if(season[k]== 24)
      {
        Tmin24=readGDAL(archivos1[24])
        z <- c(Tmin24@data[,1]);zTmin24=matrix(z,n1,n2);rm(Tmin24)
        zTmin24=zTmin24[ind1,ind2]; rownames(zTmin24)=x1; colnames(zTmin24)=y1
        
        Tmax24=readGDAL(archivos2[24])
        z <- c(Tmax24@data[,1]);zTmax24=matrix(z,n1,n2);rm(Tmax24)
        zTmax24=zTmax24[ind1,ind2]; rownames(zTmax24)=x1; colnames(zTmax24)=y1
        
      }else if(season[k]== 25)
      {
        Tmin25=readGDAL(archivos1[25])
        z <- c(Tmin25@data[,1]);zTmin25=matrix(z,n1,n2);rm(Tmin25)
        zTmin25=zTmin25[ind1,ind2]; rownames(zTmin25)=x1; colnames(zTmin25)=y1
        
        Tmax25=readGDAL(archivos2[25])
        z <- c(Tmax25@data[,1]);zTmax25=matrix(z,n1,n2);rm(Tmax25)
        zTmax25=zTmax25[ind1,ind2]; rownames(zTmax25)=x1; colnames(zTmax25)=y1
        
      }else if(season[k]== 26)
      {
        Tmin26=readGDAL(archivos1[26])
        z <- c(Tmin26@data[,1]);zTmin26=matrix(z,n1,n2);rm(Tmin26)
        zTmin26=zTmin26[ind1,ind2]; rownames(zTmin26)=x1; colnames(zTmin26)=y1
        
        Tmax26=readGDAL(archivos2[26])
        z <- c(Tmax26@data[,1]);zTmax26=matrix(z,n1,n2);rm(Tmax26)
        zTmax26=zTmax26[ind1,ind2]; rownames(zTmax26)=x1; colnames(zTmax26)=y1
        
      }else if(season[k]== 27)
      {
        Tmin27=readGDAL(archivos1[27])
        z <- c(Tmin27@data[,1]);zTmin27=matrix(z,n1,n2);rm(Tmin27)
        zTmin27=zTmin27[ind1,ind2]; rownames(zTmin27)=x1; colnames(zTmin27)=y1
        
        Tmax27=readGDAL(archivos2[27])
        z <- c(Tmax27@data[,1]);zTmax27=matrix(z,n1,n2);rm(Tmax27)
        zTmax27=zTmax27[ind1,ind2]; rownames(zTmax27)=x1; colnames(zTmax27)=y1
        
      }else if(season[k]== 28)
      {
        Tmin28=readGDAL(archivos1[28])
        z <- c(Tmin28@data[,1]);zTmin28=matrix(z,n1,n2);rm(Tmin28)
        zTmin28=zTmin28[ind1,ind2]; rownames(zTmin28)=x1; colnames(zTmin28)=y1
        
        Tmax28=readGDAL(archivos2[28])
        z <- c(Tmax28@data[,1]);zTmax28=matrix(z,n1,n2);rm(Tmax28)
        zTmax28=zTmax28[ind1,ind2]; rownames(zTmax28)=x1; colnames(zTmax28)=y1
        
      }else if(season[k]== 29)
      {
        Tmin29=readGDAL(archivos1[29])
        z <- c(Tmin29@data[,1]);zTmin29=matrix(z,n1,n2);rm(Tmin29)
        zTmin29=zTmin29[ind1,ind2]; rownames(zTmin29)=x1; colnames(zTmin29)=y1
        
        Tmax29=readGDAL(archivos2[29])
        z <- c(Tmax29@data[,1]);zTmax29=matrix(z,n1,n2);rm(Tmax29)
        zTmax29=zTmax29[ind1,ind2]; rownames(zTmax29)=x1; colnames(zTmax29)=y1
        
      }else if(season[k]== 30)
      {
        Tmin30=readGDAL(archivos1[30])
        z <- c(Tmin30@data[,1]);zTmin30=matrix(z,n1,n2);rm(Tmin30)
        zTmin30=zTmin30[ind1,ind2]; rownames(zTmin30)=x1; colnames(zTmin30)=y1
        
        Tmax30=readGDAL(archivos2[30])
        z <- c(Tmax30@data[,1]);zTmax30=matrix(z,n1,n2);rm(Tmax30)
        zTmax30=zTmax30[ind1,ind2]; rownames(zTmax30)=x1; colnames(zTmax30)=y1
        
      }else if(season[k]== 31)
      {
        Tmin31=readGDAL(archivos1[31])
        z <- c(Tmin31@data[,1]);zTmin31=matrix(z,n1,n2);rm(Tmin31)
        zTmin31=zTmin31[ind1,ind2]; rownames(zTmin31)=x1; colnames(zTmin31)=y1
        
        Tmax31=readGDAL(archivos2[31])
        z <- c(Tmax31@data[,1]);zTmax31=matrix(z,n1,n2);rm(Tmax31)
        zTmax31=zTmax31[ind1,ind2]; rownames(zTmax31)=x1; colnames(zTmax31)=y1
        
      }else if(season[k]== 32)
      {
        Tmin32=readGDAL(archivos1[32])
        z <- c(Tmin32@data[,1]);zTmin32=matrix(z,n1,n2);rm(Tmin32)
        zTmin32=zTmin32[ind1,ind2]; rownames(zTmin32)=x1; colnames(zTmin32)=y1
        
        Tmax32=readGDAL(archivos2[32])
        z <- c(Tmax32@data[,1]);zTmax32=matrix(z,n1,n2);rm(Tmax32)
        zTmax32=zTmax32[ind1,ind2]; rownames(zTmax32)=x1; colnames(zTmax32)=y1
        
      }else if(season[k]== 33)
      {
        Tmin33=readGDAL(archivos1[33])
        z <- c(Tmin33@data[,1]);zTmin33=matrix(z,n1,n2);rm(Tmin33)
        zTmin33=zTmin33[ind1,ind2]; rownames(zTmin33)=x1; colnames(zTmin33)=y1
        
        Tmax33=readGDAL(archivos2[33])
        z <- c(Tmax33@data[,1]);zTmax33=matrix(z,n1,n2);rm(Tmax33)
        zTmax33=zTmax33[ind1,ind2]; rownames(zTmax33)=x1; colnames(zTmax33)=y1
        
      }else if(season[k]== 34)
      {
        Tmin34=readGDAL(archivos1[34])
        z <- c(Tmin34@data[,1]);zTmin34=matrix(z,n1,n2);rm(Tmin34)
        zTmin34=zTmin34[ind1,ind2]; rownames(zTmin34)=x1; colnames(zTmin34)=y1
        
        Tmax34=readGDAL(archivos2[34])
        z <- c(Tmax34@data[,1]);zTmax34=matrix(z,n1,n2);rm(Tmax34)
        zTmax34=zTmax34[ind1,ind2]; rownames(zTmax34)=x1; colnames(zTmax34)=y1
        
      }else if(season[k]== 35)
      {
        Tmin35=readGDAL(archivos1[35])
        z <- c(Tmin35@data[,1]);zTmin35=matrix(z,n1,n2);rm(Tmin35)
        zTmin35=zTmin35[ind1,ind2]; rownames(zTmin35)=x1; colnames(zTmin35)=y1
        
        Tmax35=readGDAL(archivos2[35])
        z <- c(Tmax35@data[,1]);zTmax35=matrix(z,n1,n2);rm(Tmax35)
        zTmax35=zTmax35[ind1,ind2]; rownames(zTmax35)=x1; colnames(zTmax35)=y1
        
      }else if(season[k]== 36)
      {
        Tmin36=readGDAL(archivos1[36])
        z <- c(Tmin36@data[,1]);zTmin36=matrix(z,n1,n2);rm(Tmin36)
        zTmin36=zTmin36[ind1,ind2]; rownames(zTmin36)=x1; colnames(zTmin36)=y1
        
        Tmax36=readGDAL(archivos2[36])
        z <- c(Tmax36@data[,1]);zTmax36=matrix(z,n1,n2);rm(Tmax36)
        zTmax36=zTmax36[ind1,ind2]; rownames(zTmax36)=x1; colnames(zTmax36)=y1
        
      }else if(season[k]== 37)
      {
        Tmin37=readGDAL(archivos1[37])
        z <- c(Tmin37@data[,1]);zTmin37=matrix(z,n1,n2);rm(Tmin37)
        zTmin37=zTmin37[ind1,ind2]; rownames(zTmin37)=x1; colnames(zTmin37)=y1
        
        Tmax37=readGDAL(archivos2[37])
        z <- c(Tmax37@data[,1]);zTmax37=matrix(z,n1,n2);rm(Tmax37)
        zTmax37=zTmax37[ind1,ind2]; rownames(zTmax37)=x1; colnames(zTmax37)=y1
        
      }else if(season[k]== 38)
      {
        Tmin38=readGDAL(archivos1[38])
        z <- c(Tmin38@data[,1]);zTmin38=matrix(z,n1,n2);rm(Tmin38)
        zTmin38=zTmin38[ind1,ind2]; rownames(zTmin38)=x1; colnames(zTmin38)=y1
        
        Tmax38=readGDAL(archivos2[38])
        z <- c(Tmax38@data[,1]);zTmax38=matrix(z,n1,n2);rm(Tmax38)
        zTmax38=zTmax38[ind1,ind2]; rownames(zTmax38)=x1; colnames(zTmax38)=y1
        
      }else if(season[k]== 39)
      {
        Tmin39=readGDAL(archivos1[39])
        z <- c(Tmin39@data[,1]);zTmin39=matrix(z,n1,n2);rm(Tmin39)
        zTmin39=zTmin39[ind1,ind2]; rownames(zTmin39)=x1; colnames(zTmin39)=y1
        
        Tmax39=readGDAL(archivos2[39])
        z <- c(Tmax39@data[,1]);zTmax39=matrix(z,n1,n2);rm(Tmax39)
        zTmax39=zTmax39[ind1,ind2]; rownames(zTmax39)=x1; colnames(zTmax39)=y1
        
      }else if(season[k]== 40)
      {
        Tmin40=readGDAL(archivos1[40])
        z <- c(Tmin40@data[,1]);zTmin40=matrix(z,n1,n2);rm(Tmin40)
        zTmin40=zTmin40[ind1,ind2]; rownames(zTmin40)=x1; colnames(zTmin40)=y1
        
        Tmax40=readGDAL(archivos2[40])
        z <- c(Tmax40@data[,1]);zTmax40=matrix(z,n1,n2);rm(Tmax40)
        zTmax40=zTmax40[ind1,ind2]; rownames(zTmax40)=x1; colnames(zTmax40)=y1
        
      }else if(season[k]== 41)
      {
        Tmin41=readGDAL(archivos1[41])
        z <- c(Tmin41@data[,1]);zTmin41=matrix(z,n1,n2);rm(Tmin41)
        zTmin41=zTmin41[ind1,ind2]; rownames(zTmin41)=x1; colnames(zTmin41)=y1
        
        Tmax41=readGDAL(archivos2[41])
        z <- c(Tmax41@data[,1]);zTmax41=matrix(z,n1,n2);rm(Tmax41)
        zTmax41=zTmax41[ind1,ind2]; rownames(zTmax41)=x1; colnames(zTmax41)=y1
        
      }else if(season[k]== 42)
      {
        Tmin42=readGDAL(archivos1[42])
        z <- c(Tmin42@data[,1]);zTmin42=matrix(z,n1,n2);rm(Tmin42)
        zTmin42=zTmin42[ind1,ind2]; rownames(zTmin42)=x1; colnames(zTmin42)=y1
        
        Tmax42=readGDAL(archivos2[42])
        z <- c(Tmax42@data[,1]);zTmax42=matrix(z,n1,n2);rm(Tmax42)
        zTmax42=zTmax42[ind1,ind2]; rownames(zTmax42)=x1; colnames(zTmax42)=y1
        
      }else if(season[k]== 43)
      {
        Tmin43=readGDAL(archivos1[43])
        z <- c(Tmin43@data[,1]);zTmin43=matrix(z,n1,n2);rm(Tmin43)
        zTmin43=zTmin43[ind1,ind2]; rownames(zTmin43)=x1; colnames(zTmin43)=y1
        
        Tmax43=readGDAL(archivos2[43])
        z <- c(Tmax43@data[,1]);zTmax43=matrix(z,n1,n2);rm(Tmax43)
        zTmax43=zTmax43[ind1,ind2]; rownames(zTmax43)=x1; colnames(zTmax43)=y1
        
      }else if(season[k]== 44)
      {
        Tmin44=readGDAL(archivos1[44])
        z <- c(Tmin44@data[,1]);zTmin44=matrix(z,n1,n2);rm(Tmin44)
        zTmin44=zTmin44[ind1,ind2]; rownames(zTmin44)=x1; colnames(zTmin44)=y1
        
        Tmax44=readGDAL(archivos2[44])
        z <- c(Tmax44@data[,1]);zTmax44=matrix(z,n1,n2);rm(Tmax44)
        zTmax44=zTmax44[ind1,ind2]; rownames(zTmax44)=x1; colnames(zTmax44)=y1
        
      }else if(season[k]== 45)
      {
        Tmin45=readGDAL(archivos1[45])
        z <- c(Tmin45@data[,1]);zTmin45=matrix(z,n1,n2);rm(Tmin45)
        zTmin45=zTmin45[ind1,ind2]; rownames(zTmin45)=x1; colnames(zTmin45)=y1
        
        Tmax45=readGDAL(archivos2[45])
        z <- c(Tmax45@data[,1]);zTmax45=matrix(z,n1,n2);rm(Tmax45)
        zTmax45=zTmax45[ind1,ind2]; rownames(zTmax45)=x1; colnames(zTmax45)=y1
        
      }else if(season[k]== 46)
      {
        Tmin46=readGDAL(archivos1[46])
        z <- c(Tmin46@data[,1]);zTmin46=matrix(z,n1,n2);rm(Tmin46)
        zTmin46=zTmin46[ind1,ind2]; rownames(zTmin46)=x1; colnames(zTmin46)=y1
        
        Tmax46=readGDAL(archivos2[46])
        z <- c(Tmax46@data[,1]);zTmax46=matrix(z,n1,n2);rm(Tmax46)
        zTmax46=zTmax46[ind1,ind2]; rownames(zTmax46)=x1; colnames(zTmax46)=y1
        
      }else if(season[k]== 47)
      {
        Tmin47=readGDAL(archivos1[47])
        z <- c(Tmin47@data[,1]);zTmin47=matrix(z,n1,n2);rm(Tmin47)
        zTmin47=zTmin47[ind1,ind2]; rownames(zTmin47)=x1; colnames(zTmin47)=y1
        
        Tmax47=readGDAL(archivos2[47])
        z <- c(Tmax47@data[,1]);zTmax47=matrix(z,n1,n2);rm(Tmax47)
        zTmax47=zTmax47[ind1,ind2]; rownames(zTmax47)=x1; colnames(zTmax47)=y1
        
      }else if(season[k]== 48)
      {
        Tmin48=readGDAL(archivos1[48])
        z <- c(Tmin48@data[,1]);zTmin48=matrix(z,n1,n2);rm(Tmin48)
        zTmin48=zTmin48[ind1,ind2]; rownames(zTmin48)=x1; colnames(zTmin48)=y1
        
        Tmax48=readGDAL(archivos2[48])
        z <- c(Tmax48@data[,1]);zTmax48=matrix(z,n1,n2);rm(Tmax48)
        zTmax48=zTmax48[ind1,ind2]; rownames(zTmax48)=x1; colnames(zTmax48)=y1
        
      }else if(season[k]== 49)
      {
        Tmin49=readGDAL(archivos1[49])
        z <- c(Tmin49@data[,1]);zTmin49=matrix(z,n1,n2);rm(Tmin49)
        zTmin49=zTmin49[ind1,ind2]; rownames(zTmin49)=x1; colnames(zTmin49)=y1
        
        Tmax49=readGDAL(archivos2[49])
        z <- c(Tmax49@data[,1]);zTmax49=matrix(z,n1,n2);rm(Tmax49)
        zTmax49=zTmax49[ind1,ind2]; rownames(zTmax49)=x1; colnames(zTmax49)=y1
        
      }else if(season[k]== 50)
      {
        Tmin50=readGDAL(archivos1[50])
        z <- c(Tmin50@data[,1]);zTmin50=matrix(z,n1,n2);rm(Tmin50)
        zTmin50=zTmin50[ind1,ind2]; rownames(zTmin50)=x1; colnames(zTmin50)=y1
        
        Tmax50=readGDAL(archivos2[50])
        z <- c(Tmax50@data[,1]);zTmax50=matrix(z,n1,n2);rm(Tmax50)
        zTmax50=zTmax50[ind1,ind2]; rownames(zTmax50)=x1; colnames(zTmax50)=y1
        
      }else if(season[k]== 51)
      {
        Tmin51=readGDAL(archivos1[51])
        z <- c(Tmin51@data[,1]);zTmin51=matrix(z,n1,n2);rm(Tmin51)
        zTmin51=zTmin51[ind1,ind2]; rownames(zTmin51)=x1; colnames(zTmin51)=y1
        
        Tmax51=readGDAL(archivos2[51])
        z <- c(Tmax51@data[,1]);zTmax51=matrix(z,n1,n2);rm(Tmax51)
        zTmax51=zTmax51[ind1,ind2]; rownames(zTmax51)=x1; colnames(zTmax51)=y1
        
      }else if(season[k]== 52)
      {
        Tmin52=readGDAL(archivos1[52])
        z <- c(Tmin52@data[,1]);zTmin52=matrix(z,n1,n2);rm(Tmin52)
        zTmin52=zTmin52[ind1,ind2]; rownames(zTmin52)=x1; colnames(zTmin52)=y1
        
        Tmax52=readGDAL(archivos2[52])
        z <- c(Tmax52@data[,1]);zTmax52=matrix(z,n1,n2);rm(Tmax52)
        zTmax52=zTmax52[ind1,ind2]; rownames(zTmax52)=x1; colnames(zTmax52)=y1
        
      }
      
      
      rm(z)
      
    }
    result = list(zTmin1=zTmin1,zTmin2=zTmin2,zTmin3=zTmin3,zTmin4=zTmin4,zTmin5=zTmin5,zTmin6=zTmin6,zTmin7=zTmin7,zTmin8=zTmin8,zTmin9=zTmin9,zTmin10=zTmin10 ,zTmin11=zTmin11,zTmin12=zTmin12,zTmin13=zTmin13,zTmin14=zTmin14,zTmin15=zTmin15,zTmin16=zTmin16,zTmin17=zTmin17,zTmin18=zTmin18,zTmin19=zTmin19,zTmin20=zTmin20,zTmin21=zTmin21,zTmin22=zTmin22,zTmin23=zTmin23,zTmin24=zTmin24,zTmin25=zTmin25,zTmin26=zTmin26,zTmin27=zTmin27,zTmin28=zTmin28,zTmin29=zTmin29,zTmin30=zTmin30,zTmin31=zTmin31,zTmin32=zTmin32,zTmin33=zTmin33,zTmin34=zTmin34,zTmin35=zTmin35,zTmin36=zTmin36,zTmin37=zTmin37,zTmin38=zTmin38,zTmin39=zTmin39,zTmin40=zTmin40,zTmin41=zTmin41,zTmin42=zTmin42,zTmin43=zTmin43,zTmin44=zTmin44,zTmin45=zTmin45,zTmin46=zTmin46,zTmin47=zTmin47,zTmin48=zTmin48,zTmin49=zTmin49,zTmin50=zTmin50,zTmin51=zTmin51,zTmin52=zTmin52,zTmax1=zTmax1,zTmax2=zTmax2,zTmax3=zTmax3,zTmax4=zTmax4,zTmax5=zTmax5,zTmax6=zTmax6,zTmax7=zTmax7,zTmax8=zTmax8,zTmax9=zTmax9,zTmax10=zTmax10,zTmax11=zTmax11,zTmax12=zTmax12,zTmax13=zTmax13,zTmax14=zTmax14,zTmax15=zTmax15,zTmax16=zTmax16,zTmax17=zTmax17,zTmax18=zTmax18,zTmax19=zTmax19,zTmax20=zTmax20,zTmax21=zTmax21,zTmax22=zTmax22,zTmax23=zTmax23,zTmax24=zTmax24,zTmax25=zTmax25,zTmax26=zTmax26,zTmax27=zTmax27,zTmax28=zTmax28,zTmax29=zTmax29,zTmax30=zTmax30,zTmax31=zTmax31,zTmax32=zTmax32,zTmax33=zTmax33,zTmax34=zTmax34,zTmax35=zTmax35,zTmax36=zTmax36,zTmax37=zTmax37,zTmax38=zTmax38,zTmax39=zTmax39,zTmax40=zTmax40,zTmax41=zTmax41,zTmax42=zTmax42,zTmax43=zTmax43,zTmax44=zTmax44,zTmax45=zTmax45,zTmax46=zTmax46,zTmax47=zTmax47,zTmax48=zTmax48,zTmax49=zTmax49,zTmax50=zTmax50,zTmax51=zTmax51,zTmax52=zTmax52,coords=coords,x1=x1,y1=y1)
  }
  
  
  return(result)
}



plotMap<- function(dir.out,fileName)
{
	IMT = readAsciiGrid(paste(dir.out,fileName,".asc",sep=""))
	print(spplot(IMT[c(paste(fileName,".asc",sep=""))]))

		
}
