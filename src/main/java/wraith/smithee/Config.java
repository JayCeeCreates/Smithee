package wraith.smithee;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Config {

    public static void createMaterials(boolean overwrite) {
        HashSet<String> materials = new HashSet<String>(){{
            add("oak");
            add("spruce");
            add("acacia");
            add("dark_oak");
            add("jungle");
            add("birch");
            add("warped");
            add("crimson");
            add("stone");
            add("granite");
            add("andesite");
            add("diorite");
            add("basalt");
            add("blackstone");
            add("end_stone");
            add("prismarine");
            add("bamboo");
            add("bone");
            add("flint");
            add("ice");
            add("cactus");
            add("netherrack");
            add("golden");
            add("iron");
            add("diamond");
            add("netherite");
        }};
        if (FabricLoader.getInstance().isModLoaded("mythicmetals")) {
            materials.add("adamantite");
            materials.add("aetherium");
            materials.add("aquarium");
            materials.add("argonium");
            materials.add("banglum");
            materials.add("brass");
            materials.add("bronze");
            materials.add("carmot");
            materials.add("celestium");
            materials.add("copper");
            materials.add("discordium");
            materials.add("durasteel");
            materials.add("electrum");
            materials.add("etherite");
            materials.add("ferrite");
            materials.add("kyber");
            materials.add("metallurgium");
            materials.add("midas_gold");
            materials.add("mythril");
            materials.add("orichalcum");
            materials.add("osmium");
            materials.add("platinum");
            materials.add("prometheum");
            materials.add("quadrillum");
            materials.add("quicksilver");
            materials.add("runite");
            materials.add("silver");
            materials.add("slowsilver");
            materials.add("starrite");
            materials.add("steel");
            materials.add("stormyx");
            materials.add("tantalite");
            materials.add("tin");
            materials.add("truesilver");
            materials.add("ur");
        }
        if (FabricLoader.getInstance().isModLoaded("astromine-foundations")) {
            materials.add("copper");
            materials.add("tin");
            materials.add("silver");
            materials.add("lead");
            materials.add("bronze");
            materials.add("steel");
            materials.add("electrum");
            materials.add("rose_gold");
            materials.add("sterling_silver");
            materials.add("fools_gold");
            materials.add("metite");
            materials.add("asterite");
            materials.add("stellum");
            materials.add("galaxium");
            materials.add("univite");
            materials.add("lunum");
            materials.add("meteoric_steel");
        }

        String defaultMaterials =
            "{\n" +
            "  \"materials\": [\n" ;

        Iterator<String> it = materials.iterator();
        while (it.hasNext()) {
            String material = it.next();
            defaultMaterials += "    \"" + material + "\"";
            if (it.hasNext()) {
                defaultMaterials += ",";
            }
            defaultMaterials += "\n";
        }
        defaultMaterials +=
                "  ]\n" +
                "}";
        String path = "config/smithee/materials.json";
        Config.createFile(path, defaultMaterials, overwrite);
    }

    public static JsonObject loadConfig() {
        String defaultConfig =
                        "{\n" +
                        "  \"disable_vanilla_tools\": false,\n" +
                        "\n" +
                        "  \"regenerate_deleted_stat_files\": true,\n" +
                        "  \"replace_old_stat_files_when_regenerating\": false,\n" +
                        "  \"regenerate_deleted_recipe_files\": true,\n" +
                        "  \"replace_old_recipe_files_when_regenerating\": false,\n" +
                        "  \"regenerate_deleted_smithing_files\": true,\n" +
                        "  \"replace_old_smithing_files_when_regenerating\": false,\n" +
                        "  \"regenerate_deleted_combination_files\": true,\n" +
                        "  \"replace_old_combination_files_when_regenerating\": false,\n" +
                        "  \"regenerate_deleted_palettes\": true,\n" +
                        "  \"replace_old_palettes_when_regenerating\": false,\n" +
                        "  \"regenerate_deleted_texture_files\": true,\n" +
                        "  \"replace_old_texture_files_when_regenerating\": false,\n" +
                        "  \"regenerate_material_list\": true,\n" +
                        "  \"replace_material_list_when_regenerating\": false\n" +
                        "}";
        String path = "config/smithee/config.json";
        Config.createFile(path, defaultConfig, false);
        return getJsonObject(readFile(new File(path)));
    }

    public static void createFile(String path, String contents, boolean overwrite) {
        File file = new File(path);
        if (file.exists() && !overwrite) {
            return;
        }
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.setReadable(true);
        file.setWritable(true);
        file.setExecutable(true);
        if (contents == null || "".equals(contents)) {
            return;
        }
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(contents);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(File file) {
        String output = "";
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\Z");
            output = scanner.next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static JsonObject getJsonObject(String json) {
        try {
            return new JsonParser().parse(json).getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
            Smithee.LOGGER.error("Error while parsing following json:\n\n" + json);
            return null;
        }
    }

    public static File[] getFiles(String path) {
        return new File(path).listFiles();
    }

}
