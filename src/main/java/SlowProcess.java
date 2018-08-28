/**
 * Created by mtumilowicz on 2018-08-27.
 */
class SlowProcess {

    static String call() {
        System.out.println("SlowProcess started");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SlowProcess ended");
        return "ended!";
    }
}
