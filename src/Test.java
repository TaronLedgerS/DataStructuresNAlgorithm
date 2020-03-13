public class Test {
    static  int a = 1;
    public static void main(String[] args) {
        Test.staticClass t = new Test.staticClass();
        t.test();
        System.out.println(t.a);

    }
    static class staticClass{
        int a = 2;

        public void test() {
            System.out.println(this.a);
            System.out.println(new Test().a);
        }
    }
}
