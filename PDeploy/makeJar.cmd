@echo off

set PATH_7Z=C:\Soft\sevenZip
set PATH=%PATH%;%PATH_7z%



if exist Deploy\PXXX.jar del Deploy\PXXX.jar
if errorlevel 1 (
	echo. 
    echo "Failed to delete old PXXX.jar"
    exit /b 1
	)


7z a -tzip Deploy/PXXX.jar ../PXXX/bin/ch
if errorlevel 1 (
	echo.
    echo "Failed to jar PXXX"
    exit /b 1
	)

echo.
echo ----------
echo SUCCESS
echo ----------
echo.

rem pause