import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class main {
    @Test
    public void testMD5(){

        SimpleHash hash = new SimpleHash("MD5","123456", ByteSource.Util.bytes("Zachary"),1024);
        System.out.println(hash);
    }

    @Test
    public void testCalculate(){
        double result = Math.pow(10,Math.pow(10,Math.pow(10,Math.pow(10,2.08))));
        System.out.println(result);
    }

    @Test
    public void testReplace(){
        System.out.println("fqeqwe\n".replaceAll("\n","\r"));

    }
}
