package PracticalExperiments.DesignPattern.FactoryDesign;




public class DemoFactory {


    public static void main(String[] args) {
        FactoryInterface factory = new Factory1();
        factory.doSomething();

    }
}
