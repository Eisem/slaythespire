# STS Bot Mod - 杀戮尖塔自动打牌AI

基于规则的AI自动打牌Mod，无需联网，本地运行。

## 🚀 快速开始

### 步骤1：安装Java和Maven

**Java 8：** https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html

**Maven：** https://maven.apache.org/download.cgi
- 下载 `apache-maven-3.9.6-bin.zip`
- 解压到任意目录
- 添加环境变量 `MAVEN_HOME` 和 `PATH`

### 步骤2：准备依赖文件

在 `libs/` 文件夹中放入以下3个JAR文件：

| 文件 | 来源 | 说明 |
|------|------|------|
| `ModTheSpire.jar` | Steam创意工坊 或 https://github.com/kiooeht/ModTheSpire/releases | Mod加载器 |
| `BaseMod.jar` | https://github.com/daviscook477/BaseMod/releases | BaseMod API |
| `desktop-1.0.jar` | Steam游戏目录 | 游戏核心文件 |

**获取 desktop-1.0.jar：**
```
C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar
```

### 步骤3：编译

```bash
mvn clean package
```

编译成功后，JAR文件在：`target/sts-bot-mod-1.0.0.jar`

### 步骤4：安装到游戏

复制以下文件到游戏的 `mods/` 文件夹：
```
C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\
├── BaseMod.jar              (从 libs/ 复制)
└── sts-bot-mod-1.0.0.jar    (从 target/ 复制)
```

### 步骤5：运行游戏

1. 使用 ModTheSpire 启动游戏（运行 `MTS.cmd`）
2. 勾选 **BaseMod** 和 **sts-bot-mod**
3. 点击 **Play** 启动游戏

### 步骤6：启用自动打牌

1. 开始新游戏（推荐Ironclad）
2. 进入战斗
3. 按 `` ` `` （反引号键）打开控制台
4. 输入：
   ```java
   bot.MyBotMod.setAutoPlayEnabled(true)
   ```
5. 按回车，AI开始自动打牌！

---

## 📊 AI工作原理

**完全本地运行**，基于规则的评分系统：

```
游戏每帧更新 → AI分析手牌 → 计算每张牌分数 → 选择最优 → 执行动作
```

评分因素：
- 卡牌类型（攻击/技能/能力）
- 卡牌稀有度
- 能量效率
- 当前血量状态

---

## 🛠️ 项目结构

```
src/main/java/bot/
├── MyBotMod.java           # Mod入口
├── GameState.java          # 游戏状态提取
├── GameInterface.java      # 游戏操作接口
├── AIEngine.java           # AI决策引擎
├── AIAgent.java            # AI接口
├── AIAction.java           # 动作定义
├── BotConfig.java          # 配置参数
├── patches/                # 游戏Hook
│   ├── GameStatePatch.java
│   ├── HandCardPatch.java
│   ├── MonsterPatch.java
│   └── AutoPlayPatch.java
└── ai/
    └── RuleBasedAgent.java # 规则AI实现
```

---

## ⚙️ 自定义AI策略

编辑 `src/main/java/bot/BotConfig.java` 调整参数：

```java
// 防御优先级阈值（血量低于30%时优先防御）
public static int DEFENSE_PRIORITY_HP_THRESHOLD = 30;

// 卡牌类型权重
public static double WEIGHT_ATTACK = 10.0;
public static double WEIGHT_SKILL = 15.0;
public static double WEIGHT_POWER = 20.0;

// 目标选择策略
public static String TARGET_STRATEGY = "lowest_hp";  // 优先攻击低血量怪物
```

修改后重新编译：
```bash
mvn clean package
```

---

## 🧠 扩展：接入机器学习

当前架构支持多种AI Agent：

```java
public interface AIAgent {
    AIAction decideAction();  // 决策接口
    void reset();             // 重置状态
}
```

可以实现：
- **RLAgent** - 强化学习（PPO/DQN）
- **MCTSAgent** - 蒙特卡洛树搜索
- **NeuralNetworkAgent** - 神经网络

通过Java-Python通信使用你的GPU训练模型。

---

## 📞 问题排查

### 编译失败：找不到符号
- 检查 `libs/` 文件夹中3个JAR文件是否齐全
- 检查Java版本（需要Java 8）

### Mod没有加载
- 确保使用ModTheSpire启动游戏
- 检查 `mods/` 文件夹中是否有JAR文件

### 自动打牌不工作
- 确认在控制台输入了启用命令
- 查看控制台是否有错误信息

---

## 🔗 相关链接

- **GitHub：** https://github.com/Eisem/slaythespire
- **ModTheSpire：** https://github.com/kiooeht/ModTheSpire
- **BaseMod：** https://github.com/daviscook477/BaseMod

---

**祝你玩得开心！** 🎮
