// Copyright 2000-2018 JetBrains s.r.o.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package org.jetbrains.vuejs.language

import com.intellij.codeHighlighting.HighlightDisplayLevel
import com.intellij.codeInsight.daemon.HighlightDisplayKey
import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.lang.javascript.typescript.TypeScriptHighlightingTest
import com.intellij.lang.typescript.inspections.TypeScriptValidateJSTypesInspection
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.profile.codeInspection.InspectionProfileManager

/**
 * @author Irina.Chernushina on 10/24/2017.
 */
class VueTypeScriptHighlightingTest : TypeScriptHighlightingTest() {
  private val whitelist = setOf("AbstractAsVariable",
                                "AbstractClassImplementation",
                                "AbstractClasses",
                                "AbstractClassesCreate",
                                "AbstractClassesGenerics",
                                "AbstractClassesImport",
                                "AbstractClassesImportES6",
                                "AbstractClassesSuperAccess",
                                "AbstractProperties",
                                "AccessToImportStatementFromMergedModule",
                                "Accessibility",
                                "Accessibility2",
                                "AccessibilityProtected",
                                "AccessibilityProtectedConstructorField",
                                "AccessibilityProtectedField",
                                "AccessorExt",
                                "Accessors",
                                "AmbientConst",
                                "AmbientDeclarationConstructorNoErrors",
                                "Annotator",
                                "AnnotatorRestParameter",
                                "AnyTypeAssignmentFieldToObjectLiteral",
                                "ArgumentTypesCheck",
                                "ArrayAsIndexSignature",
                                "ArrayAssignment",
                                "ArrayBuffers",
                                "ArrayComponentType",
                                "ArrayContextTyping",
                                "ArrayExtension",
                                "ArrayInitializer",
                                "ArrayIsArray",
                                "ArrayPushTwoElements",
                                "ArrayStringLiteralTypes",
                                "ArrowFunctionWithConstructorGenerics",
                                "AssignmentGenericToPrimitiveArray",
                                "AsyncAwaitExample",
                                "AsyncFunctionReturnPromise",
                                "AsyncFunctionTypeInference",
                                "AsyncInObjectLiteral",
                                "AsyncPromiseVoid",
                                "AsyncReparse",
                                "BaseClassHasNonTrivialConstructor_DerivedConstructorNotRequired",
                                "BuiltInPartialType",
                                "CacheInvalidationParameter",
                                "CallAndConstructorSignature",
                                "CallSignature",
                                "CallSignature2",
                                "CallSignatureAsFunctionCall",
                                "CallSignatureWithObjectLiteral",
                                "CanBeStaticForFunctionExpression",
                                "CascadeGenerics",
                                "CheckFunctionTypeDeclaration",
                                "CheckTypeContextInterfaceName",
                                "CheckTypeContextModuleName",
                                "CheckTypeContextNestedModuleName",
                                "Class",
                                "Class2",
                                "ClassBaseMembers",
                                "ClassDefaultFromTypeContext",
                                "ClassDefaultGenerics",
                                "ClassExpressionAsGenericParameterType",
                                "ClassExpressionAsParameterType",
                                "ClassExpressionContext",
                                "ClassExpressionGenerics",
                                "ClassExpressionGenericsSimple",
                                "ClassExpressionInExtendSimple",
                                "ClassExpressionWithSameNameNamespace",
                                "ClassExpressionWithStaticGenericParameterType",
                                "ClassExpressionWithoutNameFieldAccess",
                                "ClassExtendExpressionChain",
                                "ClassExtendExpressionCreateInstance",
                                "ClassExtendExpressionValidateSuperCall",
                                "ClassExtendStandardClasses",
                                "ClassExtendWithExpressionGenerics",
                                "ClassExtendsGenericConstructorFunction",
                                "ClassExtendsNull",
                                "ClassExtendsPrimitives",
                                "ClassImplementation",
                                "ClassImplementsRecursiveSOE",
                                "ClassSuperCallWithGenerics",
                                "CompositeMismatch",
                                "CompositeSubstituteToRecord",
                                "CompositeTypeCompare",
                                "CompositeTypeWithCallConstructorSignature",
                                "ComputedClassField",
                                "ComputedProperty",
                                "ConditionalExpressionCrossFile",
                                "ConditionalExpressionWithArrays",
                                "ConstEnum",
                                "ConstVariable",
                                "ConstraintExtendFunction",
                                "ConstructorCall",
                                "ConstructorSuperCallNotHighlightedAsClass",
                                "ContextType",
                                "ContextTypeForIndexedType",
                                "ContextTypeGenericCallSignature",
                                "ConvertVarToLetInspectionForExportDeclare",
                                "CycleReferenceList",
                                "DeclarationFile",
                                "DeclarationMerging",
                                "DeclareWithOverload",
                                "DecoratorExpression",
                                "DecoratorFactory",
                                "DecoratorWithSameNameWithField",
                                "DerivedConstructorValidation",
                                "DestructuringArrayWithFunction",
                                "DestructuringCall",
                                "DestructuringParameterOptional",
                                "DestructuringParameterWithTypeChecking",
                                "DestructuringReassignment",
                                "DestructuringReduceArray",
                                "DestructuringTypeChecking",
                                "DestructuringVariable",
                                "DestructuringWithNesting",
                                "DirectPrototypeFunctionCall",
                                "ES6ArrayAccess",
                                "EnumCompareToReturnType",
                                "EnumFromSecondFile",
                                "EnumImport",
                                "EnumLiteralTypeAssignability",
                                "EnumTypesAreNotAssignable",
                                "EnumValues",
                                "EvaluateReturnType",
                                "ExcessPropertyChecks",
                                "ExcessPropertyChecksObject",
                                "ExplicitThisType",
                                "ExportAssignmentAsExportDefault",
                                "ExportDefault",
                                "ExportImport",
                                "ExportImportInSeveralFiles",
                                "ExportSpecifierGlobalThing",
                                "ExportSpecifierInAmbientModule",
                                "ExportStatement",
                                "ExportStatement2",
                                "ExportStatementInInternalModule",
                                "ExportTypeAliasInExternalModule",
                                "ExportVariableInDeclareModule",
                                "ExportVariableInModule",
                                "ExportedModuleResolve",
                                "ExtendError",
                                "ExtendExpressionSOE",
                                "ExtendFunction",
                                "ExtendPrototype",
                                "ExtendTypeAlias",
                                "ExtendingDefaultClasses",
                                "ExtendsForArrays",
                                "ExtendsMappedType",
                                "ExtendsMappedTypeDeferredGeneric",
                                "ExtendsRecursiveTypeAlias",
                                "ExtendsTypeAliasAssignability",
                                "ExternalModuleOverrideInterfaceWithLocalClass",
                                "ExternalModuleWithOwnStandardClass",
                                "ExternalModules",
                                "ExternalModules2",
                                "FBoundGenericsAndLiterals",
                                "FBounded",
                                "FactoryMethodTypeInference",
                                "FieldImplementsMarkers",
                                "FieldParameterSemantic",
                                "FieldWithSameNameAsClass",
                                "ForAwait",
                                "ForInTypeCheck",
                                "ForInValueInference",
                                "FunctionBody",
                                "FunctionDefaultGenerics",
                                "FunctionGenericType",
                                "FunctionNameProperty",
                                "FunctionParameterGenericsConstructorTypeInference",
                                "FunctionParameterGenericsTypeInference",
                                "FunctionTypeAcceptConstructorType",
                                "FunctionTypeGenerics",
                                "FunctionalExpressionsWithTypeGuard",
                                "GeneratorAmbientContext",
                                "GeneratorClassOverloads",
                                "GeneratorContext",
                                "GeneratorFunctional",
                                "GeneratorOverloadIncorrect",
                                "GeneratorReturnDeclaration",
                                "GeneratorReturnStatement",
                                "GeneratorReturnTypeAssignable",
                                "GeneratorWithNestedGenerator",
                                "GeneratorYieldMultMustBeIterable",
                                "GeneratorsSimpleYield",
                                "GeneratorsSimpleYieldIterable",
                                "GenericDestructuring",
                                "GenericFunctionMap",
                                "GenericFunctions",
                                "GenericInSuperClassFunctionParameter",
                                "GenericIndirectCallSignature",
                                "GenericIndirectConstructorAssignment",
                                "GenericIndirectNewSignature",
                                "GenericIndirectParameterCallSignature",
                                "GenericSeveralSignaturesWithConstraint",
                                "GenericWithNewSignature",
                                "Generics",
                                "Generics3",
                                "Generics4",
                                "GenericsArgumentApplying",
                                "GenericsAssignableForSimilarSignatures",
                                "GenericsAssignment",
                                "GenericsChain",
                                "GenericsCommonTypeErrors",
                                "GenericsConstructorTypeInference",
                                "GenericsConstructorTypeInferenceComplex",
                                "GenericsExtends",
                                "GenericsFindArray",
                                "GenericsHierarchyInterfaces",
                                "GenericsInObjectTypeLiteral",
                                "GenericsInRecordType",
                                "GenericsIndirectVariableMethod",
                                "GenericsMarker",
                                "GenericsMethodCallWithSubclass",
                                "GenericsMethodChain",
                                "GenericsNamesOrder",
                                "GenericsNestedTypeAssignment",
                                "GenericsNestedWithConstraint",
                                "GenericsRecursive",
                                "GenericsResolve",
                                "GenericsReturnNullWithConstraint",
                                "GenericsReturnType",
                                "GenericsSelfReference",
                                "GenericsShouldMatchFirstNonGenericParameter",
                                "GenericsSuperCall",
                                "GenericsTypeAlias",
                                "GenericsTypeAliasesWithUnionTypes",
                                "GenericsTypeInference",
                                "GenericsUnexpected",
                                "GenericsVariableMethodCall",
                                "GenericsWithArrays",
                                "GenericsWithConstraintTypeAssign",
                                "GenericsWithNamelessCalls",
                                "GenericsWithRecursiveConstraint",
                                "GenericsWithoutType",
                                "GetterAndSetterAccessibilityMismatch",
                                "GetterAndSetterMatch",
                                "GetterForInterfaceProperty",
                                "GlobalDeclarations",
                                "GlobalModuleExport",
                                "GlobalNamespaceExportAssignment",
                                "IdenticallyNamedClasses",
                                "IdenticallyNamedClasses2",
                                "IdenticallyNamedClasses3",
                                "IdenticallyNamedClasses4",
                                "IdenticallyNamedInterfaceAndModule",
                                "ImmutableJS",
                                "ImplementThisType",
                                "ImplementationWithGenerics",
                                "ImplementsInClassExpression",
                                "ImplementsMarkerForClass",
                                "ImplicitIndexers",
                                "ImplicitIndexersNoError",
                                "Import",
                                "Import2",
                                "Import3",
                                "Import4",
                                "Import5",
                                "Import6",
                                "Import7",
                                "Import8",
                                "ImportAbstractClassSOE",
                                "ImportCallReferences",
                                "ImportDefaultVisibility",
                                "ImportInternalClassCases",
                                "ImportInternalModule",
                                "ImportInternalModuleWithVariable",
                                "ImportManyDefinitions",
                                "ImportMergeExternalModule",
                                "ImportOverloadFunction",
                                "ImportReferencingImport",
                                "ImportSOE",
                                "ImportUsingExternalModule",
                                "ImportUsingInternalModule",
                                "ImportVar",
                                "IncompatibleOverrideClassHierarchy",
                                "IncompatibleOverrideInterfaceHierarchy",
                                "IncompleteInterface",
                                "IndexSignatureInClass",
                                "IndexSignatureIncorrectType",
                                "IndexSignatureWithGenerics",
                                "IndexedAccessTypeSimple",
                                "IndirectReferenceDefinition",
                                "InferredFunctionParameterError",
                                "InitializerForNullTypes",
                                "InterfaceHierarchyMarkers",
                                "InterfaceImplementation",
                                "InterfaceImplementation3",
                                "InterfaceImplementation4",
                                "InterfaceImplementationWithComplexType",
                                "InterfaceImplementationWithUnionType",
                                "InterfaceInheritance",
                                "InterfaceMergeWithVar",
                                "InterfaceMethodCall",
                                "IntermediateResultsNotCachedForRecursiveTypes",
                                "IntersectionAndUnionTypes",
                                "IntersectionContextualType",
                                "IntersectionEquality",
                                "IntersectionSOE",
                                "IntersectionSOE2",
                                "IntersectionTypeAssignment",
                                "IntersectionTypeInference",
                                "IntersectionTypeMembers",
                                "IntersectionTypeNoRecordType",
                                "IntersectionTypeOverloading",
                                "IntersectionTypeRecursive",
                                "IntersectionTypeWithSeveralReturnCallSignatures",
                                "IterableAssignable",
                                "JSUnusedAssignmentForAmbientVariables",
                                "KeepTypeofType",
                                "KeyofSimple",
                                "KeywordsAsIdentifiers",
                                "LibES6Methods",
                                "LibES6MethodsDisabledByDefault",
                                "LibRecordType",
                                "LiteralEnumVsLiteralEnum",
                                "LiteralEnumsAssignability",
                                "LiteralValuedEnumVsValues",
                                "LocalClassAfterUsage",
                                "LocalClassesTypeHandle",
                                "LocalTypeWithTheSameName",
                                "LocalTypes",
                                "LocalTypesErrors",
                                "LocalTypesGenerics",
                                "LocalTypesNoModifiers",
                                "LocalTypesWithConstructor",
                                "LocalTypesWithParentGenerics",
                                "LongParameterChain",
                                "MappedTypeAlwaysPreserveOptionality",
                                "MappedTypeComplex",
                                "MappedTypeParameter",
                                "MappedTypeTheMostComplexOptionalityPreserving",
                                "MappedTypeWithOptionalProps",
                                "MemberOfOptionalParameter",
                                "MemberVisibilityInModule",
                                "MergeAmbientClassAndInterface",
                                "MergeClassWithModuleInField",
                                "MergeEnum",
                                "MergeFunctionAndModuleInExport",
                                "MergeGenericsClasses",
                                "MergeModuleAndFunction",
                                "MergeModuleWithClass",
                                "MergeModuleWithClassStaticFields",
                                "MergeModuleWithClassStaticFieldsInTwoFiles",
                                "MergeModuleWithNestedInterface",
                                "MergeVariableAndModuleInExport",
                                "MethodAsFunction",
                                "MethodSeparators",
                                "MissingAwaitCall",
                                "MissingExplicitTypeDeclarations",
                                "ModuleAsType",
                                "ModuleAsTypeInImport",
                                "ModuleClassVisibility",
                                "ModuleName",
                                "ModuleReferencePartialQualifiedName",
                                "ModuleWithJSExport",
                                "ModuleWithNestedSameNameClass",
                                "ModuleWithRequire",
                                "Modules2",
                                "Modules3",
                                "Modules6",
                                "ModulesAmbientExternal",
                                "ModulesCountMoreRestriction",
                                "ModulesExternal",
                                "ModulesExternal2",
                                "ModulesNamesResolution",
                                "ModulesTwoTopLevel",
                                "NamespaceImportColor",
                                "NestedInterfaceHasPriorityOverGlobal",
                                "NestedModuleAugmentation",
                                "NeverType",
                                "NewSignature",
                                "NewTarget",
                                "NoCanBeStaticForOverloads",
                                "NoErrorsInPlainJS",
                                "NoInfiniteRecursion",
                                "NoRecordTypeUsage",
                                "NoRecordTypeUsageGenerics",
                                "NoResolveToOverloadImplementation",
                                "NoResolveToOverloadImplementationGlobalFunction",
                                "NotExportedModule",
                                "NotNullOperator",
                                "NullInitializedValueIsAny",
                                "NullInitializerAsAnyType",
                                "NullTypeParse",
                                "ObjectLiteralLastComma",
                                "ObjectLiteralShortProperties",
                                "ObjectLiteralTypeInference",
                                "ObjectLiteralTypeInferenceVariable",
                                "ObjectLiteralTypes",
                                "ObjectPropertiesForTypes",
                                "ObjectTypeLiteralWithOverloadMethod",
                                "ObjectTypeUsage",
                                "OptionalClassMembers",
                                "OptionalMethodImplement",
                                "OptionalMethodOverride",
                                "OptionalParameterFollowsInitializedOptionalParameter",
                                "OptionalParameterWithInitializer",
                                "OverloadFromOtherFile",
                                "OverloadInterfaceMember",
                                "OverloadReassignment",
                                "OverloadSeveralParams",
                                "OverloadStandard",
                                "OverloadWithAnyType",
                                "OverloadWithSeveralSimilarSignatures",
                                "Overloading",
                                "OverloadsWithLiterals",
                                "OverrideClassGenerics",
                                "OverrideGenerics",
                                "OverrideInterfaceMarkers",
                                "OverridingMarkers",
                                "ParenthesesInType",
                                "PartialType",
                                "PrimitiveArrayContextType",
                                "PrimitiveObjectType",
                                "PromiseThenParameter",
                                "PropertyMarker",
                                "QueryInspection",
                                "ReExportDefaultSOE",
                                "React",
                                "ReactDef",
                                "ReadonlyAssignmentClassErrors",
                                "ReadonlyAssignmentInterfaceErrors",
                                "ReadonlyConstructorParam",
                                "RecordTypeNested",
                                "RecordTypeSOE",
                                "RecursiveCompositeSOE",
                                "RecursiveDirectCompositeType",
                                "RecursiveIndirectCompositeType",
                                "RecursiveRecordType",
                                "RecursiveTypeWithAny",
                                "RedundantVariableTypeDeclarations",
                                "ReferencePaths",
                                "RequireJSModules",
                                "RequireNonexistentFileByRelativePath",
                                "RequireStrictTypeChecking",
                                "RequireVarRef",
                                "ResolveConstructor",
                                "ResolveFromJavaScript",
                                "ResolveInObjectType",
                                "ResolveMembers",
                                "ResolveMembers2",
                                "ResolveModuleWithSameNameAsInterface",
                                "ResolveObjectMembers",
                                "ResolveSuper",
                                "ResolveSuper2",
                                "ResolveToObjectTypeParameters",
                                "ResolveTypeProperties",
                                "RestArgsGenericTypes",
                                "RestArray",
                                "RestOptional",
                                "ReturnBinaryExpression",
                                "ReturnEmptyInFunction",
                                "ReturnFunctionWithOverloads",
                                "Rx",
                                "RxUsagePerformance",
                                "Rxes6",
                                "SOE",
                                "SOE2",
                                "SOE3",
                                "SOEExportSpecifier",
                                "SameNameInterfaceClass",
                                "SecondDefaultGeneric",
                                "SelectGenericSignature",
                                "SemanticHighlighting",
                                "SemanticKeywords",
                                "SignatureAssignable",
                                "SimpleAssignWithInitializer",
                                "SimpleExtendsTypeAlias",
                                "SimpleGlobal",
                                "SimpleInheritance",
                                "SimpleInheritance2",
                                "SkipJSDocTypes",
                                "SpellChecker",
                                "StaticClassReferenceIsFunction",
                                "StaticOrInstance",
                                "StringIndexSignatureProperty",
                                "StringLiteralInterfaceImplementation",
                                "StringLiteralTypeAssignment",
                                "StringLiteralTypeInConditionalExpression",
                                "StringLiteralTypeUnion",
                                "StringLiteralTypesInArray",
                                "StringLiteralWithPar",
                                "StringLiteralWithUnion",
                                "StringOverload",
                                "StringTemplate",
                                "StructuralInheritance",
                                "Stubs",
                                "SubtypingInGuard",
                                "SuperClassFunction",
                                "SuperNoClassName",
                                "SymbolSignature",
                                "ThisCallbackType",
                                "ThisFunctionParameter",
                                "ThisFunctionParameterType",
                                "ThisParameterCall",
                                "ThisParameterOverride",
                                "ThisParameterType",
                                "ThisPredicateType",
                                "ThisType",
                                "ThisTypeExtends",
                                "ThisTypeFluentPattern",
                                "ThisTypeInClasses",
                                "ThisTypeInInterfaces",
                                "ThisTypeInSignature",
                                "ThisTypeReturn",
                                "ThisTypeWithInterfaceMerge",
                                "TupleImplicitly",
                                "TupleType",
                                "TypeAlias",
                                "TypeAliasArrays",
                                "TypeAliasAsInterfaceField",
                                "TypeAliasAssignableForParentType",
                                "TypeAliasClassAssignments",
                                "TypeAliasErrors",
                                "TypeAliasFieldAccess",
                                "TypeAliasForCallSignatureType",
                                "TypeAliasForEqualNames",
                                "TypeAliasForFunctionType",
                                "TypeAliasGenerics",
                                "TypeAliasMethodCallForField",
                                "TypeAliasMultiLevelResolve",
                                "TypeAliasOverride",
                                "TypeAliasPosition",
                                "TypeAliasSOEOnAliasRefAlias",
                                "TypeAliasSOEOnRecursiveType",
                                "TypeAliasSOEOnRecursiveType2",
                                "TypeAliasSOEOnRecursiveType3",
                                "TypeAliasSOEOnRecursiveType4",
                                "TypeAliasTypeAssertion",
                                "TypeAliasTypeAssignments",
                                "TypeAliasTypes",
                                "TypeAliasWithFunctionType",
                                "TypeAliasWithNameInt",
                                "TypeAliasesEqualsByTypes",
                                "TypeAssertions",
                                "TypeCastAsExpression",
                                "TypeCheckInJavaScript",
                                "TypeContext",
                                "TypeGuardComposite",
                                "TypeGuardCompositeElse",
                                "TypeGuardForModuleReference",
                                "TypeGuardForOf",
                                "TypeGuardForRecordType",
                                "TypeGuardLiteral",
                                "TypeGuardNamedClasses",
                                "TypeGuardQualified",
                                "TypeGuardWithIndirectTypeAlias",
                                "TypeGuardWithTypeOf",
                                "TypeInferenceForParameterWithInitializer",
                                "TypeMembersWithoutSemicolon",
                                "TypeOfInField",
                                "TypeOfInFieldComplex",
                                "TypeOfInFunctionBody",
                                "TypeOfQualifiedName",
                                "TypeOfStaticField",
                                "TypeOfStubs",
                                "TypeOfTypeSOE",
                                "TypeOfWithOverloads",
                                "TypePredicateAssignableSignatureType",
                                "TypePredicateCall",
                                "TypePredicateDeclaration",
                                "TypePredicateForConditions",
                                "TypePredicateGenerics",
                                "TypeScriptGenericSignatureSelect",
                                "TypeScriptOptionalInInterface",
                                "Typeof",
                                "Typeof2",
                                "TypeofImports",
                                "TypesAccess",
                                "TypesAssignments",
                                "TypesAssignments2",
                                "TypesAssignmentsFromFunctionWithTypeGuard",
                                "TypesAssignmentsToFunctionType",
                                "TypesAssignmentsToFunctionType2",
                                "TypesAssignmentsToFunctionType3",
                                "TypesAssignmentsToInterface",
                                "TypesAssignmentsToInterface2",
                                "TypesAssignmentsToInterface3",
                                "TypesAssignmentsToInterface4",
                                "UndefinedAsModuleName",
                                "UndefinedTypeParse",
                                "UninitializedVariableForDestructuring",
                                "UnionAndIntersectionSimplification",
                                "UnionAndIntersectionWithAliases",
                                "UnionStringArrayTypes",
                                "UnionTypeThreeLiterals",
                                "UnionTypeWithLocalClasses",
                                "UnionTypeWithMethodCall",
                                "UnionTypeWithObjectLiterals",
                                "UnionTypes",
                                "UnionTypesAssignments",
                                "UnionTypesAssignmentsPrimitive",
                                "UnionTypesWithTypeGuard",
                                "UnusedExportModuleFunction",
                                "UnusedFieldParameter",
                                "UnusedMembers",
                                "UseImportExternalModule",
                                "UseImportInternalModule",
                                "UseStrict",
                                "VarCacheInvalidation",
                                "VariableCanBeVoidType",
                                "VariableGenericCallSignatures",
                                "VoidAsType",
                                "XmlHttpRequestApi",
                                "YieldInDecorator")

