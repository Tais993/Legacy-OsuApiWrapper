package v1.entities.multiplayer;

import v1.ApiHandler;
import v1.entities.RequestBuilder;

public class MatchRequestBuilder extends RequestBuilder<MatchRequestBuilder> {
    private String key;

    private long matchId;

    @Override
    public MatchRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public MatchRequestBuilder setMatchId(long matchId) {
        this.matchId = matchId;
        return this;
    }

    @Override
    public String getUrl() {
        StringBuilder url = new StringBuilder(ApiHandler.startUrl);

        url.append("get_match?k=").append(key);

        if (matchId != 0) {
            url.append("&mp=").append(matchId);
        }

        System.out.println(url);

        return url.toString();
    }
}
