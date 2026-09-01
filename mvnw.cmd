@echo off
setlocal
set SCRIPT_DIR=%~dp0
set WRAPPER_DIR=%SCRIPT_DIR%\.mvn\wrapper
set WRAPPER_JAR=%WRAPPER_DIR%\maven-wrapper.jar

if not exist "%WRAPPER_JAR%" (
  echo Downloading Maven Wrapper jar...
  if not exist "%WRAPPER_DIR%" mkdir "%WRAPPER_DIR%"
  powershell -Command "try { (New-Object System.Net.WebClient).DownloadFile('https://repo1.maven.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar','%WRAPPER_JAR%') } catch { exit 1 }"
  if errorlevel 1 (
    echo Failed to download maven-wrapper.jar
    exit /b 1
  )
)

if defined JAVA_HOME (
  "%JAVA_HOME%\bin\java" -cp "%WRAPPER_JAR%" org.apache.maven.wrapper.WrapperExecutor %*
) else (
  java -Dmaven.multiModuleProjectDirectory="%~dp0" -cp "%WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
)
