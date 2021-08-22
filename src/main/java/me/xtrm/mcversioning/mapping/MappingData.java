package me.xtrm.mcversioning.mapping;

import lombok.Data;

import java.util.Map;

public @Data class MappingData {
    private final MappingType sourceMappings;
    private final MappingType targetMappings;
    private final Map<String, ClassData> classes;

    public void map(ClassData classData) {
        this.classes.put(classData.originalName, classData);
    }

    public @Data static class ClassData {
        private final String originalName;
        private final String remappedName;

        private final Map<String, NodeData> methods;
        private final Map<String, NodeData> fields;

        public void mapField(NodeData nodeData) {
            this.fields.put(nodeData.originalName, nodeData);
        }

        public void mapMethod(NodeData nodeData) {
            this.methods.put(nodeData.originalName, nodeData);
        }

        public @Data static class NodeData {
            private final String originalName;
            private final String originalDescriptor;
            private final String remappedName;
            private final String remappedDescriptor;
        }
    }
}
