# 🚀 超级简单安装指南（3步搞定）

## ✅ 最简单的方法：一键编译

由于你上传了 `desktop-1.0.jar`，现在只需要3步！

---

## 步骤1：准备文件（2分钟）

1. 拉取最新代码：
   ```bash
   git pull origin main
   ```

2. 把你上传的 `desktop-1.0.jar` 放到 `libs/` 文件夹

3. 检查 `libs/` 文件夹应该有：
   - ✅ `BaseMod.jar` （已有）
   - ✅ `desktop-1.0.jar` （你上传的）
   - ❌ `ModTheSpire.jar` （不需要了！）

---

## 步骤2：一键编译（1分钟）

直接双击运行：
```
compile.bat
```

**就这么简单！** 🎉

脚本会自动：
- ✓ 检查依赖
- ✓ 编译项目
- ✓ 复制JAR到游戏目录

---

## 步骤3：启用自动打牌（1分钟）

在游戏中按 `` ` `` 打开控制台，输入：

```java
bot.MyBotMod.setAutoPlayEnabled(true)
```

---

## ❓ 如果编译失败？

### 问题1：未找到Maven
**解决：**
- 下载 Maven：https://maven.apache.org/download.cgi
- 解压到：`C:\Program Files\Apache\maven`
- 添加环境变量：
  - `MAVEN_HOME` = `C:\Program Files\Apache\maven`
  - `PATH` 添加 `%MAVEN_HOME%\bin`

### 问题2：未找到Java
**解决：**
- 下载 JDK 8：https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html
- 安装后重新运行 `compile.bat`

### 问题3：仍然编译失败
**替代方案：使用 IntelliJ IDEA**
1. 下载 IDEA：https://www.jetbrains.com/idea/download/
2. 打开项目
3. Build → Build Project

---

## 🎯 总时间

```
准备文件：2分钟
编译：1分钟
启用：1分钟
━━━━━━━━━━━━━━━━━
总计：4分钟！
```

---

## 🔗 GitHub仓库

**https://github.com/Eisem/slaythespire**

---

**现在试试双击 `compile.bat` 吧！应该能成功了！** 🚀
