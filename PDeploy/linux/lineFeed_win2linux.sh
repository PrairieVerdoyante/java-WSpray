#!/bin/bash
#

set -e
set -u

# -------------------------------------------
# 			tools
# -------------------------------------------

function linefeedVersion1()
	{
	sed --in-place 's/\r//g' $1
	}
	
function linefeedVersion2()
	{
	dos2unix $1
	}
	
# -------------------------------------------
# 			main
# -------------------------------------------

FILE1=../Deploy/run_quadra.sh
FILE2=../Deploy/run.sh
FILE3=../Deploy/install_linux/install_quadra.sh

linefeedVersion2 $FILE1
linefeedVersion2 $FILE2
linefeedVersion2 $FILE3

chmod a+x $FILE1
chmod a+x $FILE2
chmod a+x $FILE3

# -------------------------------------------
# 			end
# -------------------------------------------

echo ""
