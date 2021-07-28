/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Manolo
 */
public class ImageManager {

    public HashMap<Integer, BufferedImage> listaImagensMapa;
    private static ImageManager instance;

    private ImageManager() {
        this.listaImagensMapa = new HashMap<>();
        this.carregarSpriteMapa();
    }

    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    private void carregarSpriteMapa() {
        for (int i = 0; i < 4; i++) {
            try {
                URL url = getClass().getResource("/imagens/" + i + ".png");
                BufferedImage img = ImageIO.read(url);
                listaImagensMapa.put(i, img);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar Map de imagens de mapa." + i + "\nERRO: " + e);
            }
        }

    }

    //Map de sprite de mapa 32 x 32 px
    public BufferedImage getSpriteMapa(int ID) {
        if (ID < 0) {
            throw new RuntimeException("ID imagem < 0");
        } else {
            try {
                return this.listaImagensMapa.get(ID);
            } catch (Exception e) {
                throw new RuntimeException("ID de imagem de mapa não encontrado/nID: " + ID + "/nERRO:" + e);
            }
        }
    }

    public BufferedImage getSpriteMapa(int ID, int posIniX, int posIniY, int posFinX, int posFinY) {
        BufferedImage aux = this.getSpriteMapa(ID);
        if (aux.getWidth() != 32) {
            try {
                BufferedImage img = aux.getSubimage(posIniX, posIniY, posFinX, posFinY);
                return img;
            } catch (Exception e) {
                throw new RuntimeException("SubImagem de ID: " + ID + "/ERRO:" + e);
            }
        }
        return aux;
    }

}
