@echo off
chcp 65001 >nul
cls
echo ========================================
echo STS Bot Mod - 一键编译脚本
echo ========================================
echo.

REM 显示调试信息
echo [调试] 当前目录: %CD%
echo [调试] 脚本路径: %~dp0
echo.

REM 切换到脚本所在目录
cd /d "%~dp0"

echo [步骤1] 检查Maven是否安装...
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
    echo 或者使用 IntelliJ IDEA 编译（更简单）：
    echo 1. 下载：https://www.jetbrains.com/idea/download/
    echo 2. 打开项目后按 Ctrl+F9 编译
    echo.
    pause
    exit /b 1
)
echo [OK] Maven 已安装
echo.

echo [步骤2] 检查依赖文件...
echo [调试] 检查 libs 文件夹...
if not exist "libs" (
    echo [错误] libs 文件夹不存在！
    pause
    exit /b 1
)

if not exist "libs\BaseMod.jar" (
    echo [错误] 缺少 libs\BaseMod.jar
    pause
    exit /b 1
)
echo [OK] BaseMod.jar 存在

if not exist "libs\desktop-1.0.jar" (
    echo [错误] 缺少 libs/desktop-1.0.jar
    echo.
    echo 请从游戏目录复制：
    echo C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar
    echo.
    pause
    exit /b 1
)
echo [OK] desktop-1.0.jar 存在
echo.

echo [步骤3] 清理旧的编译文件...
call mvn clean 2>nul
echo [OK] 清理完成
echo.

echo [步骤4] 开始编译...
echo 这可能需要几分钟，请耐心等待...
echo.
call mvn package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [错误] 编译失败！
    echo.
    echo 可能的原因：
    echo 1. 没有安装 Java 8
    echo 2. Maven 配置错误
    echo 3. 网络问题（无法下载依赖）
    echo.
    echo 请尝试使用 IntelliJ IDEA 编译（更简单）：
    echo 1. 下载：https://www.jetbrains.com/idea/download/ (Community版，免费)
    echo 2. File -^> Open -^> 选择项目文件夹
    echo 3. File -^> Project Structure -^> Libraries -^> + -^> 选择 libs 文件夹
    echo 4. Build -^> Build Project (Ctrl+F9)
    echo 5. 编译好的JAR在: out\production\sts-bot-mod\
    echo.
    pause
    exit /b 1
)
echo [OK] 编译成功
echo.

echo [步骤5] 检查编译结果...
if not exist "target\sts-bot-mod-1.0.0.jar" (
    echo [错误] 编译成功但找不到 JAR 文件！
    echo.
    echo 请查看 target 文件夹中的文件列表：
    dir /b target
    echo.
    pause
    exit /b 1
)
echo [OK] JAR 文件已生成
echo.

echo [步骤6] 复制到游戏目录...
set GAME_DIR=C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire
if not exist "%GAME_DIR%" (
    echo [警告] 未找到游戏目录：%GAME_DIR%
    echo.
    echo 请手动复制文件：
    echo 源文件: target\sts-bot-mod-1.0.0.jar
    echo 目标文件夹: 游戏目录\mods\
    echo.
) else (
    if not exist "%GAME_DIR%\mods" mkdir "%GAME_DIR%\mods"
    copy /Y "target\sts-bot-mod-1.0.0.jar" "%GAME_DIR%\mods\" >nul
    copy /Y "libs\BaseMod.jar" "%GAME_DIR%\mods\" >nul
    echo [OK] 已复制到游戏目录
    echo.
    echo 游戏目录：%GAME_DIR%
)

echo.
echo ========================================
echo ✓ 编译完成！
echo ========================================
echo.
echo 编译的JAR文件位置:
echo - target\sts-bot-mod-1.0.0.jar
echo - %GAME_DIR%\mods\sts-bot-mod-1.0.0.jar
echo.
echo 下一步：
echo 1. 使用 ModTheSpire 启动游戏
echo 2. 勾选 BaseMod 和 sts-bot-mod
echo 3. 在游戏中按 ` 打开控制台
echo 4. 输入：bot.MyBotMod.setAutoPlayEnabled(true)
echo 5. 享受自动打牌！
echo.

pause
