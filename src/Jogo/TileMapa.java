/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Manolo
 */
public class TileMapa {

    private final int posX;
    private final int posY;

    public ArrayList<Entidade> listaEntidades;
    public TileMapa[] colisao;

    public static final int COLISAO_BAIXO = 0;
    public static final int COLISAO_BAIXO_ESQUERDA = 1;
    public static final int COLISAO_ESQUERDA = 2;
    public static final int COLISAO_ESQUERDA_CIMA = 3;
    public static final int COLISAO_CIMA = 4;
    public static final int COLISAO_CIMA_DIREITA = 5;
    public static final int COLISAO_DIREITA = 6;
    public static final int COLISAO_DIREITA_BAIXO = 7;

    public TileMapa(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        listaEntidades = new ArrayList<>();
        colisao = new TileMapa[8];

        listaEntidades.add(new Entidade(this.posX, this.posY, 1, 1));
    }

    public void init() {

    }

    public void update(int currentTick) {
        if (listaEntidades.size() > 0) {
            for (int i = listaEntidades.size()-1;i >= 0;i--) {
                listaEntidades.get(i).update(currentTick);
            }
        }
    }

    public void render(Graphics2D g) {
        if (listaEntidades.size() > 0) {
            for (Entidade e : listaEntidades) {
                if (e.getCamada() == 1) {

                    g.drawImage(e.getImage(), posX * 32, posY * 32, 32, 32, null);
                }
            }
            for (Entidade e : listaEntidades) {
                if (e.getCamada() == 2) {
                    g.drawImage(e.getImage(), posX * 32, posY * 32, 32, 32, null);
                }
            }
            for (Entidade e : listaEntidades) {
                if (e.getCamada() == 3) {
                    g.setColor(Color.white);
                    g.drawRect(posX * 32, posY * 32, 31, 31);
                    
                    g.drawImage(e.getImage(), posX * 32, posY * 32, 32, 32, null);
                }
            }
        }
    }

    //Seters
    public void setEntidade(Entidade e) {
        listaEntidades.add(e);
    }

    public boolean removerEntidade(Entidade e) {
        if (listaEntidades.contains(e)) {
            listaEntidades.remove(e);
            return true;
        }
        return false;
    }

    //Geters
    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public boolean isBlock() {
        if (listaEntidades.size() > 0) {
            for (Entidade e : listaEntidades) {
                if (e.getBlock()) {
                    return true;
                }
            }
        }
        return false;
    }

}
