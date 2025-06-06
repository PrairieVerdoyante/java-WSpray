@echo off

set PATH_7Z=C:\Soft\sevenZip
set PATH=%PATH%;%PATH_7z%



if exist Deploy\PSpray.jar del Deploy\PSpray.jar
if errorlevel 1 (
	echo. 
    echo "Failed to delete old PSpray.jar"
    exit /b 1
	)


7z a -tzip Deploy/PSpray.jar ../PSpray/bin/ch
if errorlevel 1 (
	echo.
    echo "Failed to jar PSpray"
    exit /b 1
	)

echo.
echo ----------
echo SUCCESS
echo ----------
echo.

rem pause