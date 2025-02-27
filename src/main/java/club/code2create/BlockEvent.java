package club.code2create;

public class BlockEvent {
    public static final int HIT = 0;
    private int type;
    private Vec3 pos;
    private int face;
    private int entityId; // ここにフィールドを追加

    public BlockEvent(int type, int x, int y, int z, int face, int entityId) {
        this.type = type;
        this.pos = new Vec3(x, y, z);
        this.face = face;
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        String sType = (type == HIT) ? "BlockEvent.HIT" : "???";
        return String.format("BlockEvent(%s, %d, %d, %d, %d, %d)", sType, pos.getX(), pos.getY(), pos.getZ(), face, entityId);
    }

    public static BlockEvent Hit(int x, int y, int z, int face, int entityId) {
        return new BlockEvent(HIT, x, y, z, face, entityId);
    }
}