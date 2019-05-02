package no.greenall.bintraydescriptiongenerator

class DescriptorGeneratorTest extends GroovyTestCase {

    public static final String SOME_UPLOAD_PATTERN = "some/upload/pattern"
    public static final String SOME_INCLUDE_PATTERN = "some/include/pattern"
    public static final String EXPECTED_ERROR_ALL_BAD_PACKAGE = "In order to be valid, Bintray requires that the package object contains fields, name, repo and subject, the following were lacking: name, repo, subject"
    public static final String EXPECTED_ERROR_BAD_VERSION = "In order to be valid, Bintray requires that the version object contains a name field and this was lacking"
    public static final String EXPECTED_ERROR_ALL_BAD_FILE_PATH_DEFINITION = "One or more filepath definition lacked includePattern, uploadPattern"
    public static final String REPO = "repo"
    public static final String NAME = "name"
    public static final String ALL = "all"
    public static final String A_PACKAGE_NAME = "a-package-name"
    public static final String A_PACKAGE_SUBJECT = "a-package-subject"
    public static final String A_PACKAGE_REPO = "a-package-repo"
    public static final String SUBJECT = "subject"
    public static final String PATH_TO_EXPECTED_BINTRAY_JSON = "/bintray-descriptor.json"
    public static final String SOME_VERSION_NAME = "1.0.0"
    public static final String UPLOAD_PATTERN = "uploadPattern"
    public static final String INCLUDE_PATTERN = "includePattern"

    void testDescriptorGeneratorHappyDayScenario() {

        Version version = createGoodVersion()
        FilePathDefinition[] filePathDefinitions = [createGoodFilePathDefinition()]
        Package aPackage = createGoodPackage()
        DescriptorGenerator descriptorGenerator = new DescriptorGenerator()
        descriptorGenerator.withVersion(version)
                .withFilePathDefinitions(filePathDefinitions)
                .withPackage(aPackage)

        String expectedJson = this.class.getResource(PATH_TO_EXPECTED_BINTRAY_JSON).text

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
            descriptorGenerator.produceBintrayDescriptorJson()
        }

        assert EXPECTED_ERROR_ALL_BAD_PACKAGE == failure
    }

    void testDescriptorGeneratorFailsWithBadPackage() {
        Version version = createGoodVersion()
        Package aPackage = createBadPackage()
        FilePathDefinition[] filePathDefinitions = [createGoodFilePathDefinition()]
        DescriptorGenerator descriptorGenerator = new DescriptorGenerator()
        descriptorGenerator.withVersion(version)
                .withFilePathDefinitions(filePathDefinitions)
                .withPackage(aPackage)

        def failure = shouldFail {
            descriptorGenerator.produceBintrayDescriptorJson()
        }

        assert EXPECTED_ERROR_ALL_BAD_PACKAGE == failure
    }

    void testDescriptorGeneratorFailsWithBadVersion() {
        Version version = createBadVersion()
        Package aPackage = createGoodPackage()
        FilePathDefinition[] filePathDefinitions = [createGoodFilePathDefinition()]
        DescriptorGenerator descriptorGenerator = new DescriptorGenerator()
        descriptorGenerator.withVersion(version)
                .withFilePathDefinitions(filePathDefinitions)
                .withPackage(aPackage)

        def failure = shouldFail {
            descriptorGenerator.produceBintrayDescriptorJson()
        }

        assert EXPECTED_ERROR_BAD_VERSION == failure
    }

    void testDescriptorGeneratorFailsWithBadFilePathDefinition() {
        Version version = createGoodVersion()
        Package aPackage = createGoodPackage()
        FilePathDefinition[] filePathDefinitions = [createBadFilePathDefinitionArray()]
        DescriptorGenerator descriptorGenerator = new DescriptorGenerator()
        descriptorGenerator.withVersion(version)
                .withFilePathDefinitions(filePathDefinitions)
                .withPackage(aPackage)

        def failure = shouldFail {
            descriptorGenerator.produceBintrayDescriptorJson()
        }

        assert EXPECTED_ERROR_ALL_BAD_FILE_PATH_DEFINITION == failure
    }

    static Version createBadVersion() {
        return new Version()
    }

    static Version createGoodVersion() {
        return new Version().withName(SOME_VERSION_NAME)
    }


    static Package createBadPackage(String missingField = ALL) {
        Package aPackage = new Package()
        switch (missingField) {
            case REPO:
                aPackage.withName(A_PACKAGE_NAME).withSubject(A_PACKAGE_SUBJECT)
                break
            case NAME: aPackage.withSubject(A_PACKAGE_SUBJECT).withRepo(A_PACKAGE_REPO)
                break
            case SUBJECT: aPackage.withName(A_PACKAGE_NAME).withRepo(A_PACKAGE_REPO)
                break
            case ALL:
                break
            default:
                break

        }
        return aPackage
    }

    static Package createGoodPackage() {
        return new Package().withRepo(A_PACKAGE_REPO).withName(A_PACKAGE_NAME).withSubject(A_PACKAGE_SUBJECT)
    }

    static FilePathDefinition createBadFilePathDefinitionArray(String missingField = ALL) {
        FilePathDefinition filePathDefinition = new FilePathDefinition()

        switch (missingField) {
            case UPLOAD_PATTERN: filePathDefinition.withUploadPattern(SOME_UPLOAD_PATTERN)
                break
            case INCLUDE_PATTERN: filePathDefinition.withIncludePattern(SOME_INCLUDE_PATTERN)
                break
            case ALL:
                break
            default:
                break
        }

        return filePathDefinition
    }

    static FilePathDefinition createGoodFilePathDefinition() {
        return new FilePathDefinition()
                .withUploadPattern(SOME_UPLOAD_PATTERN)
                .withIncludePattern(SOME_INCLUDE_PATTERN)
    }
}
