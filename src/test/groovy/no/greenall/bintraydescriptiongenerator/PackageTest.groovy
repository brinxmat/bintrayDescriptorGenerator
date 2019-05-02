package no.greenall.bintraydescriptiongenerator

class PackageTest extends GroovyTestCase {

    public static final String SOME_NAME = "some name"
    public static final String SOME_REPO = "some-repo"
    public static final String SOME_SUBJECT = "some subject"

    void testPackageFluentInterface() {
        Package pkg = new Package()
                .withName(SOME_NAME)
                .withRepo(SOME_REPO)
                .withSubject(SOME_SUBJECT)

        assert SOME_NAME == pkg.name
        assert SOME_REPO == pkg.repo
        assert SOME_SUBJECT == pkg.subject
    }

    void testPackageIsValidIsTrue() {
        Package pkg = new Package()
                .withName(SOME_NAME)
                .withRepo(SOME_REPO)
                .withSubject(SOME_SUBJECT)
        boolean shouldBeTrue = pkg.isValid()
        assert true == shouldBeTrue
    }

    void testPackageIsValidIsFalse() {
        (1..3).each {
            Package pkg = generateInvalidPackage(it)
            boolean shouldBeFalse = pkg.isValid()
            assert !shouldBeFalse
        }
    }

    Package generateInvalidPackage(int random) {
        Package pkg = new Package()

        switch (random) {
            case 1: pkg.withName(" ")
                       .withRepo("ok")
                       .withSubject("ok")
                    break
            case 2: pkg.withName("ok")
                    .withRepo(" ")
                    .withSubject("ok")
                    break
            case 2:
            default:
                    pkg.withName("ok")
                    .withRepo("ok")
                    .withSubject(" ")
                    break
        }


    }
}