  override fun doHighlightingWithInvokeFixAndCheckResult(fixName: String?,
                                                         ext: String?,
                                                         vararg files: String?): MutableCollection<HighlightInfo> {
    LOG.info("Running overridden code for vue")
    if (skipTest()) {
      LOG.info("Skipping muted test")
      return mutableListOf()
    }
    return super.doHighlightingWithInvokeFixAndCheckResult(fixName, ext, *files)
  }

  override fun doTestWithExplicitAssertOnRecursion(assertOnRecursion: Boolean, 
                                                   checkWeakWarnings: Boolean, vararg fileNames: String?): MutableCollection<HighlightInfo> {
    LOG.info("Running overridden code for vue")
    if (skipTest()) {
      LOG.info("Skipping muted test")
      return mutableListOf()
    }
    if (fileNames.size == 1 && fileNames[0]!!.endsWith(".d.ts")) {
      LOG.info("Skipping because only .d.ts file for test")
      return mutableListOf()
    }
    if (fileNames.size > 1) {
      LOG.info("Skipping because several files")
      return mutableListOf()
    }

    val rollback = ContextCreator().createContext(project)
    try {
      return super.doTestWithExplicitAssertOnRecursion(false, checkWeakWarnings, *fileNames)
    }
    finally {
      rollback()
    }
  }

