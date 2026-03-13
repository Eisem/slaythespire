# 🚀 如何编译和安装（推荐使用IDE）

由于Maven下载依赖太慢，推荐使用IntelliJ IDEA Community（免费）编译。

## 方法1：使用IntelliJ IDEA（强烈推荐，最快）

### 步骤：

1. **下载并安装 IntelliJ IDEA Community**
   - 访问：https://www.jetbrains.com/idea/download/
   - 下载 Community 版（免费）
   - 安装并启动

2. **打开项目**
   - File → Open
   - 选择你的项目文件夹：`D:/SlayTheSpire/STS/slaythespire-main`
   - 点击 OK

3. **配置JDK**
   - File → Project Structure → Project
   - SDK：选择 Java 8 (1.8)
   - Language level：8
   - 点击 OK

4. **配置依赖（重要！）**
   - File → Project Structure → Libraries
   - 点击 `+` → Java
   - 选择 `libs/` 文件夹
   - 确保以下文件都在libs文件夹中：
     - ✅ `BaseMod.jar`
     - ❌ `ModTheSpire.jar` (需要下载)
     - ❌ `desktop-1.0.jar` (需要从游戏目录复制)
   - 点击 OK

5. **编译项目**
   - Build → Build Project (Ctrl+F9)
   - 等待编译完成（应该只需要几秒钟）

6. **获取编译好的JAR**
   - 查看 `out/production/sts-bot-mod/` 文件夹
   - 或使用 Build → Build Artifacts

7. **安装到游戏**
   - 复制编译好的JAR到游戏的 `mods/` 文件夹

## 方法2：使用命令行（Maven）

如果你有Maven并且能下载依赖：

```bash
mvn clean package
```

JAR文件会在 `target/sts-bot-mod-1.0.0.jar`

## 📋 准备工作

在编译之前，确保 `libs/` 文件夹中有以下3个文件：

| 文件 | 来源 | 状态 |
|------|------|------|
| `BaseMod.jar` | GitHub | ✅ 已在仓库中 |
| `ModTheSpire.jar` | https://github.com/kiooeht/ModTheSpire/releases | ❌ 需要下载 |
| `desktop-1.0.jar` | 游戏目录 | ❌ 需要复制 |

### 下载 ModTheSpire.jar

访问：https://github.com/kiooeht/ModTheSpire/releases
下载最新的 `ModTheSpire.jar` 放到 `libs/` 文件夹

### 复制 desktop-1.0.jar

从你的游戏目录复制：
```
C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar
```

## 🎮 安装到游戏

编译成功后：

```
SlayTheSpire/
└── mods/
    ├── BaseMod.jar             (从 libs/BaseMod.jar 复制)
    └── sts-bot-mod-1.0.0.jar   (编译生成的文件)
```

## ⚙️ 启用自动打牌

在游戏中按 `` ` `` 打开控制台，输入：

```java
bot.MyBotMod.setAutoPlayEnabled(true)
```

## ❓ 遇到问题？

### 问题：IntelliJ IDEA无法找到JDK
- 解决：下载并安装 JDK 8：https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html

### 问题：依赖文件找不到
- 解决：确保libs文件夹中有3个JAR文件（见上表）

### 问题：编译仍然失败
- 解决：使用IntelliJ IDEA，它会自动处理依赖
- 或者在IDEA中查看具体的错误信息

## 🆘 获取帮助

- GitHub仓库：https://github.com/Eisem/slaythespire
- 提交问题：https://github.com/Eisem/slaythespire/issues

---

**推荐使用IntelliJ IDEA方法，这是最快最简单的方式！** 🚀
