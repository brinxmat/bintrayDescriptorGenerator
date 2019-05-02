package no.greenall.bintraydescriptiongenerator

class PackageTest extends GroovyTestCase {

    public static final String SOME_NAME = "some name"
    public static final String SOME_REPO = "some-repo"
    public static final String SOME_SUBJECT = "some subject"
    public static final String OK = "ok"
    public static final String SINGLE_SPACE = " "

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
        assert shouldBeTrue
    }

    void testPackageIsValidIsFalse() {
        (1..3).each {
            Package pkg = generateInvalidPackage(it)
            boolean shouldBeFalse = pkg.isValid()
            assert !shouldBeFalse
        }
    }

    static Package generateInvalidPackage(int random) {
        Package pkg = new Package()

        switch (random) {
            case 1: pkg.withName(SINGLE_SPACE)
                    .withRepo(OK)
                    .withSubject(OK)

                break
            case 2: pkg.withName(OK)
                    .withRepo(SINGLE_SPACE)
                    .withSubject(OK)
                break
            case 3: pkg.withName(OK)
                    .withRepo(OK)
                    .withSubject(SINGLE_SPACE)
                break
            default: pkg.withName(OK)
                    .withRepo(OK)
                    .withSubject(SINGLE_SPACE)
                break
        }


    }
}
