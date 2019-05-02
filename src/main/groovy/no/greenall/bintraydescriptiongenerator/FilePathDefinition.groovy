package no.greenall.bintraydescriptiongenerator

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

@Builder(builderStrategy = SimpleStrategy, prefix = 'with')
class FilePathDefinition {
    String includePattern
    String uploadPattern

    boolean isValid() {
        return verifyBintrayPathString(this.includePattern) && verifyBintrayPathString(this.uploadPattern)
    }
    boolean verifyBintrayPathString(String string) {
        return string && string?.trim()?.length() > 0
    }
}
