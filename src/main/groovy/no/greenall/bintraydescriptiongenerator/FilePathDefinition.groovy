package no.greenall.bintraydescriptiongenerator

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

@Builder(builderStrategy = SimpleStrategy, prefix = 'with')
class FilePathDefinition {
    private static final String INCLUDE_PATTERN = "includePattern"
    private static final String UPLOAD_PATTERN = "uploadPattern"
    public static final String SEPARATOR = ", "
    public static final String ERROR_PREFIX = "One or more filepath definition lacked"

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

        return "${ERROR_PREFIX} ${report.join(SEPARATOR)}"
    }
}
