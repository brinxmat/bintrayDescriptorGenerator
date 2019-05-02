package no.greenall.bintraydescriptiongenerator

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy

@Builder(builderStrategy = SimpleStrategy, prefix = 'with')
class Version {
    String name
    Date released
    Boolean gpgSign


    String reportValidationErrors() {
        if (!isValid()) {

            String reportString = "In order to be valid, Bintray requires that the package object contains "
            + "a name field and this was lacking"

            return reportString
        }
    }

    Boolean isValid() {
        return this.name && this.name?.trim()?.length() > 0
    }
}
