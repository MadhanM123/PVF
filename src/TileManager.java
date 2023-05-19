import java.awt.Graphics;
import java.awt.Graphics2D;

public class TileManager {
    private Tile[][] tiles;

    public TileManager(Tile[][] tiles){
        this.tiles = tiles;
        setTiles();
    }

    private void setTiles(){
        for(int r = 0; r < tiles.length; r++){
            for(int c = 0; c < tiles[0].length; c++){
                tiles[r][c] = new Tile(r, c);
            }
        }
    }

    public void draw(Graphics g){
        int x = 0;
        int y = 0;

        for(int r = 0; r < tiles.length; r++){
            for(int c = 0; c < tiles.length; c++){
                g.drawImage(Tile.TILE_IMAGE, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
                x += Tile.TILE_SIZE;
            }
            y += Tile.TILE_SIZE;
        }
    }
}
