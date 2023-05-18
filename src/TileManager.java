public class TileManager {
    private Tile[][] tiles;

    public TileManager(Tile[][] tiles){
        this.tiles = tiles;
        setTiles();
    }

    private void setTiles(){
        for(int r = 0; r < tiles.length; r++){
            for(int c = 0; c < tiles[0].length; c++){
                tiles[r][c] = new Tile(r, c, )
            }
        }
    }

    public void draw(){
        
    }
}
