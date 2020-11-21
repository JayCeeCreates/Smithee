package wraith.smithee;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wraith.smithee.registry.BlockEntityRegistry;
import wraith.smithee.registry.BlockRegistry;
import wraith.smithee.registry.ItemRegistry;
import wraith.smithee.registry.ScreenHandlerRegistry;

public class Smithee implements ModInitializer {

    public static final String MOD_ID = "smithee";
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        LOGGER.info("[Smithee] is loading.");

        ItemRegistry.addItems();
        ItemRegistry.registerItems();
        BlockRegistry.registerBlocks();
        BlockEntityRegistry.addBlockEntities();
        BlockEntityRegistry.registerBlockEntities();
        ScreenHandlerRegistry.registerScreenHandlers();

        LOGGER.info("[Smithee] has successfully been loaded.");
    }


}
