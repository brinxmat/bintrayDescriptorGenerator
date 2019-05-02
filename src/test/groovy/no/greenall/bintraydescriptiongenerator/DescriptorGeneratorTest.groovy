package no.greenall.bintraydescriptiongenerator

class DescriptorGeneratorTest extends GroovyTestCase {
    void testDescriptorGeneratorHappyDayScenario() {

        Version version = new Version().withName("in-deed")
        FilePathDefinition filePathDefinition = new FilePathDefinition()
                .withUploadPattern("something")
                .withIncludePattern("something/else")
        FilePathDefinition[] filePathDefinitions = [filePathDefinition]
        Package aPackage = new Package()
                .withSubject("a")
                .withRepo("b")
                .withName("c")
        DescriptorGenerator descriptorGenerator = new DescriptorGenerator()
        descriptorGenerator.withVersion(version)
                .withFilePathDefinitions(filePathDefinitions)
                .withPackage(aPackage)

        String expectedJson = this.class.getResource("/bintray-descriptor.json").text

        assert aPackage == descriptorGenerator.aPackage
        assert filePathDefinitions == descriptorGenerator.filePathDefinitions
        assert version == descriptorGenerator.version

        assert expectedJson == descriptorGenerator.produceBintrayDescriptorJson()
    }

    void testDescriptorGeneratorSadDayScenario() {
        Version version = new Version()
        Package aPackage = new Package()
        FilePathDefinition[] filePathDefinitions = []
        DescriptorGenerator descriptorGenerator = new DescriptorGenerator()
        descriptorGenerator
                .withFilePathDefinitions(filePathDefinitions)
                .withVersion(version)
                .withPackage(aPackage)

        def failure = shouldFail {
            String json = descriptorGenerator.produceBintrayDescriptorJson()
        }

        assert "In order to be valid, Bintray requires that the package object contains fields, name, repo and subject, the following were lacking: name, repo, subject" == failure
    }
}
