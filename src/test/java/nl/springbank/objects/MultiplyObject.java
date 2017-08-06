package nl.springbank.objects;

/**
 * Object used for testing.
 *
 * @author Tristan de Boer
 */
public class MultiplyObject {
    private int a;

    private int b;

    public MultiplyObject() {
    }

    public MultiplyObject(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}