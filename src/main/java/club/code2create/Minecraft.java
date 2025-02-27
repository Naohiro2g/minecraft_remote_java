package club.code2create;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Minecraft {
    private Connection conn;
    public CmdCamera camera;
    public CmdEntity entity;
    public CmdPlayer player;
    public CmdEvents events;

    public Minecraft(Connection connection) {
        this.conn = connection;
        this.camera = new CmdCamera(connection);
        this.entity = new CmdEntity(connection);
        this.player = new CmdPlayer(connection);
        this.events = new CmdEvents(connection);
    }

    @Mcpy("world.getBlock")
    public int getBlock(int... args) throws IOException, Connection.RequestError {
        try {
            return Integer.parseInt(conn.sendReceive("world.getBlock", args));
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Mcpy("world.getBlockWithData")
    public String[] getBlockWithData(int... args) throws IOException, Connection.RequestError {
        try {
            return conn.sendReceive("world.getBlockWithData", args).split(",");
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    @Mcpy("world.getBlocks")
    public String[] getBlocks(int... args) throws IOException, Connection.RequestError {
        try {
            return conn.sendReceive("world.getBlocks", args).split(",");
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    @Mcpy("world.setBlock")
    public void setBlock(int x, int y, int z, Blocks block) throws IOException {
        conn.send("world.setBlock", x, y, z, block.getName());
    }

    @Mcpy("world.setBlocks")
    public void setBlocks(int x1, int y1, int z1, int x2, int y2, int z2, Blocks block) throws IOException {
        conn.send("world.setBlocks", x1, y1, z1, x2, y2, z2, block.getName());
    }

    @Mcpy("world.setSign")
    public void setSign(int... args) throws IOException {
        conn.send("world.setSign", args);
    }

    @Mcpy("world.spawnEntity")
    public Entity spawnEntity(int... args) throws IOException, Connection.RequestError {
        try {
            int entityId = Integer.parseInt(conn.sendReceive("world.spawnEntity", args));
            return new Entity(conn, entityId, String.valueOf(args[3]));
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
            return null;
        }
    }

    @Mcpy("world.spawnParticle")
    public void spawnParticle(int... args) throws IOException {
        conn.send("world.spawnParticle", args);
    }

    @Mcpy("world.getNearbyEntities")
    public List<Entity> getNearbyEntities(int... args) throws IOException, Connection.RequestError {
        try {
            String[] entities = conn.sendReceive("world.getNearbyEntities", args).split(",");
            List<Entity> entityList = new ArrayList<>();
            for (String entity : entities) {
                String[] parts = entity.split(":");
                entityList.add(new Entity(conn, Integer.parseInt(parts[1]), parts[0]));
            }
            return entityList;
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Mcpy("world.removeEntity")
    public void removeEntity(int... args) throws IOException, Connection.RequestError {
        try {
            conn.sendReceive("world.removeEntity", args);
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
        }
    }

    @Mcpy("world.getHeight")
    public int getHeight(int... args) throws IOException, Connection.RequestError {
        try {
            return Integer.parseInt(conn.sendReceive("world.getHeight", args));
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Mcpy("world.getPlayerIds")
    public String[] getPlayerEntityIds() throws IOException, Connection.RequestError {
        try {
            return conn.sendReceive("world.getPlayerIds").split("\\|");
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    @Mcpy("world.getPlayerId")
    public int getPlayerEntityId(String name) throws IOException, Connection.RequestError {
        try {
            return Integer.parseInt(conn.sendReceive("world.getPlayerId", name));
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Mcpy("world.checkpoint.save")
    public void saveCheckpoint() throws IOException {
        conn.send("world.checkpoint.save");
    }

    @Mcpy("world.checkpoint.restore")
    public void restoreCheckpoint() throws IOException {
        conn.send("world.checkpoint.restore");
    }

    @Mcpy("chat.post")
    public void postToChat(String msg) throws IOException {
        conn.send("chat.post", "\"" + msg + "\"");
    }

    @Mcpy("world.setting")
    public void setting(String setting, boolean status) throws IOException {
        conn.send("world.setting", setting, status ? 1 : 0);
    }

    @Mcpy("setPlayer")
    public String setPlayer(String name, int x, int y, int z) throws IOException, Connection.RequestError {
        try {
            return conn.sendReceive("setPlayer", name, x, y, z);
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Minecraft create(String address, int port, boolean debug) throws IOException {
        return new Minecraft(new Connection(address, port, debug));
    }

    public static Minecraft create() throws IOException {
        String address = System.getenv().getOrDefault("JRP_API_HOST", "localhost");
        int port = Integer.parseInt(System.getenv().getOrDefault("JRP_API_PORT", "25575"));
        return create(address, port, false);
    }
}