
package ija.game.board;

import java.util.Objects;



/**
 *
 * @author Jan
 */
public class MazeCard {
    
    private CANGO direction1;
    private CANGO direction2;
    private CANGO direction3;
    private String type;
    
/*    
    @Override
    public int hashCode(){
        return this.type.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MazeCard other = (MazeCard) obj;
        if (this.direction1 != other.direction1) {
            return false;
        }
        if (this.direction2 != other.direction2) {
            return false;
        }
        if (this.direction3 != other.direction3) {
            return false;
        }
        return Objects.equals(this.type, other.type);
    }
  */  
    public static enum CANGO{
       LEFT, UP, RIGHT, DOWN; 
    }
    
    public String get_type(){
        return this.type;
    }
    
    private MazeCard(String type){
       
        this.type = type;
        
        switch (type){
         
            case "C":
                this.direction1 = CANGO.LEFT;
                this.direction2 = CANGO.UP;
                break;
                
            case "L":
                this.direction1 = CANGO.LEFT;
                this.direction2 = CANGO.RIGHT;
                break;
            
            case "F":
                this.direction1 = CANGO.LEFT;
                this.direction2 = CANGO.UP;
                this.direction3 = CANGO.RIGHT;
                break;
                
            default:
                throw new IllegalArgumentException(type);
            }   
    }
    
    
    public static MazeCard create(String type){ 
        return new MazeCard(type);
    } 
    
    public boolean canGo(MazeCard.CANGO dir){
        
        if (dir.equals(this.direction1)||
            dir.equals(this.direction2))
            return true;
        
        if ("F".equals(this.type)){
            if (dir.equals(this.direction3))
                return true;    
        }
        return false;
    }
    
    private MazeCard.CANGO turnDirection(MazeCard.CANGO dir){
        
        switch (dir){
                    
                case LEFT:
                    dir = CANGO.UP;
                    break;
                  
                case RIGHT:
                    dir = CANGO.DOWN;
                    break;
                   
                case UP:
                    dir = CANGO.RIGHT;
                    break;
                    
                case DOWN:
                    dir = CANGO.LEFT;
                    break;
                    
                default:
                    break;
                        
                }
        
        return dir;
        
    }
    
    public void turnRight(){
        
       this.direction1 = turnDirection(this.direction1);
       this.direction2 = turnDirection(this.direction2);
       
       if ("F".equals(this.type))
         this.direction3 = turnDirection(this.direction3);    
       
    }
}
