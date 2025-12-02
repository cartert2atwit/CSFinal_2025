package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;         
import javafx.scene.image.Image;
import javafx.geometry.*;

public class Ship {
	
	    private Image shipImage;
	    private final double SHIP_SPEED = 5.0;
		private double x; 
		private double y;
		private double width = 50; 
		private double height = 50; 
		private boolean alive;
		private int health;
        

        private boolean movingLeft = false;
        private boolean movingRight = false;
		
        
	    public Ship(double initialX, double initialY) {
	     
	        
	        x = initialX;
	        y = initialY;
	        
	        
	        health = 3;
            alive = true;
            
            try {
        	    
    	        this.shipImage = new Image("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQYAAADBCAMAAAAace62AAAAhFBMVEUAAAAg/v89//4SGBoDAABH8PNB9vYRFRgg//4AAAITGhwAAwACAAIh/f8SFxsDAQBI9vkWJihG+/kAAQlm/Pc///oWJShF+P4iLDM3/Pki/voXKiocJykSBwBL/P8h//gbHiYRGxpl8OxG9fFC8PZl+fwSIyQPAAFe/fJe//8XFyATGRbEv8jfAAAEmUlEQVR4nO2dbWOaPBSGE3q0yRIKbUeZbKPb7PPS9f//vyVHUNFtAkWCcl8f1lmrJJcnIbfxRQgAAAAAAAAAAAAAAAAAAAAAAAAAAADAKXToBkwAa7XV2oZuRmiIBGlBQm+w1hmZoRTXdRLbgaH5op3hMNGuFujxsbqUJIIMBW1QGIi0NZZ2JH6gzA43Q4qDbhsTpikBocQkJD6mD0yZ59nHTzQ/DX52JPGglHQoz+o+dIuCUGnYEs1bg6zqIfocukVB2NegoGFbDRgU89KgmxdmOTccZspHe6xhBqHiIFOSdYulTG6nSOkGRX3lNefNZqbkTCGi+kxROA2fd38prjhvNjOl6yaJrKgGhauH6EnUi+mrzpuJz5R1nrQ+XIq8GhQylir6QmYOedPNkMlB19KqGpSMVfa1ed2V5k0ynCnLqKIsoyyuNTwXMs72rspev11t3vTnieV2MthGy/pUsUtZ7r+ru9CtPRuVhr3Oyhp1SPQhdGvPRq1hVw37+N/vamMuGv7CnKrhhAdogIY5aNCtNchr1tC+GqABGmaiAXPDBmhgoMHTflBc9boBy6cN0MBAgwdzw4Yr00B+I6X70+cdqkHK6WuwTkOik863M5Q4DUWrdYOMvlPn3TsiS343pHPLeuE0aNKm2ov0B9WbPbnGj+PfW7812UZDzBp4D6/1nfsfZLWxSffHp6cGNyqs7jwoKLGuY0vZphoKWd52b5h/THo0rB9aJ65ejah22Trc0vkTK3V6clDqWUU99ik2m4J6nG0eP9PZ3hutyxYaZFw8r/oegPRYO+Hk991/LNJ00YWXxWK5Xsi41QlTxovFutPd+yO8vPxzP+brIpyG6Gij6RSF/ycuTluIY//XxztZLY6Q/TvePrgWzV241pwUsK2GvkT3o75wqJeGthYuSsPxLuSQdJC2f6MxNfCgKLs8vCPBrxwatxoepqhBjj4o5qihmSm1XxBOVMPTXquHXkEcZEp+SeNqihqK6Inqh2v4vNnIlNa/wlVMdYq8ZwnnyZsuE9lddLNCO8/59DS4VWTpFtPVWBg+bz42MiW5I03zhCljVw310zXD583fZcpsghaUyv5rtnLQvKk3mXJZpp6H9XKdLuQEqyGWcbosc25lmuf564ehzxZ6L0X4la5q9ZziyLgAu4q3+cLlzdth8+ZRpgzd4d9zFLRuh11MHWoI3d8/MJaG0P08yX4Tz1gN4TrYHbe0hgZoqIAGBhoYaGCggYEGBhoYaGCggYEGBhoYaGCggYEGBhoYaGCggYEGBhoYaGCggYEGBhoYaGCggYEGBhoYaGCggYEGBhoYaGCggYEGBhoYaGCggfEahn37/iVqiJ2GG2iABgYamFgpaICGijNosNpcngbp5oZh1w38Ifr50fvjJ44s3wb9lguymkikm/t+x8cKjERVDn5QDPteK+01rEN3rxtucoh6fFzQX/DvvSTxf3RpvN4N/WUfxtDPG8fdzfS5Y27e3t7ImAFHBQlL/lsz6LIQg9eCFmT8u77H/IyId+LLgC6qxQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHgnvwAy5YvwT9YlwgAAAABJRU5ErkJggg==");
    	    } catch (Exception e) {
    	        System.err.println("Failed to load image with simple filename: " + e.getMessage());
    	    }
	    }
	    
        
        public void update(double gameWidth) {
            
            if (movingLeft) {
                x -= SHIP_SPEED;
            }
            if (movingRight) {
                x += SHIP_SPEED;
            }

           
            if (x < width / 2) {
                x = width / 2;
            }
            if (x > gameWidth - (width / 2)) {
                x = gameWidth - (width / 2);
            }
        }

	    // 
        public void draw(GraphicsContext gc) {
            double drawX = x - (width / 2); 
            if (alive) {
            	if(this.shipImage !=null) {
            		 gc.setFill(Color.BLUE); 
                     gc.fillRect(drawX, y, width, height); 
           	}
           	gc.drawImage(shipImage, drawX,y,width, height);
            
            }
        
	    }
	    
       
        public void setMovingLeft(boolean movingLeft) {
            this.movingLeft = movingLeft;
        }

        public void setMovingRight(boolean movingRight) {
            this.movingRight = movingRight;
        }
        
      
	    public Rectangle2D getBoundary() {
	    	
	    	return new Rectangle2D(x - (width / 2), y, width, height);
	    }

        public double getX() { 
            return x; //To know where to spawn the bullet
        }
        
        public double getY() { 
            return y; 
        }

	    public boolean isHit(Alien alien, Bullet bullet) {
	    	
            return false;
	    }
}

