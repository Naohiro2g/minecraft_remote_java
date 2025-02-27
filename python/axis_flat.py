"""
Draw x, y, z axis in the Minecraft world
    x: stone
    y: grass/dirt
    z: gold
Flatten the world
    width: Size of flat world to produce.
           x, z: from -widh to width
           y: from AXIS_BOTTOM to AXIS_TOP

mc: an instance of Minecraft must be created beforehand
"""
import sys
from time import sleep

from mcje.minecraft import Minecraft
import param_MCJE as param
from param_MCJE import PLAYER_ORIGIN as po
from param_MCJE import blocks


# axis parameters
AXIS_WIDTH = 40  # x, z: -40 .. 0 .. 40
AXIS_TOP = 127
AXIS_Y_V_ORG = 96  # y of virtual origin
AXIS_BOTTOM = 63  # y: 63 .. 96 .. 127

AXIS_BLOCK_X = blocks.DIAMOND_BLOCK
AXIS_BLOCK_Y = blocks.SEA_LANTERN
AXIS_BLOCK_Z = blocks.GOLD_BLOCK
AXIS_BLOCK_TOP = blocks.GLOWSTONE


def draw_XYZ_axis(_mc, wait=0.1):
    """
    Draw xyz axis with some wait between placing each block.
    You must create mc or instance of Minecraft world beforehand.
    """

    # x-axis
    _mc.postToChat("x-axis from negative to positive region")
    x, y, z = -AXIS_WIDTH, AXIS_Y_V_ORG, 0
    while x <= AXIS_WIDTH:
        _mc.setBlock(x, y, z, AXIS_BLOCK_X)
        if x < 0:
            x += 2
            sleep(wait * 2)
        else:
            x += 1
            sleep(wait)
    # y-axis
    _mc.postToChat("y-axis from negative to positive region")
    x, y, z = 0, AXIS_BOTTOM, 0
    while y <= AXIS_TOP - 5:
        _mc.setBlock(x, y, z, AXIS_BLOCK_Y)
        if y < AXIS_Y_V_ORG:
            y += 2
            sleep(wait * 2)
        else:
            y += 1
            sleep(wait)
    while y <= AXIS_TOP:
        _mc.setBlock(x, y, z, AXIS_BLOCK_TOP)
        y += 1
        sleep(wait)
    # z-axis
    _mc.postToChat("z-axis from negative to positive region")
    x, y, z = 0, AXIS_Y_V_ORG, -AXIS_WIDTH
    while z <= AXIS_WIDTH:
        _mc.setBlock(x, y, z, AXIS_BLOCK_Z)
        if z < 0:
            z += 2
            sleep(wait * 2)
        else:
            z += 1
            sleep(wait)


def clear_XYZ_axis(_mc, wait=0.5):
    _mc.setBlocks(-AXIS_WIDTH, AXIS_Y_V_ORG, 0,   AXIS_WIDTH, AXIS_Y_V_ORG, 0,   blocks.AIR)  # x
    sleep(wait)
    _mc.setBlocks(0, 0, 0,   0, AXIS_TOP, 0,   blocks.AIR)  # y
    sleep(wait)
    _mc.setBlocks(0, AXIS_Y_V_ORG, -AXIS_WIDTH,   0, AXIS_Y_V_ORG, AXIS_WIDTH,   blocks.AIR)  # z
    sleep(wait)


def reset_minecraft_world(_mc, width=80):
    _mc.setBlocks(-width, param.Y_SEA + 1, -width,   width, AXIS_TOP,    width,    blocks.AIR)
    _mc.setBlocks(-width, param.Y_SEA,     -width,   width, param.Y_SEA, width,    blocks.GRASS_BLOCK)


if __name__ == "__main__":
    # Connect to minecraft and open a session as player with origin location
    mc = Minecraft.create(address=param.ADRS_MCR, port=param.PORT_MCR)
    result = mc.setPlayer(param.PLAYER_NAME, po.x, po.y, po.z)
    if "Error" in result:
        sys.exit(result)
    else:
        print(result)

    mc.postToChat("axis_flat module main part")

    reset_minecraft_world(mc, width=48)
    # draw_XYZ_axis(mc, wait=0.2)
    # clear_XYZ_axis(mc, wait=0)
    draw_XYZ_axis(mc, wait=0.1)
