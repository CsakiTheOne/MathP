package cpp

import java.io.File

class Generator {
    companion object {
        private const val VAR_NAME = "%classname%"
        private const val VAR_LOWER = "%lowername%"
        private const val VAR_UPPER = "%uppername%"
        private const val VAR_PARENT = "%parentname%"
        private const val VAR_PARENT_LOWER = "%parentlowername%"

        private const val baseClassCpp = "#include \"$VAR_LOWER.h\"\n\n$VAR_NAME::$VAR_NAME()\n{\n\n}\n"
        private const val baseClassH = "#ifndef ${VAR_UPPER}_H\n#define ${VAR_UPPER}_H\n\n\nclass $VAR_NAME\n{\npublic:\n\t${VAR_NAME}();\n};\n\n#endif // ${VAR_UPPER}_H"
        private const val baseChildClassCpp = "#include \"$VAR_LOWER.h\"\n\n$VAR_NAME::$VAR_NAME()\n{\n\n}\n"
        private const val baseChildClassH = "#ifndef ${VAR_UPPER}_H\n#define ${VAR_UPPER}_H\n\n#inclide \"$VAR_PARENT_LOWER.h\"\n\nclass $VAR_NAME : public $VAR_PARENT\n{\npublic:\n\t$VAR_NAME();\n};\n\n#endif // ${VAR_UPPER}_H"

        fun createClassesByName(classNames: List<String>) {
            for (name in classNames) {
                val isChild = name.contains(':')
                val className = name.split(':')[0]
                val parentName = name.split(':')[1]

                val fc = File("./$className.cpp")
                val fh = File("./$className.h")
            }
        }
    }
}