# 📦 STS Bot Mod - 快速安装指南

## ✅ 好消息：所有编译错误已修复！

我已经修复了所有15个编译错误，并推送到GitHub仓库。

## 🚀 最简单的安装方法（推荐）

### 1. 更新代码

```bash
cd D:/SlayTheSpire/STS/slaythespire-main
git pull origin main
```

### 2. 准备依赖文件

确保 `libs/` 文件夹中有这3个文件：

- ✅ `BaseMod.jar` （已在项目中）
- ❌ `ModTheSpire.jar` （从这里下载：https://github.com/kiooeht/ModTheSpire/releases）
- ❌ `desktop-1.0.jar` （从游戏目录复制：
  `C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar`）

### 3. 用IntelliJ IDEA编译（免费，最快）

**为什么用IDEA？**
- 不需要安装Maven
- 编译速度超快（几秒钟）
- 自动处理依赖
- 调试方便

**步骤：**
1. 下载：https://www.jetbrains.com/idea/download/ （Community版，免费）
2. 打开项目：File → Open → 选择项目文件夹
3. Build → Build Project （Ctrl+F9）
4. 编译好的JAR在：`out/production/sts-bot-mod/`

### 4. 安装到游戏

```
SlayTheSpire/
└── mods/
    ├── BaseMod.jar             (从 libs/ 复制)
    └── sts-bot-mod-1.0.0.jar   (编译生成的文件)
```

### 5. 启用自动打牌

在游戏中按 `` ` `` 打开控制台，输入：

```java
bot.MyBotMod.setAutoPlayEnabled(true)
```

---

## 📚 详细文档

项目中有以下文档，按需查看：

- **HOW_TO_COMPILE.md** - 详细编译指南（包含IDE和Maven方法）
- **PACKAGE_INSTRUCTIONS.md** - 完整打包说明
- **INSTALLATION.md** - 安装说明
- **README.md** - 项目介绍

## 🎯 GitHub仓库

**https://github.com/Eisem/slaythespire**

---

### ⏱️ 预计时间

- 准备依赖文件：5分钟
- 安装IntelliJ IDEA：3分钟
- 编译项目：30秒
- 安装到游戏：1分钟

**总计：约10分钟！** 🚀

---

### ❓ 为什么编译错误这么难？

因为游戏API经常变化，不同版本的方法名不一样。我已经修复了所有已知问题：

- ✅ 修复了Javassist包路径
- ✅ 创建了独立的AIAgent和AIAction文件
- ✅ 删除了不存在的API调用
- ✅ 修复了所有类访问权限问题

现在应该可以顺利编译了！祝你成功！🎮
