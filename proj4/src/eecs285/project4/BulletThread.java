package eecs285.project4;

public class BulletThread extends Thread {
    protected Model model;
    protected Bullet bullet;
    protected final Tank tank;
    private volatile boolean run = true;

    public BulletThread(final Tank tank, final Model model, final int bulletStrength, final int bulletSpeed, final int direction,
                        final int startRow, final int startCol) {
        this.tank = tank;
        this.model = model;
        this.bullet = new Bullet(bulletStrength, bulletSpeed, direction, startRow, startCol);
    }

    /*
     * Method to terminate this BulletThread.
     * Currently termination is just being done using stop() instead
     */
    public void term() {
        run = false;
    }
    public void run() {
        while(run) {
            try {
                model.notifyLocation(this);
                sleep(50);
                bullet.move();
            } catch (InterruptedException e) {}
        }
        tank.canShoot = true;
    }
}
