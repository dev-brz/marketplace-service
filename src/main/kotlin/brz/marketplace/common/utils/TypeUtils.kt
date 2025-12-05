package brz.marketplace.common.utils

import org.springframework.core.ParameterizedTypeReference

class TypeUtils private constructor() {
    companion object {
        inline fun <reified T : Any> typeReference() = object : ParameterizedTypeReference<T>() {}
    }
}