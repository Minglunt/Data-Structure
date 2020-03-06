import org.junit.Test;
import org.junit.Assert;

public class TestUnionFind {

    @Test
    public void test(){
        UnionFind sets = new UnionFind(10);
        Assert.assertEquals(false, sets.connected(1,3));
        sets.union(1,3);
        sets.union(2,4);
        sets.union(3,5);
        Assert.assertEquals(true, sets.connected(1,5));
        Assert.assertEquals(false, sets.connected(1,2));
        sets.union(5,4);
        Assert.assertEquals(3, sets.find(5));
    }
}
