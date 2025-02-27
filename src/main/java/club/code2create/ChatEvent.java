package club.code2create;

public class ChatEvent {
    public static final int POST = 0;
    private int type;
    private int entityId;
    private String message;

    public ChatEvent(int type, int entityId, String message) {
        this.type = type;
        this.entityId = entityId;
        this.message = message;
    }

    @Override
    public String toString() {
        String sType = (type == POST) ? "ChatEvent.POST" : "???";
        return String.format("ChatEvent(%s, %d, %s)", sType, entityId, message);
    }

    public static ChatEvent Post(int entityId, String message) {
        return new ChatEvent(POST, entityId, message);
    }
}