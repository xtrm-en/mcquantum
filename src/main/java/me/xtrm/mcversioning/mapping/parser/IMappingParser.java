package me.xtrm.mcversioning.mapping.parser;

import me.xtrm.mcversioning.mapping.MappingData;
import me.xtrm.mcversioning.mapping.MappingData.ClassData;
import me.xtrm.mcversioning.mapping.MappingData.ClassData.NodeData;
import me.xtrm.mcversioning.mapping.MappingType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@FunctionalInterface
public interface IMappingParser {
    MappingData parseMappingData(MappingData baseData, File mappingFile) throws IOException;

    default MappingType getSourceMapping() {
        return MappingType.UNKNOWN;
    }

    default MappingType getTargetMapping() {
        return MappingType.UNKNOWN;
    }

    default IMappingParser runAndChain(File data, IMappingParser parser) {
        return (data1, mappingFile) -> parser.parseMappingData(parseMappingData(data), mappingFile);
    }

    default MappingData parseMappingData(File mappingFile) throws IOException {
        return parseMappingData(mappingData(), mappingFile);
    }

    default MappingData mappingData() {
        return new MappingData(getSourceMapping(), getTargetMapping(), new HashMap<>());
    }

    default ClassData classData(String originalName, String remappedName) {
        return new ClassData(originalName, remappedName, new HashMap<>(), new HashMap<>());
    }

    default NodeData nodeData(String originalName, String originalDescriptor, String remappedName, String remappedDescriptor) {
        return new NodeData(originalName, originalDescriptor, remappedName, remappedDescriptor);
    }
}
