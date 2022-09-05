package http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fact {

    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final String UpVotes;

    public Fact(@JsonProperty("id") String id,
                @JsonProperty("text") String text,
                @JsonProperty("type") String type,
                @JsonProperty("user") String user,
                @JsonProperty("upvotes") String UpVotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.UpVotes = UpVotes;
    }

    @Override
    public String toString() {
        return  "id = '" + id + "'\n" +
                "text = '" + text + "'\n" +
                "type = '" + type + "'\n" +
                "user = '" + user + "'\n" +
                "UpVotes = '" + UpVotes + "'\n";
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public String getUpVotes() {
        return UpVotes;
    }
}
