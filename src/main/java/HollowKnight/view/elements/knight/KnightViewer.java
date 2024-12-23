package HollowKnight.view.elements.knight;

import HollowKnight.gui.GUI;

import HollowKnight.model.game.elements.Knight.*;
import HollowKnight.view.elements.ElementViewer;
import HollowKnight.view.elements.ParticleViewer;
import HollowKnight.view.elements.knight.knightStates.*;
import HollowKnight.view.sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class KnightViewer implements ElementViewer<Knight> {

    private final List<StateAnimation> animations;

    public KnightViewer() throws IOException {
        this.animations = new ArrayList<>();

        // Initialize each animation state
        animations.add(new DashAnimation(DashState.class, 10));
        animations.add(new AfterDashAnimation(AfterDashState.class, 5));
        animations.add(new DamagedAnimation(DamagedState.class, 10));
        animations.add(new IdleAnimation(IdleState.class, 8));
        animations.add(new MaxVelocityAnimation(MaxVelocityState.class, 10));
        animations.add(new RunningAnimation(RunningState.class, 5));
        animations.add(new WalkingAnimation(WalkingState.class, 5));
        animations.add(new FallingAnimation(FallingState.class, 10));
        animations.add(new JumpAnimation(JumpState.class, 6));
        animations.add(new RespawnAnimation(RespawnState.class, 0)); // No animation
        // Load animations for each state

        for (StateAnimation animation : animations) {
            animation.loadAnimation("sprites/Knight"); // Base path to sprites
        }
    }

    @Override
    public void draw(Knight model, GUI gui, long time, int offsetX, int offsetY) throws IOException {
        StateAnimation animation = findAnimationForState(model.getState().getClass());
        if (animation != null) {
            Sprite sprite = animation.getSprite(time, model.isFacingRight());
            if (sprite != null) {
                int offSetX = 4;
                int offSetY = 1;
                sprite.draw(gui, (int) model.getPosition().x() - offSetX, (int) model.getPosition().y() - offSetY);
            }
        }
    }

    private StateAnimation findAnimationForState(Class<?> stateClass) {
        for (StateAnimation animation : animations) {
            if (animation.getState() == stateClass) {
                System.out.println(animation.getState().getSimpleName());
                return animation;
            }
        }
        return null;
    }

}
