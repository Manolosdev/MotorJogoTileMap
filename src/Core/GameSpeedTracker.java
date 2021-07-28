/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author Manolo
 */
public class GameSpeedTracker {

    public static double NANOS_IN_ONE_SECOND = 1e9;
    protected int ticksPerSecond;
    protected long previousNanoTime;
    protected int countedTicks;
    protected int totalTicks;

    public void start() {
        this.previousNanoTime = System.nanoTime();
        this.countedTicks = 0;
        this.ticksPerSecond = 0;
        this.totalTicks = 0;
    }

    public int countTick() {
        this.countedTicks++;
        this.totalTicks++;
        this.update();
        return this.totalTicks;
    }

    protected void update() {
        if (System.nanoTime() - this.previousNanoTime > this.NANOS_IN_ONE_SECOND) {
            this.ticksPerSecond = this.countedTicks;
            this.countedTicks = 0;
            this.previousNanoTime = System.nanoTime();
        }
    }

    public int getTPS() {
        return this.ticksPerSecond;
    }

    public int getTotalTicks() {
        return this.totalTicks;
    }
}
