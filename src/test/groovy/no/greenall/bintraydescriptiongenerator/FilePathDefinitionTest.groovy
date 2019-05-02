package no.greenall.bintraydescriptiongenerator

class FilePathDefinitionTest extends GroovyTestCase{

    public static final String SOME_UPLOAD_PATTERN = "something"
    public static final String SOME_INCLUDE_PATTERN = "something/else"

    void testFilePathDefinitionFluentApi() {
        FilePathDefinition filePathDefinition = new FilePathDefinition()
        filePathDefinition.withUploadPattern(SOME_UPLOAD_PATTERN)
        filePathDefinition.withIncludePattern(SOME_INCLUDE_PATTERN)

        assert SOME_INCLUDE_PATTERN == filePathDefinition.includePattern
        assert SOME_UPLOAD_PATTERN == filePathDefinition.uploadPattern
    }

    void testValidWithValidPathsReturnsTrue() {
        FilePathDefinition filePathDefinition = new FilePathDefinition()
                .withIncludePattern(SOME_INCLUDE_PATTERN)
                .withUploadPattern(SOME_UPLOAD_PATTERN)
        assert filePathDefinition.isValid()
    }

    void testValidWithInvalidPathsReturnsFalse() {
        FilePathDefinition fpd1 = new FilePathDefinition()
                .withUploadPattern(SOME_UPLOAD_PATTERN)
        assert !fpd1.isValid()

        FilePathDefinition fpd2 = new FilePathDefinition()
                .withIncludePattern(SOME_INCLUDE_PATTERN)
        assert !fpd2.isValid()
    }


}
