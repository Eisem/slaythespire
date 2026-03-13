# 🚀 快速开始指南

## ⚠️ 重要提示

由于GitHub下载限制和版权原因，以下文件需要你手动准备：

1. **ModTheSpire.jar** - 从 https://github.com/kiooeht/ModTheSpire/releases 下载
2. **desktop-1.0.jar** - 从你的Slay the Spire游戏目录复制

## 📦 方法1：使用预编译版本（推荐，无需编译）

如果你想直接使用而不想编译：

### 步骤：

1. **下载依赖**
   - 下载 `ModTheSpire.jar` 并放到 `libs/` 文件夹
   - 从游戏目录复制 `desktop-1.0.jar` 到 `libs/` 文件夹
   - `BaseMod.jar` 已经在 `libs/` 文件夹中了

2. **编译项目**
   ```bash
   mvn clean package
   ```

3. **使用编译好的JAR**
   - 编译成功后，`target/sts-bot-mod-1.0.0.jar` 就可以使用了
   - 复制这个JAR到你的Slay the Spire的 `mods/` 文件夹

4. **运行游戏**
   - 使用ModTheSpire启动游戏
   - 勾选 BaseMod 和 sts-bot-mod
   - 开始新游戏测试

## 🛠️ 方法2：使用IDE编译

如果Maven有问题：

### 使用IntelliJ IDEA Community（免费）

1. **下载并安装**
   - 访问：https://www.jetbrains.com/idea/download/
   - 下载Community版（免费）

2. **打开项目**
   - File → Open
   - 选择这个项目文件夹
   - 选择Open as Project

3. **配置SDK**
   - File → Project Structure → Project
   - SDK：选择Java 8
   - Language Level：8

4. **配置依赖**
   - File → Project Structure → Libraries
   - 点击 `+` → Java
   - 选择 `libs/` 文件夹中的所有JAR文件

5. **编译**
   - 点击 Build → Build Project
   - 或右键点击项目 → Maven → package

6. **找到编译好的JAR**
   - 查看 `out/artifacts/` 文件夹
   - 或 `target/` 文件夹

## 🎮 安装到游戏

### 游戏目录结构

```
SlayTheSpire/
├── desktop-1.0.jar              (游戏主文件)
├── ModTheSpire.jar              (Mod加载器)
├── MTS.cmd / MTS.sh              (启动脚本)
├── mods/                        (Mod文件夹)
│   ├── BaseMod.jar             (BaseMod - 必需)
│   └── sts-bot-mod-1.0.0.jar   (你的Bot Mod)
└── ...
```

### 安装步骤

1. **确保ModTheSpire已安装**
   - 复制 `ModTheSpire.jar` 到游戏目录
   - 复制 `MTS.cmd` (Windows) 或 `MTS.sh` (macOS/Linux) 到游戏目录

2. **安装BaseMod和Bot Mod**
   - 创建 `mods/` 文件夹（如果没有）
   - 复制 `BaseMod.jar` 到 `mods/`
   - 复制 `sts-bot-mod-1.0.0.jar` 到 `mods/`

3. **运行游戏**
   - 运行 `MTS.cmd` (Windows) 或 `MTS.sh` (macOS/Linux)
   - 在启动器中勾选 BaseMod 和 sts-bot-mod
   - 点击 Play

## ⚙️ 启用自动打牌

在游戏中按 `` ` `` 打开控制台，输入：

```java
bot.MyBotMod.setAutoPlayEnabled(true)
```

按回车，自动打牌就会启用！

## 📊 查看日志

控制台会显示：
```
STS Bot Mod initialized successfully!
AI Decision: PLAY_CARD - Attack (6 dmg) - 1 energy
Player HP: 45/80
Energy: 2
Hand: 5 cards
```

## 🆘 遇到问题？

### 问题：编译失败
- 检查 `libs/` 文件夹中是否有3个JAR文件
- 确认Java版本是1.8：`java -version`
- 尝试使用IDE编译

### 问题：Mod没有加载
- 检查 `mods/` 文件夹中的文件
- 确认使用了ModTheSpire启动游戏

### 问题：自动打牌不工作
- 通过控制台启用：`bot.MyBotMod.setAutoPlayEnabled(true)`
- 确保在战斗中

## 📞 获取帮助

- GitHub：https://github.com/Eisem/slaythespire
- 提交问题：https://github.com/Eisem/slaythespire/issues
