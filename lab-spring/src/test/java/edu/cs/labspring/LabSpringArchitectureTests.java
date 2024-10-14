package edu.cs.labspring;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@SpringBootTest
class LabSpringArchitectureTests {

    private JavaClasses applicationClasses;

    @BeforeEach
    void initialize() {
        applicationClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("edu.cs.labspring");
    }

    @Test
    void shouldFollowLayerArchitecture()  {
        layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy("..controller..")
                .layer("Service").definedBy("..service..")
                .layer("Repository").definedBy("..repository..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Service")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")

                .check(applicationClasses);
    }

    @Test
    void servicesShouldNotDependOnControllers() {
        noClasses()
                .that().resideInAPackage("..service..")
                .should().dependOnClassesThat().resideInAPackage("..controller..")
                .check(applicationClasses);
    }

    @Test
    void controllersShouldNotDependOnOtherControllers() {
        noClasses()
                .that().resideInAPackage("..controller..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..controller..")
                .because("out of arch rules")
                .check(applicationClasses);
    }

    @Test
    void repositoriesShouldNotDependOnServices() {
        noClasses()
                .that().resideInAPackage("..repository..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..service..")
                .because("out of arch rules")
                .check(applicationClasses);
    }

    @Test
    void repositoriesShouldOnlyBeAccessedByServices() {
        classes()
                .that().resideInAPackage("..repository..")
                .should().onlyBeAccessed().byClassesThat().resideInAPackage("..service..")
                .check(applicationClasses);
    }

    @Test
    void controllerClassesShouldBeAnnotatedByController() {
        classes()
                .that().resideInAPackage("..controller..")
                .should()
                .beAnnotatedWith(RestController.class)
                .check(applicationClasses);
    }

    @Test
    void serviceShouldBeAnnotatedByService() {
        classes()
                .that().resideInAPackage("..service..")
                .should().beAnnotatedWith(Service.class)
                .check(applicationClasses);
    }

    @Test
    void repositoryShouldBeAnnotatedByRepository() {
        classes()
                .that().resideInAPackage("..repository..")
                .should()
                .beAnnotatedWith(Repository.class)
                .check(applicationClasses);
    }

    @Test
    void servicesShouldHaveServiceSuffix() {
        classes()
                .that().resideInAPackage("..service..")
                .should().haveSimpleNameEndingWith("Service")
                .check(applicationClasses);
    }

    @Test
    void controllersShouldHaveControllerSuffix() {
        classes()
                .that().resideInAPackage("..controller..")
                .should().haveSimpleNameEndingWith("Controller")
                .check(applicationClasses);
    }

    @Test
    void repositoriesShouldHaveRepositorySuffix() {
        classes()
                .that().resideInAPackage("..repository..")
                .should().haveSimpleNameEndingWith("Repository")
                .check(applicationClasses);
    }

    @Test
    void repositoryShouldBeInterface() {
        classes()
                .that().resideInAPackage("..repository..")
                .should()
                .beInterfaces()
                .check(applicationClasses);
    }

    @Test
    void anyControllerFieldsShouldNotBeAnnotatedAutowired() {
        noClasses()
                .that().resideInAPackage("..controller..")
                .should()
                .beAnnotatedWith(Autowired.class)
                .check(applicationClasses);
    }

    @Test
    void methodsShouldNotThrowExceptions() {
        methods()
                .should().notDeclareThrowableOfType(Exception.class)
                .check(applicationClasses);
    }

    @Test
    void modelFieldsShouldBePrivate() {
        fields()
                .that().areDeclaredInClassesThat()
                .resideInAPackage("..model..")
                .should().notBePublic()
                .check(applicationClasses);

    }

    @Test
    void methodsShouldBePublicVoidExceptPostConstruct() {
        methods()
                .that().areDeclaredInClassesThat().resideInAPackage("..service..")
                .and().areNotAnnotatedWith(PostConstruct.class)
                .should().bePublic()
                .check(applicationClasses);
    }

    @Test
    void fieldsInServicesShouldBePrivate() {
        fields()
                .that().areDeclaredInClassesThat()
                .resideInAPackage("..service..")
                .should().bePrivate()
                .check(applicationClasses);
    }

    @Test
    void controllersShouldBeAnnotatedWithRequestMapping() {
        classes()
                .that().resideInAPackage("..controller..")
                .should()
                .beAnnotatedWith(RequestMapping.class)
                .check(applicationClasses);
    }

    @Test
    void repositoriesShouldExtendMongoRepository() {
        classes()
                .that().resideInAPackage("..repository..")
                .should().beAssignableTo(MongoRepository.class)
                .check(applicationClasses);
    }

    @Test
    void modelsShouldBeAnnotatedByDocument() {
        classes()
                .that().resideInAPackage("..model..")
                .should().beAnnotatedWith(Document.class)
                .check(applicationClasses);
    }

}
