import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by asrory on 23/12/16.
 */
class llist {
    String data;
    llist link;

    public llist(String data, llist link) {
        this.data = data;
        this.link = link;
    }

    public String getData() {
        return data;
    }

    public llist getLink() {
        return link;
    }
}

public class linkedlist {
    public static void main(String[] args) {
        llist t = new llist("tama", new llist("asrory", new llist("ridhana", null)));
        llist b = t;
        while (b != null) {
            String a = b.getData();
            System.out.println(a);
            b = b.getLink();
        }
    }

}