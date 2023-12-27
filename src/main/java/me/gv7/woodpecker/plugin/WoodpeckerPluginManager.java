package me.gv7.woodpecker.plugin;

public class WoodpeckerPluginManager implements IPluginManager{
    @Override
    public void registerPluginManagerCallbacks(IPluginManagerCallbacks pluginManagerCallbacks) {
        // 注册漏洞信息
        DeserializationPayloadGenerate classToBCEL_plugin = new DeserializationPayloadGenerate();
        pluginManagerCallbacks.registerHelperPlugin(classToBCEL_plugin);
    }
}
