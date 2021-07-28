/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Core.ImageManager;
import java.awt.image.BufferedImage;

/**
 *
 * @author Manolo
 */
public class Entidade {

    //configuracoes iniciais
    protected int posX = 0;
    protected int posY = 0;
    protected int IDSprite = 0;
    protected boolean block = false;
    
    
    protected int camada = 0;

    public Entidade(int posx, int posy, int idImage, int camada) {
        this.posX = posX;
        this.posY = posY;
        IDSprite = idImage;
        this.camada = camada;
    }
    
    public Entidade(){
    }

    public void init() {
    }

    public void update(int CurrentTick) {
    }

    public BufferedImage getImage() {
        BufferedImage img;
        return img = ImageManager.getInstance().getSpriteMapa(IDSprite);
    }

    //Seters
    public void setIDSprite(int id) {
        this.IDSprite = id;
    }

    //geters
    protected int getPosX() {
        return posX;
    }

    protected int getPosY() {
        return posY;
    }

    protected int getSprite() {
        return this.IDSprite;
    }

    protected boolean getBlock() {
        return this.block;
    }

    protected int getCamada() {
        return this.camada;
    }

}
