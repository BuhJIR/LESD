@echo off
REM -----------------------------------------------------------------------------
REM Gradle startup script for Windows
REM -----------------------------------------------------------------------------

setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set WRAPPER_JAR=%DIRNAME%gradle\wrapper\gradle-wrapper.jar

if not exist "%WRAPPER_JAR%" (
    echo Wrapper jar not found at %WRAPPER_JAR%
    exit /b 1
)

set JAVA_EXE=java.exe
if not "%JAVA_HOME%" == "" set JAVA_EXE=%JAVA_HOME%\bin\java.exe

"%JAVA_EXE%" -jar "%WRAPPER_JAR%" %*
endlocal
