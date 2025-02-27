package club.code2create;

          import java.io.IOException;

          public class Entity {
              private CmdPositioner p;
              private int id;
              private String type;

              public Entity(Connection conn, int entity_uuid, String typeName) {
                  this.p = new CmdPositioner(conn, "entity");
                  this.id = entity_uuid;
                  this.type = typeName;
              }

              public Vec3 getPos() throws IOException, Connection.RequestError {
                  return p.getPos(id);
              }

              public void setPos(int... args) throws IOException {
                  p.setPos(id, args);
              }

              public Vec3 getTilePos() throws IOException, Connection.RequestError {
                  return p.getTilePos(id);
              }

              public void setTilePos(int... args) throws IOException {
                  p.setTilePos(id, args);
              }

              public void setDirection(int... args) throws IOException {
                  p.setDirection(id, args);
              }

              public Vec3 getDirection() throws IOException, Connection.RequestError {
                  return p.getDirection(id);
              }

              public void setRotation(int yaw) throws IOException {
                  p.setRotation(id, yaw);
              }

              public float getRotation() throws IOException, Connection.RequestError {
                  return p.getRotation(id);
              }

              public void setPitch(int pitch) throws IOException {
                  p.setPitch(id, pitch);
              }

              public float getPitch() throws IOException, Connection.RequestError {
                  return p.getPitch(id);
              }

              public void remove() throws IOException {
                  p.conn.send("entity.remove", id);
              }
          }