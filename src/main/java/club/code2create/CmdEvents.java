package club.code2create;

import java.io.IOException;

public class CmdEvents {
    private Connection conn;

    public CmdEvents(Connection connection) {
        this.conn = connection;
    }

    public void clear() throws IOException {
        conn.send("events.clear");
    }

    public String poll() throws IOException, Connection.RequestError {
        try {
            return conn.sendReceive("events.poll");
        } catch (IOException | Connection.RequestError e) {
            e.printStackTrace();
            return "";
        }
    }
}