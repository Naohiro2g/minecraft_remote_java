package club.code2create;

        import java.io.IOException;

        public class CmdPositioner {
            protected Connection conn;
            protected String pkg;

            public CmdPositioner(Connection connection, String packagePrefix) {
                this.conn = connection;
                this.pkg = packagePrefix;
            }

            public Vec3 getPos(int id) throws IOException, Connection.RequestError {
                try {
                    String s = conn.sendReceive(pkg + ".getPos", id);
                    String[] parts = s.split(",");
                    return new Vec3(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                } catch (IOException | Connection.RequestError e) {
                    e.printStackTrace();
                    return new Vec3(0, 0, 0);
                }
            }

            public void setPos(int id, int... args) throws IOException {
                try {
                    conn.send(pkg + ".setPos", id, args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public Vec3 getTilePos(int id) throws IOException, Connection.RequestError {
                try {
                    String s = conn.sendReceive(pkg + ".getTile", id);
                    String[] parts = s.split(",");
                    return new Vec3(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                } catch (IOException | Connection.RequestError e) {
                    e.printStackTrace();
                    return new Vec3(0, 0, 0);
                }
            }

            public void setTilePos(int id, int... args) throws IOException {
                try {
                    conn.send(pkg + ".setTile", id, args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void setDirection(int id, int... args) throws IOException {
                try {
                    conn.send(pkg + ".setDirection", id, args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public Vec3 getDirection(int id) throws IOException, Connection.RequestError {
                try {
                    String s = conn.sendReceive(pkg + ".getDirection", id);
                    String[] parts = s.split(",");
                    return new Vec3(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                } catch (IOException | Connection.RequestError e) {
                    e.printStackTrace();
                    return new Vec3(0, 0, 0);
                }
            }

            public void setRotation(int id, int yaw) throws IOException {
                try {
                    conn.send(pkg + ".setRotation", id, yaw);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public float getRotation(int id) throws IOException, Connection.RequestError {
                try {
                    return Float.parseFloat(conn.sendReceive(pkg + ".getRotation", id));
                } catch (IOException | Connection.RequestError e) {
                    e.printStackTrace();
                    return 0.0f;
                }
            }

            public void setPitch(int id, int pitch) throws IOException {
                try {
                    conn.send(pkg + ".setPitch", id, pitch);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public float getPitch(int id) throws IOException, Connection.RequestError {
                try {
                    return Float.parseFloat(conn.sendReceive(pkg + ".getPitch", id));
                } catch (IOException | Connection.RequestError e) {
                    e.printStackTrace();
                    return 0.0f;
                }
            }

            public void setting(String setting, boolean status) throws IOException {
                try {
                    conn.send(pkg + ".setting", setting, status ? 1 : 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }