package eecs285.project4;

public class BulletThread extends Thread {
    protected Model model;
    protected Bullet bullet;
    protected final Tank tank;

    public BulletThread(final Tank tank, final Model model, final int bulletStrength, final int bulletSpeed, final int direction,
            final int startRow, final int startCol) {
        this.tank = tank;
        this.model = model;
        this.bullet = new Bullet(bulletStrength, bulletSpeed, tank.getType(), direction, startRow, startCol);
    }

    public void run() {
        while(model.notifyLocation(this)) {
            try {
                sleep(50);
            } catch (InterruptedException e) {}
            bullet.move();
        }
        tank.canShoot = true;
    }
}
