package no.greenall.bintraydescriptiongenerator

import groovy.json.JsonGenerator
import groovy.json.JsonOutput
import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import no.greenall.bintraydescriptiongenerator.exceptions.InvalidFilePathDefinitionsException
import no.greenall.bintraydescriptiongenerator.exceptions.InvalidPackageException
import no.greenall.bintraydescriptiongenerator.exceptions.InvalidVersionException

@Builder(builderStrategy = SimpleStrategy, prefix = 'with', excludes = 'aPackage')
class DescriptorGenerator {
    Version version
    Package aPackage
    FilePathDefinition[] filePathDefinitions

    void withPackage(Package aPackage) {
        this.aPackage = aPackage
    }

    String produceBintrayDescriptorJson() {

        if (!this.aPackage || !this.aPackage.isValid()) {
            throw new InvalidPackageException(this.aPackage.reportValidationErrors())
        }

        if (!this.version || !this.version.isValid()) {
            throw new InvalidVersionException(this.version.reportValidationErrors())
        }

        if (!this.filePathDefinitions) {
            throw new InvalidFilePathDefinitionsException()
        }

        def jsonGenerator = new JsonGenerator.Options()
                .excludeNulls()
                .dateFormat('yyyy-MM-dd')
                .excludeFieldsByName(['contentHash', 'originalClassName']).build()
        def packageObject = ['package': aPackage, 'version': version, 'files': filePathDefinitions]
        return JsonOutput.prettyPrint(jsonGenerator.toJson(packageObject))
    }
}
