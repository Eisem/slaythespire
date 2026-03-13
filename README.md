# STS Bot Mod - 自动打牌AI

一个用于 Slay the Spire 的 AI Bot，可以自动打牌、选择卡牌、管理资源。

## 🚀 快速开始（3步搞定）

### 步骤1：准备文件（2分钟）

1. 拉取最新代码：
   ```bash
   git pull origin main
   ```

2. 把 `desktop-1.0.jar` 放到 `libs/` 文件夹
   - 从游戏目录复制：`C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar`

3. 检查 `libs/` 文件夹应该有：
   - ✅ `BaseMod.jar` （已有）
   - ✅ `desktop-1.0.jar` （你放置的）

### 步骤2：一键编译（1分钟）

**方法A：使用批处理脚本（推荐）**

双击运行 `compile.bat`

如果没有任何反应，先运行 `diagnose.bat` 诊断环境问题

**方法B：使用 IntelliJ IDEA（更简单，推荐）**

1. 下载 IDEA：https://www.jetbrains.com/idea/download/（Community版，免费）
2. File → Open → 选择项目文件夹
3. File → Project Structure → Libraries → 点击 `+` → 选择 `libs/` 文件夹
4. Build → Build Project (Ctrl+F9)
5. 编译好的JAR在：`out/production/sts-bot-mod/`

### 步骤3：启用自动打牌（1分钟）

在游戏中按 `` ` `` 打开控制台，输入：
```java
bot.MyBotMod.setAutoPlayEnabled(true)
```

---

## ❓ 如果编译没有反应或失败

### 问题：点击compile.bat没有任何反应

**解决：**
1. 先运行 `diagnose.bat` 查看环境配置
2. 如果提示"未找到Maven"，请安装Maven或使用IntelliJ IDEA

### 问题：未找到Maven
**解决：**
- 下载 Maven：https://maven.apache.org/download.cgi
- 解压到：`C:\Program Files\Apache\maven`
- 添加环境变量：
  - `MAVEN_HOME` = `C:\Program Files\Apache\maven`
  - `PATH` 添加 `%MAVEN_HOME%\bin`

### 问题：未找到Java
**解决：**
- 下载 JDK 8：https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html
- 安装后重新运行 `compile.bat`

### 问题：使用IntelliJ IDEA编译（替代方案）
1. 下载 IDEA：https://www.jetbrains.com/idea/download/（Community版，免费）
2. File → Open → 选择项目文件夹
3. File → Project Structure → Libraries → 点击 `+` → 选择 `libs/` 文件夹
4. Build → Build Project (Ctrl+F9)
5. 编译好的JAR在：`out/production/sts-bot-mod/`

---

## 📁 项目结构

```
sts-bot-mod/
├── src/main/java/bot/
│   ├── MyBotMod.java          # Mod入口
│   ├── GameState.java         # 游戏状态
│   ├── GameInterface.java     # 游戏接口
│   ├── AIEngine.java          # AI引擎
│   ├── AIAgent.java           # AI接口
│   ├── AIAction.java          # 动作定义
│   ├── BotConfig.java         # 配置
│   ├── patches/               # 游戏Hook
│   │   ├── GameStatePatch.java
│   │   ├── HandCardPatch.java
│   │   ├── MonsterPatch.java
│   │   └── AutoPlayPatch.java
│   └── ai/
│       └── RuleBasedAgent.java # 规则AI
├── libs/
│   ├── BaseMod.jar           # BaseMod依赖
│   └── desktop-1.0.jar       # 游戏依赖
├── compile.bat               # 一键编译脚本
└── README.md                 # 本文件
```

---

## ⚙️ 功能特性

- ✅ 自动打牌（基于规则的AI）
- ✅ 实时游戏状态提取
- ✅ 调试模式显示详细日志
- ✅ 可配置的AI参数

---

## 🎮 使用方法

1. 使用 ModTheSpire 启动游戏
2. 勾选 BaseMod 和 sts-bot-mod
3. 开始新游戏
4. 在战斗中观察AI自动打牌

---

## 📊 调试模式

调试模式默认开启，控制台会显示：
- 每张打出的卡牌
- 怪物状态变化
- AI决策过程
- 游戏状态更新

---

## 🔧 配置

编辑 `BotConfig.java` 可以调整：
- AI权重参数
- 目标选择策略
- 安全设置
- 日志级别

---

## 📞 获取帮助

- GitHub仓库：https://github.com/Eisem/slaythespire
- 提交问题：https://github.com/Eisem/slaythespire/issues

---

## ⏱️ 总时间

```
准备文件：2分钟
编译：1分钟
启用：1分钟
━━━━━━━━━━━━━━━━━
总计：4分钟
```
