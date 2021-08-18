package cpw.mods.fml.common;

import java.util.List;

public class Loader {

    public static Loader instance() {
        throw new LinkageError();
    }

    public List<ModContainer> getModList() {
        throw new LinkageError();
    }

}
