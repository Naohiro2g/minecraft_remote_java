package club.code2create;

public class ProjectileEvent {
    public static final int HIT = 0;
    private int type;
    private Vec3 pos;
    private int face;
    private String shooterName;
    private String victimName;

    public ProjectileEvent(int type, int x, int y, int z, int face, String shooterName, String victimName) {
        this.type = type;
        this.pos = new Vec3(x, y, z);
        this.face = face;
        this.shooterName = shooterName;
        this.victimName = victimName;
    }

    @Override
    public String toString() {
        String sType = (type == HIT) ? "ProjectileEvent.HIT" : "???";
        return String.format("ProjectileEvent(%s, %d, %d, %d, %d, %s, %s)", sType, pos.getX(), pos.getY(), pos.getZ(), face, shooterName, victimName);
    }

    public static ProjectileEvent Hit(int x, int y, int z, int face, String shooterName, String victimName) {
        return new ProjectileEvent(HIT, x, y, z, face, shooterName, victimName);
    }
}