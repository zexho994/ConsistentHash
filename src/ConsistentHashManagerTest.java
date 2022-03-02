import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
    void loadBalancingTest() {
        ConsistentHashManager consistentHashManager = new ConsistentHashManager();
        consistentHashManager.addNode(new Node("node1", "192.0.0.1", 8080));
        consistentHashManager.addNode(new Node("node2", "192.0.0.2", 8080));
        consistentHashManager.addNode(new Node("node3", "192.0.0.3", 8080));
        consistentHashManager.addNode(new Node("node4", "192.0.0.4", 8080));

        String preKey = "Data_";
        Map<String, Integer> map = new HashMap<>(200000);
        map.put("node1", 0);
        map.put("node2", 0);
        map.put("node3", 0);
        map.put("node4", 0);
        for (int i = 0; i < 200000; i++) {
            Node nextNote = consistentHashManager.getNextNote(preKey + i);
            map.computeIfPresent(nextNote.getName(), (k, v) -> v + 1);
        }
        map.entrySet().forEach(System.out::println);
    }

    @Test
    void removeNote() {
    }

    @Test
    void getNextNote() {
    }
}