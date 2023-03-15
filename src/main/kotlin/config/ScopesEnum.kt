package config

enum class ScopesEnum(val value: String) {
    LOCAL("local"), TEST("test"), PROD("prod");


    companion object {
        fun getScopeByValue(value: String): ScopesEnum? {
            return values().find { it.value == value }
        }
    }


}