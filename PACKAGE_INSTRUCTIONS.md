# 📦 完整打包说明

## ✅ 已完成的工作

我已经帮你完成了以下工作：

1. ✅ 创建了完整的Java项目代码
2. ✅ 写好了所有AI逻辑（规则引擎）
3. ✅ 下载了 BaseMod.jar (1.3 MB)
4. ✅ 编写了详细的安装文档
5. ✅ 推送到了GitHub仓库

## 📋 你需要做的事情

### 步骤1：克隆项目（已有代码）

```bash
git clone https://github.com/Eisem/slaythespire.git
cd slaythespire
```

### 步骤2：准备依赖文件

创建 `libs/` 文件夹并放入以下3个文件：

| 文件名 | 大小 | 来源 | 状态 |
|--------|------|------|------|
| `BaseMod.jar` | ~1.3 MB | GitHub | ✅ 已在仓库中 |
| `ModTheSpire.jar` | ~100 KB | GitHub | ❌ 需要下载 |
| `desktop-1.0.jar` | ~100 MB | 游戏目录 | ❌ 需要复制 |

#### 下载 ModTheSpire.jar

访问：https://github.com/kiooeht/ModTheSpire/releases
下载最新的 `ModTheSpire.jar` 放到 `libs/` 文件夹

#### 复制 desktop-1.0.jar

从你的游戏目录复制 `desktop-1.0.jar` 到 `libs/` 文件夹：

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

### 步骤3：编译项目

**选项A：使用Maven（推荐）**

```bash
mvn clean package
```

编译成功后，文件会在 `target/sts-bot-mod-1.0.0.jar`

**选项B：使用IntelliJ IDEA**

1. 下载 IntelliJ IDEA Community：https://www.jetbrains.com/idea/download/
2. 打开项目：File → Open → 选择项目文件夹
3. Build → Build Project
4. Build → Build Artifacts

### 步骤4：安装到游戏

```
SlayTheSpire/
├── desktop-1.0.jar              (游戏文件 - 已有)
├── ModTheSpire.jar              (Mod加载器 - 需要安装)
├── MTS.cmd / MTS.sh              (启动脚本 - 需要安装)
└── mods/                        (Mod文件夹)
    ├── BaseMod.jar             (从 libs/BaseMod.jar 复制)
    └── sts-bot-mod-1.0.0.jar   (编译后的文件)
```

### 步骤5：运行游戏

1. 运行 ModTheSpire 启动器（`MTS.cmd` 或 `MTS.sh`）
2. 勾选 BaseMod 和 sts-bot-mod
3. 点击 Play 启动游戏

### 步骤6：启用自动打牌

在游戏中按 `` ` `` 打开控制台，输入：

```java
bot.MyBotMod.setAutoPlayEnabled(true)
```

按回车，自动打牌就会启用！

## 🎉 完成！

现在你可以：
- 开始新游戏
- 观察AI自动打牌
- 查看控制台日志了解AI决策

## 📚 文档位置

项目中有以下文档：

- `README.md` - 项目介绍和架构说明
- `INSTALLATION.md` - 详细安装步骤
- `QUICK_START.md` - 快速开始指南（含IDE方法）
- `SETUP_GUIDE.md` - 5分钟快速开始
- `libs/README.txt` - 依赖文件说明

## 🔗 GitHub仓库

https://github.com/Eisem/slaythespire

## 💡 提示

- 如果Maven有问题，使用IntelliJ IDEA编译
- 自动打牌默认关闭，需要在控制台启用
- 调试模式会显示详细日志
- 可以修改 `BotConfig.java` 调整AI策略

祝你玩得开心！🎮
