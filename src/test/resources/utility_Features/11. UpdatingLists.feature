Feature: IC admin updates a list using utility class

  Scenario Outline: IC admin can update a list
    Given TestName "IC admin can update a list" the landing page is loaded
    When a user "7" logs in with "non-MFA status"
    And clicks on "List Management" link in Innovation Center
    And scrolls into view "<module>", searches the "<list>", clicks on update icon
    Then "Update List Details" page, list name and entries are displayed
    When iteratively searches and updates values for the below from "<source_list>":
      | ENTITY_ID | UpdatedNameValue |
    Then logout
    Examples:
      | module                                           | list                 | source_list           |
      | List Management of Application Custom Attributes | HDR_ENTITY_ID_NESTED | VAL_HDR_Entities.xlsx |