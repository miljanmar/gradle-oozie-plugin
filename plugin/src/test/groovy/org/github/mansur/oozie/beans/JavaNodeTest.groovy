package org.github.mansur.oozie.beans;

import static org.junit.Assert.*;
import org.junit.Test;
import static BuilderTestUtils.assertXml

class JavaNodeTest extends AbstractHadoopActionNodeTest {
  def javaArgs = [ mainClass: 'com.example.Main', javaOpts: '-XrunFast', args: ['a', 'b'] ]
  def args = baseArgs + javaArgs;

  @Test
  public void testToMap() {
    assertEquals(baseResult + javaArgs + [type: 'java'], new JavaNode(baseArgs + javaArgs).toMap());
  }

  @Test
  public void testCaptureOutput() {
    assertEquals(
      baseResult + javaArgs + [type: 'java', captureOutput: true],
      new JavaNode(baseArgs + javaArgs + [captureOutput: true]).toMap());
  }

  @Test
  public void testBuildXml() {
    assertXml(new JavaNode(args), actionXml("java", "", """
 <main-class>com.example.Main</main-class>
 <java-opts>-XrunFast</java-opts>
 <arg>a</arg>
 <arg>b</arg>
"""))
  }

    @Test
  public void testBuildXmlCaptureOutput() {
    assertXml(new JavaNode(args + [captureOutput: true]), actionXml("java", "", """
        <main-class>com.example.Main</main-class>
        <java-opts>-XrunFast</java-opts>
        <arg>a</arg>
        <arg>b</arg>
        <capture-output/>
        """))
  }
}
