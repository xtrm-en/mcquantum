package cpw.mods.fml.relauncher;

public class FMLInjectionData {

    /**
     * In minecraft 1.4:
     *  XXX -> minecraft server version
     *
     * After minecraft 1.4.5:
     *  XXX -> MCP version
     *
     * @return { major, minor, rev, build, mcclientversion, XXX, minecraftHome, containers };
     */
    public static Object[] data() {
        throw new LinkageError();
    }

}
