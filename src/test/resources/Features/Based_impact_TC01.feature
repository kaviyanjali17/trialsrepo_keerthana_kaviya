Feature: Verify navigation to key pages

  Scenario Outline: Validate navigation
    Given I launch the application
    When I click on "<linkName>"
    Then I should see the heading "<expectedHeading>"

    Examples:
      | linkName        | expectedHeading         |
      | Products        | CATEGORY                |
      | Signup/Login    | LOGIN TO YOUR ACCOUNT   |
      | Contact Us      | CONTACT US              |
      | Cart            | SUBSCRIPTION            |
      | Test Cases      | TEST CASES              |
      | API Testing     | APIS LIST FOR PRACTICE  |
      | Video Tutorials | VIDEO TUTORIALS         |

