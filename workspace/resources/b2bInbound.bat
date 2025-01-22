@rem Author	: Yogesh Tyagi
@rem Company 	: HCL Technologies
@rem Description: script to execute the standalone class in b2bInbound.jar

@rem set JAVA_HOME
set JAVA_HOME=D:/jdk1.5.0_08

@rem set HOME_DIR
set HOME_DIR=D:/projects

@rem set APP_DIR
set APP_DIR=%HOME_DIR%/b2b

@rem finally run the java class
java -ms128m -mx512m -jar -DFILEPATH="%APP_DIR%/config" -DCONFIGFILE="%APP_DIR%/config/b2b.config" %APP_DIR%/b2bInbound.jar