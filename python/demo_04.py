import sys
from datetime import datetime
import pygame

from seven_seg_pg import Seven_seg
from lcd_font_pg import LCD_font

from lcd_font_mc import DotmatrixDisplay as LCD_font_mc
from mcje.minecraft import Minecraft
import param_MCJE as param
from param_MCJE import PLAYER_ORIGIN as po
from param_MCJE import blocks

mc = Minecraft.create(address=param.ADRS_MCR, port=param.PORT_MCR)
result = mc.setPlayer(param.PLAYER_NAME, po.x, po.y, po.z)
if "Error" in result:
    sys.exit(result)
else:
    print(result)

mc.postToChat("hello Minecraft!  Date and Time display")

DATE_BLOCK_ON = blocks.GLOWSTONE
DATE_BLOCK_OFF = blocks.AIR
TIME_BLOCK_ON = blocks.SEA_LANTERN
TIME_BLOCK_OFF = blocks.AIR

DARK_GRAY = (40, 40, 40)
GRAY = (80, 80, 80)
RED = (255, 0, 0)
GREEN = (10, 250, 10)
CYAN = (120, 120, 250)
YELLOW = (250, 250, 20)
WHITE = (250, 250, 250)

pygame.init()
clock = pygame.time.Clock()
screen = pygame.display.set_mode([480, 320])
pygame.display.set_caption("pygame 7-segment & LCD display simulation")
screen.fill(DARK_GRAY)

display5 = Seven_seg(screen)
display5.init_col(BLOCK_SIZE=9, BLOCK_INTV=9, COLOR_ON=(120, 200, 250), COLOR_OFF=GRAY)
display5.init_row(X_ORG=8, Y_ORG=8, COL_INTV=6)

display6 = LCD_font(screen)
display6.init_col(BLOCK_SIZE=4, BLOCK_INTV=6, COLOR_ON=WHITE, COLOR_OFF=GRAY)
display6.init_row(X_ORG=10, Y_ORG=21, COL_INTV=6)

display7 = LCD_font(screen)
display7.init_col(BLOCK_SIZE=6, BLOCK_INTV=8, COLOR_ON=RED, COLOR_OFF=GRAY)
display7.init_row(X_ORG=6, Y_ORG=24, COL_INTV=6)

display8 = LCD_font_mc(mc, config_file="config.yml", config_section="date")
display8.init_col(color_on=DATE_BLOCK_ON, color_off=DATE_BLOCK_OFF)

display9 = LCD_font_mc(mc, config_file="config.yml", config_section="time")
display9.init_col(color_on=TIME_BLOCK_ON, color_off=TIME_BLOCK_OFF)

running = True
while running:
    for count in range(16**4):
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
        if not running:
            break

        dt_now = datetime.now()
        time_now = dt_now.hour * 10000 + dt_now.minute * 100 + dt_now.second
        display5.disp_num2(zfil=True, rjust=6, num=time_now, base=10)

        display6.update_col(col=0, code=int(str(dt_now.year)[0]))
        display6.update_col(col=1, code=int(str(dt_now.year)[1]))
        display6.update_col(col=2, code=int(str(dt_now.year)[2]))
        display6.update_col(col=3, code=int(str(dt_now.year)[3]))
        display6.update_col(col=4, code=11)
        display6.update_col(col=5, code=dt_now.month // 10)
        display6.update_col(col=6, code=dt_now.month % 10)
        display6.update_col(col=7, code=11)
        display6.update_col(col=8, code=dt_now.day // 10)
        display6.update_col(col=9, code=dt_now.day % 10)

        display7.update_col(col=0, code=dt_now.hour // 10)
        display7.update_col(col=1, code=dt_now.hour % 10)
        display7.update_col(col=2, code=10)
        display7.update_col(col=3, code=dt_now.minute // 10)
        display7.update_col(col=4, code=dt_now.minute % 10)
        display7.update_col(col=5, code=10)
        display7.update_col(col=6, code=dt_now.second // 10)
        display7.update_col(col=7, code=dt_now.second % 10)

        pygame.display.flip()

        # 日付表示
        display8.update_col(col=0, code=ord(str(dt_now.year)[0]))
        display8.update_col(col=1, code=ord(str(dt_now.year)[1]))
        display8.update_col(col=2, code=ord(str(dt_now.year)[2]))
        display8.update_col(col=3, code=ord(str(dt_now.year)[3]))
        display8.update_col(col=4, code=ord("-"))
        display8.update_col(col=5, code=ord(str(dt_now.month // 10)))
        display8.update_col(col=6, code=ord(str(dt_now.month % 10)))
        display8.update_col(col=7, code=ord("-"))
        display8.update_col(col=8, code=ord(str(dt_now.day // 10)))
        display8.update_col(col=9, code=ord(str(dt_now.day % 10)))

        # 時刻表示
        display9.update_col(col=0, code=ord(str(dt_now.hour // 10)))
        display9.update_col(col=1, code=ord(str(dt_now.hour % 10)))
        display9.update_col(col=2, code=ord(":"))
        display9.update_col(col=3, code=ord(str(dt_now.minute // 10)))
        display9.update_col(col=4, code=ord(str(dt_now.minute % 10)))
        display9.update_col(col=5, code=ord(":"))
        display9.update_col(col=6, code=ord(str(dt_now.second // 10)))
        display9.update_col(col=7, code=ord(str(dt_now.second % 10)))

        clock.tick(5)
pygame.quit()
