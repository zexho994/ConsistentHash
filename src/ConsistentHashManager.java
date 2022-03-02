/**
 * @author Zexho
 * @date 2022/3/2 7:05 PM
 */
public class ConsistentHashManager {

    private static final int DEFAULT_VIR_NODE_COUNT = 150;
    public int virNodeCount;

    public ConsistentHashManager() {
        this.virNodeCount = DEFAULT_VIR_NODE_COUNT;
    }

    public ConsistentHashManager(int virNodeCount) {
        this.virNodeCount = virNodeCount;
    }

    public void addNode(Node node) {

    }

    public void removeNote() {

    }

    public Node getNextNote(int hash) {

        return null;
    }


}
