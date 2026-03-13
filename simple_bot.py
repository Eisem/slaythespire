"""
STS Simple Bot - 超简单版本
无需编译，直接运行！

需要安装：
pip install pyautogui opencv-python numpy pytesseract

使用方法：
1. 启动 Slay the Spire
2. 运行这个脚本
3. 在游戏中按 F1 启用自动打牌
4. 按 F2 禁用自动打牌
5. 按 ESC 退出脚本
"""

import pyautogui
import time
import keyboard
from PIL import ImageGrab
import cv2
import numpy as np

class STSSimpleBot:
    def __init__(self):
        self.running = False
        self.auto_play = False

        # 游戏区域坐标（需要根据你的屏幕调整）
        # 你可以用 pyautogui.position() 来找到正确的坐标
        self.game_region = (0, 0, 1920, 1080)

        print("=" * 50)
        print("STS Simple Bot 已启动")
        print("=" * 50)
        print("F1 - 启用自动打牌")
        print("F2 - 禁用自动打牌")
        print("ESC - 退出")
        print("=" * 50)

    def start(self):
        """启动bot"""
        self.running = True

        # 注册快捷键
        keyboard.on_press_key("f1", self.enable_auto_play)
        keyboard.on_press_key("f2", self.disable_auto_play)
        keyboard.on_press_key("esc", self.stop)

        print("Bot正在运行，等待指令...")

        while self.running:
            if self.auto_play:
                self.play_turn()
            time.sleep(0.1)

    def enable_auto_play(self, event):
        """启用自动打牌"""
        self.auto_play = True
        print("✓ 自动打牌已启用")

    def disable_auto_play(self, event):
        """禁用自动打牌"""
        self.auto_play = False
        print("✗ 自动打牌已禁用")

    def stop(self, event):
        """停止bot"""
        self.running = False
        print("Bot已停止")

    def play_turn(self):
        """自动打牌"""
        try:
            # 1. 检查是否在战斗中（简化版：假设在战斗中）
            # 2. 点击第一张卡牌
            # 卡牌位置需要根据实际情况调整
            card_positions = [
                (500, 800),
                (650, 800),
                (800, 800),
                (950, 800),
                (1100, 800)
            ]

            for pos in card_positions:
                try:
                    # 尝试点击卡牌
                    pyautogui.click(pos[0], pos[1], duration=0.1)
                    time.sleep(0.2)
                except:
                    pass

            # 3. 结束回合（简化版：每打3张牌就结束）
            time.sleep(1)
            pyautogui.click(960, 900, duration=0.1)  # 结束回合按钮位置
            time.sleep(1)

        except Exception as e:
            print(f"错误: {e}")

    def get_screenshot(self):
        """获取游戏截图"""
        screenshot = ImageGrab.grab(bbox=self.game_region)
        return cv2.cvtColor(np.array(screenshot), cv2.COLOR_RGB2BGR)

    def find_card(self, card_name):
        """查找特定卡牌（OCR功能）"""
        # TODO: 使用 OCR 识别卡牌
        pass


if __name__ == "__main__":
    print("正在启动 STS Simple Bot...")

    # 检查屏幕分辨率
    screen_width, screen_height = pyautogui.size()
    print(f"屏幕分辨率: {screen_width}x{screen_height}")

    bot = STSSimpleBot()
    bot.start()
