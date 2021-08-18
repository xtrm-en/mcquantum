package net.minecraftforge.fml.relauncher;

public class FMLInjectionData {

    /**
     * @return { major, minor, rev, build, mcclientversion, mcpversion, minecraftHome, containers };
     */
    public static Object[] data() {
        throw new LinkageError();
    }
}
