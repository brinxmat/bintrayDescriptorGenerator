package no.greenall.bintraydescriptiongenerator

class VersionTest extends GroovyTestCase {


    public static final String SOME_VERSION_NAME = "has name"
    public static final String DATE_FORMAT = "yyyy-MM-dd"

    void testIsValidPopulatedVersionNameIsTrue() {
        Version version = new Version().withName(SOME_VERSION_NAME)
        boolean shouldBeTrue = version.isValid()
        assert shouldBeTrue
    }

    void testIsValidEmptyNameIsFalse() {
        Version version = new Version().withGpgSign(true).withReleased(new Date())
        boolean shouldBeFalse = version.isValid()
        assert !shouldBeFalse
    }

    void testFluentInterface() {
        String expectedDate = new Date().format(DATE_FORMAT)
        Version version = new Version().withGpgSign(false).withName(SOME_VERSION_NAME).withReleased(new Date())
        assert !version.gpgSign
        assert SOME_VERSION_NAME == version.name
        assert expectedDate == version.released.format(DATE_FORMAT)
    }
}
