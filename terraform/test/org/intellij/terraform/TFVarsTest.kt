package org.intellij.terraform

import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.intellij.terraform.config.inspection.TFDuplicatedVariableInspection
import org.intellij.terraform.config.inspection.TFVARSIncorrectElementInspection
import org.intellij.terraform.config.model.local.TERRAFORM_LOCK_FILE_NAME

@Suppress("UsagesOfObsoleteApi")
class TFVarsTest : BasePlatformTestCase() {


  fun testSameDir() {

    myFixture.enableInspections(TFVARSIncorrectElementInspection::class.java)

    myFixture.configureByText("simple.tf", """
      variable "foo" {
        default = "42"
        type = "string"
      }
      variable "baz" {
        type = "map"
      }
    """.trimIndent())

    myFixture.configureByText("local.tfvars", """
      foo = "9000"
      baz = <warning descr="Incorrect variable value type. Expected: 'map'">1</warning>
      <warning descr="Undefined variable 'bar'">bar</warning> = 0
    """.trimIndent())

    myFixture.testHighlighting("local.tfvars")

  }

  fun testDifferentDirsWithoutLock() {

    myFixture.enableInspections(TFVARSIncorrectElementInspection::class.java)

    myFixture.configureByText("simple.tf", """
      variable "foo" {
        default = "42"
        type = "string"
      }
      variable "baz" {
        type = "map"
      }
    """.trimIndent())

    val fileName = "dir/prod/prod.tfvars"
    configureByTextInDir(fileName, """
      <warning descr="Undefined variable 'foo'">foo</warning> = "9000"
      <warning descr="Undefined variable 'baz'">baz</warning> = 1
      <warning descr="Undefined variable 'bar'">bar</warning> = 0
    """.trimIndent())
    myFixture.testHighlighting(fileName)
  }

  fun testDifferentDirsWithLock() {

    myFixture.enableInspections(TFVARSIncorrectElementInspection::class.java)
    myFixture.configureByText(TERRAFORM_LOCK_FILE_NAME, "")

    myFixture.configureByText("simple.tf", """
      variable "foo" {
        default = "42"
        type = "string"
      }
      variable "baz" {
        type = "map"
      }
    """.trimIndent())

    val fileName = "dir/prod/prod.tfvars"
    configureByTextInDir(fileName, """
      foo = "9000"
      baz = <warning descr="Incorrect variable value type. Expected: 'map'">1</warning>
      <warning descr="Undefined variable 'bar'">bar</warning> = 0
    """.trimIndent())
    myFixture.testHighlighting(fileName)
  }

  fun testNoDifferentDirsDuplicates() {

    myFixture.enableInspections(TFDuplicatedVariableInspection::class.java)
    configureByTextInDir("dir1/$TERRAFORM_LOCK_FILE_NAME", "")

    configureByTextInDir("dir1/samedir1.tf", """
      variable "foo" {
        default = "42"
        type = "string"
      }
    """.trimIndent())

    configureByTextInDir("dir1/samedir2.tf", """
      <error descr="Variable 'foo' declared multiple times">variable "foo" {
        default = "42"
        type = "string"
      }</error>
    """.trimIndent())
    myFixture.testHighlighting("dir1/samedir2.tf")


    configureByTextInDir("dir2/file.tf", """
      variable "foo" {
        default = "42"
        type = "string"
      }
    """.trimIndent())
    myFixture.testHighlighting("dir2/file.tf")

  }

  fun testCompletionFromMultipleSources() {

    myFixture.enableInspections(TFVARSIncorrectElementInspection::class.java)
    myFixture.configureByText(TERRAFORM_LOCK_FILE_NAME, "")

    myFixture.configureByText("simple.tf", """
      variable "foo1" {
        default = "42"
        type = "string"
      }
      variable "baz1" {
        type = "map"
      }
    """.trimIndent())

    configureByTextInDir("dir/prod/another.tf", """
      variable "foo2" {
        default = "42"
        type = "string"
      }
      variable "baz2" {
        type = "map"
      }
    """.trimIndent())

    val fileName = "dir/prod/prod.tfvars"
    configureByTextInDir(fileName, """
      <caret>
    """.trimIndent())
    myFixture.testHighlighting(fileName)
    myFixture.testCompletionVariants("dir/prod/prod.tfvars", "foo1", "foo2", "baz1", "baz2")
  }


  private fun configureByTextInDir(fileName: String, text: String) {
    WriteAction.compute<VirtualFile, Throwable> {
      val prodTfvarsFile: VirtualFile = myFixture.tempDirFixture.createFile(fileName)
      VfsUtil.saveText(prodTfvarsFile, text)
      prodTfvarsFile
    }
  }

}