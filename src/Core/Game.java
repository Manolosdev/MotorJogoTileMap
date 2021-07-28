/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author Manolo
 */
public abstract class Game implements WindowListener {

    private JFrame mainWindow;
    private boolean active;
    private BufferStrategy bs;
    private GameSpeedTracker speedTracker;
    private int expectedTPS;
    private double expectedNanosPerTicks;
    
    public boolean showFPS;

    public Game() {
        this.mainWindow = new JFrame("RPG projeto");
        this.mainWindow.setSize(500, 400);
        this.mainWindow.addWindowListener(this);
        this.mainWindow.addKeyListener(InputManager.getInstance());
        this.mainWindow.addMouseListener(InputManager.getInstance());
        this.mainWindow.addMouseMotionListener(InputManager.getInstance());
        this.active = false;
        this.showFPS = false;
    }

    public void run() {
        this.active = true;
        load();
        this.expectedTPS = 60;
        this.expectedNanosPerTicks = GameSpeedTracker.NANOS_IN_ONE_SECOND / this.expectedTPS;
        long nanoTimeAtNextTick = System.nanoTime();
        while (this.active) {
            this.speedTracker.update();
            if (System.nanoTime() > nanoTimeAtNextTick) {
                nanoTimeAtNextTick += expectedNanosPerTicks;
                InputManager.getInstance().update();
                update();
                render();
            }
            if (InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)) {
                this.active = false;
            }
        }
        unload();
    }

    public void load() {
        this.mainWindow.setUndecorated(false);
        this.mainWindow.setIgnoreRepaint(true);
        this.mainWindow.setLocationRelativeTo(null);
        this.mainWindow.setVisible(true);
        this.mainWindow.createBufferStrategy(2);
        this.bs = this.mainWindow.getBufferStrategy();
        this.speedTracker = new GameSpeedTracker();
        onLoad();
        this.speedTracker.start();
    }

    public void update() {
        this.speedTracker.countTick();
        onUpdate(this.speedTracker.totalTicks);
        Thread.yield();
    }

    public void render() {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), getHeight());
        onRender(g);
        g.setColor(Color.darkGray);
        if (showFPS) {
            g.setColor(Color.darkGray);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            g.fillRect(getWidth() - 65, 39, 51, 17);
            g.setComposite(AlphaComposite.SrcOver);
            g.setColor(Color.white);
            g.drawRoundRect(getWidth() - 65, 38, 50, 18, 3, 3);
            g.setFont(new Font("", Font.BOLD, 12));
            g.drawString("Fps " + this.speedTracker.getTPS(), getWidth() - 62, 51);
        }
        g.dispose();
        this.bs.show();
    }

    public void unload() {
        onUnload();
        this.bs.dispose();
        this.mainWindow.dispose();
    }

    public void terminate() {
        this.active = false;
    }

    public JFrame getMainWindow() {
        return this.mainWindow;
    }

    public int getWidth() {
        return this.mainWindow.getWidth();
    }

    public int getHeight() {
        return this.mainWindow.getHeight();
    }

    ////////////////////////////////////////////////////////////////////////////
    //                     Metodos abstratos para subClasses                  //
    ////////////////////////////////////////////////////////////////////////////
    public abstract void onLoad();

    public abstract void onUnload();

    public abstract void onRender(Graphics2D g);

    public abstract void onUpdate(int currentTick);

    ////////////////////////////////////////////////////////////////////////////
    //                      Metodos não utilizados                            //
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void windowClosing(WindowEvent e) {
        this.terminate();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
