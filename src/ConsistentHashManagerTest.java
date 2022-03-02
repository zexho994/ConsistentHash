import org.junit.jupiter.api.Test;

class ConsistentHashManagerTest {


    @Test
    void addNode() {
        int vCount = 10;
        ConsistentHashManager consistentHashManager = new ConsistentHashManager(vCount);
        consistentHashManager.addNode(new Node("node1", "192.0.0.1", 8080));
        consistentHashManager.addNode(new Node("node2", "192.0.0.2", 8080));
        consistentHashManager.addNode(new Node("node3", "192.0.0.3", 8080));
        consistentHashManager.addNode(new Node("node4", "192.0.0.4", 8080));
        assert consistentHashManager.nodeCount() == 44;
    }

    @Test
    void removeNote() {
    }

    @Test
    void getNextNote() {
    }
}