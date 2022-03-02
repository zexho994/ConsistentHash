import java.util.Objects;

/**
 * @author Zexho
 * @date 2022/3/2 7:09 PM
 */
public class Node {

    private String name;
    private String host;
    private Integer port;

    public Node(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getKey() {
        return this.name + this.host + this.port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return getPort() == node.getPort() && getName().equals(node.getName()) && getHost().equals(node.getHost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHost(), getPort());
    }
}
