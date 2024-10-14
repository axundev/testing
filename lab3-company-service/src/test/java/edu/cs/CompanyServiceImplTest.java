package edu.cs;

/*
  @author   AlexAT
  @project   lab3-company-service
  @class  CompanyServiceImplTest
  @version  1.0.0
  @since 28.09.2024 - 17.59
*/

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyServiceImplTest {

    // Companies 1 (from given task + added some extra)
    private final ICompanyService underTest = new CompanyServiceImpl();

    private final Company main = new Company(null, 3);

    private final Company development = new Company(main, 4);
    private final Company design = new Company(development, 3);
    private final Company frontEnd = new Company(development, 10);
    private final Company backEnd = new Company(development, 11);

    private final Company businessLogic = new Company(backEnd, 5);
    private final Company db = new Company(backEnd, 3);


    private final Company bookkeeping = new Company(main, 2);

    private final Company projectManagement = new Company(main, 3);

    private final Company resourceManagement = new Company(projectManagement, 3);
    private final Company timeManagement = new Company(projectManagement, 2);

    private final Company sprintsControlling = new Company(timeManagement, 2);

    // Companies 2
    private final Company main2  = new Company(null, 10);
    private final Company digitalMarketing = new Company(main2, 7);
    private final Company printMarketing = new Company(main2, 15);

    private final Company socialMediaTeam = new Company(digitalMarketing, 25);
    private final Company emailMarketingTeam = new Company(digitalMarketing, 14);



    List<Company> CompanyList = new ArrayList<Company>() {{
        add(main);
        add(development);
        add(design);
        add(frontEnd);
        add(backEnd);
        add(businessLogic);
        add(db);
        add(bookkeeping);
        add(projectManagement);
        add(resourceManagement);
        add(timeManagement);
        add(sprintsControlling);
    }};

    List<Company> PromotionCompanyList = new ArrayList<Company>() {{
        add(main2);
        add(digitalMarketing);
        add(printMarketing);
        add(socialMediaTeam);
        add(emailMarketingTeam);
    }};


    //----------TESTS 10 FOR getTopLevelParent()--------------//
    @Test
    public void whenGetTopLevelParentForDesignCompanyThenReturnMain() {
        Company result = underTest.getTopLevelParent(design);
        assertEquals(main, result);
    }

    @Test
    public void whenGetTopLevelParentForNullThenReturnNull() {
        Company result = underTest.getTopLevelParent(null);
        assertNull(result);
    }

    @Test
    public void whenGetTopLevelParentForTopLevelCompanyThenReturnItself() {
        Company result = underTest.getTopLevelParent(main);
        assertEquals(main, result);
    }

    @Test
    public void whenGetTopLevelParentForDbCompanyThenReturnMain() {
        Company result = underTest.getTopLevelParent(db);
        assertEquals(main, result);
    }

    @Test
    public void whenGetTopLevelParentForSprintControllingCompanyThenReturnMain() {
        Company result = underTest.getTopLevelParent(sprintsControlling);
        assertEquals(main, result);
    }

    @Test
    public void whenGetTopLevelParentForDigitalMarketingThenReturnMain2() {
        Company result = underTest.getTopLevelParent(digitalMarketing);
        assertEquals(main2, result);
    }

    @Test
    public void whenGetTopLevelParentForEmailMarketingTeamThenReturnMain2() {
        Company result = underTest.getTopLevelParent(emailMarketingTeam);
        assertEquals(main2, result);
    }

    @Test
    public void whenGetTopLevelParentForNewCompanyWithoutParentThenReturnNewCompany() {
        Company newCompany = new Company(null, 0);
        Company topLevelParent = underTest.getTopLevelParent(newCompany);
        assertEquals(newCompany, topLevelParent);
    }

    @Test
    public void whenCompanyHasCircularReferencesThenThrowIllegalStateException() {
        Company newCompany1 = new Company(null, 0);
        Company newCompany2 = new Company(newCompany1, 0);
        newCompany1.setParent(newCompany2);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            underTest.getTopLevelParent(newCompany1);
        });

        assertEquals("Circular reference detected in company hierarchy.", exception.getMessage());
    }

    //----------TESTS 10 FOR getEmployeeCountForCompanyAndChildren()--------------//
    @Test
    void whenGettingEmployeeCountForDevelopmentThenReturns34() {
        long expected = 4 + 3 + 10 + 11 + 5 + 3; // Counts from development and its children
        long actual = underTest.getEmployeeCountForCompanyAndChildren(development, CompanyList);
        assertEquals(expected, actual);
    }

    @Test
    void whenGettingEmployeeCountForMainThenReturns38() {
        long expected = 3 + 4 + 3 + 10 + 11 + 5 + 3 + 2 + 3 + 3 + 2 + 2; // Counts from all children
        long actual = underTest.getEmployeeCountForCompanyAndChildren(main, CompanyList);
        assertEquals(expected, actual);
    }

    @Test
    void whenGettingEmployeeCountForBackEndThenReturns19() {
        long expected = 11 + 5 + 3; // Counts from backEnd and its children
        long actual = underTest.getEmployeeCountForCompanyAndChildren(backEnd, CompanyList);
        assertEquals(expected, actual);
    }

    @Test
    void whenGettingEmployeeCountForBookkeepingThenReturns2() {
        long expected = 2; // Only counts from bookkeeping
        long actual = underTest.getEmployeeCountForCompanyAndChildren(bookkeeping, CompanyList);
        assertEquals(expected, actual);
    }

    @Test
    void whenGettingEmployeeCountForProjectManagementThenReturns8() {
        long expected = 3 + 3 + 2; // Counts from projectManagement and its children
        long actual = underTest.getEmployeeCountForCompanyAndChildren(projectManagement, CompanyList);
        assertEquals(expected, actual);
    }

    @Test
    void whenGettingEmployeeCountForTimeManagementThenReturns2() {
        long expected = 2; // Only counts from timeManagement
        long actual = underTest.getEmployeeCountForCompanyAndChildren(timeManagement, CompanyList);
        assertEquals(expected, actual);
    }

    @Test
    void whenGettingEmployeeCountForDesignThenReturns3() {
        long expected = 3; // Only counts from design
        long actual = underTest.getEmployeeCountForCompanyAndChildren(design, CompanyList);
        assertEquals(expected, actual);
    }

    @Test
    void whenGettingEmployeeCountForSprintsControllingThenReturns2() {
        long expected = 2; // Only counts from sprintsControlling
        long actual = underTest.getEmployeeCountForCompanyAndChildren(sprintsControlling, CompanyList);
        assertEquals(expected, actual);
    }

    @Test
    void whenGettingEmployeeCountForSocialMediaTeamThenReturns25() {
        long expected = 25; // Assuming socialMediaTeam has no children
        long actual = underTest.getEmployeeCountForCompanyAndChildren(socialMediaTeam, PromotionCompanyList);
        assertEquals(expected, actual);
    }

    @Test
    void whenGettingEmployeeCountForEmailMarketingTeamThenReturns14() {
        long expected = 14; // Assuming emailMarketingTeam has no children
        long actual = underTest.getEmployeeCountForCompanyAndChildren(emailMarketingTeam, PromotionCompanyList);
        assertEquals(expected, actual);
    }
}