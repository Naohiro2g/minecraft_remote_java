# Parameters for Minecraft Java Edition

from mcje.vec3 import Vec3
import mcje.blocks as blocks  # copy blocks_1_21_4.py as blocks.py

# PLAYER_NAME = "PLAYER_NAME"  # set your player name in Minecraft
PLAYER_NAME = "nao2g"  # set your player name in Minecraft
PLAYER_ORIGIN = Vec3(1000, 0, 1000)  # po.x, po.y, po.z
print(f"param_MCJE loaded for {PLAYER_NAME} at {PLAYER_ORIGIN.x}, {PLAYER_ORIGIN.y}, {PLAYER_ORIGIN.z}")

# minecraft remote connection to the host at address:port
ADRS_MCR = "localhost"  # or server address
# ADRS_MCR = "c2cc.mydns.jp"
PORT_MCR = 25575

# vertical levels in Minecraft 1.20+
Y_TOP = 320  # the top where blocks can be set
Y_CLOUD_BOTTOM = 199  # the bottom of clouds
Y_SEA = 62  # the sea level
Y_BOTTOM = 0  # the bottom where blocks can be set
Y_BOTTOM_STEVE = -64  # the bottom where Steve can go down

# For the block IDs, use the script mcje/blocks_1_21_4.py to generate the list.
# Usage:
#     python mcje/get_block_id.py 1.21.4  # run the script in the terminal
#     You will get mcje/blocks_1_21_4.py containing all the block IDs for Minecraft 1.21.4.
#     You have to play Minecraft 1.21.4 at least once beforehand.
#     then copy blocks_1_21_4.py as blocks.py to use.
#
#     setBlock(x, y, z, blocks.GOLD_BLOCK)  # if you want to use the block ID in other files

blocks.GOLD_BLOCK  # if you want to use the block ID in this file

