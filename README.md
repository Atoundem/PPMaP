# PPMaP
 PPMaP is a software for  plant phonological phase prediction and mapping. This work was implemented by the International Maize and Wheat Improvement Center (CIMMYT) as part of TAMASA (Taking Maize
Agronomy to Scale) project in collaboration with IITA and IPNI.


#########################  INSTALLATION AND EXECUTION  #######################################
PPMaP requite for is execution R to  install R package and launch Rserve; and the Java Virtual Machine(JVM) to launch PPMaP runnable Jar. To proceed, follow the instruction bellow.

# Install R sorftware and Java!!
#After Installed and open R, execute this commande to install usefull R-package!! 
#
    install.packages(c("minpack.lm","MASS", "sp","maptools", "rgdal", "maps","doRNG","Rserve"), dependencies = T) 

#Once the installation of the packages is completed, execute the following two(2) commande in the console to launch Rserve

	library(Rserve)
	Rserve()
	
#Now to run PPMaP software just "DOUBLE-CLICK" the runnable-Jar(PPMaP_1.0.jar) you downloaded from PPMaP repository.
#Once it open, Create a project, build temperature-dependant model, generate the map using the provided functionalities in the toolbar.

