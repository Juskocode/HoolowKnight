package HollowKnight.model.game.elements.enemies;

import HollowKnight.model.Vector;
import HollowKnight.model.game.elements.Element;
import HollowKnight.model.game.scene.Scene;

public abstract class Enemies extends Element {
    private int HP;
    private Scene scene;
    private int damage;
    public Enemies(int x, int y,int HP, Scene scene, int damage) {
        super(x,y);
        this.HP = HP;
        this.scene = scene;
        this.damage = damage;
    }
    public boolean isAlive(){
        return this.HP >0;
    }

    public int getHP() {
        return HP;
    }

    public Scene getScene() {
        return scene;
    }

    public int getDamage() {
        return damage;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    protected abstract Vector applyCollisions(Vector velocity);

}
