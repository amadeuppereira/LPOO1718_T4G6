import com.fr.funrungame.model.GameModel;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class GameLogicTester {

    @Test
    public void mapLoaderTest() {
        GameModel.setCurrentMap(1);
        GameModel model = GameModel.getInstance();

        assertFalse(model.isFinished());

        assertNotNull(model.getPlayers());
        assertNotNull(model.getEndline());
        assertNotNull(model.getEnemies());
        assertNotNull(model.getPlatforms());
        assertNotNull(model.getPowerUps());
    }

}
