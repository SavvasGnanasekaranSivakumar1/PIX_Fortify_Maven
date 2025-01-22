# Author      : Yogesh Tyagi
# Company     : HCL Technologies
# Description : script to execute the standalone class in b2bOutbound.jar

cd /casper/xcomgate/B2B/b2b

# set JAVA_HOME
#JAVA_HOME=/usr/local/jdk5

# set HOME_DIR
set HOME_DIR=/casper/xcomgate/B2B

# set APP_DIR
set APP_DIR=$HOME_DIR/b2b

# finally run the java class
java -ms128m -mx512m -jar -DFILEPATH="$APP_DIR/config" -DCONFIGFILE="$APP_DIR/config/b2b.config" b2bOutbound.jar