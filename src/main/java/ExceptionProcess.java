/**
 * Created by mtumilowicz on 2018-08-28.
 */
class ExceptionProcess {
    static String call() {
        System.out.println("ExceptionProcess started");
        throw new RuntimeException();
    }
}
