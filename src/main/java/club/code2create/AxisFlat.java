package club.code2create;

    import java.io.IOException;

    public class AxisFlat {
        private static final int AXIS_WIDTH = 40;
        private static final int AXIS_TOP = 127;
        private static final int AXIS_Y_V_ORG = 96;
        private static final int AXIS_BOTTOM = 63;

        private static final Blocks AXIS_BLOCK_X = Blocks.DIAMOND_BLOCK;
        private static final Blocks AXIS_BLOCK_Y = Blocks.SEA_LANTERN;
        private static final Blocks AXIS_BLOCK_Z = Blocks.GOLD_BLOCK;
        private static final Blocks AXIS_BLOCK_TOP = Blocks.GLOWSTONE;

        private static Minecraft connectAndSetPlayer() throws IOException, Connection.RequestError {
            try {
                Minecraft mc = Minecraft.create(ParamMCJE.ADRS_MCR, ParamMCJE.PORT_MCR, false);
                String result = mc.setPlayer(ParamMCJE.PLAYER_NAME, ParamMCJE.PLAYER_ORIGIN.x, ParamMCJE.PLAYER_ORIGIN.y, ParamMCJE.PLAYER_ORIGIN.z);
                if (result.contains("Error")) {
                    throw new Connection.RequestError("Error setting player: " + result);
                } else {
                    System.out.println(result);
                }
                return mc;
            } catch (IOException e) {
                System.err.println("IOException occurred while connecting and setting player: " + e.getMessage());
                throw e;
            } catch (Connection.RequestError e) {
                System.err.println("RequestError occurred while setting player: " + e.getMessage());
                throw e;
            }
        }

        public static void drawXYZAxis() {
            try {
                Minecraft mc = connectAndSetPlayer();
                mc.postToChat("drawXYZAxis");
                mc.postToChat("x-axis from negative to positive region");
                for (int x = -AXIS_WIDTH; x <= AXIS_WIDTH; x += (x < 0 ? 2 : 1)) {
                    mc.setBlock(x, AXIS_Y_V_ORG, 0, AXIS_BLOCK_X);
                    Thread.sleep(100);
                }

                mc.postToChat("y-axis from bottom to top");
                for (int y = AXIS_BOTTOM; y <= AXIS_TOP; y++) {
                    mc.setBlock(0, y, 0, (y <= AXIS_TOP - 5 ? AXIS_BLOCK_Y : AXIS_BLOCK_TOP));
                    Thread.sleep(100);
                }

                mc.postToChat("z-axis from negative to positive region");
                for (int z = -AXIS_WIDTH; z <= AXIS_WIDTH; z += (z < 0 ? 2 : 1)) {
                    mc.setBlock(0, AXIS_Y_V_ORG, z, AXIS_BLOCK_Z);
                    Thread.sleep(100);
                }
            } catch (InterruptedException | IOException | Connection.RequestError e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        public static void clearXYZAxis() {
            try {
                Minecraft mc = connectAndSetPlayer();
                mc.postToChat("clearXYZAxis");
                mc.setBlocks(-AXIS_WIDTH, AXIS_Y_V_ORG, 0, AXIS_WIDTH, AXIS_Y_V_ORG, 0, Blocks.AIR);
                Thread.sleep(100);
                mc.setBlocks(0, AXIS_BOTTOM, 0, 0, AXIS_TOP, 0, Blocks.AIR);
                Thread.sleep(100);
                mc.setBlocks(0, AXIS_Y_V_ORG, -AXIS_WIDTH, 0, AXIS_Y_V_ORG, AXIS_WIDTH, Blocks.AIR);
                Thread.sleep(100);
            } catch (InterruptedException | IOException | Connection.RequestError e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        public static void resetMinecraftWorld() {
            try {
                Minecraft mc = connectAndSetPlayer();
                mc.postToChat("resetMinecraftWorld");
                mc.setBlocks(-48, ParamMCJE.Y_SEA + 1, -48, 48, AXIS_TOP, 48, Blocks.AIR);
                mc.setBlocks(-48, ParamMCJE.Y_SEA, -48, 48, ParamMCJE.Y_SEA, 48, Blocks.GRASS_BLOCK);
            } catch (IOException | Connection.RequestError e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        public static void run() {
            try {
                Minecraft mc = connectAndSetPlayer();
                mc.postToChat("axis_flat module main part");

                // resetMinecraftWorld(mc, 48);
                // drawXYZAxis(mc, 0.1);
                clearXYZAxis();
            } catch (IOException | Connection.RequestError e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }