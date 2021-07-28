/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Core.ImageManager;
import Core.InputManager;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Seven
 */
public class EntidadeJogador extends Entidade {

    public int contador = 0;

    private int contadorAndando = 0;

    private int estadoComportamento = 0;
    private int estadoFace = 0;

    public static final int ESTADO_PARADO = 0;
    public static final int ESTADO_ANDANDO = 1;

    public static final int FACE_BAIXO = 0;
    public static final int FACE_ESQUERDA = 1;
    public static final int FACE_CIMA = 2;
    public static final int FACE_DIREITA = 3;

    public EntidadeJogador(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.camada = 3;
        this.IDSprite = 3;
    }

    @Override
    public void init() {
    }

    @Override
    public void update(int currentTick) {
        if (contador == 60) {
            if (estadoComportamento == ESTADO_PARADO) {
                if (InputManager.getInstance().isPressed(KeyEvent.VK_W)) {
                    if (Jogo.tileMapa[this.posX][this.posY].colisao[TileMapa.COLISAO_CIMA] != null && !Jogo.tileMapa[this.posX][this.posY].colisao[TileMapa.COLISAO_CIMA].isBlock()) {
                        Jogo.tileMapa[this.posX][this.posY - 1].setEntidade(this);
                        if (Jogo.tileMapa[this.posX][this.posY].removerEntidade(this)) {
                            this.posY -= 1;
                            estadoFace = FACE_CIMA;
                            estadoComportamento = ESTADO_ANDANDO;
                            contadorAndando = 0;
                        }
                    }
                    contador = 0;
                }
                if (InputManager.getInstance().isPressed(KeyEvent.VK_S)) {
                    if (Jogo.tileMapa[this.posX][this.posY].colisao[TileMapa.COLISAO_BAIXO] != null && !Jogo.tileMapa[this.posX][this.posY].colisao[TileMapa.COLISAO_BAIXO].isBlock()) {
                        Jogo.tileMapa[this.posX][this.posY + 1].setEntidade(this);
                        if (Jogo.tileMapa[this.posX][this.posY].removerEntidade(this)) {
                            this.posY += 1;
                            estadoFace = FACE_BAIXO;
                            estadoComportamento = ESTADO_ANDANDO;
                            contadorAndando = 0;
                        }
                    }
                    contador = 0;
                }
                if (InputManager.getInstance().isPressed(KeyEvent.VK_D)) {
                    if (Jogo.tileMapa[this.posX][this.posY].colisao[TileMapa.COLISAO_DIREITA] != null && !Jogo.tileMapa[this.posX][this.posY].colisao[TileMapa.COLISAO_DIREITA].isBlock()) {
                        Jogo.tileMapa[this.posX + 1][this.posY].setEntidade(this);
                        if (Jogo.tileMapa[this.posX][this.posY].removerEntidade(this)) {
                            this.posX += 1;
                            estadoFace = FACE_DIREITA;
                            estadoComportamento = ESTADO_ANDANDO;
                            contadorAndando = 0;
                        }
                    }
                    contador = 0;
                }
                if (InputManager.getInstance().isPressed(KeyEvent.VK_A)) {
                    if (Jogo.tileMapa[this.posX][this.posY].colisao[TileMapa.COLISAO_ESQUERDA] != null && !Jogo.tileMapa[this.posX][this.posY].colisao[TileMapa.COLISAO_ESQUERDA].isBlock()) {
                        Jogo.tileMapa[this.posX - 1][this.posY].setEntidade(this);
                        if (Jogo.tileMapa[this.posX][this.posY].removerEntidade(this)) {
                            this.posX -= 1;
                            estadoFace = FACE_ESQUERDA;
                            estadoComportamento = ESTADO_ANDANDO;
                            contadorAndando = 0;
                        }
                    }
                    contador = 0;
                }
            }
        }

        contador++;
        if (contador > 60) {
            contador = 60;
            estadoComportamento = ESTADO_PARADO;
        }

    }

    @Override
    public BufferedImage getImage() {
        if (estadoComportamento == ESTADO_ANDANDO) {
            if (estadoFace == FACE_BAIXO) {
                return ImageManager.getInstance().getSpriteMapa(IDSprite, 0, 0, 32, 32);
            } else if (estadoFace == FACE_ESQUERDA) {
                return ImageManager.getInstance().getSpriteMapa(IDSprite, 0, 64, 32, 32);
            } else if (estadoFace == FACE_CIMA) {
                return ImageManager.getInstance().getSpriteMapa(IDSprite, 0, 96, 32, 32);
            } else {
                return ImageManager.getInstance().getSpriteMapa(IDSprite, 0, 32, 32, 32);
            }
        } else {
            if (estadoFace == FACE_BAIXO) {
                return ImageManager.getInstance().getSpriteMapa(IDSprite, 0, 0, 32, 32);
            } else if (estadoFace == FACE_ESQUERDA) {
                return ImageManager.getInstance().getSpriteMapa(IDSprite, 0, 64, 32, 32);
            } else if (estadoFace == FACE_CIMA) {
                return ImageManager.getInstance().getSpriteMapa(IDSprite, 0, 96, 32, 32);
            } else {
                return ImageManager.getInstance().getSpriteMapa(IDSprite, 0, 32, 32, 32);
            }
        }
        //return ImageManager.getInstance().getSpriteMapa(IDSprite, 0, 0, 32, 32);
    }
}
