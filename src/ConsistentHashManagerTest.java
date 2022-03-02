import org.junit.jupiter.api.Test;

class ConsistentHashManagerTest {


    @Test
    void addNode() {
        ConsistentHashManager consistentHashManager = new ConsistentHashManager();
        Node node = new Node("node1", "192.0.0.1", 8080);
        consistentHashManager.addNode(node);
    }

    @Test
    void removeNote() {
    }

    @Test
    void getNextNote() {
    }
}