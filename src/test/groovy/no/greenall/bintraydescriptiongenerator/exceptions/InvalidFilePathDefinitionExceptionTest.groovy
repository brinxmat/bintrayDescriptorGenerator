package no.greenall.bintraydescriptiongenerator.exceptions

class InvalidFilePathDefinitionExceptionTest extends GroovyTestCase {
    public static final String EXPECTED_MESSAGE = "Some error"

    void testInvalidFilePathDefinitionExceptionCanBeThrowWithMessage() {

        String message = shouldFail {
            throw new InvalidFilePathDefinitionException(EXPECTED_MESSAGE)
        }

        assert EXPECTED_MESSAGE == message
    }
}
