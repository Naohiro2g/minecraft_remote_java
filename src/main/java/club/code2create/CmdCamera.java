package club.code2create;

import java.io.IOException;

public class CmdCamera {
    private Connection conn;

    public CmdCamera(Connection connection) {
        this.conn = connection;
    }

    public void setNormal(int... args) throws IOException {
        conn.send("camera.mode.setNormal", args);
    }

    public void setFixed() throws IOException {
        conn.send("camera.mode.setFixed");
    }

    public void setFollow(int... args) throws IOException {
        conn.send("camera.mode.setFollow", args);
    }

    public void setPos(int... args) throws IOException {
        conn.send("camera.setPos", args);
    }
}