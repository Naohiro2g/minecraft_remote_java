package club.code2create;

            import java.io.IOException;

            public class Hello {
                public static void run() {
                    try {
                        System.out.println("Creating Minecraft instance...");
                        Minecraft mc = Minecraft.create(ParamMCJE.ADRS_MCR, ParamMCJE.PORT_MCR, true);
                        System.out.println("Minecraft instance created.");

                        System.out.println("Setting player...");
                        String result = mc.setPlayer(ParamMCJE.PLAYER_NAME, ParamMCJE.PLAYER_ORIGIN.x, ParamMCJE.PLAYER_ORIGIN.y, ParamMCJE.PLAYER_ORIGIN.z);
                        System.out.println("Player set. Result: " + result);

                        if (result.contains("Error")) {
                            System.out.println(result);
                            System.exit(1);
                        } else {
                            System.out.println(result);
                        }
                        mc.postToChat("hello from Java");

                        mc.setBlock(10, 64, 10, Blocks.SEA_LANTERN);
                        mc.setBlocks(-1, 63, -1, 1, 100, 1, Blocks.REDSTONE_BLOCK);

                    } catch (IOException | Connection.RequestError e) {
                        System.err.println("Error occurred in main method:");
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
            }