package InterviewByCompany.Apple;

public class GuessTheOutput {

    static Integer I;
    public static void main(String[] args) {
        String s;
        try {
            // this will throw exception wince, "I" is null now it will throw null pointer exception
            s = I.toString();
        } finally {
            try {
                int i = Integer.parseInt(args[0]);
                // "ex" will catch the index out of bound exception, because there is no args,
                // since the args array is length 0
            } catch (Exception ex) {
                System.out.println("ex");
            } finally {
                // finally will execute no matter how
                System.out.println("fin2");
            }
            // outer finally
            System.out.println("fin1");
        }
    }

    // thus the overall output will be,

    /*
     ex
     fin2
     fin1
     null pointer exception because of "s = I.toString();"
     */

    // 这是因为最外层的 try 没有 catch block，所以 finally 会先运行
    // 如果我们添加了 catch 那么
    // null pointer exception because of "s = I.toString();" 会首先被抛出，然后才进行 finally
}