  private fun skipTest() = !whitelist.contains(getTestName(false))

  companion object {
    private class ContextCreator {
      private val inspections = arrayOf(TypeScriptValidateJSTypesInspection().shortName/*,
                                        TypeScriptUnresolvedVariableInspection().shortName*/)
      private val was: MutableMap<HighlightDisplayKey, HighlightDisplayLevel> = mutableMapOf()

      fun createContext(project: Project): () -> Unit {
        val manager = InspectionProfileManager.getInstance(project)
        val profile = manager.currentProfile
        inspections.forEach {
          val key = HighlightDisplayKey.find(it)
          was.put(key, profile.getToolDefaultState(it, project).level)
          profile.setErrorLevel(key, HighlightDisplayLevel.ERROR, project)
        }
        return { was.forEach { profile.setErrorLevel(it.key, it.value, project) } }
      }
    }
  }

  override fun findVirtualFile(filePath: String): VirtualFile {
    val original = super.findVirtualFile(filePath)
    if (filePath.endsWith(".d.ts")) return original

    val text = VfsUtil.loadText(original)
    val withoutExtension = filePath.substringBeforeLast("", filePath)
    val ioFile = createTempFile(withoutExtension.substringAfterLast("/", withoutExtension) + ".vue",
                                "<script lang=\"ts\">\n" + text + "\n</script>")
    return LocalFileSystem.getInstance().refreshAndFindFileByIoFile(ioFile)!!
  }

  // these tests need to be ignored with additional code:
  override fun testKeepTypeofType() {
    LOG.info("Skipping muted test")
  }

  override fun testIntermediateResultsNotCachedForRecursiveTypes() {
    LOG.info("Skipping muted test")
  }

  override fun testAsyncReparse() {
    LOG.info("Skipping muted test")
  }
}