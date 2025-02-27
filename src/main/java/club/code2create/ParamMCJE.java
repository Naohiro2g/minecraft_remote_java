package club.code2create;

public class ParamMCJE {
//  Minecraft Java Edition Server
    public static final String ADRS_MCR = "localhost";
    public static final int PORT_MCR = 25575;
//  Player
    public static final String PLAYER_NAME = "PLAYER_NAME";
    public static final PlayerOrigin PLAYER_ORIGIN = new PlayerOrigin(5000, 0, 5000);

//  vertical levels in Minecraft 1.20+
    public static final int Y_TOP = 320;
    public static final int Y_CLOUD_BOTTOM = 199;
    public static final int Y_SEA = 62;
    public static final int Y_BOTTOM = 0;
    public static final int Y_BOTTOM_STEVE = -64;

    public static class PlayerOrigin {
        public final int x, y, z;

        public PlayerOrigin(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}