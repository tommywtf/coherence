@echo off
@
@rem This will start a cache server
@
setlocal

:config
@rem specify the Coherence installation directory
set coherence_home=%~dp0\..

@rem specify the JVM heap size
set memory=4g

set java_opts="-Xms%memory% -Xmx%memory% -Dcoherence.cluster=tommy_cluster"

:start
if not exist "%coherence_home%\lib\coherence.jar" goto instructions

set java_home=%java_home:"=%

if "%java_home%"=="" (set java_exec=java) else (set "java_exec=%java_home%\bin\java")


:launch

if "%1"=="-jmx" (
	set jmxproperties=-Dcoherence.management=all -Dcoherence.management.remote=true
	shift
)

set java_opts=-Xms%memory% -Xmx%memory% %jmxproperties%

set CLASSPATH=%COHERENCE_HOME%\resources;%COHERENCE_HOME%\externalJars\*;%COHERENCE_HOME%\lib\coherence.jar

"%java_exec%" -server -showversion %java_opts% -classpath %CLASSPATH% com.tangosol.net.DefaultCacheServer %*

goto exit

:instructions

echo Usage:
echo   ^<coherence_home^>\bin\cache-server.cmd
goto exit

:exit
endlocal
@echo on
