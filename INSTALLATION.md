# STS Bot Mod - 完整安装包

## 📦 文件清单

本压缩包包含以下文件：

```
sts-bot-mod-complete.zip
├── sts-bot-mod-1.0.0.jar          (编译好的Mod主文件)
├── libs/
│   ├── BaseMod.jar                 (BaseMod依赖 - 已下载)
│   ├── ModTheSpire.jar             (Mod加载器 - 需要下载)
│   └── README_LIBS.txt             (依赖说明)
├── README.md                       (项目文档)
├── SETUP_GUIDE.md                  (5分钟快速开始)
├── INSTALLATION.md                 (本文件 - 安装说明)
└── USAGE.md                        (使用说明)
```

## 🚀 安装步骤

### 第1步：下载缺失的依赖

由于GitHub下载限制，`ModTheSpire.jar` 需要你手动下载：

1. 访问：https://github.com/kiooeht/ModTheSpire/releases
2. 下载最新的 `ModTheSpire.jar`
3. 放到 `libs/` 文件夹中

### 第2步：复制游戏文件

从你的Slay the Spire游戏安装目录复制 `desktop-1.0.jar` 到 `libs/` 文件夹：

**Windows:**
```
C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar
```

**macOS:**
```
~/Library/Application Support/Steam/steamapps/common/SlayTheSpire/desktop-1.0.jar
```

**Linux:**
```
~/.steam/steam/steamapps/common/SlayTheSpire/desktop-1.0.jar
```

### 第3步：准备游戏的Mod加载器

1. 确保你已经在游戏目录安装了ModTheSpire
2. 如果没有安装：
   - 访问：https://github.com/kiooeht/ModTheSpire/releases
   - 下载最新的ModTheSpire
   - 按照ModTheSpire的README安装

### 第4步：安装Bot Mod

1. 打开Slay the Spire游戏目录
2. 进入 `mods/` 文件夹（如果没有则创建）
3. 复制 `sts-bot-mod-1.0.0.jar` 到 `mods/` 文件夹
4. 确保BaseMod也在 `mods/` 文件夹中

### 第5步：运行游戏

使用ModTheSpire启动游戏：

**Windows:**
- 双击运行游戏目录中的 `MTS.cmd`
- 或者在命令提示符中运行

**macOS/Linux:**
```bash
cd /path/to/SlayTheSpire
./MTS.sh
```

### 第6步：选择Mod

在ModTheSpire启动器中：
1. 勾选 "BaseMod"
2. 勾选 "sts-bot-mod"
3. 点击 "Play" 启动游戏

## ⚙️ 启用自动打牌

默认情况下，自动打牌功能是**关闭**的。

### 方法1：通过代码启用（推荐）

如果你想修改代码并重新编译：
1. 编辑源码中的 `src/main/java/bot/MyBotMod.java`
2. 找到第38行
3. 修改 `private static boolean autoPlayEnabled = false;` 为 `true`
4. 重新编译项目

### 方法2：通过控制台启用（无需重新编译）

1. 在游戏中按反引号键 `` ` `` 打开控制台
2. 输入命令：
```java
bot.MyBotMod.setAutoPlayEnabled(true)
```
3. 按回车，自动打牌功能就会启用

## 🎮 如何使用

1. **开始新游戏** - 选择任意角色（推荐Ironclad）
2. **进入战斗** - 开始任何一场战斗
3. **观察AI** - AI会自动选择并打出最优卡牌
4. **查看日志** - 控制台会显示AI的决策过程

## 📊 调试模式

调试模式默认开启，会显示：
- 每张打出的卡牌
- 怪物状态变化
- AI决策过程
- 游戏状态更新

要关闭调试模式，在代码中将 `debugMode` 设置为 `false`。

## 🐛 常见问题

### Q: Mod没有加载
**A:** 检查 `mods/` 文件夹中是否正确放置了所有JAR文件

### Q: 自动打牌不工作
**A:** 确保通过控制台或代码启用了 `autoPlayEnabled`

### Q: 游戏崩溃
**A:** 检查控制台错误信息，可能是缺少依赖文件

### Q: AI做出糟糕的决策
**A:** 当前使用的是规则AI，可以通过修改 `BotConfig.java` 调整参数

## 📞 获取帮助

- GitHub仓库：https://github.com/Eisem/slaythespire
- 提交问题：https://github.com/Eisem/slaythespire/issues

## 🎯 下一步

- 查看项目代码了解AI如何工作
- 修改 `BotConfig.java` 调整AI策略
- 尝试创建你自己的AI Agent

祝游戏愉快！🎮
