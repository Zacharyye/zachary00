import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class main {
    @Test
    public void testMD5(){

        SimpleHash hash = new SimpleHash("MD5","123456", ByteSource.Util.bytes("Zachary"),1024);
        System.out.println(hash);
    }
}
