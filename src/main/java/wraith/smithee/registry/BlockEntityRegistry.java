package wraith.smithee.registry;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import wraith.smithee.blocks.ChiselingTableBlockEntity;
import wraith.smithee.utils.Utils;
import wraith.smithee.blocks.AassemblyTableBlockEntity;
import wraith.smithee.blocks.DisassemblyTableBlockEntity;

import java.util.HashMap;

public class BlockEntityRegistry {

    public static HashMap<String, BlockEntityType<? extends BlockEntity>> BLOCK_ENTITIES = new HashMap<>();

    public static void addBlockEntities() {
        BLOCK_ENTITIES.put("assembly_table", BlockEntityType.Builder.create(AassemblyTableBlockEntity::new,
                BlockRegistry.BLOCKS.get("oak_assembly_table"),
                BlockRegistry.BLOCKS.get("dark_oak_assembly_table"),
                BlockRegistry.BLOCKS.get("spruce_assembly_table"),
                BlockRegistry.BLOCKS.get("birch_assembly_table"),
                BlockRegistry.BLOCKS.get("jungle_assembly_table"),
                BlockRegistry.BLOCKS.get("acacia_assembly_table")
        ).build(null));
        BLOCK_ENTITIES.put("disassembly_table", BlockEntityType.Builder.create(DisassemblyTableBlockEntity::new,
                BlockRegistry.BLOCKS.get("stone_disassembly_table"),
                BlockRegistry.BLOCKS.get("cobblestone_disassembly_table"),
                BlockRegistry.BLOCKS.get("mossy_cobblestone_disassembly_table"),
                BlockRegistry.BLOCKS.get("diorite_disassembly_table"),
                BlockRegistry.BLOCKS.get("andesite_disassembly_table"),
                BlockRegistry.BLOCKS.get("granite_disassembly_table")
        ).build(null));
        BLOCK_ENTITIES.put("chiseling_table", BlockEntityType.Builder.create(ChiselingTableBlockEntity::new,
                BlockRegistry.BLOCKS.get("oak_chiseling_table"),
                BlockRegistry.BLOCKS.get("dark_oak_chiseling_table"),
                BlockRegistry.BLOCKS.get("spruce_chiseling_table"),
                BlockRegistry.BLOCKS.get("birch_chiseling_table"),
                BlockRegistry.BLOCKS.get("jungle_chiseling_table"),
                BlockRegistry.BLOCKS.get("acacia_chiseling_table")
        ).build(null));
    }

    public static void registerBlockEntities() {
        for (String id : BLOCK_ENTITIES.keySet()) {
            Registry.register(Registry.BLOCK_ENTITY_TYPE, Utils.ID(id), BLOCK_ENTITIES.get(id));
        }
    }

}
