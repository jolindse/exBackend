package to.mattias.beans;

/**
 * <h1>Created by Mattias on 2017-01-24.</h1>
 */
public class Cmd {
    private String cmd;
    private Object[] payload;

    public Cmd() {
    }

    public Cmd(String cmd, Object[] payload) {
        this.cmd = cmd;
        this.payload = payload;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Object[] getPayload() {
        return payload;
    }

    public void setPayload(Object[] payload) {
        this.payload = payload;
    }
}
