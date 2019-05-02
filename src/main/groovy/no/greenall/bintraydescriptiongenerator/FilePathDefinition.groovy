package no.greenall.bintraydescriptiongenerator

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

@Builder(builderStrategy = SimpleStrategy, prefix = 'with')
class FilePathDefinition {
    private static final String INCLUDE_PATTERN = "includePattern"
    private static final String UPLOAD_PATTERN = "uploadPattern"

    String includePattern
    String uploadPattern

    boolean isValid() {
        return verifyBintrayPathString(this.includePattern) && verifyBintrayPathString(this.uploadPattern)
    }

    static boolean verifyBintrayPathString(String string) {
        return string && string?.trim()?.length() > 0
    }

    String reportValidationErrors() {
        def report = []
        if (!this.includePattern) {
            report << INCLUDE_PATTERN
        }
        if (!this.uploadPattern) {
            report << UPLOAD_PATTERN
        }

        return "One or more filepath definition lacked ${report.join(", ")}"
    }
}
