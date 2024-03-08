// This is a generated file. Not intended for manual editing.
package org.intellij.terraform.hil.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.terraform.hil.HILElementTypes.*;
import org.intellij.terraform.hil.psi.*;

public class ElseConditionImpl extends ILExpressionBase implements ElseCondition {

  public ElseConditionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ILGeneratedVisitor visitor) {
    visitor.visitElseCondition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ILGeneratedVisitor) accept((ILGeneratedVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getTemplateStart() {
    return findNotNullChildByType(TEMPLATE_START);
  }

}
