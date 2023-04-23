package clubdev.text;

public class ConsoleAnimation implements Runnable {
    
    private volatile boolean running = true;

    public void run() {
        String[] frames = {"|", "/", "-", "\\"};
        int i = 0;
        while (running) {
            System.out.print("\r" + TextFormat.YELLOW + frames[i % 4] + " ");
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
