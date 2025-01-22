@rem Author	: Yogesh Tyagi
@rem Company 	: HCL Technologies
@rem Description: script to execute the standalone class in b2bOutbound.jar

@rem set JAVA_HOME
set JAVA_HOME=C:/Program Files/Java/jdk1.8.0_291

@rem set HOME_DIR
set HOME_DIR=G:/savvas_sekar/pix/pix_workspace_21012022/workspace

@rem set APP_DIR
set APP_DIR=%HOME_DIR%/

@rem finally run the java class
java -ms128m -mx512m -jar -DFILEPATH="%APP_DIR%/config" -DCONFIGFILE="%APP_DIR%/config/b2b.config" %APP_DIR%/b2bOutbound.jar