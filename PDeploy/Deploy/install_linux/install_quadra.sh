#!/bin/bash
#

set -e
set -u

# -------------------------------------------
# 			Tools
# -------------------------------------------

function installJava11()
	{
	apt-get update
	apt-get install openjdk-11-jdk -y
	
	echo ""
	java -version
	echo ""
	}
	
function installJava8()
	{
	add-apt-repository ppa:webupd8team/java -y
	apt-get update
	apt-get install oracle-java8-installer -y
	apt-get install oracle-java8-set-default -y
	
	echo ""
	java -version
	echo ""
	}
	
function deployToOpt()
	{
	local readonly FOLDER_SRC=../../Deploy
	local readonly FOLDER_DESTINATION=/opt/quadra
	
	rm -rf $FOLDER_DESTINATION
	mkdir -p $FOLDER_DESTINATION
	
	cp -r $FOLDER_SRC/. $FOLDER_DESTINATION/
	# - r recursive
	#/. content of

	#droit
	chown root:root -R $FOLDER_DESTINATION
	chmod o-w -R $FOLDER_DESTINATION
	chmod a+rx -R $FOLDER_DESTINATION
	
	echo ""
	echo "Deploy /opt/quadra : success!"
	echo ""
	}


##!/bin/bash
#
# set -e
# set -u
#
# pushd . > /dev/null
#
# cd /opt/quadra
# ./run_quadra.sh $1 $2 $3
#
# popd > /dev/null
#
function configUsrLocalBin()
	{
	local readonly FILE=/usr/local/bin/quadra
	
	rm -f $FILE
	
	echo "#!/bin/bash" > $FILE
	
	echo "set -e" >> $FILE
	echo "set -u" >> $FILE
	
	echo "pushd . > /dev/null" >> $FILE
	
	echo "cd /opt/quadra " >> $FILE
	echo "./run_quadra.sh \$1 \$2 \$3" >> $FILE

	echo "popd > /dev/null" >> $FILE
	
	#droit
	chown root:root $FILE
	chmod o-w $FILE
	chmod a+rx $FILE
	
	#check
	ls -la $FILE
	
	echo ""
	cat $FILE
	
	echo ""
	echo "Deploy /opt/quadra : success!"
	echo ""
	}
	
function purgeWin()
	{
	rm -f ../run.cmd
	rm -f ../run.sh
	rm -f ../run_quadra.cmd
	}	
	
function useCase()
	{
	echo ""
	echo "useCase"
	echo ""
	
	pushd . >/dev/null
	
	cd ~
	quadra -8 -2 3
	
	popd >/dev/null
	}
	
# -------------------------------------------
# 			Main
# -------------------------------------------

function installJavaSoft()
	{
	installJava11
	#installJava8
	
	purgeWin
	
	deployToOpt
	
	configUsrLocalBin
	
	useCase
	}

# -------------------------------------------
# 			Use
# -------------------------------------------

# Goal :
#
#		Utiliser la commande
#		
#				quadra -8 -2 3
#		
#		Dans un terminal, depuis n importe quel folder
#
# Solution :
#
#		Etape 1 : /opt/quadra 
#
#				Deployer dans /opt/quadra notre programme java.
#				Avec un script de lancement run_quadra.sh
#				
#				Usage : 
#
#						cd /opt/quadra
#						./run_quadra.sh -8 -2 3	
#				
#				Observation:
#
#						Pas tres pratique,
#						mais permet de tester l'étape 1
#
#		Etape 2: /usr/local/bin
#
#				Creer dans /usr/local/bin la commande quadra
#
#				Usage : 
#
#						quadra -8 -2 3
#
# Contrainte:
#
#		Use this script :
#
#				(C1) from the folder where you find it!	
#				(C2) from a terminal
#

installJavaSoft

# -------------------------------------------
# 			end
# -------------------------------------------


