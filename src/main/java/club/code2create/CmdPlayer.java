package club.code2create;

import java.io.IOException;

public class CmdPlayer extends CmdPositioner {
    public CmdPlayer(Connection connection) {
        super(connection, "player");
    }

    public Vec3 getPos() throws IOException, Connection.RequestError {
        return super.getPos(0);
    }

    public void setPos(int... args) throws IOException {
        super.setPos(0, args);
    }

    public Vec3 getTilePos() throws IOException, Connection.RequestError {
        return super.getTilePos(0);
    }

    public void setTilePos(int... args) throws IOException {
        super.setTilePos(0, args);
    }

    public void setDirection(int... args) throws IOException {
        super.setDirection(0, args);
    }

    public Vec3 getDirection() throws IOException, Connection.RequestError {
        return super.getDirection(0);
    }

    public void setRotation(int yaw) throws IOException {
        super.setRotation(0, yaw);
    }

    public float getRotation() throws IOException, Connection.RequestError {
        return super.getRotation(0);
    }

    public void setPitch(int pitch) throws IOException {
        super.setPitch(0, pitch);
    }

    public float getPitch() throws IOException, Connection.RequestError {
        return super.getPitch(0);
    }
}