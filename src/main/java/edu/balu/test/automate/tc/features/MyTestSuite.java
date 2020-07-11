package edu.balu.test.automate.tc.features;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@IncludeTags("smoke")
//@Suite.SuiteClasses({FeatureTwoTests.class})
@SelectPackages({"edu.balu.test.automate.tc.features"})
@IncludeClassNamePatterns(".*Tests?")
public class MyTestSuite {


}
