package wraith.smithee.mixin;

import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wraith.smithee.Utils;

//ItemModels

@Mixin(ModelLoader.class)
public abstract class TestMixin {

    @Inject(method = "loadModelFromJson", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceManager;getResource(Lnet/minecraft/util/Identifier;)Lnet/minecraft/resource/Resource;"), cancellable = true)
    public void test(Identifier id, CallbackInfoReturnable<JsonUnbakedModel> cir) {
        String modelJson = Utils.createModelJson(id);
        if (modelJson != null && !"".equals(modelJson)) {
            JsonUnbakedModel model = JsonUnbakedModel.deserialize(modelJson);
            model.id = id.toString();
            cir.setReturnValue(model);
            cir.cancel();
        }
    }
}
