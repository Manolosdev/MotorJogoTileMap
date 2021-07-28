/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Core.DataManager;
import Core.Game;
import Core.ImageManager;
import java.awt.Graphics2D;

/**
 *
 * @author Seven
 */
public class Jogo extends Game {

    private int mapaLargura = 0;
    private int mapaAltura = 0;
    public static TileMapa[][] tileMapa;
    public EntidadeJogador jogador;

    public Jogo() {
        super();
        showFPS = true;
        ImageManager.getInstance();
        //Carregar mapa de tile
        carregarMapa("mapa1");
        jogador = new EntidadeJogador(7,5);

        tileMapa[jogador.getPosX()][jogador.getPosY()].setEntidade(jogador);

    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onUnload() {
    }

    @Override
    public void onUpdate(int currentTick) {
        for (int linha = 0; linha < mapaLargura; linha++) {
            for (int coluna = 0; coluna < mapaAltura; coluna++) {
                tileMapa[linha][coluna].update(currentTick);
            }
        }
    }

    @Override
    public void onRender(Graphics2D g) {
        for (int linha = 0; linha < mapaLargura; linha++) {
            for (int coluna = 0; coluna < mapaAltura; coluna++) {
                tileMapa[linha][coluna].render(g);
            }
        }
    }

    public void carregarMapa(String fileName) {
        try {
            DataManager dm = new DataManager(getClass().getResource("/mapas/" + fileName).toURI());
            this.mapaLargura = dm.read("mapaLargura", mapaLargura);
            this.mapaAltura = dm.read("mapaAltura", mapaAltura);
            tileMapa = new TileMapa[mapaLargura][mapaAltura];
            for (int largura = 0; largura < mapaLargura; largura++) {
                for (int altura = 0; altura < mapaAltura; altura++) {
                    tileMapa[largura][altura] = new TileMapa(largura, altura);
                    tileMapa[largura][altura].setEntidade(new Entidade(largura, altura, 1, 1));
                }
            }
            int qtdEntidades = 0;
            qtdEntidades = dm.read("qtdEntidade", qtdEntidades);
            for (int i = 1; i <= qtdEntidades; i++) {
                int posX = 0;
                int posY = 0;
                int idSprite = 0;
                int camada = 0;
                posX = dm.read("E." + i + ".posX", posX);
                posY = dm.read("E." + i + ".posY", posY);
                idSprite = dm.read("E." + i + ".IDSprite", idSprite);
                camada = dm.read("E." + i + ".camada", camada);

                tileMapa[posX][posY].setEntidade(new Entidade(posX, posY, idSprite, camada));
            }
        } catch (Exception e) {
            System.out.println("Erro:\n" + e);
        }
        //Carregar colisoes tileMapa
        for (int linha = 0; linha < mapaLargura; linha++) {
            for (int coluna = 0; coluna < mapaAltura; coluna++) {
                if (linha == 0) {
                    tileMapa[linha][coluna].colisao[TileMapa.COLISAO_ESQUERDA] = null;
                    tileMapa[linha][coluna].colisao[TileMapa.COLISAO_ESQUERDA_CIMA] = null;
                    tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO_ESQUERDA] = null;
                    if (coluna == 0) {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA] = null;
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA_DIREITA] = null;
                    } else {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA] = tileMapa[linha][coluna - 1];
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA_DIREITA] = tileMapa[linha + 1][coluna - 1];
                    }
                    if (coluna == mapaAltura - 1) {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO] = null;
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_DIREITA_BAIXO] = null;
                    } else {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO] = tileMapa[linha][coluna + 1];
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_DIREITA_BAIXO] = tileMapa[linha + 1][coluna + 1];
                    }
                    tileMapa[linha][coluna].colisao[TileMapa.COLISAO_DIREITA] = tileMapa[linha + 1][coluna];
                } else if (linha == mapaLargura - 1) {
                    tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA_DIREITA] = null;
                    tileMapa[linha][coluna].colisao[TileMapa.COLISAO_DIREITA] = null;
                    tileMapa[linha][coluna].colisao[TileMapa.COLISAO_DIREITA_BAIXO] = null;
                    if (coluna == 0) {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA] = null;
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_ESQUERDA_CIMA] = null;
                    } else {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA] = tileMapa[linha][coluna - 1];
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_ESQUERDA_CIMA] = tileMapa[linha - 1][coluna - 1];
                    }
                    if (coluna == mapaAltura - 1) {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO] = null;
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO_ESQUERDA] = null;
                    } else {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO] = tileMapa[linha][coluna + 1];
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO_ESQUERDA] = tileMapa[linha - 1][coluna + 1];
                    }
                    tileMapa[linha][coluna].colisao[TileMapa.COLISAO_ESQUERDA] = tileMapa[linha - 1][coluna];
                } else {
                    if (coluna == 0) {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA] = null;
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA_DIREITA] = null;
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_ESQUERDA_CIMA] = null;
                    } else {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA] = tileMapa[linha][coluna - 1];
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_CIMA_DIREITA] = tileMapa[linha + 1][coluna - 1];
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_ESQUERDA_CIMA] = tileMapa[linha - 1][coluna - 1];
                    }
                    if (coluna == mapaAltura - 1) {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO] = null;
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO_ESQUERDA] = null;
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_DIREITA_BAIXO] = null;
                    } else {
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO] = tileMapa[linha][coluna + 1];
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_BAIXO_ESQUERDA] = tileMapa[linha - 1][coluna + 1];
                        tileMapa[linha][coluna].colisao[TileMapa.COLISAO_DIREITA_BAIXO] = tileMapa[linha + 1][coluna + 1];
                    }
                    tileMapa[linha][coluna].colisao[TileMapa.COLISAO_ESQUERDA] = tileMapa[linha - 1][coluna];
                    tileMapa[linha][coluna].colisao[TileMapa.COLISAO_DIREITA] = tileMapa[linha + 1][coluna];
                }

            }
        }

    }
}
