package club.code2create;

import java.io.IOException;

public class CmdEntity extends CmdPositioner {
    public CmdEntity(Connection connection) {
        super(connection, "entity");
    }

    public String getName(int id) throws IOException {
        try {
            return conn.sendReceive("entity.getName", id);
        } catch (Connection.RequestError e) {
            e.printStackTrace();
            return "";
        }
    }

    public void remove(int id) throws IOException {
        conn.send("entity.remove", id);
    }
}