package no.greenall.bintraydescriptiongenerator.exceptions

class InvalidFilePathDefinitionsExceptionTest extends GroovyTestCase {

    public static final String EXPECTED_MESSAGE = "The file path definitions were empty"

    void testInvalidFilePathDefinitionsExceptionCanBeThrowWithMessage() {

        String message = shouldFail {
            throw new InvalidFilePathDefinitionsException()
        }

        assert EXPECTED_MESSAGE == message
    }

}
