import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class ConsistentHashManagerTest {

    @Test
    void addNodeTest() {
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
        for (int i = 0; i < 20000; i++) {
            Node nextNote = consistentHashManager.getNextNode(preKey + i);
            map.computeIfPresent(nextNote.getName(), (k, v) -> v + 1);
        }
        map.entrySet().forEach(System.out::println);
    }

    @Test
    void removeNodeTest() {
        ConsistentHashManager consistentHashManager = new ConsistentHashManager(100);
        Node node4 = new Node("node4", "192.0.0.4", 8080);

        consistentHashManager.addNode(new Node("node1", "192.0.0.1", 8080));
        consistentHashManager.addNode(new Node("node2", "192.0.0.2", 8080));
        consistentHashManager.addNode(new Node("node3", "192.0.0.3", 8080));
        consistentHashManager.addNode(node4);

        String preKey = "Data_";
        Map<Integer, String> map = new HashMap<>(20000);

        for (int i = 0; i < 20000; i++) {
            Node nextNode = consistentHashManager.getNextNode(preKey + i);
            map.put(i, nextNode.getName());
        }
        Map<String, Integer> statistic = new HashMap<>();
        statistic.put("node1", 0);
        statistic.put("node2", 0);
        statistic.put("node3", 0);
        statistic.put("node4", 0);
        map.values().forEach(name -> statistic.computeIfPresent(name, (k, v) -> v + 1));

        // 移除一个节点
        consistentHashManager.removeNote(node4);
        // 测试下移除节点后的命中率
        AtomicInteger n1 = new AtomicInteger(0);
        AtomicInteger n2 = new AtomicInteger(0);
        AtomicInteger n3 = new AtomicInteger(0);
        for (int i = 0; i < 20000; i++) {
            Node nextNode = consistentHashManager.getNextNode(preKey + i);
            if (nextNode.getName().equals("node1")) {
                n1.incrementAndGet();
            } else if (nextNode.getName().equals("node2")) {
                n2.incrementAndGet();
            } else if (nextNode.getName().equals("node3")) {
                n3.incrementAndGet();
            } else {
                throw new RuntimeException("node4 未清理干净");
            }
        }

        // 打印命中率, 原本的次数/删除后的次数
        statistic.forEach((key, value) -> {
            if (key.equals("node1")) {
                System.out.println("Node1,总访问次数=" + n1 + ",有效访问 " + value + " 命中率 = " + (double) (value) / n1.get());
            } else if (key.equals("node2")) {
                System.out.println("Node2,总访问次数=" + n2 + ",有效访问 " + value + " 命中率 = " + (double) (value) / n2.get());
            } else if (key.equals("node3")) {
                System.out.println("Node3,总访问次数=" + n3 + ",有效访问 " + value + " 命中率 = " + (double) (value) / n3.get());
            }
        });
    }

    @Test
    void getNextNodeTest() {
    }
}