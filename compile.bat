@echo off
chcp 65001 >nul
echo ========================================
echo STS Bot Mod - 一键编译脚本
echo ========================================
echo.

REM 检查Maven是否安装
where mvn >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [错误] 未找到 Maven！
    echo.
    echo 请先安装 Maven：
    echo 1. 访问 https://maven.apache.org/download.cgi
    echo 2. 下载 apache-maven-3.9.6-bin.zip
    echo 3. 解压到 C:\Program Files\Apache\maven
    echo 4. 添加环境变量：MAVEN_HOME = C:\Program Files\Apache\maven
    echo 5. 添加到 PATH: %%MAVEN_HOME%%\bin
    echo.
    pause
    exit /b 1
)

echo [1/5] 检查依赖文件...
if not exist "libs\BaseMod.jar" (
    echo [错误] 缺少 libs/BaseMod.jar
    pause
    exit /b 1
)
if not exist "libs\desktop-1.0.jar" (
    echo [错误] 缺少 libs/desktop-1.0.jar
    echo.
    echo 请从游戏目录复制：
    echo C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar
    pause
    exit /b 1
)
echo ✓ 依赖文件检查通过
echo.

echo [2/5] 清理旧的编译文件...
call mvn clean
echo.

echo [3/5] 编译项目...
call mvn package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo [错误] 编译失败！
    echo.
    echo 请尝试：
    echo 1. 确保安装了 Java 8
    echo 2. 确保安装了 Maven
    echo 3. 检查 libs/ 文件夹中的文件
    pause
    exit /b 1
)
echo.

echo [4/5] 检查编译结果...
if not exist "target\sts-bot-mod-1.0.0.jar" (
    echo [错误] 编译成功但找不到 JAR 文件！
    pause
    exit /b 1
)
echo ✓ 编译成功！
echo.

echo [5/5] 复制到游戏目录...
set GAME_DIR=C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire
if not exist "%GAME_DIR%" (
    echo [警告] 未找到游戏目录：%GAME_DIR%
    echo 请手动复制 target\sts-bot-mod-1.0.0.jar 到游戏的 mods 文件夹
) else (
    if not exist "%GAME_DIR%\mods" mkdir "%GAME_DIR%\mods"
    copy /Y "target\sts-bot-mod-1.0.0.jar" "%GAME_DIR%\mods\"
    copy /Y "libs\BaseMod.jar" "%GAME_DIR%\mods\"
    echo ✓ 已复制到游戏目录
    echo.
    echo 游戏目录：%GAME_DIR%
)

echo.
echo ========================================
echo ✓ 编译完成！
echo ========================================
echo.
echo 下一步：
echo 1. 使用 ModTheSpire 启动游戏
echo 2. 勾选 BaseMod 和 sts-bot-mod
echo 3. 在游戏中按 ` 打开控制台
echo 4. 输入：bot.MyBotMod.setAutoPlayEnabled(true)
echo 5. 享受自动打牌！
echo.

pause
