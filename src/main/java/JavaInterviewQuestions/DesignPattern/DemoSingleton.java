package JavaInterviewQuestions.DesignPattern;

public class DemoSingleton {
    private static volatile DemoSingleton demoSingleton;

    private DemoSingleton(){

    }

    // make sure the getDemoSinglerton method is static, so that, if we try to initialize a singleton
    // we can directly call DemoSingleton.getDemoSingleton
    public static DemoSingleton getDemoSingleton(){
        if(demoSingleton == null){
            synchronized (DemoSingleton.class){
                if(demoSingleton == null){
                    demoSingleton = new DemoSingleton();
                }
            }
        }

        return demoSingleton;
    }
}
