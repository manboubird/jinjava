# Jinjava Releases #

### Version 2.1.11 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.11%22)) ###

* Add additional specific error enums
* Add escapeJS filter
* Allow null expressions as target of replace filter

### Version 2.1.10 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.10%22)) ###


### Version 2.1.9 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.9%22)) ###

* Exclude 'caller' from recursive macro check

### Version 2.1.8 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.8%22)) ###

* Add additional category information and error message tokens to TemplateError
* Add range function
* Update ListFilter to work with strings
* Do not allow macros to be called recursively
* Update checkstyle to 2.17

### Version 2.1.7 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.7%22)) ###

* Updated RawTag to not evaluate tags nested within it

### Version 2.1.6 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.6%22)) ###

* Added new bool filter to return boolean value from string
* Added record of expressions and values evalulated

### Version 2.1.5 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.5%22)) ###

* Add support for java.util.Optional properties, nested properties in EL expressions

### Version 2.1.4 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.4%22)) ###

* fixed ArrayIndexOutOfBoundsException when importing template with no trailing newline

### Version 2.1.3 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.3%22)) ###

* Added two new expression tests for strings: "is string_startingwith" and "is string_containing"
* Added support for multi-variable set in set tag (@amannm)
* Added new detail dimension to TemplateError: ErrorItem

### Version 2.1.2 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.2%22)) ###

* Use resolved path value in include tag cycle detection
* Store autoEscape flag in context outside of user-editable properties
* Store superBlock reference in context outside of user-editable properties
* make EL resolver read-only by default, expose as config parameter
* restrict certain methods/properties in object expressions

### Version 2.1.1 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.1%22)) ###

* Better error messages for invalid assignment in expression 
* Allow for locale-based date formatting in StrftimeFormatter
* Use configured locale for Functions.datetimeformat

### Version 2.1.0 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.1.0%22)) ###

* Refactored node render logic to return richer OutputNode instances, removing a need for a special intermediate string value in text output
* Refactored cycle detection in import and include tags to remove use of special named vars in context
* Fix bug to properly detect cycles in extends tag
* Fix infinite recursion bug in resolveBlockStubs when block contains self-reference

### Version 2.0.11 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.11%22)) ###

* Renaming JinjavaInterpreter.renderString() to renderFlat() to better signify its purpose
* Released build for jdk7 as version 2.0.11-java7

### Version 2.0.10 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.10%22)) ###

* minor performance enhancements

### Version 2.0.9 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.9%22)) ###

* update truncate_html filter to support preserving words by default, with an additional parameter to chop words at length
* added unique filter to remove duplicate objects from a sequence 
* add support for global trim_blocks, lstrip_blocks config settings

### Version 2.0.8 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.8%22)) ###

* Updated escape filter to apply to string representation of all objects, not just string instances
* Reworked variable resolution to use juel rather than custom approach
* Adding ability to track dependencies used in rendering templates
* fix issue with handling escape sequences in quoted strings within block tags

### Version 2.0.7 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.7%22)) ###

* Propagate interpreter errors from child interpreters
* Changed error level to WARN for invalid date format strings, invalid locale strings

### Version 2.0.6 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.6%22)) ###

* Fix ifchanged end tag name
* Fix implementation of autoescape tag
* Fix propagation of InterpretExceptions from tag render
* doc updates

### Version 2.0.5 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.5%22)) ###

* Changed behavior of date filter to be same as datetimeformat filter
* Fixed date format conversion for single-digit values, for %e, %k, %l
* Updated Jinjavadoc to support isDeprecated flag, as well as arbitrary key-value metadata

### Version 2.0.4 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.4%22)) ###

* Fix issue with PyishDate.isoformat()

### Version 2.0.3 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.3%22)) ###

* Upgrading javassist, commons-lang3 library dependencies
* Adding new factory method to Jinjava for creating a new JinjavaInterpreter instance

### Version 2.0.2 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.2%22)) ###

* Adding code snippets to jinjavadoc
* Adding shuffle filter for collections

### Version 2.0.1 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.1%22)) ###

* Adding self-documenting feature to jinjava core
* Updating addition expression operator ('+') to work with lists and dicts

### Version 2.0.0 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%222.0.0%22)) ###

* 2.0.x requires JDK 8, as it contains some critical fixes to date formatting for certain languages (i.e. Finnish months)

* The 2.0.x release has some significant refactorings in the parsing code:
** nests the .parse package under the existing .tree package
** consolidating the token scanner logic, updating the node tree parser

* future updates will be able to detect more specific template syntax errors than was previously possible.

* NodeList has been removed in favor of the native JDK LinkedList implementation


### Version 1.0.9 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%221.0.9%22)) ###

* changed how validation of object properties happens; previously, jinjava would add TemplateErrors for unknown properties at the context root (i.e. 'foo') and when the property existed but the value was null (i.e. 'foo.bar == null'). the new logic will only add an error when the property isn't found on the given base object.

* creating richer exception hierarchy for more detailed and specific error messaging

* the simple Jinjava.render(String, Map) method will now return a container exception containing all errors encountered during rendering, instead of simply the first one

### Version 1.0.8 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%221.0.8%22)) ###

* fixed issue with multiple includes of the same file in a template

### Version 1.0.7 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%221.0.7%22)) ###

* added logic to avoid include/import cycles in include and import tags

### Version 1.0.6 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%221.0.6%22)) ###

* fixed issue in strftimeformatter with format strings ending in literals
* updated expression resolver to return blank strings rather than false for exception cases

### Version 1.0.5 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%221.0.5%22)) ###

* fixed issue in sort filter when using nested attribute

### Version 1.0.4 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%221.0.4%22)) ###

* Fix issue where HelperStringTokenizer could return a null last value
* Added ```unless``` tag, for inverse-if functionality
* Implemented ```from``` tag for importing specific named variables from another context

### Version 1.0.3 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%221.0.3%22)) ###

* Adding python methods dict.update() and date.isoformat() to analogous types

### Version 1.0.2 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%221.0.2%22)) ###

* Fix issue with closure scope lookups within macro functions

### Version 1.0.1 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%221.0.1%22)) ###

* Properly implement call tag syntax
* Fix issue with declaring a list as a default method parameter value
* Adding string split built-in filter

### Version 1.0.0 ([Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.hubspot.jinjava%22%20AND%20v%3A%221.0.0%22)) ###

* Initial Public Release

