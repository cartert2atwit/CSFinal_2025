package application;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;         
import java.util.Random;
import javafx.scene.image.Image;
import javafx.geometry.*;
public class Alien {
	private double x;

	private double y;

	private double width;

	private double height;

	private boolean alive;
	
	private Image alienImage;
	public Alien() {
	    // 1. Initialize Random object
	    Random random = new Random();

	    // 2. Set Random Size (e.g., between 40 and 60 pixels)
	    this.width = random.nextDouble() * 20 + 40;
	    this.height = this.width; // Keep it square

	    // 3. Set Random Position (Must be within the bounds of your game window)
	    // NOTE: Replace 800 with your actual game width
	    this.x = random.nextDouble() * (800 - this.width);
	    this.y = random.nextDouble() * 100; // Start near the top
	    
	    this.alive = true; 
	  
	    try {
	        // JavaFX may automatically look in the classpath for resources
	        this.alienImage = new Image("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQcAAAC/CAMAAADEm+k5AAAAflBMVEV2/wMAAAB6/wM2dAFh0gN0+gNu7gMdPgENHQE+hgJdyQMpWQFy9gMoVwEYNQFo4gNJngI6fAFFlQJRsAJHmgIQIgEmUQFk2QMiSQErXgEMGgE7gAIDBwBi0wNNpgIWLwAHDwBr5gNbxQMRJQEiSgAzbgFBjAEwaAEcPAAJFABBWC5GAAADB0lEQVR4nO3d21ITQRhF4WFMOIejECSigMrh/V/QkmV51Vvr155KYq11Hbadj3AzU2OGnWLLWau7WXWnU5/umudZ3hR3huo/PA7tqjudOmyfZzwq7vRymFd3OnXYPo4OOuiggw4/04F0IB1IB9KBdCAdSAfSgXQgHUgH0oF0IB1ofQ7t1ne9ut2H4k75/Ff7rW4/D/Nmp9X90GV7f1jdN89z9bG43+v3eBv+Xh467V+G+xTnnfZ7OewHh36fBx1+pAPpQDqQDqQD6UA6kA6kA+lAOpAOpAPpQDqQDqQD6UDb4pCu0/63DuGCePo8PPzb8X5VdKhetu92Hyc9jjCk1xerzk9+HyeeaKNa2/3NDUsH0oF0IB1IB9KBdCAdSAfSgXQgHUgH0oF0IB1IB9KBdCAdaFz0crheNLtJDqftHjq9sd2wfxwcVuH8i8VR895GdJjXbiSk53H2+nx+xouw/5h+IPel5BCgU8nhupPD+7Afnkv63dSVDm9TOjClA1M6MKUDUzowpQNTOjClA1M6MKUDUzowpQNTOjClA1M6MKUDUzowpQNTOjBVc9itrSeHrxNfr/4Lh/umw7eDZufVz8N5e+es032fx1VzfrUsL41PraFhWXzgIc532inu91oaZr0OuuXpQDqQDqQD6UA6kA6kA+lAOpAOpAPpQDqQDqQD6UA6kA6kAyWHqa8/T131+MFh/HTW7GKiY/fuMhx/nn4gOTSfUtjZmudxrsPx4+2p5HCy3Q7p+SwddNBBBx3e0oF0IB1IB9KBdCAdSAfSgXQgHUgH0oF0IB1ofQ7pfsfEbZrD/PndOnoOz1+szyG8fOqq3+euA+lAOpAOpAPpQDqQDqQD6UA6kA6kA+lAOpAOpAPpQDqQDqQDRYf0/wVFh3AfYao3+ocOw3nS9zXH5zJO9tqlb8Buv/rl5ni31GOYf6rtHB+E87+G/Zfwdnv9Hl9r9/vGWdi5KO5s2vfap+9zT+mggw466KCDDjroQDqQDqQD6UA6kA6kA+lAOpAOpAPpQNvu8B0MppqP1eayIgAAAABJRU5ErkJggg==");
	    } catch (Exception e) {
	        System.err.println("Failed to load image with simple filename: " + e.getMessage());
	    }
	}
public void draw(GraphicsContext gc) {
		if(this.alienImage !=null) {
			gc.drawImage(alienImage, x,y,width, height);
		}
	}

public void update(double deltaTime) {
	this.y+= 30* deltaTime;
}

public Rectangle2D getBoundary()
{
	return new Rectangle2D(x,y,width,height);
}

public boolean isHit(Bullet bullet) {
	

Rectangle2D alienBoundary = this.getBoundary();
Rectangle2D bulletBoundary = bullet.getBoundary(); // Bullet class must also have this method!

// 2. Check for intersection (the core of collision detection)
if (alienBoundary.intersects(bulletBoundary)) {
    
    // 3. Collision is detected
    this.alive = false; // Mark alien for removal
  

    return true;
}

return false; // No collision
}
}
