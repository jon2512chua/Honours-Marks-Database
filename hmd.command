DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

echo $DIR

JARPATH=$DIR/HMD.jar 

echo $JARPATH

java -XstartOnFirstThread -jar $JARPATH

exit 0

#SCRIPT=$(realpath "$0")
#SCRIPT=$(readlink -f "$0")
#SCRIPTPATH=$(dirname "$SCRIPT")
#SCRIPTPATH=$(dirname "$SCRIPT")

