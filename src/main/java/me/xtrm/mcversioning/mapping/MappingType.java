package me.xtrm.mcversioning.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.xtrm.mcversioning.mapping.parser.IMappingParser;
import me.xtrm.mcversioning.mapping.parser.impl.IntermediaryToYarn;
import me.xtrm.mcversioning.mapping.parser.impl.NotchToMCP;
import me.xtrm.mcversioning.mapping.parser.impl.SeargeToMCP;

import java.util.function.Supplier;

@Getter
@RequiredArgsConstructor
public enum MappingType {
    YARN("Yarn", false, null), // Fabric deobfuscated
    INTERMEDIARY("Intermediary", true, IntermediaryToYarn::new), // Fabric middle obfuscation

    MCP("MCP", false, null), // MCP Deobfuscated
    SEARGE("Searge", true, SeargeToMCP::new), // Forge middle obfuscation

    NOTCH("Notch", true, NotchToMCP::new), // Base game, obfuscated

    MOJMAP("Mojmap", false, null), // Official Mojang-provided mappings, deobfuscated

    UNKNOWN("Unknown", true, null); // ? obfuscated

    private final String name;
    private final boolean requiresDeobfuscation;
    private final Supplier<IMappingParser> mappingParserSupplier;
}
