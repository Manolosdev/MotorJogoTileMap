/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.event.MouseInputListener;

/**
 * Informa Eventos do teclado
 *
 * @author Manolo
 */
public class InputManager implements KeyListener, MouseInputListener {

    static final protected int KEY_RELEASED = 0;
    static final protected int KEY_JUST_PRESSED = 1;
    static final protected int KEY_PRESSED = 2;
    static private InputManager instance;

    HashMap<Integer, Integer> keyCache;
    private ArrayList<Integer> pressedKeys;
    private ArrayList<Integer> releasedKeys;

    private boolean mouseButton1;
    private boolean mouseButton2;
    private Point mousePos;

    private InputManager() {
        this.keyCache = new HashMap<Integer, Integer>();
        this.pressedKeys = new ArrayList<Integer>();
        this.releasedKeys = new ArrayList<Integer>();
        this.mousePos = new Point();
    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    //                          EVENTOS DO TECLADO                            //
    ////////////////////////////////////////////////////////////////////////////
    public boolean isPressed(int keyId) {
        return this.keyCache.containsKey(keyId) && this.keyCache.get(keyId).equals(this.KEY_PRESSED);
    }

    public boolean isJustPressed(int KeyId) {
        return this.keyCache.containsKey(KeyId) && this.keyCache.get(KeyId).equals(this.KEY_JUST_PRESSED);
    }

    public boolean isReleased(int KeyId) {
        return !this.keyCache.containsKey(KeyId) || this.keyCache.get(KeyId).equals(this.KEY_RELEASED);
    }

    public void update() {
        for (Integer keyCode : keyCache.keySet()) {
                if (isJustPressed(keyCode)) {
                    keyCache.put(keyCode, KEY_PRESSED);
                }
            }
            for (Integer keyCode : pressedKeys) {
                if (isReleased(keyCode)) {
                    keyCache.put(keyCode, KEY_JUST_PRESSED);
                } else {
                    keyCache.put(keyCode, KEY_PRESSED);
                }
            }
            for (Integer keyCode : releasedKeys) {
                keyCache.put(keyCode, KEY_RELEASED);
            }
            pressedKeys.clear();
            releasedKeys.clear();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.releasedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Rotina não utilizada
    }

    ////////////////////////////////////////////////////////////////////////////
    //                          EVENTOS DO MOUSE                              //
    ////////////////////////////////////////////////////////////////////////////
    public boolean isMousePressed(int buttonId) {
        if (buttonId == MouseEvent.BUTTON1) {
            return this.mouseButton1;
        }
        if (buttonId == MouseEvent.BUTTON2) {
            return this.mouseButton2;
        }
        return false;
    }

    public int getMouseX() {
        return (int) this.mousePos.getX();
    }

    public int getMouseY() {
        return (int) this.mousePos.getY();
    }

    public Point getMousePos() {
        return this.mousePos;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.mouseButton1 = true;
        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            this.mouseButton2 = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.mouseButton1 = false;
        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            this.mouseButton2 = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mousePos.setLocation(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mousePos.setLocation(e.getPoint());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Rotina não utilizada
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Rotina não utilizada
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Rotina não utilizada
    }

}
