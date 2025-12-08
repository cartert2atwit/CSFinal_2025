package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Alien {

    private double x;
    private double y;
    private final double width = 40;
    private final double height = 30;
    private boolean isAlive = true;
    private Image alienImage;

    // --------------------------
    // CONSTRUCTOR
    // --------------------------
    public Alien(double x, double y) {
        this.x = x;
        this.y = y;

        try {
            this.alienImage = new Image(
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQcAAAC/CAMAAADEm+k5AAAAflBMVEV2/wMAAAB6/wM2dAFh0gN0+gNu7gMdPgENHQE+hgJdyQMpWQFy9gMoVwEYNQFo4gNJngI6fAFFlQJRsAJHmgIQIgEmUQFk2QMiSQErXgEMGgE7gAIDBwBi0wNNpgIWLwAHDwBr5gNbxQMRJQEiSgAzbgFBjAEwaAEcPAAJFABBWC5GAAADB0lEQVR4nO3d21ITQRhF4WFMOIejECSigMrh/V/QkmV51Vvr155KYq11Hbadj3AzU2OGnWLLWau7WXWnU5/umudZ3hR3huo/PA7tqjudOmyfZzwq7vRymFd3OnXYPo4OOuiggw4/04F0IB1IB9KBdCAdSAfSgXQgHUgH0oF0IB1ofQ7t1ne9ut2H4k75/Ff7rW4/D/Nmp9X90GV7f1jdN89z9bG43+v3eBv+Xh467V+G+xTnnfZ7OewHh36fBx1+pAPpQDqQDqQD6UA6kA6kA+lAOpAOpAPpQDqQDqQD6UDb4pCu0/63DuGCePo8PPzb8X5VdKhetu92Hyc9jjCk1xerzk9+HyeeaKNa2/3NDUsH0oF0IB1IB9KBdCAdSAfSgXQgHUgH0oF0IB1IB9KBdCAdaFz0crheNLtJDqftHjq9sd2wfxwcVuH8i8VR895GdJjXbiSk53H2+nx+xouw/5h+IPel5BCgU8nhupPD+7Afnkv63dSVDm9TOjClA1M6MKUDUzowpQNTOjClA1M6MKUDUzowpQNTOjClA1M6MKUDUzowpQNTOjBVc9itrSeHrxNfr/4Lh/umw7eDZufVz8N5e+es032fx1VzfrUsL41PraFhWXzgIc532inu91oaZr0OuuXpQDqQDqQD6UA6kA6kA+lAOpAOpAPpQDqQDqQD6UA6kA6kAyWHqa8/T131+MFh/HTW7GKiY/fuMhx/nn4gOTSfUtjZmudxrsPx4+2p5HCy3Q7p+SwddNBBBx3e0oF0IB1IB9KBdCAdSAfSgXQgHUgH0oF0IB1ofQ7pfsfEbZrD/PndOnoOz1+szyG8fOqq3+euA+lAOpAOpAPpQDqQDqQD6UA6kA6kA+lAOpAOpAPpQDqQDqQDRYf0/wVFh3AfYao3+ocOw3nS9zXH5zJO9tqlb8Buv/rl5ni31GOYf6rtHB+E87+G/Zfwdnv9Hl9r9/vGWdi5KO5s2vfap+9zT+mggw466KCDDjroQDqQDqQD6UA6kA6kA+lAOpAOpAPpQNvu8B0MppqP1eayIgAAAABJRU5ErkJggg=="
            );
        } catch (Exception e) {
            System.err.println("Failed to load alien image: " + e.getMessage());
        }
    }

    // --------------------------
    // DRAW
    // --------------------------
    public void draw(GraphicsContext gc) {
        if (!isAlive) return;

        if (alienImage != null) {
            gc.drawImage(alienImage, x, y, width, height);
        } else {
            gc.setFill(Color.LIMEGREEN);
            gc.fillRect(x, y, width, height);
        }
    }

    // --------------------------
    // MOVEMENT
    // --------------------------
    public void moveX(double dx) { x += dx; }
    public void moveDown(double dy) { y += dy; }

    // --------------------------
    // COLLISION
    // --------------------------
    public Rectangle2D getBoundary() { return new Rectangle2D(x, y, width, height); }

    public boolean isHit(Bullet bullet) {
        if (!isAlive) return false;
        if (getBoundary().intersects(bullet.getBoundary())) {
            isAlive = false;
            return true;
        }
        return false;
    }

    // --------------------------
    // GETTERS / SETTERS
    // --------------------------
    public boolean isAlive() { return isAlive; }
    public double getX() { return x; }
    public double getY() { return y; }
    public void setAlive(boolean alive) { this.isAlive = alive; }
}