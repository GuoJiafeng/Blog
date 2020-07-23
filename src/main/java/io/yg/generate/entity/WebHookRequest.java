package io.yg.generate.entity;

/**
 * @author v_guojiafeng
 * @time 2020/7/23 12:57 下午
 * @info
 */
public class WebHookRequest {
    private String ref;
    private String before;
    private String after;

    public WebHookRequest() {
    }

    public WebHookRequest(String ref, String before, String after) {
        this.ref = ref;
        this.before = before;
        this.after = after;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    @Override
    public String toString() {
        return "WebHookRequest{" +
                "ref='" + ref + '\'' +
                ", before='" + before + '\'' +
                ", after='" + after + '\'' +
                '}';
    }
}
