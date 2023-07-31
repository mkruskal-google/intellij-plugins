package com.intellij.protobuf.lang.resolve

import com.intellij.protobuf.fixtures.PbCodeInsightFixtureTestCase
import com.intellij.protobuf.ide.settings.PbProjectSettings
import com.intellij.protobuf.lang.psi.PbImportName
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.util.parentOfType
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PbImportPathResolverTest : PbCodeInsightFixtureTestCase() {
  @Before
  fun beforeEachTest() {
    PbProjectSettings.getInstance(myFixture.project).apply {
      isIncludeContentRoots = false
      isIncludeProtoDirectories = false
      isThirdPartyConfigurationEnabled = false
      isIndexBasedResolveEnabled = false
      importPathEntries = mutableListOf()
    }
  }

  @Test
  fun `test no imports resolved when all settings are disabled`() {
    myFixture.addFileToProject("imported.proto", "")
    myFixture.configureByText("root.proto", """
      import "<error descr="Cannot resolve import 'imported.proto'">imported.proto</error>";
    """)
    myFixture.checkHighlighting()
  }

  @Test
  fun `test resolve import without explicit import path configuration`() {
    PbProjectSettings.getInstance(myFixture.project).isIndexBasedResolveEnabled = true

    val importedFile = myFixture.addFileToProject("/dir1/dir2/dir3/imported.proto", "")
    myFixture.configureByText("root.proto", """
      import "dir2/dir3/impor<caret>ted.proto"
    """.trimIndent())

    val reference = myFixture.file.findElementAt(myFixture.editor.caretModel.offset)!!.parentOfType<PbImportName>()?.reference
    Assert.assertTrue(reference is PbImportReference)
    Assert.assertEquals(importedFile, reference!!.resolve())
  }

  @Test
  fun `test resolve import from content root`() {
    PbProjectSettings.getInstance(myFixture.project).isIncludeContentRoots = true

    val importedFile = myFixture.addFileToProject("/dir1/dir2/dir3/imported.proto", "")
    myFixture.configureByText("root.proto", """
      import "dir1/dir2/dir3/impor<caret>ted.proto"
    """.trimIndent())

    val reference = myFixture.file.findElementAt(myFixture.editor.caretModel.offset)!!.parentOfType<PbImportName>()?.reference
    Assert.assertTrue(reference is PbImportReference)
    Assert.assertEquals(importedFile, reference!!.resolve())
  }

  @Test
  fun `test resolve import from protobuf folder`() {
    PbProjectSettings.getInstance(myFixture.project).isIncludeProtoDirectories = true

    val importedFile = myFixture.addFileToProject("/proto/imported.proto", "")
    myFixture.configureByText("root.proto", """
      import "impor<caret>ted.proto"
    """.trimIndent())

    val reference = myFixture.file.findElementAt(myFixture.editor.caretModel.offset)!!.parentOfType<PbImportName>()?.reference
    Assert.assertTrue(reference is PbImportReference)
    Assert.assertEquals(importedFile, reference!!.resolve())
  }

  @Test
  fun `test ambiguous import is not highlighted when index search is enabled`() {
    PbProjectSettings.getInstance(myFixture.project).isIndexBasedResolveEnabled = true

    myFixture.addFileToProject("/testroot/google/protobuf/wrappers.proto", """
      message Test {}
    """.trimIndent())
    myFixture.configureByText("main.proto", """
      import "google/protobuf/wrap<caret>pers.proto";
    """.trimIndent())
    myFixture.checkHighlighting(true, true, true)
    val reference = myFixture.getReferenceAtCaretPosition("main.proto")
    Assert.assertEquals(2, (reference as PsiPolyVariantReference).multiResolve(false).size)
  }

  @Test
  fun `test symbol path with ambiguous target can be resolved`() {
    PbProjectSettings.getInstance(myFixture.project).isIndexBasedResolveEnabled = true

    myFixture.addFileToProject("/testroot/google/protobuf/wrappers.proto", """
      package google.protobuf;
      message DoubleValue {}
    """.trimIndent())
    myFixture.configureByText("main.proto", """
      syntax = "proto3";
      import "google/protobuf/wrappers.proto";
      
      message Test {
        google.protobuf.Double<caret>Value value = 1;
      }
    """.trimIndent())
    myFixture.checkHighlighting(true, true, true)
    val reference = myFixture.getReferenceAtCaretPosition("main.proto")
    Assert.assertEquals(1, (reference as PsiPolyVariantReference).multiResolve(false).size)
  }
}