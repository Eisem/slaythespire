@echo off
chcp 65001 >nul
cls
echo ========================================
echo 诊断工具 - 检查编译环境
echo ========================================
echo.

echo [检查1] 当前目录
echo 当前目录: %CD%
echo 脚本位置: %~dp0
echo.

echo [检查2] 文件结构
echo.
echo 项目文件:
if exist "pom.xml" (echo [OK] pom.xml) else (echo [X] pom.xml 不存在)
if exist "compile.bat" (echo [OK] compile.bat) else (echo [X] compile.bat 不存在)
if exist "README.md" (echo [OK] README.md) else (echo [X] README.md 不存在)
echo.

echo libs 文件夹:
if exist "libs" (
    echo [OK] libs 文件夹存在
    if exist "libs\BaseMod.jar" (echo [OK] BaseMod.jar) else (echo [X] BaseMod.jar 不存在)
    if exist "libs\desktop-1.0.jar" (echo [OK] desktop-1.0.jar) else (echo [X] desktop-1.0.jar 不存在)
) else (
    echo [X] libs 文件夹不存在
)
echo.

echo [检查3] Java 安装
java -version 2>nul
if %ERRORLEVEL% EQU 0 (
    echo [OK] Java 已安装
    java -version | findstr /i "version"
) else (
    echo [X] Java 未安装
    echo.
    echo 请安装 JDK 8: https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html
)
echo.

echo [检查4] Maven 安装
mvn -version 2>nul
if %ERRORLEVEL% EQU 0 (
    echo [OK] Maven 已安装
    mvn -version | findstr /i "Apache Maven"
) else (
    echo [X] Maven 未安装
    echo.
    echo 请安装 Maven: https://maven.apache.org/download.cgi
)
echo.

echo [检查5] 环境变量
echo JAVA_HOME: %JAVA_HOME%
echo MAVEN_HOME: %MAVEN_HOME%
if "%JAVA_HOME%"=="" (echo [X] JAVA_HOME 未设置) else (echo [OK] JAVA_HOME 已设置)
if "%MAVEN_HOME%"=="" (echo [X] MAVEN_HOME 未设置) else (echo [OK] MAVEN_HOME 已设置)
echo.

echo [检查6] 编译输出
if exist "target" (
    echo [OK] target 文件夹存在
    echo.
    echo target 文件夹内容:
    dir /b target
    echo.
    if exist "target\sts-bot-mod-1.0.0.jar" (
        echo [OK] 编译的JAR存在
        dir "target\sts-bot-mod-1.0.0.jar" | findstr "sts-bot-mod-1.0.0.jar"
    ) else (
        echo [X] 编译的JAR不存在，需要先编译
    )
) else (
    echo [X] target 文件夹不存在，需要先编译
)
echo.

echo ========================================
echo 诊断完成
echo ========================================
echo.

REM 建议
echo [建议]
if "%MAVEN_HOME%"=="" (
    echo.
    echo Maven 未安装，建议使用 IntelliJ IDEA 编译:
    echo 1. 下载 IDEA: https://www.jetbrains.com/idea/download/
    echo 2. 打开项目后按 Ctrl+F9 编译
)
if not exist "libs\desktop-1.0.jar" (
    echo.
    echo 缺少 desktop-1.0.jar，请从游戏目录复制:
    echo C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar
)

echo.
pause
